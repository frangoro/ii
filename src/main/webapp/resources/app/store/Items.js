Ext.define('II.store.Items', {
    extend: 'Ext.data.Store',
    model: 'II.model.Items',
    alias: 'widget.itemsStore',
    autoLoad: true,
    filterOnLoad: false,
    //remoteFilter: true,
    actionMethods: {
        create: 'POST',
        read: 'GET',
        update: 'PUT',
        destroy: 'POST'
    }/*,
    filters: [
        {
            property: 'code',
            value   : ''
        }
    ]*/
});
