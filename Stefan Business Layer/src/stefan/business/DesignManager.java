/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import stefan.business.objects.Design;



/**
 *
 * @author Matija
 */
public class DesignManager {
    private EntityManager entityManager;
    
    public DesignManager()
    {
        entityManager=QueryManager.getEntityManagerInstance();
    }
    
    public void SaveDesign(Design design) throws Exception
    {      
        try {
            stefan.data.Design newDesign = new stefan.data.Design();
            newDesign.setDate(design.getDate());
            newDesign.setClassMark(design.getClassMark());
            newDesign.setDesignNumber(design.getDesignNumber());
            newDesign.setDesignIdentity(design.getDesignIdentity());
            newDesign.setName(design.getName());
            newDesign.setRevision(design.getRevision());
            MaterialManager m=new MaterialManager();
            if (design.getMaterialId()!=null)
            {
                newDesign.setMaterialId(m.GetMaterialByIdDB(design.getMaterialId()));
                newDesign.setNiklanje(m.GetMaterialById(design.getMaterialId()).getMaterialDetails().getNiklanje());
            }
            else
            {
                newDesign.setNiklanje(design.isNiklanje());
            }
            newDesign.setNiklanje(design.isNiklanje());//uvijek postavi niklanje
            newDesign.setIsTokarenje(design.getIsTokarenje()); 
            newDesign.setIsNlx(design.getIsNlx());
            newDesign.setIsActive(true);
            newDesign.setDateModified(new Date());
            //1 kom
            newDesign.setK(design.getPcs1());
            //2 kom
            newDesign.setK1(design.getPcs2());
            //3 kom
            newDesign.setK2(design.getPcs3());
            //4 kom
            newDesign.setK3(design.getPcs4());
            //5 kom
            newDesign.setK4(design.getPcs5());
            //6 kom
            newDesign.setK5(design.getPcs6());
            //10 kom
            newDesign.setK6(design.getPcs10());
            //15 kom
            newDesign.setK7(design.getPcs15());
            //20 kom
            newDesign.setK8(design.getPcs20());
            //30 kom
            newDesign.setK9(design.getPcs30());
            //40 kom
            newDesign.setK10(design.getPcs40());
            //50 kom
            newDesign.setK11(design.getPcs50());
            //100 kom
            newDesign.setK12(design.getPcs100());
            //200
            newDesign.setK13(design.getPcs200());
            //500 kom
            newDesign.setK14(design.getPcs500());
            //1k kom
            newDesign.setK15(design.getPcs1000());
            
            newDesign.setK16(design.getPcs8());
            
            newDesign.setK17(design.getPcs16());
            
            newDesign.setK18(design.getPcs32());
            
            newDesign.setK19(design.getPcs64());
            
            newDesign.setK20(design.getPcs128());
            
            newDesign.setCode(design.getCode());
            
            newDesign.setCalcIsFerting(design.getCalcIsFerting());
            newDesign.setCalcMinPoKom(design.getCalcMinPoKom());
            newDesign.setCalcStezanjeTok(design.getCalcStezanjeTok());
            newDesign.setCalcStezanjeGlod(design.getCalcStezanjeGlod());
            newDesign.setCalcSatnica(design.getCalcSatnica());
            newDesign.setCalcEurPoKom(design.getCalcEurPoKom());
            
            newDesign.setPosao1k(design.getPosao1k());
            newDesign.setPosao2k(design.getPosao2k());
            newDesign.setPosao3k(design.getPosao3k());
            newDesign.setPosao4k(design.getPosao4k());
            newDesign.setPosao5k(design.getPosao5k());
            newDesign.setPosao8k(design.getPosao8k());
            newDesign.setPosao10k(design.getPosao10k());
            newDesign.setPosao15k(design.getPosao15k());
            newDesign.setPosao16k(design.getPosao16k());
            newDesign.setPosao20k(design.getPosao20k());
            newDesign.setPosao32k(design.getPosao32k());
            newDesign.setPosao50k(design.getPosao50k());
            newDesign.setPosao64k(design.getPosao64k());
            newDesign.setPosao100k(design.getPosao100k());
            newDesign.setPosao128k(design.getPosao128k());
            newDesign.setPosao200k(design.getPosao200k());
            newDesign.setPosao500k(design.getPosao500k());
            
            
      
            entityManager.getTransaction().begin();
            
            entityManager.persist(newDesign);
            
            entityManager.getTransaction().commit();
            
           
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
       
    }
    
    public void ChangeDesign(Design design) throws Exception
    {
        SaveDesign(design);
        stefan.data.Design newDesign=GetDesignsByDBId(design.getIdDesign());
       
        entityManager.getTransaction().begin();
      
        newDesign.setDateModified(new Date());
        newDesign.setIsActive(false);
      
        entityManager.getTransaction().commit(); 
       
    }
    
    public void UpdateAllPrices(BigDecimal percent) throws Exception
    {
        List<Design> all=GetAll();
        
        entityManager.getTransaction().begin();
        for (Design design : all) 
        {
            design.IncreasePrice(percent);            
            
            stefan.data.Design newDesign = new stefan.data.Design();
            newDesign.setDate(design.getDate());
            newDesign.setClassMark(design.getClassMark());
            newDesign.setDesignNumber(design.getDesignNumber());
            newDesign.setDesignIdentity(design.getDesignIdentity());
            newDesign.setName(design.getName());
            newDesign.setRevision(design.getRevision());
            newDesign.setIsTokarenje(design.getIsTokarenje());
            newDesign.setIsNlx(design.getIsNlx());
            MaterialManager m=new MaterialManager();
            if (design.getMaterialId()!=null)
            {
                newDesign.setMaterialId(m.GetMaterialByIdDB(design.getMaterialId()));
                newDesign.setNiklanje(m.GetMaterialById(design.getMaterialId()).getMaterialDetails().getNiklanje());
            }
            else
            {
                newDesign.setNiklanje(design.isNiklanje());
            }
            newDesign.setIsActive(true);
            newDesign.setDateModified(new Date());
            //1 kom
            newDesign.setK(design.getPcs1());
            //2 kom
            newDesign.setK1(design.getPcs2());
            //3 kom
            newDesign.setK2(design.getPcs3());
            //4 kom
            newDesign.setK3(design.getPcs4());
            //5 kom
            newDesign.setK4(design.getPcs5());
            //6 kom
            newDesign.setK5(design.getPcs6());
            //10 kom
            newDesign.setK6(design.getPcs10());
            //15 kom
            newDesign.setK7(design.getPcs15());
            //20 kom
            newDesign.setK8(design.getPcs20());
            //30 kom
            newDesign.setK9(design.getPcs30());
            //40 kom
            newDesign.setK10(design.getPcs40());
            //50 kom
            newDesign.setK11(design.getPcs50());
            //100 kom
            newDesign.setK12(design.getPcs100());
            //200
            newDesign.setK13(design.getPcs200());
            //500 kom
            newDesign.setK14(design.getPcs500());
            //1k kom
            newDesign.setK15(design.getPcs1000());     
            
            newDesign.setK16(design.getPcs8());
            
            newDesign.setK17(design.getPcs16());
            
            newDesign.setK18(design.getPcs32());
            
            newDesign.setK19(design.getPcs64());
            
            newDesign.setK20(design.getPcs128());
      
            
            entityManager.persist(newDesign);
            
            
            //stefan.data.Design oldDesign=GetDesignsByDBId(design.getIdDesign());
            Query q = entityManager.createNamedQuery("Design.findByIdDesign");
            q.setParameter("idDesign", design.getIdDesign());
            List<stefan.data.Design> designs = q.getResultList();
            stefan.data.Design oldDesign = designs.get(0); 
            
            oldDesign.setDateModified(new Date());
            oldDesign.setIsActive(false);
            
           
        }
         entityManager.getTransaction().commit();
        
        
    }
    
    public List<Design> GetAll()
    {
       List<stefan.data.Design> designs= entityManager.createNamedQuery("Design.findAll").getResultList();
       return mapData(designs);
    }
    
    public List<Design> GetTop100()
    {
       List<stefan.data.Design> designs= entityManager.createNamedQuery("Design.findAll").setMaxResults(100).getResultList();
       return mapData(designs);
    }
    
    public List<Design> GetDesignsById(String designNumber,String designIdentity, String classMark)
    {            
       Query q = entityManager.createNamedQuery("Design.findByFilters");
       q.setParameter("designNumber", designNumber + "%");
       q.setParameter("classMark", classMark + "%");
       q.setParameter("designIdentity", designIdentity + "%");       
       List<stefan.data.Design> designs = q.setMaxResults(100).getResultList();
       return mapData(designs);      
    }
    
    public List<Design> GetDesignsByNumber(String designNumber)
    {            
       Query q = entityManager.createNamedQuery("Design.findByNumber");
       q.setParameter("designNumber", designNumber + "%");        
       List<stefan.data.Design> designs = q.setMaxResults(100).getResultList();
       return mapData(designs);      
    }
    
    public List<Design> GetDesignsByNumberAndCode(String designNumber, String code)
    {    
       Query q;
       if("FOPAC".equals(code)){
           q = entityManager.createNamedQuery("Design.findByNumberFopac");
       }else{
           q = entityManager.createNamedQuery("Design.findByNumberWH");
       }
       
       q.setParameter("designNumber", designNumber + "%");        
       List<stefan.data.Design> designs = q.setMaxResults(100).getResultList();
       return mapData(designs);      
    }
    
    public stefan.data.Design GetDesignsByDBId(int id)
    {            
       Query q = entityManager.createNamedQuery("Design.findByIdDesign");
       q.setParameter("idDesign", id);          
       List<stefan.data.Design> designs = q.getResultList();
       return designs.get(0);      
    }
    
    
    private List<Design> mapData(List<stefan.data.Design> designs)
    {
        List<Design> results=new ArrayList<Design>();        
        for(stefan.data.Design d : designs)
        {           
            results.add(mapData(d));
        }
        return results;    
    }
    
    public Design mapData(stefan.data.Design design)
    {
        Design result=new Design(design.getIdDesign());        
        result.setClassMark(design.getClassMark());
        result.setDate(design.getDate());
        result.setDesignIdentity(design.getDesignIdentity());
        result.setDesignNumber(design.getDesignNumber());
        result.setRevision(design.getRevision());
        result.setNiklanje(design.getNiklanje());
        result.setIsTokarenje(design.getIsTokarenje());
        result.setIsNlx(design.getIsNlx());
        result.setName(design.getName());        
        result.setPcs1(design.getK());
        result.setPcs2(design.getK1());
        result.setPcs3(design.getK2());
        result.setPcs4(design.getK3());
        result.setPcs5(design.getK4());
        result.setPcs6(design.getK5());
        result.setPcs10(design.getK6());
        result.setPcs15(design.getK7());
        result.setPcs20(design.getK8());
        result.setPcs30(design.getK9());
        result.setPcs40(design.getK10());
        result.setPcs50(design.getK11());
        result.setPcs100(design.getK12());
        result.setPcs200(design.getK13());
        result.setPcs500(design.getK14());
        result.setPcs1000(design.getK15());
        result.setPcs8(design.getK16());
        result.setPcs16(design.getK17());
        result.setPcs32(design.getK18());
        result.setPcs64(design.getK19());
        result.setPcs128(design.getK20());
        result.setCode(design.getCode());
        
        result.setCalcIsFerting(design.getCalcIsFerting());
        result.setCalcEurPoKom(design.getCalcEurPoKom());
        result.setCalcMinPoKom(design.getCalcMinPoKom());
        result.setCalcSatnica(design.getCalcSatnica());
        result.setCalcStezanjeGlod(design.getCalcStezanjeGlod());
        result.setCalcStezanjeTok(design.getCalcStezanjeTok());
        
        result.setPosao1k(design.getPosao1k());
        result.setPosao2k(design.getPosao2k());
        result.setPosao3k(design.getPosao3k());
        result.setPosao4k(design.getPosao4k());
        result.setPosao5k(design.getPosao5k());
        result.setPosao8k(design.getPosao8k());
        result.setPosao10k(design.getPosao10k());
        result.setPosao15k(design.getPosao15k());
        result.setPosao16k(design.getPosao16k());
        result.setPosao20k(design.getPosao20k());
        result.setPosao32k(design.getPosao32k());
        result.setPosao50k(design.getPosao50k());
        result.setPosao64k(design.getPosao64k());
        result.setPosao100k(design.getPosao100k());
        result.setPosao128k(design.getPosao128k());
        result.setPosao200k(design.getPosao200k());
        result.setPosao500k(design.getPosao500k());
        
        
        return result;            
    }

    public void DeleteDesign(Design deleted) {
        stefan.data.Design newDesign=GetDesignsByDBId(deleted.getIdDesign());
       
        entityManager.getTransaction().begin();
      
        newDesign.setDateModified(new Date());
        newDesign.setIsActive(false);
      
        entityManager.getTransaction().commit(); 
       
    }
}
