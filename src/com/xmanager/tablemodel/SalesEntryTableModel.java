
package com.xmanager.tablemodel;

import com.xmanager.bl.SalesBL;
import com.xmanager.entity.Sales;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author scarface
 */
public class SalesEntryTableModel extends AbstractTableModel{
    private List<Sales> sales = new ArrayList<Sales>();
    private String[] columnNames = {"SalesID","Entry Date","Entered From","Shop","Item","Quantity", "Price"};
    private Object[][] data;
    
    public SalesEntryTableModel() {
        sales = new SalesBL().getNewSalesEntry();
        convertListToTableData();
    }
    
    public SalesEntryTableModel(Date salesdate, Date sd) {
        sales = new SalesBL().getSalesByDate(salesdate);
        convertListToTableData();
    }
    
    public SalesEntryTableModel(String shop, Date sd, Date ed) {
        sales = new SalesBL().getSalesByDate(shop, sd, ed);
        convertListToTableData();
    }
    
    public SalesEntryTableModel(String shop, String item, Date sd, Date ed) {
        sales = new SalesBL().getSalesByDate(shop, item, sd, ed);
        convertListToTableData();
    }


    private void convertListToTableData() {
        data = new Object[sales.size()][columnNames.length];
        for (int i = 0; i < sales.size(); i++) {
            Sales s = sales.get(i);
            for (int x = 0; x < columnNames.length; x++) {
                data[i][0] = s.getSalesId();
                data[i][1] = s.getDate();
                data[i][2] = s.getEntry();
                data[i][3] = s.getShop().getShopName();
                data[i][4] = s.getItem().getItemCode();
                data[i][5] = s.getQuantity();
                data[i][6] = s.getPrice();
            }
        }
    }

    @Override
    public int getRowCount() {
        return this.sales.size();
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
