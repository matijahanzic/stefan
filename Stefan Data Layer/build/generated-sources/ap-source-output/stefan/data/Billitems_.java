package stefan.data;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import stefan.data.Bills;
import stefan.data.Orderitems;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2014-02-22T08:58:27")
@StaticMetamodel(Billitems.class)
public class Billitems_ { 

    public static volatile SingularAttribute<Billitems, BigDecimal> pricePerUnit;
    public static volatile SingularAttribute<Billitems, Integer> idBillItem;
    public static volatile SingularAttribute<Billitems, Integer> parts;
    public static volatile SingularAttribute<Billitems, Orderitems> idOrderItem;
    public static volatile SingularAttribute<Billitems, Boolean> niklanje;
    public static volatile SingularAttribute<Billitems, String> packageNumber;
    public static volatile SingularAttribute<Billitems, Bills> idBill;

}