package randwizop;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import randwizop.Company;
import randwizop.Transactions;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-26T11:33:58")
@StaticMetamodel(Admin.class)
public class Admin_ { 

    public static volatile SingularAttribute<Admin, String> masterpassword;
    public static volatile SingularAttribute<Admin, String> password;
    public static volatile SingularAttribute<Admin, Company> companyid;
    public static volatile SingularAttribute<Admin, Double> totaldeposited;
    public static volatile SingularAttribute<Admin, String> masterusername;
    public static volatile SingularAttribute<Admin, String> passwordhint;
    public static volatile SingularAttribute<Admin, Double> adminbalance;
    public static volatile SingularAttribute<Admin, Integer> adminid;
    public static volatile CollectionAttribute<Admin, Transactions> transactionsCollection;
    public static volatile SingularAttribute<Admin, String> datecreated;
    public static volatile SingularAttribute<Admin, Double> totalwithdrawn;
    public static volatile SingularAttribute<Admin, String> username;

}