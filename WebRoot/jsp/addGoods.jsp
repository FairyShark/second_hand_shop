<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="java.net.Inet4Address"%>
<%@ page import="java.net.InetAddress"%>
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
<title>发布二手商品</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<link rel="stylesheet" href="css/publish.css" />
<script src="js/kit.js"></script>
<script src="js/dom.js"></script>
<script src="js/form.js"></script>
<script src="js/validator.js"></script>
<script src="js/autowired.validator.js"></script>
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

		function goback() {
			window.history.back(-1);
		}
	</script>
</head>
<body>
	<h1>发布二手商品</h1>
	<div id="regist-main">
		<form id="registForm" action="servlet/AddGoodsServlet" method="post">
		<input id="userip" name="userip" value="<%=userip%>" type="hidden"/>
			<div class="pub_1">
				<div class="pub_2">
					<div class="pub_3">
						<label for="Gname"><span class="necessary">*</span>商品名： <span
							class="kitjs-validator" for="@Gname"
							rules="[{notNull:true, message:'商品名不能为空'}]"></span> </label> <span
							class="field-validation-valid" data-valmsg-for="Gname"
							data-valmsg-replace="true"></span> <input id="Gname" name="Gname"
							type="text" value="">
					</div>

					<div class="pub_4">
						<label for="Price"><span class="necessary">*</span>价格： <span
							class="kitjs-validator" for="@Price"
							rules="[{notNull:true, message:'价格不能为空'}]"></span> </label> <span
							class="field-validation-valid" data-valmsg-for="Price"
							data-valmsg-replace="true"></span> <input id="Price" name="Price"
							type="text" value="">
					</div>
				</div>
				<div class="pub_2">
					<div class="pub_3">
						<label for="Number">数量： </label><input id="Number" name="Number"
							type="text" value="">
					</div>

					<div class="pub_4">
						<label for="Carriage">运费： </label> <input id="Carriage"
							name="Carriage" type="text" value="">
					</div>
				</div>
				<div class="pub_2">
					<div class="pub_3">
						<label for="Usage">使用情况 </label>
						<select id="Usage" name="Usage">
						    <option value="全新">全新</option>
							<option value="轻度使用">轻度使用</option>
							<option value="中度使用">中度使用</option>
							<option value="重度使用">重度使用</option>
							<option value="未知" selected="selected">未知</option>
						</select>
					</div>

					<div class="pub_4">
						<label for="Paddress">发货地 </label><input id="Paddress"
							name="Paddress" type="text" value="" />
					</div>
				</div>
				<div class="pub_2">
					<div class="pub_3">
						<label for="Types">商品类型：</label>
						<select id="Types" name="Types">
							<option value="文具">文具</option>
							<option value="书籍">书籍</option>
							<option value="食品">食品</option>
							<option value="日用品">日用品</option>
							<option value="电子产品">电子产品</option>
							<option value="其他" selected="selected">其他</option>
						</select>
					</div>
				</div>
				
				<div class="pub_2">
					<div class="pub_3">
						<label for="Described">详细信息： </label>
						<textarea id="Described" name="Described"></textarea>
					</div>
				</div>
			</div>
			<div class="registError"></div>
			<div class="pub_5">
				<div class="pub_6">
					<input type="submit" value="下一步" class="btn-submit-reg"> <input
						type="button" value="取消" class="btn-submit-reg" onclick="goback()">
				</div>
			</div>
		</form>
	</div>

</body>
</html>