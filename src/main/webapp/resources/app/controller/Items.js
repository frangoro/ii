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
                click: this.updateItem
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

    cleanSearchForm: function(button){
      var form = button.up('form');
      form.getForm().reset();
    },

    searchItem: function(button){
      var form = button.up('form');
      var params = form.getForm().getValues();
      var store = Ext.widget('itemsStore');
      var filters = [];

      for (var name in params) {
          value = params[name];
          if (value !== '' && value !== null) {
              filters[filters.length] = {
                  property : name,
                  value : value
              };
              console.log('property ' + name + 'value ' + value);
          }
      }

      store.clearFilter();
      store.filter(filters);

      this.getItemsStore().load();
    }

});
