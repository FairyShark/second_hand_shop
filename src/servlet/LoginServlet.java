package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import bean.User;
import dao.UserDao;
import factory.DAOFactory;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public LoginServlet() {
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
		User user = null;
		String message = "";
		String path = "jsp/login.jsp";
		try {
			UserDao dao = DAOFactory.getUserServiceInstance();
			if ((user = dao.queryByName(uname)) != null) {
				if (user.getPasswd().equals(passwd)) {
					String lastLoginTime = user.getLastLogin();
					dao.editLoginTime(user.getUid());
					request.getSession().setAttribute("uname", uname);
					request.getSession().setAttribute("uid",
							String.valueOf(user.getUid()));
					request.getSession().setAttribute("lastLoginTime",
							lastLoginTime);
					path = "jsp/index.jsp";
				} else {
					message = "密码错误，请重新输入";
				}
			} else {
				message = "不存在该用户，请重新输入";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String truePath = request.getContextPath() + "/" + path;
		if ("".equals(message)) {
			response.sendRedirect(truePath);
		} else {
			//JOptionPane.showMessageDialog(null, message, "抱歉", JOptionPane.ERROR_MESSAGE);
			response.sendRedirect(truePath);
		}
	}

	public void init() throws ServletException {
		
	}

}
