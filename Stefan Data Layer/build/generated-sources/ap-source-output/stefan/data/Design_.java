package stefan.data;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import stefan.data.Material;
import stefan.data.Orderitems;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2014-02-22T08:58:27")
@StaticMetamodel(Design.class)
public class Design_ { 

    public static volatile SingularAttribute<Design, BigDecimal> k11;
    public static volatile SingularAttribute<Design, BigDecimal> k10;
    public static volatile SingularAttribute<Design, Date> dateModified;
    public static volatile SingularAttribute<Design, BigDecimal> k15;
    public static volatile SingularAttribute<Design, BigDecimal> k14;
    public static volatile SingularAttribute<Design, Material> materialId;
    public static volatile SingularAttribute<Design, BigDecimal> k13;
    public static volatile SingularAttribute<Design, BigDecimal> k12;
    public static volatile SingularAttribute<Design, Date> date;
    public static volatile SingularAttribute<Design, Integer> idDesign;
    public static volatile SingularAttribute<Design, String> name;
    public static volatile SingularAttribute<Design, Boolean> niklanje;
    public static volatile SingularAttribute<Design, BigDecimal> k3;
    public static volatile SingularAttribute<Design, BigDecimal> k4;
    public static volatile SingularAttribute<Design, BigDecimal> k5;
    public static volatile SingularAttribute<Design, BigDecimal> k6;
    public static volatile SingularAttribute<Design, BigDecimal> k7;
    public static volatile SingularAttribute<Design, BigDecimal> k8;
    public static volatile SingularAttribute<Design, BigDecimal> k9;
    public static volatile ListAttribute<Design, Orderitems> orderitemsList;
    public static volatile SingularAttribute<Design, String> classMark;
    public static volatile SingularAttribute<Design, BigDecimal> k;
    public static volatile SingularAttribute<Design, BigDecimal> k1;
    public static volatile SingularAttribute<Design, BigDecimal> k2;
    public static volatile SingularAttribute<Design, Boolean> isActive;
    public static volatile SingularAttribute<Design, String> designNumber;
    public static volatile SingularAttribute<Design, String> designIdentity;

}