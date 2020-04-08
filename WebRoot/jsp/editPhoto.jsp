<%@ page import="dao.GoodsDao" %>
<%@ page import="factory.DAOFactory" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.IOException" %>
<%@ page import="sun.misc.BASE64Encoder" %>
<%@ page import="java.util.Objects" %>
<%@ page pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%
    int GID = Integer.parseInt(request.getParameter("gid"));

    String guid = null;
    if (session.getAttribute("uid") != null) {
        guid = String.valueOf(session.getAttribute("uid"));
    }
    int uid;
    if (guid == null) {
        uid = 0;
    } else {
        uid = Integer.parseInt(guid);
    }

    int COUNTER = 0;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>修改商品信息</title>
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
<h1>修改商品图片</h1>
<div id="regist-main">
    <div id="registForm">
        <%
            try {
                GoodsDao gdao = DAOFactory.getGoodsServiceInstance();
                String photoPath = gdao.queryPho(GID);
                String P1 = null;
                String P2 = null;
                String P3 = null;
                if (!photoPath.equals("nophoto.jpg")) {
                    String[] GP = photoPath.split("&");
                    if (GP.length == 1) {
                        P1 = GP[0];
                        COUNTER = 1;
                    } else if (GP.length == 2) {
                        P1 = GP[0];
                        P2 = GP[1];
                        COUNTER = 2;
                    } else {
                        P1 = GP[0];
                        P2 = GP[1];
                        P3 = GP[2];
                        COUNTER = 3;
                    }
                }
        %>
        <div id="div_imgfile">选择图片</div>
        <%
            if (P3 != null) {

        %>
        <input type="file" class="imgfile" accept=".png,.jpg,.jpeg" num="2">
        <%
            }
            if (P2 != null) {
        %>
        <input type="file" class="imgfile" accept=".png,.jpg,.jpeg" num="1">
        <%
            }
            if (P1 != null) {
        %>
        <input type="file" class="imgfile" accept=".png,.jpg,.jpeg" num="0">
        <%
            }
        %>
        <div id="div_imglook">
            <%
                if (P1 != null) {
                    byte[] data = null;
                    try {
                        InputStream in = new FileInputStream(request.getSession().getServletContext().getRealPath("/images") + "\\" + P1);
                        data = new byte[in.available()];
                        in.read(data);
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    BASE64Encoder encoder = new BASE64Encoder();
                    String chart = "\\.";
                    String IMGtype = P1.split(chart)[1];
                    if (IMGtype.equals("jpg")) IMGtype = "jpeg";

            %>
            <div class="lookimg" num="0"><img
                    src="data:image/<%=IMGtype%>;base64,<%=encoder.encode(Objects.requireNonNull(data))%>">
                <div class="lookimg_delBtn" style="display: none;">移除</div>
                <div class="lookimg_progress">
                    <div></div>
                </div>
            </div>
            <%
                }
                if (P2 != null) {
                    byte[] data = null;
                    try {
                        InputStream in = new FileInputStream(request.getSession().getServletContext().getRealPath("/images") + "\\" + P2);
                        data = new byte[in.available()];
                        in.read(data);
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    BASE64Encoder encoder = new BASE64Encoder();
                    String chart = "\\.";
                    String IMGtype = P2.split(chart)[1];
                    if (IMGtype.equals("jpg")) IMGtype = "jpeg";
            %>
            <div class="lookimg" num="1"><img
                    src="data:image/<%=IMGtype%>;base64,<%=encoder.encode(Objects.requireNonNull(data))%>">
                <div class="lookimg_delBtn" style="display: none;">移除</div>
                <div class="lookimg_progress">
                    <div></div>
                </div>
            </div>
            <%
                }
                if (P3 != null) {
                    byte[] data = null;
                    try {
                        InputStream in = new FileInputStream(request.getSession().getServletContext().getRealPath("/images") + "\\" + P3);
                        data = new byte[in.available()];
                        in.read(data);
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    BASE64Encoder encoder = new BASE64Encoder();
                    String chart = "\\.";
                    String IMGtype = P3.split(chart)[1];
                    if (IMGtype.equals("jpg")) IMGtype = "jpeg";
            %>
            <div class="lookimg" num="2"><img
                    src="data:image/<%=IMGtype%>;base64,<%=encoder.encode(Objects.requireNonNull(data))%>">
                <div class="lookimg_delBtn" style="display: none;">移除</div>
                <div class="lookimg_progress">
                    <div></div>
                </div>
            </div>
            <%
                }
            %>
            <div style="clear: both;"></div>
        </div>
        <p id="succ" class="ph_succ"></p>
        <div>
            <a type="button" href="javascript:void(0)" id="btn_ImgUpStart">确定上传</a>
            <%
                if (uid == 8) {
            %>
            <a type="button" id="btn_T" href="<%=basePath%>jsp/adminGoods.jsp">完成</a>
            <%
            } else {
            %>
            <a type="button" id="btn_T" href="<%=basePath%>jsp/saleGoods.jsp">完成</a>
            <%
                }
            %>
        </div>
        <%
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>
    </div>
</div>
<label for="Gid"><input id="Gid" value=<%=GID%> type="hidden"/></label>
<label for="COUNTER"><input id="COUNTER" value="<%=COUNTER%>" type="hidden"/></label>
<script src="<%=basePath%>js/addphoto.js" type="text/javascript"></script>
<script type="text/javascript">
    function goback() {
        history.back();
    }

    window.onunload = function () {
        navigator.sendBeacon("servlet/LogCancelTServlet");
    }
</script>
</body>
</html>