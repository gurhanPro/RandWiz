package randwizop;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import randwizop.Admin;
import randwizop.Clients;
import randwizop.Transactiontype;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-26T11:33:58")
@StaticMetamodel(Transactions.class)
public class Transactions_ { 

    public static volatile SingularAttribute<Transactions, Transactiontype> transactiontypeid;
    public static volatile SingularAttribute<Transactions, Clients> clientid;
    public static volatile SingularAttribute<Transactions, String> timestamps;
    public static volatile SingularAttribute<Transactions, Admin> handlerid;
    public static volatile SingularAttribute<Transactions, Integer> transactionid;

}