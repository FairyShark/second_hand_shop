<%@ page import="bean.AlreadySale" %>
<%@ page import="bean.Goods" %>
<%@ page import="dao.AlreadySaleDao" %>
<%@ page import="dao.GoodsDao" %>
<%@ page import="dao.UserDao" %>
<%@ page import="factory.DAOFactory" %>
<%@ page import="java.util.List" %>
<%@ page pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>销售记录</title>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="<%=basePath%>css/main.css" rel="stylesheet" type="text/css" media="all"/>
    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/memenu.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/simpleCart.min.js"></script>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<div class="container">
    <div class="check-out">
        <h1>销售记录</h1>
        <table>
            <tr>
                <th>商品</th>
                <th>买家</th>
                <th>类型</th>
                <th>数量</th>
                <th>单价</th>
                <th>运费</th>
                <th>总价</th>
            </tr>
            <%
                String strUid = (String) session.getAttribute("uid");
                int uid = 0;
                if (strUid != null) {
                    uid = Integer.parseInt(strUid);
                }
                try {
                    AlreadySaleDao dao = DAOFactory.getAlreadySaleServiceInstance();
                    List<AlreadySale> asList = dao.getAllSaleGoods(uid);
                    if (asList != null) {
                        if (asList.size() > 0) {
                            UserDao userDao = DAOFactory.getUserServiceInstance();
                            GoodsDao goodsDao = DAOFactory.getGoodsServiceInstance();
                            Goods goods;
                            AlreadySale as;
                            int gid;
                            int buy_uid;
                            int number;
                            String buyUname;
                            String gtype;
                            String saleTime;
                            String photoPath;
                            float price;
                            float carriage;
                            float totalPrice;
                            String goods_price;
                            String goods_carriage;
                            String goods_totalPrice;
                            for (int i = 0; i < asList.size(); i++) {
                                as = asList.get(i);
                                gid = as.getGid();
                                gtype = as.getGtype();
                                buy_uid = as.getBUid();
                                number = as.getNumber();
                                buyUname = userDao.queryUName(buy_uid);
                                saleTime = as.getSaleTime();
                                goods = goodsDao.queryById(gid);
                                photoPath = basePath + "images/" + goods.getPhoto().split("&")[0];
                                price = as.getPrice();
                                carriage = as.getCarriage();
                                totalPrice = number * price + carriage;
                                goods_price = String.valueOf(price);
                                goods_carriage = String.valueOf(carriage);
                                goods_totalPrice = String.valueOf(totalPrice);
                                if (!goods_price.contains(".")) {
                                    goods_price += ".00";
                                } else {
                                    if (goods_price.split("\\.")[1].length() == 1) {
                                        goods_price += "0";
                                    }
                                }
                                if (!goods_carriage.contains(".")) {
                                    goods_carriage += ".00";
                                } else {
                                    if (goods_carriage.split("\\.")[1].length() == 1) {
                                        goods_carriage += "0";
                                    }
                                }
                                if (!goods_totalPrice.contains(".")) {
                                    goods_totalPrice += ".00";
                                } else {
                                    if (goods_totalPrice.split("\\.")[1].length() == 1) {
                                        goods_totalPrice += "0";
                                    }
                                }
            %>
            <tr>
                <td class="ring-in"><a
                        href="<%=basePath%>jsp/goodsDescribed.jsp?gid=<%=goods.getGid()%>"
                        class="at-in" target="_blank"> <img src="<%=photoPath%>"
                                                            class="img-responsive" alt="">
                </a>
                    <div class="sed">
                        <h5>
                            <%=goods.getGname()%>
                        </h5>
                        <br>
                        <br>
                        <p>
                            卖出时间：<%=saleTime%>
                        </p>
                    </div>
                    <div class="clearfix"></div>
                </td>
                <td><%=buyUname%>
                </td>
                <td><%=gtype%>
                </td>
                <td><%=number%>
                </td>
                <td><%=goods_price%>元</td>
                <td><%=goods_carriage%>元</td>
                <td><%=goods_totalPrice%>元</td>
            </tr>
            <%
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            %>
        </table>
    </div>
</div>
<div class="bottom_tools">
    <a id="quxian" href="<%=basePath%>jsp/saleChart.jsp" title="销售报表" target="_blank">销售报表</a>
    <a id="salegoods" href="<%=basePath%>jsp/saleGoods.jsp" title="出售二手">出售二手</a>
    <a id="feedback" href="<%=basePath%>jsp/shoppingCart.jsp" title="购物车">购物车</a>
    <a id="collectiongoods" href="<%=basePath%>jsp/collectionGoods.jsp" title="收藏夹">收藏夹</a>
    <a id="scrollUp" href="javascript:" title="回到顶部"></a>
</div>
<script type="text/javascript">
    $(function () {
        const $body = $(document.body);
        const $bottomTools = $('.bottom_tools');
        const $qrTools = $('.qr_tool');
        const qrImg = $('.qr_img');
        $(window).scroll(function () {
            const scrollHeight = $(document).height();
            const scrollTop = $(window).scrollTop();
            const $footerHeight = $('.page-footer').outerHeight(true);
            const $windowHeight = $(window).innerHeight();
            scrollTop > 50 ? $("#scrollUp").fadeIn(200).css("display", "block") : $("#scrollUp").fadeOut(200);
            $bottomTools.css("bottom", scrollHeight - scrollTop - $footerHeight > $windowHeight ? 40 : $windowHeight + scrollTop + $footerHeight + 40 - scrollHeight);
        });
        $('#scrollUp').click(function (e) {
            e.preventDefault();
            $('html,body').animate({scrollTop: 0});
        });
        $qrTools.hover(function () {
            qrImg.fadeIn();
        }, function () {
            qrImg.fadeOut();
        });
    });

</script>
<script type="text/javascript">
    window.onunload = function () {
        navigator.sendBeacon("servlet/LogCancelTServlet");
    }
</script>
</body>
</html>