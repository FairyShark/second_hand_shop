<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="bean.OperationMes" %>
<%@ page import="dao.OperationMesDao" %>
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
    <title>操作日志</title>
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
        <h2>操 作 日 志</h2>
    </div>
    <div class="bottter">
        <div class="seach_1">
            <div class="typ_14">
                <label>会员ID：</label><input id="user_id" class="inp_11">
            </div>
            <div class="typ_16">
                <label>会员名：</label><input id="user_name" class="inp_8">
            </div>
            <div class="typ_16">
                <label>IP地址：</label><input id="user_ip" class="inp_13">
            </div>
            <div class="typ_18">
                <label>类型：</label> <select id="operation_type" name="Types">
                <option value="注册">注册</option>
                <option value="添加">添加</option>
                <option value="删除">删除</option>
                <option value="修改">修改</option>
                <option value="查询">查询</option>
                <option value="全部" selected="selected">全部</option>
            </select>
            </div>
            <div class="typ_19">
                <label>时间：</label><input id="operation_time" class="inp_15" placeholder="格式：yyyy-mm-dd">
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
                    <th>会员IP地址</th>
                    <th>操作类型</th>
                    <th>操作内容</th>
                    <th>操作时间</th>
                </tr>
                <%
                    try {
                        OperationMesDao lmdao = DAOFactory.getOperationMesServiceInstance();
                        List<OperationMes> OMList = lmdao.getAllOperationMes();
                        if (OMList != null) {
                            if (OMList.size() > 0) {
                                OperationMes operationmes;
                                int num = 0;
                                int uid_t;
                                String uname_t;
                                String userip_t;
                                String otype_t;
                                String opcontent_t;
                                String landtime_t;
                                for (int i = 0; i < OMList.size(); i++) {
                                    operationmes = OMList.get(i);
                                    uid_t = operationmes.getUid();
                                    uname_t = operationmes.getUname();
                                    userip_t = operationmes.getUserip();
                                    otype_t = operationmes.getOtype();
                                    opcontent_t = operationmes.getOpcontent();
                                    landtime_t = operationmes.getOperationtime();
                                    num++;
                %>
                <tr>
                    <td><%=num%>.</td>
                    <td><%=uid_t%>
                    </td>
                    <td><%
                        if (uid_t != 8) {
                    %>
                        <a href="<%=basePath%>jsp/showMessage.jsp?uid=<%=uid_t%>" target="_blank"><%=uname_t%>
                        </a>
                        <%
                        } else {
                        %>
                        admin
                        <%
                            }
                        %>
                    </td>
                    <td><%=userip_t%>
                    </td>
                    <td><%=otype_t%>
                    </td>
                    <%
                        if (opcontent_t.length() < 23) {
                    %>
                    <td><a class="theme-login" href="javascript:void(0)"
                           onclick="return loginclick('<%=uname_t%>','<%=opcontent_t%>','<%=landtime_t%>')"><%=opcontent_t%>
                    </a></td>
                    <%
                    } else {
                    %>
                    <td><a class="theme-login" href="javascript:void(0)"
                           onclick="return loginclick('<%=uname_t%>','<%=opcontent_t%>','<%=landtime_t%>')"><%=opcontent_t.substring(0, 20)%>
                        ···</a></td>
                    <%
                        }
                    %>
                    <td><%=landtime_t%>
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

<div class="theme-popover">
    <div class="theme-poptit">
        <a href="javascript:" title="关闭" class="close">×</a>
        <h3 id="htitle"></h3>
    </div>
    <div class="theme-popbod dform">
        <div class="theme-signin" id="loginform">
            <div><h4 id="htime"></h4></div>
            <div><strong id="ocontent"></strong></div>
            <div>
                <button class="btn btn-primary" onclick="return closeclick()">确 定</button>
            </div>
        </div>
    </div>
</div>
<div class="theme-popover-mask"></div>

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
        let UserIP = $("#user_ip").val();
        let Otype = $("#operation_type").val();
        let LandTime = $("#operation_time").val();
        let c = 0;
        if (LandTime == null || LandTime === "") {
            c = 1;
        } else {
            const bagin_r = LandTime.match(/^(\d{4})(-)(\d{2})(-)(\d{2})$/);
            if (bagin_r == null) {
                alert("日期格式不对，必须为“yyyy-mm-dd”，请重新输入！");
                $("#land_time").value = "";
            } else {
                const b_d = new Date(bagin_r[1], bagin_r[3] - 1, bagin_r[5]);
                const b_num = (b_d.getFullYear() == bagin_r[1] && (b_d.getMonth() + 1) == bagin_r[3] && b_d.getDate() == bagin_r[5]);
                if (b_num == 0) {
                    alert("时间不合法,请输入正确的时间！");
                    $("#land_time").value = "";
                } else {
                    c = 1;
                }
            }
        }
        if (c == 1) {
            if ((UserID == null || UserID === "")
                && (UserName == null || UserName === "")
                && (UserIP == null || UserIP === "")
                && (Otype === "全部")
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
                var re =  /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
                if(!isNaN(UserID)){
                	if(UserIP === "%&ALL&%" || re.test(UserIP)){
                $.ajax({
                    url: 'SelectOperationTServlet',
                    type: 'GET',
                    data: {
                        Userip: '<%=userip%>',
                        Uid: <%=uid%>,
                        UserID: UserID,
                        UserName: UserName,
                        UserIP: UserIP,
                        Otype: Otype,
                        LandTime: LandTime,
                    },
                    dataType: 'json',
                    success: function (json) {
                        $("#resultTable").empty();
                        const tr = $("<tr/>");
                        $("<th/>").html("序号").appendTo(tr);
                        $("<th/>").html("会员ID").appendTo(tr);
                        $("<th/>").html("会员名").appendTo(tr);
                        $("<th/>").html("会员IP地址").appendTo(tr);
                        $("<th/>").html("操作类型").appendTo(tr);
                        $("<th/>").html("操作内容").appendTo(tr);
                        $("<th/>").html("操作时间").appendTo(tr);
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
                            $("<td/>").html(val.userip).appendTo(tr);
                            $("<td/>").html(val.otype).appendTo(tr);
                            const td2 = $("<td/>");
                            const a2 = $("<a/>");
                            a2.attr("class", "theme-login");
                            a2.attr("href", "javascript:void(0)");
                            a2.attr("onclick", "return loginclick('" + val.uname + "','" + val.opcontent + "','" + val.operationtime + "')");
                            if (val.opcontent.length < 23) {
                                a2.html(val.opcontent).appendTo(td2);
                            } else {
                                a2.html(val.opcontent.substring(0, 20) + "···").appendTo(td2);
                            }
                            td2.appendTo(tr);
                            $("<td/>").html(val.operationtime).appendTo(tr);
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
            }else {
            	alert("IP地址格式不正确，请重新输入!")
            }
        }else {
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
