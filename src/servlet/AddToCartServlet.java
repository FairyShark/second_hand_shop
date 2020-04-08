package servlet;

import dao.*;
import factory.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        int uid = Integer.parseInt(request.getParameter("Uid"));
        int gid = Integer.parseInt(request.getParameter("Gid"));
        int number = Integer.parseInt(request.getParameter("Number"));
        try {
            GoodsDao gd = DAOFactory.getGoodsServiceInstance();
            if(gd.queryById(gid).getNumber() < number){
                String jsonStr = "{\"isok\":\"2\", \"number\": \"" + gd.queryById(gid).getNumber() + "\"}";
                response.getWriter().print(jsonStr);
            }else {
                ShoppingCartDao dao = DAOFactory.getShoppingCartServiceInstance();
                if (dao.addGoods(uid, gid, number)) {
                    UserDao ud = DAOFactory.getUserServiceInstance();
                    String uname = ud.queryUName(uid);
                    String gtype = gd.queryTypesByGid(gid);
                    UserTagDao utd = DAOFactory.getUserTagServiceInstance();
                    utd.addUserTag(uid, uname, "加入购物车", gtype, 3);
                    String jsonStr = "{\"isok\":\"1\"}";
                    response.getWriter().print(jsonStr);
                } else {
                    String jsonStr = "{\"isok\":\"0\"}";
                    response.getWriter().print(jsonStr);
                }
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

