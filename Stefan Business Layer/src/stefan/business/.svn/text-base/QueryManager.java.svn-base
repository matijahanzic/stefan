/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.business;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Matija
 */
public class QueryManager {
    
    private static EntityManager entityManager;
    
    private QueryManager()
    {    
    }
    
    public static EntityManager getEntityManagerInstance()
    {
        if (entityManager==null)
        {
            entityManager=Persistence.createEntityManagerFactory("Stefan_Data_LayerPU").createEntityManager();  
            return entityManager;
        }
        else
        {
            return entityManager;        
        }             
    
    }  
}
