package com.xmanager.tablemodel;

import com.xmanager.bl.ItemBL;
import com.xmanager.bl.ReportBL;
import com.xmanager.entity.Item;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author scarface
 */
public class TotalStockOfItemsTableModel extends AbstractTableModel {

    private String[] columnNames = {"Item Code", "Item Name", "Destination","Quantity"};
    ReportBL rp = new ReportBL();
    private List<Item> items = new ArrayList<Item>();
    private Object[][] data;
    int ROW = 0;
    String destination;

    public TotalStockOfItemsTableModel(String destination) {
        items = new ItemBL().getAllItems();
        ROW = items.size();
        this.destination = destination;
        convertListToTableData();
    }

    private void convertListToTableData() {
        data = new Object[items.size()][columnNames.length];
        int i = 0;
        for (Item item : items) {
            //System.out.println("Value of i:" + i);
            //System.out.println("Value of Stockin:" + items.size());
            
            Integer stockinqty = rp.getTotalQuantityOfItemInStockIn(destination, item.getItemCode());
            Integer stockoutqty = rp.getTotalQuantityofItemInStockOut(destination, item.getItemCode());
            //if (obj.length > 0 && objs.length > 0) {
            data[i][0] = (String) item.getItemCode();
            data[i][1] = (String) item.getItemName();
            data[i][2] = (String) destination;
            data[i][3] = (Integer) stockinqty - stockoutqty;
            i++;
        }
       
    }

    @Override
    public int getRowCount() {
        return this.ROW;
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
