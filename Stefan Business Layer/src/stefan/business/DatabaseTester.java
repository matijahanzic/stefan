/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.business;
/**
 *
 * @author Robert
 */
public class DatabaseTester { 
    
    private static DatabaseTester instance = null;
    
    protected DatabaseTester(){        
    }
    
    public static DatabaseTester getInstance(){
        if(instance == null){
            instance = new DatabaseTester();
        }
        return instance;
    }
   
    public void TestDatabase() throws Exception {
        try 
        {
            OrderManager manager = new OrderManager();
            manager.deleteOldOrders();            
        } 
        catch (Exception e)
        {
            throw e;
        }
    }      
}
