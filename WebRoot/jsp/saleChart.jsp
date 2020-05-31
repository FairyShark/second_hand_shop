<%@ page pageEncoding="utf-8" %>
<%@ page import="bean.AlreadySale" %>
<%@ page import="dao.AlreadySaleDao" %>
<%@ page import="factory.DAOFactory" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.UserDao" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <title>销售报表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <link rel="stylesheet" href="<%=basePath%>css/chart.css"/>
</head>
<body>
<%
    String strUid = (String) request.getParameter("uid");
    int uid = 0;
    if (strUid != null) {
        uid = Integer.parseInt(strUid);
    }
    try {
        AlreadySaleDao dao = DAOFactory.getAlreadySaleServiceInstance();
        UserDao udao = DAOFactory.getUserServiceInstance();
        List<AlreadySale> asList = dao.getAllSaleGoods(uid);
        String uname = udao.queryUName(uid);
        if (asList != null) {
            if (asList.size() > 0) {
                AlreadySale as;
                int number;
                int smonth;
                float price;
                float[] monPrice = new float[12];
                int[] type_n = new int[6];
                for (int i = 0; i < 12; i++) {
                    monPrice[i] = 0;
                }
                for (int i = 0; i < 6; i++) {
                    type_n[i] = 0;
                }
                for (AlreadySale alreadySale : asList) {
                    as = alreadySale;
                    number = as.getNumber();
                    smonth = dao.getMonth(as.getSaleTime());
                    price = as.getPrice();
                    String stype = as.getGtype();

                    if (smonth == 1) {
                        monPrice[0] = number * price;
                    } else if (smonth == 2) {
                        monPrice[1] += number * price;
                    } else if (smonth == 3) {
                        monPrice[2] += number * price;
                    } else if (smonth == 4) {
                        monPrice[3] += number * price;
                    } else if (smonth == 5) {
                        monPrice[4] += number * price;
                    } else if (smonth == 6) {
                        monPrice[5] += number * price;
                    } else if (smonth == 7) {
                        monPrice[6] += number * price;
                    } else if (smonth == 8) {
                        monPrice[7] += number * price;
                    } else if (smonth == 9) {
                        monPrice[8] += number * price;
                    } else if (smonth == 10) {
                        monPrice[9] += number * price;
                    } else if (smonth == 11) {
                        monPrice[10] += number * price;
                    } else if (smonth == 12) {
                        monPrice[11] += number * price;
                    }
                    if (stype.equals("文具")) {
                        type_n[0] += number;
                    } else if (stype.equals("书籍")) {
                        type_n[1] += number;
                    } else if (stype.equals("食品")) {
                        type_n[2] += number;
                    } else if (stype.equals("日用品")) {
                        type_n[3] += number;
                    } else if (stype.equals("电子产品")) {
                        type_n[4] += number;
                    } else if (stype.equals("其他")) {
                        type_n[5] += number;
                    }
                }
                if (monPrice[0] != 0 || monPrice[1] != 0 || monPrice[2] != 0 || monPrice[3] != 0 || monPrice[4] != 0 || monPrice[5] != 0 || monPrice[6] != 0 || monPrice[7] != 0 || monPrice[8] != 0 || monPrice[9] != 0 || monPrice[10] != 0 || monPrice[11] != 0) {
%>
<h1><%=uname%> 的销售报表</h1>
<div>
    <div class="chart__container">
        <h3>每月销售额</h3>
        <canvas id="chart" width="600" height="300"></canvas>
    </div>
    <%
        }
    %>
</div>
<div>
    <%
        if (type_n[0] != 0 || type_n[1] != 0 || type_n[2] != 0 || type_n[3] != 0 || type_n[4] != 0 || type_n[5] != 0) {
    %>
    <div class="chart__container">
        <h3>各类型销售量</h3>
        <table class="tables">
            <tr>
                <th>类型</th>
                <th>销量</th>

            </tr>
            <%
                if (type_n[0] != 0) {
            %>
            <tr>
                <td>文具</td>
                <td><%=type_n[0]%>
                </td>

            </tr>
            <%} %>
            <%
                if (type_n[1] != 0) {
            %>
            <tr>
                <td>书籍</td>
                <td><%=type_n[1]%>
                </td>

            </tr>
            <%} %>
            <%
                if (type_n[2] != 0) {
            %>
            <tr>
                <td>食品</td>
                <td><%=type_n[2]%>
                </td>

            </tr>
            <%} %>
            <%
                if (type_n[3] != 0) {
            %>
            <tr>
                <td>日用品</td>
                <td><%=type_n[3]%>
                </td>

            </tr>
            <%} %>
            <%
                if (type_n[4] != 0) {
            %>
            <tr>
                <td>电子产品</td>
                <td><%=type_n[4]%>
                </td>

            </tr>
            <%} %>
            <%
                if (type_n[5] != 0) {
            %>
            <tr>
                <td>其他</td>
                <td><%=type_n[5]%>
                </td>

            </tr>
            <%} %>
        </table>
        <div id="chartContainer"></div>
    </div>
    <%}%>
</div>
<input type="button" value="返回" class="btn-submit-reg"
       onclick="goback()">

<input id="mon_1" type="hidden" value="<%=monPrice[0]%>"/>
<input id="mon_2" type="hidden" value="<%=monPrice[1]%>"/>
<input id="mon_3" type="hidden" value="<%=monPrice[2]%>"/>
<input id="mon_4" type="hidden" value="<%=monPrice[3]%>"/>
<input id="mon_5" type="hidden" value="<%=monPrice[4]%>"/>
<input id="mon_6" type="hidden" value="<%=monPrice[5]%>"/>
<input id="mon_7" type="hidden" value="<%=monPrice[6]%>"/>
<input id="mon_8" type="hidden" value="<%=monPrice[7]%>"/>
<input id="mon_9" type="hidden" value="<%=monPrice[8]%>"/>
<input id="mon_10" type="hidden" value="<%=monPrice[9]%>"/>
<input id="mon_11" type="hidden" value="<%=monPrice[10]%>"/>
<input id="mon_12" type="hidden" value="<%=monPrice[11]%>"/>
<input id="type_1" type="hidden" value="<%=type_n[0]%>"/>
<input id="type_2" type="hidden" value="<%=type_n[1]%>"/>
<input id="type_3" type="hidden" value="<%=type_n[2]%>"/>
<input id="type_4" type="hidden" value="<%=type_n[3]%>"/>
<input id="type_5" type="hidden" value="<%=type_n[4]%>"/>
<input id="type_6" type="hidden" value="<%=type_n[5]%>"/>
<%
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
<script type="text/javascript" src='<%=basePath%>js/Chart.min.js'></script>
<script type="text/javascript" src="<%=basePath%>js/saleChart.js"></script>
<script type="text/javascript" src="<%=basePath%>js/canvas.js"></script>
<script type="text/javascript">
    const type_val = Array(6);
    type_val[0] = Number(document.getElementById("type_1").value);
    type_val[1] = Number(document.getElementById("type_2").value);
    type_val[2] = Number(document.getElementById("type_3").value);
    type_val[3] = Number(document.getElementById("type_4").value);
    type_val[4] = Number(document.getElementById("type_5").value);
    type_val[5] = Number(document.getElementById("type_6").value);
    new CanvasJS.Chart('chartContainer', {
        theme: 'light7',
        animationEnabled: true,
        data: [{
            type: "pie",
            dataPoints: [{
                label: "文具",
                y: type_val[0]
            }, {
                label: "书籍",
                y: type_val[1]
            }, {
                label: "食品",
                y: type_val[2]
            }, {
                label: "日用品",
                y: type_val[3]
            }, {
                label: "电子产品",
                y: type_val[4]
            }, {
                label: "其他",
                y: type_val[5]
            }]
        }]
    }).render();
</script>
<script type="text/javascript">
    function goback() {
        location.href = document.referrer;
    }

    window.onunload = function () {
        navigator.sendBeacon("servlet/LogCancelTServlet");
    }
</script>
</body>
</html>