<%@page import="bean.Goods"%>
<%@page import="dao.GoodsDao"%>
<%@page import="service.GoodsService"%>
<%@page import="bean.ShoppingCart"%>
<%@page import="java.util.List"%>
<%@page import="factory.DAOFactory"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<title>出售二手</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/main.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/memenu.js"></script>
<script type="text/javascript" src="js/simpleCart.min.js"></script>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<div class="container">
		<div class="check-out">
			<h1>出售二手</h1>
			<table>
				<tr>
					<th>商品</th>
					<th>库存</th>
					<th>价格</th>
					<th>运费</th>
					<th>类型</th>
				</tr>
				<%
					String strUid = (String) session.getAttribute("uid");
					int uid = 0;
					if (strUid != null) {
						uid = Integer.parseInt(strUid);
					}
					GoodsDao goodsDao = DAOFactory.getGoodsServiceInstance();
					List<Goods> uidGoodsList = goodsDao.getUidGoodsList(uid);
					float allTotalPrice = 0;
					if (uidGoodsList != null & uidGoodsList.size() > 0) {
						Goods goods;
						String photoPath;
						String gtype;
						int number;
						float price;
						float totalPrice;
						int gid;
						for (int i = 0; i < uidGoodsList.size(); i++) {
							goods = uidGoodsList.get(i);
							String[] photo = goods.getPhoto().split("&");
							photoPath = "images/" + photo[0];
							number = goods.getNumber();
							price = goods.getPrice();
							gid = goods.getGid();
							gtype = goods.getType();
							totalPrice = number * price;
							allTotalPrice = allTotalPrice + totalPrice;
				%>
				<tr>
					<td class="ring-in"><a
						href="jsp/goodsDescribed.jsp?gid=<%=goods.getGid()%>"
						class="at-in"> <img src="<%=photoPath%>"
							class="img-responsive" alt="">
					</a>
						<div class="sed">
							<h5>
								商品名：<%=goods.getGname()%></h5>
							<br>
							<p>
								发布时间：<%=goods.getPdate()%></p>
						</div>
						<div class="clearfix"></div></td>
					<td><%=number%></td>
					<td><%=price%>元</td>
					<td><%=goods.getCarriage()%>元</td>
					<td><%=gtype%></td>
					<td>
					<a
						href="jsp/editGoods.jsp?gid=<%=gid%>">修改</a></td>
					<td>
					<a
						href="jsp/deleteSaleGoods.jsp?gid=<%=gid%>"
						onclick="return confirmDelete()">删除</a></td>
				</tr>
				<%
					}
					}
				%>
			</table>
			<a>总价值：<%=allTotalPrice%>元</a> 
		</div>
	</div>
	
<div class="bottom_tools">
  <a id="addgoods" href="jsp/addGoods.jsp" title="发布商品">发布商品</a>
  <a id="feedback" href="jsp/shoppingCart.jsp" title="购物车">购物车</a>
  <a id="scrollUp" href="javascript:;" title="回到顶部"></a>
</div>
	
<script type="text/javascript">

	function confirmDelete() {
		return confirm("确认删除该商品吗");
	}
	
	$(function(){
		var $body = $(document.body);;
		var $bottomTools = $('.bottom_tools');
		var $qrTools = $('.qr_tool');
		var qrImg = $('.qr_img');
			$(window).scroll(function () {
				var scrollHeight = $(document).height();
				var scrollTop = $(window).scrollTop();
				var $footerHeight = $('.page-footer').outerHeight(true);
				var $windowHeight = $(window).innerHeight();
				scrollTop > 50 ? $("#scrollUp").fadeIn(200).css("display","block") : $("#scrollUp").fadeOut(200);			
				$bottomTools.css("bottom", scrollHeight - scrollTop - $footerHeight > $windowHeight ? 40 : $windowHeight + scrollTop + $footerHeight + 40 - scrollHeight);
			});
			$('#scrollUp').click(function (e) {
				e.preventDefault();
				$('html,body').animate({ scrollTop:0});
			});
			$qrTools.hover(function () {
				qrImg.fadeIn();
			}, function(){
				 qrImg.fadeOut();
			});
	});
</script>
</body>
</html>