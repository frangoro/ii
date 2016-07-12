Ext.define('II.controller.Items', {
    extend: 'Ext.app.Controller',

    views: ['items.List',
    'items.Edit'],
    
    stores: ['Items'],

    models: ['Items'],

    init: function() {
        this.control({
            'itemsList': {
                itemdblclick: this.editItem
            },
            'itemsEdit button[action=save]': {
                click: this.updateItem
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
    }

});