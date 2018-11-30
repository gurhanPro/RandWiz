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
public class TransferJpaController implements Serializable {

    public TransferJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Transfer transfer) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clients clientidto = transfer.getClientidto();
            if (clientidto != null) {
                clientidto = em.getReference(clientidto.getClass(), clientidto.getClientid());
                transfer.setClientidto(clientidto);
            }
            Clients clientidfrom = transfer.getClientidfrom();
            if (clientidfrom != null) {
                clientidfrom = em.getReference(clientidfrom.getClass(), clientidfrom.getClientid());
                transfer.setClientidfrom(clientidfrom);
            }
            em.persist(transfer);
            if (clientidto != null) {
                clientidto.getTransferCollection().add(transfer);
                clientidto = em.merge(clientidto);
            }
            if (clientidfrom != null) {
                clientidfrom.getTransferCollection().add(transfer);
                clientidfrom = em.merge(clientidfrom);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Transfer transfer) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Transfer persistentTransfer = em.find(Transfer.class, transfer.getTransferid());
            Clients clientidtoOld = persistentTransfer.getClientidto();
            Clients clientidtoNew = transfer.getClientidto();
            Clients clientidfromOld = persistentTransfer.getClientidfrom();
            Clients clientidfromNew = transfer.getClientidfrom();
            if (clientidtoNew != null) {
                clientidtoNew = em.getReference(clientidtoNew.getClass(), clientidtoNew.getClientid());
                transfer.setClientidto(clientidtoNew);
            }
            if (clientidfromNew != null) {
                clientidfromNew = em.getReference(clientidfromNew.getClass(), clientidfromNew.getClientid());
                transfer.setClientidfrom(clientidfromNew);
            }
            transfer = em.merge(transfer);
            if (clientidtoOld != null && !clientidtoOld.equals(clientidtoNew)) {
                clientidtoOld.getTransferCollection().remove(transfer);
                clientidtoOld = em.merge(clientidtoOld);
            }
            if (clientidtoNew != null && !clientidtoNew.equals(clientidtoOld)) {
                clientidtoNew.getTransferCollection().add(transfer);
                clientidtoNew = em.merge(clientidtoNew);
            }
            if (clientidfromOld != null && !clientidfromOld.equals(clientidfromNew)) {
                clientidfromOld.getTransferCollection().remove(transfer);
                clientidfromOld = em.merge(clientidfromOld);
            }
            if (clientidfromNew != null && !clientidfromNew.equals(clientidfromOld)) {
                clientidfromNew.getTransferCollection().add(transfer);
                clientidfromNew = em.merge(clientidfromNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = transfer.getTransferid();
                if (findTransfer(id) == null) {
                    throw new NonexistentEntityException("The transfer with id " + id + " no longer exists.");
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
            Transfer transfer;
            try {
                transfer = em.getReference(Transfer.class, id);
                transfer.getTransferid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The transfer with id " + id + " no longer exists.", enfe);
            }
            Clients clientidto = transfer.getClientidto();
            if (clientidto != null) {
                clientidto.getTransferCollection().remove(transfer);
                clientidto = em.merge(clientidto);
            }
            Clients clientidfrom = transfer.getClientidfrom();
            if (clientidfrom != null) {
                clientidfrom.getTransferCollection().remove(transfer);
                clientidfrom = em.merge(clientidfrom);
            }
            em.remove(transfer);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Transfer> findTransferEntities() {
        return findTransferEntities(true, -1, -1);
    }

    public List<Transfer> findTransferEntities(int maxResults, int firstResult) {
        return findTransferEntities(false, maxResults, firstResult);
    }

    private List<Transfer> findTransferEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Transfer.class));
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

    public Transfer findTransfer(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Transfer.class, id);
        } finally {
            em.close();
        }
    }

    public int getTransferCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Transfer> rt = cq.from(Transfer.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
