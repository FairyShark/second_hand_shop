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
    <div id="InfoForm">
        <ol>
            <%
                try {
                    User user = udao.queryByUid(uid);
                    if (user != null) {
                    	String user_sex = "";
                    	String user_birth = "";
                    	String user_tel = "";
                    	String user_addr = "";
                    	if(!user.getSex().equals("未知")){
                    		user_sex = user.getSex();
                    	}
                    	if(!user.getBirth().equals("1900-01-01")){
                    		user_birth = user.getBirth();
                    	}
                    	if(!user.getTel().equals("未知")){
                    		user_tel = user.getTel();
                    	}
                    	if(!user.getAddr().equals("未知")){
                    		user_addr = user.getAddr();
                    	}
            %>

            <li class="re_li"><label for="UserName"><span class="necessary">*</span>用户名： <span
                    class="kitjs-validator" for="@UserName"
                    rules="[{notNull:true, message:'用户名不能为空'}]"></span>
            </label> <span class="field-validation-valid" data-valmsg-for="UserName"
                           data-valmsg-replace="true"></span> <input id="UserName"
                                                                     name="UserName" type="text"
                                                                     value="<%=user.getUname()%>"
                                                                     disabled="disabled"></li>
                                                                     
            <li class="re_li"><label for="UserSex">性别： 
            </label> <span class="error_mes" id="sex_mes"></span> <input id="UserSex"
                                                                     name="UserSex" type="text"
                                                                     value="<%=user_sex%>"
                                                                     disabled="disabled"></li>
            <li class="re_li"><label for="UserBirth">出生日期： 
            </label> <span class="error_mes" id="birth_mes"></span> <input id="UserBirth"
                                                                     name="UserBirth" type="text"
                                                                     value="<%=user_birth%>"
                                                                     disabled="disabled"></li>

            <li class="re_li"><label for="Email"><span class="necessary">*</span>邮箱： <span
                    class="kitjs-validator" for="@Email"
                    rules="[{notNull:true, message:'电子邮件不能为空'},{isEmail:true,message:'电子邮件格式不正确'}]"></span>
            </label> <span class="field-validation-valid" data-valmsg-for="Email"
                           data-valmsg-replace="true"></span> <input id="Email" name="Email"
                                                                     type="text" value="<%=user.getEmail()%>"
                                                                     disabled="disabled"></li>
                                                                     
            <li class="re_li"><label for="Tel">手机： 
            </label> <span class="error_mes" id="tel_mes"></span> <input id="Tel"
                                                                     name="Tel" type="text"
                                                                     value="<%=user_tel%>"
                                                                     disabled="disabled"></li>
                                                                     
            <li class="re_li"><label for="Address">地址：
            </label> <span class="error_mes" id="addr_mes"></span> <input id="Address"
                                                                     name="Address" type="text"
                                                                     value="<%=user_addr%>"
                                                                     disabled="disabled"></li>


            <li class="re_li"><label for="Password"><span class="necessary">*</span>密码： <span
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
            document.getElementById("UserSex").disabled = false;
            document.getElementById("UserBirth").disabled = false;
            document.getElementById("Tel").disabled = false;
            document.getElementById("Address").disabled = false;
            document.getElementById("submit").value = "确认修改";
        } else {
            const Password = String($('#Password').val());
            const Email = String($('#Email').val());
            let Sex = String($('#UserSex').val());
            let Tel = String($('#Tel').val());
            let Addr = String($('#Address').val());
            let Birth = String($('#UserBirth').val());
            const re_tel = /^[1][3,4,5,7,8,9][0-9]{9}$/;
            const re_email = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
            const re_date = /^[1-9]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
            $('#sex_mes').html("");
            $('#tel_mes').html("");
            $('#birth_mes').html("");
            if(Sex!==""){
            	if(Sex!=="男" && Sex!=="女"){
            		$('#sex_mes').html("性别只能是 “男” 或 “女”");
            	}
            }
            if(Tel!==""){
            	if(!re_tel.test(Tel)){
            		$('#tel_mes').html("请输入11位有效的手机号码");
            	}
            }
            if(Birth!==""){
            	if(!re_date.test(Birth)){
            		$('#birth_mes').html("日期格式为 “YYYY-MM-DD”");
            	}
            }
            if (Password !== "" && Email !== "") {
                if(Sex==="男" ||  Sex==="女"  || Sex === ""){
                    if(re_date.test(Birth) || Birth === "") {
                        if (re_email.test(Email)) {
                            if(re_tel.test(Tel) || Tel === "") {
                                if (Password.length >= 6) {
                                    if (confirm("确定修改吗?")) {
                                        if(Sex===""){
                                            Sex="未知";
                                        }
                                        if(Tel===""){
                                            Tel="未知";
                                        }
                                        if(Birth===""){
                                            Birth="1900-01-01";
                                        }
                                        if(Addr===""){
                                            Addr="未知";
                                        }
                                        $.ajax({
                                            type: "POST",
                                            url: "EditInfoServlet",
                                            data: {
                                                userid: <%=uid%>,
                                                Sex: Sex,
                                                Tel: Tel,
                                                Birth: Birth,
                                                Addr: Addr,
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
                        }
                    }
                }
            } else if (Email === "") {
                alert("请输入注册的邮箱！");
            } else if (Password === "" && re_email.test(Email)) {
                alert("请输入密码！");
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