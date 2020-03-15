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
    try {
        ShoppingCartDao scDao = null;
        scDao = DAOFactory.getShoppingCartServiceInstance();
        if (scDao.payAllGoods(Integer.parseInt(uid))) {
            response.sendRedirect("alreadyBuy.jsp");
        } else {
            String truePath = request.getContextPath() + "/jsp/shoppingCart.jsp";
            response.getWriter().println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
            response.getWriter().println("<HTML>");
            response.getWriter().println("  <HEAD><TITLE>购买商品</TITLE>");
            response.getWriter().println("<meta http-equiv=\"refresh\" content=\"5;url=" + truePath + "\">");
            response.getWriter().println("</HEAD>");
            response.getWriter().println("  <BODY>");
            response.getWriter().print("<div align=\"center\">");
            response.getWriter().print("购买商品失败，请重试！");
            response.getWriter().print("<br/>");
            response.getWriter().print("将自动跳转到相应页面");
            response.getWriter().print("<br/>");
            response.getWriter().print("或点击这里：");
            response.getWriter().print("<a href=\"" + truePath + "\"" + ">返回" + "</a>");
            response.getWriter().print("</div>");
            response.getWriter().println("  </BODY>");
            response.getWriter().println("</HTML>");
            response.getWriter().flush();
            response.getWriter().close();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
</body>
</html>