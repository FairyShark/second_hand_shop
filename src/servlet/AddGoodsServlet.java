package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Goods;
import dao.UserDao;
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
			UserDao userDao = DAOFactory.getUserServiceInstance();
			String uname = userDao.queryUName(Integer.parseInt(uid));
			if (addgoods(Integer.parseInt(uid), uname, gname, Integer.parseInt(number), photo, type, usage,
					Float.parseFloat(price), Float.parseFloat(carriage), paddress, described)) {
				message = 1;
				int gid = qugid(Integer.parseInt(uid), uname, gname, Integer.parseInt(number), photo, type, usage,
						Float.parseFloat(price), Float.parseFloat(carriage), paddress, described);
				request.getSession().setAttribute("uid", uid);
				request.getSession().setAttribute("gid", gid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (message == 0) {
			String truePath = request.getContextPath() + "/" + "jsp/addGoods.jsp";
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>发布商品</TITLE>");
			out.println("<meta http-equiv=\"refresh\" content=\"5;url=" + truePath
					+ "\">");
			out.println("</HEAD>");
			out.println("  <BODY>");
			out.print("<div align=\"center\">");
			out.print("发布商品失败，请重试！");
			out.print("<br/>");
			out.print("将自动跳转到相应页面");
			out.print("<br/>");
			out.print("或点击这里：");
			out.print("<a href=\"" + truePath + "\"" + ">返回" + "</a>");
			out.print("</div>");
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();

		}else{
			response.sendRedirect(request.getContextPath() + "/" + "jsp/addPhoto.jsp");
		}
	
	}

	public void init() throws ServletException {

	}

	public boolean addgoods(int uid, String uname, String gname, int number, String photo, String type, String usage, float price,
			float carriage, String paddress, String described) throws Exception {
		Goods goods = new Goods(uid, uname, gname, number, photo, type, usage, price, carriage, paddress, described);
		return DAOFactory.getGoodsServiceInstance().addGoods(goods);
	}

	public int qugid(int uid, String uname, String gname, int number, String photo, String type, String usage, float price,
			float carriage, String paddress, String described) throws Exception {
		Goods goods = new Goods(uid, uname, gname, number, photo, type, usage, price, carriage, paddress, described);
		return DAOFactory.getGoodsServiceInstance().queryGid(goods);
	}

}
