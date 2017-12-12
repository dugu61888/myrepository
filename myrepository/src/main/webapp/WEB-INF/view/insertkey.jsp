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
		<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
		<script type="text/javascript" src="../js/insertkey.js"></script>				
		<script src="https://static.runoob.com/assets/jquery/jquery.growl/javascripts/jquery.growl.js" type="text/javascript"></script>
		<link href="https://static.runoob.com/assets/jquery/jquery.growl/stylesheets/jquery.growl.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			.divch {
				position:relative;
				float: left;
				margin: 5px;				
			}
			body {
				background-color: #F5F5F5;
			}
			.header {
				background-color: #FFFFFF;
			}
			.divch {
				border-style: solid;
				border-width: 1px;
				border-color: #66AFE9;
				padding:4px;
			}
		</style>
	</head>

	<body>
		
		<div class="header" style="margin-left:50px;margin:50px;width: 1200px;padding: 10px;">
			<h1>吾优woyoo</h1>
		</div>
		<div style="margin-left:50px;margin-top:10px;width: 1200px;background-color: #FFFFFF;padding: 10px;height: 800px;">
			<div style="margin: 5px;">
				<input  id="word" placeholder="输入中文" />
				<input id="fword" placeholder="输入英文" /> 
				<button onclick="addkey()">add</button>
			</div>
			<div id="keysw">
				<p style="margin: 5px;"><button onclick="showkey()">showKeyWord</button></p>	
			</div>
		<form id="tab"></form>
		</div>
		
	</body>

</html>