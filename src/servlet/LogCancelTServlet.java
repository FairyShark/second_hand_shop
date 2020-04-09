package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.LandMessageDao;
import factory.DAOFactory;

@WebServlet("/LogCancelTServlet")
public class LogCancelTServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogCancelTServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        if (request.getSession().getAttribute("uid") == null) return;
        int uid = Integer.parseInt(String.valueOf(request.getSession().getAttribute("uid")));
        String userip = String.valueOf(request.getSession().getAttribute("userip"));
        String landtime = String.valueOf(request.getSession().getAttribute("landtime"));
        try {
            LandMessageDao lmdao = DAOFactory.getLandMessageServiceInstance();
            lmdao.addCancelTimeMes(uid, userip, landtime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

}

