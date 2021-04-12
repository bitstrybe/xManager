package com.xmanager.tablemodel;

import com.xmanager.bl.ReportBL;
import com.xmanager.bl.StocksInBL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author scarface
 */
public class StockTraceTableModel extends AbstractTableModel {

    List<Object[]> stockin = new ArrayList<Object[]>();
    List<Object[]> sales = new ArrayList<Object[]>();
    List<Object[]> entries = new ArrayList<Object[]>();
    List<Object[]> stocks = new ArrayList<Object[]>();
    String[] columnNames = {"Item Code", "Item Name", "Shop Name", "Date", "Total Requisition", "Opening Balance", "Sales", "Balance"};
    private Object[][] data;
    int ROW = 0;

    public StockTraceTableModel(String shopName, String item, Date from, Date to) {
        ReportBL rb = new ReportBL();
        entries = rb.getSalesEntries(shopName, item, from, to);
        stocks = new StocksInBL().getTotalStockInEntries(shopName, item, from, to);
        stockin = rb.getTotalItemsInStore(shopName, item, from);
        ROW = entries.size();
        
//        System.out.println("ROW: " + ROW);
//        System.out.println("STOCK IN: " + stockin.size());
        
        sales = rb.getTotalItemsSoldByShop(shopName, item, from);
        getTotalQuantityOfItem();
    }

    private void getTotalQuantityOfItem() {
        int result = 0;
        if (stockin.size() > 0 && sales.size() > 0) {
            Object[] ob = stockin.get(0);
            Object[] obs = sales.get(0);
            if (stocks.size() > 0) {
                Object[] s = stocks.get(0);
                result = Integer.parseInt(ob[3].toString()) - Integer.parseInt(obs[3].toString());
                convertListToTableData(ob, Integer.parseInt(s[1].toString()), result);
            }else{
                result = Integer.parseInt(ob[3].toString()) - Integer.parseInt(obs[3].toString());
                convertListToTableData(ob, result);
            }

        } else if (stockin.size() > 0) {
            Object[] ob = stockin.get(0);
            if (stocks.size() > 0) {
                Object[] s = stocks.get(0);
                result = Integer.parseInt(ob[3].toString());
                convertListToTableData(ob, Integer.parseInt(s[1].toString()), result);
            }
            else{
                result = Integer.parseInt(ob[3].toString());
                convertListToTableData(ob, result);
            }
        }
    }

    private void convertListToTableData(Object[] ob, Integer s, int qtyItem) {
        data = new Object[entries.size()][columnNames.length];
        int c = 0;
        if (entries.size() > 0) {
            data[c][4] = s;
            data[c][5] = qtyItem;
            while (entries.size() > 0) {
                data[c][0] = ob[1];
                data[c][1] = ob[2];
                data[c][2] = ob[0];
                Object[] obj = entries.get(0);
                data[c][3] = (java.util.Date) obj[3];
                data[c][6] = obj[4];
                data[c][7] = (qtyItem + s) - Integer.parseInt(obj[4].toString());
                qtyItem -= Integer.parseInt(obj[4].toString());
                c++;
                entries.remove(0);
            }
        }
        else if(stocks.size() > 0){
            data = new Object[stocks.size()][columnNames.length];
            data[c][4] = s;
            data[c][5] = qtyItem;
            while(stocks.size() > 0){
                data[c][0] = ob[1];
                data[c][1] = ob[2];
                data[c][2] = ob[0];                
                data[c][3] = null;
                data[c][6] = 0;
                data[c][7] = (qtyItem + s);
                //qtyItem -= Integer.parseInt(obj[4].toString());
                c++;
                stocks.remove(0);
            }
        }


    }

    private void convertListToTableData(Object[] ob, int qtyItem) {
        data = new Object[entries.size()][columnNames.length];
        int c = 0;
        if (ob.length > 0) {
            data[c][4] = 0;
            data[c][5] = qtyItem;
            while (entries.size() > 0) {
                data[c][0] = ob[1];
                data[c][1] = ob[2];
                data[c][2] = ob[0];
                Object[] obj = entries.get(0);
                data[c][3] = (java.util.Date) obj[3];
                data[c][6] = obj[4];
                data[c][7] = qtyItem - Integer.parseInt(obj[4].toString());
                qtyItem -= Integer.parseInt(obj[4].toString());
                c++;
                entries.remove(0);
            }
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
