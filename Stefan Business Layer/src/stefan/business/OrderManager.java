/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stefan.business;

import stefan.business.objects.BillItem;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import stefan.business.objects.Order;
import stefan.business.objects.OrderItem;
import stefan.data.Orderitems;
import stefan.data.Orders;
import java.util.Calendar;

/**
 *
 * @author Matija
 */
public class OrderManager {

    private EntityManager entityManager;

    public OrderManager() {
        entityManager = QueryManager.getEntityManagerInstance();
    }

    public List<stefan.business.objects.Order> getOrders() {
        Query q = entityManager.createNamedQuery("Orders.findAll");
        List<stefan.data.Orders> orders = q.getResultList();
        return mapData(orders);

    }   
    

    public List<BillItem> getUndeliveredBillItemsByDesignNumber(String designNumber) {
        List<BillItem> items = new ArrayList<BillItem>();
        Query q = entityManager.createNamedQuery("Orderitems.findUndelivered");
        List<stefan.data.Orderitems> orders = q.getResultList();
        AddBillItems(items,orders,designNumber);
        return items;
    }
    
    public List<BillItem> getAllBillItemsByDesignNumber(String designNumber){
        List<BillItem> items = new ArrayList<BillItem>();
        Query q = entityManager.createNamedQuery("Orderitems.findAll");
        List<stefan.data.Orderitems> orders = q.getResultList();
        AddBillItems(items, orders, designNumber);
        return items;
    }

    public List<BillItem> getUndeliveredBillItems() {
        List<BillItem> items = new ArrayList<BillItem>();
        Query q = entityManager.createNamedQuery("Orderitems.findUndelivered");
        List<stefan.data.Orderitems> orders = q.getResultList();
        
        
       
        for (Orderitems orderitem : orders) {
            items.add(populateBillItem(orderitem));
        }

        return items;
    }

    public List<BillItem> getAllBillItems() {
        List<BillItem> items = new ArrayList<BillItem>();
        Query q = entityManager.createNamedQuery("Orderitems.findAll");
        List<stefan.data.Orderitems> orders = q.getResultList();

        for (Orderitems orderitem : orders) {
            items.add(populateBillItem(orderitem));
        }

        return items;
    }

    static BillItem populateBillItem(Orderitems orderitem) {

        BillItem item = new BillItem();
        item.setDesignClass(orderitem.getIdDesign().getClassMark());
        item.setDesignId(orderitem.getIdDesign().getIdDesign());
        item.setDesignIdentity(orderitem.getIdDesign().getDesignIdentity());
        item.setDesignName(orderitem.getIdDesign().getName());
        item.setDesignNumber(orderitem.getIdDesign().getDesignNumber());
        item.setOrderId(orderitem.getIdOrder().getIdOrder());
        item.setOrderItemId(orderitem.getIdOrderItems());
        item.setOrderDate(orderitem.getIdOrder().getDate());
        item.setOrderNumber(orderitem.getIdOrder().getOrderNumber());
        item.setPosition(orderitem.getPosition());        
        item.setQuantityDelivered(orderitem.getQuantityDelivered());
        item.setQuantityOrdered(orderitem.getQuantityOrdered());
        item.setNiklanje(orderitem.getIdDesign().getNiklanje());
        return item;

    }

    private BillItem filterByDesignName(Orderitems orderitem, String designNumber) {
        BillItem item = new BillItem();
        String designNum = orderitem.getIdDesign().getDesignNumber();
        if(designNum.startsWith(designNumber)){
            item = populateBillItem(orderitem);
        }else{
            item=null;
        }
        return item;
    }

    public void UpdateOrderItem(BillItem item) {
        try {
            entityManager.getTransaction().begin();
            Query q = entityManager.createNamedQuery("Orderitems.findByOrderItemId");
            q.setParameter("idOrderItems", item.getOrderItemId());
            stefan.data.Orderitems orderItem = (stefan.data.Orderitems) q.getResultList().get(0);
            orderItem.setQuantityDelivered(orderItem.getQuantityDelivered() + item.getParts());
            entityManager.getTransaction().commit();
        } catch (Exception e) {
        }


    }

    public stefan.data.Orderitems getOrderItemById(Integer Id)
    {
       Query q = entityManager.createNamedQuery("Orderitems.findByOrderItemId");
       q.setParameter("idOrderItems", Id);          
       List<stefan.data.Orderitems> items = q.getResultList();
       return items.get(0);   
    }
    
    public void deleteOrder(Integer Id)
    {
        try
        {             
             Orders order=getOrderById(Id);
             entityManager.getTransaction().begin();
             entityManager.remove(order);
             entityManager.getTransaction().commit();
        } 
        catch(Exception e)
        {
        }
    }  
    public void deleteOrderItem(Integer Id)
    {
        try
        {             
             Orderitems orderItem=getOrderItemById(Id);
             Orders order=orderItem.getIdOrder();
             entityManager.getTransaction().begin();
             entityManager.remove(orderItem);             
             entityManager.getTransaction().commit();
             entityManager.refresh(order);
             
        } 
        catch(Exception e)
        {
        }
    }
    public stefan.data.Orders getOrderById(Integer Id)
    {
       Query q = entityManager.createNamedQuery("Orders.findByIdOrder");
       q.setParameter("idOrder", Id);          
       List<stefan.data.Orders> items = q.getResultList();
       return items.get(0);   
    }
    
    public stefan.business.objects.Order GetOrderByOrderNumber(String orderNumber){
        Query q = entityManager.createNamedQuery("Orders.findByOrderNumber");
        q.setParameter("orderNumber", orderNumber);
        stefan.data.Orders order = (stefan.data.Orders) q.getSingleResult();
        return MapSingleResult(order);
    }
    
