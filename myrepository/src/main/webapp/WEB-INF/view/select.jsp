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
<link href="../css/test.css" rel="stylesheet">
<link href="../css/public.css" rel="stylesheet">

<script type="text/x-mathjax-config">
			MathJax.Hub.Config({ tex2jax: { inlineMath: [["$","$"],["\\(","\\)"]] } },
			{TeX: {equationNumbers: {
            autoNumber: ["AMS"], useLabelIds: true}},
            "HTML-CSS": {linebreaks: {automatic: true}},
            SVG: {linebreaks: {automatic: true}}});
</script>
<script type="text/javascript" async src="../js/MathJax-master/MathJax.js?config=default"></script>
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<style type="text/css">
	#cmj {
	width: 400px;
	height: 100px;
	}
</style>
<script typ="text/javascript">
$(document).ready(function(){
	$("#dv2").on("mouseover",".quesT,.anT,.opT",function(){
		$(this).css("background-color","#C8E0F0");
	});
	$("#dv2").on("mouseout",".quesT,.anT,.opT",function(){
		$(this).css("background-color","#FFFFFF");
	});
	$("#dv2").on("click",".quesT,.anT,.opT",function(){
		var tmp = $("<input />");
		tmp.attr("class", "tmpInput");
		var tmp2 = $("<button>Click to Submit</button>");
		tmp2.attr("class", "tmpBt");
		tmp2.insertAfter($(this));
		tmp.insertAfter($(this));
		$(this).css("background-color","#C8E0F0");
		//id赋值
		var str = $("#flg").text();
		$(this).attr("id","tmpPage" + str);
		
		tmp2.css("background-color","#C8E0F0");
		tmp.css("background-color","#C8E0F0");
		tmp2.attr("onclick","tmpClick()");
		
	});
});
function tmpClick(){
	console.log("in");
	//console.log($("#tmpPage").text());
	var str = $("#flg").text();
	$("#tmpPage" + str).text( "$\{" + $(".tmpInput").val() + "\}$" );
	MathJax.Hub.Queue(["Typeset",MathJax.Hub]);
	$("#tmpPage" + str).css("background-color","#FFFFFF");
	var num = Number(str);
	num++;
	$("#flg").text(num);
	//$("#tmpPage").removeAttr("id");
	$(".tmpInput").remove();
	$(".tmpBt").remove();
}	
</script>
<script type="text/javascript">
	function editorOK(){
		var oldtxt = document.getElementById("quesInp").value;
		//console.log(oldtxt);
		var le = "$\{";
		var rg ="\}$";
		var newtxt = le + oldtxt  + rg;
		//console.log(newtxt);
		document.getElementById("quesLabel").innerHTML = newtxt;
		
		oldtxt = document.getElementById("answ").value;
		newtxt = "";
		//console.log(oldtxt);
		newtxt = "$\{" + oldtxt + "\}$";
		//console.log(newtxt);
		document.getElementById("answLabel").innerHTML = newtxt;
		
		oldtxt = document.getElementById("op1").value;
		newtxt = "";
		newtxt = "$\{" + oldtxt + "\}$";
		document.getElementById("option1").innerHTML = newtxt;
		
		oldtxt = document.getElementById("op2").value;
		newtxt = "";
		newtxt = "$\{" + oldtxt + "\}$";
		document.getElementById("option2").innerHTML = newtxt;
		
		oldtxt = document.getElementById("op3").value;
		newtxt = "";
		newtxt = "$\{" + oldtxt + "\}$";
		document.getElementById("option3").innerHTML = newtxt;
		
		MathJax.Hub.Queue(["Typeset",MathJax.Hub]);
		
	}
	function refresh(){
		MathJax.Hub.Queue(["Typeset",MathJax.Hub]);
	}
	function test2(){
		$.get("/apcompany/data/select", function(result) {  
	       //console.log(result); 
	       //将result转为对象。
	       var jsonObj = JSON.parse(result);
	       //对对象执行循环操作。
	       for(var jn in jsonObj)
			{
	    	   var chw = $("<div></div>" ,{class:"childw"});
	    	   var qs = $("<h1></h1>",{class:"quesT"});
	    	   qs.html("$\{" +jsonObj[jn].question + "\}$");
	    	   var an = $("<h2></h2>",{class:"anT"});
	    	   an.html( "The answer is:"+ "\{" +jsonObj[jn].tAnswers.answer + "\}$");
	    	   qs.appendTo(chw);
	    	   var i = 0;
	    	   var str = new String("ABCDEFGHIJK");
				for(var jn2 in jsonObj[jn].choices)
				{
					//console.log("option:" +jsonObj[jn].choices[jn2].choise);
					var op1 = $("<p></p>",{class:"opT"});
					if(i != 0)
					{
						op1.text("("+ str.charAt(i-1)+ ") " + "$\{" +jsonObj[jn].choices[jn2].choise + "\}$");
						op1.appendTo(chw);
					}
					i++;
					
				}
				an.appendTo(chw);
				chw.appendTo($("#dv2"));
				
			}
	       //在每个循环里都执行一次插入节点的操作。
	    });
		MathJax.Hub.Queue(["Typeset",MathJax.Hub]);
	}
	function test3(){
		test2();
		refresh();
	}
	var num=0;
	function AddCin(){
		num++;
		var op = $("<p>Input option:<input name=tChoises["+num+"].choise /></p>" ,{class:"neip"});
		op.appendTo($("#cinQues"));
	}
</script>
</head>
<body>
	<form id="tab" action="./insert" method="post">
		<div class="f_left">问题:</div>
		<div class="f_left">
			<input name="tQuestions.question" value=""  id = "quesInp" type="text"/>
			<label id="quesLabel"></label>
		</div>
		<div class="f_left">答案:</div>
		<div class="f_left">
			<input name="tAnswers.answer" value=""  id="answ" type="text"/>
			<label id="answLabel"></label>
		</div>
		<div class="f_left">选项:</div>
		<div id="x" class="f_left">
			<input name="tChoises[0].choise" value="" class="choises" id="op1" type="text"/>
			<label id="option1"></label>
		</div>
		<div id="x" class="f_left">
			<input name="tChoises[1].choise" value="" class="choises" id="op2" type="text"/>
			<label id="option2"></label>
		</div>
		<div id="x" class="f_left">
			<input name="tChoises[2].choise" value="" class="choises" id="op3" type="text" />
			<label id="option3"></label>
		</div>
		<div>
			<input type="submit" value="提交表单" />
		</div>
	</form>
	<input id="cmj"/>
	<button id="edr" onclick="editorOK()">编辑公式完成</button>
	<button onclick="test3()">test2</button>
	<div class="header">
				<h1>吾优woyoo</h1>
	</div>
	<div class="main">
			<div class="col" id="dv2">
				<div class="childW">
					<div id="cinQues">
						<p class="neip">Input new question:<input  /></p>
						<p class="neip">Inut   the  answer:<input /></p>
						<p class="neip">Input    option   :<input name="tChoises[0].choise" /></p>
					</div>
					
					<button onclick="AddCin()">添加新的选项框</button>
				</div>
			</div>
			
	</div>
	<p style="display: none;" id="flg">0</p>
</body>
</html>