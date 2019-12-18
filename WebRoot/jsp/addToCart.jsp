<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="java.io.IOException"%>
<%@page import="factory.DAOFactory"%>
<%@page import="dao.ShoppingCartDao"%>
<%@page import="dao.GoodsDao"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>加入购物车</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<body>
	<%
		String strUid = (String) session.getAttribute("uid");
		int uid = 0;
		if (strUid != null) {
			uid = Integer.parseInt(strUid);
		}
		int gid = Integer.valueOf(request.getParameter("gid"));
		int number = Integer.valueOf(request.getParameter("buyNumber"));
		ShoppingCartDao dao = DAOFactory.getShoppingCartServiceInstance();
		GoodsDao goodsDao = DAOFactory.getGoodsServiceInstance();
		if (goodsDao.querySaleUid(gid) != uid) {
			boolean flag = dao.addGoods(uid, gid, number);
			if (flag) {
				response.sendRedirect("shoppingCart.jsp");
			} else {
				//JOptionPane.showMessageDialog(null, "添加到购物车失败，请重试！", "抱歉", JOptionPane.ERROR_MESSAGE);
				response.sendRedirect(request.getContextPath() + "/" + "jsp/index.jsp");
		}
		}else{
			//JOptionPane.showMessageDialog(null, "不能购买自己出售的商品，请重试！", "抱歉", JOptionPane.ERROR_MESSAGE);
			response.sendRedirect(request.getContextPath() + "/" + "jsp/index.jsp");
		}
	%>
</body>
</html>
