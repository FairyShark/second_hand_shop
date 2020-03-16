package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OperationMesDao;
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

		String uid = request.getParameter("userid");
		String userip = request.getParameter("userip");
		String email = request.getParameter("Email");
		String passwd = request.getParameter("Password");

		UserDao userDao;
		try {
			userDao = DAOFactory.getUserServiceInstance();
			if (userDao.editEmail(Integer.parseInt(uid), email)
					&& userDao.editPasswd(Integer.parseInt(uid), passwd)) {
				try {
					String uname = userDao.queryUName(Integer.parseInt(String.valueOf(session.getAttribute("uid"))));
					String opcontent = "�޸Ļ�Ա��Ϣ�����֣�" + userDao.queryUName(Integer.parseInt(uid)) + "��,���䣨" + email + "��,���루" + passwd + "��" ;
					OperationMesDao omdao = DAOFactory.getOperationMesServiceInstance();
					omdao.addOperationMes(Integer.parseInt(String.valueOf(session.getAttribute("uid"))), uname, userip, "�޸�", opcontent);
				} catch (Exception e) {
					e.printStackTrace();
				}
				response.sendRedirect(request.getContextPath() + "/" + "jsp/showMessage.jsp?uid=" + uid);

			}else {
				String truePath = request.getContextPath() + "/" + "jsp/showMessage.jsp?uid=" + uid;
				PrintWriter out = response.getWriter();
				out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
				out.println("<HTML>");
				out.println("  <HEAD><TITLE>�޸ĸ�����Ϣ</TITLE>");
				out.println("<meta http-equiv=\"refresh\" content=\"5;url=" + truePath
						+ "\">");
				out.println("</HEAD>");
				out.println("  <BODY>");
				out.print("<div align=\"center\">");
				out.print("�޸ĸ�����Ϣʧ��,�����ַ��ʹ�ã�");
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() throws ServletException {
		
	}

}
