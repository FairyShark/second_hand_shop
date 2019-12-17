package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import bean.User;
import factory.DAOFactory;

public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
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
		String uname = request.getParameter("uname");
		String passwd = request.getParameter("passwd");
		String email = request.getParameter("Email");
		String path = "jsp/register.jsp";
		String message = "注册失败，用户名或邮箱已被使用";
		try {
			if (resister(uname, passwd, email)) {
				message = "注册成功";
				path = "jsp/login.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String truePath = request.getContextPath() + "/" + path;
		JOptionPane.showMessageDialog(null, message);
		response.sendRedirect(truePath);
	}

	public void init() throws ServletException {
		
	}

	public boolean resister(String uname, String passwd, String email)
			throws Exception {
		User user = new User();
		user.setUname(uname);
		user.setPasswd(passwd);
		user.setEmail(email);
		return DAOFactory.getUserServiceInstance().addUser(user);
	}
}