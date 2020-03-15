<%@ page import="dao.GoodsDao" pageEncoding="UTF-8" %>
<%@ page import="dao.ShoppingCartDao" %>
<%@ page import="factory.DAOFactory" %>
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
    <meta http-equiv="Pragma" content="no-cache">
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
    int gid = Integer.parseInt(request.getParameter("gid"));
    int number = Integer.parseInt(request.getParameter("buyNumber"));
    ShoppingCartDao dao = null;
    try {
        dao = DAOFactory.getShoppingCartServiceInstance();
        GoodsDao goodsDao = DAOFactory.getGoodsServiceInstance();
        if (goodsDao.querySaleUid(gid) != uid) {
            boolean flag = dao.addGoods(uid, gid, number);
            if (flag) {
                response.sendRedirect("shoppingCart.jsp");
            } else {
                String truePath = request.getContextPath() + "/jsp/index.jsp";
                response.getWriter().println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
                response.getWriter().println("<HTML>");
                response.getWriter().println("  <HEAD><TITLE>添加到购物车</TITLE>");
                response.getWriter().println("<meta http-equiv=\"refresh\" content=\"5;url=" + truePath + "\">");
                response.getWriter().println("</HEAD>");
                response.getWriter().println("  <BODY>");
                response.getWriter().print("<div align=\"center\">");
                response.getWriter().print("添加到购物车失败，请重试！");
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
        } else {
            String truePath = request.getContextPath() + "/jsp/index.jsp";
            response.getWriter().println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
            response.getWriter().println("<HTML>");
            response.getWriter().println("  <HEAD><TITLE>添加到购物车</TITLE>");
            response.getWriter().println("<meta http-equiv=\"refresh\" content=\"5;url=" + truePath + "\">");
            response.getWriter().println("</HEAD>");
            response.getWriter().println("  <BODY>");
            response.getWriter().print("<div align=\"center\">");
            response.getWriter().print("不能购买自己出售的商品，请重试！");
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
