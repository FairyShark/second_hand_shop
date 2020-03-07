<%@page import="factory.DAOFactory"%>
<%@page import="dao.UserDao"%>
<%@page import="bean.Goods"%>
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
	int uid = Integer.parseInt(request.getParameter("uid"));
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>删除会员</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<body>
	<%
		UserDao userDao = DAOFactory.getUserServiceInstance();
	    GoodsDao goodsDao = DAOFactory.getGoodsServiceInstance();
	    AlreadyBuyDao abDao = DAOFactory.getAlreadyBuyServiceInstance();
	    AlreadySaleDao asDao = DAOFactory.getAlreadySaleServiceInstance();
	    List<Goods> uidGoodsList = goodsDao.getUidGoodsList(uid);
		if (uidGoodsList != null & uidGoodsList.size() > 0) {
			Goods goods;
			int gid;
			for (int i = 0; i < uidGoodsList.size(); i++) {
				goods = uidGoodsList.get(i);
				gid = goods.getGid();
				goodsDao.deleteGoods(gid);
				abDao.deleteBuyGoods(gid);
				asDao.deleteSaleGoods(gid);
			}
		}
		if (userDao.deleteUser(uid)) {
			response.sendRedirect("adminUser.jsp");
		} else {
	%>
	<div align="center"><br/>
	删除会员失败，请重试。
	</div>
	<%
		}
	%>
</body>
</html>
