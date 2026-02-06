sap.ui.define([
    "sap/ui/core/mvc/Controller"
], function (Controller) {
    "use strict";

    return Controller.extend("tmsfo.ext.controller.StopsSection", {

        onDragStart: function (oEvent) {
            const oDraggedRow = oEvent.getParameter("draggedRow");
            if (!oDraggedRow) {
                return;
            }

            const oCtx = oDraggedRow.getBindingContext();
            if (!oCtx) {
                return;
            }

            const bInPlanning = oCtx.getProperty("freightOrder/isInPlanning");
            if (!bInPlanning) {
                oEvent.preventDefault();
                return;
            }

            const iDraggedIndex = oEvent.getParameter("draggedRowIndex");
            const oTable = this.byId("StopsDndTable");
            const iTotal = oTable.getBinding("rows").getLength();

            if (iDraggedIndex === 0 || iDraggedIndex === iTotal - 1) {
                oEvent.preventDefault();
            }
        },

        onDrop: function (oEvent) {
            const oTable = this.byId("StopsDndTable");
            const oBinding = oTable.getBinding("rows");

            const iFrom = oEvent.getParameter("draggedRowIndex");
            const iTo = oEvent.getParameter("droppedRowIndex");

            if (iFrom === iTo || iFrom == null || iTo == null) {
                return;
            }

            const aContexts = oBinding.getContexts();
            const aData = aContexts.map(ctx => ctx.getObject());

            const oMoved = aData.splice(iFrom, 1)[0];
            aData.splice(iTo, 0, oMoved);

            aData.forEach((oStop, iIndex) => {
                oStop.sequence = iIndex + 1;
            });

            oBinding.refresh(true);
        }

    });
});
