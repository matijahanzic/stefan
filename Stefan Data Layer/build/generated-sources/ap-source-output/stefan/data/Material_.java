package stefan.data;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import stefan.data.Calculationform;
import stefan.data.Design;
import stefan.data.Materialdetails;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2014-02-22T08:58:27")
@StaticMetamodel(Material.class)
public class Material_ { 

    public static volatile SingularAttribute<Material, BigDecimal> euroPerKg;
    public static volatile ListAttribute<Material, Design> designList;
    public static volatile SingularAttribute<Material, String> name;
    public static volatile SingularAttribute<Material, Calculationform> idCalculationForm;
    public static volatile SingularAttribute<Material, Integer> idShape;
    public static volatile SingularAttribute<Material, Materialdetails> idMaterialDetails;

}