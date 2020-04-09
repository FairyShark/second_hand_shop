package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.OperationMesDao;
import dao.UserDao;

import factory.DAOFactory;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userip = request.getParameter("userip");
        String uname = request.getParameter("uname");
        String passwd = request.getParameter("passwd");
        String email = request.getParameter("Email");
        User us = new User();
        us.setUname(uname);
        us.setPasswd(passwd);
        us.setEmail(email);
        try {
            if (DAOFactory.getUserServiceInstance().addUser(us)) {
                UserDao udao = DAOFactory.getUserServiceInstance();
                User user = udao.queryByEmail(email);
                String opcontent = "×¢²á»áÔ±ÐÅÏ¢£ºÃû×Ö£¨" + uname + "£©,ÓÊÏä£¨" + email + "£©,ÃÜÂë£¨" + passwd + "£©";
                OperationMesDao omdao = DAOFactory.getOperationMesServiceInstance();
                omdao.addOperationMes(user.getUid(), user.getUname(), userip, "×¢²á", opcontent);
                String jsonStr = "{\"isok\":\"1\"}";
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
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

}