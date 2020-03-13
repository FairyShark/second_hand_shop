package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import bean.User;
import dao.OperationMesDao;
import dao.UserDao;
import factory.DAOFactory;

/**
 * Servlet implementation class SelectUserServlet
 */
@WebServlet("/SelectUserServlet")
public class SelectUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String umail=request.getParameter("UserMail");
		String uname=request.getParameter("UserName");
		int uid=Integer.parseInt(request.getParameter("UserID"));
	
		UserDao goodsDao;
		List<User> list;
		try {
			if(uid==8) {
				String userip=request.getParameter("Userip");
				System.out.println(userip);
				String opcontent = "查询会员：会员ID（" + uid + "）,名字（" + uname + "）,邮箱（" + umail +"）" ;
				OperationMesDao omdao = DAOFactory.getOperationMesServiceInstance();
				omdao.addOperationMes(uid, userip, opcontent);
			}
			goodsDao = DAOFactory.getUserServiceInstance();
			list = goodsDao.selectUserList(uid,uname,umail);
			PrintWriter out=response.getWriter();
			out.write(JSON.toJSONString(list));
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
