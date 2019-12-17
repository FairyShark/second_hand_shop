<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String gid = String.valueOf(session.getAttribute("gid"));
	int GID = Integer.parseInt(gid);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>发布二手商品</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<link rel="stylesheet" href="css/addphoto.css" />
<script src="js/kit.js"></script>
<script src="js/dom.js"></script>
<script src="js/form.js"></script>
<script src="js/validator.js"></script>
<script src="js/autowired.validator.js"></script>
<script src="js/jquery.min.js"></script>
<script src="js/jquery-migrate-1.2.1.min.js"></script>
</head>
<body>
	<h1>上传商品图片</h1>
	<div id="regist-main">
		<div id="registForm">

			<div id="div_imgfile">选择图片</div>
			<div id="div_imglook">
				<div style="clear: both;"></div>
			</div>
			<p id="succ" class="ph_succ"> </p>
			<div>
				<a type="button" id="btn_ImgUpStart">确定上传</a>
				<a type="button" id="btn_T" href="jsp/saleGoods.jsp">完成</a>
			</div>
		</div>
	</div>
	<input id="Gid" value=<%=GID%> type="hidden" />
	<script src="js/addphoto.js"></script>
</body>
</html>