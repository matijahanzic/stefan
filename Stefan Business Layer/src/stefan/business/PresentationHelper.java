/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Robert
 */
public class PresentationHelper {

    private String designNumber;
    private String designIdentity;
    private String designClass;
    private String designName;
    private String revision;
    private Date designDate;
    private boolean niklanje;
    private boolean isTokarenje;
    private String position;
    private int parts;
    private int desingDBid;
    private BigDecimal pricePerPart;    
    private Date shippingDate; 
   

    public PresentationHelper() {
    }
    public PresentationHelper(stefan.business.objects.Design design, int parts, Date shippingDate) {        
        this.designNumber = design.getDesignNumber();
        this.designIdentity = design.getDesignIdentity();
        this.designClass = design.getClassMark();
        this.designName = design.getName();
        this.revision = design.getRevision();
        this.designDate = design.getDate();
        this.niklanje = design.isNiklanje();
        this.isTokarenje = design.getIsTokarenje();
        this.parts = parts;
        this.shippingDate = shippingDate;
        this.desingDBid = design.getIdDesign();
        this.pricePerPart = new BigDecimal("-1");        
        

        CalculatePrice(design);
    }

    /**
     * @return the designNumber
     */
    public String getDesignNumber() {
        return designNumber;
    }

    /**
     * @return the designIdentity
     */
    public String getDesignIdentity() {
        return designIdentity;
    }

    /**
     * @return the designClass
     */
    public String getDesignClass() {
        return designClass;
    }

    /**
     * @return the designName
     */
    public String getDesignName() {
        return designName;
    }

    /**
     * @return the revision
     */
    public String getRevision() {
        return revision;
    }    
    
    /**
     * @return the designDate
     */
    public Date getDesignDate() {
        return designDate;
    }

    /**
     * @return the niklanje
     */
    public boolean getNiklanje() {
        return niklanje;
    }
    
    /**
     * @return the isTokarenje
     */
    public boolean getIsTokarenje() {
        return isTokarenje;
    }

    /**
     * @return the parts
     */
    public int getParts() {
        return parts;
    }
    
    /**
     * @return the shippingDate
     */
    public Date getShippingDate() {
        return shippingDate;
    }

    /**
     * @return the pricePerPart
     */
    public BigDecimal getPricePerPart() {
        if(pricePerPart == null){
            return new BigDecimal(-1);
        }
        return pricePerPart;
    }

    /**
     * @return the totalPrice
     */
    public BigDecimal getTotalPrice() {
        BigDecimal tempPricePerPart = getPricePerPart();
       // return pricePerPart.multiply(new BigDecimal(parts)).setScale(2, RoundingMode.HALF_UP);
         return tempPricePerPart.multiply(new BigDecimal(parts)).setScale(2, RoundingMode.HALF_UP);
    }

    private void CalculatePrice(stefan.business.objects.Design d) {
        if(d==null)
        {
            DesignManager manager=new DesignManager();
            stefan.data.Design design = manager.GetDesignsByDBId(getDesingDBid());
            d=manager.mapData(design);
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
        for(int i = 15; i>=0; i--){
            if(this.parts >= partsList.get(i)){
                if(pricePerPartList.get(i)!=null){
                    this.setPricePerPart(pricePerPartList.get(i));                    
                    break;
                }else
                    continue;
            }
        }
        //privremeno rjesenje
        //this.pricePerParts = -1 
        //postavljeno je u konstruktoru
    }

    /**
     * @param designNumber the designNumber to set
     */
    public void setDesignNumber(String designNumber) {
        this.designNumber = designNumber;
    }

    /**
     * @param designIdentity the designIdentity to set
     */
    public void setDesignIdentity(String designIdentity) {
        this.designIdentity = designIdentity;
    }

    /**
     * @param designClass the designClass to set
     */
    public void setDesignClass(String designClass) {
        this.designClass = designClass;
    }

    /**
     * @param designName the designName to set
     */
    public void setDesignName(String designName) {
        this.designName = designName;
    }
    
     /**
     * @param revision the revision to set
     */
    public void setRevision(String revision) {
        this.revision = revision;
    }

    /**
     * @param designDate the designDate to set
     */
    public void setDesignDate(Date designDate) {
        this.designDate = designDate;
    }

    /**
     * @param niklanje the niklanje to set
     */
    public void setNiklanje(boolean niklanje) {
        this.niklanje = niklanje;
    }
    
    /**
     * @param isTokarenje the isTokarenje to set
     */
    public void setIsTokarenje(boolean isTokarenje) {
        this.isTokarenje = isTokarenje;
    }

    /**
     * @param parts the parts to set
     */
    public void setParts(int parts) {
        this.parts = parts;
        CalculatePrice(null);
    }
    
    /**
     * @param shippingDate the shippingDate to set
     */
    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    /**
     * @param pricePerPart the pricePerPart to set
     */
    public void setPricePerPart(BigDecimal pricePerPart) {
        this.pricePerPart = pricePerPart;
    }

   

    /**
     * @return the desingDBid
     */
    public int getDesingDBid() {
        return desingDBid;
    }

    /**
     * @param desingDBid the desingDBid to set
     */
    public void setDesingDBid(int desingDBid) {
        this.desingDBid = desingDBid;
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
}
