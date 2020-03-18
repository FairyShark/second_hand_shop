<%@ page import="dao.ShoppingCartDao" %>
<%@ page import="factory.DAOFactory" %>
<%@ page pageEncoding="UTF-8" %>
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
    <link href="<%=basePath%>css/main.css" rel="stylesheet" type="text/css" media="all"/>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
</head>
<body>
<%
    try {
        ShoppingCartDao scDao = DAOFactory.getShoppingCartServiceInstance();
        if (scDao.deleteGoods(Integer.parseInt(uid), gid, number)) {
            response.sendRedirect("shoppingCart.jsp");
        } else {
%>
<div class="delete_1"><br/>
    删除订单失败，请关闭重试！
</div>
<%
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
</body>
</html>
