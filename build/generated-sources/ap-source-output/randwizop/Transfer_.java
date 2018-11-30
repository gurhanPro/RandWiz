package randwizop;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import randwizop.Clients;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-26T11:33:58")
@StaticMetamodel(Transfer.class)
public class Transfer_ { 

    public static volatile SingularAttribute<Transfer, String> comments;
    public static volatile SingularAttribute<Transfer, Double> transferamount;
    public static volatile SingularAttribute<Transfer, String> timestamps;
    public static volatile SingularAttribute<Transfer, Integer> transferid;
    public static volatile SingularAttribute<Transfer, Clients> clientidto;
    public static volatile SingularAttribute<Transfer, Clients> clientidfrom;

}