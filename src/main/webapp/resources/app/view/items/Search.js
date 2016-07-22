Ext.define('II.view.items.Search', {
  extend: 'Ext.form.Panel',
  alias:'widget.itemsSearch',
  // Fields will be arranged vertically, stretched to full width
  layout: 'anchor',
  defaults: {
      anchor: '100%'
  },
  defaultType: 'textfield',
  initComponent: function() {
      this.items = [
          {
              xtype: 'form',
              items: [
                  {
                      xtype: 'textfield',
                      name : 'code',
                      fieldLabel: 'Code'
                  },{
                      xtype: 'textfield',
                      name : 'name',
                      fieldLabel: 'Name'
                  },
                  {
                      xtype: 'textfield',
                      name : 'description',
                      fieldLabel: 'Description'
                  }
              ]
          }
      ];

      this.buttons = [
          {
              text: 'Search',
              action: 'search'
          },
          {
              text: 'Clean form',
              action: 'cleanForm'
          }
      ];

      this.callParent(arguments);
  }
});
