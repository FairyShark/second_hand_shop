<%@ page pageEncoding="utf-8" %>
<%@ page import="bean.Goods" %>
<%@ page import="dao.GoodsDao" %>
<%@ page import="factory.DAOFactory" %>
<%@ page import="java.net.Inet4Address" %>
<%@ page import="java.net.InetAddress" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    int gid = Integer.parseInt(request.getParameter("gid"));

    InetAddress ip4 = Inet4Address.getLocalHost();
    String userip = ip4.getHostAddress();
%>
<%
    try {
        GoodsDao dao = DAOFactory.getGoodsServiceInstance();
        Goods goods = dao.queryById(gid);
        String name = goods.getGname();
        int number = goods.getNumber();
        String type = goods.getType();
        String usage = goods.getUsage();
        float price = goods.getPrice();
        float carriage = goods.getCarriage();
        String paddress = goods.getPaddress();
        String described = goods.getDescribed();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>修改商品信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="<%=basePath%>css/publish.css"/>
    <script src="<%=basePath%>js/kit.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/dom.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/form.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/validator.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/autowired.validator.js" type="text/javascript"></script>
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

        function submit_sure() {
            const gnl = confirm("下一步编辑图片，确定修改吗?");
            return gnl === true;
        }
    </script>
</head>
<body>
<h1>修改商品信息</h1>
<div id="regist-main">
    <form id="registForm" action="servlet/EditGoodsServlet" method="post" onsubmit="return submit_sure()">
        <input id="userip" name="userip" value="<%=userip%>" type="hidden"/>
        <input type="hidden" value="<%=gid%>" name="Gid" id="Gid"/>
        <div class="pub_1">
            <div class="pub_2">
                <div class="pub_3">
                    <label for="Gname"><span class="necessary">*</span>商品名： <span
                            class="kitjs-validator" for="@Gname"
                            rules="[{notNull:true, message:'商品名不能为空'}]"></span> </label> <span
                        class="field-validation-valid" data-valmsg-for="Gname"
                        data-valmsg-replace="true"></span> <input id="Gname" name="Gname"
                                                                  type="text" value="<%=name%>">
                </div>

                <div class="pub_4">
                    <label for="Price"><span class="necessary">*</span>价格： <span
                            class="kitjs-validator" for="@Price"
                            rules="[{notNull:true, message:'价格不能为空'}]"></span> </label> <span
                        class="field-validation-valid" data-valmsg-for="Price"
                        data-valmsg-replace="true"></span> <input id="Price" name="Price"
                                                                  type="text" value="<%=price%>">
                </div>
            </div>
            <div class="pub_2">
                <div class="pub_3">
                    <label for="Number">数量： </label><input id="Number" name="Number"
                                                           type="text" value="<%=number%>">
                </div>

                <div class="pub_4">
                    <label for="Carriage">运费： </label> <input id="Carriage"
                                                              name="Carriage" type="text" value="<%=carriage%>">
                </div>
            </div>
            <div class="pub_2">
                <div class="pub_3">
                    <label for="Types">商品类型： </label>
                    <select id="Types" name="Types">
                        <% if (type.equals("其他")) { %>
                        <option value="其他" selected="selected">其他</option>
                        <%} else { %>
                        <option value="其他">其他</option>
                        <%} %>
                        <% if (type.equals("文具")) { %>
                        <option value="文具" selected="selected">文具</option>
                        <%} else { %>
                        <option value="文具">文具</option>
                        <%} %>
                        <% if (type.equals("书籍")) { %>
                        <option value="书籍" selected="selected">书籍</option>
                        <%} else { %>
                        <option value="书籍">书籍</option>
                        <%} %>
                        <% if (type.equals("食品")) { %>
                        <option value="食品" selected="selected">食品</option>
                        <%} else { %>
                        <option value="食品">食品</option>
                        <%} %>
                        <% if (type.equals("日用品")) { %>
                        <option value="日用品" selected="selected">日用品</option>
                        <%} else { %>
                        <option value="日用品">日用品</option>
                        <%} %>
                        <% if (type.equals("电子产品")) { %>
                        <option value="电子产品" selected="selected">电子产品</option>
                        <%} else { %>
                        <option value="电子产品">电子产品</option>
                        <%} %>
                    </select>
                </div>

                <div class="pub_4">
                    <label for="Usage">使用情况： </label>
                    <select id="Usage" name="Usage">
                        <% if (usage.equals("全新")) { %>
                        <option value="全新" selected="selected">全新</option>
                        <%} else { %>
                        <option value="全新">全新</option>
                        <%} %>
                        <% if (usage.equals("轻度使用")) { %>
                        <option value="轻度使用" selected="selected">轻度使用</option>
                        <%} else { %>
                        <option value="轻度使用">轻度使用</option>
                        <%} %>
                        <% if (usage.equals("中度使用")) { %>
                        <option value="中度使用" selected="selected">中度使用</option>
                        <%} else { %>
                        <option value="中度使用">中度使用</option>
                        <%} %>
                        <% if (usage.equals("重度使用")) { %>
                        <option value="重度使用" selected="selected">重度使用</option>
                        <%} else { %>
                        <option value="重度使用">重度使用</option>
                        <%} %>
                        <% if (usage.equals("未知")) { %>
                        <option value="未知" selected="selected">未知</option>
                        <%} else { %>
                        <option value="未知">未知</option>
                        <%} %>
                    </select>
                </div>
            </div>
            <div class="pub_2">
                <div class="pub_3">
                    <label for="Paddress">发货地 </label><input id="Paddress"
                                                             name="Paddress" type="text" value="<%=paddress%>"/>
                </div>


            </div>
            <div class="pub_2">
                <div class="pub_3">
                    <label for="Described">详细信息： </label>
                    <textarea id="Described" name="Described"><%=described%></textarea>
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
<%
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
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