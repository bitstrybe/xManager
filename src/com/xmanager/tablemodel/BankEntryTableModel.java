
package com.xmanager.tablemodel;

import com.xmanager.bl.ReportBL;
import com.xmanager.entity.BankEntry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author scarface
 */
public class BankEntryTableModel extends AbstractTableModel{
    
    List<BankEntry> bankentry = new ArrayList<BankEntry>();
    String[] columnNames = {"Bank Code","Bank Name.","Date", "Details","DR","CR", "Balance"};
    private Object[][] data; 

    public BankEntryTableModel(Date from, Date to, String accno) {
            bankentry = new ReportBL().getBankEntryReport(from, to, accno);
            convertListToTableData();
              
        //credits = new ReportBL().getDebtorsReportCredits(accno);
        
    }
    
    private void convertListToTableData() {
        float sumDr = 0.0F, sumCr = 0.0F, total;
        data = new Object[bankentry.size()][columnNames.length];
        for (int i = 0; i < bankentry.size(); i++) {
            BankEntry d = bankentry.get(i);
            if(d.getType().equalsIgnoreCase("dr")){                    
                    sumDr += d.getAmount();
                }
                else if(d.getType().equalsIgnoreCase("cr")){
                    sumCr += d.getAmount();
                }
            total = sumDr - sumCr;
            for (int x = 0; x < columnNames.length; x++) {
                data[i][0] = d.getBank().getBankid();
                data[i][1] = d.getBank().getBankName();
                data[i][2] = d.getDate();
                data[i][3] = d.getDetails();
                if(d.getType().equalsIgnoreCase("dr")){                    
                    data[i][4] = d.getAmount();
                    data[i][5] = new Float(0.00);
                }
                else if(d.getType().equalsIgnoreCase("cr")){
                    data[i][5] = d.getAmount();                    
                    data[i][4] = new Float(0.00);                                      
                }
                data[i][6] = total;
            }            
        }
    }
    
    

    @Override
    public int getRowCount() {
        return this.bankentry.size();
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
