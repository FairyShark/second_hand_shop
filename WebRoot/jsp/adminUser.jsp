<%@ page import="bean.User" %>
<%@ page import="dao.UserDao" %>
<%@ page import="factory.DAOFactory" %>
<%@ page import="java.net.Inet4Address" %>
<%@ page import="java.net.InetAddress" %>
<%@ page import="java.util.List" %>
<%@ page pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";

    InetAddress ip4 = Inet4Address.getLocalHost();
    String userip = ip4.getHostAddress();
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
%>
<!DOCTYPE html>
<html>
<head>
    <title>会员管理</title>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="<%=basePath%>css/main.css" rel="stylesheet" type="text/css" media="all"/>
    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/responsiveslides.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/memenu.js"></script>
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
<jsp:include page="head.jsp"></jsp:include>

<div class="content goods_show">
    <div class="sear_w">
        <h2>会 员 管 理</h2>
    </div>
    <div class="bottter">
        <div class="seach_1">
            <div class="typ_6">
                <label>会员ID：</label><input id="user_id" class="inp_4">
            </div>
            <div class="typ_7">
                <label>会员名：</label><input id="user_name" class="inp_5">
            </div>
            <div class="typ_8">
                <label>邮箱：</label><input id="user_mail" class="inp_6">
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
                    <th>邮箱</th>
                    <th>登录次数(近30天)</th>
                    <th>最后登录时间</th>
                </tr>
                <%
                    try {
                        UserDao userDao = null;
                        userDao = DAOFactory.getUserServiceInstance();
                        List<User> UsersList = userDao.selectAllUser();
                        if (UsersList != null) {
                            if (UsersList.size() > 0) {
                                User user;
                                int num = 0;
                                int uid_t;
                                int frequency;
                                String uname_t;
                                String umail_t;
                                String lastlogin_t;
                                for (int i = 0; i < UsersList.size(); i++) {
                                    user = UsersList.get(i);
                                    if (user.getUid() == 8) continue;
                                    uid_t = user.getUid();
                                    uname_t = user.getUname();
                                    umail_t = user.getEmail();
                                    lastlogin_t = user.getLastLogin();
                                    frequency = user.getFrequency();
                                    num++;
                %>
                <tr>
                    <td><%=num%>.</td>
                    <td><%=uid_t%>
                    </td>
                    <td><%=uname_t%>
                    </td>
                    <td><%=umail_t%>
                    </td>
                    <td><%=frequency%>
                    </td>
                    <td><%=lastlogin_t%>
                    </td>
                    <td><a href="<%=basePath%>jsp/showMessage.jsp?uid=<%=uid_t%>" target="_blank">查看</a></td>
                    <td><a href="javascript:" onclick="CheckSaleChart(<%=uid_t%>)">销售</a></td>
                    <td><a href="javascript:" onclick="deleteuser(<%=uid_t%>)">删除</a></td>
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

    function deleteuser(uid) {
        if (confirm("确认删除该会员吗，同时会清空该会员的售卖商品？")) {
            $.ajax({
                type: "GET",
                url: "DeleteUserServlet",
                data: {
                    Uid: Number(uid),
                    UserIP: '<%=userip%>'
                },
                dataType: "json",
                success: function (data) {
                    if (data.isok === "1") {
                        alert("删除成功！");
                    } else {
                        alert("删除失败，请重试！");
                    }
                    location.reload();
                },
                error: function (err) {
                    alert("error");
                }
            });
        }
    }

    function clickSearch() {
        let UserID = $("#user_id").val();
        let UserName = $("#user_name").val();
        let UserMail = $("#user_mail").val();
        if ((UserID == null || UserID === "")
            && (UserName == null || UserName === "")
            && (UserMail == null || UserMail === "")) {
            window.location.reload();
        } else {
            if (UserID == null || UserID === "")
                UserID = -1;
            if (UserName == null || UserName === "")
                UserName = "%&ALL&%";
            if (UserMail == null || UserMail === "")
                UserMail = "%&ALL&%";
            const re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
            if (!isNaN(UserID)) {
                if (UserMail === "%&ALL&%" || re.test(UserMail)) {
                    $.ajax({
                        url: 'SelectUserServlet',
                        type: 'GET',
                        data: {
                            Userip: '<%=userip%>',
                            Uid: <%=uid%>,
                            UserID: UserID,
                            UserName: UserName,
                            UserMail: UserMail,
                        },
                        dataType: 'json',
                        success: function (json) {
                            $("#resultTable").empty();
                            const tr = $("<tr/>");
                            $("<th/>").html("序号").appendTo(tr);
                            $("<th/>").html("会员ID").appendTo(tr);
                            $("<th/>").html("会员名").appendTo(tr);
                            $("<th/>").html("邮箱").appendTo(tr);
                            $("<th/>").html("登录次数(近30天)").appendTo(tr);
                            $("<th/>").html("最后登陆时间").appendTo(tr);
                            $("#resultTable").append(tr);
                            let temp = 0;
                            $.each(json, function (i, val) {
                                if (val.uid != "8") {
                                    temp++;
                                    const tr = $("<tr/>");
                                    $("<td/>").html(temp + ".").appendTo(tr);
                                    $("<td/>").html(val.uid).appendTo(tr);
                                    $("<td/>").html(val.uname).appendTo(tr);
                                    $("<td/>").html(val.email).appendTo(tr);
                                    $("<td/>").html(val.frequency).appendTo(tr);
                                    $("<td/>").html(val.lastLogin).appendTo(tr);
                                    const td0 = $("<td/>");
                                    const a0 = $("<a/>");
                                    a0.attr("href", "<%=basePath%>jsp/showMessage.jsp?uid=" + val.uid);
                                    a0.attr("target", "_blank");
                                    a0.html("查看").appendTo(td1);
                                    td0.appendTo(tr);
                                    const td1 = $("<td/>");
                                    const a1 = $("<a/>");
                                    a1.attr("href", "javascript:");
                                    a1.attr("onclick", "CheckSaleChart(" + val.uid + ")");
                                    a1.html("销售").appendTo(td1);
                                    td1.appendTo(tr);
                                    const td2 = $("<td/>");
                                    const a2 = $("<a/>");
                                    a2.attr("href", "javascript:");
                                    a2.attr("onclick", "deleteuser(" + val.uid + ")");
                                    a2.html("删除").appendTo(td2);
                                    td2.appendTo(tr);
                                    $("#resultTable").append(tr);
                                }
                            });
                            if (temp == 0) {
                                $("#resultTable").empty();
                                $("#tempP").empty();
                                const p2 = $("<p/>");
                                p2.addClass("tempmess");
                                p2.html("暂时没有该类型的会员，换一个试试！").appendTo(p2);
                                $("#tempP").append(p2);
                            } else {
                                $("#tempP").empty();
                                const p3 = $("<p/>");
                                p3.addClass("tempmess");
                                p3.html("共找到" + temp + "个该类型的会员！").appendTo(p3);
                                $("#tempP").append(p3);
                            }
                        },
                        error: function () {
                            $("#test").append("条件查询错误！");
                        }

                    });
                } else {
                    alert("邮箱格式不正确，请重新输入!")
                }
            } else {
                alert("请输入正确的UID（整数）!")
            }
        }
    }

    function CheckSaleChart(uid){
        $.ajax({
            url: 'CheckSaleServlet',
            type: 'POST',
            data: {
                UID: uid
            },
            dataType: 'json',
            success: function (data) {
                if (data.isok === "1") {
                    window.open("<%=basePath%>jsp/saleChart.jsp?uid=" + uid, "_blank");
                } else {
                    alert("该会员没有销售记录！");
                }
            },
            error: function () {
                alert("error！");
            }
        });
    }

    document.onkeydown = function (event) {
        const e = event ? event : (window.event ? window.event : null);
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