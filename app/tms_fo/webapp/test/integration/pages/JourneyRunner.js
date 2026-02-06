sap.ui.define([
    "sap/fe/test/JourneyRunner",
	"tmsfo/test/integration/pages/FreightOrdersList",
	"tmsfo/test/integration/pages/FreightOrdersObjectPage",
	"tmsfo/test/integration/pages/FreightOrderItemsObjectPage"
], function (JourneyRunner, FreightOrdersList, FreightOrdersObjectPage, FreightOrderItemsObjectPage) {
    'use strict';

    var runner = new JourneyRunner({
        launchUrl: sap.ui.require.toUrl('tmsfo') + '/test/flpSandbox.html#tmsfo-tile',
        pages: {
			onTheFreightOrdersList: FreightOrdersList,
			onTheFreightOrdersObjectPage: FreightOrdersObjectPage,
			onTheFreightOrderItemsObjectPage: FreightOrderItemsObjectPage
        },
        async: true
    });

    return runner;
});