    public stefan.business.objects.Order GetOrderByOrderNumberFullyMapped(String orderNumber){
        Query q = entityManager.createNamedQuery("Orders.findByOrderNumber");
        q.setParameter("orderNumber", orderNumber);
        stefan.data.Orders order = (stefan.data.Orders) q.getSingleResult();
        return mapOrderData(order);
    }
    
     public void UpdateOrder(Order existingOrder) throws Exception {
        try {
           
            stefan.data.Orders o = entityManager.find(stefan.data.Orders.class,existingOrder.getIdOrder());         
            List<Orderitems> allItems = o.getOrderitemsList();
            DesignManager designManager = new DesignManager();
          
            for(stefan.business.objects.OrderItem item : existingOrder.getOrderitemsList()){
                stefan.data.Orderitems add = new Orderitems();
                add.setIdOrder(o);
                add.setPosition(item.getPosition());
                add.setQuantityDelivered(item.getQuantityDelivered());
                add.setQuantityOrdered(item.getQuantityOrdered());
                add.setIdDesign(designManager.GetDesignsByDBId(item.getDesignId()));         
                allItems.add(add);
            }
            
            o.setOrderitemsList(allItems);
             entityManager.getTransaction().begin();
            entityManager.merge(o);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
    public boolean SaveOrder(stefan.business.objects.Order order) {
        try {
            stefan.data.Orders newOrder = new stefan.data.Orders();
            newOrder.setDate(order.getDate());
            newOrder.setIsDelivered(order.isIsDelivered());
            newOrder.setOrderNumber(order.getOrderNumber());
            List<stefan.data.Orderitems> orderItems = new ArrayList<Orderitems>();
            DesignManager designManager = new DesignManager();
            for (stefan.business.objects.OrderItem oi : order.getOrderitemsList()) {
                stefan.data.Orderitems newOrderItem = new Orderitems();
                newOrderItem.setIdOrder(newOrder);
                newOrderItem.setPosition(oi.getPosition());
                newOrderItem.setQuantityDelivered(oi.getQuantityDelivered());
                newOrderItem.setQuantityOrdered(oi.getQuantityOrdered());
                newOrderItem.setIdDesign(designManager.GetDesignsByDBId(oi.getDesignId()));

                orderItems.add(newOrderItem);
            }
            newOrder.setOrderitemsList(orderItems);

            entityManager.getTransaction().begin();
            entityManager.persist(newOrder);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();//mislim da ovo ne dela
            return false;
        }
    }
    
   

    private List<stefan.business.objects.Order> mapData(List<stefan.data.Orders> orders) {
        List<stefan.business.objects.Order> results = new ArrayList<stefan.business.objects.Order>();
        for (stefan.data.Orders order : orders) {
            results.add(mapOrderData(order));
        }
        return results;
    }

    stefan.business.objects.Order mapOrderData(stefan.data.Orders order) {
        Order o = new Order();
        o.setDate(order.getDate());
        o.setIdOrder(order.getIdOrder());
        o.setIsDelivered(order.getIsDelivered());
        o.setOrderNumber(order.getOrderNumber());

        List<OrderItem> oi = new ArrayList<OrderItem>();
        for (stefan.data.Orderitems orderItem : order.getOrderitemsList()) {
            oi.add(mapOrderItemData(orderItem, o));
        }
        o.setOrderitemsList(oi);
        return o;
    }

    stefan.business.objects.OrderItem mapOrderItemData(stefan.data.Orderitems orderItem, Order order) {
        OrderItem item = new OrderItem();
        item.setIdOrderItems(orderItem.getIdOrderItems());
        item.setPosition(orderItem.getPosition());
        item.setQuantityDelivered(orderItem.getQuantityDelivered());
        item.setQuantityOrdered(orderItem.getQuantityOrdered());
        item.setOrder(order);
        DesignManager designManager = new DesignManager();
        item.setDesign(designManager.mapData(orderItem.getIdDesign()));
        return item;
    }

    private void AddBillItems(List<BillItem> items ,List<Orderitems> orders, String designNumber) {
        for (Orderitems o : orders) {
            BillItem i = filterByDesignName(o, designNumber);
            if(i!=null){
                items.add(i);
            }
        }
    }
    
    public void deleteOldOrders()
    {
        Query q = entityManager.createNamedQuery("Orders.findAll");
        List<stefan.data.Orders> orders = q.getResultList();
        List<stefan.data.Orders> toDelete=new ArrayList<Orders>();
        for (Orders order : orders)
        {
            Calendar c=Calendar.getInstance();
            c.setTime(order.getDate());
            c.add(Calendar.MONTH, 6);
            Calendar now=Calendar.getInstance();
            
            if (c.after(now))
            {
                continue;
            }
            
            boolean isDelivered=true;
            for (Orderitems oi : order.getOrderitemsList()) 
            {
                if (oi.getQuantityDelivered()<oi.getQuantityOrdered())
                {
                    isDelivered=false;
                    break;
                }                
            }
                               
            if (isDelivered)
            {                
               toDelete.add(order);
            }            
        }
        for (Orders order : toDelete)
        {
            deleteOrder(order.getIdOrder());            
        }
    }

    private Order MapSingleResult(Orders order) {
        stefan.business.objects.Order newOrder = new stefan.business.objects.Order();
        newOrder.setDate(order.getDate());
        newOrder.setIdOrder(order.getIdOrder());
        newOrder.setIsDelivered(order.getIsDelivered());
        newOrder.setOrderNumber(order.getOrderNumber());
        return newOrder;
    }

 
    

}
