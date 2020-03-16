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
        String uname = request.getParameter("UserName");
        String userip = request.getParameter("UserIP");
        String otype = request.getParameter("Otype");
        String landtime = request.getParameter("LandTime");

        try {
            if(uid==8) {
                String userip_t = request.getParameter("Userip");
                String opcontent = "��ѯ������־����ԱID��" + uid + "��,���֣�" + uname + "��,IP��ַ��" + userip + "��,�������ͣ�" + otype + "��,����ʱ�䣨" + landtime +"��" ;
                OperationMesDao omdao = DAOFactory.getOperationMesServiceInstance();
                omdao.addOperationMes(uid, uname, userip_t, "��ѯ", opcontent);
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
