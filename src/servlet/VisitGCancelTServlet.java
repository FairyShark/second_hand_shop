package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GoodsDao;
import dao.UserDao;
import dao.UserTagDao;
import dao.VisitMessageDao;
import factory.DAOFactory;


@WebServlet("/VisitGCancelTServlet")
public class VisitGCancelTServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisitGCancelTServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        int uid = Integer.parseInt(request.getParameter("uid"));
        int gid = Integer.parseInt(request.getParameter("gid"));
        String landtime = request.getParameter("landtime");
        try {
            if (uid != 8) {
                VisitMessageDao vmdao = DAOFactory.getVisitMessageServiceInstance();
                vmdao.addCancelTimeMes(uid, gid, landtime);
                int visittime = vmdao.queryLasttime(uid, gid, landtime);
                int weight;
                if (visittime < 10) {
                    weight = 1;
                } else if (visittime < 30) {
                    weight = 2;
                } else if (visittime < 60) {
                    weight = 3;
                } else {
                    weight = 4;
                }
                UserDao ud = DAOFactory.getUserServiceInstance();
                GoodsDao gd = DAOFactory.getGoodsServiceInstance();
                String uname = ud.queryUName(uid);
                String gtype = gd.queryTypesByGid(gid);
                UserTagDao utd = DAOFactory.getUserTagServiceInstance();
                utd.addUserTag(uid, uname, "ä¯ÀÀ", gtype, weight);
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
