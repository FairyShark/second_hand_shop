package servlet;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Goods;
import dao.UserDao;
import dao.UserTagDao;
import factory.DAOFactory;
import dao.GoodsDao;
import dao.OperationMesDao;

import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class SelectGoodsServlet
 */
@WebServlet("/SelectGoodsServlet")
public class SelectGoodsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        int opuid = Integer.parseInt(request.getParameter("Uid"));
        String gtype = request.getParameter("GoodsType");
        String usage = request.getParameter("GoodsUsage");
        float lowp = Float.parseFloat(request.getParameter("GoodsLowP"));
        float highp = Float.parseFloat(request.getParameter("GoodsHighP"));
        String gname = request.getParameter("GoodsName");
        GoodsDao goodsDao;
        List<Goods> list;
        try {
            UserDao udao = DAOFactory.getUserServiceInstance();
            String opuname = udao.queryUName(opuid);
            if (opuid == 8 || request.getParameter("OPT") != null) {
                String userip = request.getParameter("Userip");
                String opcontent = "查询商品信息";
                if (!gtype.equals("全部") || !usage.equals("全部") || lowp != 0 || highp != 214748364 || !gname.equals("%&ALL&%")) {
                    opcontent += "：";
                }
                if (!gtype.equals("全部")) {
                    opcontent += "类型（" + gtype + "） ";
                }
                if (!usage.equals("全部")) {
                    opcontent += "使用情况（" + usage + "） ";
                }
                if (lowp != 0) {
                    opcontent += "最低价格（" + lowp + "） ";
                }
                if (highp != 214748364) {
                    opcontent += "最高价格（" + highp + "） ";
                }
                if (!gname.equals("%&ALL&%")) {
                    opcontent += "关键词（" + gname + "）";
                }
                OperationMesDao omdao = DAOFactory.getOperationMesServiceInstance();
                omdao.addOperationMes(opuid, opuname, userip, "查询", opcontent);
            }
            if(opuid != 8 && !gtype.equals("全部")) {
                UserTagDao ud = DAOFactory.getUserTagServiceInstance();
                ud.addUserTag(opuid, opuname, "搜索", gtype, 3);
            }
            goodsDao = DAOFactory.getGoodsServiceInstance();
            list = goodsDao.selectGoodsList(opuid, gtype, usage, lowp, highp, gname);
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
