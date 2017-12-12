<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
<title>Skyblue Responsive Template</title>
<!-- ALL STYLESHEET -->
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../css/font-awesome.min.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">
<link href="../css/blog-single.css" rel="stylesheet">
<link href="../css/responsive.css" rel="stylesheet">

<script type="text/javascript" language="javascript">
	function iFrameHeight() {
		var ifm = document.getElementById("iframepage");
		var subWeb = document.frames ? document.frames["iframepage"].document
				: ifm.contentDocument;
		if (ifm != null && subWeb != null) {
			ifm.height = subWeb.body.scrollHeight;
			ifm.width = subWeb.body.scrollWidth;
		}
	}
</script>
</head>

<body>
	<!-- HEADER -->
	<section class="header">
		<div class="header-area">
			<div class="container">
				<div class="row">
					<div class="col-md-6">
						<div class="header-left">
							<ul class="list-inline">
								<li><a class="question" href="#">Have Any Questions?</a></li>
								<li><a href="#"><i class="fa fa-envelope-o"></i>
										support@steeltheme.com</a></li>
								<li><a href="#"><i class="fa fa-phone"></i> +61(0)7
										9180 3458</a></li>
							</ul>
						</div>
					</div>
					<div class="col-md-6">
						<ul class="list-inline header-social-links pull-right ">
							<li>
								<div class="input-group">
									<input type="text" class="form-control input-sm"
										placeholder="Search"> <span class="input-group-btn"><button
											class="btn btn-default btn-sm" type="button">
											<i class="fa fa-search"></i>
										</button></span>
								</div>
							</li>
							<li><a href="#"><i class="fa fa-facebook s-link"></i></a></li>
							<li><a href="#"><i class="fa fa-twitter s-link"></i></a></li>
							<li><a href="#"><i class="fa fa-pinterest s-link"></i></a></li>
							<li><a href="#"><i class="fa fa-google-plus s-link"></i></a></li>
							<!--li><a href="#"><img src="../images/is.png" alt="language" /> <i class="fa fa-angle-down"></i></a></li-->
							<li class="language">
								<div class="btn-group">
									<a type="button" class="dropdown-toggle" data-toggle="dropdown"
										aria-haspopup="true" aria-expanded="false"> <img
										src="../images/is.png" alt="language" /> <i
										class="fa fa-angle-down"></i></a>
									<ul class="dropdown-menu">
										<li><a href="#"><img src="../images/be.png"
												alt="language" /></a></li>
										<li><a href="#"><img src="../images/bv.png"
												alt="language" /></a></li>
										<li><a href="#"><img src="../images/ck.png"
												alt="language" /></a></li>
										<li><a href="#"><img src="../images/ae.png"
												alt="language" /></a></li>
									</ul>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<hr>
	</section>
	<!-- END HEADER -->

	<!-- NAVIGATION -->
	<section>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="menu">
					<nav class="navbar navbar-default">
						<div class="container">
							<!-- Brand and toggle get grouped for better mobile display -->
							<div class="navbar-header">
								<button type="button" class="navbar-toggle collapsed"
									data-toggle="collapse"
									data-target="#bs-example-navbar-collapse-1"
									aria-expanded="false">
									<span class="sr-only">Toggle navigation</span> <span
										class="icon-bar"></span> <span class="icon-bar"></span> <span
										class="icon-bar"></span>
								</button>
								<a class="navbar-brand" href="index"><i class="fa fa-photo"></i>
									Sky<span>Blue</span></a>
							</div>

							<!-- Collect the nav links, forms, and other content for toggling -->
							<div class="collapse navbar-collapse"
								id="bs-example-navbar-collapse-1">
								<ul class="nav navbar-nav navbar-right">
									<li><a href="index">Home</a></li>
									<li class=" dropdown"><a href="portfolio-1"
										class="dropdown-toggle" data-toggle="dropdown" role="button"
										aria-haspopup="true" aria-expanded="false">Portfolio <i
											class="fa fa-caret-down"></i></a>
										<ul class="dropdown-menu portfolio-list">
											<li><a href="portfolio-1">Portfolio Page 1</a></li>
											<li><a href="portfolio-2">Portfolio Page 2</a></li>
										</ul></li>
									<li class="active"><a href="elements">Elements</a></li>
									<li><a href="single">Single</a></li>
									<li><a href="blog">Blog</a></li>
									<li><a href="contact">Contact</a></li>
									<li class="dropdown hidden-xs"><a
										class="cart dropdown-toggle" data-toggle="dropdown"
										role="button" aria-haspopup="true" aria-expanded="false"
										href="#"><i class="fa fa-shopping-cart"></i><span
											class="badge">3</span></a>
										<ul class="dropdown-menu cart-list">
											<li>
												<ul class="media-list">
													<li class="media">
														<div class="media-left">
															<a href="#"><img class="media-object"
																src="../images/card-1.jpg" alt=""></a>
														</div>
														<div class="media-body">
															<h5 class="media-heading">Travis Pullover</h5>
															<p>Man
															<p>
																<strong>$86.99</strong>
														</div>
														<div class="media-right media-middle">
															<a href="#"><i class="fa fa-times"></i></a>
														</div>
													</li>
													<li role="separator" class="divider"></li>
													<li class="media">
														<div class="media-left">
															<a href="#"><img class="media-object"
																src="../images/card-2.jpg" alt=""></a>
														</div>
														<div class="media-body">
															<h5 class="media-heading">Travis Pullover</h5>
															<p>Woman
															<p>
																<strong>$65.99</strong>
														</div>
														<div class="media-right media-middle">
															<a href="#"><i class="fa fa-times"></i></a>
														</div>
													</li>
													<li role="separator" class="divider"></li>
													<li class="media">
														<div class="media-left">
															<a href="#"><img class="media-object"
																src="../images/card-1.jpg" alt=""></a>
														</div>
														<div class="media-body">
															<h5 class="media-heading">Travis Pullover</h5>
															<p>Woman
															<p>
																<strong>$59.99</strong>
														</div>
														<div class="media-right media-middle">
															<a href="#"><i class="fa fa-times"></i></a>
														</div>
													</li>
													<li role="separator" class="divider"></li>
													<li><h4>
															Total : $120.99 <a
																class="btn btn-primary btn-sm pull-right" href="#">View
																Cart</a>
														</h4></li>
												</ul>
											</li>
										</ul></li>
								</ul>
							</div>
							<!-- navbar-collapse -->
						</div>
						<!-- container-fluid -->
					</nav>
				</div>
			</div>
		</div>
	</section>
	<!-- END NAVIGATION -->


	<!-- ELEMENTS -->
	<section>
		<div class="page-header">
			<div class="container" style="max-width: 1170px;">
				<div class="row">
					<div class="col-md-12">
						<h1>教师团队</h1>
						<p>专业的老师，一流的服务</p>
					</div>
				</div>
			</div>
		</div>
		<div class="elements">
			<div class="container" style="max-width: 1170px;">
		   <div id="myCarousel" class="carousel slide">
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
				</ol>
				<!-- jumbotron -->
				<h1>教师简介</h1>
				<div class="carousel-inner">
					<div class="item active">
						<div class="jumbotron" alt="First slide">
							<h1>崔晓龙（Cui Xiao Long）</h1>
							<p>俄勒冈 经济 数学 在美 5年经验</p>
							<div class="media-own" id="test1" align="center">
								<iframe id="iframepage"
									src="http://player.youku.com/embed/XMTUyNDgxODY2MA==">
								</iframe>
							</div>
							<p>近期课程：</p>
						</div>
					</div>
					<div class="item">
						<div class="jumbotron" alt="Second slide">
							<h1>封硕（Fen shuo）</h1>
							<p>俄勒冈 经济 数学 在美 5年经验</p>
							<div class="media-own" id="test1" align="center">
								<iframe id="iframepage"
									src="http://player.youku.com/embed/XMTQ5NzMyOTUzNg==">
								</iframe>
							</div>
							<p>近期课程：</p>
						</div>
					</div>
					<div class="item">
						<div class="jumbotron" alt="Third slide">
							<h1>曹宇（Cao yu）</h1>
							<p>俄勒冈 经济 数学 在美 5年经验</p>
							<div class="media-own" id="test1" align="center">
								<iframe id="iframepage"
									src="http://player.youku.com/embed/XNTA3MzY4ODk2">
								</iframe>
							</div>
							<p>近期课程：</p>
						</div>
					</div>
				</div>
				</div>
			</div>
			<!-- //container -->
		</div>
		<div id="myCarousel" class="carousel slide">
			<!-- 轮播（Carousel）指标 -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>
			<!-- 轮播（Carousel）项目 -->
			<div class="carousel-inner">
				<div class="item active">
					<img src="/wp-content/uploads/2014/07/slide1.png" alt="First slide">
				</div>
				<div class="item">
					<img src="/wp-content/uploads/2014/07/slide2.png"
						alt="Second slide">
				</div>
				<div class="item">
					<img src="/wp-content/uploads/2014/07/slide3.png" alt="Third slide">
				</div>
			</div>
			<!-- 轮播（Carousel）导航 -->
			<a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
			<a class="carousel-control right" href="#myCarousel"data-slide="next">&rsaquo;</a>
		</div>
	</section>
	<!-- END SINGLE -->

	<!-- TOP FOOTER -->
	<section class="top-footer">
		<div class="container">
			<div class="row">
				<div class="col-md-3">
					<h1>
						<i class="fa fa-cube"></i> Porton
					</h1>
					<p>sapiente, nam sunt rem beatae architecto cupiditate.</p>
					<div class="media">
						<div class="media-left">
							<a href="#"><i class="fa fa-home"></i></a>
						</div>
						<div class="media-body">
							<h5 class="media-heading">Address</h5>
							<p>Lebel 2, 13 Elezabe St, Melbounire, Victoria 3000</p>
						</div>
					</div>
					<div class="media">
						<div class="media-left">
							<a href="#"><i class="fa fa-envelope-o"></i></a>
						</div>
						<div class="media-body">
							<h5 class="media-heading">Having any questions?</h5>
							<p>support@settletheme.com</p>
						</div>
					</div>
					<div class="media">
						<div class="media-left">
							<a href="#"><i class="fa fa-phone"></i></a>
						</div>
						<div class="media-body">
							<h5 class="media-heading">Call us & Hair us</h5>
							<p>+61(0)7 9180 3458</p>
						</div>
					</div>
					<div class="media">
						<div class="media-left">
							<a href="#"><i class="fa fa-fax"></i></a>
						</div>
						<div class="media-body">
							<h5 class="media-heading">Fax</h5>
							<p>+(921) 34256537893</p>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<h4>Fresh Tweets</h4>
					<div class="media">
						<div class="media-left">
							<a href="#"><i class="fa fa-twitter"></i></a>
						</div>
						<div class="media-body">
							<p>
								<a href="#">@Steeltheme,</a> Porton HTML various out now
							</p>
							<strong>10 Mins Ago</strong>
						</div>
					</div>
					<div class="media">
						<div class="media-left">
							<a href="#"><i class="fa fa-twitter"></i></a>
						</div>
						<div class="media-body">
							<p>
								<a href="#">@envato,</a> Porton HTML various out now, search
								more market
							</p>
							<strong>10 Mins Ago</strong>
						</div>
					</div>
					<div class="media">
						<div class="media-left">
							<a href="#"><i class="fa fa-twitter"></i></a>
						</div>
						<div class="media-body">
							<p>
								<a href="#">@collins,</a> Porton HTML various out now
							</p>
							<strong>10 Mins Ago</strong>
						</div>
					</div>
					<div class="media">
						<div class="media-left">
							<a href="#"><i class="fa fa-twitter"></i></a>
						</div>
						<div class="media-body">
							<p>
								<a href="#">@Steeltheme,</a> Porton HTML various out now
							</p>
							<strong>10 Mins Ago</strong>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<h4>Latest Updates</h4>
					<div class="media">
						<div class="media-left">
							<a href="#"><img class="media-object"
								src="../images/feed-1.png" alt=""></a>
						</div>
						<div class="media-body">
							<p class="update">An engaging video a the post</p>
							<strong class="update-info"><i class="fa fa-user"></i>Mano
								<i class="fa fa-eye"></i>22 Viwes</strong>
						</div>
					</div>
					<div class="media">
						<div class="media-left">
							<a href="#"><img class="media-object" src="../"
								images/
							feed-1.png" alt=""> </a>
						</div>
						<div class="media-body">
							<p class="update">An engaging video a the post</p>
							<strong class="update-info"><i class="fa fa-user"></i>Mano
								<i class="fa fa-eye"></i>72 Viwes</strong>
						</div>
					</div>
					<div class="media">
						<div class="media-left">
							<a href="#"><img class="media-object"
								src="../images/feed-1.png" alt=""></a>
						</div>
						<div class="media-body">
							<p class="update">An engaging video a the post</p>
							<strong class="update-info"><i class="fa fa-user"></i>Mano
								<i class="fa fa-eye"></i>32 Viwes</strong>
						</div>
					</div>
					<div class="media">
						<div class="media-left">
							<a href="#"><img class="media-object"
								src="../images/feed-1.png" alt=""></a>
						</div>
						<div class="media-body">
							<p class="update">An engaging video a the post Nam sunt rem
								beatae</p>
							<strong class="update-info"><i class="fa fa-user"></i>Mano
								<i class="fa fa-eye"></i>27 Viwes</strong>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<h4>Popular tags</h4>
					<div class="tags" style="padding-top: 8px;">
						<a class="btn btn-default btn-sm" href="#">Amazing</a> <a
							class="btn btn-default btn-sm" href="#">Design</a> <a
							class="btn btn-default btn-sm" href="#">Jequery</a> <a
							class="btn btn-default btn-sm" href="#">Photoshop</a> <a
							class="btn btn-default btn-sm" href="#">art</a> <a
							class="btn btn-default btn-sm" href="#">wordpres</a>
					</div>

					<div class="subscribe">
						<h4>subscribe us</h4>
						<p>Nam sunt rem beatae architecto cupiditate numquam.</p>
						<div class="input-group">
							<input type="text" class="form-control input-sm"
								placeholder="Email Address"> <span
								class="input-group-btn">
								<button class="btn btn-default input-sm" type="button">
									<i class="fa fa-check"></i>
								</button>
							</span>
						</div>
						<p>We respect your privacy.</p>
					</div>
					<ul class="list-inline social-link">
						<li><a href="#"><i class="fa fa-facebook"></i></a></li>
						<li><a href="#"><i class="fa fa-twitter"></i></a></li>
						<li><a href="#"><i class="fa fa-pinterest"></i></a></li>
						<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
						<li><a href="#"><i class="fa fa-linkedin"></i></a></li>
					</ul>
				</div>
			</div>
		</div>
	</section>
	<!-- END TOP FOOTER -->

	<!-- BOTTOM FOOTER -->
	<section class="bottom-footer">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					Copyrights 2015 All Reseved More Templates <a
						href="http://www.cssmoban.com/" target="_blank"
						title="æ¨¡æ¿ä¹å®¶">æ¨¡æ¿ä¹å®¶</a> - Collect from <a
						href="http://www.cssmoban.com/" title="ç½é¡µæ¨¡æ¿"
						target="_blank">ç½é¡µæ¨¡æ¿</a>
				</div>
				<div class="col-md-6 text-right">
					<a href="#">Terms of use</a> <a href="#">Privacy & Security
						Statement</a>
				</div>
			</div>
		</div>
	</section>
	<!-- END BOTTOM FOOTER -->

	<!-- ALL JAVASCRIPT -->
	<script src="../js/jquery.js"></script>
	<script src="../bootstrap/js/bootstrap.min.js"></script>
	<script src="../js/custom.js"></script>
</body>
</html>