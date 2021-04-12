
package com.xmanager.tablemodel;

import com.xmanager.bl.ReportBL;
import com.xmanager.entity.GeneralLedger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author scarface
 */
public class LedgerTableModel extends AbstractTableModel{

//    List<Object[]> dledger = new ArrayList<Object[]>();
//    List<Object[]> cledger = new ArrayList<Object[]>();
    List<GeneralLedger> dledger = new ArrayList<GeneralLedger>();
    String[] columnNames = {"Expenditure","Date","Dr", "Cr"};
    private Object[][] data;

    public LedgerTableModel(Date from, Date to, String type) {
            dledger = new ReportBL().getGLedgerByType(from, to, type);
            //cledger = new ReportBL().getLedgerCreditByYear(year);            
            convertListToTableData();            
              
        //credits = new ReportBL().getDebtorsReportCredits(accno);
        
    }
    
    private void convertListToTableData() {
        
        data = new Object[dledger.size()][columnNames.length];
        for (int i = 0; i < dledger.size(); i++) {
            GeneralLedger d = dledger.get(i); 
            for (int x = 0; x < columnNames.length; x++) {
                data[i][0] = d.getExpenditures().getExpenditures();
                data[i][1] = d.getDate();
                if(d.getType().equalsIgnoreCase("dr")){                    
                    data[i][2] = d.getAmount();
                    data[i][3] = new Float(0.00);
                }else if(d.getType().equalsIgnoreCase("cr")){
                    data[i][3] = d.getAmount();                    
                    data[i][2] = new Float(0.00);                                      
                }
//                data[i][0] = String.valueOf(d[1]);
//                if(cledger.size() > 0){
//                    Object[] d2 = cledger.get(i);
//                    if(d[1].toString().equalsIgnoreCase(d2[1].toString())){
//                        data[i][1] = Float.parseFloat(d[3].toString()) - Float.parseFloat(d2[3].toString());
//                    }else{
//                        data[i][1] = Float.parseFloat(d[3].toString());
//                    }                  
//                    //cledger.remove(0);
//                }else {
//                    data[i][1] = Float.parseFloat(d[3].toString());
//                }            
            }            
        }
    }
    
    

    @Override
    public int getRowCount() {
        return this.dledger.size();
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
