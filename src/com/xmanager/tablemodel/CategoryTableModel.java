
package com.xmanager.tablemodel;

import com.xmanager.bl.CategoryBL;
import com.xmanager.entity.Category;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author scarface
 */
public class CategoryTableModel extends AbstractTableModel{

    private Object[][] data;
    String[] columnNames = {"Category Code","Category Name"};
    List<Category> categorys = new CategoryBL().getAllCategories();
    
    public CategoryTableModel() {
        convertListToTableData();
    }
    
    public CategoryTableModel(String code){
        categorys = new CategoryBL().getCategoryByFilterCode(code);
        convertListToTableData();
    }
    
    private void convertListToTableData() {
        data = new Object[categorys.size()][columnNames.length];
        for (int i = 0; i < categorys.size(); i++) {
            Category c = categorys.get(i);
            for (int x = 0; x < columnNames.length; x++) {
                data[i][0] = c.getCategoryCode();
                data[i][1] = c.getCategoryName();
            }
        }
    }
    
    @Override
    public int getRowCount() {
        return this.categorys.size();
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
