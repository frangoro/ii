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
	        update:'./item'
	    },
        type: 'rest',
        url: './item',
        reader: {
            type: 'json',
            root: 'items',
            successProperty: 'success'
        }
    }/*,
    filters: [
        {
            property: 'code',
            value   : ''
        }
    ]*/
});
