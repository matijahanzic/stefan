package stefan.data;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import stefan.data.Billitems;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2014-02-22T08:58:27")
@StaticMetamodel(Bills.class)
public class Bills_ { 

    public static volatile ListAttribute<Bills, Billitems> billitemsList;
    public static volatile SingularAttribute<Bills, String> billNumber;
    public static volatile SingularAttribute<Bills, Date> date;
    public static volatile SingularAttribute<Bills, Integer> idBill;

}