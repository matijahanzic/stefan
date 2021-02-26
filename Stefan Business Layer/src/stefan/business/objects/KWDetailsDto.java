/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.business.objects;

/**
 *
 * @author robert.borosak
 */


public class KWDetailsDto {
    //kw je kw isporuke a kwnapomena niklanje je napomena koja se treba prikazati u excelu ako se nikla
    //tj kwNapomena je ustvari kw + 1 
    public KWDetailsDto(String kw, String kwNapomenaNiklanje){
        this.KW = kw;
        this.KWNapomenaNiklanje = kwNapomenaNiklanje;
    }
    
    private String KW;
    private String KWNapomenaNiklanje;
    
     public String GetKWNapomenaNiklanje(){
        return this.KWNapomenaNiklanje;
    }
     
     public String GetKw(){
         return this.KW;
     }
}
