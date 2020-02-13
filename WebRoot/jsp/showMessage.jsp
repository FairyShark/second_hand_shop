<%@page import="bean.User"%>
<%@page import="factory.DAOFactory"%>
<%@page import="dao.UserDao"%>
<%@page import="service.UserService"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String uname = (String) session.getAttribute("uname");
	int uid = Integer.parseInt(request.getParameter("uid"));
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>个人信息</title>
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

	function editInfo() {
		if (document.getElementById("submit").value == "编辑个人信息") {
			document.getElementById("Email").disabled = false;
			document.getElementById("Password").disabled = false;
			document.getElementById("submit").value = "确认修改";
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<h1>个人信息</h1>
	<div id="regist-main">
		<form id="registForm" action="servlet/EditInfoServlet" method="get">
			<ol>
				<%
					UserDao dao = DAOFactory.getUserServiceInstance();
					User user = dao.queryByUid(uid);
					if (user != null) {

					}
				%>

				<li><label for="UserName">用户名： <span
						class="kitjs-validator" for="@UserName"
						rules="[{notNull:true, message:'用户名不能为空'}]"></span>
				</label> <span class="field-validation-valid" data-valmsg-for="UserName"
					data-valmsg-replace="true"></span> <input id="UserName"
					name="UserName" type="text" value="<%=user.getUname()%>"
					disabled="disabled"></li>

				<li><label for="Email">邮箱地址： <span
						class="kitjs-validator" for="@Email"
						rules="[{notNull:true, message:'电子邮件不能为空'},{isEmail:true,message:'电子邮件格式不正确'}]"></span>
				</label> <span class="field-validation-valid" data-valmsg-for="Email"
					data-valmsg-replace="true"></span> <input id="Email" name="Email"
					type="text" value="<%=user.getEmail()%>" disabled="disabled"></li>

				<li><label for="Password">密码： <span
						class="kitjs-validator" for="@Password"
						rules="[{notNull:true, message:'密码不能为空'},{minLength:'6',message:'密码长度最少为6位'}]"></span>
				</label> <span class="field-validation-valid" data-valmsg-for="Password"
					data-valmsg-replace="true"></span> <input id="Password"
					name="Password" type="password" value="<%=user.getPasswd()%>"
					disabled="disabled"></li>

				<li><label for="email">上次登录时间： </label> <input id="email"
					name="email" type="text" value="<%=user.getLastLogin()%>"
					disabled="disabled"></li>

			</ol>
			<div class="registError"></div>
			<div class="outer_log">
				<div class="but_log">
					<input id="submit" type="submit" value="编辑个人信息"
						class="btn-submit-mes" onclick="return editInfo()">
				</div>
			</div>
		</form>
	</div>
</body>
</html>