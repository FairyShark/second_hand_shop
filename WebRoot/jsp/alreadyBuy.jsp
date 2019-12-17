<%@page import="bean.AlreadyBuy"%>
<%@page import="dao.AlreadyBuyDao"%>
<%@page import="bean.Goods"%>
<%@page import="dao.GoodsDao"%>
<%@page import="service.GoodsService"%>
<%@page import="bean.ShoppingCart"%>
<%@page import="java.util.List"%>
<%@page import="factory.DAOFactory"%>
<%@page import="dao.ShoppingCartDao"%>
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
<title>已购买</title>
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
			<h1>购买历史</h1>
			<table>
				<tr>
					<th>商品</th>
					<th>卖家</th>
					<th>数量</th>
					<th>运费</th>
					<th>总价</th>
					<th>购买时间</th>
				</tr>
				<%
					String strUid = (String) session.getAttribute("uid");
					int uid = 0;
					if (strUid != null) {
						uid = Integer.parseInt(strUid);
					}
					AlreadyBuyDao dao = DAOFactory.getAlreadyBuyServiceInstance();
					List<AlreadyBuy> abList = dao.getAllBuyGoods(uid);
					if (abList != null & abList.size() > 0) {
						GoodsDao goodsDao = DAOFactory.getGoodsServiceInstance();
						Goods goods;
						AlreadyBuy ab;
						int gid;
						int number;
						String saleUname;
						String buyTime;
						String photoPath;
						float price;
						float totalPrice;
						for (int i = 0; i < abList.size(); i++) {
							ab = abList.get(i);
							gid = ab.getGid();
							number = ab.getNumber();
							buyTime = ab.getBuyTime();
							goods = goodsDao.queryById(gid);
							String[] Gphoto = goods.getPhoto().split("&");
							photoPath = "images/" + Gphoto[0];
							price = goods.getPrice();
							totalPrice = number * price + goods.getCarriage();
							saleUname = goodsDao.queryUName(gid);
				%>
				<tr>
					<td class="ring-in"><a
						href="jsp/goodsDescribed.jsp?gid=<%=goods.getGid()%>"
						class="at-in"> <img src="<%=photoPath%>"
							class="img-responsive" alt="">
					</a>
						<div class="sed">
							<h5>
								<%=goods.getGname()%></h5>
							<br>
						</div>
						<div class="clearfix"></div></td>
					<td><%=saleUname%></td>
					<td><%=number%></td>
					<td><%=goods.getCarriage()%>元</td>
					<%
						
					%>
					<td><%=totalPrice%>元</td>
					<td><%=buyTime%></td>
				</tr>
				<%
					}
					}
				%>
			</table>
		</div>
	</div>
	
<div class="bottom_tools">
  <a id="salegoods" href="jsp/saleGoods.jsp" title="出售二手">出售二手</a>
  <a id="feedback" href="jsp/shoppingCart.jsp" title="购物车">购物车</a>
  <a id="scrollUp" href="javascript:;" title="回到顶部"></a>
</div>
	
<script type="text/javascript">
	function confirmBuy() {
		return confirm("确定支付订单吗？");
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