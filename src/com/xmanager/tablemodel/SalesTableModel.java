package com.xmanager.tablemodel;

import com.xmanager.bl.ReportBL;
import com.xmanager.entity.Sales;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author scarface
 */
public class SalesTableModel extends AbstractTableModel {

    private List<Sales> sales = new ArrayList<Sales>();
    private String[] columnNames = {"Sales Date", "Shop","Item Code","Item", "Quantity", "Price", "Total"};
    private Object[][] data;
    
    public SalesTableModel(String frequency) {
        if(frequency.equalsIgnoreCase("day")){
            sales = new ReportBL().getDailySales();
        }
        else if(frequency.equalsIgnoreCase("month")){
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date(System.currentTimeMillis()));
            sales = new ReportBL().getMonthlySales(String.valueOf(cal.get(Calendar.MONTH)));
            System.out.println(String.valueOf(cal.get(Calendar.MONTH)));
        }        
        convertListToTableData();
    }

    public SalesTableModel(String shopname, String from, String to) {
        sales = new ReportBL().getSalesDataByDate(shopname, from, to);
        convertListToTableData();
        fireTableDataChanged();
    }

    private void convertListToTableData() {
        data = new Object[sales.size()][columnNames.length];
        for (int i = 0; i < sales.size(); i++) {
            Sales s = sales.get(i);
            for (int x = 0; x < columnNames.length; x++) {
                data[i][0] = s.getDate();
                data[i][1] = s.getShop().getShopName();
                data[i][2] = s.getItem().getItemCode();
                data[i][3] = s.getItem().getItemName();
                data[i][4] = s.getQuantity();
                data[i][5] = s.getPrice();
                data[i][6] = new DecimalFormat("#.00").format(s.getQuantity() * s.getPrice());
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

