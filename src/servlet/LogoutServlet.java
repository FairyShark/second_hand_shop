package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		request.getSession().removeAttribute("uname");
		request.getSession().removeAttribute("uid");
		response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
	}

	public void init() throws ServletException {
		
	}

}
