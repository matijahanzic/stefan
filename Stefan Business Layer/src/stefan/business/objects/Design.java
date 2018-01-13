/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.business.objects;

import java.util.Date;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Matija
 */
public class Design {    
    
    private Integer idDesign;  
    private String designNumber;    
    private String designIdentity;   
    private String classMark;   
    private String name;   
    private Date date;     
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
    private Integer materialId;
    private boolean niklanje;

    public Design()
    {        
    }
    
    public void IncreasePrice(BigDecimal percent)
    {
        percent=percent.divide(new BigDecimal("100"),4, RoundingMode.HALF_UP);
        percent=percent.add(BigDecimal.ONE);
        
        if (pcs1!=null)
        {
            pcs1=pcs1.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs2!=null)
        {
            pcs2=pcs2.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs3!=null)
        {
            pcs3=pcs3.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs4!=null)
        {
            pcs4=pcs4.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs5!=null)
        {
            pcs5=pcs5.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs6!=null)
        {
            pcs6=pcs6.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs10!=null)
        {
            pcs10=pcs10.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs15!=null)
        {
            pcs15=pcs15.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs20!=null)
        {
            pcs20=pcs20.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs30!=null)
        {
            pcs30=pcs30.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs40!=null)
        {
            pcs40=pcs40.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs50!=null)
        {
            pcs50=pcs50.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs100!=null)
        {
            pcs100=pcs100.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs200!=null)
        {
            pcs200=pcs200.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs500!=null)
        {
            pcs500=pcs500.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs1000!=null)
        {
            pcs1000=pcs1000.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
    }
    public Design(Integer id)
    {
        idDesign=id;        
    }
    
    
    /**
     * @return the idDesign
     */
    public Integer getIdDesign() {
        return idDesign;
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
     * @return the classMark
     */
    public String getClassMark() {
        return classMark;
    }

    /**
     * @param classMark the classMark to set
     */
    public void setClassMark(String classMark) {
        this.classMark = classMark;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the materialId
     */
    public Integer getMaterialId() {
        return materialId;
    }

    /**
     * @param materialId the materialId to set
     */
    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
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
   
    
}
