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
			MathJax.Hub.Config({ tex2jax: { inlineMath: [["$","$"],["\\(","\\)"]] } }, {TeX: {equationNumbers: { autoNumber: ["AMS"], useLabelIds: true}}, "HTML-CSS": {linebreaks: {automatic: true}}, SVG: {linebreaks: {automatic: true}}});
		</script>
		<script type="text/javascript" async src="../js/MathJax-master/MathJax.js?config=default"></script>
		<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
		<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" async src="../js/test.js"></script>
		<script src="https://static.runoob.com/assets/jquery/jquery.growl/javascripts/jquery.growl.js" type="text/javascript"></script>
		<script type="text/javascript" src="../js/ckeditor/ckeditor.js"></script>
		<link href="https://static.runoob.com/assets/jquery/jquery.growl/stylesheets/jquery.growl.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			#cmj {
				width: 400px;
				height: 100px;
			}
		</style>
	</head>

	<body>
		
		<div class="header">
			<h1>吾优woyoo</h1>
		</div>
		<div>
			
		</div>
		<div class="main">
			<form id="tabUp" enctype="multipart/form-data">			
			</form>
			<form id="tabStep" enctype="multipart/form-data">
			</form>
			<div class="col" id="dv2">				
				<div class="childW">
						<p>
							<!-- 
							<button onclick="refresh()">refresh</button>  -->
							<button onclick="formUp()">提交修改</button>
							<button onclick="AddOptionBt($(this))">AddOption</button>
							<button onclick="deleteDiv($(this))">DeleteOption</button>
							<button onclick="AddStepBt($(this))">AddStep</button>
							<button onclick="deleteStep($(this))">DeleteStep</button>
						</p>
						
						<div class="nonrelativeLabel">
							<!-- 应该弹出
							// 00科目 + 00出版 + 000000年份 + 0是否真题 + 00题号 + 0难度 + 0题类 + 0题型 + 0计算器 + 0数表 +0图片
							// + 0证明题 + 0部分; -->
							<div class="label-selectSubject">选择科目
								<select></select>
							</div>
							<div class="label-selectPublisher">选择出版商
								<select></select>
							</div>
							<div class="label-selectYear">
								<p>使用此选项<input type="checkbox" value="" checked="checked" /></p>
								<input type="text" id="d241" value="2008-01" onfocus="WdatePicker({dateFmt:'yyyy-MM',startDate:'1980-05-01'})" class="Wdate"/>
							</div>
							<div class="label-isRealPro">
								<p>使用此选项<input type="checkbox" value="" checked="checked" /></p>
								<p>是真题<input type="checkbox" value="" checked="checked" /></p>
							</div>
							<div class="label-proNum">
								<p>题号<select></select></p>
							</div>
							<div class="label-proDiff">
								<p>难度<select></select></p>
							</div>
							<div class="label-proKind">
								<p>题类<select></select></p>
							</div>
							<div class="label-proType">
								<p>题型<select></select></p>
							</div>
							<div class="label-calculator">
								<p>使用此选项<input type="checkbox" value="" checked="checked" /></p>
								<p>能用计算器<input type="checkbox" value="" checked="checked" /></p>
							</div>
							<div class="label-diagram">
								<p>使用此选项<input type="checkbox" value="" checked="checked" /></p>
								<p>含数表<input type="checkbox" value="" checked="checked" /></p>
							</div>
							<div class="label-image">
								<p>使用此选项<input type="checkbox" value="" checked="checked" /></p>
								<p>含图片<input type="checkbox" value="" checked="checked" /></p>
							</div>
							<div class="label-prove">
								<p>使用此选项<input type="checkbox" value="" checked="checked" /></p>
								<p>证明题<input type="checkbox" value="" checked="checked" /></p>
							</div>
							<div class="label-section">
								<p>题部分<select></select></p>
							</div>					
							<br />
						</div>
						<div class="relativeLabel" id="relativeDiv">
							
						</div>							
						<br />			
						<div class="neip">
							<span>问题是：</span>
							<div id="ques" title="tQuestions.question"></div>
						</div>
						<div class="neip" id="frameChoice" style="height: auto;">
							<span>选项：</span>							
								<p>(A)</p>
								<div id="optionChoice0" title="tChoises[0].choise"></div>							
						</div>
						<div class="neip">
							<span>答案是：</span>
							<div id="answer" title="tAnswers.answer"></div>
						</div>
						<div class="neip" id="frameStep">
							<span>步骤：</span>
							<div id="step0" title="wait"></div>
						</div>			
				</div>				
			</div>			
		</div>
		<p style="display: none;" id="flg">0</p>
		<p style="display: none;" id="editorFlg">0</p>
		<p style="display: none;" id="editorUIFlg">0</p>
		<script>
			initSample('ques');
			initSample('optionChoice0');
			initSample('answer');
			initSample('step0');
		</script>
	</body>

</html>