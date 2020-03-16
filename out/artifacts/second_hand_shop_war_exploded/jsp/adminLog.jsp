<%@ page import="util.OnlineCounter" %>
<%@ page import="bean.LandMessage" %>
<%@ page import="java.util.List" %>
<%@ page import="factory.DAOFactory" %>
<%@ page import="dao.LandMessageDao" %>
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
    <title>登陆信息</title>
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
            <h2>登 陆 信 息</h2>
        </div>
        <div class="bottter">
            <div class="seach_1">
                <div class="typ_10">
                    <label>会员ID：</label><input id="user_id" class="inp_7">
                </div>
                <div class="typ_11">
                    <label>会员名：</label><input id="user_name" class="inp_8">
                </div>
                <div class="typ_12">
                    <label>登陆IP：</label><input id="user_ip" class="inp_9">
                </div>
                <div class="typ_13">
                    <label>登陆时间：</label><input id="land_time" class="inp_10" placeholder="格式：yyyy-mm-dd">
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
                        <th>登陆IP地址</th>
                        <th>登录时间</th>
                        <th>退出时间</th>
                    </tr>
                    <%
                        try {
                            LandMessageDao lmdao = DAOFactory.getLandMessageServiceInstance();
                            List<LandMessage> LMList = lmdao.getAllLandMessage();
                            if (LMList != null) {
                                if (LMList.size() > 0) {
                                    LandMessage landmessage;
                                    int num = 0;
                                    int uid_t;
                                    String uname_t;
                                    String userip_t;
                                    String landtime_t;
                                    String canceltime_t;
                                    for (int i = 0; i < LMList.size(); i++) {
                                        landmessage = LMList.get(i);
                                        uid_t = landmessage.getUid();
                                        uname_t = landmessage.getUname();
                                        userip_t = landmessage.getUserip();
                                        landtime_t = landmessage.getLandtime();
                                        canceltime_t = landmessage.getCanceltime();
                                        num++;
                    %>
                    <tr>
                        <td><%=num%>.</td>
                        <td><%=uid_t%>
                        </td>
                        <td><a href="<%=basePath%>/jsp/showMessage.jsp?uid=<%=uid_t%>" target="_blank"><%=uname_t%>
                        </a>
                        </td>
                        <td><%=userip_t%>
                        </td>
                        <td><%=landtime_t%>
                        </td>
                        <td><%=canceltime_t%>
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
        let UserIP = $("#user_ip").val();
        let LandTime = $("#land_time").val();
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
                && (UserIP == null || UserIP === "")
                && (LandTime == null || LandTime === "")) {
                window.location.reload();
            } else {
                if (UserID == null || UserID === "")
                    UserID = -1;
                if (UserName == null || UserName === "")
                    UserName = "%&ALL&%";
                if (UserIP == null || UserIP === "")
                    UserIP = "%&ALL&%";
                if (LandTime == null || LandTime === "")
                    LandTime = "%&ALL&%";
                $.ajax({
                    url: 'SelectLandTServlet',
                    type: 'GET',
                    data: {
                        UserIP: UserIP,
                        UserID: UserID,
                        UserName: UserName,
                        LandTime: LandTime,
                    },
                    dataType: 'json',
                    success: function (json) {
                        $("#resultTable").empty();
                        const tr = $("<tr/>");
                        $("<th/>").html("序号").appendTo(tr);
                        $("<th/>").html("会员ID").appendTo(tr);
                        $("<th/>").html("会员名").appendTo(tr);
                        $("<th/>").html("登陆IP地址").appendTo(tr);
                        $("<th/>").html("登陆时间").appendTo(tr);
                        $("<th/>").html("退出时间").appendTo(tr);
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
                            $("<td/>").html(val.userip).appendTo(tr);
                            $("<td/>").html(val.landtime).appendTo(tr);
                            $("<td/>").html(val.canceltime).appendTo(tr);
                            $("#resultTable").append(tr);

                        });
                        if (temp == 0) {
                            $("#resultTable").empty();
                            $("#tempP").empty();
                            const p2 = $("<p/>");
                            p2.addClass("tempmess");
                            p2.html("暂时没有该类型的信息，换一个试试！").appendTo(p2);
                            $("#tempP").append(p2);
                        } else {
                            $("#tempP").empty();
                            const p3 = $("<p/>");
                            p3.addClass("tempmess");
                            p3.html("共找到" + temp + "个该类型的信息！").appendTo(p3);
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