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
public class TransactiontypeJpaController implements Serializable {

    public TransactiontypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Transactiontype transactiontype) {
        if (transactiontype.getTransactionsCollection() == null) {
            transactiontype.setTransactionsCollection(new ArrayList<Transactions>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Transactions> attachedTransactionsCollection = new ArrayList<Transactions>();
            for (Transactions transactionsCollectionTransactionsToAttach : transactiontype.getTransactionsCollection()) {
                transactionsCollectionTransactionsToAttach = em.getReference(transactionsCollectionTransactionsToAttach.getClass(), transactionsCollectionTransactionsToAttach.getTransactionid());
                attachedTransactionsCollection.add(transactionsCollectionTransactionsToAttach);
            }
            transactiontype.setTransactionsCollection(attachedTransactionsCollection);
            em.persist(transactiontype);
            for (Transactions transactionsCollectionTransactions : transactiontype.getTransactionsCollection()) {
                Transactiontype oldTransactiontypeidOfTransactionsCollectionTransactions = transactionsCollectionTransactions.getTransactiontypeid();
                transactionsCollectionTransactions.setTransactiontypeid(transactiontype);
                transactionsCollectionTransactions = em.merge(transactionsCollectionTransactions);
                if (oldTransactiontypeidOfTransactionsCollectionTransactions != null) {
                    oldTransactiontypeidOfTransactionsCollectionTransactions.getTransactionsCollection().remove(transactionsCollectionTransactions);
                    oldTransactiontypeidOfTransactionsCollectionTransactions = em.merge(oldTransactiontypeidOfTransactionsCollectionTransactions);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Transactiontype transactiontype) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Transactiontype persistentTransactiontype = em.find(Transactiontype.class, transactiontype.getTransactiontypeid());
            Collection<Transactions> transactionsCollectionOld = persistentTransactiontype.getTransactionsCollection();
            Collection<Transactions> transactionsCollectionNew = transactiontype.getTransactionsCollection();
            Collection<Transactions> attachedTransactionsCollectionNew = new ArrayList<Transactions>();
            for (Transactions transactionsCollectionNewTransactionsToAttach : transactionsCollectionNew) {
                transactionsCollectionNewTransactionsToAttach = em.getReference(transactionsCollectionNewTransactionsToAttach.getClass(), transactionsCollectionNewTransactionsToAttach.getTransactionid());
                attachedTransactionsCollectionNew.add(transactionsCollectionNewTransactionsToAttach);
            }
            transactionsCollectionNew = attachedTransactionsCollectionNew;
            transactiontype.setTransactionsCollection(transactionsCollectionNew);
            transactiontype = em.merge(transactiontype);
            for (Transactions transactionsCollectionOldTransactions : transactionsCollectionOld) {
                if (!transactionsCollectionNew.contains(transactionsCollectionOldTransactions)) {
                    transactionsCollectionOldTransactions.setTransactiontypeid(null);
                    transactionsCollectionOldTransactions = em.merge(transactionsCollectionOldTransactions);
                }
            }
            for (Transactions transactionsCollectionNewTransactions : transactionsCollectionNew) {
                if (!transactionsCollectionOld.contains(transactionsCollectionNewTransactions)) {
                    Transactiontype oldTransactiontypeidOfTransactionsCollectionNewTransactions = transactionsCollectionNewTransactions.getTransactiontypeid();
                    transactionsCollectionNewTransactions.setTransactiontypeid(transactiontype);
                    transactionsCollectionNewTransactions = em.merge(transactionsCollectionNewTransactions);
                    if (oldTransactiontypeidOfTransactionsCollectionNewTransactions != null && !oldTransactiontypeidOfTransactionsCollectionNewTransactions.equals(transactiontype)) {
                        oldTransactiontypeidOfTransactionsCollectionNewTransactions.getTransactionsCollection().remove(transactionsCollectionNewTransactions);
                        oldTransactiontypeidOfTransactionsCollectionNewTransactions = em.merge(oldTransactiontypeidOfTransactionsCollectionNewTransactions);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = transactiontype.getTransactiontypeid();
                if (findTransactiontype(id) == null) {
                    throw new NonexistentEntityException("The transactiontype with id " + id + " no longer exists.");
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
            Transactiontype transactiontype;
            try {
                transactiontype = em.getReference(Transactiontype.class, id);
                transactiontype.getTransactiontypeid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The transactiontype with id " + id + " no longer exists.", enfe);
            }
            Collection<Transactions> transactionsCollection = transactiontype.getTransactionsCollection();
            for (Transactions transactionsCollectionTransactions : transactionsCollection) {
                transactionsCollectionTransactions.setTransactiontypeid(null);
                transactionsCollectionTransactions = em.merge(transactionsCollectionTransactions);
            }
            em.remove(transactiontype);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Transactiontype> findTransactiontypeEntities() {
        return findTransactiontypeEntities(true, -1, -1);
    }

    public List<Transactiontype> findTransactiontypeEntities(int maxResults, int firstResult) {
        return findTransactiontypeEntities(false, maxResults, firstResult);
    }

    private List<Transactiontype> findTransactiontypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Transactiontype.class));
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

    public Transactiontype findTransactiontype(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Transactiontype.class, id);
        } finally {
            em.close();
        }
    }

    public int getTransactiontypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Transactiontype> rt = cq.from(Transactiontype.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
