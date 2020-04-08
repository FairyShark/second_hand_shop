<%@ page pageEncoding="utf-8" %>
<%@ page import="bean.Goods" %>
<%@ page import="java.util.List" %>
<%@ page import="factory.DAOFactory" %>
<%@ page import="dao.*" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    int gid = Integer.parseInt(request.getParameter("gid"));
    String guid = null;
    if (session.getAttribute("uid") != null) {
        guid = String.valueOf(session.getAttribute("uid"));
    }
    int uid = 0;
    if (guid != null) {
        uid = Integer.parseInt(guid);
    }
    int counts = 0;
%>
<!DOCTYPE html>
<html>
<head>
    <title>商品详情</title>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="<%=basePath%>css/main.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="<%=basePath%>css/goods.css" rel="stylesheet" type="text/css" media="screen"/>
    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/imagezoom.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/memenu.js"></script>
    <script defer src="<%=basePath%>js/jquery.flexslider.js"></script>
</head>
<body>
<%
    try {
        String goodslandtime = null;
        GoodsDao dao = DAOFactory.getGoodsServiceInstance();
        if (dao.queryById(gid) != null) {
            Goods goods = dao.queryById(gid);
            String name = goods.getGname();
            int number = goods.getNumber();
            String[] photo = goods.getPhoto().split("&");
            String type = goods.getType();
            String uname = dao.queryUName(gid);
            String usage = goods.getUsage();
            float price = goods.getPrice();
            float carriage = goods.getCarriage();
            String paddress = goods.getPaddress();
            String described = goods.getDescribed();
            int sale_uid = goods.getUid();
            VisitMessageDao vmdao = DAOFactory.getVisitMessageServiceInstance();
            UserDao udao = DAOFactory.getUserServiceInstance();
            String uname_t = udao.queryUName(uid);
            if (uid != 8) {
                vmdao.addLandTimeMes(uid, gid, uname_t, name, type);
                goodslandtime = vmdao.getVisitlandtime(uid, gid);
            }
%>
<jsp:include page="head.jsp"></jsp:include>
<div class="single">
    <div class="container">
        <div class="col-md-9">
            <div class="col-md-5 grid">
                <div class="flexslider">
                    <ul class="slides">
                        <%
                            if (photo.length > 0) {
                                for (int i = 0; i < photo.length; i++) {
                        %>
                        <li data-thumb="<%=basePath%>images/<%=photo[i]%>">
                            <div class="thumb-image">
                                <img src="images/<%=photo[i]%>" data-imagezoom="true"
                                     class="img-responsive" alt="商品图片">
                            </div>
                        </li>
                        <%
                                }
                            }
                        %>
                    </ul>
                </div>
            </div>
            <div class="col-md-7 single-top-in">
                <div class="single-para simpleCart_shelfItem">
                    <h1><%=name%>
                    </h1>
                    <p><%=described%>
                    </p>
                    <div class="star-on">
                        <div class="review">
                            <a>卖家：<%=uname%>
                            </a><br/> <a>使用情况：<%=usage%>
                        </a> <br/> <a>发货地：<%=paddress%>
                        </a>
                            <br/> <a>运费：<%=carriage%>元
                        </a> / <a>库存：<%=number%>
                        </a>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <label class="add-to item_price"><%=price%>元</label>
                    <%
                        if (dao.queryById(gid).getDel() == 1) {
                    %>
                    <%
                        if (sale_uid == uid) {
                    %>
                    <a id="carthref" href="<%=basePath%>jsp/editGoods.jsp?gid=<%=gid%>"
                       class="cart item_add">修改商品</a>
                    <%
                    } else if (uid != 8) {
                    %>
                    <div class="available">
                        <h6>购买数量 :</h6>
                        <input id="buyNumber" name="buyNumber" type="number" min="1"
                               max=<%=number%> value="1"/>
                    </div>
                    <a id="carthref" href="javascript:" class="cart item_add" onclick="addtocart()">加入购物车</a>

                    <%
                        }
                    } else {
                    %>
                    <div class="available">
                        <h6 id="delete_2">此商品已下架！</h6>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
        <%
            if (uid != 8) {
        %>
        <div class="col-md-3 product-bottom">
            <div class="product-bottom">
                <h3 class="cate">最新商品</h3>
                <%
                    List<Goods> latestGoods = dao.getLatestGoods(gid, type);
                    if (latestGoods != null) {
                        if (latestGoods.size() > 0) {
                            int flag = 4;
                            if(latestGoods.size() < 4){
                                flag = latestGoods.size();
                            }
                            for (int i = 0; i < flag; i++) {
                                if (latestGoods.get(i).getDel() == 1) {
                                    String goodsName = latestGoods.get(i).getGname();
                                    String goodsHref = basePath + "jsp/goodsDescribed.jsp?gid=" + latestGoods.get(i).getGid();
                                    String[] newest_photo = latestGoods.get(i).getPhoto().split("&");
                                    String goodsPhoto = basePath + "images/" + newest_photo[0];
                                    float goodsPrice = latestGoods.get(i).getPrice();
                %>
                <div class="product-go">
                    <div class=" fashion-grid">
                        <a href="<%=goodsHref%>" target="_blank"> <img
                                class="img-responsive " src="<%=goodsPhoto%>" alt="商品图片"/></a>
                    </div>
                    <div class=" fashion-grid1">
                        <h6 class="best2">
                            <a href="<%=goodsHref%>" target="_blank"><%=goodsName%>
                            </a>
                        </h6>
                        <span class="price-in1"> <%=goodsPrice%>元
							</span>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <%
                                }else {
                                    flag++;
                                }
                            }
                        }
                    }
                %>
            </div>
            <div class="scdiv">
                <%
                    if (uid != 8 && sale_uid != uid) {
                        CollectionDao cd = DAOFactory.getCollectionServiceInstance();
                        counts = cd.getCount(gid);
                        if (uid == 0 || !cd.judgeCollection(uid, gid)) {
                %>
                <a class="sca" href="javascript:"><img class="scimg" src="<%=basePath%>images/scj.png"
                                    onmouseover="scjimgover(this)" onmouseout="scjimgout(this)"
                                    onclick="collectiongoods(<%=uid%>)"/><span class="rq">（<%=counts%>人气）</span></a>
                <%
                } else {
                %>
                <a class="sca" href="javascript:"><img class="scimg" src="<%=basePath%>images/ysc.png" onclick="cancelcollection()"/><span class="rq">（<%=counts%>人气）</span></a>
                <%
                        }
                    }
                %>
            </div>
        </div>
        <%
            }
        %>
        <div class="clearfix"></div>
    </div>
</div>
<script type="text/javascript">

    window.onunload = function () {
        var URL1 = "servlet/VisitGCancelTServlet?uid=<%=uid%>&gid=<%=gid%>&landtime=<%=goodslandtime%>";
        var URL2 = "servlet/LogCancelTServlet";
        navigator.sendBeacon(URL1);
        navigator.sendBeacon(URL2);
    };

    $(document).ready(function () {
        $(".memenu").memenu();
    });

    $(window).load(function () {
        $('.flexslider').flexslider({
            animation: "slide",
            controlNav: "thumbnails"
        });
    });

    function scjimgover(x) {
        x.src = "<%=basePath%>images/scj_act.png";
    }

    function scjimgout(x) {
        x.src = "<%=basePath%>images/scj.png";
    }

    $(function () {
        const menu_ul = $('.menu-drop > li > ul'), menu_a = $('.menu-drop > li > a');
        menu_ul.hide();
        menu_a.click(function (e) {
            e.preventDefault();
            if (!$(this).hasClass('active')) {
                menu_a.removeClass('active');
                menu_ul.filter(':visible').slideUp('normal');
                $(this).addClass('active').next().stop(true, true)
                    .slideDown('normal');
            } else {
                $(this).removeClass('active');
                $(this).next().stop(true, true).slideUp('normal');
            }
        });

    });

    function collectiongoods(uid) {
        if (uid === 0) {
            alert('请先登录！');
        } else {
            $.ajax({
                type: "POST",
                url: "AddCollectionServlet",
                data: {
                    Uid: <%=uid%>,
                    Gid: <%=gid%>
                },
                dataType: "json",
                success: function (data) {

                    if (data.isok === "1") {
                        $(".sca").empty();
                        const img = $("<img/>");
                        img.attr("class", "scimg");
                        img.attr("src", "<%=basePath%>images/ysc.png");
                        img.attr("onclick", "cancelcollection()");
                        img.appendTo($(".sca"));
                        const span = $("<span/>");
                        span.attr("class", "rq");
                        span.html("(" + data.counts + "人气)");
                        span.appendTo($(".sca"));
                        alert("收藏商品成功！");
                    } else {
                        alert("收藏失败，请重试！");
                    }
                },
                error: function (err) {
                    alert("error");
                }
            });
        }
    }

    function cancelcollection(){
        if(confirm("确定要取消收藏吗？")){
            $.ajax({
                type: "POST",
                url: "DeleteCollectionServlet",
                data: {
                    Uid: <%=uid%>,
                    Gid: <%=gid%>
                },
                dataType: "json",
                success: function (data) {
                    if (data.isok === "1") {
                        $(".sca").empty();
                        const img = $("<img/>");
                        img.attr("class", "scimg");
                        img.attr("src", "<%=basePath%>images/scj.png");
                        img.attr("onmouseover", "scjimgover(this)");
                        img.attr("onmouseout", "scjimgout(this)");
                        img.attr("onclick", "collectiongoods(<%=uid%>)");
                        img.appendTo($(".sca"));
                        const span = $("<span/>");
                        span.attr("class", "rq");
                        span.html("(" + data.counts + "人气)");
                        span.appendTo($(".sca"));
                    } else {
                        alert("取消收藏失败，请重试！");
                    }
                },
                error: function (err) {
                    alert("error");
                }
            });
        }
    }

    function addtocart() {
        let flag = <%=uid%>;
        if(flag === 0){
            alert("请先登录！");
        }else {
            $.ajax({
                type: "POST",
                url: "AddToCartServlet",
                data: {
                    Uid: <%=uid%>,
                    Gid: <%=gid%>,
                    Number: Number($('#buyNumber').val())
                },
                dataType: "json",
                success: function (data) {
                    if (data.isok === "1") {
                        alert("添加到购物车成功！");
                    }else if(data.isok === "2") {
                        alert("库存仅为" + data.number + ",请重新输入数量！");
                    }else {
                        alert("添加失败，请重试！");
                    }
                },
                error: function (err) {
                    alert("error");
                }
            });
        }
    }
</script>
<%
} else {
%>
<div class="delete_1"><br/>
    商品已不存在，请关闭重试！
</div>
<%
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
</body>
</html>