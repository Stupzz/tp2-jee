package fr.usmb.javaee;

import fr.usmb.m2isc.javaee.comptes.ejb.CommentaireEJB;
import fr.usmb.m2isc.javaee.comptes.ejb.UserstoryEJB;
import fr.usmb.m2isc.javaee.comptes.jpa.Commentaire;
import fr.usmb.m2isc.javaee.comptes.jpa.Userstory;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@WebServlet(value = "/commentaire", name = "commentaire")
public class CommentaireServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private CommentaireEJB ejbCommentaire;

    @EJB
    private UserstoryEJB ejbUserstory;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentaireServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        int id = Integer.parseInt(request.getParameter("id"));
        Userstory userstory = ejbUserstory.getUserstory(id);


        if (action != null) {

            int idComment = Integer.parseInt(request.getParameter("idComment"));
            for (int i = 0; i < userstory.getCommentaires().size(); i++) {
                if (userstory.getCommentaires().get(i).getId() == idComment) {
                    userstory.getCommentaires().remove(i);
                    ejbUserstory.updateUserstory(userstory);
                    break;
                }
            }
        }
        request.setAttribute("userstory", userstory);
        request.getRequestDispatcher("/userstory.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");
        int id = Integer.parseInt(request.getParameter("id"));
        Userstory userstory = ejbUserstory.getUserstory(id);
        Commentaire commentaire = new Commentaire();
        commentaire.setDescription(description);
        commentaire.setDate(new Date());
        commentaire.setAuteur(request.getRemoteUser());
        commentaire.setUserstory(userstory);
        commentaire = ejbCommentaire.creerCommentaire(commentaire);
        userstory.getCommentaires().add(commentaire);
        ejbUserstory.updateUserstory(userstory);

        userstory = ejbUserstory.getUserstory(id);

        request.setAttribute("userstory", userstory);
        request.getRequestDispatcher("/userstory.jsp").forward(request, response);
    }


}
