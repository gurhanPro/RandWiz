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
public class DepositJpaController implements Serializable {

    public DepositJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Deposit deposit) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clients clientid = deposit.getClientid();
            if (clientid != null) {
                clientid = em.getReference(clientid.getClass(), clientid.getClientid());
                deposit.setClientid(clientid);
            }
            em.persist(deposit);
            if (clientid != null) {
                clientid.getDepositCollection().add(deposit);
                clientid = em.merge(clientid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Deposit deposit) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Deposit persistentDeposit = em.find(Deposit.class, deposit.getDepositid());
            Clients clientidOld = persistentDeposit.getClientid();
            Clients clientidNew = deposit.getClientid();
            if (clientidNew != null) {
                clientidNew = em.getReference(clientidNew.getClass(), clientidNew.getClientid());
                deposit.setClientid(clientidNew);
            }
            deposit = em.merge(deposit);
            if (clientidOld != null && !clientidOld.equals(clientidNew)) {
                clientidOld.getDepositCollection().remove(deposit);
                clientidOld = em.merge(clientidOld);
            }
            if (clientidNew != null && !clientidNew.equals(clientidOld)) {
                clientidNew.getDepositCollection().add(deposit);
                clientidNew = em.merge(clientidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = deposit.getDepositid();
                if (findDeposit(id) == null) {
                    throw new NonexistentEntityException("The deposit with id " + id + " no longer exists.");
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
            Deposit deposit;
            try {
                deposit = em.getReference(Deposit.class, id);
                deposit.getDepositid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The deposit with id " + id + " no longer exists.", enfe);
            }
            Clients clientid = deposit.getClientid();
            if (clientid != null) {
                clientid.getDepositCollection().remove(deposit);
                clientid = em.merge(clientid);
            }
            em.remove(deposit);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Deposit> findDepositEntities() {
        return findDepositEntities(true, -1, -1);
    }

    public List<Deposit> findDepositEntities(int maxResults, int firstResult) {
        return findDepositEntities(false, maxResults, firstResult);
    }

    private List<Deposit> findDepositEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Deposit.class));
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

    public Deposit findDeposit(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Deposit.class, id);
        } finally {
            em.close();
        }
    }

    public int getDepositCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Deposit> rt = cq.from(Deposit.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
