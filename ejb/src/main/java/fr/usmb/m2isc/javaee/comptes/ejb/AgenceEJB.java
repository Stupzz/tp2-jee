package fr.usmb.m2isc.javaee.comptes.ejb;

import fr.usmb.m2isc.javaee.comptes.jpa.Agence;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@LocalBean
public class AgenceEJB {

    @PersistenceContext
    private EntityManager em;

    public AgenceEJB() {
    }

    public Agence creerAgence(Agence agence) {
        em.persist(agence);
        return agence;
    }

    public Agence getAgence(int id) {
        return em.find(Agence.class, id);
    }

    public List<Agence> getAll() {
        Query req = em.createNamedQuery("all");
        return req.getResultList();
    }

    public void supprimerAgence(int id) {
        em.remove(getAgence(id));
    }

    public void updateAgence(Agence agence) {
        em.merge(agence);
    }
}
