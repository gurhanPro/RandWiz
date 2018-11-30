package randwizop;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import randwizop.Clients;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-26T11:33:58")
@StaticMetamodel(Withdraw.class)
public class Withdraw_ { 

    public static volatile SingularAttribute<Withdraw, String> comments;
    public static volatile SingularAttribute<Withdraw, Clients> clientid;
    public static volatile SingularAttribute<Withdraw, String> timestamps;
    public static volatile SingularAttribute<Withdraw, Integer> withdrawid;
    public static volatile SingularAttribute<Withdraw, Double> withdrawamount;

}