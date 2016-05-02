<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-header navbar-fixed-top">
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
								window.open("/secondaryMarket/pages/Theme_Pages/showSearchThemeList.html?searchText="+$("#inputSearch").val().trim());
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
							<a class="btn btn-primary" href="/secondaryMarket/pages/uploadCommodity/uploadCommodity.html">上传商品</a>
							<a class="btn btn-primary"  href="/secondaryMarket/pages/Theme_Pages/publicTheme.html">发布主题</a>
							<a class="btn btn-primary" href="/secondaryMarket/pages/uploadCommodity/showCommodityList.html">查看自己的物品</a>
							<a class="btn btn-default" href="/secondaryMarket/LoginOut">注销</a>
						<%
							}
						%>
						</div>
					</div>
				</div>
			</div>
		</nav>