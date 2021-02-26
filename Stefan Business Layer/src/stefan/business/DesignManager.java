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
