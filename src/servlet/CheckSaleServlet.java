package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AlreadySale;

import dao.AlreadySaleDao;
import factory.DAOFactory;

@WebServlet("/CheckSaleServlet")
public class CheckSaleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckSaleServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int uid = Integer.parseInt(request.getParameter("UID"));
        try {
            AlreadySaleDao dao = DAOFactory.getAlreadySaleServiceInstance();
            List<AlreadySale> asList = dao.getAllSaleGoods(uid);
            if (asList != null) {
                if (asList.size() > 0) {
                    String jsonStr = "{\"isok\":\"1\"}";
                    response.getWriter().print(jsonStr);
                } else {
                    String jsonStr = "{\"isok\":\"0\"}";
                    response.getWriter().print(jsonStr);
                }
            }else{
                String jsonStr = "{\"isok\":\"0\"}";
                response.getWriter().print(jsonStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }

}
