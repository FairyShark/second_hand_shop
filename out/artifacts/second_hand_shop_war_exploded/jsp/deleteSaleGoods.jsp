<%@ page import="dao.*" %>
<%@ page import="factory.DAOFactory" %>
<%@ page import="java.net.Inet4Address" %>
<%@ page import="java.net.InetAddress" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";

    InetAddress ip4 = Inet4Address.getLocalHost();
    String userip = ip4.getHostAddress();
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
    <link href="<%=basePath%>css/main.css" rel="stylesheet" type="text/css" media="all"/>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
</head>
<body>
<%
    try {
        GoodsDao gdao = DAOFactory.getGoodsServiceInstance();
        if (gdao.deleteGoods(gid)) {
            String opcontent = "删除在售商品:商品ID（" + gid + "）";
            OperationMesDao omdao = DAOFactory.getOperationMesServiceInstance();
            UserDao udao = DAOFactory.getUserServiceInstance();
            String uname = udao.queryUName(Integer.parseInt(uid));
            omdao.addOperationMes(Integer.parseInt(uid), uname, userip, "删除", opcontent);
            if (Integer.parseInt(uid) == 8) {
                response.sendRedirect("adminGoods.jsp");
            } else {
                response.sendRedirect("saleGoods.jsp");
            }
        } else {
%>
<div class="delete_1"><br/>
    删除商品失败，请重试。
</div>
<%
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
</body>
</html>
