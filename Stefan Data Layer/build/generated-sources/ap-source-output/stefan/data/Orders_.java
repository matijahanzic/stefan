package stefan.data;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import stefan.data.Orderitems;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2014-02-22T08:58:27")
@StaticMetamodel(Orders.class)
public class Orders_ { 

    public static volatile SingularAttribute<Orders, Integer> idOrder;
    public static volatile SingularAttribute<Orders, String> orderNumber;
    public static volatile ListAttribute<Orders, Orderitems> orderitemsList;
    public static volatile SingularAttribute<Orders, Date> date;
    public static volatile SingularAttribute<Orders, Boolean> isDelivered;

}