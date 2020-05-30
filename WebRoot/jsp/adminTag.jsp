<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dao.UserTagDao" %>
<%@ page import="factory.DAOFactory" %>
<%@ page import="java.net.Inet4Address" %>
<%@ page import="java.net.InetAddress" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.UserTag" %>
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
    <title>行为标签</title>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="<%=basePath%>css/main.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="<%=basePath%>css/popup.css" rel="stylesheet" type="text/css" media="all"/>
    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/responsiveslides.min.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function ($) {

            $('.theme-poptit .close').click(function () {
                $('.theme-popover-mask').fadeOut(100);
                $('.theme-popover').slideUp(200);
            });

        });

        function loginclick(name, content, time) {
            $('.theme-popover-mask').fadeIn(100);
            $('.theme-popover').slideDown(200);
            $('#htitle').html("查看 " + name + " 的操作内容");
            $('#ocontent').html(content);
            $('#htime').html("操作时间： " + time);
        }

        function closeclick() {
            $('.theme-popover-mask').fadeOut(100);
            $('.theme-popover').slideUp(200);
        }

    </script>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>

<div class="content goods_show">
    <div class="sear_w">
        <h2>行 为 标 签</h2>
    </div>
    <div class="bottter">
        <div class="seach_1">
            <div class="typ_14">
                <label>会员ID：</label><input id="user_id" class="inp_11">
            </div>
            <div class="typ_16">
                <label>会员名：</label><input id="user_name" class="inp_8">
            </div>
            <div class="typ_18">
                <label>行为：</label> <select id="active_type" name="Act_Types">
                <option value="全部" selected="selected">全部</option>
                <option value="搜索">搜索</option>
                <option value="浏览">浏览</option>
                <option value="收藏">收藏</option>
                <option value="购买">购买</option>
                <option value="取消收藏">取消收藏</option>
                <option value="加入购物车">加入购物车</option>
                <option value="移出购物车">移出购物车</option>
            </select>
            </div>
            <div class="typ_18">
                <label>商品：</label> <select id="goods_type" name="God_Types">
                <option value="全部" selected="selected">全部</option>
                <option value="文具">文具</option>
                <option value="书籍">书籍</option>
                <option value="食品">食品</option>
                <option value="日用品">日用品</option>
                <option value="电子产品">电子产品</option>
                <option value="其他">其他</option>
            </select>
            </div>
            <div class="typ_19">
                <label>时间：</label><input id="tag_time" class="inp_15" placeholder="格式：yyyy-mm-dd">
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
                    <th>行为类型</th>
                    <th>商品类型</th>
                    <th>权重</th>
                    <th>标记时间</th>
                </tr>
                <%
                    try {
                        UserTagDao utdao = DAOFactory.getUserTagServiceInstance();
                        List<UserTag> UTList = utdao.getAllUserTag();
                        if (UTList != null) {
                            if (UTList.size() > 0) {
                                UserTag usertag;
                                int num = 0;
                                int uid_t;
                                String name_t;
                                String act_type_t;
                                String goods_type_t;
                                int tag_weight_t;
                                String tag_time_t;
                                for (int i = 0; i < UTList.size(); i++) {
                                    usertag = UTList.get(i);
                                    uid_t = usertag.getUid();
                                    name_t = usertag.getUname();
                                    act_type_t = usertag.getActtype();
                                    goods_type_t = usertag.getTagtype();
                                    tag_weight_t = usertag.getTagweight();
                                    tag_time_t = usertag.getTagtime();
                                    num++;
                %>
                <tr>
                    <td><%=num%>.</td>
                    <td><%=uid_t%>
                    </td>
                    <td><%
                        if (uid_t != 8) {
                    %>
                        <a href="<%=basePath%>jsp/showMessage.jsp?uid=<%=uid_t%>" target="_blank"><%=name_t%>
                        </a>
                        <%
                        } else {
                        %>
                        admin
                        <%
                            }
                        %>
                    </td>
                    <td><%=act_type_t%>
                    </td>
                    <td><%=goods_type_t%>
                    </td>
                    <td><%=tag_weight_t%>
                    </td>
                    <td><%=tag_time_t%>
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
        let ActType = $("#active_type").val();
        let GodType = $("#goods_type").val();
        let TagTime = $("#tag_time").val();
        let c = 0;
        if (TagTime == null || TagTime === "") {
            c = 1;
        } else {
            const bagin_r = TagTime.match(/^(\d{4})(-)(\d{2})(-)(\d{2})$/);
            if (bagin_r == null) {
                alert("日期格式不对，必须为“yyyy-mm-dd”，请重新输入！");
                $("#tag_time").value = "";
            } else {
                const b_d = new Date(bagin_r[1], bagin_r[3] - 1, bagin_r[5]);
                const b_num = (b_d.getFullYear() === bagin_r[1] && (b_d.getMonth() + 1) === bagin_r[3] && b_d.getDate() === bagin_r[5]);
                if (b_num === 0) {
                    alert("时间不合法,请输入正确的时间！");
                    $("#tag_time").value = "";
                } else {
                    c = 1;
                }
            }
        }
        if (c === 1) {
            if ((UserID == null || UserID === "")
                && (UserName == null || UserName === "")
                && (ActType === "全部")
                && (GodType === "全部")
                && (TagTime == null || TagTime === "")) {
                window.location.reload();
            } else {
                if (UserID == null || UserID === "")
                    UserID = -1;
                if (UserName == null || UserName === "")
                    UserName = "%&ALL&%";
                if (TagTime == null || TagTime === "")
                    TagTime = "%&ALL&%";
                var re = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
                if (!isNaN(UserID)) {
                    $.ajax({
                        url: 'SelectUserTagServlet',
                        type: 'GET',
                        data: {
                            Uid: <%=uid%>,
                            Userip: '<%=userip%>',
                            UserID: UserID,
                            UserName: UserName,
                            ActType: ActType,
                            GodType: GodType,
                            TagTime: TagTime
                        },
                        dataType: 'json',
                        success: function (json) {
                            $("#resultTable").empty();
                            const tr = $("<tr/>");
                            $("<th/>").html("序号").appendTo(tr);
                            $("<th/>").html("会员ID").appendTo(tr);
                            $("<th/>").html("会员名").appendTo(tr);
                            $("<th/>").html("行为类型").appendTo(tr);
                            $("<th/>").html("商品类型").appendTo(tr);
                            $("<th/>").html("权重").appendTo(tr);
                            $("<th/>").html("标记时间").appendTo(tr);
                            $("#resultTable").append(tr);
                            let temp = 0;
                            $.each(json, function (i, val) {
                                temp++;
                                const tr = $("<tr/>");
                                $("<td/>").html(temp + ".").appendTo(tr);
                                $("<td/>").html(val.uid).appendTo(tr);
                                if (val.uid == 8) {
                                    $("<td/>").html(val.uname).appendTo(tr);
                                } else {
                                    const td1 = $("<td/>");
                                    const a1 = $("<a/>");
                                    a1.attr("href", "<%=basePath%>jsp/showMessage.jsp?uid=" + val.uid);
                                    a1.attr("target", "_blank");
                                    a1.html(val.uname).appendTo(td1);
                                    td1.appendTo(tr);
                                }
                                $("<td/>").html(val.acttype).appendTo(tr);
                                $("<td/>").html(val.tagtype).appendTo(tr);
                                $("<td/>").html(val.tagweight).appendTo(tr);
                                $("<td/>").html(val.tagtime).appendTo(tr);
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
                } else {
                    alert("请输入正确的UID（整数）!")
                }
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
