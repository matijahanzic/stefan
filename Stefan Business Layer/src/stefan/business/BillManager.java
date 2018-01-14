/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.business;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import stefan.business.objects.Bill;
import stefan.business.objects.BillItem;
import stefan.business.objects.BusinessPartner;
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
            newBill.setBusinessPartnerId(getBp(bill.getBusinessPartnerId()));

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
            b.setBusinessPartnerId(billDB.getBusinessPartnerId().getId());
            
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
    
    private stefan.data.Businesspartner getBp(Integer id){
        Query q = entityManager.createNamedQuery("Businesspartner.findById");
        q.setParameter("id", id);
        List<stefan.data.Businesspartner> bps = q.getResultList();
        return bps.get(0);
    }
    
     public List<BusinessPartner> getBusinessPartners()
    {
       List<BusinessPartner> bpList = new ArrayList<BusinessPartner>();
       
        Query q = entityManager.createNamedQuery("Businesspartner.findAll");
        List<stefan.data.Businesspartner> bps = q.getResultList();
        for (stefan.data.Businesspartner item : bps) 
        {
            stefan.business.objects.BusinessPartner bp =new BusinessPartner();
            bp.setDisplayName(item.getDisplayName());
            bp.setId(item.getId());
            bp.setName(item.getName());
            bp.setPrintInd(item.getPrintInd());
            bp.setPrintRow1(item.getPrintRow1());
            bp.setPrintRow2(item.getPrintRow2());
            bp.setPrintRow3(item.getPrintRow3());
            
            bpList.add(bp);
        }
        
        return bpList;        
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
