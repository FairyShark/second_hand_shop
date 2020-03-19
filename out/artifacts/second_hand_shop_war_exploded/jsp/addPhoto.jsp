<%@ page pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%
    int GID = Integer.parseInt(request.getParameter("gid"));
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>发布二手商品</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <link rel="stylesheet" href="<%=basePath%>css/addphoto.css"/>
    <script src="<%=basePath%>js/kit.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/dom.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/form.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/validator.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/autowired.validator.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/jquery.min.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
</head>
<body>
<h1>上传商品图片</h1>
<div id="regist-main">
    <div id="registForm">

        <div id="div_imgfile">选择图片</div>
        <div id="div_imglook">
            <div style="clear: both;"></div>
        </div>
        <p id="succ" class="ph_succ"></p>
        <div>
            <a type="button" id="btn_ImgUpStart">确定上传</a>
            <a type="button" id="btn_T" href="<%=basePath%>jsp/saleGoods.jsp">完成</a>
        </div>
    </div>
</div>
<label for="Gid"><input id="Gid" value=<%=GID%> type="hidden"/></label>
<label for="COUNTER"><input id="COUNTER" value="0" type="hidden"/></label>
<script src="<%=basePath%>js/addphoto.js" type="text/javascript"></script>
<script type="text/javascript">
    window.onunload = function () {
        navigator.sendBeacon("servlet/LogCancelTServlet");
    }
</script>
</body>
</html>