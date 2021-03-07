/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.business.objects;



import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 *
 * @author robert.borosak
 */

public class OpenOrderDto implements Comparable  {

   public OpenOrderDto(Integer idOrderItems,Integer quantityOrdered,Integer quantityDelivered,String orderNumber, Date shippingDate,
           String city,String designNumber,BigDecimal pcs1,BigDecimal pcs2,BigDecimal pcs3,BigDecimal pcs4,BigDecimal pcs5,BigDecimal pcs6,BigDecimal pcs10,
           BigDecimal pcs15,BigDecimal pcs20,BigDecimal pcs30,BigDecimal pcs40,BigDecimal pcs50,BigDecimal pcs100,BigDecimal pcs200,BigDecimal pcs500,BigDecimal pcs1000,boolean niklanje,boolean isTokarenje){
       
       this.idOrderItems = idOrderItems;
       this.quantityOrdered = quantityOrdered;
       this.quantityDelivered = quantityDelivered;
       this.orderNumber=orderNumber;
       this.shippingDate = shippingDate;
       this.city = city;
       this.designNumber = designNumber;
       this.pcs1 = pcs1;
       this.pcs2 = pcs2;
       this.pcs3 = pcs3;
        this.pcs4=pcs4;
    
    this.pcs5=pcs5;
    
    this.pcs6=pcs6;
    this.pcs10=pcs10;
    
    this.pcs15=pcs15;
    
    this.pcs20=pcs20;
    
    this.pcs30=pcs30;
    
    this.pcs40=pcs40;
    
    this.pcs50=pcs50;
    
    this.pcs100=pcs100;
    
    this.pcs200=pcs200;
    
    this.pcs500=pcs500;
    
   this.pcs1000=pcs1000;  
    
    this.niklanje=niklanje;
    
    this.isTokarenje=isTokarenje;
   }
  
  //order items 

    private Integer idOrderItems;
  
    private Integer quantityOrdered;   
   
    private Integer quantityDelivered;   
  
    //orders
    
    private String orderNumber;

    private Date shippingDate;  
    
    //bp
 
    private String city;
    
    //design
   
    private String designNumber;    
    
    private BigDecimal pcs1;
    
    private BigDecimal pcs2;
    
    private BigDecimal pcs3;
    
    private BigDecimal pcs4;
    
    private BigDecimal pcs5;
    
    private BigDecimal pcs6;
    
    private BigDecimal pcs10;
    
    private BigDecimal pcs15;
    
    private BigDecimal pcs20;
    
    private BigDecimal pcs30;
    
    private BigDecimal pcs40;
    
    private BigDecimal pcs50;
    
    private BigDecimal pcs100;
    
    private BigDecimal pcs200;
    
    private BigDecimal pcs500;
    
    private BigDecimal pcs1000;  
    
    private boolean niklanje;
    
    private boolean isTokarenje;
    
 

    /**
     * Get the value of idOrderItems
     *
     * @return the value of idOrderItems
     */
    public int getIdOrderItems() {
        return idOrderItems;
    }

