/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randwizop;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author maxamed
 */
public class AdminModifier {
  static  EntityManagerFactory emf = Persistence.createEntityManagerFactory("RandWizOPPU");
    static AdminJpaController ajc = new AdminJpaController(emf);
  static   ClientsJpaController cjc = new ClientsJpaController(emf);
    
    public static void main(String arg[]) throws Exception
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RandWizOPPU"); 
      AdminModifier ad = new AdminModifier();
      ad.admins(ajc.findAdmin(1));
        Clients c = new Clients();
        
        
       AdminJpaController ajc = new AdminJpaController(emf);
       
      
        System.out.println(ajc.findAdmin(1).getAdminbalance());
        System.out.println(cjc.getClientsCount());
    }
    public void admins(Admin a) throws Exception
    {
    a.setAdminbalance(0.0);
    a.setTotaldeposited(0.0);
    a.setTotalwithdrawn(0.0);
    a.setMasterpassword("randwiz");
    a.setMasterusername("wizrand");
    ajc.edit(a);
    
    
    }
    public  static void clear() throws Exception
    {  Clients c = new Clients();
        Admin a = new Admin();
        AdminModifier am = new AdminModifier();
        am.admins(a);
    
        
    }
    
}
