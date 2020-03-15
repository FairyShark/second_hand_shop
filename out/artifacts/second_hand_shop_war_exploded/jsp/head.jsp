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
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="<%=basePath%>/css/main.css" rel="stylesheet" type="text/css" media="all"/>
    <script type="text/javascript" src="<%=basePath%>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/responsiveslides.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/memenu.js"></script>
    <script>
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
                <a href="<%=basePath%>/jsp/index.jsp"><img src="<%=basePath%>/images/logo.png" alt=""></a>
            </div>
            <div class="col-sm-4 header-left">
                <p class="log">
                    <%
                        if (uname != null) {
                            out.print("<a>" + uname + ",欢迎登录" + "</a>");
                            out.print("<a href=\"" + basePath +"/jsp/showMessage.jsp?uid=" + uid + "\" target=\"_blank\">" + "个人信息" + "</a>");
                            out.print("<a href=\"servlet/LogoutServlet\" onClick=\"return key()\">" + "注销" + "</a>");
                        } else {
                            out.print("<a href=\"" + basePath + "/jsp/login.jsp\">请  登录</a>");
                            out.print("<a>or</a>");
                            out.print("<a href=\"" + basePath + "/jsp/register.jsp\">注册</a>");
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
                    <li class=" grid"><a href="<%=basePath%>/jsp/index.jsp">首页</a></li>
                    <li><a>分类</a>
                        <div class="mepanel">
                            <div class="row">
                                <%
                                    GoodsDao dao = null;
                                    try {
                                        dao = DAOFactory.getGoodsServiceInstance();
                                        String[] types = dao.queryTypes();
                                        if (types != null & types.length > 0) {
                                            String type;
                                            List<Goods> goodsList;
                                            Goods goods;
                                            for (int i = 0; i < types.length; i++) {
                                                if (types[i] != null & !"".equals(types[i])) {
                                                    type = types[i];
                                                    goodsList = dao.getTypeGoodsList(type);
                                %>
                                <div class="col1">
                                    <div class="h_nav">
                                        <h4><%=type%>
                                        </h4>
                                        <ul>
                                            <%
                                                if (goodsList != null & goodsList.size() > 0) {
                                                    for (int j = 0; j < goodsList.size(); j++) {
                                                        goods = goodsList.get(j);
                                            %>
                                            <li><a
                                                    href="<%=basePath%>/jsp/goodsDescribed.jsp?gid=<%=goods.getGid()%>"
                                                    target="_blank"><%=goods.getGname()%>
                                            </a></li>
                                            <%
                                                    }
                                                }
                                            %>
                                        </ul>
                                    </div>
                                </div>
                                <%
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                %>
                            </div>
                        </div>
                    </li>
                    <li><a href="<%=basePath%>/jsp/saleGoods.jsp">出售二手</a></li>
                    <li><a href="<%=basePath%>/jsp/shoppingCart.jsp">购物车</a></li>
                    <li><a href="<%=basePath%>/jsp/alreadyBuy.jsp">购买记录</a></li>
                    <li><a href="<%=basePath%>/jsp/alreadySale.jsp">销售记录</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>