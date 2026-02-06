package customer.tms_java.domain;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sap.cds.ql.Delete;
import com.sap.cds.ql.Insert;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.Update;
import com.sap.cds.services.ErrorStatuses;
import com.sap.cds.services.ServiceException;
import com.sap.cds.services.persistence.PersistenceService;

import cds.gen.tms.FreightOrder;
import cds.gen.tms.FreightOrder_;
import cds.gen.tms.FreightOrderItem;
import cds.gen.tms.FreightOrderItem_;
import cds.gen.tms.TransportationDemand;
import cds.gen.tms.TransportationDemand_;
import cds.gen.tms.TransportationDemandItem;
import cds.gen.tms.TransportationDemandItem_;

@Service
public class FreightOrderDomainService {

    private static final String IN_PLANNING = "IN_PLANNING";
    private static final String READY_FOR_EXECUTION = "READY_FOR_EXECUTION";
    private static final String IN_EXECUTION = "IN_EXECUTION";

    private static final Set<String> VALID_TRANSITIONS = Set.of(
            IN_PLANNING + "->" + READY_FOR_EXECUTION,
            READY_FOR_EXECUTION + "->" + IN_PLANNING,
            READY_FOR_EXECUTION + "->" + IN_EXECUTION);

    private final PersistenceService db;

    public FreightOrderDomainService(PersistenceService db) {
        this.db = db;
    }

    public void assignTd(String foId, String tdDisplayId) {
        FreightOrder fo = loadFo(foId);

        requireInPlanning(fo, "assign TD");

        TransportationDemand td = db.run(
                Select.from(TransportationDemand_.class)
                        .where(t -> t.displayId().eq(tdDisplayId)))
                .first(TransportationDemand.class)
                .orElseThrow(() -> new ServiceException(
                        ErrorStatuses.NOT_FOUND,
                        "Transportation Demand not found"));

        if (td.getFreightOrderId() != null) {
            throw new ServiceException(
                    ErrorStatuses.CONFLICT,
                    "Transportation Demand already assigned");
        }

        db.run(
                Update.entity(TransportationDemand_.class)
                        .data(TransportationDemand.FREIGHT_ORDER_ID, foId)
                        .where(t -> t.ID().eq(td.getId())));

        List<TransportationDemandItem> tdItems = db.run(
                Select.from(TransportationDemandItem_.class)
                        .where(i -> i.transportationDemand_ID().eq(td.getId())))
                .listOf(TransportationDemandItem.class);

        for (TransportationDemandItem tdItem : tdItems) {
            FreightOrderItem item = FreightOrderItem.create();

            item.setId(UUID.randomUUID().toString());
            item.setDisplayId(generateDisplayId("FOI"));
            item.setFreightOrderId(foId);
            item.setProductName(tdItem.getProductName());
            item.setQuantity(tdItem.getQuantity());
            item.setTransportationDemandItemId(tdItem.getId());

            db.run(Insert.into(FreightOrderItem_.class).entry(item));
        }
    }

    public void unassignTd(String foId, String tdId) {
        FreightOrder fo = loadFo(foId);

        requireInPlanning(fo, "unassign TD");

        TransportationDemand td = loadTd(tdId);

        if (!foId.equals(td.getFreightOrderId())) {
            throw new ServiceException(
                    ErrorStatuses.BAD_REQUEST,
                    "TD is not assigned to this Freight Order");
        }

        List<TransportationDemandItem> tdItems = db.run(
                Select.from(TransportationDemandItem_.class)
                        .where(i -> i.transportationDemand_ID().eq(tdId)))
                .listOf(TransportationDemandItem.class);

        tdItems.forEach(item -> db.run(
                Delete.from(FreightOrderItem_.class)
                        .where(i -> i.transportationDemandItem_ID().eq(item.getId()))));

        db.run(
                Update.entity(TransportationDemand_.class)
                        .data(TransportationDemand.FREIGHT_ORDER_ID, null)
                        .where(t -> t.ID().eq(tdId)));
    }

    public void setStatus(String foId, String newStatus) {
        FreightOrder fo = loadFo(foId);

        String current = fo.getStatusCode();
        if (current.equals(newStatus)) {
            return;
        }

        if (!VALID_TRANSITIONS.contains(current + "->" + newStatus)) {
            throw new ServiceException(
                    ErrorStatuses.CONFLICT,
                    "Invalid status transition");
        }

        db.run(
                Update.entity(FreightOrder_.class)
                        .data(FreightOrder.STATUS_CODE, newStatus)
                        .where(f -> f.ID().eq(foId)));
    }

    private FreightOrder loadFo(String id) {
        return db.run(
                Select.from(FreightOrder_.class)
                        .where(f -> f.ID().eq(id)))
                .single(FreightOrder.class);
    }

    private TransportationDemand loadTd(String id) {
        return db.run(
                Select.from(TransportationDemand_.class)
                        .where(t -> t.ID().eq(id)))
                .single(TransportationDemand.class);
    }

    private void requireInPlanning(FreightOrder fo, String action) {
        if (!IN_PLANNING.equals(fo.getStatusCode())) {
            throw new ServiceException(
                    ErrorStatuses.CONFLICT,
                    "Cannot " + action + ": Freight Order must be IN_PLANNING");
        }
    }

    private String generateDisplayId(String prefix) {
        return prefix + "-" + UUID.randomUUID().toString().replace("-", "").substring(0, 5).toUpperCase();
    }
}
