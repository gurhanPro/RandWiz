/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randwizop;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import randwizop.exceptions.NonexistentEntityException;

/**
 *
 * @author maxamed
 */
public class ClientsJpaController implements Serializable {

    public ClientsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Clients clients) {
        if (clients.getWithdrawCollection() == null) {
            clients.setWithdrawCollection(new ArrayList<Withdraw>());
        }
        if (clients.getTransferCollection() == null) {
            clients.setTransferCollection(new ArrayList<Transfer>());
        }
        if (clients.getTransferCollection1() == null) {
            clients.setTransferCollection1(new ArrayList<Transfer>());
        }
        if (clients.getDepositCollection() == null) {
            clients.setDepositCollection(new ArrayList<Deposit>());
        }
        if (clients.getTransactionsCollection() == null) {
            clients.setTransactionsCollection(new ArrayList<Transactions>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Company companyid = clients.getCompanyid();
            if (companyid != null) {
                companyid = em.getReference(companyid.getClass(), companyid.getCompanyid());
                clients.setCompanyid(companyid);
            }
            Collection<Withdraw> attachedWithdrawCollection = new ArrayList<Withdraw>();
            for (Withdraw withdrawCollectionWithdrawToAttach : clients.getWithdrawCollection()) {
                withdrawCollectionWithdrawToAttach = em.getReference(withdrawCollectionWithdrawToAttach.getClass(), withdrawCollectionWithdrawToAttach.getWithdrawid());
                attachedWithdrawCollection.add(withdrawCollectionWithdrawToAttach);
            }
            clients.setWithdrawCollection(attachedWithdrawCollection);
            Collection<Transfer> attachedTransferCollection = new ArrayList<Transfer>();
            for (Transfer transferCollectionTransferToAttach : clients.getTransferCollection()) {
                transferCollectionTransferToAttach = em.getReference(transferCollectionTransferToAttach.getClass(), transferCollectionTransferToAttach.getTransferid());
                attachedTransferCollection.add(transferCollectionTransferToAttach);
            }
            clients.setTransferCollection(attachedTransferCollection);
            Collection<Transfer> attachedTransferCollection1 = new ArrayList<Transfer>();
            for (Transfer transferCollection1TransferToAttach : clients.getTransferCollection1()) {
                transferCollection1TransferToAttach = em.getReference(transferCollection1TransferToAttach.getClass(), transferCollection1TransferToAttach.getTransferid());
                attachedTransferCollection1.add(transferCollection1TransferToAttach);
            }
            clients.setTransferCollection1(attachedTransferCollection1);
            Collection<Deposit> attachedDepositCollection = new ArrayList<Deposit>();
            for (Deposit depositCollectionDepositToAttach : clients.getDepositCollection()) {
                depositCollectionDepositToAttach = em.getReference(depositCollectionDepositToAttach.getClass(), depositCollectionDepositToAttach.getDepositid());
                attachedDepositCollection.add(depositCollectionDepositToAttach);
            }
            clients.setDepositCollection(attachedDepositCollection);
            Collection<Transactions> attachedTransactionsCollection = new ArrayList<Transactions>();
            for (Transactions transactionsCollectionTransactionsToAttach : clients.getTransactionsCollection()) {
                transactionsCollectionTransactionsToAttach = em.getReference(transactionsCollectionTransactionsToAttach.getClass(), transactionsCollectionTransactionsToAttach.getTransactionid());
                attachedTransactionsCollection.add(transactionsCollectionTransactionsToAttach);
            }
            clients.setTransactionsCollection(attachedTransactionsCollection);
            em.persist(clients);
            if (companyid != null) {
                companyid.getClientsCollection().add(clients);
                companyid = em.merge(companyid);
            }
            for (Withdraw withdrawCollectionWithdraw : clients.getWithdrawCollection()) {
                Clients oldClientidOfWithdrawCollectionWithdraw = withdrawCollectionWithdraw.getClientid();
                withdrawCollectionWithdraw.setClientid(clients);
                withdrawCollectionWithdraw = em.merge(withdrawCollectionWithdraw);
                if (oldClientidOfWithdrawCollectionWithdraw != null) {
                    oldClientidOfWithdrawCollectionWithdraw.getWithdrawCollection().remove(withdrawCollectionWithdraw);
                    oldClientidOfWithdrawCollectionWithdraw = em.merge(oldClientidOfWithdrawCollectionWithdraw);
                }
            }
            for (Transfer transferCollectionTransfer : clients.getTransferCollection()) {
                Clients oldClientidtoOfTransferCollectionTransfer = transferCollectionTransfer.getClientidto();
                transferCollectionTransfer.setClientidto(clients);
                transferCollectionTransfer = em.merge(transferCollectionTransfer);
                if (oldClientidtoOfTransferCollectionTransfer != null) {
                    oldClientidtoOfTransferCollectionTransfer.getTransferCollection().remove(transferCollectionTransfer);
                    oldClientidtoOfTransferCollectionTransfer = em.merge(oldClientidtoOfTransferCollectionTransfer);
                }
            }
            for (Transfer transferCollection1Transfer : clients.getTransferCollection1()) {
                Clients oldClientidfromOfTransferCollection1Transfer = transferCollection1Transfer.getClientidfrom();
                transferCollection1Transfer.setClientidfrom(clients);
                transferCollection1Transfer = em.merge(transferCollection1Transfer);
                if (oldClientidfromOfTransferCollection1Transfer != null) {
                    oldClientidfromOfTransferCollection1Transfer.getTransferCollection1().remove(transferCollection1Transfer);
                    oldClientidfromOfTransferCollection1Transfer = em.merge(oldClientidfromOfTransferCollection1Transfer);
                }
            }
            for (Deposit depositCollectionDeposit : clients.getDepositCollection()) {
                Clients oldClientidOfDepositCollectionDeposit = depositCollectionDeposit.getClientid();
                depositCollectionDeposit.setClientid(clients);
                depositCollectionDeposit = em.merge(depositCollectionDeposit);
                if (oldClientidOfDepositCollectionDeposit != null) {
                    oldClientidOfDepositCollectionDeposit.getDepositCollection().remove(depositCollectionDeposit);
                    oldClientidOfDepositCollectionDeposit = em.merge(oldClientidOfDepositCollectionDeposit);
                }
            }
            for (Transactions transactionsCollectionTransactions : clients.getTransactionsCollection()) {
                Clients oldClientidOfTransactionsCollectionTransactions = transactionsCollectionTransactions.getClientid();
                transactionsCollectionTransactions.setClientid(clients);
                transactionsCollectionTransactions = em.merge(transactionsCollectionTransactions);
                if (oldClientidOfTransactionsCollectionTransactions != null) {
                    oldClientidOfTransactionsCollectionTransactions.getTransactionsCollection().remove(transactionsCollectionTransactions);
                    oldClientidOfTransactionsCollectionTransactions = em.merge(oldClientidOfTransactionsCollectionTransactions);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Clients clients) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clients persistentClients = em.find(Clients.class, clients.getClientid());
            Company companyidOld = persistentClients.getCompanyid();
            Company companyidNew = clients.getCompanyid();
            Collection<Withdraw> withdrawCollectionOld = persistentClients.getWithdrawCollection();
            Collection<Withdraw> withdrawCollectionNew = clients.getWithdrawCollection();
            Collection<Transfer> transferCollectionOld = persistentClients.getTransferCollection();
            Collection<Transfer> transferCollectionNew = clients.getTransferCollection();
            Collection<Transfer> transferCollection1Old = persistentClients.getTransferCollection1();
            Collection<Transfer> transferCollection1New = clients.getTransferCollection1();
            Collection<Deposit> depositCollectionOld = persistentClients.getDepositCollection();
            Collection<Deposit> depositCollectionNew = clients.getDepositCollection();
            Collection<Transactions> transactionsCollectionOld = persistentClients.getTransactionsCollection();
            Collection<Transactions> transactionsCollectionNew = clients.getTransactionsCollection();
            if (companyidNew != null) {
                companyidNew = em.getReference(companyidNew.getClass(), companyidNew.getCompanyid());
                clients.setCompanyid(companyidNew);
            }
            Collection<Withdraw> attachedWithdrawCollectionNew = new ArrayList<Withdraw>();
            for (Withdraw withdrawCollectionNewWithdrawToAttach : withdrawCollectionNew) {
                withdrawCollectionNewWithdrawToAttach = em.getReference(withdrawCollectionNewWithdrawToAttach.getClass(), withdrawCollectionNewWithdrawToAttach.getWithdrawid());
                attachedWithdrawCollectionNew.add(withdrawCollectionNewWithdrawToAttach);
            }
            withdrawCollectionNew = attachedWithdrawCollectionNew;
            clients.setWithdrawCollection(withdrawCollectionNew);
            Collection<Transfer> attachedTransferCollectionNew = new ArrayList<Transfer>();
            for (Transfer transferCollectionNewTransferToAttach : transferCollectionNew) {
                transferCollectionNewTransferToAttach = em.getReference(transferCollectionNewTransferToAttach.getClass(), transferCollectionNewTransferToAttach.getTransferid());
                attachedTransferCollectionNew.add(transferCollectionNewTransferToAttach);
            }
            transferCollectionNew = attachedTransferCollectionNew;
            clients.setTransferCollection(transferCollectionNew);
            Collection<Transfer> attachedTransferCollection1New = new ArrayList<Transfer>();
            for (Transfer transferCollection1NewTransferToAttach : transferCollection1New) {
                transferCollection1NewTransferToAttach = em.getReference(transferCollection1NewTransferToAttach.getClass(), transferCollection1NewTransferToAttach.getTransferid());
                attachedTransferCollection1New.add(transferCollection1NewTransferToAttach);
            }
            transferCollection1New = attachedTransferCollection1New;
            clients.setTransferCollection1(transferCollection1New);
            Collection<Deposit> attachedDepositCollectionNew = new ArrayList<Deposit>();
            for (Deposit depositCollectionNewDepositToAttach : depositCollectionNew) {
                depositCollectionNewDepositToAttach = em.getReference(depositCollectionNewDepositToAttach.getClass(), depositCollectionNewDepositToAttach.getDepositid());
                attachedDepositCollectionNew.add(depositCollectionNewDepositToAttach);
            }
            depositCollectionNew = attachedDepositCollectionNew;
            clients.setDepositCollection(depositCollectionNew);
            Collection<Transactions> attachedTransactionsCollectionNew = new ArrayList<Transactions>();
            for (Transactions transactionsCollectionNewTransactionsToAttach : transactionsCollectionNew) {
                transactionsCollectionNewTransactionsToAttach = em.getReference(transactionsCollectionNewTransactionsToAttach.getClass(), transactionsCollectionNewTransactionsToAttach.getTransactionid());
                attachedTransactionsCollectionNew.add(transactionsCollectionNewTransactionsToAttach);
            }
            transactionsCollectionNew = attachedTransactionsCollectionNew;
            clients.setTransactionsCollection(transactionsCollectionNew);
            clients = em.merge(clients);
            if (companyidOld != null && !companyidOld.equals(companyidNew)) {
                companyidOld.getClientsCollection().remove(clients);
                companyidOld = em.merge(companyidOld);
            }
            if (companyidNew != null && !companyidNew.equals(companyidOld)) {
                companyidNew.getClientsCollection().add(clients);
                companyidNew = em.merge(companyidNew);
            }
            for (Withdraw withdrawCollectionOldWithdraw : withdrawCollectionOld) {
                if (!withdrawCollectionNew.contains(withdrawCollectionOldWithdraw)) {
                    withdrawCollectionOldWithdraw.setClientid(null);
                    withdrawCollectionOldWithdraw = em.merge(withdrawCollectionOldWithdraw);
                }
            }
            for (Withdraw withdrawCollectionNewWithdraw : withdrawCollectionNew) {
                if (!withdrawCollectionOld.contains(withdrawCollectionNewWithdraw)) {
                    Clients oldClientidOfWithdrawCollectionNewWithdraw = withdrawCollectionNewWithdraw.getClientid();
                    withdrawCollectionNewWithdraw.setClientid(clients);
                    withdrawCollectionNewWithdraw = em.merge(withdrawCollectionNewWithdraw);
                    if (oldClientidOfWithdrawCollectionNewWithdraw != null && !oldClientidOfWithdrawCollectionNewWithdraw.equals(clients)) {
                        oldClientidOfWithdrawCollectionNewWithdraw.getWithdrawCollection().remove(withdrawCollectionNewWithdraw);
                        oldClientidOfWithdrawCollectionNewWithdraw = em.merge(oldClientidOfWithdrawCollectionNewWithdraw);
                    }
                }
            }
            for (Transfer transferCollectionOldTransfer : transferCollectionOld) {
                if (!transferCollectionNew.contains(transferCollectionOldTransfer)) {
                    transferCollectionOldTransfer.setClientidto(null);
                    transferCollectionOldTransfer = em.merge(transferCollectionOldTransfer);
                }
            }
            for (Transfer transferCollectionNewTransfer : transferCollectionNew) {
                if (!transferCollectionOld.contains(transferCollectionNewTransfer)) {
                    Clients oldClientidtoOfTransferCollectionNewTransfer = transferCollectionNewTransfer.getClientidto();
                    transferCollectionNewTransfer.setClientidto(clients);
                    transferCollectionNewTransfer = em.merge(transferCollectionNewTransfer);
                    if (oldClientidtoOfTransferCollectionNewTransfer != null && !oldClientidtoOfTransferCollectionNewTransfer.equals(clients)) {
                        oldClientidtoOfTransferCollectionNewTransfer.getTransferCollection().remove(transferCollectionNewTransfer);
                        oldClientidtoOfTransferCollectionNewTransfer = em.merge(oldClientidtoOfTransferCollectionNewTransfer);
                    }
                }
            }
            for (Transfer transferCollection1OldTransfer : transferCollection1Old) {
                if (!transferCollection1New.contains(transferCollection1OldTransfer)) {
                    transferCollection1OldTransfer.setClientidfrom(null);
                    transferCollection1OldTransfer = em.merge(transferCollection1OldTransfer);
                }
            }
            for (Transfer transferCollection1NewTransfer : transferCollection1New) {
                if (!transferCollection1Old.contains(transferCollection1NewTransfer)) {
                    Clients oldClientidfromOfTransferCollection1NewTransfer = transferCollection1NewTransfer.getClientidfrom();
                    transferCollection1NewTransfer.setClientidfrom(clients);
                    transferCollection1NewTransfer = em.merge(transferCollection1NewTransfer);
                    if (oldClientidfromOfTransferCollection1NewTransfer != null && !oldClientidfromOfTransferCollection1NewTransfer.equals(clients)) {
                        oldClientidfromOfTransferCollection1NewTransfer.getTransferCollection1().remove(transferCollection1NewTransfer);
                        oldClientidfromOfTransferCollection1NewTransfer = em.merge(oldClientidfromOfTransferCollection1NewTransfer);
                    }
                }
            }
            for (Deposit depositCollectionOldDeposit : depositCollectionOld) {
                if (!depositCollectionNew.contains(depositCollectionOldDeposit)) {
                    depositCollectionOldDeposit.setClientid(null);
                    depositCollectionOldDeposit = em.merge(depositCollectionOldDeposit);
                }
            }
            for (Deposit depositCollectionNewDeposit : depositCollectionNew) {
                if (!depositCollectionOld.contains(depositCollectionNewDeposit)) {
                    Clients oldClientidOfDepositCollectionNewDeposit = depositCollectionNewDeposit.getClientid();
                    depositCollectionNewDeposit.setClientid(clients);
                    depositCollectionNewDeposit = em.merge(depositCollectionNewDeposit);
                    if (oldClientidOfDepositCollectionNewDeposit != null && !oldClientidOfDepositCollectionNewDeposit.equals(clients)) {
                        oldClientidOfDepositCollectionNewDeposit.getDepositCollection().remove(depositCollectionNewDeposit);
                        oldClientidOfDepositCollectionNewDeposit = em.merge(oldClientidOfDepositCollectionNewDeposit);
                    }
                }
            }
            for (Transactions transactionsCollectionOldTransactions : transactionsCollectionOld) {
                if (!transactionsCollectionNew.contains(transactionsCollectionOldTransactions)) {
                    transactionsCollectionOldTransactions.setClientid(null);
                    transactionsCollectionOldTransactions = em.merge(transactionsCollectionOldTransactions);
                }
            }
            for (Transactions transactionsCollectionNewTransactions : transactionsCollectionNew) {
                if (!transactionsCollectionOld.contains(transactionsCollectionNewTransactions)) {
                    Clients oldClientidOfTransactionsCollectionNewTransactions = transactionsCollectionNewTransactions.getClientid();
                    transactionsCollectionNewTransactions.setClientid(clients);
                    transactionsCollectionNewTransactions = em.merge(transactionsCollectionNewTransactions);
                    if (oldClientidOfTransactionsCollectionNewTransactions != null && !oldClientidOfTransactionsCollectionNewTransactions.equals(clients)) {
                        oldClientidOfTransactionsCollectionNewTransactions.getTransactionsCollection().remove(transactionsCollectionNewTransactions);
                        oldClientidOfTransactionsCollectionNewTransactions = em.merge(oldClientidOfTransactionsCollectionNewTransactions);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = clients.getClientid();
                if (findClients(id) == null) {
                    throw new NonexistentEntityException("The clients with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clients clients;
            try {
                clients = em.getReference(Clients.class, id);
                clients.getClientid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clients with id " + id + " no longer exists.", enfe);
            }
            Company companyid = clients.getCompanyid();
            if (companyid != null) {
                companyid.getClientsCollection().remove(clients);
                companyid = em.merge(companyid);
            }
            Collection<Withdraw> withdrawCollection = clients.getWithdrawCollection();
            for (Withdraw withdrawCollectionWithdraw : withdrawCollection) {
                withdrawCollectionWithdraw.setClientid(null);
                withdrawCollectionWithdraw = em.merge(withdrawCollectionWithdraw);
            }
            Collection<Transfer> transferCollection = clients.getTransferCollection();
            for (Transfer transferCollectionTransfer : transferCollection) {
                transferCollectionTransfer.setClientidto(null);
                transferCollectionTransfer = em.merge(transferCollectionTransfer);
            }
            Collection<Transfer> transferCollection1 = clients.getTransferCollection1();
            for (Transfer transferCollection1Transfer : transferCollection1) {
                transferCollection1Transfer.setClientidfrom(null);
                transferCollection1Transfer = em.merge(transferCollection1Transfer);
            }
            Collection<Deposit> depositCollection = clients.getDepositCollection();
            for (Deposit depositCollectionDeposit : depositCollection) {
                depositCollectionDeposit.setClientid(null);
                depositCollectionDeposit = em.merge(depositCollectionDeposit);
            }
            Collection<Transactions> transactionsCollection = clients.getTransactionsCollection();
            for (Transactions transactionsCollectionTransactions : transactionsCollection) {
                transactionsCollectionTransactions.setClientid(null);
                transactionsCollectionTransactions = em.merge(transactionsCollectionTransactions);
            }
            em.remove(clients);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Clients> findClientsEntities() {
        return findClientsEntities(true, -1, -1);
    }

    public List<Clients> findClientsEntities(int maxResults, int firstResult) {
        return findClientsEntities(false, maxResults, firstResult);
    }

    private List<Clients> findClientsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Clients.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Clients findClients(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Clients.class, id);
        } finally {
            em.close();
        }
    }

    public int getClientsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Clients> rt = cq.from(Clients.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
