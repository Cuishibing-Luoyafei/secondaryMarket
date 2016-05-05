<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-header navbar-fixed-top navbar-inverse">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#" style="font-size: 24px;font-family: '微软雅黑';">二手交易市场</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">首页</a></li>
						<li><a class="text-primary" href="#">关于我们</a></li>
						<li><a class="text-primary" href="/secondaryMarket/pages/Theme_Pages/showThemeList.html">论坛</a></li>
						<form class="navbar-form navbar-left" role="search">
							<div class="form-group">
								<input type="text" class="form-control" placeholder="Search" id="inputSearch">
							</div>
							<button type="submit" class="btn btn-default" onclick="search()">搜索</button>
						</form>
						<script>
							function search() {
								if($("#inputSearch").val().trim() != "") {
									window.open("/secondaryMarket/pages/Theme_Pages/showSearchThemeList.html?searchText="+$("#inputSearch").val().trim());
								}
								//window.open("www.baidu.com");
							}
						</script>

					</ul>
					<div class="navbar-form pull-right">

						<div id="login-register-bar">
						<%
							if(session.getAttribute("userName") == null) {
						%>
							<a class="btn btn-primary" href="/secondaryMarket/pages/Public_Visit/login.html">登陆</a>
							<a class="btn btn-default" href="/secondaryMarket/pages/Public_Visit/register.html">注册</a>
						<%
							} else {
						%>
							<a class="btn btn-sm btn-primary" href="/secondaryMarket/pages/modifyInfo/modifySelfInfo.html">修改个人信息</a>
							<a class="btn btn-sm btn-primary" href="/secondaryMarket/pages/uploadCommodity/uploadCommodity.html">上传商品</a>
							<a class="btn btn-sm btn-primary"  href="/secondaryMarket/pages/Theme_Pages/publicTheme.html">发布主题</a>
							<a class="btn btn-sm btn-primary" href="/secondaryMarket/pages/uploadCommodity/showCommodityList.html">查看自己的物品</a>
							<a class="btn btn-sm btn-default" href="/secondaryMarket/LoginOut" style="float:right;">注销</a>
							<script>
								$(document).ready(function() {
									$.get('/secondaryMarket/TopTheme',{
										status : 2
									},function(data, textStatus) {
										if(textStatus == "success") {
											if(data.isAdmin == "true") {
												var a = $('<a></a>');
												a.attr("class", "btn btn-sm btn-primary");
												a.attr("href", "/secondaryMarket/pages/admin_operate/adminOperateList.html");
												a.text("管理员操作");
												
												a.appendTo($("#login-register-bar"));
											}
										}
									},"json");
								});
							</script>
						<%
							}
						%>
						</div>
					</div>
				</div>
			</div>
		</nav>