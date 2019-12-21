package fr.usmb.m2isc.javaee.comptes.ejb;

import fr.usmb.m2isc.javaee.comptes.jpa.Commentaire;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class CommentaireEJB {

    @PersistenceContext
    private EntityManager em;

    public CommentaireEJB() {
    }

    public Commentaire creerCommentaire(Commentaire commentaire) {
        em.persist(commentaire);
        return commentaire;
    }

    public Commentaire getCommentaire(int id) {
        return em.find(Commentaire.class, id);
    }


    public void supprimerCommentaire(int id) {
        em.remove(getCommentaire(id));
    }

    public void updateCommentaire(Commentaire commentaire) {
        em.merge(commentaire);
    }


}
