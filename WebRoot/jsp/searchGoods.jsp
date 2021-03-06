<%@ page pageEncoding="utf-8" %>
<%@ page import="dao.GoodsDao" %>
<%@ page import="bean.Goods" %>
<%@ page import="factory.DAOFactory" %>
<%@ page import="java.util.List" %>
<%@ page import="jdk.internal.org.objectweb.asm.tree.analysis.Value" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>搜索商品</title>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="<%=basePath%>css/main.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="<%=basePath%>css/box.css" rel="stylesheet" type="text/css" media="all"/>
    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/responsiveslides.min.js"></script>
    <script type="text/javascript"
            src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>

<div class="content goods_show">
    <div class="sear_w">
        <h2>搜 索 商 品</h2>
    </div>
    <div class="bottter">
        <div class="seach_1">
            <div class="typ_1">
                <label>商品类型：</label> <select id="Types" name="Types">
                <option value="文具">文具</option>
                <option value="书籍">书籍</option>
                <option value="食品">食品</option>
                <option value="日用品">日用品</option>
                <option value="电子产品">电子产品</option>
                <option value="其他">其他</option>
                <option value="全部" selected="selected">全部</option>
            </select>
            </div>
            <div class="typ_2">
                <label>使用情况：</label> <select id="Usage" name="Usage">
                <option value="全新">全新</option>
                <option value="轻度使用">轻度使用</option>
                <option value="中度使用">中度使用</option>
                <option value="重度使用">重度使用</option>
                <option value="未知">未知</option>
                <option value="全部" selected="selected">全部</option>
            </select>
            </div>
            <div class="typ_3">
                <label>价格：</label><input id="low_pr" class="inp_1"><label>~</label><input
                    id="high_pr" class="inp_2">
            </div>
            <div class="typ_4">
                <label>关键词：</label><input id="main_w" class="inp_3">
            </div>
            <div class="typ_5">
                <button class="inp_4" onclick="clickSearch()">搜索</button>
            </div>
        </div>

    </div>

    <div class="container">
        <div class="content-top">
            <table id="resultTable">
                <tr>
                    <th>商品</th>
                    <th>发货地</th>
                    <th>库存</th>
                    <th>价格</th>
                    <th>运费</th>
                    <th>类型</th>
                    <th>使用情况</th>
                </tr>
                <%
                    try {
                        GoodsDao goodsDao = DAOFactory.getGoodsServiceInstance();
                        List<Goods> GoodsList = goodsDao.getAllGoods();
                        float allTotalPrice = 0;
                        if (GoodsList != null) {
                            if (GoodsList.size() > 0) {
                                Goods goods;
                                String photoPath;
                                String gAdress;
                                String gtype;
                                String gusage;
                                int number;
                                float price;
                                float carriage;
                                float totalPrice;
                                int gid;
                                for (Goods value : GoodsList) {
                                    if (value.getDel() == 1) {
                                        goods = value;
                                        String[] photo = goods.getPhoto().split("&");
                                        photoPath = basePath + "images/" + photo[0];
                                        number = goods.getNumber();
                                        price = goods.getPrice();
                                        carriage = goods.getCarriage();
                                        String goodsprice = String.valueOf(price);
                                        String goodscarriage = String.valueOf(carriage);
                                        if (!goodsprice.contains(".")) {
                                            goodsprice += ".00";
                                        } else {
                                            if (goodsprice.split("\\.")[1].length() == 1) {
                                                goodsprice += "0";
                                            }
                                        }
                                        if (!goodscarriage.contains(".")) {
                                            goodscarriage += ".00";
                                        } else {
                                            if (goodscarriage.split("\\.")[1].length() == 1) {
                                                goodscarriage += "0";
                                            }
                                        }
                                        gid = goods.getGid();
                                        gAdress = goods.getPaddress();
                                        gtype = goods.getType();
                                        gusage = goods.getUsage();
                                        totalPrice = number * price;
                                        allTotalPrice = allTotalPrice + totalPrice;
                %>
                <tr>
                    <td class="ring-in"><a
                            href="<%=basePath%>jsp/goodsDescribed.jsp?gid=<%=gid%>"
                            class="at-in" target="_blank"> <img src="<%=photoPath%>"
                                                                class="img-responsive" alt="">
                    </a>
                        <div class="sed">
                            <h5>
                                商品名：<%=goods.getGname()%>
                            </h5>
                            <br>
                            <p>
                                发布时间：<%=goods.getPdate()%>
                            </p>
                        </div>
                        <div class="clearfix"></div>
                    </td>
                    <td><%=gAdress%>
                    </td>
                    <td><%=number%>
                    </td>
                    <td><%=goodsprice%>元</td>
                    <td><%=goodscarriage%>元</td>
                    <td><%=gtype%>
                    </td>
                    <td><%=gusage%>
                    </td>
                </tr>
                <%
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                %>
            </table>
            <div id="tempP"></div>
        </div>
    </div>
</div>
<div class="bottom_tools">
    <a id="salegoods" href="<%=basePath%>jsp/saleGoods.jsp" title="出售二手">出售二手</a>
    <a id="feedback" href="<%=basePath%>jsp/shoppingCart.jsp" title="购物车">购物车</a>
    <a id="scrollUp" href="javascript:" title="回到顶部"></a>
</div>
<script type="text/javascript">
    $(function () {
        $("#slider").responsiveSlides({
            auto: true,
            speed: 500,
            namespace: "callbacks",
            pager: true,
        });
    });

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

    function clickSearch() {
        const GoodsType = $("#Types").val();
        const GoodsUsage = $("#Usage").val();
        let GoodsLowP = $("#low_pr").val();
        let GoodsHighP = $("#high_pr").val();
        let GoodsName = $("#main_w").val();
        if (GoodsType === "全部" && GoodsUsage === "全部"
            && (GoodsLowP == null || GoodsLowP === "")
            && (GoodsHighP == null || GoodsHighP === "")
            && (GoodsName == null || GoodsName === "")) {
            window.location.reload();
        } else {
            if (GoodsLowP == null || GoodsLowP === "")
                GoodsLowP = 0;
            if (GoodsHighP == null || GoodsHighP === "")
                GoodsHighP = 214748364;
            if (GoodsName == null || GoodsName === "")
                GoodsName = "%&ALL&%";
            const re = /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/;
            if(re.test(GoodsLowP) && re.test(GoodsHighP)) {
                $.ajax({
                    url: 'SelectGoodsServlet',
                    type: 'GET',
                    data: {
                        Uid: 8,
                        GoodsType: GoodsType,
                        GoodsUsage: GoodsUsage,
                        GoodsLowP: GoodsLowP,
                        GoodsHighP: GoodsHighP,
                        GoodsName: GoodsName
                    },
                    dataType: 'json',
                    success: function (json) {
                        $("#resultTable").empty();
                        const tr = $("<tr/>");
                        $("<th/>").html("商品").appendTo(tr);
                        $("<th/>").html("发货地").appendTo(tr);
                        $("<th/>").html("库存").appendTo(tr);
                        $("<th/>").html("价格").appendTo(tr);
                        $("<th/>").html("运费").appendTo(tr);
                        $("<th/>").html("类型").appendTo(tr);
                        $("<th/>").html("使用情况").appendTo(tr);
                        $("#resultTable").append(tr);
                        let temp = 0;
                        $.each(json, function (i, val) {
                            if (val.del === 1) {
                                const tr = $("<tr/>");
                                const td1 = $("<td/>");
                                td1.addClass("ring-in");
                                const a1 = $("<a/>");
                                a1.attr("href", "<%=basePath%>jsp/goodsDescribed.jsp?gid="
                                    + val.gid);
                                a1.attr("target", "_blank");
                                a1.addClass("at-in");
                                const img1 = $("<img/>");
                                let image1;
                                image1 = val.photo.split("&");
                                img1.attr("src", "<%=basePath%>images/" + image1[0]);
                                img1.addClass("img-responsive");
                                img1.appendTo(a1);
                                const div1 = $("<div/>");
                                div1.addClass("sed");
                                $("<h5/>").html("商品名：" + val.gname).appendTo(div1);
                                $("<br/>").appendTo(div1);
                                $("<p/>").html("发布时间：" + val.pdate).appendTo(div1);
                                const div2 = $("<div/>");
                                div2.addClass("clearfix");
                                a1.appendTo(td1);
                                div1.appendTo(td1);
                                div2.appendTo(td1);
                                td1.appendTo(tr);
                                $("<td/>").html(val.paddress).appendTo(tr);
                                $("<td/>").html(val.number).appendTo(tr);
                                let goodsprice = val.price;
                                let goodscarriage = val.carriage;
                                if (goodsprice.toString().indexOf('.') < 0) {
                                    goodsprice += '.00';
                                } else {
                                    if (goodsprice.toString().split('.')[1].length === 1) {
                                        goodsprice += '0';
                                    }
                                }
                                if (goodscarriage.toString().indexOf('.') < 0) {
                                    goodscarriage += '.00';
                                } else {
                                    if (goodscarriage.toString().split('.')[1].length === 1) {
                                        goodscarriage += '0';
                                    }
                                }
                                $("<td/>").html(goodsprice + "元").appendTo(tr);
                                $("<td/>").html(goodscarriage + "元").appendTo(tr);
                                $("<td/>").html(val.type).appendTo(tr);
                                $("<td/>").html(val.usage).appendTo(tr);
                                $("#resultTable").append(tr);
                                temp++;
                            }
                        });
                        if (temp == 0) {
                            $("#resultTable").empty();
                            $("#tempP").empty();
                            const p2 = $("<p/>");
                            p2.addClass("tempmess");
                            p2.html("暂时没有该类型的商品，换一个试试！").appendTo(p2);
                            $("#tempP").append(p2);
                        } else {
                            $("#tempP").empty();
                            const p3 = $("<p/>");
                            p3.addClass("tempmess");
                            p3.html("共找到" + temp + "个该类型的商品！").appendTo(p3);
                            $("#tempP").append(p3);
                        }
                    },
                    error: function () {
                        $("#test").append("条件查询错误！");
                    }

                });
            }else{
               alert("请输入正确的商品价格:整数或者保留两位小数");
            }
        }
    }

    document.onkeydown = function (event) {
        e = event ? event : (window.event ? window.event : null);
        if (e.keyCode === 13) {
            clickSearch();
        }
    };
</script>
<script type="text/javascript">
    window.onunload = function () {
        navigator.sendBeacon("servlet/LogCancelTServlet");
    }
</script>
</body>
</html>