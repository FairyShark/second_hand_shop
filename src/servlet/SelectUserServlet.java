package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import bean.User;
import dao.OperationMesDao;
import dao.UserDao;
import factory.DAOFactory;

/**
 * Servlet implementation class SelectUserServlet
 */
@WebServlet("/SelectUserServlet")
public class SelectUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectUserServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String umail = request.getParameter("UserMail");
        String uname = request.getParameter("UserName");
        int uid = Integer.parseInt(request.getParameter("UserID"));
        int opuid = Integer.parseInt(request.getParameter("Uid"));

        UserDao udao;
        List<User> list;
        try {
            udao = DAOFactory.getUserServiceInstance();
            if (opuid == 8) {
                String userip = request.getParameter("Userip");
                String opcontent = "查询会员信息";
                if (uid != -1 || !uname.equals("%&ALL&%") || !umail.equals("%&ALL&%")) {
                    opcontent += "：";
                }
                if (uid != -1) {
                    opcontent += "会员ID(" + uid + ") ";
                }
                if (!uname.equals("%&ALL&%")) {
                    opcontent += "会员名(" + uname + ") ";
                }
                if (!umail.equals("%&ALL&%")) {
                    opcontent += "邮箱(" + umail + ") ";
                }
                OperationMesDao omdao = DAOFactory.getOperationMesServiceInstance();
                String opuname = udao.queryUName(opuid);
                omdao.addOperationMes(opuid, opuname, userip, "查询", opcontent);
            }
            list = udao.selectUserList(uid, uname, umail);
            PrintWriter out = response.getWriter();
            out.write(JSON.toJSONString(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
