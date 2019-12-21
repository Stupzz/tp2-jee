package fr.usmb.javaee;

import fr.usmb.m2isc.javaee.comptes.ejb.BacklogEJB;
import fr.usmb.m2isc.javaee.comptes.ejb.UserstoryEJB;
import fr.usmb.m2isc.javaee.comptes.jpa.Backlog;
import fr.usmb.m2isc.javaee.comptes.jpa.Userstory;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@WebServlet(value = "/userstory", name = "userstory")
public class UserstoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private BacklogEJB ejbBacklog;

    @EJB
    private UserstoryEJB ejbUserstory;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserstoryServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        int id;
        Backlog backlog = null;
        int idUserstory;
        boolean sorti = false;

        if (action != null) {
            switch (action) {
                case "get":
                    idUserstory = Integer.parseInt(request.getParameter("idUserstory"));
                    request.setAttribute("userstory", ejbUserstory.getUserstory(idUserstory));
                    request.getRequestDispatcher("/userstory.jsp").forward(request, response);
                    sorti = true;
                    break;
                case "delete":

                    id = Integer.parseInt(request.getParameter("id"));
                    backlog = ejbBacklog.getBacklog(id);
                    idUserstory = Integer.parseInt(request.getParameter("idUserstory"));

                    for (int i = 0; i < backlog.getUserstories().size(); i++) {
                        if (backlog.getUserstories().get(i).getId() == idUserstory) {
                            backlog.getUserstories().remove(i);
                            ejbBacklog.updateBacklog(backlog);
                            break;
                        }
                    }
                    break;
                default:
                    break;
            }
        }

        if (!sorti) {
            id = Integer.parseInt(request.getParameter("id"));
            backlog = ejbBacklog.getBacklog(id);
            request.setAttribute("backlog", backlog);
            request.getRequestDispatcher("/backlog.jsp").forward(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int id = Integer.parseInt(request.getParameter("id"));
        String titre = request.getParameter("titre");

        Backlog backlog = ejbBacklog.getBacklog(id);
        Userstory userstory = new Userstory();
        userstory.setDate(new Date());
        userstory.setTitre(titre);
        userstory.setBacklog(backlog);

        backlog.getUserstories().add(userstory);

        ejbBacklog.updateBacklog(backlog);

        backlog = ejbBacklog.getBacklog(id);


        request.setAttribute("backlog", backlog);
        request.getRequestDispatcher("/backlog.jsp").forward(request, response);
    }
}
