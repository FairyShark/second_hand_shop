<%@ page pageEncoding="utf-8" %>
<%@ page import="util.OnlineCounter" %>
<%@ page import="bean.Goods" %>
<%@ page import="java.util.List" %>
<%@ page import="factory.DAOFactory" %>
<%@ page import="dao.GoodsDao" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%
    String uname = (String) session.getAttribute("uname");
    String uid = String.valueOf(session.getAttribute("uid"));
    int c = 0;
    if (session.getAttribute("uid") != null) {
        if (String.valueOf(session.getAttribute("uid")) != null) {
            if (Integer.parseInt(uid) != 8) {
                c = 1;
            }
        }
    } else {
        c = 1;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="<%=basePath%>css/main.css" rel="stylesheet" type="text/css" media="all"/>
    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/responsiveslides.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/memenu.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $(".memenu").memenu();
        });

        function key() {
            return confirm("确定退出吗？");
        }

        function showtime() {
            var myDate = new Date();
            document.getElementById("time").innerHTML = myDate.getHours() + "点"
                + myDate.getMinutes() + "分" + myDate.getSeconds() + "秒";
            setTimeout("showtime()", 1000);
        }
    </script>
</head>
<body>
<div class="header">
    <div class="header-top">
        <div class="container">
            <div class="col-sm-4 world">
                <ul>
                    <li></li>
                </ul>
            </div>
            <div class="col-sm-4 logo">
                <a href="<%=basePath%>jsp/index.jsp"><img src="<%=basePath%>images/logo.png" alt=""></a>
            </div>
            <div class="col-sm-4 header-left">
                <p class="log">
                    <%
                        if (uname != null) {
                            if (c == 1) {
                                out.print("<a>" + uname + ",欢迎登录" + "</a>");
                                out.print("<a href=\"" + basePath + "jsp/showMessage.jsp?uid=" + uid + "\" target=\"_blank\">" + "个人信息" + "</a>");
                            } else {
                                out.print("<a>" + "你好管理员,欢迎登录" + "</a>");
                            }
                            out.print("<a href=\"servlet/LogoutServlet\" onClick=\"return key()\">" + "注销" + "</a>");
                        } else {
                            out.print("<a href=\"" + basePath + "jsp/login.jsp\">请  登录</a>");
                            out.print("<a>or</a>");
                            out.print("<a href=\"" + basePath + "jsp/register.jsp\">注册</a>");
                        }
                    %>
                    <a id="time">
                        <script type="text/javascript">
                            showtime();
                        </script>
                    </a> <a>在线人数:<%=OnlineCounter.getOnline()%>
                </a>
                </p>

            </div>
        </div>
    </div>
    <div class="container">
        <div class="head-top">
            <div class="col-sm-8 h_menu4">
                <ul class="memenu skyblue">
                    <%
                        if (c == 1) {
                    %>
                    <li class=" grid"><a href="<%=basePath%>jsp/index.jsp">首页</a></li>
                    <li><a href="<%=basePath%>jsp/saleGoods.jsp">出售二手</a></li>
                    <li><a href="<%=basePath%>jsp/shoppingCart.jsp">购物车</a></li>
                    <li><a href="<%=basePath%>jsp/alreadyBuy.jsp">购买记录</a></li>
                    <li><a href="<%=basePath%>jsp/alreadySale.jsp">销售记录</a></li>
                    <li><a href="<%=basePath%>jsp/collectionGoods.jsp">收藏夹</a></li>
                    <%
                    } else {
                    %>
                    <li class=" grid"><a href="<%=basePath%>jsp/adminUser.jsp" class="a_active">会员管理</a></li>
                    <li><a href="<%=basePath%>jsp/adminGoods.jsp">商品管理</a></li>
                    <li><a href="<%=basePath%>jsp/adminLog.jsp">登陆信息</a></li>
                    <li><a href="<%=basePath%>jsp/adminVis.jsp">浏览记录</a></li>
                    <li><a href="<%=basePath%>jsp/adminOpe.jsp">操作日志</a></li>
                    <%
                        }
                    %>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>