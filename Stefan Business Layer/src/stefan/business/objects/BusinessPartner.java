/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.business.objects;

/**
 *
 * @author Robert
 */
public class BusinessPartner {
  
    private Integer id;    
    private String name;
    private String displayName;
    private boolean  printInd;
     private String printRow1;
     private String printRow2;
     private String printRow3;
     private String city;

    /**
     * @return the idBill
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param idBill the idBill to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

     public String getName() {
        return name;
    }

   
    public void setName(String name) {
        this.name = name;
    }
    
     public String getDisplayName() {
        return displayName;
    }

   
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
     public String getPrintRow1() {
        return printRow1;
    }

   
    public void setPrintRow1(String printRow1) {
        this.printRow1 = printRow1;
    }
    
     public String getPrintRow2() {
        return printRow2;
    }

   
    public void setPrintRow2(String printRow2) {
        this.printRow2 = printRow2;
    }
    
     public String getPrintRow3() {
        return printRow3;
    }

   
    public void setPrintRow3(String printRow3) {
        this.printRow3 = printRow3;
    }
    
     public String getCity() {
        return city;
    }

   
    public void setCity(String city) {
        this.city = city;
    }

     public boolean getPrintInd() {
        return printInd;
    }

   
    public void setPrintInd(boolean printInd) {
        this.printInd = printInd;
    }

    
    @Override
    public String toString()
    {
        return displayName;
    }
}
