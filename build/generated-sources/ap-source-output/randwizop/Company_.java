package randwizop;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import randwizop.Admin;
import randwizop.Clients;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-26T11:33:58")
@StaticMetamodel(Company.class)
public class Company_ { 

    public static volatile SingularAttribute<Company, String> country;
    public static volatile SingularAttribute<Company, Integer> companyid;
    public static volatile SingularAttribute<Company, String> city;
    public static volatile SingularAttribute<Company, String> phone;
    public static volatile CollectionAttribute<Company, Admin> adminCollection;
    public static volatile SingularAttribute<Company, String> street;
    public static volatile SingularAttribute<Company, String> name;
    public static volatile SingularAttribute<Company, String> description;
    public static volatile SingularAttribute<Company, String> suburb;
    public static volatile CollectionAttribute<Company, Clients> clientsCollection;

}