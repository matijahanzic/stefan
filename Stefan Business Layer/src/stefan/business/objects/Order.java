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
public class Order {    
   
    private Integer idOrder;    
    private boolean isDelivered;    
    private Date date;    
    private List<OrderItem> orderitemsList;
    private String orderNumber;
    private Integer businessPartnerId;
    private Date shippingDate;  
    
    
    /**
     * @return the idOrder
     */
    public Integer getIdOrder() {
        return idOrder;
    }

    /**
     * @param idOrder the idOrder to set
     */
    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    /**
     * @return the isDelivered
     */
    public boolean isIsDelivered() {
        return isDelivered;
    }

    /**
     * @param isDelivered the isDelivered to set
     */
    public void setIsDelivered(boolean isDelivered) {
        this.isDelivered = isDelivered;
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
     * @return the orderitemsList
     */
    public List<OrderItem> getOrderitemsList() {
        return orderitemsList;
    }

    /**
     * @param orderitemsList the orderitemsList to set
     */
    public void setOrderitemsList(List<OrderItem> orderitemsList) {
        this.orderitemsList = orderitemsList;
    }

    /**
     * @return the orderNumber
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    /**
     * @return the businessPartnerId
     */   
    public Integer getBusinessPartnerId(){
        return businessPartnerId;
    }
 
    /**
     * @param businessPartnerId the businessPartnerId to set
     */
    public void setBusinessPartnerId(Integer businessPartnerId){
        this.businessPartnerId = businessPartnerId;
    }
    
    /**
     * @return the shippingDate
     */
    public Date getShippingDate() {
        return shippingDate;
    }

    /**
     * @param shippingDate the shippingDate to set
     */
    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }
}
