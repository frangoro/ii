Ext.define('II.controller.Items', {
    extend: 'Ext.app.Controller',

    views: ['items.List',
    'items.Edit', 'items.Search'],

    stores: ['Items'],

    models: ['Items'],

    init: function() {
        this.control({
            'itemsList': {
                itemdblclick: this.editItem
            },
            'itemsEdit button[action=save]': {
                //click: this.updateItem
                click: this.createItem
            },
            'itemsSearch button[action=cleanForm]': {
              click: this.cleanSearchForm
            },
            'itemsSearch button[action=search]': {
              click: this.searchItem
            }
        });
    },

    editItem: function(grid, record) {
        var view = Ext.widget('itemsEdit');
        view.down('form').loadRecord(record);
    },

    updateItem: function(button) {
    	var win = button.up('window'),
      form = win.down('form'),
      record = form.getRecord(),
      values = form.getValues();
    	record.set(values);
    	win.close();
      this.getItemsStore().sync();
    },

    createItem: function(button) {
    	var win = button.up('window'),
      form = win.down('form').getForm(); // Popup form
      record = form.getRecord();// get the underlying model instance (all fields)
      if (!record) {
        record = Ext.create('II.model.Items');
      }
      form.updateRecord(record);// update the record with the form data (fields: code, name and description)
      /*if (form.isValid()) {
        record.save({ // save the record to the server
          waitMsg: 'Saving..',
            success: function(item) {
                Ext.Msg.alert('Success', 'Item saved successfully.');
                Ext.getStore('Items').load();
            },
            failure: function(form, action) {
                if (action.failureType) {
                  if (action.failureType === Ext.form.action.Action.CLIENT_INVALID) {
                    Ext.Msg.alert('CLIENT_INVALID', 'Something has been missed. Please check and try again.');
                  }
                  if (action.failureType === Ext.form.action.Action.CONNECT_FAILURE) {
                    Ext.Msg.alert('CONNECT_FAILURE', 'Status: ' + action.response.status + ': ' + action.response.statusText);
                  }
                  if (action.failureType === Ext.form.action.Action.SERVER_INVALID) {
                    Ext.Msg.alert('SERVER_INVALID', action.result.message);
                  }
                } else {
                  Ext.Msg.alert('Failure', 'Failed to save item.');
                }
            }
        });
      }*/
      //values = form.getValues();
    	//record.set(values);// ??? VS updateRecord
    /*  if (!form.getForm().isDirty()) {
            Ext.Msg.alert('Status', 'No new student data to create.');
            return;
        }*/
      //if (stdMaster.isValid()) {
      form.submit({
        method: 'POST',
        waitMsg: 'Saving..',
        headers:
        {
            'Content-Type': 'application/json'
        },
        success: function (form, action) {
          Ext.Msg.alert('Status', 'Saved successfully.');
          Ext.getStore('Items').load();
          //TODO: repetir búsqueda
        },
        failure: function (form, action) {
            if (action.failureType === Ext.form.action.Action.CLIENT_INVALID) {
                Ext.Msg.alert('CLIENT_INVALID', 'Something has been missed. Please check and try again.');
            }
            if (action.failureType === Ext.form.action.Action.CONNECT_FAILURE) {
                Ext.Msg.alert('CONNECT_FAILURE', 'Status: ' + action.response.status + ': ' + action.response.statusText);
            }
            if (action.failureType === Ext.form.action.Action.SERVER_INVALID) {
                Ext.Msg.alert('SERVER_INVALID', action.result.message);
            }
        }
      });
      //}
    	win.close();

    },

    cleanSearchForm: function(button){
      var form = button.up('form');
      form.getForm().reset();
    },

    searchItem: function(button){
      var form = button.up('form');
      var params = form.getForm().getValues();
      var store = Ext.getStore('Items');
      var filters = [];

      for (var name in params) {
          value = params[name];
          if (value !== '' && value !== null) {
              filters[filters.length] = {
                  property : name,
                  value : value
              };
              console.log('property: ' + name + ' - value: ' + value);
          }
      }

      store.clearFilter();
      store.filter(filters);
      store.load();

    }

});
