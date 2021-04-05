/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.business.objects;

import java.util.Date;

/**
 *
 * @author robert.borosak
 */
public class DuplicateOrderedDesignDto {

    private int quantityOrdered;
    private String designNumber;
    private String orderNumber;
    private int quantityDelivered;
    private String businessPartnerName;
    private Date shippingDate;
    
    public DuplicateOrderedDesignDto(String designNumber, Integer quantityOrdered, Integer quantityDelivered,
            String orderNumber,String businessPartnerName, Date shippingDate){
        this.designNumber = designNumber;
        this.quantityOrdered = quantityOrdered;
        this.quantityDelivered = quantityDelivered;
        this.orderNumber = orderNumber;
        this.businessPartnerName = businessPartnerName;
        this.shippingDate = shippingDate;
    }
    



    /**
     * Get the value of shippingDate
     *
     * @return the value of shippingDate
     */
    public Date getShippingDate() {
        return shippingDate;
    }

    /**
     * Get the value of businessPartnerName
     *
     * @return the value of businessPartnerName
     */
    public String getBusinessPartnerName() {
        return businessPartnerName;
    }

    /**
     * Get the value of orderNumber
     *
     * @return the value of orderNumber
     */
    public String getOrderNumber() {
        return orderNumber;
    }

 

    /**
     * Get the value of quantityDelivered
     *
     * @return the value of quantityDelivered
     */
    public int getQuantityDelivered() {
        return quantityDelivered;
    }

     /**
     * Get the value of quantityOrdered
     *
     * @return the value of quantityOrdered
     */
    public int getQuantityOrdered() {
        return quantityOrdered;
    }
    
   

    /**
     * Get the value of designNumber
     *
     * @return the value of designNumber
     */
    public String getDesignNumber() {
        return designNumber;
    }

}
