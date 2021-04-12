package com.xmanager.bl;

import com.xmanager.entity.BankEntry;
import com.xmanager.entity.Creditors;
import com.xmanager.entity.Debtors;
import com.xmanager.entity.GeneralLedger;
import com.xmanager.entity.Item;
import com.xmanager.entity.Sales;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author scarface
 */
public class ReportBL extends XManagerBL {

    public ReportBL() {
    }

    @Override
    public int insertData(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateData(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int removeData(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Sales> getDailySales() {
        return em.createQuery("SELECT s FROM Sales s WHERE s.date = CURRENT_DATE ORDER BY s.shop.shopName ASC").getResultList();
    }

    public List<Sales> getMonthlySales(String month) {
        return em.createQuery("SELECT s FROM Sales s WHERE SUBSTRING(s.date, 6, 1) = :month ORDER BY s.shop.shopName ASC")
                .setParameter("month", month)
                .getResultList();
    }

    public List<Sales> getSalesDataByDate(String shopname, String from, String to) {
        Date startdate = null, enddate = null;
        try {
            startdate = new Date(new SimpleDateFormat("dd-MM-yyyy").parse(from).getTime());
            enddate = new Date(new SimpleDateFormat("dd-MM-yyyy").parse(to).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(ReportBL.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (shopname.equalsIgnoreCase("---") && !from.isEmpty() && !to.isEmpty()) {
            Query q = em.createQuery("SELECT s FROM Sales s WHERE s.date BETWEEN :from AND :to ORDER BY s.salesId ASC", Sales.class)
                    .setParameter("from", startdate, TemporalType.DATE)
                    .setParameter("to", enddate, TemporalType.DATE);
            em.clear();
            return q.getResultList();
        } else {
            Query q = em.createQuery("SELECT s FROM Sales s WHERE s.shop.shopName = :shopName AND s.date BETWEEN :from AND :to ORDER BY s.salesId ASC", Sales.class)
                    .setParameter("shopName", shopname)
                    .setParameter("from", startdate, TemporalType.DATE)
                    .setParameter("to", enddate, TemporalType.DATE);
            em.clear();
            return q.getResultList();
        }
    }

    //returns total items in stores or warehouse.
    public List<Item> getTotalItemsInStore(String destination) {
        try {
            Query q = em.createQuery("SELECT DISTINCT s.item FROM Stockin s WHERE s.destination.destinationCode = :dest GROUP BY s.item.itemCode ORDER BY s.item.itemCode ASC")
                    .setParameter("dest", destination);
            em.clear();
            return q.getResultList();
        } catch (Exception ex) {
            Logger.getLogger(ReportBL.class.getName()).log(Level.SEVERE, destination, ex);
            em.clear();
            return null;
        }
    }

    //returns total items in stores or warehouse.
    public List<Object[]> getTotalItemsInStore(String shopName, String itemCode, Date from) {
        try {
            Query q = em.createQuery("SELECT r.destination.destinationCode, r.item.itemCode, r.item.itemName, SUM(r.quantity) FROM Stockin r WHERE r.destination.destinationCode = :shopname AND r.item.itemCode = :itemcode  AND r.date <= :from GROUP BY r.item.itemCode ORDER BY r.item.itemCode")
                    .setParameter("shopname", shopName)
                    .setParameter("itemcode", itemCode)
                    .setParameter("from", from);
            em.clear();
            return q.getResultList();
        } catch (Exception ex) {
            Logger.getLogger(ReportBL.class.getName()).log(Level.SEVERE, shopName, ex);
            em.clear();
            return null;
        }

    }

    public List<Object[]> getTotalItemsSoldByShop(String shopName, String itemCode, Date from) {
        try {
            return em.createQuery("SELECT s.shop.shopName, s.item.itemCode, s.item.itemName, SUM(s.quantity) FROM Sales s WHERE s.shop.shopName = :shopname  AND s.item.itemCode = :itemcode AND s.date <= :from GROUP BY s.item.itemCode ORDER BY s.item.itemCode")
                    .setParameter("shopname", shopName)
                    .setParameter("itemcode", itemCode)
                    .setParameter("from", from)
                    .getResultList();
        } catch (Exception ex) {
            Logger.getLogger(ReportBL.class.getName()).log(Level.SEVERE, shopName, ex);
            em.clear();
            return null;
        }
    }

    //returns total number of times an item has been sold in a shop in rows...
    public List<Object[]> getSalesEntries(String shopName, String itemCode, Date from, Date to) {
        try {
            Query q = em.createQuery("SELECT s.shop.shopName, s.item.itemCode, s.item.itemName, s.date, s.quantity FROM Sales s WHERE s.shop.shopName = :shopname  AND s.item.itemCode = :itemcode AND s.date BETWEEN :from AND :to")
                    .setParameter("shopname", shopName)
                    .setParameter("itemcode", itemCode)
                    .setParameter("from", from)
                    .setParameter("to", to);
            em.clear();
            return q.getResultList();
        } catch (Exception ex) {
            Logger.getLogger(ReportBL.class.getName()).log(Level.SEVERE, shopName, ex);
            em.clear();
            return null;
        }
    }

    public Integer getTotalItemsSoldInStores(String shopName, String itemcode) {
        try {
            Query q = em.createQuery("SELECT SUM(s.quantity) FROM Sales s WHERE s.shop.shopName = :shopname AND s.item.itemCode = :itemcode GROUP BY s.item.itemCode", Integer.class)
                    .setParameter("shopname", shopName)
                    .setParameter("itemcode", itemcode);
            if (q == null) {
                return 0;
            } else {
                return Integer.parseInt(q.getSingleResult().toString());
            }
        } catch (Exception ex) {
            //Logger.getLogger(ReportBL.class.getName()).log(Level.SEVERE, shopName, ex);
            return 0;
        } finally {
            em.clear();
        }
    }

    public Integer getTotalQuantityOfItemInStockIn(String destination, String itemcode) {
        try {
            Query q = em.createQuery("SELECT SUM(s.quantity) FROM Stockin s WHERE s.destination.destinationCode = :dest AND s.item.itemCode = :itemcode GROUP BY s.item.itemCode", Integer.class)
                    .setParameter("dest", destination)
                    .setParameter("itemcode", itemcode);
            if (q == null) {
                return 0;
            } else {
                return Integer.parseInt(q.getSingleResult().toString());
            }
        } catch (Exception ex) {
            //Logger.getLogger(ReportBL.class.getName()).log(Level.SEVERE, itemcode, ex);
            return 0;
        } finally {
            em.clear();
        }
    }

    public Integer getTotalQuantityofItemInStockOut(String destination, String itemcode) {
        try {
            Query q = em.createQuery("SELECT SUM(s.quantity) FROM Stockout s WHERE s.source.destination = :dest AND s.item.itemCode = :itemcode GROUP BY s.item.itemCode", Integer.class)
                    .setParameter("dest", destination)
                    .setParameter("itemcode", itemcode);
            if (q == null) {
                return 0;
            } else {
                return Integer.parseInt(q.getSingleResult().toString());
            }
        } catch (Exception ex) {
            //Logger.getLogger(ReportBL.class.getName()).log(Level.SEVERE, itemcode, ex);
            return 0;
        } finally {
            em.clear();
        }
    }

    public Integer getTotalQuantityofItemInStockOut(String destination) {
        Query q = em.createQuery("SELECT s.item.itemCode, s.item.itemName, s.source.destinationCode, s.details, s.date, SUM(s.quantity) FROM Stockout s WHERE s.source.destinationCode = :dest GROUP BY s.item.itemCode ORDER BY s.item.itemCode ASC")
                .setParameter("dest", destination);
        if (q == null) {
            return 0;
        } else {
            return Integer.parseInt(q.getSingleResult().toString());
        }
    }

    public List<Object[]> getDebtorsReportDebts(String accno) {
        return em.createQuery("SELECT d.accounts.accountsId, d.date, d.reason, SUM(d.amount) FROM Debtors d WHERE d.type = 'dr' BY d.accounts.accountsId ORDER BY d.accounts.accountsId")
                .setParameter("accno", em)
                .getResultList();
    }

    public List<Object[]> getDebtorsReportCredits(String accno) {
        return em.createQuery("SELECT d.accounts.accountsId, d.date, d.reason, SUM(d.amount) FROM Debtors d WHERE d.type = 'cr' BY d.accounts.accountsId ORDER BY d.accounts.accountsId")
                .setParameter("accno", em)
                .getResultList();
    }

    public List<Debtors> getDebtorsReport(Date from, Date to, String accno) {
        return em.createQuery("SELECT d FROM Debtors d WHERE d.accounts.accountsId = :accno AND d.date BETWEEN :from AND :to")
                .setParameter("accno", accno)
                .setParameter("from", from, TemporalType.DATE)
                .setParameter("to", to, TemporalType.DATE)
                .getResultList();
    }

    public List<Creditors> getCreditorsReport(Date from, Date to, String accno) {
        return em.createQuery("SELECT c FROM Creditors c WHERE c.accounts.accountsId = :accno AND c.date BETWEEN :from AND :to")
                .setParameter("accno", accno)
                .setParameter("from", from, TemporalType.DATE)
                .setParameter("to", to, TemporalType.DATE)
                .getResultList();
    }

    public List<BankEntry> getBankEntryReport(Date from, Date to, String bankno) {
        return em.createQuery("SELECT b FROM BankEntry b WHERE b.bank.bankid = :accno AND b.date BETWEEN :from AND :to")
                .setParameter("accno", bankno)
                .setParameter("from", from, TemporalType.DATE)
                .setParameter("to", to, TemporalType.DATE)
                .getResultList();
    }

    public List<GeneralLedger> getGLedgerByType(Date from, Date to, String type) {
        return em.createQuery("SELECT g FROM GeneralLedger g WHERE g.expenditures.expenditures = :type AND g.date BETWEEN :from AND :to ORDER BY g.generalLedgerId")
                .setParameter("type", type)
                .setParameter("from", from, TemporalType.DATE)
                .setParameter("to", to, TemporalType.DATE)
                .getResultList();
    }
}
