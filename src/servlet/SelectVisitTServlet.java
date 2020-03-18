package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.VisitMessage;
import com.alibaba.fastjson.JSON;

import dao.OperationMesDao;
import dao.VisitMessageDao;
import factory.DAOFactory;

@WebServlet("/SelectVisitTServlet")
public class SelectVisitTServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectVisitTServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        int uid = Integer.parseInt(request.getParameter("UserID"));
        String uname = request.getParameter("UserName");
        int gid = Integer.parseInt(request.getParameter("GoodsID"));
        String gname = request.getParameter("GoodsName");
        String gtype = request.getParameter("Gtype");
        String landtime = request.getParameter("LandTime");

        try {
            if (uid == 8) {
                String userip = request.getParameter("Userip");
                String opcontent = "查询操作日志：会员ID（" + uid + "）,会员名（" + uname + "）,商品ID（" + gid + "）,商品名（" + gname + "）,IP地址（" + userip + "）,操作类型（" + gtype + "）,操作时间（" + landtime + "）";
                OperationMesDao omdao = DAOFactory.getOperationMesServiceInstance();
                omdao.addOperationMes(uid, uname, userip, "查询", opcontent);
            }
            VisitMessageDao vmdao = DAOFactory.getVisitMessageServiceInstance();
            List<VisitMessage> list = vmdao.getVisitMessage(uid, gid, uname, gname, gtype, landtime);
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
