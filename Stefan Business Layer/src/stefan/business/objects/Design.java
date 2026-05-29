/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.business.objects;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 *
 * @author Matija
 */
public class Design {

    public Integer[] getPcsQuantities() {
        return new Integer[]
        {
            1,
            2,
            3,
            4,
            5,
            6,
            8,
            10,
            15,
            16,
            20,
            30,
            32,
            40,
            50,
            64,
            100,
            128,
            200,
            500,
            1000
        };
    }

    public List<Integer> getSetPcsQuantities() {
        List<Integer> setQtys = new ArrayList<Integer>();

        for (int qt : getPcsQuantities()) {
            try {
                Field f = Design.class.getDeclaredField(String.format("pcs%d", qt));
                if (f.getType() == BigDecimal.class && f.get(this) != null)
                    setQtys.add(qt);
            } catch (NoSuchFieldException nfe) { /* Ignore */ }
            catch (IllegalAccessException iae) { /* Ignore */ }
        }

        return setQtys;
    }

    public BigDecimal getPriceForPcs(int pcs) {
        List<Integer> lst = Arrays.asList(getPcsQuantities());
        if (!lst.contains(pcs))
            return null;

        try {
            Object val = Design.class.getDeclaredField(String.format("pcs%d", pcs)).get(this);
            if (!(val instanceof BigDecimal))
                return null;

            return (BigDecimal)val;
        } catch (NoSuchFieldException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }
    }

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
    private BigDecimal pcs8;
    private BigDecimal pcs10;
    private BigDecimal pcs15;
    private BigDecimal pcs16;
    private BigDecimal pcs20;
    private BigDecimal pcs30;
    private BigDecimal pcs32;
    private BigDecimal pcs40;
    private BigDecimal pcs50;
    private BigDecimal pcs64;
    private BigDecimal pcs100;
    private BigDecimal pcs128;
    private BigDecimal pcs200;
    private BigDecimal pcs500;
    private BigDecimal pcs1000;
    private Integer materialId;
    private boolean niklanje;
    private String revision;
    private boolean isTokarenje;
    private boolean isNlx;
    private String code;
    private boolean calcIsFerting;
    private BigDecimal calcMinPoKom;
    private BigDecimal calcStezanjeTok;
    private BigDecimal calcStezanjeGlod;
    private BigDecimal calcSatnica;
    private BigDecimal calcEurPoKom;
    private BigDecimal posao1k;
    private BigDecimal posao2k;
    private BigDecimal posao3k;
    private BigDecimal posao4k;
    private BigDecimal posao5k;
    private BigDecimal posao8k;
    private BigDecimal posao10k;
    private BigDecimal posao15k;
    private BigDecimal posao16k;
    private BigDecimal posao20k;
    private BigDecimal posao32k;
    private BigDecimal posao50k;
    private BigDecimal posao64k;
    private BigDecimal posao100k;
    private BigDecimal posao128k;
    private BigDecimal posao200k;
    private BigDecimal posao500k;

    public Design() {
    }

    public void IncreasePrice(BigDecimal percent) {
        percent = percent.divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP);
        percent = percent.add(BigDecimal.ONE);

        if (pcs1 != null) {
            pcs1 = pcs1.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs2 != null) {
            pcs2 = pcs2.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs3 != null) {
            pcs3 = pcs3.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs4 != null) {
            pcs4 = pcs4.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs5 != null) {
            pcs5 = pcs5.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs6 != null) {
            pcs6 = pcs6.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs8 != null) {
            pcs8 = pcs8.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs10 != null) {
            pcs10 = pcs10.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs15 != null) {
            pcs15 = pcs15.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs16 != null) {
            pcs16 = pcs16.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs20 != null) {
            pcs20 = pcs20.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs30 != null) {
            pcs30 = pcs30.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs32 != null) {
            pcs32 = pcs32.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs40 != null) {
            pcs40 = pcs40.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs50 != null) {
            pcs50 = pcs50.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs64 != null) {
            pcs64 = pcs64.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs100 != null) {
            pcs100 = pcs100.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs128 != null) {
            pcs128 = pcs128.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs200 != null) {
            pcs200 = pcs200.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }

        if (pcs500 != null) {
            pcs500 = pcs500.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
        if (pcs1000 != null) {
            pcs1000 = pcs1000.multiply(percent).setScale(2, RoundingMode.HALF_UP);
        }
    }

    public Design(Integer id) {
        idDesign = id;
    }

    /**
     * @return the idDesign
     */
    public Integer getIdDesign() {
        return idDesign;
    }

