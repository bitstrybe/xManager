
package com.xmanager.tablemodel;

import com.xmanager.bl.ItemBL;
import com.xmanager.entity.Item;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author scarface
 */
public class ItemsTableModel extends AbstractTableModel{

    private String[] columnNames = {"Item code", "Item Name", "Item Category"};
    List<Item> items = new ArrayList<Item>();
    private Object[][] data;
    String category;
    
    public ItemsTableModel(){
        items = new ItemBL().getAllItems();
        convertListToTableData();
    }
    
    public ItemsTableModel(String itemCode, int i){
        convertListToTableData(itemCode.toString());
    }
    public ItemsTableModel(String category) {
        items = new ItemBL().getItemByCategory(category);
        convertListToTableData();
    }
    
    private void convertListToTableData() {        
        data = new Object[items.size()][columnNames.length];
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            for (int x = 0; x < columnNames.length; x++) {
                data[i][0] = item.getItemCode();
                data[i][1] = item.getItemName();
                data[i][2] = item.getCategory().getCategoryCode();
            }
        }
    }
    
    private void convertListToTableData(String itemCode) {
        items = new ItemBL().getItemByFilterItemCode(itemCode);
        data = new Object[items.size()][columnNames.length];
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            for (int x = 0; x < columnNames.length; x++) {
                data[i][0] = item.getItemCode();
                data[i][1] = item.getItemName();
            }
        }
    }
    
    
    @Override
    public int getRowCount() {
        return this.items.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.data[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object value, int row, int col) {        
        data[row][col] = value;
        this.fireTableCellUpdated(row, col);
    }
}
