<%@ page pageEncoding="utf-8" %>
<%@ page import="java.net.Inet4Address" %>
<%@ page import="java.net.InetAddress" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";

    InetAddress ip4 = Inet4Address.getLocalHost();
    String userip = ip4.getHostAddress();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>用户登录</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="<%=basePath%>css/user.css"/>
    <script src="<%=basePath%>js/kit.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/dom.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/form.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/validator.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/autowired.validator.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
    <script type="text/javascript">
        const _gaq = _gaq || [];
        _gaq.push(['_setAccount', 'UA-30210234-1']);
        _gaq.push(['_trackPageview']);
        (function () {
            const ga = document.createElement('script');
            ga.type = 'text/javascript';
            ga.async = true;
            ga.src = ('https:' === document.location.protocol ? 'https://ssl'
                : 'http://www')
                + '.google-analytics.com/ga.js';
            const s = document.getElementsByTagName('script')[0];
            s.parentNode.insertBefore(ga, s);
        })();
    </script>
</head>
<body>
<h1>登录</h1>
<div id="regist-main">
    <div id="registForm">
        <ol>
            <li class="re_li0"><label for="uname">用户名： <span class="kitjs-validator" for="@uname"
                                                             rules="[{notNull:true, message:'用户名不能为空'}]"></span></label>
                <span class="field-validation-valid" data-valmsg-for="uname" data-valmsg-replace="true"></span>
                <input id="uname" name="uname" type="text" value=""/>
            </li>
            <li class="re_li0">
                <label for="passwd">密码： <span class="kitjs-validator" for="@passwd"
                                              rules="[{notNull:true, message:'密码不能为空'},{minLength:'6',message:'密码长度最短为6位'}]"></span></label>
                <span class="field-validation-valid" data-valmsg-for="passwd" data-valmsg-replace="true"></span>
                <input id="passwd" name="passwd" type="password">
            </li>
        </ol>
        <div class="registError"></div>
        <div class="outer_log">
            <div class="but_log">
                <a href="javascript:" class="btn-submit-log" onclick="userlogin()">登陆</a>
                <a class="btn-submit-log" href="<%=basePath%>jsp/register.jsp">注册</a>
                <a href="<%=basePath%>jsp/index.jsp" class="btn-submit-log">取消</a>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function userlogin() {
        const uname = String($('#uname').val());
        const passwd = String($('#passwd').val());
        if(uname !== "" && passwd !== "") {
            if(passwd.length >= 6) {
                $.ajax({
                    type: "POST",
                    url: "LoginServlet",
                    data: {
                        uname: uname,
                        passwd: passwd,
                        userip: '<%=userip%>'
                    },
                    dataType: "json",
                    success: function (data) {
                        if (data.isok === "1") {
                            alert("不存在该用户，请重新输入!");
                            location.reload();
                        } else if (data.isok === "2") {
                            alert("密码错误，请重新输入!");
                            $('#passwd').val("");
                        } else if (data.isok === "3") {
                            window.location.href = "<%=basePath%>jsp/index.jsp";
                        } else {
                            window.location.href = "<%=basePath%>jsp/adminUser.jsp";
                        }
                    },
                    error: function (err) {
                        alert("error");
                    }
                });
            }
        }else if(uname === ""){
            alert("请输入用户名！");
        }else{
            alert("请输入密码！");
        }
    }

    document.onkeydown = function (event) {
        const e = event ? event : (window.event ? window.event : null);
        if (e.keyCode === 13) {
            userlogin();
        }
    };
</script>
</body>
</html>