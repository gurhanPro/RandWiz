/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randwizop;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import randwizop.exceptions.NonexistentEntityException;

/**
 *
 * @author maxamed
 */
public class TransactionsJpaController implements Serializable {

    public TransactionsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Transactions transactions) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Admin handlerid = transactions.getHandlerid();
            if (handlerid != null) {
                handlerid = em.getReference(handlerid.getClass(), handlerid.getAdminid());
                transactions.setHandlerid(handlerid);
            }
            Clients clientid = transactions.getClientid();
            if (clientid != null) {
                clientid = em.getReference(clientid.getClass(), clientid.getClientid());
                transactions.setClientid(clientid);
            }
            Transactiontype transactiontypeid = transactions.getTransactiontypeid();
            if (transactiontypeid != null) {
                transactiontypeid = em.getReference(transactiontypeid.getClass(), transactiontypeid.getTransactiontypeid());
                transactions.setTransactiontypeid(transactiontypeid);
            }
            em.persist(transactions);
            if (handlerid != null) {
                handlerid.getTransactionsCollection().add(transactions);
                handlerid = em.merge(handlerid);
            }
            if (clientid != null) {
                clientid.getTransactionsCollection().add(transactions);
                clientid = em.merge(clientid);
            }
            if (transactiontypeid != null) {
                transactiontypeid.getTransactionsCollection().add(transactions);
                transactiontypeid = em.merge(transactiontypeid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Transactions transactions) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Transactions persistentTransactions = em.find(Transactions.class, transactions.getTransactionid());
            Admin handleridOld = persistentTransactions.getHandlerid();
            Admin handleridNew = transactions.getHandlerid();
            Clients clientidOld = persistentTransactions.getClientid();
            Clients clientidNew = transactions.getClientid();
            Transactiontype transactiontypeidOld = persistentTransactions.getTransactiontypeid();
            Transactiontype transactiontypeidNew = transactions.getTransactiontypeid();
            if (handleridNew != null) {
                handleridNew = em.getReference(handleridNew.getClass(), handleridNew.getAdminid());
                transactions.setHandlerid(handleridNew);
            }
            if (clientidNew != null) {
                clientidNew = em.getReference(clientidNew.getClass(), clientidNew.getClientid());
                transactions.setClientid(clientidNew);
            }
            if (transactiontypeidNew != null) {
                transactiontypeidNew = em.getReference(transactiontypeidNew.getClass(), transactiontypeidNew.getTransactiontypeid());
                transactions.setTransactiontypeid(transactiontypeidNew);
            }
            transactions = em.merge(transactions);
            if (handleridOld != null && !handleridOld.equals(handleridNew)) {
                handleridOld.getTransactionsCollection().remove(transactions);
                handleridOld = em.merge(handleridOld);
            }
            if (handleridNew != null && !handleridNew.equals(handleridOld)) {
                handleridNew.getTransactionsCollection().add(transactions);
                handleridNew = em.merge(handleridNew);
            }
            if (clientidOld != null && !clientidOld.equals(clientidNew)) {
                clientidOld.getTransactionsCollection().remove(transactions);
                clientidOld = em.merge(clientidOld);
            }
            if (clientidNew != null && !clientidNew.equals(clientidOld)) {
                clientidNew.getTransactionsCollection().add(transactions);
                clientidNew = em.merge(clientidNew);
            }
            if (transactiontypeidOld != null && !transactiontypeidOld.equals(transactiontypeidNew)) {
                transactiontypeidOld.getTransactionsCollection().remove(transactions);
                transactiontypeidOld = em.merge(transactiontypeidOld);
            }
            if (transactiontypeidNew != null && !transactiontypeidNew.equals(transactiontypeidOld)) {
                transactiontypeidNew.getTransactionsCollection().add(transactions);
                transactiontypeidNew = em.merge(transactiontypeidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = transactions.getTransactionid();
                if (findTransactions(id) == null) {
                    throw new NonexistentEntityException("The transactions with id " + id + " no longer exists.");
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
            Transactions transactions;
            try {
                transactions = em.getReference(Transactions.class, id);
                transactions.getTransactionid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The transactions with id " + id + " no longer exists.", enfe);
            }
            Admin handlerid = transactions.getHandlerid();
            if (handlerid != null) {
                handlerid.getTransactionsCollection().remove(transactions);
                handlerid = em.merge(handlerid);
            }
            Clients clientid = transactions.getClientid();
            if (clientid != null) {
                clientid.getTransactionsCollection().remove(transactions);
                clientid = em.merge(clientid);
            }
            Transactiontype transactiontypeid = transactions.getTransactiontypeid();
            if (transactiontypeid != null) {
                transactiontypeid.getTransactionsCollection().remove(transactions);
                transactiontypeid = em.merge(transactiontypeid);
            }
            em.remove(transactions);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Transactions> findTransactionsEntities() {
        return findTransactionsEntities(true, -1, -1);
    }

    public List<Transactions> findTransactionsEntities(int maxResults, int firstResult) {
        return findTransactionsEntities(false, maxResults, firstResult);
    }

    private List<Transactions> findTransactionsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Transactions.class));
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

    public Transactions findTransactions(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Transactions.class, id);
        } finally {
            em.close();
        }
    }

    public int getTransactionsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Transactions> rt = cq.from(Transactions.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
