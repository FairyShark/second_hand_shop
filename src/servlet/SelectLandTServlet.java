package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.LandMessage;
import com.alibaba.fastjson.JSON;

import dao.LandMessageDao;
import dao.OperationMesDao;
import dao.UserDao;
import factory.DAOFactory;

@WebServlet("/SelectLandTServlet")
public class SelectLandTServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectLandTServlet() {
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
        String landtime = request.getParameter("LandTime");

        try {
            if (opuid == 8) {
                String userip_t = request.getParameter("Userip");
                String opcontent = "��ѯ��½��Ϣ";
                if (uid != -1 || !uname.equals("%&ALL&%") || !userip.equals("%&ALL&%") || !landtime.equals("%&ALL&%")) {
                    opcontent += "��";
                }
                if (uid != -1) {
                    opcontent += "��ԱID��" + uid + ") ";
                }
                if (!uname.equals("%&ALL&%")) {
                    opcontent += "��Ա��(" + uname + ") ";
                }
                if (!userip.equals("%&ALL&%")) {
                    opcontent += "IP��ַ(" + userip + ") ";
                }
                if (!landtime.equals("%&ALL&%")) {
                    opcontent += "��½ʱ��(" + landtime + ")";
                }
                OperationMesDao omdao = DAOFactory.getOperationMesServiceInstance();
                UserDao udao = DAOFactory.getUserServiceInstance();
                String opuname = udao.queryUName(opuid);
                omdao.addOperationMes(opuid, opuname, userip_t, "��ѯ", opcontent);
            }
            LandMessageDao lmdao = DAOFactory.getLandMessageServiceInstance();
            List<LandMessage> list = lmdao.getLandMessage(uid, uname, userip, landtime);
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
