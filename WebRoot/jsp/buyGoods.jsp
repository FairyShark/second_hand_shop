<%@page import="javax.swing.JOptionPane"%>
<%@page import="java.io.IOException"%>
<%@page import="factory.DAOFactory"%>
<%@page import="dao.ShoppingCartDao"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String uid = String.valueOf(session.getAttribute("uid"));
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>支付商品</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<body>
	<%
		ShoppingCartDao scDao = DAOFactory.getShoppingCartServiceInstance();
		if (scDao.payAllGoods(Integer.parseInt(uid))) {
			response.sendRedirect("alreadyBuy.jsp");
		} else {
			JOptionPane.showMessageDialog(null, "购买商品失败，请重试！", "抱歉", JOptionPane.ERROR_MESSAGE);
			response.sendRedirect(request.getContextPath() + "/" + "jsp/shoppingCart.jsp");
		}
	%>
</body>
</html>