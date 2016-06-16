Ext.define('Salesdemo.salesdemo.web.com.sales.view.salesboundedcontext.sales.SalesDataMain', {
     "extend": "Ext.tab.Panel",
     "xtype": "salesDataMainView",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "SalesDataMainController",
     "restURL": "/SalesData",
     "defaults": {
          "split": true
     },
     "requires": ["Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.SalesDataModel", "Salesdemo.salesdemo.web.com.sales.controller.salesboundedcontext.sales.SalesDataMainController", "Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.ChannelModel", "Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.RetailerModel", "Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.MaterialModel", "Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.BrandModel", "Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.CategoryModel", "Salesdemo.salesdemo.shared.com.sales.viewmodel.salesboundedcontext.sales.SalesDataViewModel"],
     "communicationLog": [],
     "tabPosition": "bottom",
     "items": [{
          "title": "Data Browser",
          "layout": "border",
          "defaults": {
               "split": true
          },
          "autoScroll": false,
          "customWidgetType": "vdBorderLayout",
          "items": [{
               "xtype": "tabpanel",
               "customWidgetType": "vdTabLayout",
               "displayName": "SalesData",
               "name": "SalesDataTreeContainer",
               "itemId": "SalesDataTreeContainer",
               "margin": "5 0 5 5",
               "autoScroll": false,
               "collapsible": true,
               "titleCollapse": true,
               "collapseMode": "header",
               "collapsed": false,
               "items": [{
                    "xtype": "treepanel",
                    "customWidgetType": "vdTree",
                    "useArrows": true,
                    "name": "entityTreePanel",
                    "title": "Browse",
                    "rootVisible": false,
                    "itemId": "SalesDataTree",
                    "listeners": {
                         "select": "treeClick"
                    },
                    "tbar": [{
                         "xtype": "triggerfield",
                         "customWidgetType": "vdTriggerField",
                         "emptyText": "Search",
                         "triggerCls": "",
                         "listeners": {
                              "change": "onTriggerfieldChange",
                              "buffer": 250
                         }
                    }, "->", {
                         "xtype": "tool",
                         "type": "refresh",
                         "tooltip": "Refresh Tree Data",
                         "handler": "onTreeRefreshClick"
                    }]
               }, {
                    "title": "Advance Search",
                    "xtype": "form",
                    "customWidgetType": "vdFormpanel",
                    "itemId": "queryPanel",
                    "buttons": [{
                         "text": "Filter",
                         "handler": "onFilterClick",
                         "name": "filterButton"
                    }],
                    "items": []
               }],
               "region": "west",
               "width": "20%"
          }, {
               "region": "center",
               "layout": "border",
               "defaults": {
                    "split": true
               },
               "customWidgetType": "vdBorderLayout",
               "items": [{
                    "xtype": "form",
                    "displayName": "SalesData",
                    "name": "SalesData",
                    "itemId": "SalesDataForm",
                    "bodyPadding": 10,
                    "items": [{
                         "xtype": "form",
                         "itemId": "form0",
                         "customWidgetType": "vdCard",
                         "header": {
                              "hidden": true
                         },
                         "items": [{
                              "layout": "column",
                              "customWidgetType": "vdColumnLayout",
                              "header": {
                                   "hidden": true
                              },
                              "xtype": "panel",
                              "items": [{
                                   "name": "autoid",
                                   "itemId": "autoid",
                                   "xtype": "numberfield",
                                   "customWidgetType": "vdNumberfield",
                                   "displayName": "Auto Id",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "Auto Id<font color='red'> *<\/font>",
                                   "fieldId": "11201B9B-B272-4D6A-BC0E-0D2A87341B75",
                                   "minValue": "-2147483648",
                                   "maxValue": "2147483647",
                                   "hidden": true,
                                   "value": "",
                                   "bindable": "autoid"
                              }, {
                                   "name": "channelId",
                                   "itemId": "channelId",
                                   "xtype": "combo",
                                   "customWidgetType": "vdCombo",
                                   "displayName": "Channel",
                                   "margin": "5 5 5 5",
                                   "valueField": "primaryKey",
                                   "displayField": "primaryDisplay",
                                   "typeAhead": true,
                                   "queryMode": "local",
                                   "minChars": 2,
                                   "store": {
                                        "data": [],
                                        "model": "Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.ChannelModel"
                                   },
                                   "allowBlank": false,
                                   "fieldLabel": "Channel<font color='red'> *<\/font>",
                                   "fieldId": "EFA43643-AABD-42A7-93FB-BB70EFF87A17",
                                   "restURL": "Channel",
                                   "bindable": "channelId",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "reatilercode",
                                   "itemId": "reatilercode",
                                   "xtype": "combo",
                                   "customWidgetType": "vdCombo",
                                   "displayName": "Retailer",
                                   "margin": "5 5 5 5",
                                   "valueField": "primaryKey",
                                   "displayField": "primaryDisplay",
                                   "typeAhead": true,
                                   "queryMode": "local",
                                   "minChars": 2,
                                   "store": {
                                        "data": [],
                                        "model": "Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.RetailerModel"
                                   },
                                   "allowBlank": false,
                                   "fieldLabel": "Retailer<font color='red'> *<\/font>",
                                   "fieldId": "B59AFE2B-4BFF-49B4-BB2B-DF27D039CDE1",
                                   "restURL": "Retailer",
                                   "bindable": "reatilercode",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "retailername",
                                   "itemId": "retailername",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "Retailer name",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "Retailer name",
                                   "fieldId": "50C82FAD-44CB-4547-81B8-7461C0F00596",
                                   "minLength": "0",
                                   "maxLength": "64",
                                   "bindable": "retailername",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "salesdate",
                                   "itemId": "salesdate",
                                   "xtype": "datefield",
                                   "customWidgetType": "vdDatefield",
                                   "displayName": "Sales Date",
                                   "margin": "5 5 5 5",
                                   "format": "d-m-Y",
                                   "submitFormat": "d-m-Y",
                                   "fieldLabel": "Sales Date<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "04D23FB1-7B80-4083-8B88-C7FB7E25BED2",
                                   "bindable": "salesdate",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "salesmonth",
                                   "itemId": "salesmonth",
                                   "xtype": "numberfield",
                                   "customWidgetType": "vdNumberfield",
                                   "displayName": "Sales Month",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "Sales Month<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "C53D7F9E-3E3C-4BA1-A0BD-DD5C538E66C4",
                                   "minValue": "-2147483648",
                                   "maxValue": "2147483647",
                                   "bindable": "salesmonth",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "salesyear",
                                   "itemId": "salesyear",
                                   "xtype": "numberfield",
                                   "customWidgetType": "vdNumberfield",
                                   "displayName": "Sales Year",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "Sales Year<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "5A1E920B-97FA-4A6C-B62D-62FB6B0A3BAF",
                                   "minValue": "-2147483648",
                                   "maxValue": "2147483647",
                                   "bindable": "salesyear",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "salesinvoicenbr",
                                   "itemId": "salesinvoicenbr",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "Invoice Number",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "Invoice Number<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "5F4BD6A4-1332-46E2-ABE4-4B184587AF8B",
                                   "minLength": "0",
                                   "maxLength": "64",
                                   "bindable": "salesinvoicenbr",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "materialdescription",
                                   "itemId": "materialdescription",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "Material Desc",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "Material Desc",
                                   "fieldId": "B09A1F91-82D6-491C-A1C3-45155629B520",
                                   "minLength": "1",
                                   "maxLength": "64",
                                   "bindable": "materialdescription",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "branddescription",
                                   "itemId": "branddescription",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "Brand Desc",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "Brand Desc",
                                   "fieldId": "686BCE7F-B935-428C-B428-D7223F025DE3",
                                   "maxLength": "1",
                                   "bindable": "branddescription",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "salesqty",
                                   "itemId": "salesqty",
                                   "xtype": "numberfield",
                                   "customWidgetType": "vdNumberfield",
                                   "displayName": "Sales Quantity",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "Sales Quantity<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "0BF2ED36-96B1-44D4-BC80-586ED302F05B",
                                   "minValue": "-9223372000000000000",
                                   "maxValue": "9223372000000000000",
                                   "bindable": "salesqty",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "netsalesamt",
                                   "itemId": "netsalesamt",
                                   "xtype": "numberfield",
                                   "customWidgetType": "vdNumberfield",
                                   "displayName": "Net Sales",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "Net Sales<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "0C0441C4-53FC-41A2-A1A2-B6547AA71A61",
                                   "minValue": "-9223372000000000000",
                                   "maxValue": "9223372000000000000",
                                   "bindable": "netsalesamt",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "grosssalesamt",
                                   "itemId": "grosssalesamt",
                                   "xtype": "numberfield",
                                   "customWidgetType": "vdNumberfield",
                                   "displayName": "Gross Sales",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "Gross Sales<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "F7D41342-4B84-485A-9962-A49630FF2C3B",
                                   "minValue": "-9223372000000000000",
                                   "maxValue": "9223372000000000000",
                                   "bindable": "grosssalesamt",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "materialcode",
                                   "itemId": "materialcode",
                                   "xtype": "combo",
                                   "customWidgetType": "vdCombo",
                                   "displayName": "Material",
                                   "margin": "5 5 5 5",
                                   "valueField": "primaryKey",
                                   "displayField": "primaryDisplay",
                                   "typeAhead": true,
                                   "queryMode": "local",
                                   "minChars": 2,
                                   "store": {
                                        "data": [],
                                        "model": "Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.MaterialModel"
                                   },
                                   "allowBlank": false,
                                   "fieldLabel": "Material<font color='red'> *<\/font>",
                                   "fieldId": "38F09723-295D-4761-A099-4D26D93870AC",
                                   "restURL": "Material",
                                   "bindable": "materialcode",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "brandcode",
                                   "itemId": "brandcode",
                                   "xtype": "combo",
                                   "customWidgetType": "vdCombo",
                                   "displayName": "Brand",
                                   "margin": "5 5 5 5",
                                   "valueField": "primaryKey",
                                   "displayField": "primaryDisplay",
                                   "typeAhead": true,
                                   "queryMode": "local",
                                   "minChars": 2,
                                   "store": {
                                        "data": [],
                                        "model": "Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.BrandModel"
                                   },
                                   "allowBlank": false,
                                   "fieldLabel": "Brand<font color='red'> *<\/font>",
                                   "fieldId": "B3077BAD-155B-406B-A45D-990CA1B6950A",
                                   "restURL": "Brand",
                                   "bindable": "brandcode",
                                   "columnWidth": 0.5,
                                   "listeners": {
                                        "change": "onBrandcodeChange"
                                   }
                              }, {
                                   "name": "categoryId",
                                   "itemId": "categoryId",
                                   "xtype": "combo",
                                   "customWidgetType": "vdCombo",
                                   "displayName": "Category",
                                   "margin": "5 5 5 5",
                                   "valueField": "primaryKey",
                                   "displayField": "primaryDisplay",
                                   "typeAhead": true,
                                   "queryMode": "local",
                                   "minChars": 2,
                                   "store": {
                                        "data": [],
                                        "model": "Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.CategoryModel"
                                   },
                                   "allowBlank": false,
                                   "fieldLabel": "Category<font color='red'> *<\/font>",
                                   "fieldId": "5B530194-3F6C-41E6-86FA-23E33FBDCB87",
                                   "restURL": "Category",
                                   "bindable": "categoryId",
                                   "columnWidth": 0.5,
                                   "listeners": {
                                        "change": "onCategoryIdChange"
                                   }
                              }, {
                                   "name": "versionId",
                                   "itemId": "versionId",
                                   "xtype": "numberfield",
                                   "customWidgetType": "vdNumberfield",
                                   "displayName": "versionId",
                                   "margin": "5 5 5 5",
                                   "value": "-1",
                                   "fieldLabel": "versionId",
                                   "fieldId": "012861D6-47E8-4E90-9844-17315DD62A1E",
                                   "bindable": "versionId",
                                   "hidden": true
                              }]
                         }]
                    }],
                    "tools": [{
                         "type": "help",
                         "tooltip": "Get Console",
                         "handler": "onConsoleClick"
                    }, {
                         "type": "refresh",
                         "tooltip": "Refresh Tab",
                         "handler": "init"
                    }],
                    "layout": "card",
                    "defaults": {
                         "autoScroll": true
                    },
                    "autoScroll": true,
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "ui": "footer",
                         "isToolBar": true,
                         "isDockedItem": true,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill"
                         }, {
                              "xtype": "button",
                              "customWidgetType": "vdButton",
                              "margin": 5,
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "listeners": {
                                   "click": "saveForm"
                              }
                         }, {
                              "xtype": "button",
                              "customWidgetType": "vdButton",
                              "margin": 5,
                              "text": "Reset",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "resetFormButton",
                              "listeners": {
                                   "click": "resetForm"
                              }
                         }]
                    }],
                    "listeners": {},
                    "extend": "Ext.form.Panel",
                    "region": "center",
                    "customWidgetType": "vdCardLayout"
               }, {
                    "xtype": "grid",
                    "customWidgetType": "vdGrid",
                    "displayName": "SalesData",
                    "title": "Details Grid",
                    "name": "SalesDataGrid",
                    "itemId": "SalesDataGrid",
                    "store": [],
                    "requires": [],
                    "columns": [{
                         "header": "Auto Id",
                         "dataIndex": "autoid",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "Channel",
                         "dataIndex": "channelId",
                         "flex": 1
                    }, {
                         "header": "Retailer",
                         "dataIndex": "reatilercode",
                         "flex": 1
                    }, {
                         "header": "Retailer name",
                         "dataIndex": "retailername",
                         "flex": 1
                    }, {
                         "header": "Sales Date",
                         "dataIndex": "salesdate",
                         "flex": 1
                    }, {
                         "header": "Sales Month",
                         "dataIndex": "salesmonth",
                         "flex": 1
                    }, {
                         "header": "Sales Year",
                         "dataIndex": "salesyear",
                         "flex": 1
                    }, {
                         "header": "Invoice Number",
                         "dataIndex": "salesinvoicenbr",
                         "flex": 1
                    }, {
                         "header": "Material Desc",
                         "dataIndex": "materialdescription",
                         "flex": 1
                    }, {
                         "header": "Brand Desc",
                         "dataIndex": "branddescription",
                         "flex": 1
                    }, {
                         "header": "Sales Quantity",
                         "dataIndex": "salesqty",
                         "flex": 1
                    }, {
                         "header": "Net Sales",
                         "dataIndex": "netsalesamt",
                         "flex": 1
                    }, {
                         "header": "Gross Sales",
                         "dataIndex": "grosssalesamt",
                         "flex": 1
                    }, {
                         "header": "Material",
                         "dataIndex": "materialcode",
                         "flex": 1
                    }, {
                         "header": "Brand",
                         "dataIndex": "brandcode",
                         "flex": 1
                    }, {
                         "header": "Category",
                         "dataIndex": "categoryId",
                         "flex": 1
                    }, {
                         "header": "createdBy",
                         "dataIndex": "createdBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "createdDate",
                         "dataIndex": "createdDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedBy",
                         "dataIndex": "updatedBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedDate",
                         "dataIndex": "updatedDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "versionId",
                         "dataIndex": "versionId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "activeStatus",
                         "dataIndex": "activeStatus",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "txnAccessCode",
                         "dataIndex": "txnAccessCode",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "xtype": "actioncolumn",
                         "customWidgetType": "vdActionColumn",
                         "sortable": false,
                         "menuDisable": true,
                         "items": [{
                              "icon": "images/delete.gif",
                              "tooltip": "Delete Record",
                              "handler": "onDeleteActionColumnClickMainGrid"
                         }]
                    }],
                    "listeners": {
                         "itemclick": "onGridItemClick"
                    },
                    "tools": [{
                         "type": "refresh",
                         "tooltip": "Refresh Grid Data",
                         "handler": "onGridRefreshClick"
                    }],
                    "collapsible": true,
                    "titleCollapse": true,
                    "collapseMode": "header",
                    "region": "south",
                    "height": "40%"
               }]
          }]
     }, {
          "title": "Add New",
          "itemId": "addNewForm",
          "layout": "border",
          "customWidgetType": "vdBorderLayout",
          "autoScroll": false,
          "items": [{
               "xtype": "form",
               "displayName": "SalesData",
               "name": "SalesData",
               "itemId": "SalesDataForm",
               "bodyPadding": 10,
               "items": [{
                    "xtype": "form",
                    "itemId": "form0",
                    "customWidgetType": "vdCard",
                    "header": {
                         "hidden": true
                    },
                    "items": [{
                         "layout": "column",
                         "customWidgetType": "vdColumnLayout",
                         "header": {
                              "hidden": true
                         },
                         "xtype": "panel",
                         "items": [{
                              "name": "autoid",
                              "itemId": "autoid",
                              "xtype": "numberfield",
                              "customWidgetType": "vdNumberfield",
                              "displayName": "Auto Id",
                              "margin": "5 5 5 5",
                              "fieldLabel": "Auto Id<font color='red'> *<\/font>",
                              "fieldId": "11201B9B-B272-4D6A-BC0E-0D2A87341B75",
                              "minValue": "-2147483648",
                              "maxValue": "2147483647",
                              "hidden": true,
                              "value": "",
                              "bindable": "autoid"
                         }, {
                              "name": "channelId",
                              "itemId": "channelId",
                              "xtype": "combo",
                              "customWidgetType": "vdCombo",
                              "displayName": "Channel",
                              "margin": "5 5 5 5",
                              "valueField": "primaryKey",
                              "displayField": "primaryDisplay",
                              "typeAhead": true,
                              "queryMode": "local",
                              "minChars": 2,
                              "store": {
                                   "data": [],
                                   "model": "Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.ChannelModel"
                              },
                              "allowBlank": false,
                              "fieldLabel": "Channel<font color='red'> *<\/font>",
                              "fieldId": "EFA43643-AABD-42A7-93FB-BB70EFF87A17",
                              "restURL": "Channel",
                              "bindable": "channelId",
                              "columnWidth": 0.5
                         }, {
                              "name": "reatilercode",
                              "itemId": "reatilercode",
                              "xtype": "combo",
                              "customWidgetType": "vdCombo",
                              "displayName": "Retailer",
                              "margin": "5 5 5 5",
                              "valueField": "primaryKey",
                              "displayField": "primaryDisplay",
                              "typeAhead": true,
                              "queryMode": "local",
                              "minChars": 2,
                              "store": {
                                   "data": [],
                                   "model": "Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.RetailerModel"
                              },
                              "allowBlank": false,
                              "fieldLabel": "Retailer<font color='red'> *<\/font>",
                              "fieldId": "B59AFE2B-4BFF-49B4-BB2B-DF27D039CDE1",
                              "restURL": "Retailer",
                              "bindable": "reatilercode",
                              "columnWidth": 0.5
                         }, {
                              "name": "retailername",
                              "itemId": "retailername",
                              "xtype": "textfield",
                              "customWidgetType": "vdTextfield",
                              "displayName": "Retailer name",
                              "margin": "5 5 5 5",
                              "fieldLabel": "Retailer name",
                              "fieldId": "50C82FAD-44CB-4547-81B8-7461C0F00596",
                              "minLength": "0",
                              "maxLength": "64",
                              "bindable": "retailername",
                              "columnWidth": 0.5
                         }, {
                              "name": "salesdate",
                              "itemId": "salesdate",
                              "xtype": "datefield",
                              "customWidgetType": "vdDatefield",
                              "displayName": "Sales Date",
                              "margin": "5 5 5 5",
                              "format": "d-m-Y",
                              "submitFormat": "d-m-Y",
                              "fieldLabel": "Sales Date<font color='red'> *<\/font>",
                              "allowBlank": false,
                              "fieldId": "04D23FB1-7B80-4083-8B88-C7FB7E25BED2",
                              "bindable": "salesdate",
                              "columnWidth": 0.5
                         }, {
                              "name": "salesmonth",
                              "itemId": "salesmonth",
                              "xtype": "numberfield",
                              "customWidgetType": "vdNumberfield",
                              "displayName": "Sales Month",
                              "margin": "5 5 5 5",
                              "fieldLabel": "Sales Month<font color='red'> *<\/font>",
                              "allowBlank": false,
                              "fieldId": "C53D7F9E-3E3C-4BA1-A0BD-DD5C538E66C4",
                              "minValue": "-2147483648",
                              "maxValue": "2147483647",
                              "bindable": "salesmonth",
                              "columnWidth": 0.5
                         }, {
                              "name": "salesyear",
                              "itemId": "salesyear",
                              "xtype": "numberfield",
                              "customWidgetType": "vdNumberfield",
                              "displayName": "Sales Year",
                              "margin": "5 5 5 5",
                              "fieldLabel": "Sales Year<font color='red'> *<\/font>",
                              "allowBlank": false,
                              "fieldId": "5A1E920B-97FA-4A6C-B62D-62FB6B0A3BAF",
                              "minValue": "-2147483648",
                              "maxValue": "2147483647",
                              "bindable": "salesyear",
                              "columnWidth": 0.5
                         }, {
                              "name": "salesinvoicenbr",
                              "itemId": "salesinvoicenbr",
                              "xtype": "textfield",
                              "customWidgetType": "vdTextfield",
                              "displayName": "Invoice Number",
                              "margin": "5 5 5 5",
                              "fieldLabel": "Invoice Number<font color='red'> *<\/font>",
                              "allowBlank": false,
                              "fieldId": "5F4BD6A4-1332-46E2-ABE4-4B184587AF8B",
                              "minLength": "0",
                              "maxLength": "64",
                              "bindable": "salesinvoicenbr",
                              "columnWidth": 0.5
                         }, {
                              "name": "materialdescription",
                              "itemId": "materialdescription",
                              "xtype": "textfield",
                              "customWidgetType": "vdTextfield",
                              "displayName": "Material Desc",
                              "margin": "5 5 5 5",
                              "fieldLabel": "Material Desc",
                              "fieldId": "B09A1F91-82D6-491C-A1C3-45155629B520",
                              "minLength": "1",
                              "maxLength": "64",
                              "bindable": "materialdescription",
                              "columnWidth": 0.5
                         }, {
                              "name": "branddescription",
                              "itemId": "branddescription",
                              "xtype": "textfield",
                              "customWidgetType": "vdTextfield",
                              "displayName": "Brand Desc",
                              "margin": "5 5 5 5",
                              "fieldLabel": "Brand Desc",
                              "fieldId": "686BCE7F-B935-428C-B428-D7223F025DE3",
                              "maxLength": "1",
                              "bindable": "branddescription",
                              "columnWidth": 0.5
                         }, {
                              "name": "salesqty",
                              "itemId": "salesqty",
                              "xtype": "numberfield",
                              "customWidgetType": "vdNumberfield",
                              "displayName": "Sales Quantity",
                              "margin": "5 5 5 5",
                              "fieldLabel": "Sales Quantity<font color='red'> *<\/font>",
                              "allowBlank": false,
                              "fieldId": "0BF2ED36-96B1-44D4-BC80-586ED302F05B",
                              "minValue": "-9223372000000000000",
                              "maxValue": "9223372000000000000",
                              "bindable": "salesqty",
                              "columnWidth": 0.5
                         }, {
                              "name": "netsalesamt",
                              "itemId": "netsalesamt",
                              "xtype": "numberfield",
                              "customWidgetType": "vdNumberfield",
                              "displayName": "Net Sales",
                              "margin": "5 5 5 5",
                              "fieldLabel": "Net Sales<font color='red'> *<\/font>",
                              "allowBlank": false,
                              "fieldId": "0C0441C4-53FC-41A2-A1A2-B6547AA71A61",
                              "minValue": "-9223372000000000000",
                              "maxValue": "9223372000000000000",
                              "bindable": "netsalesamt",
                              "columnWidth": 0.5
                         }, {
                              "name": "grosssalesamt",
                              "itemId": "grosssalesamt",
                              "xtype": "numberfield",
                              "customWidgetType": "vdNumberfield",
                              "displayName": "Gross Sales",
                              "margin": "5 5 5 5",
                              "fieldLabel": "Gross Sales<font color='red'> *<\/font>",
                              "allowBlank": false,
                              "fieldId": "F7D41342-4B84-485A-9962-A49630FF2C3B",
                              "minValue": "-9223372000000000000",
                              "maxValue": "9223372000000000000",
                              "bindable": "grosssalesamt",
                              "columnWidth": 0.5
                         }, {
                              "name": "materialcode",
                              "itemId": "materialcode",
                              "xtype": "combo",
                              "customWidgetType": "vdCombo",
                              "displayName": "Material",
                              "margin": "5 5 5 5",
                              "valueField": "primaryKey",
                              "displayField": "primaryDisplay",
                              "typeAhead": true,
                              "queryMode": "local",
                              "minChars": 2,
                              "store": {
                                   "data": [],
                                   "model": "Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.MaterialModel"
                              },
                              "allowBlank": false,
                              "fieldLabel": "Material<font color='red'> *<\/font>",
                              "fieldId": "38F09723-295D-4761-A099-4D26D93870AC",
                              "restURL": "Material",
                              "bindable": "materialcode",
                              "columnWidth": 0.5
                         }, {
                              "name": "brandcode",
                              "itemId": "brandcode",
                              "xtype": "combo",
                              "customWidgetType": "vdCombo",
                              "displayName": "Brand",
                              "margin": "5 5 5 5",
                              "valueField": "primaryKey",
                              "displayField": "primaryDisplay",
                              "typeAhead": true,
                              "queryMode": "local",
                              "minChars": 2,
                              "store": {
                                   "data": [],
                                   "model": "Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.BrandModel"
                              },
                              "allowBlank": false,
                              "fieldLabel": "Brand<font color='red'> *<\/font>",
                              "fieldId": "B3077BAD-155B-406B-A45D-990CA1B6950A",
                              "restURL": "Brand",
                              "bindable": "brandcode",
                              "columnWidth": 0.5,
                              "listeners": {
                                   "change": "onBrandcodeChange"
                              }
                         }, {
                              "name": "categoryId",
                              "itemId": "categoryId",
                              "xtype": "combo",
                              "customWidgetType": "vdCombo",
                              "displayName": "Category",
                              "margin": "5 5 5 5",
                              "valueField": "primaryKey",
                              "displayField": "primaryDisplay",
                              "typeAhead": true,
                              "queryMode": "local",
                              "minChars": 2,
                              "store": {
                                   "data": [],
                                   "model": "Salesdemo.salesdemo.shared.com.sales.model.salesboundedcontext.sales.CategoryModel"
                              },
                              "allowBlank": false,
                              "fieldLabel": "Category<font color='red'> *<\/font>",
                              "fieldId": "5B530194-3F6C-41E6-86FA-23E33FBDCB87",
                              "restURL": "Category",
                              "bindable": "categoryId",
                              "columnWidth": 0.5,
                              "listeners": {
                                   "change": "onCategoryIdChange"
                              }
                         }, {
                              "name": "versionId",
                              "itemId": "versionId",
                              "xtype": "numberfield",
                              "customWidgetType": "vdNumberfield",
                              "displayName": "versionId",
                              "margin": "5 5 5 5",
                              "value": "-1",
                              "fieldLabel": "versionId",
                              "fieldId": "012861D6-47E8-4E90-9844-17315DD62A1E",
                              "bindable": "versionId",
                              "hidden": true
                         }]
                    }]
               }],
               "tools": [{
                    "type": "help",
                    "tooltip": "Get Console",
                    "handler": "onConsoleClick"
               }, {
                    "type": "refresh",
                    "tooltip": "Refresh Tab",
                    "handler": "init"
               }],
               "layout": "card",
               "defaults": {
                    "autoScroll": true
               },
               "autoScroll": true,
               "dockedItems": [{
                    "xtype ": "toolbar",
                    "customWidgetType": "vdBBar",
                    "dock": "bottom",
                    "ui": "footer",
                    "isToolBar": true,
                    "isDockedItem": true,
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill"
                    }, {
                         "xtype": "button",
                         "customWidgetType": "vdButton",
                         "margin": 5,
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "listeners": {
                              "click": "saveForm"
                         }
                    }, {
                         "xtype": "button",
                         "customWidgetType": "vdButton",
                         "margin": 5,
                         "text": "Reset",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "resetFormButton",
                         "listeners": {
                              "click": "resetForm"
                         }
                    }]
               }],
               "listeners": {},
               "extend": "Ext.form.Panel",
               "region": "center",
               "customWidgetType": "vdCardLayout"
          }]
     }]
});