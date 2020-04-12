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
import dao.UserDao;
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
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int uid = Integer.parseInt(request.getParameter("UserID"));
        int opuid = Integer.parseInt(request.getParameter("Uid"));
        String uname = request.getParameter("UserName");
        int gid = Integer.parseInt(request.getParameter("GoodsID"));
        String gname = request.getParameter("GoodsName");
        String gtype = request.getParameter("Gtype");
        String landtime = request.getParameter("LandTime");

        try {
            if (opuid == 8) {
                String userip = request.getParameter("Userip");
                String opcontent = "查询浏览记录";
                if (uid != -1 || gid != -1 || !uname.equals("%&ALL&%")|| !gname.equals("%&ALL&%") || !gtype.equals("全部") || !landtime.equals("%&ALL&%")) {
                    opcontent += "：";
                }
                if (uid != -1) {
                    opcontent += "会员ID(" + uid + ") ";
                }
                if (!uname.equals("%&ALL&%")) {
                    opcontent += "会员名(" + uname + ") ";
                }
                if (gid != -1) {
                    opcontent += "商品ID(" + gid + ") ";
                }
                if (!gname.equals("%&ALL&%")) {
                    opcontent += "商品名(" + gname + ") ";
                }
                if (!gtype.equals("全部")) {
                    opcontent += "商品类型(" + gtype + ") ";
                }
                if (!landtime.equals("%&ALL&%")) {
                    opcontent += "浏览时间(" + landtime + ")";
                }
                OperationMesDao omdao = DAOFactory.getOperationMesServiceInstance();
                UserDao udao = DAOFactory.getUserServiceInstance();
                String opuname = udao.queryUName(opuid);
                omdao.addOperationMes(opuid, opuname, userip, "查询", opcontent);
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

        doGet(request, response);
    }

}
