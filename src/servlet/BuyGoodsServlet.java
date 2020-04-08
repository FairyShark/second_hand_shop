package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ShoppingCart;
import dao.*;
import factory.DAOFactory;

@WebServlet("/BuyGoodsServlet")
public class BuyGoodsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        int uid = Integer.parseInt(request.getParameter("Uid"));
        try {
            int flag = 0;
            ShoppingCartDao scDao = DAOFactory.getShoppingCartServiceInstance();
            GoodsDao gd = DAOFactory.getGoodsServiceInstance();
            UserDao ud = DAOFactory.getUserServiceInstance();
            UserTagDao utd = DAOFactory.getUserTagServiceInstance();
            String uname = ud.queryUName(uid);
            List<ShoppingCart> sclist = scDao.getAllGoods(uid);
            for(int i = 0; i < sclist.size(); i++){
                if(gd.queryById(sclist.get(i).getGid()).getNumber() < sclist.get(i).getNumber()){
                    flag = 1;
                }
            }
            if(flag==0) {
                for (int i = 0; i < sclist.size(); i++) {
                    String gtype = gd.queryTypesByGid(sclist.get(i).getGid());
                    utd.addUserTag(uid, uname, "¹ºÂò", gtype, 8);
                }
                if (scDao.payAllGoods(uid)) {
                    String jsonStr = "{\"isok\":\"1\"}";
                    response.getWriter().print(jsonStr);
                } else {
                    String jsonStr = "{\"isok\":\"0\"}";
                    response.getWriter().print(jsonStr);
                }
            }else {
                String jsonStr = "{\"isok\":\"2\"}";
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
