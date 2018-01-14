/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.business.objects;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Matija
 */
public class Bill {
  
    private Integer idBill;    
    private Date date;   
    private String billNumber;    
    private Integer businessPartnerId;
    private List<BillItem> billitemsList;

    /**
     * @return the idBill
     */
    public Integer getIdBill() {
        return idBill;
    }

    /**
     * @param idBill the idBill to set
     */
    public void setIdBill(Integer idBill) {
        this.idBill = idBill;
    }

    public Integer getBusinessPartnerId(){
        return businessPartnerId;
    }
    
    public void setBusinessPartnerId(Integer businessPartnerId){
        this.businessPartnerId = businessPartnerId;
    }
    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the billNumber
     */
    public String getBillNumber() {
        return billNumber;
    }

    /**
     * @param billNumber the billNumber to set
     */
    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    /**
     * @return the billitemsList
     */
    public List<BillItem> getBillitemsList() {
        return billitemsList;
    }

    /**
     * @param billitemsList the billitemsList to set
     */
    public void setBillitemsList(List<BillItem> billitemsList) {
        this.billitemsList = billitemsList;
    }
    
    @Override
    public String toString()
    {
        return billNumber;
    }
}
