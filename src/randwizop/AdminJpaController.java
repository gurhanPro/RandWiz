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
public class AdminJpaController implements Serializable {

    public AdminJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Admin admin) {
        if (admin.getTransactionsCollection() == null) {
            admin.setTransactionsCollection(new ArrayList<Transactions>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Company companyid = admin.getCompanyid();
            if (companyid != null) {
                companyid = em.getReference(companyid.getClass(), companyid.getCompanyid());
                admin.setCompanyid(companyid);
            }
            Collection<Transactions> attachedTransactionsCollection = new ArrayList<Transactions>();
            for (Transactions transactionsCollectionTransactionsToAttach : admin.getTransactionsCollection()) {
                transactionsCollectionTransactionsToAttach = em.getReference(transactionsCollectionTransactionsToAttach.getClass(), transactionsCollectionTransactionsToAttach.getTransactionid());
                attachedTransactionsCollection.add(transactionsCollectionTransactionsToAttach);
            }
            admin.setTransactionsCollection(attachedTransactionsCollection);
            em.persist(admin);
            if (companyid != null) {
                companyid.getAdminCollection().add(admin);
                companyid = em.merge(companyid);
            }
            for (Transactions transactionsCollectionTransactions : admin.getTransactionsCollection()) {
                Admin oldHandleridOfTransactionsCollectionTransactions = transactionsCollectionTransactions.getHandlerid();
                transactionsCollectionTransactions.setHandlerid(admin);
                transactionsCollectionTransactions = em.merge(transactionsCollectionTransactions);
                if (oldHandleridOfTransactionsCollectionTransactions != null) {
                    oldHandleridOfTransactionsCollectionTransactions.getTransactionsCollection().remove(transactionsCollectionTransactions);
                    oldHandleridOfTransactionsCollectionTransactions = em.merge(oldHandleridOfTransactionsCollectionTransactions);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Admin admin) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Admin persistentAdmin = em.find(Admin.class, admin.getAdminid());
            Company companyidOld = persistentAdmin.getCompanyid();
            Company companyidNew = admin.getCompanyid();
            Collection<Transactions> transactionsCollectionOld = persistentAdmin.getTransactionsCollection();
            Collection<Transactions> transactionsCollectionNew = admin.getTransactionsCollection();
            if (companyidNew != null) {
                companyidNew = em.getReference(companyidNew.getClass(), companyidNew.getCompanyid());
                admin.setCompanyid(companyidNew);
            }
            Collection<Transactions> attachedTransactionsCollectionNew = new ArrayList<Transactions>();
            for (Transactions transactionsCollectionNewTransactionsToAttach : transactionsCollectionNew) {
                transactionsCollectionNewTransactionsToAttach = em.getReference(transactionsCollectionNewTransactionsToAttach.getClass(), transactionsCollectionNewTransactionsToAttach.getTransactionid());
                attachedTransactionsCollectionNew.add(transactionsCollectionNewTransactionsToAttach);
            }
            transactionsCollectionNew = attachedTransactionsCollectionNew;
            admin.setTransactionsCollection(transactionsCollectionNew);
            admin = em.merge(admin);
            if (companyidOld != null && !companyidOld.equals(companyidNew)) {
                companyidOld.getAdminCollection().remove(admin);
                companyidOld = em.merge(companyidOld);
            }
            if (companyidNew != null && !companyidNew.equals(companyidOld)) {
                companyidNew.getAdminCollection().add(admin);
                companyidNew = em.merge(companyidNew);
            }
            for (Transactions transactionsCollectionOldTransactions : transactionsCollectionOld) {
                if (!transactionsCollectionNew.contains(transactionsCollectionOldTransactions)) {
                    transactionsCollectionOldTransactions.setHandlerid(null);
                    transactionsCollectionOldTransactions = em.merge(transactionsCollectionOldTransactions);
                }
            }
            for (Transactions transactionsCollectionNewTransactions : transactionsCollectionNew) {
                if (!transactionsCollectionOld.contains(transactionsCollectionNewTransactions)) {
                    Admin oldHandleridOfTransactionsCollectionNewTransactions = transactionsCollectionNewTransactions.getHandlerid();
                    transactionsCollectionNewTransactions.setHandlerid(admin);
                    transactionsCollectionNewTransactions = em.merge(transactionsCollectionNewTransactions);
                    if (oldHandleridOfTransactionsCollectionNewTransactions != null && !oldHandleridOfTransactionsCollectionNewTransactions.equals(admin)) {
                        oldHandleridOfTransactionsCollectionNewTransactions.getTransactionsCollection().remove(transactionsCollectionNewTransactions);
                        oldHandleridOfTransactionsCollectionNewTransactions = em.merge(oldHandleridOfTransactionsCollectionNewTransactions);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = admin.getAdminid();
                if (findAdmin(id) == null) {
                    throw new NonexistentEntityException("The admin with id " + id + " no longer exists.");
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
            Admin admin;
            try {
                admin = em.getReference(Admin.class, id);
                admin.getAdminid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The admin with id " + id + " no longer exists.", enfe);
            }
            Company companyid = admin.getCompanyid();
            if (companyid != null) {
                companyid.getAdminCollection().remove(admin);
                companyid = em.merge(companyid);
            }
            Collection<Transactions> transactionsCollection = admin.getTransactionsCollection();
            for (Transactions transactionsCollectionTransactions : transactionsCollection) {
                transactionsCollectionTransactions.setHandlerid(null);
                transactionsCollectionTransactions = em.merge(transactionsCollectionTransactions);
            }
            em.remove(admin);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Admin> findAdminEntities() {
        return findAdminEntities(true, -1, -1);
    }

    public List<Admin> findAdminEntities(int maxResults, int firstResult) {
        return findAdminEntities(false, maxResults, firstResult);
    }

    private List<Admin> findAdminEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Admin.class));
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

    public Admin findAdmin(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Admin.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdminCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Admin> rt = cq.from(Admin.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
