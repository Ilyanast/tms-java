package customer.tms_java.handlers;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.draft.DraftService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.Before;
import com.sap.cds.services.handler.annotations.HandlerOrder;
import com.sap.cds.services.handler.annotations.ServiceName;

import cds.gen.tms.FreightOrder;
import cds.gen.tms.FreightOrderItem;
import cds.gen.tms.FreightOrderItem_;
import cds.gen.tms.FreightOrder_;
import cds.gen.tms.Location;
import cds.gen.tms.Location_;
import cds.gen.tms.TransportationDemand;
import cds.gen.tms.TransportationDemandItem;
import cds.gen.tms.TransportationDemandItem_;
import cds.gen.tms.TransportationDemand_;

import cds.gen.transportationdemandservice.TransportationDemands;
import cds.gen.transportationdemandservice.TransportationDemands_;
import cds.gen.transportationdemandservice.TransportationDemandItems;
import cds.gen.transportationdemandservice.TransportationDemandItems_;
import cds.gen.freightorderservice.FreightOrders;
import cds.gen.freightorderservice.FreightOrders_;
import cds.gen.freightorderservice.FreightOrderItems;
import cds.gen.freightorderservice.FreightOrderItems_;

@Component
@ServiceName("*")
public class DisplayIdGeneratorHandler implements EventHandler {

    @Before(event = CqnService.EVENT_CREATE, entity = Location_.CDS_NAME)
    @HandlerOrder(HandlerOrder.EARLY)
    public void generateLocationDisplayId(List<Location> locations) {
        if (locations == null)
            return;
        locations.forEach(location -> {
            if (location.getDisplayId() == null) {
                location.setDisplayId(generateDisplayId("LOC"));
            }
        });
    }

    @Before(event = CqnService.EVENT_CREATE, entity = TransportationDemand_.CDS_NAME)
    @HandlerOrder(HandlerOrder.EARLY)
    public void generateTdDisplayId(List<TransportationDemand> tds) {
        if (tds == null)
            return;
        tds.forEach(td -> {
            if (td.getDisplayId() == null) {
                td.setDisplayId(generateDisplayId("TD"));
            }
            processNestedTdItems(td.getItems());
        });
    }

    @Before(event = CqnService.EVENT_CREATE, entity = TransportationDemandItem_.CDS_NAME)
    @HandlerOrder(HandlerOrder.EARLY)
    public void generateTdItemDisplayId(List<TransportationDemandItem> items) {
        if (items == null)
            return;
        items.forEach(item -> {
            if (item.getDisplayId() == null) {
                item.setDisplayId(generateDisplayId("TDI"));
            }
        });
    }

    @Before(event = CqnService.EVENT_CREATE, entity = FreightOrder_.CDS_NAME)
    @HandlerOrder(HandlerOrder.EARLY)
    public void generateFoDisplayId(List<FreightOrder> fos) {
        if (fos == null)
            return;
        fos.forEach(fo -> {
            if (fo.getDisplayId() == null) {
                fo.setDisplayId(generateDisplayId("FO"));
            }
        });
    }

    @Before(event = CqnService.EVENT_CREATE, entity = FreightOrderItem_.CDS_NAME)
    @HandlerOrder(HandlerOrder.EARLY)
    public void generateFoItemDisplayId(List<FreightOrderItem> items) {
        if (items == null)
            return;
        items.forEach(item -> {
            if (item.getDisplayId() == null) {
                item.setDisplayId(generateDisplayId("FOI"));
            }
        });
    }

    @Before(event = CqnService.EVENT_CREATE, entity = TransportationDemands_.CDS_NAME)
    @HandlerOrder(HandlerOrder.EARLY)
    public void generateTdServiceDisplayId(List<TransportationDemands> tds) {
        if (tds == null)
            return;
        tds.forEach(td -> {
            if (td.getDisplayId() == null) {
                td.setDisplayId(generateDisplayId("TD"));
            }
            processNestedTdServiceItems(td.getItems());
        });
    }

    @Before(event = CqnService.EVENT_CREATE, entity = TransportationDemandItems_.CDS_NAME)
    @HandlerOrder(HandlerOrder.EARLY)
    public void generateTdItemServiceDisplayId(List<TransportationDemandItems> items) {
        if (items == null)
            return;
        items.forEach(item -> {
            if (item.getDisplayId() == null) {
                item.setDisplayId(generateDisplayId("TDI"));
            }
        });
    }

    @Before(event = CqnService.EVENT_CREATE, entity = FreightOrders_.CDS_NAME)
    @HandlerOrder(HandlerOrder.EARLY)
    public void generateFoServiceDisplayId(List<FreightOrders> fos) {
        if (fos == null)
            return;
        fos.forEach(fo -> {
            if (fo.getDisplayId() == null) {
                fo.setDisplayId(generateDisplayId("FO"));
            }
        });
    }

