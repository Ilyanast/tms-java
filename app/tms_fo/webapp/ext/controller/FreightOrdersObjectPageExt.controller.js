sap.ui.define([
  "sap/ui/core/mvc/ControllerExtension"
], function (ControllerExtension) {
  "use strict";

  return ControllerExtension.extend(
    "tmsfo.ext.controller.FreightOrdersObjectPageExt", {

    override: {
      onInit: function () {
        this.base.onInit();

        var oView = this.base.getView();

        oView.attachEventOnce("afterRendering", function () {
          var oTable = oView.findAggregatedObjects(true).find(function (o) {
            return o.isA("sap.m.Table") &&
              o.getBindingInfo("items")?.path === "stops";
          });

          if (!oTable) return;

          var oDragDrop = oTable.getDragDropConfig()[0];
          if (oDragDrop) {
            oDragDrop.attachDragStart(this.onDragStart, this);
            oDragDrop.attachDrop(this.onDrop, this);
          }
        }.bind(this));
      }
    },

    onDragStart: function (oEvent) {
      var oDraggedItem = oEvent.getParameter("target");
      var iSequence = oDraggedItem.getBindingContext().getProperty("sequence");

      if (iSequence === 1) {
        oEvent.preventDefault();
      }
    },

    onDrop: function (oEvent) {
      var oDraggedItem = oEvent.getParameter("draggedControl");
      var oDroppedItem = oEvent.getParameter("droppedControl");
      var sDropPosition = oEvent.getParameter("dropPosition");

      if (!oDraggedItem || !oDroppedItem) return;

      var oTable = oDraggedItem.getParent();
      var aContexts = oTable.getBinding("items").getCurrentContexts();

      var aEntries = aContexts.map(function (oCtx) {
        return { ctx: oCtx };
      });

      var iFromIndex = aEntries.findIndex(function (e) {
        return e.ctx === oDraggedItem.getBindingContext();
      });

      var iToIndex = aEntries.findIndex(function (e) {
        return e.ctx === oDroppedItem.getBindingContext();
      });

      if (iToIndex === 0 && sDropPosition === "Before") return;

      if (sDropPosition === "After") iToIndex++;

      var aRemoved = aEntries.splice(iFromIndex, 1);
      aEntries.splice(iToIndex, 0, aRemoved[0]);

      aEntries.forEach(function (oEntry, iIndex) {
        oEntry.ctx.setProperty("sequence", iIndex + 1);
      });
    }
  });
});