Ext.Loader.setConfig({
    disableCaching: false
});

//Ext.Ajax.disableCaching = false;

Ext.application({
    name: 'II',
    appFolder: 'resources/app',
    launch: function() {
        Ext.create('Ext.container.Viewport', {
            layout: 'border',
            items: [
                {
                    region: 'north',
                    html: '<h1 class="x-panel-header">Computer Inventory</h1>',
                    autoHeight: true,
                    border: false,
                    margins: '0 0 5 0'
                }, {
                    region: 'west',
                    width: 0
                }, {
                    region: 'south',
                    height: 0
                }, {
                    region: 'east',
                    width: 0
                }, {
                    region: 'center',
                    xtype: 'tabpanel', // TabPanel itself has no title
                    activeTab: 0,      // First tab active by default
                    items: {
                      xtype: 'container',
                      items: [{
                        xtype: 'itemsSearch',
                        title: 'Items Search',
                        html : 'Find a item.'
                      }, {
                        xtype: 'itemsList',
                        title: 'Items',
                        html : 'List of items go here.'
                      }]
                    }
          }]

        });
    },

     controllers: [
        'Items'
    ]

});
