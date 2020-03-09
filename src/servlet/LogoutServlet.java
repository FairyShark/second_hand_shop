package servlet;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LandMessageDao;
import factory.DAOFactory;

public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public LogoutServlet() {
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
		int uid = Integer.parseInt(String.valueOf(request.getSession().getAttribute("uid")));
		String landtime = String.valueOf(request.getSession().getAttribute("landtime"));
		request.getSession().removeAttribute("uname");
		request.getSession().removeAttribute("uid");
		try {
			InetAddress ip4 = Inet4Address.getLocalHost();
			String userip = ip4.getHostAddress();
			LandMessageDao lmdao = DAOFactory.getLandMessageServiceInstance();
			lmdao.addCancelTimeMes(uid, userip, landtime);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
	}

	public void init() throws ServletException {
		
	}

}
