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
</head>
<script type="text/javascript">
	function instTR() {
		// 获取id为tab的table表单  
		var tab = document.getElementById('tab');
		alert(tab)
		// 获取id为x的行号  
		// 在x的行号上加一作为插入行的行号  
		var newInput = document.createElement("input");  
		newInput.type="text"	
		var allnum=document.getElementsByClassName("choises");
		/* alert(allnum) */
		var n = document.getElementById('x').rowIndex;
		alert(n);
		// tr为tab表单下行号为n的行  
		// var tr = tab.insertRow(n); 
		// insertCell() 方法用于在 HTML 表的一行的指定位置插入一个空的  
		// td为插入的行的第一个td元素  
		// var td = tr.insertCell(0); 
		// 像刚才所获取的td中插入一个随机数值  
		td.innerHTML = 'new ' + Math.random();
	}
	
</script>
<body>
	<form id="tab" action="./insert" method="post">
		<div class="f_left">问题:</div>
		<div class="f_left">
			<input name="tQuestions.question" value="" />
		</div>
		<div class="f_left">答案:</div>
		<div class="f_left">
			<input name="tAnswers.answer" value="" />
		</div>
		<div class="f_left">选项:</div>
		<div id="x" class="f_left">
			<input name="tChoises[0].choise" value="" class="choises"/>
		</div>
		<div id="x" class="f_left">
			<input name="tChoises[1].choise" value="" class="choises"/>
		</div>
		<div id="x" class="f_left">
			<input name="tChoises[2].choise" value="" class="choises"/>
		</div>
		<div>
			<input type="submit" value="提交表单" />
		</div>
	</form>
	<input name="" type="button" value="新建文本框" onClick="instTR()" />
</body>
</html>