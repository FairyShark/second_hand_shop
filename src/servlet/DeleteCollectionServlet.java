package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CollectionDao;
import dao.GoodsDao;
import dao.UserDao;
import dao.UserTagDao;
import factory.DAOFactory;

@WebServlet("/DeleteCollectionServlet")
public class DeleteCollectionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCollectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        int uid = Integer.parseInt(request.getParameter("Uid"));
        int gid = Integer.parseInt(request.getParameter("Gid"));
        try {
            CollectionDao cd = DAOFactory.getCollectionServiceInstance();
            if (cd.deleteCollectionGoods(uid, gid)) {
                UserDao ud = DAOFactory.getUserServiceInstance();
                GoodsDao gd = DAOFactory.getGoodsServiceInstance();
                String uname = ud.queryUName(uid);
                String gtype = gd.queryTypesByGid(gid);
                UserTagDao utd = DAOFactory.getUserTagServiceInstance();
                utd.addUserTag(uid, uname, "»°œ˚ ’≤ÿ", gtype, -5);
                String jsonStr = "{\"isok\":\"1\", \"counts\": \"" + cd.getCount(gid) + "\"}";
                response.getWriter().print(jsonStr);
            } else {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
