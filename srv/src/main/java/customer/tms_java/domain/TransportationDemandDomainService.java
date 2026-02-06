package customer.tms_java.domain;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sap.cds.ql.Delete;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.Update;
import com.sap.cds.services.ErrorStatuses;
import com.sap.cds.services.ServiceException;
import com.sap.cds.services.persistence.PersistenceService;

import cds.gen.tms.FreightOrderItem_;
import cds.gen.tms.TransportationDemandItem;
import cds.gen.tms.TransportationDemandItem_;
import cds.gen.transportationdemanddomainservice.TransportationDemands;
import cds.gen.transportationdemanddomainservice.TransportationDemands_;

@Service
public class TransportationDemandDomainService {

    private final PersistenceService db;

    public TransportationDemandDomainService(PersistenceService db) {
        this.db = db;
    }

    public TransportationDemands assign(String tdId, String freightOrderId) {
        TransportationDemands td = load(tdId);

        if (td.getFreightOrderId() != null) {
            throw new ServiceException(
                    ErrorStatuses.CONFLICT,
                    "Transportation Demand is already assigned to a Freight Order");
        }

        db.run(
                Update.entity(TransportationDemands_.class)
                        .data(TransportationDemands.FREIGHT_ORDER_ID, freightOrderId)
                        .where(t -> t.ID().eq(tdId)));

        return load(tdId);
    }

    public TransportationDemands unassign(String tdId) {
        TransportationDemands td = load(tdId);

        if (td.getFreightOrderId() == null) {
            throw new ServiceException(
                    ErrorStatuses.BAD_REQUEST,
                    "Transportation Demand is not assigned to any Freight Order");
        }

        List<TransportationDemandItem> items = db.run(
                Select.from(TransportationDemandItem_.class)
                        .where(i -> i.transportationDemand_ID().eq(tdId)))
                .listOf(TransportationDemandItem.class);

        items.forEach(item -> db.run(
                Delete.from(FreightOrderItem_.class)
                        .where(i -> i.transportationDemandItem_ID().eq(item.getId()))));

        db.run(
                Update.entity(TransportationDemands_.class)
                        .data(TransportationDemands.FREIGHT_ORDER_ID, null)
                        .where(t -> t.ID().eq(tdId)));

        return load(tdId);
    }

    private TransportationDemands load(String id) {
        return db.run(
                Select.from(TransportationDemands_.class)
                        .where(t -> t.ID().eq(id)))
                .single(TransportationDemands.class);
    }
}
