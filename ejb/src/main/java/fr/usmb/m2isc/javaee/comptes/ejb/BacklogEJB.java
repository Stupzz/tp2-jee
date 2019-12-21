package fr.usmb.m2isc.javaee.comptes.ejb;

import fr.usmb.m2isc.javaee.comptes.jpa.Backlog;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class BacklogEJB {

    @PersistenceContext
    private EntityManager em;

    public BacklogEJB() {
    }

    public Backlog creerBacklog(Backlog backlog) {
        em.persist(backlog);
        return backlog;
    }

    public Backlog getBacklog(int id) {
        return em.find(Backlog.class, id);
    }

    public void supprimerBacklog(int id) {
        em.remove(getBacklog(id));
    }

    public void updateBacklog(Backlog backlog) {
        em.merge(backlog);
    }


}
