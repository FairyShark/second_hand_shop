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

    int del = 1;
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
                        <%
                            if (goods.getDel() == 0) {
                                del = 0;
                        %>
                        <br>
                        <p id="delete_2">
                            该商品已失效，请删除！
                        </p>
                        <%
                            }else if(goods.getNumber() < number){
                        %>
                        <br>
                        <p id="delete_2">
                            所选商品数量已超过库存，请修改购买数量或删除商品！
                        </p>
                        <%
                        }
                        %>
                    </div>
                    <div class="clearfix"></div>
                </td>
                <td><%=number%>
                </td>
                <td><%=price%>元</td>
                <td><%=goods.getCarriage()%>元</td>
                <td><%=totalPrice%>元</td>
                <%
                    if (goods.getDel() == 0) {
                %>
                <td><a href="javascript:" onclick="deletecartgoods(1, <%=gid%>, <%=number%>)">删除</a></td>
                <%
                } else {
                %>
                <td><a href="javascript:" onclick="deletecartgoods(0, <%=gid%>, <%=number%>)">删除</a></td>
                <%
                    }
                %>

            </tr>
            <%
                    }
                }
            %>
        </table>
        <a>总计：<%=allTotalPrice%>元
        </a>
        <%
            if (del == 0) {
        %>
        <a class="to-buy" href="javascript:" onclick="nogoods()">&nbsp;&nbsp;&nbsp;支付&nbsp;&nbsp;&nbsp;</a>
        <%
        } else if (cartList.size() > 0) {
        %>
        <a class="to-buy" href="javascript:" onclick="buygoods()">&nbsp;&nbsp;&nbsp;支付&nbsp;&nbsp;&nbsp;</a>
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
    <a id="collectiongoods" href="<%=basePath%>jsp/collectionGoods.jsp" title="收藏夹">收藏夹</a>
    <a id="scrollUp" href="javascript:" title="回到顶部"></a>
</div>

<script type="text/javascript">
    function nogoods() {
        alert("请删除失效的商品！");
    }

    function confirmDelete() {
        return confirm("确认删除商品吗");
    }

    function buygoods() {
        if(confirm("确认支付吗？")){
            $.ajax({
                type: "POST",
                url: "BuyGoodsServlet",
                data: {
                    Uid: <%=uid%>
                },
                dataType: "json",
                success: function (data) {
                    if (data.isok === "1") {
                        alert("支付成功！");
                    }else if(data.isok === "2") {
                        alert("所购买的商品超出了现有库存，请修改后再进行支付！");
                    }else {
                        alert("支付失败！");
                    }
                    location.reload();
                },
                error: function (err) {
                    alert("error");
                }
            });
        }
    }

    function deletecartgoods(flag, gid, number){
        if(flag===0) {
            if(confirm("确认要删除此商品吗？")){
                $.ajax({
                    type: "POST",
                    url: "DeleteCartGoodsServlet",
                    data: {
                        Uid: <%=uid%>,
                        Gid: Number(gid),
                        Number: Number(number)
                    },
                    dataType: "json",
                    success: function (data) {
                        if (data.isok === "0") {
                            alert("删除失败！");
                        }
                        location.reload();
                    },
                    error: function (err) {
                        alert("error");
                    }
                });
            }
        }else {
            $.ajax({
                type: "POST",
                url: "DeleteCartGoodsServlet",
                data: {
                    Uid: <%=uid%>,
                    Gid: Number(gid),
                    Number: Number(number)
                },
                dataType: "json",
                success: function (data) {
                    if (data.isok === "0") {
                        alert("删除失败！");
                    }
                    location.reload();
                },
                error: function (err) {
                    alert("error");
                }
            });
        }
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