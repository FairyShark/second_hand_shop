<%@ page pageEncoding="utf-8" %>
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
<h1>注册</h1>
<div id="regist-main">
    <div id="registForm">
        <ol>
            <li class="re_li"><label for="uname">用户名： <span
                    class="kitjs-validator" for="@uname"
                    rules="[{notNull:true, message:'用户名不能为空'}]"></span>
            </label> <span class="field-validation-valid" data-valmsg-for="uname"
                           data-valmsg-replace="true"></span> <input id="uname" name="uname"
                                                                     type="text" value="" /></li>

            <li class="re_li"><label for="Email">邮箱地址： <span
                    class="kitjs-validator" for="@Email"
                    rules="[{notNull:true, message:'邮箱地址不能为空'},{isEmail:true,message:'邮箱地址格式不正确'}]"></span>
            </label> <span class="field-validation-valid" data-valmsg-for="Email"
                           data-valmsg-replace="true"></span> <input id="Email" name="Email"
                                                                     type="text" value="" /></li>

            <li class="re_li"><label for="passwd">密码： <span
                    class="kitjs-validator" for="@passwd"
                    rules="[{notNull:true, message:'密码不能为空'},{minLength:'6',message:'密码长度最短为6位'}]"></span>
            </label> <span class="field-validation-valid" data-valmsg-for="passwd"
                           data-valmsg-replace="true"></span> <input id="passwd" name="passwd"
                                                                     type="password" /></li>
            <li class="re_li"><label for="Confirmpasswd">重新输入密码： <span
                    class="kitjs-validator" for="@Confirmpasswd"
                    rules="[{notNull:true, message:'密码不能为空'},{equalWith:'@passwd',message:'密码输入不一致'},{minLength:'6',message:'密码长度最短为6位'}]"></span>
            </label> <span class="field-validation-valid"
                           data-valmsg-for="Confirmpasswd" data-valmsg-replace="true"></span>
                <input id="Confirmpasswd" name="Confirmpasswd" type="password" />
            </li>
        </ol>
        <div class="registError"></div>
        <div class="outer_log0">
            <div class="but_log">
                <a href="javascript:" class="btn-submit-reg" onclick="userregister()">注册</a>
                <a href="javascript:" class="btn-submit-reg" onclick="goback()">取消</a>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function userregister(){
        const uname = String($('#uname').val());
        const passwd = String($('#passwd').val());
        const confirmpasswd = String($('#Confirmpasswd').val());
        const Email = String($('#Email').val());
        const re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
        if(uname !== "" && passwd !== "" && confirmpasswd !== "" && Email !== "") {
            if(re.test(Email)) {
                if (passwd.length >= 6 && confirmpasswd.length >= 6) {
                    if (passwd === confirmpasswd) {
                        $.ajax({
                            type: "POST",
                            url: "RegisterServlet",
                            data: {
                                uname: uname,
                                passwd: passwd,
                                Email: Email,
                                userip: '<%=userip%>'
                            },
                            dataType: "json",
                            success: function (data) {
                                if (data.isok === "1") {
                                    alert("注册成功!");
                                    window.location.href = "<%=basePath%>jsp/login.jsp";
                                } else {
                                    alert("用户名或邮箱已被占用，请重新输入!");
                                }
                            },
                            error: function (err) {
                                alert("error");
                            }
                        });
                    }
                }
            }
        }else if(uname === ""){
            alert("请输入注册的用户名！");
        }else if(Email === ""){
            alert("请输入注册的邮箱！");
        }else if(passwd === "" && re.test(Email)){
            alert("请输入密码！");
        }else if(confirmpasswd === "" && re.test(Email) && passwd.length >= 6){
            alert("请再次输入密码！");
        }
    }

    document.onkeydown = function (event) {
        const e = event ? event : (window.event ? window.event : null);
        if (e.keyCode === 13) {
            userregister();
        }
    };

    function goback() {
        location.href = document.referrer;
    }
</script>
</body>
</html>