    /**
     * Set the value of idOrderItems
     *
     * @param idOrderItems new value of idOrderItems
     */
    public void setIdOrderItems(int idOrderItems) {
        this.idOrderItems = idOrderItems;
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
    
     /**
     * @return the KW
     */
    public KWDetailsDto getKW() {
        if (shippingDate == null)
        {
            return new KWDetailsDto("0","0");
        }
        
        
        Calendar instance = Calendar.getInstance();
        instance.setFirstDayOfWeek(2);
     
        instance.setMinimalDaysInFirstWeek(4);
        instance.setTime(shippingDate);
        
        //izračunaj kw za datum isporuke
        String kw = CalculateKw(instance);
        
        //ako je niklanje onda je kw tjedan dana ranije
        //a kw za datum isporuke se upisuje u napomenu za niklanje
        if(isNiklanje()){
             instance.add(Calendar.DATE, -7);
             String kwNiklanje = CalculateKw(instance);
             return new KWDetailsDto(kwNiklanje, kw.split("-")[1]);
        }
        
        //ako nema niklanja, nema niti napomene za niklanje
        return new KWDetailsDto(kw, null);
    }
    
    private String CalculateKw(Calendar instance){
        
        Integer currentDayOfWeek = instance.get(Calendar.DAY_OF_WEEK);
        
        Integer kw = instance.get(Calendar.WEEK_OF_YEAR);        
      
        //za dostavu u četvrtak, petak, subotu i nedjelju treba poslati u tom kw-u u utorak
        if(currentDayOfWeek == 1 || (currentDayOfWeek >= 5)){
            Integer year = instance.get(Calendar.YEAR);
            return GetKWString(kw, year);
        }
            
        //inače treba poslati tjedan dana ranije
        else{
          //ako nije prvi tjedan u godini
          if(kw > 1){
                Integer year = instance.get(Calendar.YEAR);
                Integer lastWeekKw = kw - 1;  
                return GetKWString(lastWeekKw, year);
          }
          //ako je prvi tjedan u godini onda pogledati koji je to tjedan u prošloj godini
          else{
              instance.add(Calendar.DATE, -7);
              Integer lastWeekKw = instance.get(Calendar.WEEK_OF_YEAR); 
              Integer year = instance.get(Calendar.YEAR);
              
              return GetKWString(lastWeekKw, year);
          }
        }
    }
    
    private String GetKWString(Integer kw, Integer year){
        String kwString = kw.toString();
        if(kw < 10){
            kwString = "0"+kwString;    
        }
        return (year + "-" + kwString);
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
     * @return the pcs1
     */
    public BigDecimal getPcs1() {
        return pcs1;
    }

    /**
     * @param pcs1 the pcs1 to set
     */
    public void setPcs1(BigDecimal pcs1) {
        this.pcs1 = pcs1;
    }

    /**
     * @return the pcs2
     */
    public BigDecimal getPcs2() {
        return pcs2;
    }

    /**
     * @param pcs2 the pcs2 to set
     */
    public void setPcs2(BigDecimal pcs2) {
        this.pcs2 = pcs2;
    }

    /**
     * @return the pcs3
     */
    public BigDecimal getPcs3() {
        return pcs3;
    }

    /**
     * @param pcs3 the pcs3 to set
     */
    public void setPcs3(BigDecimal pcs3) {
        this.pcs3 = pcs3;
    }

    /**
     * @return the pcs4
     */
    public BigDecimal getPcs4() {
        return pcs4;
    }

    /**
     * @param pcs4 the pcs4 to set
     */
    public void setPcs4(BigDecimal pcs4) {
        this.pcs4 = pcs4;
    }

    /**
     * @return the pcs5
     */
    public BigDecimal getPcs5() {
        return pcs5;
    }

    /**
     * @param pcs5 the pcs5 to set
     */
    public void setPcs5(BigDecimal pcs5) {
        this.pcs5 = pcs5;
    }

    /**
     * @return the pcs6
     */
    public BigDecimal getPcs6() {
        return pcs6;
    }

    /**
     * @param pcs6 the pcs6 to set
     */
    public void setPcs6(BigDecimal pcs6) {
        this.pcs6 = pcs6;
    }

    /**
     * @return the pcs10
     */
    public BigDecimal getPcs10() {
        return pcs10;
    }

    /**
     * @param pcs10 the pcs10 to set
     */
    public void setPcs10(BigDecimal pcs10) {
        this.pcs10 = pcs10;
    }

    /**
     * @return the pcs15
     */
    public BigDecimal getPcs15() {
        return pcs15;
    }

    /**
     * @param pcs15 the pcs15 to set
     */
    public void setPcs15(BigDecimal pcs15) {
        this.pcs15 = pcs15;
    }

    /**
     * @return the pcs20
     */
    public BigDecimal getPcs20() {
        return pcs20;
    }

    /**
     * @param pcs20 the pcs20 to set
     */
    public void setPcs20(BigDecimal pcs20) {
        this.pcs20 = pcs20;
    }

    /**
     * @return the pcs30
     */
    public BigDecimal getPcs30() {
        return pcs30;
    }

    /**
     * @param pcs30 the pcs30 to set
     */
    public void setPcs30(BigDecimal pcs30) {
        this.pcs30 = pcs30;
    }

    /**
     * @return the pcs40
     */
    public BigDecimal getPcs40() {
        return pcs40;
    }

    /**
     * @param pcs40 the pcs40 to set
     */
    public void setPcs40(BigDecimal pcs40) {
        this.pcs40 = pcs40;
    }

    /**
     * @return the pcs50
     */
    public BigDecimal getPcs50() {
        return pcs50;
    }

    /**
     * @param pcs50 the pcs50 to set
     */
    public void setPcs50(BigDecimal pcs50) {
        this.pcs50 = pcs50;
    }

    /**
     * @return the pcs100
     */
    public BigDecimal getPcs100() {
        return pcs100;
    }

    /**
     * @param pcs100 the pcs100 to set
     */
    public void setPcs100(BigDecimal pcs100) {
        this.pcs100 = pcs100;
    }

    /**
     * @return the pcs200
     */
    public BigDecimal getPcs200() {
        return pcs200;
    }

    /**
     * @param pcs200 the pcs200 to set
     */
    public void setPcs200(BigDecimal pcs200) {
        this.pcs200 = pcs200;
    }

    /**
     * @return the pcs500
     */
    public BigDecimal getPcs500() {
        return pcs500;
    }

    /**
     * @param pcs500 the pcs500 to set
     */
    public void setPcs500(BigDecimal pcs500) {
        this.pcs500 = pcs500;
    }

    /**
     * @return the pcs1000
     */
    public BigDecimal getPcs1000() {
        return pcs1000;
    }

    /**
     * @param pcs1000 the pcs1000 to set
     */
    public void setPcs1000(BigDecimal pcs1000) {
        this.pcs1000 = pcs1000;
    }

    /**
     * @return the niklanje
     */
    public boolean isNiklanje() {
        return niklanje;
    }

    /**
     * @param niklanje the niklanje to set
     */
    public void setNiklanje(boolean niklanje) {
        this.niklanje = niklanje;
    }
    
    /**
     * @return the isTokarenje
     */
    public boolean isTokarenje() {
        return isTokarenje;
    }

    /**
     * @param isTokarenje the isTokarenje to set
     */
    public void setIsTokarenje(boolean isTokarenje) {
        this.isTokarenje = isTokarenje;
    }
   

      public String getCity() {
        return city;
    }

   
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public int compareTo(Object o) {
        return 1;
        //int compareKW=((OpenOrderDto)o).getKW();
        /* For Ascending order*/
        //return this.getKW()-compareKW;

        /* For Descending order do like this */
        //return compareKW-this.getKW();
    }
    
    
    public BigDecimal GetPrice() {
       
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
        pricePerPartList.add(getPcs1());
        pricePerPartList.add(getPcs2());
        pricePerPartList.add(getPcs3());
        pricePerPartList.add(getPcs4());
        pricePerPartList.add(getPcs5());
        pricePerPartList.add(getPcs6());
        pricePerPartList.add(getPcs10());
        pricePerPartList.add(getPcs15());
        pricePerPartList.add(getPcs20());
        pricePerPartList.add(getPcs30());
        pricePerPartList.add(getPcs40());
        pricePerPartList.add(getPcs50());
        pricePerPartList.add(getPcs100());
        pricePerPartList.add(getPcs200());
        pricePerPartList.add(getPcs500());
        pricePerPartList.add(getPcs1000());

        BigDecimal pricePerPart = new BigDecimal(-1);
        
        //cijena po komadu se odreduje na temelju toga koliko toga je naruceno
        Integer partsCount = getQuantityOrdered();
        
        for (int i = 15; i >= 0; i--) {
            if (partsCount >= partsList.get(i)) {
                if (pricePerPartList.get(i) != null) {
                    pricePerPart = pricePerPartList.get(i);
                    break;
                } else {
                    continue;
                }
            }
        }
        
        //broj komada preostalih za isporuku
        Integer partsLeftToDeliver = getQuantityOrdered() - getQuantityDelivered();
        
        return pricePerPart.multiply(new BigDecimal(partsLeftToDeliver)).setScale(2, RoundingMode.HALF_UP);
    }
         
}
