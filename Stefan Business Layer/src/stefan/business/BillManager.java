/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import stefan.business.objects.Bill;
import stefan.business.objects.BillItem;
import stefan.data.Billitems;
import stefan.data.Bills;

/**
 *
 * @author Matija
 */
public class BillManager {

    private EntityManager entityManager;

    public BillManager() {
        entityManager = QueryManager.getEntityManagerInstance();
    }

    public boolean SaveBill(stefan.business.objects.Bill bill) {
        try {
            
            if (bill.getIdBill()!=null)
            {
                deleteBill(bill.getIdBill());
            }
           
            stefan.data.Bills newBill = new Bills();
            newBill.setBillNumber(bill.getBillNumber());           
            newBill.setDate(bill.getDate());

            List<stefan.data.Billitems> billItems = new ArrayList<Billitems>();
          
            OrderManager orderManager = new OrderManager();
            for (stefan.business.objects.BillItem item : bill.getBillitemsList()) {
                stefan.data.Billitems newBillItem = new Billitems();

                newBillItem.setIdBill(newBill);
                newBillItem.setPackageNumber(item.getPackageNumber());
                newBillItem.setParts(item.getParts());
                newBillItem.setIdOrderItem(orderManager.getOrderItemById(item.getOrderItemId()));               
                newBillItem.setNiklanje(item.getNiklanje());
                newBillItem.setPricePerUnit(item.getPricePerPart());
                
                billItems.add(newBillItem);
            }
            newBill.setBillitemsList(billItems);
            entityManager.getTransaction().begin();
            entityManager.persist(newBill);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();//mislim da ovo ne dela
            return false;
        }
    }
    
    public List<Bill> getSavedBills()
    {
        List<Bill> bills=new ArrayList<Bill>();
        Query q = entityManager.createNamedQuery("Bills.findAll");
        List<stefan.data.Bills> BillsDB = q.getResultList();
        for (Bills billDB : BillsDB) 
        {
            Bill b=new Bill();
            b.setIdBill(billDB.getIdBill());
            b.setDate(billDB.getDate());
            b.setBillNumber(billDB.getBillNumber());
            
            List<BillItem> billItems=new ArrayList<BillItem>();
            for (Billitems bi : billDB.getBillitemsList()) 
            {
                BillItem item = OrderManager.populateBillItem(bi.getIdOrderItem());
                item.setParts(bi.getParts());
                item.setPackageNumber(bi.getPackageNumber());      
                item.setNiklanje(bi.getNiklanje());
                item.setPricePerPart(bi.getPricePerUnit());
                
               
                billItems.add(item);                              
            }
            b.setBillitemsList(billItems);           
            
            bills.add(b);
        }
        
        return bills;        
    }
    
    public stefan.data.Bills getBillById(Integer Id)
    {
       Query q = entityManager.createNamedQuery("Bills.findByIdBill");
       q.setParameter("idBill", Id);          
       List<stefan.data.Bills> items = q.getResultList();
       return items.get(0);   
    }
    
    public void deleteBill(Integer id)
    {
        try
        {             
             Bills bill=getBillById(id);
             entityManager.getTransaction().begin();
             entityManager.remove(bill);
             entityManager.getTransaction().commit();
        } 
        catch(Exception e)
        {
        }
    }    
    
}
