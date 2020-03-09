<%@page import="util.OnlineCounter"%>
<%@page import="bean.User"%>
<%@page import="java.util.List"%>
<%@page import="factory.DAOFactory"%>
<%@page import="dao.UserDao"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String uid = String.valueOf(session.getAttribute("uid"));
%>
<!DOCTYPE html>
<html>
<head>
<title>会员管理</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/main.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/responsiveslides.min.js"></script>
<script type="text/javascript" src="js/memenu.js"></script>
<script>
	$(document).ready(function() {
		$(".memenu").memenu();
	});

	function key() {
		return confirm("确定退出吗？");
	}

	function showtime() {
		var myDate = new Date();
		document.getElementById("time").innerHTML = myDate.getHours() + "点"
				+ myDate.getMinutes() + "分" + myDate.getSeconds() + "秒";
		setTimeout("showtime()", 1000);
	}
</script>
</head>
<body>
	<div class="header">
		<div class="header-top">
			<div class="container">
				<div class="col-sm-4 world">
					<ul>
						<li></li>
					</ul>
				</div>
				<div class="col-sm-4 logo">
					<a href="jsp/index.jsp"><img src="images/logo.png" alt=""></a>
				</div>
				<div class="col-sm-4 header-left">
					<p class="log">
						<%
							out.print("<a>" + "你好管理员,欢迎登录" + "</a>");
							out.print("<a href=\"servlet/LogoutServlet\" onClick=\"return key()\">" + "注销" + "</a>");
						%>
						<a id="time"><script type="text/javascript">
							showtime();
						</script> </a> <a>在线人数:<%=OnlineCounter.getOnline()%></a>
					</p>

				</div>
			</div>
		</div>
		
		<div class="container">
			<div class="head-top">
				<div class="col-sm-8 h_menu4">
					<ul class="memenu skyblue">
						<li class=" grid"><a href="jsp/adminUser.jsp" class="a_active">会员管理</a></li>
						<li><a href="jsp/adminGoods.jsp">商品管理</a></li>
						<li><a href="jsp/adminData.jsp">数据统计</a></li>
					</ul>
				</div>
			</div>
		</div>
		
		<div class="content goods_show">
		<div class="sear_w">
			<h2>会 员 管 理</h2>
		</div>
		<div class="bottter">
			<div class="seach_1">
				<div class="typ_6">
					<label>会员ID：</label><input id="user_id" class="inp_4">
				</div>
				<div class="typ_7">
					<label>会员名：</label><input id="user_name" class="inp_5">
				</div>
				<div class="typ_8">
					<label>邮箱：</label><input id="user_mail" class="inp_6">
				</div>
				<div class="typ_9">
					<button class="but_1" onclick="clickSearch()">搜索</button>
				</div>
			</div>

		</div>

		<div class="container">
			<div class="content-top">
				<table id="resultTable">
					<tr>
						<th>序号</th>
						<th>会员ID</th>
						<th>会员名</th>
						<th>邮箱</th>
						<th>最后登录时间</th>
					</tr>
					<%
						UserDao userDao = DAOFactory.getUserServiceInstance();
						List<User> UsersList = userDao.selectAllUser();
						if (UsersList != null & UsersList.size() > 0) {
							User user;
							int num = 0;
							int uid_t;
							String uname_t;
							String umail_t;
							String lastlogin_t;
							for (int i = 0; i < UsersList.size(); i++) {
								user = UsersList.get(i);
								if(user.getUid()==8)continue;
								uid_t = user.getUid();
								uname_t = user.getUname();
								umail_t = user.getEmail();
								lastlogin_t = user.getLastLogin();
								num++;
					%>
					<tr>
						<td><%=num%>.</td>
						<td><%=uid_t%></td>
						<td><%=uname_t%></td>
						<td><%=umail_t%></td>
						<td><%=lastlogin_t%></td>
						<td><a href="jsp/showMessage.jsp?uid=<%=uid_t%>" target="_blank">查看</a></td>
						<td><a href="jsp/deleteUser.jsp?uid=<%=uid_t%>"
							onclick="return confirmDelete()">删除</a></td>
					</tr>
					<%
						}
						}
					%>
				</table>
				<div id="tempP"></div>
			</div>
		</div>
	</div>
	<div class="bottom_tools">
		<a id="scrollUp" href="javascript:;" title="回到顶部"></a>
	</div>
	</div>
	<script>
		function confirmDelete() {
			return confirm("确认删除该会员吗，同时会清空该会员的售卖商品？");
		}
		
		$(function() {
			$("#slider").responsiveSlides({
				auto : true,
				speed : 500,
				namespace : "callbacks",
				pager : true,
			});
		});
		function showtime() {
			var myDate = new Date();
			document.getElementById("time").innerHTML = myDate.getHours() + "时"
					+ myDate.getMinutes() + "分" + myDate.getSeconds() + "秒";
			setTimeout("showtime()", 1000);
		}

		$(function() {
			var $body = $(document.body);
			;
			var $bottomTools = $('.bottom_tools');
			var $qrTools = $('.qr_tool');
			var qrImg = $('.qr_img');
			$(window)
					.scroll(
							function() {
								var scrollHeight = $(document).height();
								var scrollTop = $(window).scrollTop();
								var $footerHeight = $('.page-footer')
										.outerHeight(true);
								var $windowHeight = $(window).innerHeight();
								scrollTop > 50 ? $("#scrollUp").fadeIn(200)
										.css("display", "block") : $(
										"#scrollUp").fadeOut(200);
								$bottomTools
										.css(
												"bottom",
												scrollHeight - scrollTop
														- $footerHeight > $windowHeight ? 40
														: $windowHeight
																+ scrollTop
																+ $footerHeight
																+ 40
																- scrollHeight);
							});
			$('#scrollUp').click(function(e) {
				e.preventDefault();
				$('html,body').animate({
					scrollTop : 0
				});
			});
			$qrTools.hover(function() {
				qrImg.fadeIn();
			}, function() {
				qrImg.fadeOut();
			});
		});

		function clickSearch() {
			var UserID = $("#user_id").val();
			var UserName = $("#user_name").val();
			var UserMail = $("#user_mail").val();
			if ((UserID == null || UserID == "")
					&& (UserName == null || UserName == "")
					&& (UserMail == null || UserMail == "")) {
				window.location.reload();
			} else {
				if (UserID == null || UserID == "")
					UserID = -1;
				if (UserName == null || UserName == "")
					UserName = "%&ALL&%";
				if (UserMail == null || UserMail == "")
					UserMail = "%&ALL&%";
				$.ajax({
					url : 'SelectUserServlet',
					type : 'GET',
					data : {
						UserID : UserID,
						UserName : UserName,
						UserMail : UserMail,
					},
					dataType : 'json',
					success : function(json) {
						$("#resultTable").empty();
						var tr = $("<tr/>");
						$("<th/>").html("序号").appendTo(tr);
						$("<th/>").html("会员ID").appendTo(tr);
						$("<th/>").html("会员名").appendTo(tr);
						$("<th/>").html("邮箱").appendTo(tr);
						$("<th/>").html("最后登陆时间").appendTo(tr);
						$("#resultTable").append(tr);
						var temp = 0;
						$.each(json, function(i, val) {
							if(val.uid!="8"){
							temp++;
							var tr=$("<tr/>");
							$("<td/>").html(temp+".").appendTo(tr);
							$("<td/>").html(val.uid).appendTo(tr);
							$("<td/>").html(val.uname).appendTo(tr);
							$("<td/>").html(val.email).appendTo(tr);
							$("<td/>").html(val.lastLogin).appendTo(tr);
							var td1 = $("<td/>");
							var a1 = $("<a/>");
							a1.attr("href", "jsp/showMessage.jsp?uid=" + val.uid);
							a1.attr("target", "_blank");
							a1.html("查看").appendTo(td1);
							td1.appendTo(tr);
							var td2=$("<td/>");
							var a2=$("<a/>");
							a2.attr("href", "jsp/deleteUser.jsp?uid=" + val.uid);
							a2.attr("onclick", "return confirmDelete()");
							a2.html("删除").appendTo(td2);
							td2.appendTo(tr);
							$("#resultTable").append(tr);
							}
						})
						if (temp == 0) {
							$("#resultTable").empty();
							$("#tempP").empty();
							var p2 = $("<p/>");
							p2.addClass("tempmess");
							p2.html("暂时没有该类型的会员，换一个试试！").appendTo(p2);
							$("#tempP").append(p2);
						} else {
							$("#tempP").empty();
							var p3 = $("<p/>");
							p3.addClass("tempmess");
							p3.html("共找到" + temp + "个该类型的会员！").appendTo(p3);
							$("#tempP").append(p3);
						}
					},
					error : function() {
						$("#test").append("条件查询错误！");
					}

				});
			}
		}

		document.onkeydown = function(event) {
			e = event ? event : (window.event ? window.event : null);
			if (e.keyCode == 13) {
				clickSearch();
			}
		};
	</script>
</body>
</html>