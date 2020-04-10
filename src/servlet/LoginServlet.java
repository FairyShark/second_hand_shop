package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.LandMessageDao;
import dao.UserDao;

import factory.DAOFactory;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String uname = request.getParameter("uname");
        String passwd = request.getParameter("passwd");
        String userip = request.getParameter("userip");
        User user;
        int uid;
        try {
            UserDao dao = DAOFactory.getUserServiceInstance();
            if ((user = dao.queryByName(uname)) != null) {
                if (user.getPasswd().equals(passwd)) {
                    String lastLoginTime = user.getLastLogin();
                    dao.editLoginTime(user.getUid());
                    request.getSession().setAttribute("uname", uname);
                    request.getSession().setAttribute("uid", String.valueOf(user.getUid()));
                    request.getSession().setAttribute("lastLoginTime", lastLoginTime);
                    uid = user.getUid();
                    LandMessageDao lmdao = DAOFactory.getLandMessageServiceInstance();
                    lmdao.addLandTimeMes(uid, uname, userip);
                    String landtime = lmdao.getLandtime(uid, userip);
                    request.getSession().setAttribute("userip", userip);
                    request.getSession().setAttribute("landtime", landtime);
                    if (user.getUname().equals("admin")) {
                        String jsonStr = "{\"isok\":\"4\"}";
                        response.getWriter().print(jsonStr);
                    }else {
                        String jsonStr = "{\"isok\":\"3\"}";
                        response.getWriter().print(jsonStr);
                    }
                } else {
                    String jsonStr = "{\"isok\":\"2\"}";
                    response.getWriter().print(jsonStr);
                }
            } else {
                String jsonStr = "{\"isok\":\"1\"}";
                response.getWriter().print(jsonStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

}
