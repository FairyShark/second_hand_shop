package servlet;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Goods;
import factory.DAOFactory;
import dao.GoodsDao;

import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class SelectGoodsServlet
 */
@WebServlet("/SelectGoodsServlet")
public class SelectGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int uid=Integer.parseInt(request.getParameter("Uid"));
		String gtype=request.getParameter("GoodsType");
		String usage=request.getParameter("GoodsUsage");
		int lowp=Integer.parseInt(request.getParameter("GoodsLowP"));
		int highp=Integer.parseInt(request.getParameter("GoodsHighP"));
		String gname=request.getParameter("GoodsName");
		GoodsDao goodsDao;
		List<Goods> list;
		try {
			goodsDao = DAOFactory.getGoodsServiceInstance();
			list = goodsDao.selectGoodsList(uid,gtype,usage,lowp,highp,gname);
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
