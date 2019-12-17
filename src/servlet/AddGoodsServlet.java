package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import bean.Goods;
import factory.DAOFactory;

public class AddGoodsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public AddGoodsServlet() {
		super();
	}

	public void destroy() {
		super.destroy();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		String uid = String.valueOf(session.getAttribute("uid"));

		String gname = request.getParameter("Gname");
		String price = request.getParameter("Price");
		String number = request.getParameter("Number");
		String carriage = request.getParameter("Carriage");
		String type = request.getParameter("Types");
		String usage = request.getParameter("Usage");
		String paddress = request.getParameter("Paddress");
		String described = request.getParameter("Described");

		String photo = "nophoto.jpg";
		if (number == "" || number == null)
			number = "1";
		if (carriage == "" || carriage == null)
			carriage = "0";
		if (type == "" || type == null)
			type = "其它";
		if (usage == "" || usage == null)
			usage = "未知";
		if (paddress == "" || paddress == null)
			paddress = "未知";
		if (described == "" || described == null)
			described = "暂无详细信息";

		int message = 0;

		try {
			if (addgoods(Integer.parseInt(uid), gname, Integer.parseInt(number), photo, type, usage,
					Float.parseFloat(price), Float.parseFloat(carriage), paddress, described)) {
				message = 1;
				int gid = qugid(Integer.parseInt(uid), gname, Integer.parseInt(number), photo, type, usage,
						Float.parseFloat(price), Float.parseFloat(carriage), paddress, described);
				request.getSession().setAttribute("uid", uid);
				request.getSession().setAttribute("gid", gid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (message == 0) {
			JOptionPane.showMessageDialog(null, "发布商品失败，请重试！", "抱歉", JOptionPane.ERROR_MESSAGE);
			response.sendRedirect(request.getContextPath() + "/" + "jsp/addGoods.jsp");
		}else{
			response.sendRedirect(request.getContextPath() + "/" + "jsp/addPhoto.jsp");
		}
	
	}

	public void init() throws ServletException {

	}

	public boolean addgoods(int uid, String gname, int number, String photo, String type, String usage, float price,
			float carriage, String paddress, String described) throws Exception {
		Goods goods = new Goods(uid, gname, number, photo, type, usage, price, carriage, paddress, described);
		return DAOFactory.getGoodsServiceInstance().addGoods(goods);
	}

	public int qugid(int uid, String gname, int number, String photo, String type, String usage, float price,
			float carriage, String paddress, String described) throws Exception {
		Goods goods = new Goods(uid, gname, number, photo, type, usage, price, carriage, paddress, described);
		return DAOFactory.getGoodsServiceInstance().queryGid(goods);
	}

}
