package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;
import factory.DAOFactory;

@WebServlet("/EditCartGoodsServlet")
public class EditCartGoodsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCartGoodsServlet() {
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
            ShoppingCartDao sd = DAOFactory.getShoppingCartServiceInstance();
            GoodsDao gd = DAOFactory.getGoodsServiceInstance();
            int goods_number = gd.queryNumberById(gid);
            if(goods_number < number){
                String jsonStr = "{\"isok\":\"2\", \"number\": \"" + goods_number + "\"}";
                response.getWriter().print(jsonStr);
            }else {
                if (sd.editGoods(uid, gid, number)) {
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
