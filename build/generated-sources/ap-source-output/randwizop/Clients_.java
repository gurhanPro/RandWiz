package randwizop;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import randwizop.Company;
import randwizop.Deposit;
import randwizop.Transactions;
import randwizop.Transfer;
import randwizop.Withdraw;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-26T11:33:58")
@StaticMetamodel(Clients.class)
public class Clients_ { 

    public static volatile SingularAttribute<Clients, String> country;
    public static volatile SingularAttribute<Clients, Integer> clientid;
    public static volatile SingularAttribute<Clients, String> city;
    public static volatile CollectionAttribute<Clients, Transactions> transactionsCollection;
    public static volatile SingularAttribute<Clients, String> datecreated;
    public static volatile SingularAttribute<Clients, Double> clientbalance;
    public static volatile CollectionAttribute<Clients, Deposit> depositCollection;
    public static volatile CollectionAttribute<Clients, Withdraw> withdrawCollection;
    public static volatile SingularAttribute<Clients, Company> companyid;
    public static volatile CollectionAttribute<Clients, Transfer> transferCollection1;
    public static volatile SingularAttribute<Clients, String> phone;
    public static volatile SingularAttribute<Clients, String> street;
    public static volatile SingularAttribute<Clients, String> suburb;
    public static volatile SingularAttribute<Clients, String> fullname;
    public static volatile CollectionAttribute<Clients, Transfer> transferCollection;

}