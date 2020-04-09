package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Goods;
import dao.*;
import factory.DAOFactory;

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        int uid = Integer.parseInt(request.getParameter("Uid"));
        String userip = request.getParameter("UserIP");
        try {
            UserDao userDao = DAOFactory.getUserServiceInstance();
            GoodsDao goodsDao = DAOFactory.getGoodsServiceInstance();
            AlreadyBuyDao abDao = DAOFactory.getAlreadyBuyServiceInstance();
            AlreadySaleDao asDao = DAOFactory.getAlreadySaleServiceInstance();
            List<Goods> uidGoodsList = goodsDao.getUidGoodsList(uid);
            if (userDao.deleteUser(uid)) {
                StringBuilder opcontent = new StringBuilder("ɾ����Ա����������Ʒ:��ԱID��" + uid + "��");

                if (uidGoodsList != null) {
                    if (uidGoodsList.size() > 0) {
                        Goods goods;
                        int gid;
                        opcontent.append("�� ͬʱɾ������Ʒ��");
                        for (Goods value : uidGoodsList) {
                            goods = value;
                            gid = goods.getGid();
                            goodsDao.deleteGoods(gid);
                            abDao.deleteBuyGoods(gid);
                            asDao.deleteSaleGoods(gid);
                            opcontent.append("��ƷID��").append(gid).append(");");
                        }
                    }
                }
                OperationMesDao omdao = DAOFactory.getOperationMesServiceInstance();
                omdao.addOperationMes(8, "admin", userip, "ɾ��", opcontent.toString());
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
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
