package randwizop;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import randwizop.Clients;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-26T11:33:58")
@StaticMetamodel(Deposit.class)
public class Deposit_ { 

    public static volatile SingularAttribute<Deposit, Integer> depositid;
    public static volatile SingularAttribute<Deposit, String> comments;
    public static volatile SingularAttribute<Deposit, Clients> clientid;
    public static volatile SingularAttribute<Deposit, String> timestamps;
    public static volatile SingularAttribute<Deposit, Double> depositamount;

}