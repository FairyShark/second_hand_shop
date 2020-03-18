<%@ page pageEncoding="utf-8" %>
<%@ page import="dao.GoodsDao" %>
<%@ page import="bean.Goods" %>
<%@ page import="factory.DAOFactory" %>
<%@ page import="java.util.List" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%
    String guid = null;
    if (session.getAttribute("uid") != null) {
        guid = String.valueOf(session.getAttribute("uid"));
    }
    int uid;
    if (guid == null) {
        uid = 0;
    } else {
        uid = Integer.parseInt(guid);
    }
    if (uid == 8) response.sendRedirect("jsp/adminUser.jsp");
%>
<!DOCTYPE html>
<html>
<head>
    <title>二手交易网</title>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="<%=basePath%>css/main.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="<%=basePath%>css/box.css" rel="stylesheet" type="text/css"
          media="all"/>
    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/responsiveslides.min.js"></script>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<div class="banner">
    <div class="col-sm-3 banner-mat"></div>
    <div class="col-sm-6 matter-banner">
        <div class="slider">
            <div class="callbacks_container">
                <ul class="rslides" id="slider">
                    <li><img src="<%=basePath%>images/bj1.png" alt=""></li>
                    <li><img src="<%=basePath%>images/bj2.png" alt=""></li>
                    <li><img src="<%=basePath%>images/bj3.png" alt=""></li>
                    <li><img src="<%=basePath%>images/bj4.png" alt=""></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="col-sm-3 banner-mat"></div>
    <div class="clearfix"></div>
</div>

<div class="content goods_show">
    <div class="goods_w">
        <h1>商 品 列 表</h1>
    </div>
    <div class="container">
        <div class="content-top">
            <%
                try {
                    GoodsDao dao = DAOFactory.getGoodsServiceInstance();
                    List<Goods> goodsList = dao.getAllGoods();
                    if (goodsList != null && goodsList.size() > 0) {
                        for (int i = 0; i < goodsList.size(); i++) {
                            if (i % 4 == 0) {
            %>
            <div class="content-top1">
                <%
                    }
                %>
                <div class="col-md-3 col-md2">
                    <div class="col-md1 simpleCart_shelfItem">
                        <a
                                href="<%=basePath%>jsp/goodsDescribed.jsp?gid=<%=goodsList.get(i).getGid()%>"
                                target="_blank">
                            <%
                                String[] photo = goodsList.get(i).getPhoto().split("&");
                                String photoPath = basePath + "images/" + photo[0];
                            %>
                            <img class="img-responsive" src="<%=photoPath%>" alt="图片"/>
                        </a>
                        <h3>
                            <a
                                    href="<%=basePath%>jsp/goodsDescribed.jsp?gid=<%=goodsList.get(i).getGid()%>"
                                    target="_blank"><%=goodsList.get(i).getGname()%>
                            </a>
                        </h3>
                        <div class="price">
                            <h5 class="item_price"><%=goodsList.get(i).getPrice()%>元
                            </h5>
                            <%
                                int sale_uid = goodsList.get(i).getUid();
                                if (sale_uid == uid) {
                            %>
                            <a
                                    href="<%=basePath%>jsp/editGoods.jsp?gid=<%=goodsList.get(i).getGid()%>"
                                    class="item_add" target="_blank">修改商品</a>
                            <%
                            } else {
                            %>
                            <a
                                    href="<%=basePath%>jsp/addToCart.jsp?gid=<%=goodsList.get(i).getGid()%>&buyNumber=1"
                                    class="item_add">加入购物车</a>
                            <%} %>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
                <%
                    if (i % 4 == 3) {
                %>
                <div class="clearfix"></div>
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
</div>
<div class="bottom_tools">
    <a id="searchgoods" href="<%=basePath%>jsp/searchGoods.jsp" title="搜索">搜索</a>
    <a id="salegoods" href="<%=basePath%>jsp/saleGoods.jsp" title="出售二手">出售二手</a> <a
        id="feedback" href="<%=basePath%>jsp/shoppingCart.jsp" title="购物车">购物车</a> <a
        id="scrollUp" href="javascript:" title="回到顶部"></a>
</div>
<script>
    $(function () {
        $("#slider").responsiveSlides({
            auto: true,
            speed: 500,
            namespace: "callbacks",
            pager: true,
        });
    });

    function showtime() {
        const myDate = new Date();
        document.getElementById("time").innerHTML = myDate.getHours() + "时"
            + myDate.getMinutes() + "分" + myDate.getSeconds() + "秒";
        setTimeout("showtime()", 1000);
    }

    $(function () {
        const $body = $(document.body);
        const $bottomTools = $('.bottom_tools');
        const $qrTools = $('.qr_tool');
        const qrImg = $('.qr_img');
        $(window)
            .scroll(
                function () {
                    const scrollHeight = $(document).height();
                    const scrollTop = $(window).scrollTop();
                    const $footerHeight = $('.page-footer')
                        .outerHeight(true);
                    const $windowHeight = $(window).innerHeight();
                    scrollTop > 50 ? $("#scrollUp").fadeIn(200)
                        .css("display", "block") : $(
                        "#scrollUp").fadeOut(200);
                    $bottomTools
                        .css(
                            "bottom",
                            scrollHeight - scrollTop
                            - $footerHeight > $windowHeight ? 40
                                : $windowHeight
                                + scrollTop
                                + $footerHeight
                                + 40
                                - scrollHeight);
                });
        $('#scrollUp').click(function (e) {
            e.preventDefault();
            $('html,body').animate({
                scrollTop: 0
            });
        });
        $qrTools.hover(function () {
            qrImg.fadeIn();
        }, function () {
            qrImg.fadeOut();
        });
    });

    window.onunload = function () {
        navigator.sendBeacon("servlet/LogCancelTServlet");
    }
</script>
</body>
</html>