package com.xmanager.util;

import com.toedter.calendar.JDateChooser;
import com.xmanager.bl.AccountsBL;
import com.xmanager.bl.BankBL;
import com.xmanager.bl.CategoryBL;
import com.xmanager.bl.ExpenditureBL;
import com.xmanager.bl.ItemBL;
import com.xmanager.bl.SalesBL;
import com.xmanager.bl.ShopBL;
import com.xmanager.bl.StocksInBL;
import com.xmanager.bl.StocksOutBL;
import com.xmanager.entity.Accounts;
import com.xmanager.entity.Bank;
import com.xmanager.entity.Category;
import com.xmanager.entity.Expenditures;
import com.xmanager.entity.Item;
import com.xmanager.entity.Sales;
import com.xmanager.entity.Shops;
import com.xmanager.entity.Stockin;
import com.xmanager.entity.Stockout;
import java.awt.Component;
import java.awt.Container;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 *
 * @author scarface
 */
public class XManagerUtil {

    public XManagerUtil() {
    }

    public void clearAllFields(Container container) {
        for (Component C : container.getComponents()) {            
            if (C instanceof JTextField || C instanceof JFormattedTextField || C instanceof JTextArea) {
                ((JTextComponent) C).setText(""); //abstract superclass
            }
            if (C instanceof JComboBox) {                
                JComboBox jcombo = (JComboBox) C;
                //jcombo.removeAllItems();
            }
            if(C instanceof JList){
                JList jlist = (JList) C;
                jlist.removeAll();
            }
            if(C instanceof JDateChooser){
                JDateChooser jDateChooser = (JDateChooser) C;
                jDateChooser.setDate(null);
            }
        }
    }

    public void addCategoryListToCombo(JComboBox comboBox) throws Exception {
        comboBox.removeAllItems();
        comboBox.addItem("---");
        List<Category> category_list = new CategoryBL().getAllCategories();
        for (Category c : category_list) {
            comboBox.addItem(c.getCategoryCode());
        }
    }
    
    public void addAllShopListToCombo(JComboBox comboBox) throws Exception{
        comboBox.removeAllItems();
        comboBox.addItem("---");
        List<Shops> shops = new ShopBL().getAllShops();
        for(Shops shop : shops){
            comboBox.addItem(shop.getShopName());
        }
    }
    
    public void addShopListToCombo(JComboBox comboBox) throws Exception{
        comboBox.removeAllItems();
        comboBox.addItem("---");
        List<Shops> shops = new ShopBL().getAllShops(1);
        for(Shops shop : shops){
            comboBox.addItem(shop.getShopName());
        }
    }
    
    public void addWarehouseListToCombo(JComboBox comboBox) throws Exception{
        comboBox.removeAllItems();
        comboBox.addItem("---");
        List<Shops> shops = new ShopBL().getAllShops(2);
        for(Shops shop : shops){
            comboBox.addItem(shop.getShopName());
        }
    }

    public void addItemsToSearchList(JList jList, String categoryItem) {
        ItemBL itemBL = new ItemBL();
        List<Item> items = itemBL.getItemByCategory(categoryItem);
        jList.removeAll();
        DefaultListModel listModel = new DefaultListModel();
        for (Item item : items) {
            listModel.addElement(item.getItemCode() + "-" + item.getItemName());
        }
        jList.setModel(listModel);
    }
    
    public void addAccountsToCombo(JComboBox comboBox, String type) throws Exception{
        comboBox.removeAllItems();
        comboBox.addItem("---");
        List<Accounts> accs = new AccountsBL().getAllAccounts(type);
        for (Accounts acc : accs) {
            comboBox.addItem(acc.getAccountsId());
        }
    }
    
    public void addBankToCombo(JComboBox comboBox) throws Exception{
        comboBox.removeAllItems();
        comboBox.addItem("---");        
        List<Bank> banks = new BankBL().getBankData();
        for (Bank bank : banks) {
            comboBox.addItem(bank.getBankid());
        }
    }
    