    public void setIdDesign(Integer idDesign) {
        this.idDesign = idDesign;
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

    public BigDecimal getPcs8() {
        return pcs8;
    }

    public void setPcs8(BigDecimal pcs8) {
        this.pcs8 = pcs8;
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

    public BigDecimal getPcs16() {
        return pcs16;
    }

    public void setPcs16(BigDecimal pcs16) {
        this.pcs16 = pcs16;
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

    public BigDecimal getPcs32() {
        return pcs32;
    }

    public void setPcs32(BigDecimal pcs32) {
        this.pcs32 = pcs32;
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

    public BigDecimal getPcs64() {
        return pcs64;
    }

    public void setPcs64(BigDecimal pcs64) {
        this.pcs64 = pcs64;
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

    public BigDecimal getPcs128() {
        return pcs128;
    }

    public void setPcs128(BigDecimal pcs128) {
        this.pcs128 = pcs128;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
     * @return the isTokarenje
     */
    public boolean getIsTokarenje() {
        return isTokarenje;
    }

    /**
     * @param isTokarenje the isTokarenje to set
     */
    public void setIsTokarenje(boolean isTokarenje) {
        this.isTokarenje = isTokarenje;
    }

    /**
     * @return the isNlx
     */
    public boolean getIsNlx() {
        return isNlx;
    }

    /**
     * @param isNlx the isNlx to set
     */
    public void setIsNlx(boolean isNlx) {
        this.isNlx = isNlx;
    }

    /**
     * @return the isTokarenjeDisplayName
     */
    public String getIsTokarenjeDisplayName() {
        if (isNlx) {
            return "N";
        } else if (isTokarenje) {
            return "T";
        } else {
            return "G";
        }
    }

    /**
     * @param isTokarenjeDisplayName the isNlx to set
     */
    public void setIsTokarenjeDisplayName(String isTokarenjeDisplayName) {
        if (isTokarenjeDisplayName == "N") {
            isNlx = true;
            isTokarenje = false;
        } else if (isTokarenjeDisplayName == "T") {
            isNlx = false;
            isTokarenje = true;
        } else {
            isNlx = false;
            isTokarenje = false;
        }
    }

    public boolean getCalcIsFerting() {
        return calcIsFerting;
    }

    public void setCalcIsFerting(boolean calcIsFerting) {
        this.calcIsFerting = calcIsFerting;
    }

    public BigDecimal getCalcMinPoKom() {
        return calcMinPoKom;
    }

    public void setCalcMinPoKom(BigDecimal calcMinPoKom) {
        this.calcMinPoKom = calcMinPoKom;
    }

    public BigDecimal getCalcStezanjeTok() {
        return calcStezanjeTok;
    }

    public void setCalcStezanjeTok(BigDecimal calcStezanjeTok) {
        this.calcStezanjeTok = calcStezanjeTok;
    }

    public BigDecimal getCalcStezanjeGlod() {
        return calcStezanjeGlod;
    }

    public void setCalcStezanjeGlod(BigDecimal calcStezanjeGlod) {
        this.calcStezanjeGlod = calcStezanjeGlod;
    }

    public BigDecimal getCalcSatnica() {
        return calcSatnica;
    }

    public void setCalcSatnica(BigDecimal calcSatnica) {
        this.calcSatnica = calcSatnica;
    }

    public BigDecimal getCalcEurPoKom() {
        return calcEurPoKom;
    }

    public void setCalcEurPoKom(BigDecimal calcEurPoKom) {
        this.calcEurPoKom = calcEurPoKom;
    }

    public BigDecimal getPosao1k() {
        return posao1k;
    }

    public void setPosao1k(BigDecimal posao1k) {
        this.posao1k = posao1k;
    }

    public BigDecimal getPosao2k() {
        return posao2k;
    }

    public void setPosao2k(BigDecimal posao2k) {
        this.posao2k = posao2k;
    }

    public BigDecimal getPosao3k() {
        return posao3k;
    }

    public void setPosao3k(BigDecimal posao3k) {
        this.posao3k = posao3k;
    }

    public BigDecimal getPosao4k() {
        return posao4k;
    }

    public void setPosao4k(BigDecimal posao4k) {
        this.posao4k = posao4k;
    }

    public BigDecimal getPosao5k() {
        return posao5k;
    }

    public void setPosao5k(BigDecimal posao5k) {
        this.posao5k = posao5k;
    }

    public BigDecimal getPosao8k() {
        return posao8k;
    }

    public void setPosao8k(BigDecimal posao8k) {
        this.posao8k = posao8k;
    }

    public BigDecimal getPosao10k() {
        return posao10k;
    }

    public void setPosao10k(BigDecimal posao10k) {
        this.posao10k = posao10k;
    }

    public BigDecimal getPosao15k() {
        return posao15k;
    }

    public void setPosao15k(BigDecimal posao15k) {
        this.posao15k = posao15k;
    }

    public BigDecimal getPosao16k() {
        return posao16k;
    }

    public void setPosao16k(BigDecimal posao16k) {
        this.posao16k = posao16k;
    }

    public BigDecimal getPosao20k() {
        return posao20k;
    }

    public void setPosao20k(BigDecimal posao20k) {
        this.posao20k = posao20k;
    }

    public BigDecimal getPosao32k() {
        return posao32k;
    }

    public void setPosao32k(BigDecimal posao32k) {
        this.posao32k = posao32k;
    }

    public BigDecimal getPosao50k() {
        return posao50k;
    }

    public void setPosao50k(BigDecimal posao50k) {
        this.posao50k = posao50k;
    }

    public BigDecimal getPosao64k() {
        return posao64k;
    }

    public void setPosao64k(BigDecimal posao64k) {
        this.posao64k = posao64k;
    }

    public BigDecimal getPosao100k() {
        return posao100k;
    }

    public void setPosao100k(BigDecimal posao100k) {
        this.posao100k = posao100k;
    }

    public BigDecimal getPosao128k() {
        return posao128k;
    }

    public void setPosao128k(BigDecimal posao128k) {
        this.posao128k = posao128k;
    }

    public BigDecimal getPosao200k() {
        return posao200k;
    }

    public void setPosao200k(BigDecimal posao200k) {
        this.posao200k = posao200k;
    }

    public BigDecimal getPosao500k() {
        return posao500k;
    }

    public void setPosao500k(BigDecimal posao500k) {
        this.posao500k = posao500k;
    }
}
