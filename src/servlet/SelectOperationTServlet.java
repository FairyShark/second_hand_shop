package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.OperationMes;
import com.alibaba.fastjson.JSON;

import dao.OperationMesDao;
import dao.UserDao;
import factory.DAOFactory;

@WebServlet("/SelectOperationTServlet")
public class SelectOperationTServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectOperationTServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        int uid = Integer.parseInt(request.getParameter("UserID"));
        int opuid = Integer.parseInt(request.getParameter("Uid"));
        String uname = request.getParameter("UserName");
        String userip = request.getParameter("UserIP");
        String otype = request.getParameter("Otype");
        String landtime = request.getParameter("LandTime");

        try {
            if (opuid == 8) {
                String userip_t = request.getParameter("Userip");
                String opcontent = "查询操作日志";
                if (uid != -1 || !uname.equals("%&ALL&%") || !userip.equals("%&ALL&%") || !otype.equals("%&ALL&%") || !landtime.equals("%&ALL&%")) {
                    opcontent += "：";
                }
                if (uid != -1) {
                    opcontent += "会员ID(" + uid + ") ";
                }
                if (!uname.equals("%&ALL&%")) {
                    opcontent += "会员名(" + uname + ") ";
                }
                if (!userip.equals("%&ALL&%")) {
                    opcontent += "IP地址(" + userip + ") ";
                }
                if (!otype.equals("%&ALL&%")) {
                    opcontent += "操作类型(" + otype + ") ";
                }
                if (!landtime.equals("%&ALL&%")) {
                    opcontent += "操作时间(" + landtime + ")";
                }
                OperationMesDao omdao = DAOFactory.getOperationMesServiceInstance();
                UserDao udao = DAOFactory.getUserServiceInstance();
                String opuname = udao.queryUName(opuid);
                omdao.addOperationMes(opuid, opuname, userip_t, "查询", opcontent);
            }
            OperationMesDao omdao = DAOFactory.getOperationMesServiceInstance();
            List<OperationMes> list = omdao.getOperationMes(uid, uname, userip, otype, landtime);
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
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
