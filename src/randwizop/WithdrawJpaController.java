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
public class WithdrawJpaController implements Serializable {

    public WithdrawJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Withdraw withdraw) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clients clientid = withdraw.getClientid();
            if (clientid != null) {
                clientid = em.getReference(clientid.getClass(), clientid.getClientid());
                withdraw.setClientid(clientid);
            }
            em.persist(withdraw);
            if (clientid != null) {
                clientid.getWithdrawCollection().add(withdraw);
                clientid = em.merge(clientid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Withdraw withdraw) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Withdraw persistentWithdraw = em.find(Withdraw.class, withdraw.getWithdrawid());
            Clients clientidOld = persistentWithdraw.getClientid();
            Clients clientidNew = withdraw.getClientid();
            if (clientidNew != null) {
                clientidNew = em.getReference(clientidNew.getClass(), clientidNew.getClientid());
                withdraw.setClientid(clientidNew);
            }
            withdraw = em.merge(withdraw);
            if (clientidOld != null && !clientidOld.equals(clientidNew)) {
                clientidOld.getWithdrawCollection().remove(withdraw);
                clientidOld = em.merge(clientidOld);
            }
            if (clientidNew != null && !clientidNew.equals(clientidOld)) {
                clientidNew.getWithdrawCollection().add(withdraw);
                clientidNew = em.merge(clientidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = withdraw.getWithdrawid();
                if (findWithdraw(id) == null) {
                    throw new NonexistentEntityException("The withdraw with id " + id + " no longer exists.");
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
            Withdraw withdraw;
            try {
                withdraw = em.getReference(Withdraw.class, id);
                withdraw.getWithdrawid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The withdraw with id " + id + " no longer exists.", enfe);
            }
            Clients clientid = withdraw.getClientid();
            if (clientid != null) {
                clientid.getWithdrawCollection().remove(withdraw);
                clientid = em.merge(clientid);
            }
            em.remove(withdraw);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Withdraw> findWithdrawEntities() {
        return findWithdrawEntities(true, -1, -1);
    }

    public List<Withdraw> findWithdrawEntities(int maxResults, int firstResult) {
        return findWithdrawEntities(false, maxResults, firstResult);
    }

    private List<Withdraw> findWithdrawEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Withdraw.class));
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

    public Withdraw findWithdraw(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Withdraw.class, id);
        } finally {
            em.close();
        }
    }

    public int getWithdrawCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Withdraw> rt = cq.from(Withdraw.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
