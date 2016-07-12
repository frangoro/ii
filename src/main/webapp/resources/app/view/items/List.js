Ext.define('II.view.items.List' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.itemsList',

    title : 'All Items',

    store: 'Items',
    
    initComponent: function() {
        this.columns = [
            {header: 'Code',  dataIndex: 'code',  flex: 1},
            {header: 'Name',  dataIndex: 'name',  flex: 1},
            {header: 'Description', dataIndex: 'description', flex: 1}
        ];

        this.callParent(arguments);
    }
});