Ext.define('II.store.Items', {
    extend: 'Ext.data.Store',
    model: 'II.model.Items',
    alias: 'widget.itemsStore',
    autoLoad: true,
    actionMethods: {
        create: 'POST',
        read: 'GET',
        update: 'PUT',
        destroy: 'POST'
    },
    proxy: {
	    api: {
//	        read: 'data/items.json',
	        update:'./items'
	    },
        type: 'rest',
        url: './items',
        reader: {
            type: 'json',
            root: 'items',
            successProperty: 'success'
        }
    }
});