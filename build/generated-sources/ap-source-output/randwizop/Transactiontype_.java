package randwizop;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import randwizop.Transactions;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-26T11:33:58")
@StaticMetamodel(Transactiontype.class)
public class Transactiontype_ { 

    public static volatile SingularAttribute<Transactiontype, Integer> transactiontypeid;
    public static volatile SingularAttribute<Transactiontype, String> transctiontypename;
    public static volatile CollectionAttribute<Transactiontype, Transactions> transactionsCollection;

}