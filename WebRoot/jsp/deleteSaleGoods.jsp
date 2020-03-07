<%@page import="factory.DAOFactory"%>
<%@page import="dao.GoodsDao"%>
<%@page import="dao.AlreadyBuyDao"%>
<%@page import="dao.AlreadySaleDao"%>
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
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>删除商品</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<body>
	<%
		GoodsDao goodsDao = DAOFactory.getGoodsServiceInstance();
	    AlreadyBuyDao abDao = DAOFactory.getAlreadyBuyServiceInstance();
	    AlreadySaleDao asDao = DAOFactory.getAlreadySaleServiceInstance();
	    abDao.deleteBuyGoods(gid);
	    asDao.deleteSaleGoods(gid);
		if (goodsDao.deleteGoods(gid)) {
			response.sendRedirect("saleGoods.jsp");
		} else {
	%>
	<div align="center"><br/>
	删除商品失败，请重试。
	</div>
	<%
		}
	%>
</body>
</html>
