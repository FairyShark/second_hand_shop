<%@ page pageEncoding="utf-8" %>
<%@ page import="bean.Goods" %>
<%@ page import="bean.ShoppingCart" %>
<%@ page import="dao.GoodsDao" %>
<%@ page import="dao.ShoppingCartDao" %>
<%@ page import="factory.DAOFactory" %>
<%@ page import="java.util.List" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>购物车</title>
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
        <h1>购物车</h1>
        <table>
            <tr>
                <th>商品</th>
                <th>数量</th>
                <th>价格</th>
                <th>运费</th>
                <th>总价</th>
            </tr>
            <%
                String strUid = (String) session.getAttribute("uid");
                int uid = 0;
                if (strUid != null) {
                    uid = Integer.parseInt(strUid);
                }
                ShoppingCartDao dao = null;
                try {
                    dao = DAOFactory.getShoppingCartServiceInstance();
                    GoodsDao goodsDao = DAOFactory.getGoodsServiceInstance();
                    List<ShoppingCart> cartList = dao.getAllGoods(uid);
                    float allTotalPrice = 0;
                    if (cartList != null & cartList.size() > 0) {
                        ShoppingCart cart;
                        Goods goods;
                        String photoPath;
                        int number;
                        float price;
                        float totalPrice;
                        int gid;
                        for (int i = 0; i < cartList.size(); i++) {
                            cart = cartList.get(i);
                            goods = goodsDao.queryById(cart.getGid());
                            String[] Gphoto = goods.getPhoto().split("&");
                            photoPath = basePath + "images/" + Gphoto[0];
                            number = cart.getNumber();
                            price = goods.getPrice();
                            gid = goods.getGid();
                            totalPrice = number * price + goods.getCarriage();
                            allTotalPrice = allTotalPrice + totalPrice;
            %>
            <tr>
                <td class="ring-in"><a
                        href="<%=basePath%>jsp/goodsDescribed.jsp?gid=<%=gid%>"
                        class="at-in" target="_blank"> <img src="<%=photoPath%>"
                                                            class="img-responsive" alt="商品图片">
                </a>
                    <div class="sed">
                        <h5>
                            商品名：<%=goods.getGname()%>
                        </h5>
                        <br>
                        <p>
                            加入购物车时间：<%=cart.getSdate()%>
                        </p>
                    </div>
                    <div class="clearfix"></div>
                </td>
                <td><%=number%>
                </td>
                <td><%=price%>元</td>
                <td><%=goods.getCarriage()%>元</td>
                <td><%=totalPrice%>元</td>
                <td><a
                        href="<%=basePath%>jsp/deleteCartGoods.jsp?gid=<%=gid%>&number=<%=number%>"
                        onclick="return confirmDelete()">删除</a></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
        <a>总计：<%=allTotalPrice%>元
        </a>
        <%
            if (cartList.size() > 0) {
        %>
        <a href="<%=basePath%>jsp/buyGoods.jsp" class="to-buy"
           onclick="return confirmBuy()">&nbsp;&nbsp;&nbsp;支付&nbsp;&nbsp;&nbsp;</a>
        <%
        } else {
        %>
        <a class="to-buy">&nbsp;&nbsp;&nbsp;支付&nbsp;&nbsp;&nbsp;</a>
        <%
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>
    </div>
</div>
<div class="bottom_tools">
    <a id="salegoods" href="<%=basePath%>jsp/saleGoods.jsp" title="出售二手">出售二手</a>
    <a id="feedback" href="<%=basePath%>jsp/shoppingCart.jsp" title="购物车">购物车</a>
    <a id="scrollUp" href="javascript:" title="回到顶部"></a>
</div>

<script type="text/javascript">
    function confirmBuy() {
        return confirm("确定支付吗？");
    }

    function confirmDelete() {
        return confirm("确认删除订单吗");
    }

    $(function () {
        var $body = $(document.body);
        var $bottomTools = $('.bottom_tools');
        var $qrTools = $('.qr_tool');
        var qrImg = $('.qr_img');
        $(window).scroll(function () {
            var scrollHeight = $(document).height();
            var scrollTop = $(window).scrollTop();
            var $footerHeight = $('.page-footer').outerHeight(true);
            var $windowHeight = $(window).innerHeight();
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