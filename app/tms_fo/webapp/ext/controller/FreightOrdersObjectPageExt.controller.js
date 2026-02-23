sap.ui.define([
  "sap/fe/core/controllerextensions/BaseControllerExtension"
], function (BaseControllerExtension) {
  "use strict";

  return BaseControllerExtension.extend(
    "tmsfo.ext.controller.FreightOrdersObjectPageExt", {

    override: BaseControllerExtension.createExtensionOverrides({
      routing: {
        onAfterBinding() {
          const view = this.base.getView();

          setTimeout(() => {
            const table = view.findAggregatedObjects(true, c =>
              c.isA("sap.m.Table") && c.getId().endsWith("table")
            )[0];

            if (!table) return;

            const dndConfig = table.getDragDropConfig()[0];
            dndConfig.attachDrop(this._handleStopReorder.bind(this));
          }, 300);
        }
      }
    }),

    async _handleStopReorder(event) {
      const draggedItem = event.getParameter("draggedControl");
      const droppedItem = event.getParameter("droppedControl");
      const dropPosition = event.getParameter("dropPosition");

      if (!draggedItem || !droppedItem) return;

      const table = draggedItem.getParent();
      const listBinding = table.getBinding("items");

      const contexts = listBinding.getCurrentContexts();
      const entries = contexts.map(ctx => ({ ctx }));

      const fromIndex = entries.findIndex(e => e.ctx === draggedItem.getBindingContext());
      let toIndex = entries.findIndex(e => e.ctx === droppedItem.getBindingContext());

      if (dropPosition === "After") toIndex++;

      const [moved] = entries.splice(fromIndex, 1);
      entries.splice(toIndex, 0, moved);

      for (let i = 0; i < entries.length; i++) {
        await entries[i].ctx.setProperty("sequence", i + 1);
      }
    }
  });
});
