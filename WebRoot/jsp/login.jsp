<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>用户登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="css/user.css" />
<script src="js/kit.js"></script>
<script src="js/dom.js"></script>
<script src="js/form.js"></script>
<script src="js/validator.js"></script>
<script src="js/autowired.validator.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
		var _gaq = _gaq || [];
		_gaq.push([ '_setAccount', 'UA-30210234-1' ]);
		_gaq.push([ '_trackPageview' ]);
		(function() {
			var ga = document.createElement('script');
			ga.type = 'text/javascript';
			ga.async = true;
			ga.src = ('https:' == document.location.protocol ? 'https://ssl'
					: 'http://www')
					+ '.google-analytics.com/ga.js';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(ga, s);
		})();

		function goregister() {
			window.location.href = "register.jsp";
		}

		function goback() {
			window.history.back(-1);
		}
	</script>
</head>
<body>
	<h1>登录</h1>
	<div id="regist-main">
		<form id="registForm" action="servlet/LoginServlet" method="post">
			<ol>
				<li><label for="uname"> </label><li>
				<label for="uname"> </label>
				<li><label for="uname">用户名： <span class="kitjs-validator" for="@uname" rules="[{notNull:true, message:'用户名不能为空'}]"></span></label> 
				<span class="field-validation-valid" data-valmsg-for="uname" data-valmsg-replace="true"></span>
				<input id="uname" name="uname" type="text" value=""/></li>
				<li><label for="uname"> </label>
				<li><label for="uname"> </label>
				<li>
				<label for="passwd">密码： <span class="kitjs-validator" for="@passwd" rules="[{notNull:true, message:'密码不能为空'},{minLength:'6',message:'密码长度最短为6位'}]"></span></label> 
				<span class="field-validation-valid" data-valmsg-for="passwd" data-valmsg-replace="true"></span>
					<input id="passwd" name="passwd" type="password">
				</li>
			</ol>
			<div class="registError"></div>
			<div class="outer_log">
				<div class="but_log">
					<input type="submit" value="登录" class="btn-submit-log"/>
					<a type="button" class="btn-submit-log" href="jsp/register.jsp">注册</a>
					<input type="button" value="取消" class="btn-submit-log" onclick="goback()"/>
				</div>
			</div>
		</form>
	</div>
</body>
</html>