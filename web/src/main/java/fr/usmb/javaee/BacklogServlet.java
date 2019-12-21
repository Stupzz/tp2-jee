package fr.usmb.javaee;

import fr.usmb.m2isc.javaee.comptes.ejb.AgenceEJB;
import fr.usmb.m2isc.javaee.comptes.ejb.BacklogEJB;
import fr.usmb.m2isc.javaee.comptes.jpa.Agence;
import fr.usmb.m2isc.javaee.comptes.jpa.Backlog;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@WebServlet(value = "/backlog", name = "backlog")
public class BacklogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private AgenceEJB ejbAgence;

    @EJB
    private BacklogEJB ejbBacklog;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BacklogServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        int idAgence = Integer.parseInt(request.getParameter("idAgence"));
        Agence agence = ejbAgence.getAgence(idAgence);


        if (action != null) {
            switch (action) {
                case "delete":
                    int id = agence.getBacklog().getId();
                    agence.setBacklog(null);
                    ejbAgence.updateAgence(agence);
                    ejbBacklog.supprimerBacklog(id);
                    break;
                default:
                    break;
            }
        }
        request.setAttribute("agence", agence);
        request.getRequestDispatcher("/agence.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int idAgence = Integer.parseInt(request.getParameter("idAgence"));
        String description = request.getParameter("description");
        int priorite = Integer.parseInt(request.getParameter("priorite"));
        int estimation = Integer.parseInt(request.getParameter("estimation"));

        Agence agence = ejbAgence.getAgence(idAgence);
        Backlog backlog = new Backlog();
        backlog.setDate(new Date());
        backlog.setPriorite(priorite);
        backlog.setDescription(description);
        backlog.setEstimation(estimation);

        agence.setBacklog(backlog);

        ejbAgence.updateAgence(agence);

        agence = ejbAgence.getAgence(idAgence);

        request.setAttribute("agence", agence);
        request.getRequestDispatcher("/agence.jsp").forward(request, response);
    }


}
