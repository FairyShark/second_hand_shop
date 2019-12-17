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
	int gid = Integer.parseInt(request.getParameter("gid"));
	int number = Integer.parseInt(request.getParameter("number"));
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>删除订单</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<body>
	<%
		ShoppingCartDao scDao = DAOFactory.getShoppingCartServiceInstance();
		if (scDao.deleteGoods(Integer.parseInt(uid), gid, number)) {
			response.sendRedirect("shoppingCart.jsp");
		} else {
	%>
	删除订单失败，请重试。
	<%
		}
	%>
</body>
</html>
