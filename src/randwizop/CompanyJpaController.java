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
public class CompanyJpaController implements Serializable {

    public CompanyJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Company company) {
        if (company.getAdminCollection() == null) {
            company.setAdminCollection(new ArrayList<Admin>());
        }
        if (company.getClientsCollection() == null) {
            company.setClientsCollection(new ArrayList<Clients>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Admin> attachedAdminCollection = new ArrayList<Admin>();
            for (Admin adminCollectionAdminToAttach : company.getAdminCollection()) {
                adminCollectionAdminToAttach = em.getReference(adminCollectionAdminToAttach.getClass(), adminCollectionAdminToAttach.getAdminid());
                attachedAdminCollection.add(adminCollectionAdminToAttach);
            }
            company.setAdminCollection(attachedAdminCollection);
            Collection<Clients> attachedClientsCollection = new ArrayList<Clients>();
            for (Clients clientsCollectionClientsToAttach : company.getClientsCollection()) {
                clientsCollectionClientsToAttach = em.getReference(clientsCollectionClientsToAttach.getClass(), clientsCollectionClientsToAttach.getClientid());
                attachedClientsCollection.add(clientsCollectionClientsToAttach);
            }
            company.setClientsCollection(attachedClientsCollection);
            em.persist(company);
            for (Admin adminCollectionAdmin : company.getAdminCollection()) {
                Company oldCompanyidOfAdminCollectionAdmin = adminCollectionAdmin.getCompanyid();
                adminCollectionAdmin.setCompanyid(company);
                adminCollectionAdmin = em.merge(adminCollectionAdmin);
                if (oldCompanyidOfAdminCollectionAdmin != null) {
                    oldCompanyidOfAdminCollectionAdmin.getAdminCollection().remove(adminCollectionAdmin);
                    oldCompanyidOfAdminCollectionAdmin = em.merge(oldCompanyidOfAdminCollectionAdmin);
                }
            }
            for (Clients clientsCollectionClients : company.getClientsCollection()) {
                Company oldCompanyidOfClientsCollectionClients = clientsCollectionClients.getCompanyid();
                clientsCollectionClients.setCompanyid(company);
                clientsCollectionClients = em.merge(clientsCollectionClients);
                if (oldCompanyidOfClientsCollectionClients != null) {
                    oldCompanyidOfClientsCollectionClients.getClientsCollection().remove(clientsCollectionClients);
                    oldCompanyidOfClientsCollectionClients = em.merge(oldCompanyidOfClientsCollectionClients);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Company company) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Company persistentCompany = em.find(Company.class, company.getCompanyid());
            Collection<Admin> adminCollectionOld = persistentCompany.getAdminCollection();
            Collection<Admin> adminCollectionNew = company.getAdminCollection();
            Collection<Clients> clientsCollectionOld = persistentCompany.getClientsCollection();
            Collection<Clients> clientsCollectionNew = company.getClientsCollection();
            Collection<Admin> attachedAdminCollectionNew = new ArrayList<Admin>();
            for (Admin adminCollectionNewAdminToAttach : adminCollectionNew) {
                adminCollectionNewAdminToAttach = em.getReference(adminCollectionNewAdminToAttach.getClass(), adminCollectionNewAdminToAttach.getAdminid());
                attachedAdminCollectionNew.add(adminCollectionNewAdminToAttach);
            }
            adminCollectionNew = attachedAdminCollectionNew;
            company.setAdminCollection(adminCollectionNew);
            Collection<Clients> attachedClientsCollectionNew = new ArrayList<Clients>();
            for (Clients clientsCollectionNewClientsToAttach : clientsCollectionNew) {
                clientsCollectionNewClientsToAttach = em.getReference(clientsCollectionNewClientsToAttach.getClass(), clientsCollectionNewClientsToAttach.getClientid());
                attachedClientsCollectionNew.add(clientsCollectionNewClientsToAttach);
            }
            clientsCollectionNew = attachedClientsCollectionNew;
            company.setClientsCollection(clientsCollectionNew);
            company = em.merge(company);
            for (Admin adminCollectionOldAdmin : adminCollectionOld) {
                if (!adminCollectionNew.contains(adminCollectionOldAdmin)) {
                    adminCollectionOldAdmin.setCompanyid(null);
                    adminCollectionOldAdmin = em.merge(adminCollectionOldAdmin);
                }
            }
            for (Admin adminCollectionNewAdmin : adminCollectionNew) {
                if (!adminCollectionOld.contains(adminCollectionNewAdmin)) {
                    Company oldCompanyidOfAdminCollectionNewAdmin = adminCollectionNewAdmin.getCompanyid();
                    adminCollectionNewAdmin.setCompanyid(company);
                    adminCollectionNewAdmin = em.merge(adminCollectionNewAdmin);
                    if (oldCompanyidOfAdminCollectionNewAdmin != null && !oldCompanyidOfAdminCollectionNewAdmin.equals(company)) {
                        oldCompanyidOfAdminCollectionNewAdmin.getAdminCollection().remove(adminCollectionNewAdmin);
                        oldCompanyidOfAdminCollectionNewAdmin = em.merge(oldCompanyidOfAdminCollectionNewAdmin);
                    }
                }
            }
            for (Clients clientsCollectionOldClients : clientsCollectionOld) {
                if (!clientsCollectionNew.contains(clientsCollectionOldClients)) {
                    clientsCollectionOldClients.setCompanyid(null);
                    clientsCollectionOldClients = em.merge(clientsCollectionOldClients);
                }
            }
            for (Clients clientsCollectionNewClients : clientsCollectionNew) {
                if (!clientsCollectionOld.contains(clientsCollectionNewClients)) {
                    Company oldCompanyidOfClientsCollectionNewClients = clientsCollectionNewClients.getCompanyid();
                    clientsCollectionNewClients.setCompanyid(company);
                    clientsCollectionNewClients = em.merge(clientsCollectionNewClients);
                    if (oldCompanyidOfClientsCollectionNewClients != null && !oldCompanyidOfClientsCollectionNewClients.equals(company)) {
                        oldCompanyidOfClientsCollectionNewClients.getClientsCollection().remove(clientsCollectionNewClients);
                        oldCompanyidOfClientsCollectionNewClients = em.merge(oldCompanyidOfClientsCollectionNewClients);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = company.getCompanyid();
                if (findCompany(id) == null) {
                    throw new NonexistentEntityException("The company with id " + id + " no longer exists.");
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
            Company company;
            try {
                company = em.getReference(Company.class, id);
                company.getCompanyid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The company with id " + id + " no longer exists.", enfe);
            }
            Collection<Admin> adminCollection = company.getAdminCollection();
            for (Admin adminCollectionAdmin : adminCollection) {
                adminCollectionAdmin.setCompanyid(null);
                adminCollectionAdmin = em.merge(adminCollectionAdmin);
            }
            Collection<Clients> clientsCollection = company.getClientsCollection();
            for (Clients clientsCollectionClients : clientsCollection) {
                clientsCollectionClients.setCompanyid(null);
                clientsCollectionClients = em.merge(clientsCollectionClients);
            }
            em.remove(company);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Company> findCompanyEntities() {
        return findCompanyEntities(true, -1, -1);
    }

    public List<Company> findCompanyEntities(int maxResults, int firstResult) {
        return findCompanyEntities(false, maxResults, firstResult);
    }

    private List<Company> findCompanyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Company.class));
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

    public Company findCompany(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Company.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompanyCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Company> rt = cq.from(Company.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
