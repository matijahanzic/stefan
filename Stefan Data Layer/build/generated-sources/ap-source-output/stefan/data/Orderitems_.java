package stefan.data;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import stefan.data.Billitems;
import stefan.data.Design;
import stefan.data.Orders;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2014-02-22T08:58:27")
@StaticMetamodel(Orderitems.class)
public class Orderitems_ { 

    public static volatile SingularAttribute<Orderitems, String> position;
    public static volatile ListAttribute<Orderitems, Billitems> billitemsList;
    public static volatile SingularAttribute<Orderitems, Integer> idOrderItems;
    public static volatile SingularAttribute<Orderitems, Orders> idOrder;
    public static volatile SingularAttribute<Orderitems, Design> idDesign;
    public static volatile SingularAttribute<Orderitems, Integer> quantityDelivered;
    public static volatile SingularAttribute<Orderitems, Integer> quantityOrdered;

}