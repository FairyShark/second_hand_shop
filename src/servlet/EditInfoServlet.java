package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import dao.UserDao;
import factory.DAOFactory;

public class EditInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public EditInfoServlet() {
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

		String email = request.getParameter("Email");
		String passwd = request.getParameter("Password");

		UserDao userDao;
		try {
			userDao = DAOFactory.getUserServiceInstance();
			if (userDao.editEmail(Integer.parseInt(uid), email)
					&& userDao.editPasswd(Integer.parseInt(uid), passwd)) {
				response.sendRedirect(request.getContextPath() + "/" + "jsp/showMessage.jsp");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "修改个人信息失败,邮箱地址已使用！", "抱歉", JOptionPane.ERROR_MESSAGE);
		response.sendRedirect(request.getContextPath() + "/" + "jsp/showMessage.jsp");
	}

	public void init() throws ServletException {
		
	}

}
