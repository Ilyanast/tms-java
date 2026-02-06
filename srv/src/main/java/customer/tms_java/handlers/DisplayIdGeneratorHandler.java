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

import cds.gen.tms.Location;
import cds.gen.tms.Location_;

import cds.gen.freightorderuiservice.FreightOrders;
import cds.gen.freightorderuiservice.FreightOrders_;
import cds.gen.freightorderuiservice.FreightOrderItems;
import cds.gen.freightorderuiservice.FreightOrderItems_;

import cds.gen.transportationdemanduiservice.TransportationDemands;
import cds.gen.transportationdemanduiservice.TransportationDemands_;
import cds.gen.transportationdemanduiservice.TransportationDemandItems;
import cds.gen.transportationdemanduiservice.TransportationDemandItems_;

@Component
@ServiceName("*")
public class DisplayIdGeneratorHandler implements EventHandler {

    // Location - базовая сущность
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

    // FreightOrderUIService - FreightOrders
    @Before(event = CqnService.EVENT_CREATE, entity = FreightOrders_.CDS_NAME)
    @HandlerOrder(HandlerOrder.EARLY)
    public void generateFoDisplayId(List<FreightOrders> fos) {
        if (fos == null)
            return;
        fos.forEach(fo -> {
            if (fo.getDisplayId() == null) {
                fo.setDisplayId(generateDisplayId("FO"));
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

    // FreightOrderUIService - FreightOrderItems
    @Before(event = CqnService.EVENT_CREATE, entity = FreightOrderItems_.CDS_NAME)
    @HandlerOrder(HandlerOrder.EARLY)
    public void generateFoItemDisplayId(List<FreightOrderItems> items) {
        if (items == null)
            return;
        items.forEach(item -> {
            if (item.getDisplayId() == null) {
                item.setDisplayId(generateDisplayId("FOI"));
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

    // TransportationDemandUIService - TransportationDemands
    @Before(event = CqnService.EVENT_CREATE, entity = TransportationDemands_.CDS_NAME)
    @HandlerOrder(HandlerOrder.EARLY)
    public void generateTdDisplayId(List<TransportationDemands> tds) {
        if (tds == null)
            return;
        tds.forEach(td -> {
            if (td.getDisplayId() == null) {
                td.setDisplayId(generateDisplayId("TD"));
            }
            processNestedTdItems(td.getItems());
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
            processNestedTdItems(td.getItems());
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
            processNestedTdItems(td.getItems());
        });
    }

    // TransportationDemandUIService - TransportationDemandItems
    @Before(event = CqnService.EVENT_CREATE, entity = TransportationDemandItems_.CDS_NAME)
    @HandlerOrder(HandlerOrder.EARLY)
    public void generateTdItemDisplayId(List<TransportationDemandItems> items) {
        if (items == null)
            return;
        items.forEach(item -> {
            if (item.getDisplayId() == null) {
                item.setDisplayId(generateDisplayId("TDI"));
            }
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

    private void processNestedTdItems(List<TransportationDemandItems> items) {
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