    public void addExpenditureToCombo(JComboBox comboBox) throws Exception{
        comboBox.removeAllItems();
        comboBox.addItem("---");        
        List<Expenditures> exps = new ExpenditureBL().getAllExpenditures();
        for (Expenditures exp : exps) {
            comboBox.addItem(exp.getExpenditures());
        }
    }

    public void filterList(JList jList, String itemcode) {
        jList.removeAll();
        List<Item> items = new ItemBL().getItemByFilterItemCode(itemcode);
        DefaultListModel listModel = new DefaultListModel();
        for (Item item : items) {
            listModel.addElement(item.getItemCode() + "-" + item.getItemName());
        }
        jList.setModel(listModel);
    }

    public void showSuccessMessage(String message, JComponent component) {
        JOptionPane.showMessageDialog(component, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showErrorMessage(String message, JComponent component) {
        JOptionPane.showMessageDialog(component, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public String processQtyofStockForWareHouse(List<Stockin> sin, List<Stockout> sout, String destination, String itemCode){
        Integer stockTotalQty = 0, reqTotalQty = 0, sales = 0;
        for(Stockin stockin : sin){
            if(stockin.getDestination().getDestinationCode().equalsIgnoreCase(destination) && stockin.getItem().getItemCode().equalsIgnoreCase(itemCode)){
                stockTotalQty += stockin.getQuantity();
            }            
        }
        for(Stockout stockout : sout){
            if(stockout.getSource().getDestinationCode().equalsIgnoreCase(destination) && stockout.getItem().getItemCode().equalsIgnoreCase(itemCode)){
                reqTotalQty += stockout.getQuantity();
            }
        }
        
        
        return String.valueOf(stockTotalQty - reqTotalQty);
    }
    
    public String processQtyofStockForWareHouse(String destination, String itemCode){
        Integer stockTotalQty = 0, reqTotalQty = 0, sales = 0;
        List<Stockin> stockins = new StocksInBL().getItemsFromStockByDestination(destination);
        List<Stockout> stockos = new StocksOutBL().getStockoutByDestination(destination);
        List<Sales> salex = new SalesBL().getSalesByShopName(destination);
        for(Stockin stockin : stockins){
            if(stockin.getDestination().getDestinationCode().equalsIgnoreCase(destination) && stockin.getItem().getItemCode().equalsIgnoreCase(itemCode)){
                stockTotalQty += stockin.getQuantity();
            }            
        }
        for(Stockout stockout : stockos){
            if(stockout.getSource().getDestinationCode().equalsIgnoreCase(destination) && stockout.getItem().getItemCode().equalsIgnoreCase(itemCode)){
                reqTotalQty += stockout.getQuantity();
            }
        }
        for(Sales sale : salex){
            if(sale.getShop().getShopName().equalsIgnoreCase(destination) && sale.getItem().getItemCode().equalsIgnoreCase(itemCode)){
                sales += sale.getQuantity();
            }
        }
        
        
        return String.valueOf(stockTotalQty - (reqTotalQty+sales));
    }
    
    public String processQtyofStockForShop(List<Stockin> sin, List<Sales> sout, String destination, String itemCode){
        Integer stockTotalQty = 0, reqTotalQty = 0;
        for(Stockin stockin : sin){
            if(stockin.getDestination().getDestinationCode().equalsIgnoreCase(destination) && stockin.getItem().getItemCode().equalsIgnoreCase(itemCode)){
                stockTotalQty += stockin.getQuantity();
            }            
        }
        for(Sales s : sout){
            if(s.getShop().getShopName().equalsIgnoreCase(destination) && s.getItem().getItemCode().equalsIgnoreCase(itemCode)){
                reqTotalQty += s.getQuantity();
            }
        }
        return String.valueOf(stockTotalQty - reqTotalQty);
    }
}
