<%@ page import="factory.DAOFactory" %>
<%@ page import="dao.GoodsDao" %>
<%@ page import="dao.AlreadyBuyDao" %>
<%@ page import="dao.AlreadySaleDao" %>
<%@ page import="dao.OperationMesDao" %>
<%@ page import="java.net.Inet4Address" %>
<%@ page import="java.net.InetAddress" %>
<%@ page import="java.util.*" pageEncoding="UTF-8" %>
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
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
</head>
<body>
<%
    try {
        GoodsDao goodsDao = null;
        goodsDao = DAOFactory.getGoodsServiceInstance();
        AlreadyBuyDao abDao = DAOFactory.getAlreadyBuyServiceInstance();
        AlreadySaleDao asDao = DAOFactory.getAlreadySaleServiceInstance();
        abDao.deleteBuyGoods(gid);
        asDao.deleteSaleGoods(gid);
        if (goodsDao.deleteGoods(gid)) {
            String opcontent = "删除商品:商品ID（" + gid + "）";
            OperationMesDao omdao = DAOFactory.getOperationMesServiceInstance();
            omdao.addOperationMes(Integer.parseInt(uid), userip, opcontent);
            if (Integer.parseInt(uid) == 8) {
                response.sendRedirect("adminGoods.jsp");
            } else {
                response.sendRedirect("saleGoods.jsp");
            }
        } else {
%>
<div align="center"><br/>
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
