<%@ page pageEncoding="utf-8" %>
<%@ page import="bean.User" %>
<%@ page import="dao.UserDao" %>
<%@ page import="factory.DAOFactory" %>
<%@ page import="java.net.Inet4Address" %>
<%@ page import="java.net.InetAddress" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";

    InetAddress ip4 = Inet4Address.getLocalHost();
    String userip = ip4.getHostAddress();
%>
<%
    int uid = Integer.parseInt(request.getParameter("uid"));
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>个人信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="<%=basePath%>css/user.css"/>
    <script src="<%=basePath%>js/kit.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/dom.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/form.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/validator.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/autowired.validator.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
    <script type="text/javascript">
        var _gaq = _gaq || [];
        _gaq.push(['_setAccount', 'UA-30210234-1']);
        _gaq.push(['_trackPageview']);
        (function () {
            var ga = document.createElement('script');
            ga.type = 'text/javascript';
            ga.async = true;
            ga.src = ('https:' === document.location.protocol ? 'https://ssl'
                : 'http://www')
                + '.google-analytics.com/ga.js';
            var s = document.getElementsByTagName('script')[0];
            s.parentNode.insertBefore(ga, s);
        })();
    </script>
</head>
<body>
<%

    try {
        UserDao udao = DAOFactory.getUserServiceInstance();
        if (udao.queryByUid(uid) != null) {
%>
<h1>个人信息</h1>
<div id="regist-main">
    <div id="registForm">
        <ol>
            <%
                try {
                    User user = udao.queryByUid(uid);
                    if (user != null) {
            %>

            <li class="re_li"><label for="UserName">用户名： <span
                    class="kitjs-validator" for="@UserName"
                    rules="[{notNull:true, message:'用户名不能为空'}]"></span>
            </label> <span class="field-validation-valid" data-valmsg-for="UserName"
                           data-valmsg-replace="true"></span> <input id="UserName"
                                                                     name="UserName" type="text"
                                                                     value="<%=user.getUname()%>"
                                                                     disabled="disabled"></li>

            <li class="re_li"><label for="Email">邮箱地址： <span
                    class="kitjs-validator" for="@Email"
                    rules="[{notNull:true, message:'电子邮件不能为空'},{isEmail:true,message:'电子邮件格式不正确'}]"></span>
            </label> <span class="field-validation-valid" data-valmsg-for="Email"
                           data-valmsg-replace="true"></span> <input id="Email" name="Email"
                                                                     type="text" value="<%=user.getEmail()%>"
                                                                     disabled="disabled"></li>

            <li class="re_li"><label for="Password">密码： <span
                    class="kitjs-validator" for="@Password"
                    rules="[{notNull:true, message:'密码不能为空'},{minLength:'6',message:'密码长度最少为6位'}]"></span>
            </label> <span class="field-validation-valid" data-valmsg-for="Password"
                           data-valmsg-replace="true"></span> <input id="Password"
                                                                     name="Password" type="password"
                                                                     value="<%=user.getPasswd()%>"
                                                                     disabled="disabled"></li>

            <li class="re_li"><label for="lasttime">上次登录时间： </label> <input id="lasttime"
                                                                            name="lasttime" type="text"
                                                                            value="<%=user.getLastLogin()%>"
                                                                            disabled="disabled"></li>
            <%
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            %>
        </ol>
        <div class="registError"></div>
        <div class="outer_log0">
            <div class="but_log">
                <input id="submit" type="button" value="编辑个人信息"
                       class="btn-submit-mes" onclick="edituserinf()"/>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function edituserinf() {
        if (document.getElementById("submit").value === "编辑个人信息") {
            document.getElementById("Email").disabled = false;
            document.getElementById("Password").disabled = false;
            document.getElementById("submit").value = "确认修改";
        } else {
            const Password = String($('#Password').val());
            const Email = String($('#Email').val());
            if (Password !== "" && Email !== "") {
                const re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
                if (re.test(Email)) {
                    if (Password.length >= 6) {
                        if (confirm("确定修改吗?")) {
                            $.ajax({
                                type: "POST",
                                url: "EditInfoServlet",
                                data: {
                                    userid: <%=uid%>,
                                    Password: Password,
                                    Email: Email,
                                    userip: '<%=userip%>'
                                },
                                dataType: "json",
                                success: function (data) {
                                    if (data.isok === "1") {
                                        alert("修改成功!");
                                    } else {
                                        alert("修改失败，该邮箱已被占用，请重试!");
                                    }
                                    location.reload();
                                },
                                error: function (err) {
                                    alert("error");
                                }
                            });
                        }
                    }
                }
            } else if (uname === "") {
                alert("请输入注册的用户名！");
            } else if (Email === "") {
                alert("请输入注册的邮箱！");
            } else if (passwd === "") {
                alert("请输入密码！");
            } else if (confirmpasswd === "") {
                alert("请再次输入密码！");
            }
        }
    }

    window.onunload = function () {
        navigator.sendBeacon("servlet/LogCancelTServlet");
    }
</script>
<%
} else {
%>
<div class="delete_1"><br/>
    用户已不存在，请关闭重试！
</div>
<%
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
</body>
</html>