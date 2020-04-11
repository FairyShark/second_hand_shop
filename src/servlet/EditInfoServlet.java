package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OperationMesDao;
import dao.UserDao;

import factory.DAOFactory;

@WebServlet("/EditInfoServlet")
public class EditInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        String uid = request.getParameter("userid");
        String userip = request.getParameter("userip");
        String email = request.getParameter("Email");
        String passwd = request.getParameter("Password");
        String sex = request.getParameter("Sex");
        String birth = request.getParameter("Birth");
        String tel = request.getParameter("Tel");
        String address = request.getParameter("Addr");

        UserDao userDao;
        try {
            userDao = DAOFactory.getUserServiceInstance();
            if (userDao.editEmail(Integer.parseInt(uid), email)
                    && userDao.editPasswd(Integer.parseInt(uid), passwd) && userDao.editUser(Integer.parseInt(uid), sex, tel, birth, address)) {
                String uname = userDao.queryUName(Integer.parseInt(String.valueOf(session.getAttribute("uid"))));
                String opcontent = "修改会员信息：名字（" + userDao.queryUName(Integer.parseInt(uid)) + "）,邮箱（" + email + "）,密码（" + passwd + "）,性别（"+ sex + "）,出生日期（"+ birth + "）,手机（"+ tel + "）,地址（" + address + "）";
                OperationMesDao omdao = DAOFactory.getOperationMesServiceInstance();
                omdao.addOperationMes(Integer.parseInt(String.valueOf(session.getAttribute("uid"))), uname, userip, "修改", opcontent);
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