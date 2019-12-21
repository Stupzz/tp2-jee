package fr.usmb.m2isc.javaee.comptes.ejb;

import fr.usmb.m2isc.javaee.comptes.jpa.Userstory;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class UserstoryEJB {

    @PersistenceContext
    private EntityManager em;

    public UserstoryEJB() {
    }

    public Userstory creerUserstory(Userstory userstory) {
        em.persist(userstory);
        return userstory;
    }

    public Userstory getUserstory(int id) {
        return em.find(Userstory.class, id);
    }


    public void supprimerUserstory(int id) {
        em.remove(getUserstory(id));
    }

    public void updateUserstory(Userstory userstory) {
        em.merge(userstory);
    }


}
