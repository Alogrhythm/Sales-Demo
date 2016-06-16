Ext.define('Salesdemo.salesdemo.web.com.sales.view.salesboundedcontext.sales.CurrentSalesData', {
     "xtype": "currentSalesDataView",
     "items": [{
          "xtype": "panel",
          "items": [{
               "xtype": "reportView",
               "name": "salesDataReport",
               "title": "Current Sales Data",
               "isCustomReport": true,
               "reportWidgets": ["1", "3"],
               "refId": "D8D997EA-9F83-48A0-9E74-C015365A6053",
               "margin": 5,
               "itemId": "reportView_ext_1478"
          }],
          "border": true,
          "autoScroll": false,
          "layout": "fit",
          "margin": 5,
          "itemId": "panel_ext_1453",
          "dockedItems": []
     }],
     "border": true,
     "autoScroll": true,
     "margin": 5,
     "itemId": "form_ext_1444",
     "dockedItems": [],
     "requires": ["Salesdemo.view.appreportui.ReportView", "Salesdemo.salesdemo.web.com.sales.controller.salesboundedcontext.sales.CurrentSalesDataController", "Salesdemo.salesdemo.shared.com.sales.viewmodel.salesboundedcontext.sales.CurrentSalesDataViewModel", "Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.CurrentSalesDataModel"],
     "extend": "Ext.form.Panel",
     "viewModel": "CurrentSalesDataViewModel",
     "controller": "CurrentSalesDataController"
});