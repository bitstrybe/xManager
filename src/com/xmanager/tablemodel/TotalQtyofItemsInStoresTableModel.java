package com.xmanager.tablemodel;

import com.xmanager.bl.ItemBL;
import com.xmanager.bl.ReportBL;
import com.xmanager.entity.Item;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jenny
 */
public class TotalQtyofItemsInStoresTableModel extends AbstractTableModel {

    ReportBL rp = new ReportBL();
    private List<Item> items = new ArrayList<Item>();
    private Object[][] data;
    int ROW = 0;
    String destination;
    String[] columnNames = {"Item Code", "Item Name", "Shop Name", "Quantity"};

    public TotalQtyofItemsInStoresTableModel(String destination) {
        items = rp.getTotalItemsInStore(destination);
        ROW = items.size();
        this.destination = destination;
        convertListToTableData();
    }

    private void convertListToTableData() {
        try {
            data = new Object[items.size()][columnNames.length];
            int i = 0;
            for (Item item : items) {
//                System.out.println("Value of i:" + i);
//                System.out.println("Value of Stockin:" + items.size());
//                System.out.println("Item: "+item.getItemCode());

                Integer stockinqty = rp.getTotalQuantityOfItemInStockIn(destination, item.getItemCode());
                Integer salesqty = rp.getTotalItemsSoldInStores(destination, item.getItemCode());
                //if (obj.length > 0 && objs.length > 0) {
                data[i][0] = (String) item.getItemCode();
                data[i][1] = (String) item.getItemName();
                data[i][2] = (String) destination;
                data[i][3] = (Integer) stockinqty - salesqty;
                i++;
            }

        } catch (ArrayIndexOutOfBoundsException ax) {
        } catch (Exception ex) {
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
