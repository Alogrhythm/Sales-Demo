Ext.define('Salesdemo.salesdemo.smartdevice.com.sales.view.salesboundedcontext.sales.CurrentSales', {
     "xtype": "currentSalesView",
     "items": [{
          "xtype": "panel",
          "items": [{
               "xtype": "reportView",
               "name": "CSD",
               "title": "Current Sales Data",
               "isCustomReport": true,
               "reportWidgets": ["1", "3"],
               "refId": "D8D997EA-9F83-48A0-9E74-C015365A6053",
               "margin": 5,
               "itemId": "reportView_ext_10383",
               "height": "0"
          }],
          "border": true,
          "autoScroll": false,
          "layout": "fit",
          "margin": 5,
          "itemId": "panel_ext_10223",
          "dockedItems": []
     }],
     "border": true,
     "autoScroll": true,
     "margin": 5,
     "itemId": "form_ext_10210",
     "dockedItems": [],
     "requires": ["Salesdemo.view.smartdevice.reportview.ReportMainView", "Salesdemo.salesdemo.smartdevice.com.sales.controller.salesboundedcontext.sales.CurrentSalesController", "Salesdemo.salesdemo.shared.com.sales.viewmodel.salesboundedcontext.sales.CurrentSalesViewModel", "Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.CurrentSalesModel"],
     "extend": "Ext.form.Panel",
     "viewModel": "CurrentSalesViewModel",
     "controller": "CurrentSalesController"
});