    @Before(event = CqnService.EVENT_CREATE, entity = FreightOrderItems_.CDS_NAME)
    @HandlerOrder(HandlerOrder.EARLY)
    public void generateFoItemServiceDisplayId(List<FreightOrderItems> items) {
        if (items == null)
            return;
        items.forEach(item -> {
            if (item.getDisplayId() == null) {
                item.setDisplayId(generateDisplayId("FOI"));
            }
        });
    }

    @Before(event = DraftService.EVENT_DRAFT_NEW, entity = TransportationDemands_.CDS_NAME)
    @HandlerOrder(HandlerOrder.EARLY)
    public void generateTdDraftDisplayId(List<TransportationDemands> tds) {
        if (tds == null)
            return;
        tds.forEach(td -> {
            if (td.getDisplayId() == null) {
                td.setDisplayId(generateDisplayId("TD"));
            }
            processNestedTdServiceItems(td.getItems());
        });
    }

    @Before(event = DraftService.EVENT_DRAFT_NEW, entity = TransportationDemandItems_.CDS_NAME)
    @HandlerOrder(HandlerOrder.EARLY)
    public void generateTdItemDraftDisplayId(List<TransportationDemandItems> items) {
        if (items == null)
            return;
        items.forEach(item -> {
            if (item.getDisplayId() == null) {
                item.setDisplayId(generateDisplayId("TDI"));
            }
        });
    }

    @Before(event = DraftService.EVENT_DRAFT_NEW, entity = FreightOrders_.CDS_NAME)
    @HandlerOrder(HandlerOrder.EARLY)
    public void generateFoDraftDisplayId(List<FreightOrders> fos) {
        if (fos == null)
            return;
        fos.forEach(fo -> {
            if (fo.getDisplayId() == null) {
                fo.setDisplayId(generateDisplayId("FO"));
            }
        });
    }

    @Before(event = DraftService.EVENT_DRAFT_NEW, entity = FreightOrderItems_.CDS_NAME)
    @HandlerOrder(HandlerOrder.EARLY)
    public void generateFoItemDraftDisplayId(List<FreightOrderItems> items) {
        if (items == null)
            return;
        items.forEach(item -> {
            if (item.getDisplayId() == null) {
                item.setDisplayId(generateDisplayId("FOI"));
            }
        });
    }

    @Before(event = DraftService.EVENT_DRAFT_PATCH, entity = TransportationDemandItems_.CDS_NAME)
    @HandlerOrder(HandlerOrder.EARLY)
    public void generateTdItemDraftPatchDisplayId(List<TransportationDemandItems> items) {
        if (items == null)
            return;
        items.forEach(item -> {
            if (item.getDisplayId() == null) {
                item.setDisplayId(generateDisplayId("TDI"));
            }
        });
    }

    @Before(event = DraftService.EVENT_DRAFT_SAVE, entity = TransportationDemands_.CDS_NAME)
    @HandlerOrder(HandlerOrder.EARLY)
    public void ensureTdDisplayIdOnSave(List<TransportationDemands> tds) {
        if (tds == null)
            return;
        tds.forEach(td -> {
            if (td.getDisplayId() == null) {
                td.setDisplayId(generateDisplayId("TD"));
            }
            processNestedTdServiceItems(td.getItems());
        });
    }

    @Before(event = DraftService.EVENT_DRAFT_SAVE, entity = TransportationDemandItems_.CDS_NAME)
    @HandlerOrder(HandlerOrder.EARLY)
    public void ensureTdItemDisplayIdOnSave(List<TransportationDemandItems> items) {
        if (items == null)
            return;
        items.forEach(item -> {
            if (item.getDisplayId() == null) {
                item.setDisplayId(generateDisplayId("TDI"));
            }
        });
    }

    private void processNestedTdItems(List<TransportationDemandItem> items) {
        if (items == null)
            return;
        items.forEach(item -> {
            if (item.getDisplayId() == null) {
                item.setDisplayId(generateDisplayId("TDI"));
            }
        });
    }

    private void processNestedTdServiceItems(List<TransportationDemandItems> items) {
        if (items == null)
            return;
        items.forEach(item -> {
            if (item.getDisplayId() == null) {
                item.setDisplayId(generateDisplayId("TDI"));
            }
        });
    }

    private String generateDisplayId(String prefix) {
        return prefix + "-" + UUID.randomUUID().toString().replace("-", "").substring(0, 5).toUpperCase();
    }
}
