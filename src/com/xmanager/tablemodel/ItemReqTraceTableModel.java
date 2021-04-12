package com.xmanager.tablemodel;

import com.xmanager.bl.ReportBL;
import com.xmanager.bl.StocksInBL;
import com.xmanager.entity.Stockin;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import lxe.utility.date.DateUtil;

/**
 *
 * @author scarface
 */
public class ItemReqTraceTableModel extends AbstractTableModel {

    List<Object[]> stockin = new ArrayList<Object[]>();
    List<Object[]> sales = new ArrayList<Object[]>();
    List<Object[]> entries = new ArrayList<Object[]>();
    List<Stockin> stocks = new ArrayList<Stockin>();
    String[] columnNames = {"Shop Name", "Date", "Item Code", "Item Name", "Requisition"};
    private Object[][] data;
    int ROW = 0;

    public ItemReqTraceTableModel(Date from, Date to) {
        ReportBL rb = new ReportBL();
        stocks = new StocksInBL().getRequistionByDate(from, to);
        System.out.println("SIZE: "+stocks.size());
        ROW = stocks.size();
        convertListToTableData(stocks);
    }
    
    public ItemReqTraceTableModel(String shop, Date from, Date to) {
        ReportBL rb = new ReportBL();
        stocks = new StocksInBL().getRequistionByDate(shop, from, to);
        System.out.println("SIZE: "+stocks.size());
        ROW = stocks.size();
        convertListToTableData(stocks);
    }
    
    public ItemReqTraceTableModel(String shop, String item, Date from, Date to) {
        ReportBL rb = new ReportBL();
        stocks = new StocksInBL().getRequistionByDate(shop, item, from, to);
        System.out.println("SIZE: "+stocks.size());
        ROW = stocks.size();
        convertListToTableData(stocks);
    }

    private void convertListToTableData(List<Stockin> ob) {
        data = new Object[ROW][columnNames.length];
        int c = 0;
        for (Stockin s : ob) {
            data[c][0] = s.getDestination().getDestination();
            data[c][1] = DateUtil.formatDate(s.getDate(), "yyyy-MM-dd");
            data[c][2] = s.getItem().getItemCode();
            data[c][3] = s.getItem().getItemName();
            data[c][4] = s.getQuantity();
            c++;
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
