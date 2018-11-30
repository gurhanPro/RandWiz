/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randwizop;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ziyaad Ahmed Gurhan
 */
public class Banking {
    
    
    public void deposit(Clients c, double d,String comment)
    {
        double oldbalance = c.getClientbalance();
        System.out.println("amnt "+d);
        System.out.println("oldbalance "+c.getClientbalance());
        double newBalance = oldbalance + d;
        c.setClientbalance(newBalance);

        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RandWizOPPU");
        ClientsJpaController cjc = new ClientsJpaController(emf);
        AdminJpaController ajc = new AdminJpaController(emf);
        Admin a = ajc.findAdmin(1);
        try {
            cjc.edit(c);
            a.setAdminbalance(a.getAdminbalance()+d);
            a.setTotaldeposited(a.getTotaldeposited()+d);
            ajc.edit(a);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        Deposit deposit = new Deposit();
        DepositJpaController djc = new DepositJpaController(emf);
        deposit.setClientid(c);
        deposit.setDepositamount(d);
        deposit.setComments(comment);
        java.sql.Timestamp t = new java.sql.Timestamp(System.currentTimeMillis());
        deposit.setTimestamps((t+"").substring(0, 16));
        djc.create(deposit);
        
        
        System.out.println("newbalance "+c.getClientbalance());
        
    
    
    }
    public void withdraw(Clients c, double d,String comment)
    {
        double oldbalance = c.getClientbalance();
         System.out.println("amnt "+d);
        System.out.println("oldbalance "+c.getClientbalance());
        double newBalance = oldbalance - d;
        c.setClientbalance(newBalance);
     
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RandWizOPPU");
        ClientsJpaController cjc = new ClientsJpaController(emf);
        
        AdminJpaController ajc = new AdminJpaController(emf);
        Admin a = ajc.findAdmin(1);
        try {
            cjc.edit(c);
            a.setAdminbalance(a.getAdminbalance()-d);
            a.setTotalwithdrawn(a.getTotalwithdrawn()+d);
            ajc.edit(a);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        Withdraw withdraw = new Withdraw();
        WithdrawJpaController djc = new WithdrawJpaController(emf);
        withdraw.setClientid(c);
        withdraw.setWithdrawamount(d);
        withdraw.setComments(comment);
        java.sql.Timestamp t = new java.sql.Timestamp(System.currentTimeMillis());
        withdraw.setTimestamps((t+"").substring(0, 16));
        djc.create(withdraw);
        
        
         System.out.println("newbalance "+c.getClientbalance());
         System.out.println("admin balance "+a.getAdminbalance());
          System.out.println("totalwithdrawn "+a.getTotalwithdrawn());
        
    
    
    }
    public void transfer(Clients from, Clients to, double d,String comment)
    {
        from.setClientbalance(from.getClientbalance()-d);
        to.setClientbalance(to.getClientbalance()+d);
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("RandWizOPPU");
        ClientsJpaController cjc = new ClientsJpaController(emf);
      
        try {
            cjc.edit(from);
            cjc.edit(to);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
         Transfer transfer = new Transfer();
        TransferJpaController wjc = new TransferJpaController(emf);
        transfer.setClientidfrom(from);
        transfer.setClientidto(to);
        transfer.setTransferamount(d);
        transfer.setComments(comment);
        java.sql.Timestamp t = new java.sql.Timestamp(System.currentTimeMillis());
        transfer.setTimestamps((t+"").substring(0, 16));
        wjc.create(transfer);
    
    }
    
    
}
