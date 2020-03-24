<%@ page import="factory.DAOFactory" %>
<%@ page import="bean.Goods" %>
<%@ page import="java.net.Inet4Address" %>
<%@ page import="java.net.InetAddress" %>
<%@ page import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="dao.*" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";

    InetAddress ip4 = Inet4Address.getLocalHost();
    String userip = ip4.getHostAddress();
%>
<%
    int uid = Integer.parseInt(request.getParameter("uid"));
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>删除会员</title>
    <link href="<%=basePath%>css/main.css" rel="stylesheet" type="text/css" media="all"/>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
</head>
<body>
<%
    try {
        UserDao userDao = DAOFactory.getUserServiceInstance();
        GoodsDao goodsDao = DAOFactory.getGoodsServiceInstance();
        AlreadyBuyDao abDao = DAOFactory.getAlreadyBuyServiceInstance();
        AlreadySaleDao asDao = DAOFactory.getAlreadySaleServiceInstance();
        List<Goods> uidGoodsList = goodsDao.getUidGoodsList(uid);
        if (userDao.deleteUser(uid)) {
            String opcontent = "删除会员及其所有商品:会员ID（" + uid + "）";

            if (uidGoodsList != null) {
                if (uidGoodsList.size() > 0) {
                    Goods goods;
                    int gid;
                    opcontent += "。 同时删除的商品：";
                    for (int i = 0; i < uidGoodsList.size(); i++) {
                        goods = uidGoodsList.get(i);
                        gid = goods.getGid();
                        goodsDao.deleteGoods(gid);
                        abDao.deleteBuyGoods(gid);
                        asDao.deleteSaleGoods(gid);
                        opcontent += "商品ID（" + gid + ");";
                    }
                }
            }

            OperationMesDao omdao = DAOFactory.getOperationMesServiceInstance();
            omdao.addOperationMes(8, "admin", userip, "删除", opcontent);
            response.sendRedirect("adminUser.jsp");
        } else {
%>
<div class="delete_1"><br/>
    删除会员失败，请重试。
</div>
<%
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
</body>
</html>
