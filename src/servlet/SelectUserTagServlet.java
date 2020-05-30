package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserTag;
import com.alibaba.fastjson.JSON;

import dao.OperationMesDao;
import dao.UserDao;
import dao.UserTagDao;
import factory.DAOFactory;

@WebServlet("/SelectUserTagServlet")
public class SelectUserTagServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectUserTagServlet() {
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
        String acttype = request.getParameter("ActType");
        String goodstype = request.getParameter("GodType");
        String tagtime = request.getParameter("TagTime");

        try {
            if (opuid == 8) {
                String userip_t = request.getParameter("Userip");
                String opcontent = "查询行为标签";
                if (uid != -1 || !uname.equals("%&ALL&%") || !acttype.equals("全部") || !goodstype.equals("全部") || !tagtime.equals("%&ALL&%")) {
                    opcontent += "：";
                }
                if (uid != -1) {
                    opcontent += "会员ID(" + uid + ") ";
                }
                if (!uname.equals("%&ALL&%")) {
                    opcontent += "会员名(" + uname + ") ";
                }
                if (!acttype.equals("%&ALL&%")) {
                    opcontent += "行为类型(" + acttype + ") ";
                }
                if (!goodstype.equals("%&ALL&%")) {
                    opcontent += "商品类型(" + goodstype + ") ";
                }
                if (!tagtime.equals("%&ALL&%")) {
                    opcontent += "标记时间(" + tagtime + ")";
                }
                OperationMesDao omdao = DAOFactory.getOperationMesServiceInstance();
                UserDao udao = DAOFactory.getUserServiceInstance();
                String opuname = udao.queryUName(opuid);
                omdao.addOperationMes(opuid, opuname, userip_t, "查询", opcontent);
            }
            UserTagDao utdao = DAOFactory.getUserTagServiceInstance();
            List<UserTag> list = utdao.getUserTag(uid, uname, acttype, goodstype, tagtime);
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
