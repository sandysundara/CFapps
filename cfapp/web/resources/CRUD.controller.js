sap.ui.define([
	'jquery.sap.global', 'sap/ui/core/mvc/Controller'
], function(jQuery, Controller) {
	"use strict";

	return Controller.extend("poc.cf.CRUD", {

		onInit: function() {
			this.getView().byId("xsodata").setModel(this.getOwnerComponent().getModel("address_java"))
		}

	});

});
