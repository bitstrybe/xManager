
package com.xmanager.tablemodel;

import com.xmanager.bl.StocksInBL;
import com.xmanager.entity.Stockin;
import com.xmanager.entity.Stockout;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author scarface
 */
public class NewStockEntryTableModel extends AbstractTableModel{
    private String[] columnNames = {"Entry ID","Item Code", "Destination", "Quantity", "Details", "Date"};
    private List<Stockin> stockin = new ArrayList<Stockin>();
    private Object[][] data;
    
    public NewStockEntryTableModel() {
        stockin = new StocksInBL().getNewStocksIn();
        convertListToTableData();
    }
    
    public NewStockEntryTableModel(Date stockindate) {
        stockin = new StocksInBL().getStockInByDate(stockindate);
        convertListToTableData();
    }
    
    public NewStockEntryTableModel(String itemcode) {
        stockin = new StocksInBL().getStockInByItem(itemcode);
        convertListToTableData();
    }
    
    private void convertListToTableData() {
        data = new Object[stockin.size()][columnNames.length];
        for (int i = 0; i < stockin.size(); i++) {
            Stockin s = stockin.get(i);
            for (int x = 0; x < columnNames.length; x++) {
                data[i][0] = s.getStockinId();
                data[i][1] = s.getItem().getItemCode();
                data[i][2] = s.getDestination().getDestinationCode();
                data[i][3] = s.getQuantity();
                data[i][4] = s.getDetails();
                data[i][5] = s.getDate();
            }
        }
    }
    
    @Override
    public int getRowCount() {
        return this.stockin.size();
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
