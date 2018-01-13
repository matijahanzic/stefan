package stefan.data;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import stefan.data.Material;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2014-02-22T08:58:27")
@StaticMetamodel(Materialdetails.class)
public class Materialdetails_ { 

    public static volatile SingularAttribute<Materialdetails, String> materialName;
    public static volatile SingularAttribute<Materialdetails, BigDecimal> density;
    public static volatile ListAttribute<Materialdetails, Material> materialList;
    public static volatile SingularAttribute<Materialdetails, Boolean> niklanje;
    public static volatile SingularAttribute<Materialdetails, Integer> idMaterialDetails;

}