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
import dao.GoodsDao;
import dao.OperationMesDao;
import dao.UserDao;

import factory.DAOFactory;

@WebServlet("/EditGoodsServlet")
public class EditGoodsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String uid = String.valueOf(session.getAttribute("uid"));

        String userip = request.getParameter("userip");
        String gid = request.getParameter("Gid");
        String gname = request.getParameter("Gname");
        String number = request.getParameter("Number");
        String type = request.getParameter("Types");
        String usage = request.getParameter("Usage");
        String price = request.getParameter("Price");
        String carriage = request.getParameter("Carriage");
        String paddress = request.getParameter("Paddress");
        String described = request.getParameter("Described");

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

        GoodsDao goodsDao;
        try {
            UserDao userDao = DAOFactory.getUserServiceInstance();
            goodsDao = DAOFactory.getGoodsServiceInstance();
            String uname = goodsDao.queryById(Integer.parseInt(gid)).getUname();
            Goods goods = new Goods();
            //(Integer.parseInt(gid), Integer.parseInt(uid), uname, gname, Integer.parseInt(number), type, usage, Float.parseFloat(price), Float.parseFloat(carriage), paddress, described);
            goods.setGid(Integer.parseInt(gid));
            goods.setUid(Integer.parseInt(uid));
            goods.setUname(uname);
            goods.setGname(gname);
            goods.setNumber(Integer.parseInt(number));
            goods.setType(type);
            goods.setUsage(usage);
            goods.setPrice(Float.parseFloat(price));
            goods.setCarriage(Float.parseFloat(carriage));
            goods.setPaddress(paddress);
            goods.setDescribed(described);
            if (goodsDao.editInfo(goods)) {
                String uname_t = userDao.queryUName(Integer.parseInt(uid));
                String opcontent = "修改商品：商品名（" + gname + "）,价格（" + price + "）,库存（" + number + "）,运费（" + carriage + "）,类型（" + type + "）,使用情况（" + usage + "）,发货地（" + paddress + "）,描述（" + described + "）";
                OperationMesDao omdao = DAOFactory.getOperationMesServiceInstance();
                omdao.addOperationMes(Integer.parseInt(uid), uname_t, userip, "修改", opcontent);
                String jsonStr = "{\"isok\":\"1\", \"gid\": \"" + gid + "\"}";
                response.getWriter().print(jsonStr);
            }else {
                String jsonStr = "{\"isok\":\"0\"}";
                response.getWriter().print(jsonStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

}

