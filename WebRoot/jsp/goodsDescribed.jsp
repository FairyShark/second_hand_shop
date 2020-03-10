<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="dao.GoodsDao"%>
<%@ page import="dao.VisitMessageDao"%>
<%@ page import="bean.Goods"%>
<%@ page import="java.util.List"%>
<%@ page import="factory.DAOFactory"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	int gid = Integer.parseInt(request.getParameter("gid"));
	String guid = null;
	if (session.getAttribute("uid") != null) {
		guid = String.valueOf(session.getAttribute("uid"));
	}
	int uid = 0;
	if (guid != null) {
		uid = Integer.parseInt(guid);
	}
%>
<%
	GoodsDao dao = DAOFactory.getGoodsServiceInstance();
	Goods goods = dao.queryById(gid);
	String name = goods.getGname();
	int number = goods.getNumber();
	String[] photo = goods.getPhoto().split("&");
	String type = goods.getType();
	String uname = dao.queryUName(gid);
	String usage = goods.getUsage();
	float price = goods.getPrice();
	float carriage = goods.getCarriage();
	String pdate = goods.getPdate();
	String paddress = goods.getPaddress();
	String described = goods.getDescribed();
	int sale_uid = goods.getUid();
	String goodslandtime;
	GoodsDao gdao = DAOFactory.getGoodsServiceInstance();
	VisitMessageDao vmdao = DAOFactory.getVisitMessageServiceInstance();
	String gtype = gdao.queryTypesByGid(gid);
	vmdao.addLandTimeMes(uid, gid, gtype);
	goodslandtime = vmdao.getVisitlandtime(uid, gid);
%>
<!DOCTYPE html>
<html>
<head>
<title>商品详情</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/main.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/goods.css" rel="stylesheet" type="text/css"
	media="screen" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/imagezoom.js"></script>
<script type="text/javascript" src="js/memenu.js"></script>
<script defer src="js/jquery.flexslider.js"></script>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<div class="single">
		<div class="container">
			<div class="col-md-9">
				<div class="col-md-5 grid">
					<div class="flexslider">
						<ul class="slides">
							<%
								if (photo != null & photo.length > 0) {
									for (int i = 0; i < photo.length; i++) {
							%>
							<li data-thumb="images/<%=photo[i]%>">
								<div class="thumb-image">
									<img src="images/<%=photo[i]%>" data-imagezoom="true"
										class="img-responsive">
								</div>
							</li>
							<%
								}
								}
							%>
						</ul>
					</div>
				</div>
				<div class="col-md-7 single-top-in">
					<div class="single-para simpleCart_shelfItem">
						<h1><%=name%></h1>
						<p><%=described%></p>
						<div class="star-on">
							<div class="review">
								<a>卖家：<%=uname%></a><br /> <a>使用情况：<%=usage%></a> <br /> <a>发货地：<%=paddress%></a>
								<br /> <a>运费：<%=carriage%>元
								</a> / <a>库存：<%=number%></a>
							</div>
							<div class="clearfix"></div>
						</div>
						<label class="add-to item_price"><%=price%>元</label>
						<%
							if (sale_uid == uid) {
						%>
						<a id="carthref" href="jsp/editGoods.jsp?gid=<%=gid%>"
							class="cart item_add">修改商品</a>
						<%
							} else {
						%>
						<div class="available">
							<h6>购买数量 :</h6>
							<input id="buyNumber" name="buyNumber" type="number" min="1"
								max=<%=number%> value="1" />
						</div>
						<a id="carthref" href="jsp/addToCart.jsp?gid=<%=gid%>&buyNumber="
							class="cart item_add" onclick="return editHref()">加入购物车</a>
						<%
							}
						%>
					</div>
				</div>
			</div>
			<div class="col-md-3 product-bottom">
				<div class="product-bottom">
					<h3 class="cate">最新商品</h3>
					<%
						List<Goods> latestGoods = dao.getLatestGoods(gid, type);
						if (latestGoods != null & latestGoods.size() > 0) {
							for (int i = 0; i < latestGoods.size(); i++) {
								String goodsName = latestGoods.get(i).getGname();
								String goodsHref = "jsp/goodsDescribed.jsp?gid=" + latestGoods.get(i).getGid();
								String[] newest_photo = latestGoods.get(i).getPhoto().split("&");
								String goodsPhoto = "images/" + newest_photo[0];
								float goodsPrice = latestGoods.get(i).getPrice();
					%>
					<div class="product-go">
						<div class=" fashion-grid">
							<a href=<%=goodsHref%> target="_blank"> <img
								class="img-responsive " src=<%=goodsPhoto%> alt=""></a>
						</div>
						<div class=" fashion-grid1">
							<h6 class="best2">
								<a href=<%=goodsHref%> target="_blank"><%=goodsName%></a>
							</h6>
							<span class=" price-in1"> <%=goodsPrice%>元
							</span>
						</div>
						<div class="clearfix"></div>
					</div>
					<%
						}
						}
					%>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<script type="text/javascript">
	
	window.onbeforeunload= function(event) { 
		location.href="<%=basePath%>/VisitGCancelTServlet?uid=<%=uid%>&gid=<%=gid%>&landtime=<%=goodslandtime%>";
		}
	
		$(document).ready(function() {
			$(".memenu").memenu();
		});

		$(window).load(function() {
			$('.flexslider').flexslider({
				animation : "slide",
				controlNav : "thumbnails"
			});
		});

		$(function() {
			var menu_ul = $('.menu-drop > li > ul'), menu_a = $('.menu-drop > li > a');
			menu_ul.hide();
			menu_a.click(function(e) {
				e.preventDefault();
				if (!$(this).hasClass('active')) {
					menu_a.removeClass('active');
					menu_ul.filter(':visible').slideUp('normal');
					$(this).addClass('active').next().stop(true, true)
							.slideDown('normal');
				} else {
					$(this).removeClass('active');
					$(this).next().stop(true, true).slideUp('normal');
				}
			});

		});

		function editHref() {
			var number = document.getElementById("buyNumber").value;
			if (number >
	<%=number%>
		) {
				alert("要购买的数量大于库存，请重新选择");
				return false;
			}
			var car = document.getElementById("carthref");
			car.href = car.href + number;
		}
	</script>
</body>
</html>