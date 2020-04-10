package servlet;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Goods;
import dao.OperationMesDao;
import dao.UserDao;

import factory.DAOFactory;

@WebServlet("/AddGoodsServlet")
public class AddGoodsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        String uid = String.valueOf(session.getAttribute("uid"));

        String userip = request.getParameter("userip");
        String gname = request.getParameter("Gname");
        String price = request.getParameter("Price");
        String number = request.getParameter("Number");
        String carriage = request.getParameter("Carriage");
        String type = request.getParameter("Types");
        String usage = request.getParameter("Usage");
        String paddress = request.getParameter("Paddress");
        String described = request.getParameter("Described");

        String photo = "nophoto.jpg";
        if (Objects.equals(number, "") || number == null)
            number = "1";
        if (Objects.equals(carriage, "") || carriage == null)
            carriage = "0";
        if (Objects.equals(type, "") || type == null)
            type = "其它";
        if (Objects.equals(usage, "") || usage == null)
            usage = "未知";
        if (Objects.equals(paddress, "") || paddress == null)
            paddress = "未知";
        if (Objects.equals(described, "") || described == null)
            described = "暂无详细信息";

        try {
            UserDao userDao = DAOFactory.getUserServiceInstance();
            String uname = userDao.queryUName(Integer.parseInt(uid));
            if (addgoods(Integer.parseInt(uid), uname, gname, Integer.parseInt(number), photo, type, usage,
                    Float.parseFloat(price), Float.parseFloat(carriage), paddress, described)) {
                int gid = qugid(Integer.parseInt(uid), uname, gname, Integer.parseInt(number), photo, type, usage,
                        Float.parseFloat(price), Float.parseFloat(carriage), paddress, described);
                String opcontent = "添加商品：商品名（" + gname + "）,价格（" + price + "）,库存（" + number + "）,运费（" + carriage + "）,类型（" + type + "）,使用情况（" + usage + "）,发货地（" + paddress + "）,描述（" + described + "）";
                OperationMesDao omdao = DAOFactory.getOperationMesServiceInstance();
                omdao.addOperationMes(Integer.parseInt(uid), uname, userip, "添加", opcontent);
                String jsonStr = "{\"isok\":\"1\", \"gid\": \"" + gid + "\"}";
                response.getWriter().print(jsonStr);
            } else {
                String jsonStr = "{\"isok\":\"0\"}";
                response.getWriter().print(jsonStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addgoods(int uid, String uname, String gname, int number, String photo, String type, String usage, float price,
                            float carriage, String paddress, String described) throws Exception {
        Goods goods = new Goods();
        //(uid, uname, gname, number, photo, type, usage, price, carriage, paddress, described);
        goods.setUid(uid);
        goods.setUname(uname);
        goods.setGname(gname);
        goods.setNumber(number);
        goods.setPhoto(photo);
        goods.setType(type);
        goods.setUsage(usage);
        goods.setPrice(price);
        goods.setCarriage(carriage);
        goods.setPaddress(paddress);
        goods.setDescribed(described);
        return DAOFactory.getGoodsServiceInstance().addGoods(goods);
    }

    public int qugid(int uid, String uname, String gname, int number, String photo, String type, String usage, float price,
                     float carriage, String paddress, String described) throws Exception {
        Goods goods = new Goods();
        //(uid, uname, gname, number, photo, type, usage, price, carriage, paddress, described);
        goods.setUid(uid);
        goods.setUname(uname);
        goods.setGname(gname);
        goods.setNumber(number);
        goods.setPhoto(photo);
        goods.setType(type);
        goods.setUsage(usage);
        goods.setPrice(price);
        goods.setCarriage(carriage);
        goods.setPaddress(paddress);
        goods.setDescribed(described);
        return DAOFactory.getGoodsServiceInstance().queryGid(goods);
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

}
