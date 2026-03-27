sap.ui.define([
    "sap/fe/test/JourneyRunner",
	"tmstd/test/integration/pages/TransportationDemandsList",
	"tmstd/test/integration/pages/TransportationDemandsObjectPage",
	"tmstd/test/integration/pages/TransportationDemandItemsObjectPage"
], function (JourneyRunner, TransportationDemandsList, TransportationDemandsObjectPage, TransportationDemandItemsObjectPage) {
    'use strict';

    var runner = new JourneyRunner({
        launchUrl: sap.ui.require.toUrl('tmstd') + '/test/flpSandbox.html#tmstd-tile',
        pages: {
			onTheTransportationDemandsList: TransportationDemandsList,
			onTheTransportationDemandsObjectPage: TransportationDemandsObjectPage,
			onTheTransportationDemandItemsObjectPage: TransportationDemandItemsObjectPage
        },
        async: true
    });

    return runner;
});

