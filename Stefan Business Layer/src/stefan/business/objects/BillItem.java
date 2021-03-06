/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.business.objects;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import stefan.business.DesignManager;

/**
 *
 * @author Matija
 */
public class BillItem implements Comparable<BillItem> {

    private Integer orderId;
    private String orderNumber;
    private String position;
    private String packageNumber;
    private Integer designId;
    private String designName;
    private String designNumber;
    private String designIdentity;
    private String designClass;
    private String revision;
    private Integer orderItemId;
    private Integer quantityOrdered;
    private Integer quantityDelivered;
    private Date orderDate;
    private int parts;
    private BigDecimal pricePerPart;
    private Boolean niklanje; 
    private Integer itemOrderNumber;
    
    /**
     * @return the orderId
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return the designId
     */
    public Integer getDesignId() {
        return designId;
    }

    /**
     * @param designId the designId to set
     */
    public void setDesignId(Integer designId) {
        this.designId = designId;
    }

    /**
     * @return the designName
     */
    public String getDesignName() {
        return designName;
    }

    /**
     * @param designName the designName to set
     */
    public void setDesignName(String designName) {
        this.designName = designName;
    }

    /**
     * @return the designNumber
     */
    public String getDesignNumber() {
        return designNumber;
    }

    /**
     * @param designNumber the designNumber to set
     */
    public void setDesignNumber(String designNumber) {
        this.designNumber = designNumber;
    }

    /**
     * @return the designIdentity
     */
    public String getDesignIdentity() {
        return designIdentity;
    }

    /**
     * @param designIdentity the designIdentity to set
     */
    public void setDesignIdentity(String designIdentity) {
        this.designIdentity = designIdentity;
    }

    /**
     * @return the designClass
     */
    public String getDesignClass() {
        return designClass;
    }

    /**
     * @param designClass the designClass to set
     */
    public void setDesignClass(String designClass) {
        this.designClass = designClass;
    }
    
    /**
     * @return the revision
     */
    public String getRevision() {
        return revision;
    }

    /**
     * @param revision the revision to set
     */
    public void setRevision(String revision) {
        this.revision = revision;
    }

    /**
     * @return the orderItemId
     */
    public Integer getOrderItemId() {
        return orderItemId;
    }

    /**
     * @param orderItemId the orderItemId to set
     */
    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    /**
     * @return the quantityOrdered
     */
    public Integer getQuantityOrdered() {
        return quantityOrdered;
    }

    /**
     * @param quantityOrdered the quantityOrdered to set
     */
    public void setQuantityOrdered(Integer quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    /**
     * @return the quantityDelivered
     */
    public Integer getQuantityDelivered() {
        return quantityDelivered;
    }

    /**
     * @param quantityDelivered the quantityDelivered to set
     */
    public void setQuantityDelivered(Integer quantityDelivered) {
        this.quantityDelivered = quantityDelivered;
    }

    /**
     * @return the parts
     */
    public int getParts() {
        return parts;
    }

    /**
     * @param parts the parts to set
     */
    public void setParts(int parts) {
        this.parts = parts;
    }
    
    /**
     * @return the itemOrderNumber
     */
    public Integer getItemOrderNumber() {
        return itemOrderNumber;
    }

    /**
     * @param itemOrderNumber the itemOrderNumber to set
     */
    public void setItemOrderNumber(Integer orderNumber) {
        this.itemOrderNumber = orderNumber;
    }

    /**
     * @return the pricePerPart
     */
    public BigDecimal getPricePerPart() {
        if (pricePerPart == null) {
            return new BigDecimal(-1);
        }
        return pricePerPart;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal tempPricePerPart = getPricePerPart();
        //return pricePerPart.multiply(new BigDecimal(parts)).setScale(2, RoundingMode.HALF_UP);
        return tempPricePerPart.multiply(new BigDecimal(parts)).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * @param pricePerPart the pricePerPart to set
     */
    public void setPricePerPart(BigDecimal pricePerPart) {
        this.pricePerPart = pricePerPart;
    }

    public void CalculatePrice(stefan.business.objects.Design d) {
        if (d == null) {
            DesignManager manager = new DesignManager();
            stefan.data.Design design = manager.GetDesignsByDBId(designId);
            d = manager.mapData(design);
        }
        List<Integer> partsList = new ArrayList<Integer>();
        partsList.add(1);
        partsList.add(2);
        partsList.add(3);
        partsList.add(4);
        partsList.add(5);
        partsList.add(6);
        partsList.add(10);
        partsList.add(15);
        partsList.add(20);
        partsList.add(30);
        partsList.add(40);
        partsList.add(50);
        partsList.add(100);
        partsList.add(200);
        partsList.add(500);
        partsList.add(1000);

        List<BigDecimal> pricePerPartList = new ArrayList<BigDecimal>();
        pricePerPartList.add(d.getPcs1());
        pricePerPartList.add(d.getPcs2());
        pricePerPartList.add(d.getPcs3());
        pricePerPartList.add(d.getPcs4());
        pricePerPartList.add(d.getPcs5());
        pricePerPartList.add(d.getPcs6());
        pricePerPartList.add(d.getPcs10());
        pricePerPartList.add(d.getPcs15());
        pricePerPartList.add(d.getPcs20());
        pricePerPartList.add(d.getPcs30());
        pricePerPartList.add(d.getPcs40());
        pricePerPartList.add(d.getPcs50());
        pricePerPartList.add(d.getPcs100());
        pricePerPartList.add(d.getPcs200());
        pricePerPartList.add(d.getPcs500());
        pricePerPartList.add(d.getPcs1000());

        //paziti ako nema cijene manje od zadanog 
        //broja komada
        for (int i = 15; i >= 0; i--) {
            if (this.parts >= partsList.get(i)) {
                if (pricePerPartList.get(i) != null) {
                    pricePerPart = pricePerPartList.get(i);
                    break;
                } else {
                    continue;
                }
            }
        }
        //privremeno rjesenje
        //this.pricePerParts = -1 
        //postavljeno je u konstruktoru
    }

    public int compareTo(BillItem b) {
        if ((Long.parseLong(this.orderNumber) - (Long.parseLong(b.orderNumber))) == 0) {
            return (Integer.parseInt(this.position) - Integer.parseInt(b.position));
        } else {
            if (Long.parseLong(this.orderNumber) - (Long.parseLong(b.orderNumber)) > 0) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    /**
     * @return the packageNumber
     */
    public String getPackageNumber() {
        return packageNumber;
    }

    /**
     * @param packageNumber the packageNumber to set
     */
    public void setPackageNumber(String packageNumber) {
        this.packageNumber = packageNumber;
    }

    /**
     * @return the orderDate
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the niklanje
     */
    public Boolean getNiklanje() {
        return niklanje;
    }

    /**
     * @param niklanje the niklanje to set
     */
    public void setNiklanje(Boolean niklanje) {
        this.niklanje = niklanje;
    }
}
