package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.OperationMesDao;
import dao.UserDao;
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
		String userip = request.getParameter("userip");
		String uname = request.getParameter("uname");
		String passwd = request.getParameter("passwd");
		String email = request.getParameter("Email");
		String path = "jsp/register.jsp";
		String message = "注册失败，用户名或邮箱已被使用";
		try {
			if (resister(uname, passwd, email)) {
				message = "注册成功";
				path = "jsp/login.jsp";
				try {
					UserDao udao = DAOFactory.getUserServiceInstance();
					User user = new User();
					user = udao.queryByEmail(email);
					String opcontent = "注册会员信息:名字（" + uname + "）,邮箱（" + email + "）,密码（" + passwd + "）" ;
					OperationMesDao omdao = DAOFactory.getOperationMesServiceInstance();
					omdao.addOperationMes(user.getUid(), userip, opcontent);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String truePath = request.getContextPath() + "/" + path;
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>注册</TITLE>");
		out.println("<meta http-equiv=\"refresh\" content=\"5;url=" + truePath
				+ "\">");
		out.println("</HEAD>");
		out.println("  <BODY>");
		out.print("<div align=\"center\">");
		out.print(message);
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