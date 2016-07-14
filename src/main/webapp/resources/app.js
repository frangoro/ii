Ext.Loader.setConfig({
    disableCaching: false
});

//Ext.Ajax.disableCaching = false;

Ext.application({
    name: 'II',
    appFolder: 'resources/app',
    launch: function() {
        Ext.create('Ext.container.Viewport', {
            layout: 'fit',
            items: [
                {
                    xtype: 'itemsList',
                    title: 'Items',
                    html : 'List of items go here.'
                }
            ]
        });
    },

     controllers: [
        'Items'
    ]

});
