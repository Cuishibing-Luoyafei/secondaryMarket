<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<jsp:include page="modul/bootstrapAndJqExternal.jsp" flush="true"></jsp:include>
	<body>
		<jsp:include page="modul/webNav.jsp" flush="true"></jsp:include>
		
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<img class="first-slide" src="assets/img/index/demo2.jpg" alt="First slide">
					<div class="container">
						<div class="carousel-caption">
							<h1 style="color: black;">二手交易市场</h1>
							<div class="panel panel-default" onclick="toHide()" id="messagePanel" style="height: ;position: fixed;z-index: 1000;background-color: red;font-size: 20px;">
							  <div class="panel-body" id="messageInfo" style="text-align: left;">
							   		
							  </div>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="container marketing">
			<div class="thumbtitle">
				<h1>商品展示</h1>
			</div>
			<div class="row">
				<div class="col-lg-4">
					<img class="img-thumbnail themeImg0" src="" alt="Generic placeholder image" width="320" height="200" style="width: 320px;height:200px;">
					<h2 class="themeTitle0">书籍</h2>
					<p class="themeContent0">这个一本好书，你值得拥有</p>
					<p>
						<a class="btn btn-default themeDetail0" href="#" role="button">查看详细 &raquo;</a>
					</p>
				</div>
				<div class="col-lg-4">
					<img class="img-thumbnail themeImg1" src="" alt="Generic placeholder image" width="320" height="200" style="width: 320px;height:200px;">
					<h2 class="themeTitle1">书籍</h2>
					<p class="themeContent1">这个一本好书，你值得拥有</p>
					<p>
						<a class="btn btn-default themeDetail1" href="#" role="button">查看详细 &raquo;</a>
					</p>
				</div>
				<div class="col-lg-4">
					<img class="img-thumbnail themeImg2" src="" alt="Generic placeholder image" width="320" height="200" style="width: 320px;height:200px;">
					<h2 class="themeTitle2">书籍</h2>
					<p class="themeContent2">这个一本好书，你值得拥有</p>
					<p>
						<a class="btn btn-default themeDetail2" href="#" role="button">查看详细 &raquo;</a>
					</p>
				</div>
			</div>
		</div>

			<script>
			$(document).ready(function() {
				getPublicMessage();
				getThemes();
			});
			
			function toHide() {
				$("#messagePanel").hide();
			}
			
			function getPublicMessage() {
				$.get('/secondaryMarket/PulibcMsg',{
					status : 3
				},function(data, textStatus) {
					
					/* 
					publicMsgTheme":"测试了啊","publicMsgContent":"测试测试","publicMsgTime":"2016-05-03 23:42:48.0","publicMsgId":14}]
					*/
					if(textStatus == "success") {
						//alert(data.publicMsgs[0].publicMsgContent);
						if(data.publicMsgs != null) {
							//alert(data.publicMsgs.length);
							for(var i = 0; i < data.publicMsgs.length; i++) {
								var div = $('<div></div>');
								var label = $('<label></label>');
								label.text("特大好消息"+ (i+1) + ": " +data.publicMsgs[i].publicMsgContent);
								label.appendTo(div);
								div.appendTo($("#messageInfo"));
								
							}
						}
					}
					//alert(JSON.stringify(data.publicMsgs));
				},"json");
			}
			
			function getThemes() {
				$.get('/secondaryMarket/GetTheme',{
					isOne : false,
					pageNum : 0
				},function(data, textStatus) {
					if(textStatus == "success") {
							
							if(data.themes.length >= 3) {
								$(".themeTitle0").text(data.themes[0].themeTitle);
								$(".themeTitle1").text(data.themes[1].themeTitle);
								$(".themeTitle2").text(data.themes[2].themeTitle);
								
								$(".themeContent0").text(data.themes[0].themeContent);
								$(".themeContent1").text(data.themes[1].themeContent);
								$(".themeContent2").text(data.themes[2].themeContent);
								
								$(".themeDetail0").attr("href", "/secondaryMarket/pages/Theme_Pages/showThemeDetail.html?isOne=true&themeId="+data.themes[0].themeId);
								$(".themeDetail1").attr("href", "/secondaryMarket/pages/Theme_Pages/showThemeDetail.html?isOne=true&themeId="+data.themes[1].themeId);
								$(".themeDetail2").attr("href", "/secondaryMarket/pages/Theme_Pages/showThemeDetail.html?isOne=true&themeId="+data.themes[2].themeId);
								
								$(".themeImg0").attr("src", "/secondaryMarket/img/"+data.commodities[0].commodityPicture);
								$(".themeImg1").attr("src", "/secondaryMarket/img/"+data.commodities[1].commodityPicture);
								$(".themeImg2").attr("src", "/secondaryMarket/img/"+data.commodities[2].commodityPicture);
							}
							
					} else {
						alert("请刷新重试！");
					}
				},"json");
			}	
			
			</script>

		<hr class="featurette-divider">

		<!-- FOOTER -->
		<div class="container">
			<footer>
				<p class="pull-right">
					<a href="#">Back to top</a>
				</p>
				<p>
					&copy; 2016 C&L &middot; <a href="#">XIKEDA</a> &middot;
				</p>
			</footer>
		</div>
		<script>
			
		</script>
	</body>
</html>