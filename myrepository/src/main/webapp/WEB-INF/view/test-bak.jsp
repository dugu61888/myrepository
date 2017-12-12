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
		<link href="../css/editor.css" rel="stylesheet">
		<script type="text/x-mathjax-config">
			MathJax.Hub.Config({ tex2jax: { inlineMath: [["$","$"],["\\(","\\)"]] } }, {TeX: {equationNumbers: { autoNumber: ["AMS"], useLabelIds: true}}, "HTML-CSS": {linebreaks: {automatic: true}}, SVG: {linebreaks: {automatic: true}}});
		</script>
		<script type="text/javascript" async src="../js/MathJax-master/MathJax.js?config=default"></script>
		<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
		<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" async src="../js/editor.js"></script>
		<script type="text/javascript" async src="../js/test.js"></script>
		<script src="https://static.runoob.com/assets/jquery/jquery.growl/javascripts/jquery.growl.js" type="text/javascript"></script>
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
			<button id="edr" onclick="editorOK()">编辑公式完成</button>
			<button onclick="test3()">test2</button>
			<button onclick="refresh()">refresh</button>
			<button onclick="formUp()">提交修改</button>
		</div>
		<div class="main">
			<form id="tabUp" enctype="multipart/form-data">
			</form>
			<div class="col" id="dv2">				
				<div class="childW">
						<p>
							<button onclick="AddOptionBt($(this))">AddOption</button>
							<button onclick="deleteDiv($(this))">DeleteOption</button>
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
						<p class="neip">
							<label class="inputRes" title="tQuestions.question">问题</label>
							<button class="editAndsave" onclick="editAndSaveBt($(this))"> Edit</button>
							<br />						
						</p>
						
						<p class="neip">
							<label class="inputRes" title="tChoises[0].choise">选项</label>
							<button class="editAndsave" onclick="editAndSaveBt($(this))"> Edit</button>
							<br />							
						</p>
						<p class="neip">
							<label class="inputRes" title="tAnswers.answer">答案</label>
							<button class="editAndsave" onclick="editAndSaveBt($(this))"> Edit</button>	
							<br />						
						</p>			
				</div>				
			</div>			
		</div>
		<p style="display: none;" id="flg">0</p>
		<p style="display: none;" id="editorFlg">0</p>
		<p style="display: none;" id="editorUIFlg">0</p>
		<div id="editor" style="display: none;">
			<button id="cln" onclick="editorClose()">关闭编辑器</button>
			<div class="mathEditor">
			<table class="editorTable">
					<tr>
						<td> <button onclick="editorAppValue(this)" value="{">左分组符{</button></td>
						<td> <button onclick="editorAppValue(this)" value="}">右分组符}</button></td>
						<td> <button onclick="editorAppValue(this)" value="{ }">分组符{}</button></td>
						<td> <button onclick="editorAppValue(this)" value="^">^</button></td>
						<td> <button onclick="editorAppValue(this)" value="_">_</button></td>
						<td> <button onclick="editorAppValue(this)" value="(">(</button></td>
						<td> <button onclick="editorAppValue(this)" value=")">)</button></td>
						<td> <button onclick="editorAppValue(this)" value="[">[</button></td>
						<td> <button onclick="editorAppValue(this)" value="]">]</button></td>
						<td> <button onclick="editorAppValue(this)" value="\lbrace ">\(\lbrace\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\lbrace ">\(\rbrace\)</button></td>

						<td> <button onclick="editorAppValue(this)" value="|">|</button></td>
						<td> <button onclick="editorAppValue(this)" value="\frac{ }{ }">frac</button></td>
						<td> <button onclick="editorAppValue(this)" value="\sqrt{ }">sqrt</button></td>
						<td> <button onclick="editorAppValue(this)" value="\ldots">\(\ldots\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\cdots">\(\cdots\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\vec{ }">vec</button></td>
					</tr>

					<tr>
						<td> <button onclick="editorAppValue(this)" value="\${">行左公式符</button></td>
						<td> <button onclick="editorAppValue(this)" value="}$">行右公式符</button></td>
						<td> <button onclick="editorAppValue(this)" value="\alpha">\(\alpha\)</button></td>
						<td> <button onclick="editorAppValue(this)" value=" A">\(A\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\beta">\(\beta\)</button></td>
						<td> <button onclick="editorAppValue(this)" value=" B">\(B\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\gamma">\(\gamma\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\Gamma">\(\Gamma\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\delta">\(\delta\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\Delta">\(\Delta\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\epsilon">\(\epsilon\)</button></td>
						<td> <button onclick="editorAppValue(this)" value=" E">\(E\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\varepsilon">\(\varepsilon\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\zeta">\(\zeta\)</button></td>
						<td> <button onclick="editorAppValue(this)" value=" Z">\(Z\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\eta">\(\eta\)</button></td>
					</tr>

					<tr>
						<td> <button onclick="editorAppValue(this)" value=" H">\(H\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\theta">\(\theta\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\Theta">\(\Theta\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\vartheta">\(\vartheta\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\iota">\(\iota\)</button></td>
						<td> <button onclick="editorAppValue(this)" value=" I">\(I\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\kappa">\(\kappa\)</button></td>
						<td> <button onclick="editorAppValue(this)" value=" K">\(K\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\lambda">\(\lambda\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\Lambda">\(\Lambda\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\mu">\(\mu\)</button></td>
						<td> <button onclick="editorAppValue(this)" value=" M">\(M\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\nu">\(\nu\)</button></td>
						<td> <button onclick="editorAppValue(this)" value=" N">\(N\)</button></td>
					</tr>

					<tr>
						<td> <button onclick="editorAppValue(this)" value="\xi">\(\xi\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\Xi">\(\Xi\)</button></td>
						<td> <button onclick="editorAppValue(this)" value=" o">\(o\)</button></td>
						<td> <button onclick="editorAppValue(this)" value=" O">\(O\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\pi">\(\pi\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\Pi">\(\Pi\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\varpi">\(\varpi\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\rho">\(\rho\)</button></td>
						<td> <button onclick="editorAppValue(this)" value=" P">\(P\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\varrho">\(\varrho\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\sigma">\(\sigma\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\Sigma">\(\Sigma\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\varsigma">\(\varsigma\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\tau">\(\tau\)</button></td>
						<td> <button onclick="editorAppValue(this)" value=" T">\(T\)</button></td>
					</tr>

					<tr>
						<td> <button onclick="editorAppValue(this)" value="\upsilon">\(\upsilon\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\Upsilon">\(\Upsilon\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\phi">\(\phi\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\Phi">\(\Phi\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\varphi">\(\varphi\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\chi">\(\chi\)</button></td>
						<td> <button onclick="editorAppValue(this)" value=" X">\(X\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\psi">\(\psi\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\Psi">\(\Psi\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\omega">\(\omega\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\Omega">\(\Omega\)</button></td>
					</tr>

					<tr>
						<td> <button onclick="editorAppValue(this)" value="\pm">\(\pm\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\times">\(\times\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\div">\(\div\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\mid">\(\mid\)</button></td>
						
						<td> <button onclick="editorAppValue(this)" value="\cdot">\(\cdot\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\circ">\(\circ\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\ast">\(\ast\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\bigodot">\(\bigodot\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\bigotimes">\(\bigotimes\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\bigoplus">\(\bigoplus\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\leq">\(\leq\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\geq">\(\geq\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\neq">\(\neq\)</button></td>
					</tr>

					<tr>

						<td> <button onclick="editorAppValue(this)" value="\approx">\(\approx\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\equiv">\(\equiv\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\sum">\(\sum\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\prod">\(\prod\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\coprod">\(\coprod\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\emptyset">\(\emptyset\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\in">\(\in\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\notin">\(\notin\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\subset">\(\subset\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\supset">\(\supset\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\subseteq">\(\subseteq\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\supseteq">\(\supseteq\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\bigcap">\(\bigcap\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\bigcup">\(\bigcup\)</button></td>
					</tr>

					<tr>
						<td> <button onclick="editorAppValue(this)" value="\bigvee">\(\bigvee\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\bigwedge">\(\bigwedge\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\biguplus">\(\biguplus\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\bigsqcup">\(\bigsqcup\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\log">\(\log\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\lg">\(\lg\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\ln">\(\ln\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\bot">\(\bot\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\angle">\(\angle\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="30^\circ">\(30^\circ\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\sin">\(\sin\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\cos">\(\cos\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\tan">\(\tan\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\cot">\(\cot\)</button></td>
					</tr>

					<tr>
						<td> <button onclick="editorAppValue(this)" value="\sec">\(\sec\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\csc">\(\csc\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\prime">\(\prime\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\int">\(\int\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\iint">\(\iint\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\iiint">\(\iiint\)</button></td>
						
						<td> <button onclick="editorAppValue(this)" value="\oint">\(\oint\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\lim">\(\lim\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\infty">\(\infty\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\nabla">\(\nabla\)</button></td>
						
					</tr>

					<tr>
						<td> <button onclick="editorAppValue(this)" value="\forall">\(\forall\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\exists">\(\exists\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\not=">\(\not=\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\not>">\(\not>\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\not\subset">\(\not\subset\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\hat{y}">\(\hat{y}\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\check{y}">\(\check{y}\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\breve{y}">\(\breve{y}\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\overline{ }">over<br />line</button></td>
						<td> <button onclick="editorAppValue(this)" value="\underline{ }">under<br />line</button></td>
						<td> <button onclick="editorAppValue(this)" value="\overbrace{}">over<br />brace</button></td>
						<td> <button onclick="editorAppValue(this)" value="\underbrace{}">under<br />brace</button></td>

					</tr>

					<tr>
						<td> <button onclick="editorAppValue(this)" value="\uparrow">\(\uparrow\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\downarrow">\(\downarrow\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\Uparrow">\(\Uparrow\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\Downarrow">\(\Downarrow\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\rightarrow">\(\rightarrow\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\leftarrow">\(\leftarrow\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\Rightarrow">\(\Rightarrow\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\Leftarrow">\(\Leftarrow\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\longrightarrow">\(\longrightarrow\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\longleftarrow">\(\longleftarrow\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\Longrightarrow">\(\Longrightarrow\)</button></td>
						<td> <button onclick="editorAppValue(this)" value="\Longleftarrow">\(\Longleftarrow\)</button></td>
					</tr>
				</table>				
			</div>
		</div>
	</body>

</html>