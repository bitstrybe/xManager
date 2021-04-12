
package com.xmanager.tablemodel;

import com.xmanager.bl.ReportBL;
import com.xmanager.entity.Creditors;
import com.xmanager.entity.Debtors;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author scarface
 */
public class AccountsTableModel extends AbstractTableModel{
    List<Debtors> debts = new ArrayList<Debtors>();
    List<Creditors> credits = new ArrayList<Creditors>();
    String[] columnNames = {"Accounts No.","Account Name","Date", "Details","DR","CR","Balance"};
    private Object[][] data;
    int SIZE = 0;   

    public AccountsTableModel(String type,Date from, Date to, String accno) {
        if(type.equalsIgnoreCase("creditor")){
            credits = new ReportBL().getCreditorsReport(from, to, accno);
            SIZE = credits.size();
            convertListToTableDataA(credits);
        }
        else{
            debts = new ReportBL().getDebtorsReport(from, to, accno);
            SIZE = debts.size();
            convertListToTableDataB(debts);
        }       
        //credits = new ReportBL().getDebtorsReportCredits(accno);
        
    }

    private void convertListToTableDataA(List<Creditors> debts) {
        float sumDr = 0.0F, sumCr = 0.0F, total;
        data = new Object[debts.size()][columnNames.length];
        for (int i = 0; i < debts.size(); i++) {
            Creditors d = debts.get(i);
            if(d.getType().equalsIgnoreCase("dr")){                    
                    sumDr += d.getAmount();
                }
                else if(d.getType().equalsIgnoreCase("cr")){
                    sumCr += d.getAmount();
                }
            total = sumCr - sumDr;
            for (int x = 0; x < columnNames.length; x++) {
                data[i][0] = d.getAccounts().getAccountsId();
                data[i][1] = d.getAccounts().getName();
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
    
    private void convertListToTableDataB(List<Debtors> debts) {
        float sumDr = 0.0F, sumCr = 0.0F, total;
        data = new Object[debts.size()][columnNames.length];
        for (int i = 0; i < debts.size(); i++) {
            Debtors d = debts.get(i);
            if(d.getType().equalsIgnoreCase("dr")){                    
                    sumDr += d.getAmount();
                }
                else if(d.getType().equalsIgnoreCase("cr")){
                    sumCr += d.getAmount();
                }
            total = sumDr - sumCr;
            for (int x = 0; x < columnNames.length; x++) {
                data[i][0] = d.getAccounts().getAccountsId();
                data[i][1] = d.getAccounts().getName();
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
        return this.SIZE;
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
