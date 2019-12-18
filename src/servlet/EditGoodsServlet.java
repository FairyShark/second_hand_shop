package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import bean.Goods;
import dao.GoodsDao;
import factory.DAOFactory;

public class EditGoodsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public EditGoodsServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		String uid = String.valueOf(session.getAttribute("uid"));
		String gid = request.getParameter("Gid");
		String gname = request.getParameter("Gname");
		String number = request.getParameter("Number");
		String type = request.getParameter("Types");
		String usage = request.getParameter("Usage");
		String price = request.getParameter("Price");
		String carriage = request.getParameter("Carriage");
		String paddress = request.getParameter("Paddress");
		String described = request.getParameter("Described");
		
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

		GoodsDao goodsDao;
		try {
			Goods goods = new Goods(Integer.parseInt(gid), Integer.parseInt(uid),gname,Integer.parseInt(number),type,usage,Float.parseFloat(price),Float.parseFloat(carriage),paddress,described);
			goodsDao = DAOFactory.getGoodsServiceInstance();
			if (goodsDao.editInfo(goods)) {
				response.sendRedirect(request.getContextPath() + "/jsp/saleGoods.jsp");
				return;
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//JOptionPane.showMessageDialog(null, "修改商品失败，请重试！", "抱歉", JOptionPane.ERROR_MESSAGE);
		response.sendRedirect(request.getContextPath() + "/" + "jsp/saleGoods.jsp");
	}

	public void init() throws ServletException {
		
	}

}
