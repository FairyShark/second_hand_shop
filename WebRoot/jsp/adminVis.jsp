<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="util.OnlineCounter" %>
<%@ page import="bean.VisitMessage" %>
<%@ page import="java.util.List" %>
<%@ page import="factory.DAOFactory" %>
<%@ page import="dao.VisitMessageDao" %>
<%@ page import="java.net.Inet4Address" %>
<%@ page import="java.net.InetAddress" %>
<%@ page pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";

    InetAddress ip4 = Inet4Address.getLocalHost();
    String userip = ip4.getHostAddress();
%>
<%
    String uid = String.valueOf(session.getAttribute("uid"));
%>
<!DOCTYPE html>
<html>
<head>
    <title>浏览记录</title>
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
            const myDate = new Date();
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
                        out.print("<a>" + "你好管理员,欢迎登录" + "</a>");
                        out.print("<a href=\"servlet/LogoutServlet\" onClick=\"return key()\">" + "注销" + "</a>");
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
                    <li class=" grid"><a href="<%=basePath%>/jsp/adminUser.jsp" class="a_active">会员管理</a></li>
                    <li><a href="<%=basePath%>/jsp/adminGoods.jsp">商品管理</a></li>
                    <li><a href="<%=basePath%>/jsp/adminLog.jsp">登陆信息</a></li>
                    <li><a href="<%=basePath%>/jsp/adminVis.jsp">浏览记录</a></li>
                    <li><a href="<%=basePath%>/jsp/adminOpe.jsp">操作日志</a></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="content goods_show">
        <div class="sear_w">
            <h2>浏 览 记 录</h2>
        </div>
        <div class="bottter_1">
            <div class="seach_1">
                <div class="typ_14">
                    <label>会员ID：</label><input id="user_id" class="inp_11">
                </div>
                <div class="typ_15">
                    <label>商品ID：</label><input id="goods_id" class="inp_12">
                </div>
                <div class="typ_16">
                    <label>会员名：</label><input id="user_name" class="inp_13">
                </div>
                <div class="typ_17">
                    <label>商品名：</label><input id="goods_name" class="inp_14">
                </div>
                <div class="typ_18">
                    <label>商品类型：</label> <select id="goods_type" name="Types">
                    <option value="文具">文具</option>
                    <option value="书籍">书籍</option>
                    <option value="食品">食品</option>
                    <option value="日用品">日用品</option>
                    <option value="电子产品">电子产品</option>
                    <option value="其他">其他</option>
                    <option value="全部" selected="selected">全部</option>
                </select>
                </div>
                <div class="typ_19">
                    <label>时间：</label><input id="goods_time" class="inp_15" placeholder="格式：yyyy-mm-dd">
                </div>
                <div class="typ_9">
                    <button class="but_1" onclick="clickSearch()">搜索</button>
                </div>
            </div>

        </div>

        <div class="container">
            <div class="content-top">
                <table id="resultTable">
                    <tr>
                        <th>序号</th>
                        <th>会员ID</th>
                        <th>会员名</th>
                        <th>商品ID</th>
                        <th>商品名</th>
                        <th>商品类型</th>
                        <th>浏览时间</th>
                        <th>持续时间</th>
                    </tr>
                    <%
                        try {
                            VisitMessageDao lmdao = DAOFactory.getVisitMessageServiceInstance();
                            List<VisitMessage> VMList = lmdao.getAllVisitMessage();
                            if (VMList != null) {
                                if (VMList.size() > 0) {
                                    VisitMessage visitmessage;
                                    int num = 0;
                                    int uid_t;
                                    int gid_t;
                                    String uname_t;
                                    String gname_t;
                                    String gtype_t;
                                    String landtime_t;
                                    int lasttime_t;
                                    for (int i = 0; i < VMList.size(); i++) {
                                        visitmessage = VMList.get(i);
                                        uid_t = visitmessage.getUid();
                                        gid_t = visitmessage.getGid();
                                        uname_t = visitmessage.getUname();
                                        gname_t = visitmessage.getGname();
                                        gtype_t = visitmessage.getTypes();
                                        landtime_t = visitmessage.getLandtime();
                                        lasttime_t = visitmessage.getLasttime();
                                        num++;
                    %>
                    <tr>
                        <td><%=num%>.</td>
                        <td><%=uid_t%>
                        </td>
                        <td><a href="<%=basePath%>/jsp/showMessage.jsp?uid=<%=uid_t%>" target="_blank"><%=uname_t%>
                        </a>
                        </td>
                        <td><%=gid_t%>
                        </td>
                        <td><a href="<%=basePath%>/jsp/goodsDescribed.jsp?gid=<%=gid_t%>" target="_blank"><%=gname_t%>
                        </a>
                        </td>
                        <td><%=gtype_t%>
                        </td>
                        <td><%=landtime_t%>
                        </td>
                        <td><%=lasttime_t%>秒
                        </td>
                    </tr>
                    <%
                                }
                            }
                        }
                    %>
                </table>
                <div id="tempP"></div>
            </div>
        </div>
    </div>
    <div class="bottom_tools">
        <a id="scrollUp" href="javascript:" title="回到顶部"></a>
    </div>
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
        let UserID = $("#user_id").val();
        let UserName = $("#user_name").val();
        let GoodsID = $("#goods_id").val();
        let GoodsName = $("#goods_name").val();
        let Gtype = $("#goods_type").val();
        let LandTime = $("#goods_time").val();
        let c = 0;
        if (LandTime == null || LandTime === "") {
            c = 1;
        } else {
            const bagin_r = LandTime.match(/^(\d{4})(-)(\d{2})(-)(\d{2})$/);
            if (bagin_r == null) {
                alert("日期格式不对，必须为“yyyy-mm-dd”，请重新输入！");
                $("#land_time").value="";
            } else {
                const b_d = new Date(bagin_r[1], bagin_r[3] - 1, bagin_r[5]);
                const b_num = (b_d.getFullYear() == bagin_r[1] && (b_d.getMonth() + 1) == bagin_r[3] && b_d.getDate() == bagin_r[5]);
                if (b_num == 0) {
                    alert("时间不合法,请输入正确的时间！");
                    $("#land_time").value="";
                } else {
                    c = 1;
                }
            }
        }
        if (c == 1) {
            if ((UserID == null || UserID === "")
                && (UserName == null || UserName === "")
                && (GoodsID == null || GoodsID === "")
                && (GoodsName == null || GoodsName === "")
                && (Gtype=== "全部")
                && (LandTime == null || LandTime === "")) {
                window.location.reload();
            } else {
                if (UserID == null || UserID === "")
                    UserID = -1;
                if (UserName == null || UserName === "")
                    UserName = "%&ALL&%";
                if (GoodsID == null || GoodsID === "")
                    GoodsID = -1;
                if (GoodsName == null || GoodsName === "")
                    GoodsName = "%&ALL&%";
                if (LandTime == null || LandTime === "")
                    LandTime = "%&ALL&%";
                $.ajax({
                    url: 'SelectVisitTServlet',
                    type: 'GET',
                    data: {
                        UserID: UserID,
                        UserName: UserName,
                        GoodsID: GoodsID,
                        GoodsName: GoodsName,
                        Gtype: Gtype,
                        LandTime: LandTime,
                    },
                    dataType: 'json',
                    success: function (json) {
                        $("#resultTable").empty();
                        const tr = $("<tr/>");
                        $("<th/>").html("序号").appendTo(tr);
                        $("<th/>").html("会员ID").appendTo(tr);
                        $("<th/>").html("会员名").appendTo(tr);
                        $("<th/>").html("商品ID").appendTo(tr);
                        $("<th/>").html("商品名").appendTo(tr);
                        $("<th/>").html("商品类型").appendTo(tr);
                        $("<th/>").html("浏览时间").appendTo(tr);
                        $("<th/>").html("持续时间").appendTo(tr);
                        $("#resultTable").append(tr);
                        let temp = 0;
                        $.each(json, function (i, val) {
                            temp++;
                            const tr = $("<tr/>");
                            $("<td/>").html(temp + ".").appendTo(tr);
                            $("<td/>").html(val.uid).appendTo(tr);
                            const td1 = $("<td/>");
                            const a1 = $("<a/>");
                            a1.attr("href", "<%=basePath%>/jsp/showMessage.jsp?uid=" + val.uid);
                            a1.attr("target", "_blank");
                            a1.html(val.uname).appendTo(td1);
                            td1.appendTo(tr);
                            $("<td/>").html(val.gid).appendTo(tr);
                            const td2 = $("<td/>");
                            const a2 = $("<a/>");
                            a2.attr("href", "<%=basePath%>/jsp/goodsDescribed.jsp?gid=" + val.gid);
                            a2.attr("target", "_blank");
                            a2.html(val.gname).appendTo(td2);
                            td2.appendTo(tr);
                            $("<td/>").html(val.types).appendTo(tr);
                            $("<td/>").html(val.landtime).appendTo(tr);
                            $("<td/>").html(val.lasttime + "秒").appendTo(tr);
                            $("#resultTable").append(tr);

                        });
                        if (temp == 0) {
                            $("#resultTable").empty();
                            $("#tempP").empty();
                            const p2 = $("<p/>");
                            p2.addClass("tempmess");
                            p2.html("暂时没有该类型的记录，换一个试试！").appendTo(p2);
                            $("#tempP").append(p2);
                        } else {
                            $("#tempP").empty();
                            const p3 = $("<p/>");
                            p3.addClass("tempmess");
                            p3.html("共找到" + temp + "个该类型的记录！").appendTo(p3);
                            $("#tempP").append(p3);
                        }
                    },
                    error: function () {
                        $("#test").append("条件查询错误！");
                    }

                });
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
<%
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
<script type="text/javascript">
    window.onunload = function () {
        navigator.sendBeacon("servlet/LogCancelTServlet");
    }
</script>
</body>
</html>
