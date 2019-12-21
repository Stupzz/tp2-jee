package fr.usmb.javaee;

import fr.usmb.m2isc.javaee.comptes.ejb.AgenceEJB;
import fr.usmb.m2isc.javaee.comptes.jpa.Agence;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = "/agence", name = "agence")
public class AgenceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private AgenceEJB ejbAgence;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgenceServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        int idAgence;


        if (action != null) {
            switch (action) {
                case "get":
                    idAgence = Integer.parseInt(request.getParameter("idAgence"));
                    request.setAttribute("agence", ejbAgence.getAgence(idAgence));
                    request.getRequestDispatcher("/agence.jsp").forward(request, response);
                    break;
                case "delete":
                    idAgence = Integer.parseInt(request.getParameter("idAgence"));
                    ejbAgence.supprimerAgence(idAgence);
                    request.setAttribute("agences", ejbAgence.getAll());
                    request.getRequestDispatcher("/agences.jsp").forward(request, response);
                    break;
                default:
                    request.setAttribute("agences", ejbAgence.getAll());
                    request.getRequestDispatcher("/agences.jsp").forward(request, response);
                    break;
            }
        } else {
            request.setAttribute("agences", ejbAgence.getAll());
            request.getRequestDispatcher("/agences.jsp").forward(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String nom = request.getParameter("nom");

        Agence agence = new Agence();
        agence.setNom(nom);

        ejbAgence.creerAgence(agence);

        request.setAttribute("agences", ejbAgence.getAll());
        request.getRequestDispatcher("/agences.jsp").forward(request, response);
    }
}
