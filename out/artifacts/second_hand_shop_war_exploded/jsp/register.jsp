<%@ page language="java" pageEncoding="utf-8" %>
<%@ page import="java.net.Inet4Address" %>
<%@ page import="java.net.InetAddress" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";

    InetAddress ip4 = Inet4Address.getLocalHost();
    String userip = ip4.getHostAddress();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>用户注册</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="css/user.css"/>
    <script src="<%=basePath%>/js/kit.js" type="text/javascript"></script>
    <script src="<%=basePath%>/js/dom.js" type="text/javascript"></script>
    <script src="<%=basePath%>/js/form.js" type="text/javascript"></script>
    <script src="<%=basePath%>/js/validator.js" type="text/javascript"></script>
    <script src="<%=basePath%>/js/autowired.validator.js" type="text/javascript"></script>
    <script type="text/javascript">
        var _gaq = _gaq || [];
        _gaq.push(['_setAccount', 'UA-30210234-1']);
        _gaq.push(['_trackPageview']);
        (function () {
            var ga = document.createElement('script');
            ga.type = 'text/javascript';
            ga.async = true;
            ga.src = ('https:' == document.location.protocol ? 'https://ssl'
                : 'http://www')
                + '.google-analytics.com/ga.js';
            var s = document.getElementsByTagName('script')[0];
            s.parentNode.insertBefore(ga, s);
        })();

        function goback() {
            window.history.back(-1);
        }
    </script>
</head>
<body>
<h1>注册</h1>
<div id="regist-main">
    <form id="registForm" action="servlet/RegisterServlet" method="post">
        <input id="userip" name="userip" value="<%=userip%>" type="hidden"/>
        <ol>
            <li><label for="uname">用户名： <span
                    class="kitjs-validator" for="@uname"
                    rules="[{notNull:true, message:'用户名不能为空'}]"></span>
            </label> <span class="field-validation-valid" data-valmsg-for="uname"
                           data-valmsg-replace="true"></span> <input id="uname" name="uname"
                                                                     type="text" value=""></li>

            <li><label for="Email">邮箱地址： <span
                    class="kitjs-validator" for="@Email"
                    rules="[{notNull:true, message:'邮箱地址不能为空'},{isEmail:true,message:'邮箱地址格式不正确'}]"></span>
            </label> <span class="field-validation-valid" data-valmsg-for="Email"
                           data-valmsg-replace="true"></span> <input id="Email" name="Email"
                                                                     type="text" value=""></li>

            <li><label for="passwd">密码： <span
                    class="kitjs-validator" for="@passwd"
                    rules="[{notNull:true, message:'密码不能为空'},{minLength:'6',message:'密码长度最短为6位'}]"></span>
            </label> <span class="field-validation-valid" data-valmsg-for="passwd"
                           data-valmsg-replace="true"></span> <input id="passwd" name="passwd"
                                                                     type="password"></li>
            <li><label for="Confirmpasswd">重新输入密码： <span
                    class="kitjs-validator" for="@Confirmpasswd"
                    rules="[{notNull:true, message:'密码不能为空'},{equalWith:'@passwd',message:'密码输入不一致'},{minLength:'6',message:'密码长度最短为6位'}]"></span>
            </label> <span class="field-validation-valid"
                           data-valmsg-for="Confirmpasswd" data-valmsg-replace="true"></span>
                <input id="Confirmpasswd" name="Confirmpasswd" type="password">
            </li>
        </ol>
        <div class="registError"></div>
        <div class="outer_log">
            <div class="but_log">
                <input type="submit" value="注册" class="btn-submit-reg">
                <input type="button" value="取消" class="btn-submit-reg" onclick="goback()">
            </div>
        </div>
    </form>
</div>
</body>
</html>