package stefan.data;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import stefan.data.Material;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2014-02-22T08:58:27")
@StaticMetamodel(Calculationform.class)
public class Calculationform_ { 

    public static volatile SingularAttribute<Calculationform, String> calculationformname;
    public static volatile SingularAttribute<Calculationform, Integer> idcalculationform;
    public static volatile ListAttribute<Calculationform, Material> materialList;

}