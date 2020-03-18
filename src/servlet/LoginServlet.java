package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.LandMessageDao;
import dao.UserDao;
import factory.DAOFactory;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    public void destroy() {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String uname = request.getParameter("uname");
        String passwd = request.getParameter("passwd");
        String userip = request.getParameter("userip");
        User user = null;
        int uid = 0;
        String message = "";
        String path = "jsp/login.jsp";
        try {
            UserDao dao = DAOFactory.getUserServiceInstance();
            if ((user = dao.queryByName(uname)) != null) {
                if (user.getPasswd().equals(passwd)) {
                    String lastLoginTime = user.getLastLogin();
                    dao.editLoginTime(user.getUid());
                    request.getSession().setAttribute("uname", uname);
                    request.getSession().setAttribute("uid",
                            String.valueOf(user.getUid()));
                    request.getSession().setAttribute("lastLoginTime",
                            lastLoginTime);
                    uid = user.getUid();
                    if (user.getUname().equals("admin")) {
                        path = "jsp/adminUser.jsp";
                    } else {
                        path = "jsp/index.jsp";
                    }
                } else {
                    message = "密码错误，请重新输入";
                }
            } else {
                message = "不存在该用户，请重新输入";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String truePath = request.getContextPath() + "/" + path;
        if ("".equals(message)) {
            try {
                LandMessageDao lmdao = DAOFactory.getLandMessageServiceInstance();
                lmdao.addLandTimeMes(uid, uname, userip);
                String landtime = lmdao.getLandtime(uid, userip);
                request.getSession().setAttribute("userip", userip);
                request.getSession().setAttribute("landtime", landtime);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect(truePath);
        } else {
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
            out.println("<HTML>");
            out.println("  <HEAD><TITLE>登录出错</TITLE>");
            out.println("<meta http-equiv=\"refresh\" content=\"5;url="
                    + truePath + "\">");
            out.println("</HEAD>");
            out.println("  <BODY>");
            out.print("<div align=\"center\">");
            out.print(message);
            out.print("<br/>");
            out.print("将自动跳转到登录页面");
            out.print("<br/>");
            out.print("或点击这里返回：");
            out.print("<a href=\"" + truePath + "\"" + ">登录" + "</a>");
            out.print("</div>");
            out.println("  </BODY>");
            out.println("</HTML>");
            out.flush();
            out.close();
        }
    }

    public void init() throws ServletException {

    }

}
