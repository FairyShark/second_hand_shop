package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			type = "����";
		if (usage == "" || usage == null)
			usage = "δ֪";
		if (paddress == "" || paddress == null)
			paddress = "δ֪";
		if (described == "" || described == null)
			described = "������ϸ��Ϣ";

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
		String truePath = request.getContextPath() + "/" + "jsp/saleGoods.jsp";
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>�޸���Ʒ</TITLE>");
		out.println("<meta http-equiv=\"refresh\" content=\"5;url=" + truePath
				+ "\">");
		out.println("</HEAD>");
		out.println("  <BODY>");
		out.print("<div align=\"center\">");
		out.print("�޸���Ʒʧ�ܣ������ԣ�");
		out.print("<br/>");
		out.print("���Զ���ת����Ӧҳ��");
		out.print("<br/>");
		out.print("�������");
		out.print("<a href=\"" + truePath + "\"" + ">����" + "</a>");
		out.print("</div>");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		
	}

}
