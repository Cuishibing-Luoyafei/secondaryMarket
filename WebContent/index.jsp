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
						</div>
					</div>
				</div>
				
			</div>
		</div>

		<div class="container marketing">
			<!-- Three columns of text below the carousel -->
			<div class="thumbtitle">
				<h1>商品展示</h1>
			</div>

			<div class="row">
				<div class="col-lg-4">
					<img class="img-thumbnail" src="assets/img/index/book.jpg" alt="Generic placeholder image" width="320" height="200">
					<h2>书籍</h2>
					<p>这个一本好书，你值得拥有</p>
					<p>
						<a class="btn btn-default" href="#" role="button">查看详细 &raquo;</a>
					</p>
				</div>
				<!-- /.col-lg-4 -->
				<div class="col-lg-4">
					<img class="img-thumbnail" src="assets/img/index/demo2.jpg" alt="Generic placeholder image" width="320" height="200">
					<h2>衣物</h2>
					<p>这个一件好衣服，你值得拥有</p>
					<p>
						<a class="btn btn-default" href="#" role="button">查看详细 &raquo;</a>
					</p>
				</div>
				<!-- /.col-lg-4 -->
				<div class="col-lg-4">
					<img class="img-thumbnail" src="assets/img/index/phone.jpg" alt="Generic placeholder image" width="320" height="200">
					<h2>手机</h2>
					<p>这个一款好手机，你值得拥有</p>
					<p>
						<a class="btn btn-default" href="#" role="button">查看详细 &raquo;</a>
					</p>
				</div>
				<!-- /.col-lg-4 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container -->

		<hr class="featurette-divider">

		<!-- FOOTER -->
		<div class="container">
			<footer>
				<p class="pull-right">
					<a href="#">Back to top</a>
				</p>
				<p>
					&copy; 2015 luo &middot; <a href="#">XIKEDA</a> &middot;
				</p>
			</footer>
		</div>
	</body>
</html>