
package com.xmanager.tablemodel;

import com.xmanager.bl.StocksOutBL;
import com.xmanager.entity.Stockout;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author scarface
 */
public class NewStockoutEntryTableModel extends AbstractTableModel{

    private String[] columnNames = {"Entry ID","Item Code", "Destination", "Quantity", "Details", "Date"};
    private List<Stockout> stockout = new ArrayList<Stockout>();
    private Object[][] data;
    
    public NewStockoutEntryTableModel() {
        stockout =  new StocksOutBL().getNewStocksOut();
        convertListToTableData();
    }
    
    public NewStockoutEntryTableModel(Date stockdate) {
        stockout =  new StocksOutBL().getStockByDate(stockdate);
        convertListToTableData();
    }
    
    public NewStockoutEntryTableModel(String itemcode) {
        stockout =  new StocksOutBL().getStockByItem(itemcode);
        convertListToTableData();
    }
    
    private void convertListToTableData() {
        data = new Object[stockout.size()][columnNames.length];
        for (int i = 0; i < stockout.size(); i++) {
            Stockout r = stockout.get(i);
            for (int x = 0; x < columnNames.length; x++) {
                data[i][0] = r.getStockoutId();
                data[i][1] = r.getItem().getItemCode();
                data[i][2] = r.getSource().getDestinationCode();
                data[i][3] = r.getQuantity();
                data[i][4] = r.getDetails();
                data[i][5] = r.getDate();
            }
        }
    }
    
    @Override
    public int getRowCount() {
        return this.stockout.size();
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
