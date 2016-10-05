Ext.define('II.model.Items', {
    extend: 'Ext.data.Model',
    fields: ['id', 'code', 'name', 'description', 'brand', 'model', 'serialNumber', 'features', 'status', 'location', 'parent' ],
    // Pongo el proxy en el model en lugar de en el store para poder
    // tener la configuración del proxy disponible al crear un nuevo
    // record en la acción de crear nuevo item.
    proxy: {
	    api: {
//	        read: 'data/items.json',
	        update:'./item',
          create:'./item'
	    },
        type: 'rest',
        url: './item',
        reader: {
            type: 'json',
            root: 'items',
            successProperty: 'success'
        }
    }
});
