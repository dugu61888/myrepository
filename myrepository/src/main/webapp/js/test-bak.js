//这个东西要怎么搞呢？我进入界面，可以选择是要进入添加标题界面还是展示界面。或者可以分开，这就是一个单纯的输入界面。
var arraySelect = new Array();
CKEDITOR.config.height = 150;
CKEDITOR.config.width = 'auto';

function initNonrelativeLabel(){	
	$(".label-selectSubject,.label-selectPublisher").find("select").each(function() {
		var tmpopt22 = $("<option>__</option>");
		tmpopt22.attr("value","__");
		tmpopt22.appendTo($(this));
	});
	$.get("/apcompany/data/labelAll", function(result) {
		var jsonObj = JSON.parse(result);
		$(".label-proNum,.label-section,.label-proDiff,.label-proKind,.label-proType").each(function(){
			var tmpopt = $("<option>-<option>");
			tmpopt.appendTo($(this).children("select"));

		});
		for(var jn in jsonObj){
			if(jsonObj[jn].type === 1){
				//console.log(jsonObj[jn].labelname + ":" + jsonObj[jn].type);
				var tmpsubject = $("<option></option>");
				tmpsubject.text(jsonObj[jn].labelname);
				tmpsubject.prop("value",jsonObj[jn].id);
				tmpsubject.appendTo($(".label-selectSubject").children("select"));
			}else if(jsonObj[jn].type === 2){
				//console.log(jsonObj[jn].labelname + ":" + jsonObj[jn].type);
				var tmppublisher = $("<option></option>");
				tmppublisher.text(jsonObj[jn].labelname);
				tmppublisher.prop("value",jsonObj[jn].id);
				tmppublisher.appendTo($(".label-selectPublisher").children("select"));
			}
		}
	});
	$(".label-proNum,.label-section").each(function(){
		var tmpopt22 = $("<option>__</option>");
		tmpopt22.attr("value","__");
		tmpopt22.appendTo($(this).find("select"));
		for(var i = 0;i < 20;i++){
			var tmpoption = $("<option></option>");
			tmpoption.attr("value",i+1);
			tmpoption.text(i+1);
			tmpoption.appendTo($(this).find("select"));
		}
	});
	$(".label-proDiff,.label-proKind,.label-proType").each(function(){
		var tmpopt22 = $("<option>_</option>");
		tmpopt22.attr("value","_");
		tmpopt22.appendTo($(this).find("select"));
		for(var i = 0;i < 10;i++){
			var tmpoption = $("<option></option>");
			tmpoption.attr("value",i+1);
			tmpoption.text(i);
			tmpoption.appendTo($(this).find("select"));
		}
	});
	
}
function initRelativeLabel(){
	$.get("/apcompany/data/labelRelAll",function(result){
		var jsonObj = JSON.parse(result);
		var array = new Array();
		var i = 0;
		for(var jn in jsonObj){
			//console.log(jsonObj[jn].id + ";" + jsonObj[jn].labelname + ";" + jsonObj[jn].parentsid);			
			array[i] = new Array();
			array[i][0] = jsonObj[jn].id;
			array[i][1] = jsonObj[jn].labelname;
			array[i][2] = jsonObj[jn].parentsid;
			array[i][3] = jsonObj[jn].depth;//代表深度。
			i++;
		}
		array.sort(function(x,y){
			var m = x[3] - y[3];
			if(m === 0)
				return x[0] - y[0];
			else 
				return m;
		});
		//console.log(array);
		//下一步应该是根据这个东西来生成各种button
		//首先是生成级别为1的option，
		var tmpdepth = 1;
		var newlevel = 0;
		arraySelect[0] = new Array();
		var k = 0;
		for(var i = 0;i < array.length;i++){
			if(array[i][3] != tmpdepth){
				//如果不等，说明层次发生了变化，需要新建一个Array，然后才能赋值。
				//k = 0;array[depth-1] = new Array
				tmpdepth++;
				arraySelect[tmpdepth-1] = new Array();
				k = 0;
				
			}
			//四个元素分别是id，labelname，parentsid，children的路径，自身的绝对路径.
			arraySelect[tmpdepth - 1][k] = new Array(array[i][0],array[i][1],array[i][2],"",(tmpdepth - 1) + ":" +k);
			//console.log(arraySelect[tmpdepth - 1][k][1]);
			//如果相等说明还在这个层次。
			if(tmpdepth != 1){
				//第一层比较特别，不需要去查找上层的父节点，并把自己的绝对地址加进去。
				for(var j=0;j < arraySelect[tmpdepth-2].length;j++){
					if(arraySelect[tmpdepth - 1][k][2] === arraySelect[tmpdepth-2][j][0]){
						//如果该节点是新添加节点的父节点，就把绝对地址加进去。
						arraySelect[tmpdepth-2][j][3] += ("+"+(tmpdepth-1)+":"+k);
					}
				}
			}
			//赋值完成后，k要++
			k++;
		}
		//下面就要根据arraySelect来生成select。不同的select要用div隔开，不然会互相干扰change事件。
		for(var n = 0; n < arraySelect.length;n++){
			var tmpDiv = $("<div></div>");
			tmpDiv.addClass("select" + n);
			var tmpselect = $("<select></select>");
			tmpselect.appendTo(tmpDiv);
			tmpDiv.appendTo($("#relativeDiv"));
			//下面要把option加进去。
			//option的选项只要设置第一级即可，其他的保留select就够了。		
		}
		for(var p = 0;p < arraySelect[0].length;p++){
			
			tmpoption = $("<option></option>");
			//id和labelname，parentsid没必要，classname 可以用自身路径加上children路径。
			tmpoption.attr("id","option" + arraySelect[0][p][0]);
			tmpoption.attr("value",arraySelect[0][p][1]);
			tmpoption.text(arraySelect[0][p][1]);
			//这样可以用+号分割，第一个是自己的绝对路径，而后面是子菜单的路径。
			tmpoption.addClass(arraySelect[0][p][4] + arraySelect[0][p][3]);
			tmpoption.appendTo($(".select0 select"));
		}
		//下面再定义select的change函数，补齐后面的select。
	});
}
$(document).ready(function(){
	$("#dv2").on("mouseover",".childw p",function(){
		$(this).css("background-color","#C8E0F0");
	});
	$("#dv2").on("mouseout",".childw p",function(){
		$(this).css("background-color","#FFFFFF");
	});
	//下面我要添加非相关标签的select和相关标签的select。
	initNonrelativeLabel();
	initRelativeLabel();
	$("#relativeDiv").on("change","select",function(e){
		//console.log($(this)[0].tagName);
		//应该先把后面的select的option的都去掉。
		$(e.target).parent().nextAll().find("option").remove();
		//下一步就是根据取到的值将下一个select的option重新添加。
		var tmpstr = $(e.target).children("option:selected").prop("class");
		var tmpstr2 = tmpstr.split("+");
		for(var i = 1;i < tmpstr2.length;i++){
			//解析出option的在arraySelect中的绝对路径。
			tmpstr3 = tmpstr2[i].split(":");			
			tmpoption = $("<option></option>");
			//id和labelname，parentsid没必要，classname 可以用自身路径加上children路径。
			tmpoption.attr("id","option" + arraySelect[tmpstr3[0]][tmpstr3[1]][0]);
			tmpoption.attr("value",arraySelect[tmpstr3[0]][tmpstr3[1]][1]);
			tmpoption.text(arraySelect[tmpstr3[0]][tmpstr3[1]][1]);
			//这样可以用+号分割，第一个是自己的绝对路径，而后面是子菜单的路径。
			tmpoption.addClass(arraySelect[tmpstr3[0]][tmpstr3[1]][4] + arraySelect[tmpstr3[0]][tmpstr3[1]][3]);
			tmpoption.appendTo($(e.target).parent().next().children("select"));
		}
	});
	$(".label-selectYear,.label-isRealPro,.label-calculator,.label-diagram,.label-image,.label-prove").each(function(){
		$(this).find("input:first").on("click",function(e){
			if($(e.target).prop("checked") === true){
				$(e.target).parent().parent().find("input:last").removeAttr("disabled");
			} else {
				$(e.target).parent().parent().find("input:last").attr("disabled","true");
			}
			
		});
	});
	if ( CKEDITOR.env.ie && CKEDITOR.env.version < 9 )
		CKEDITOR.tools.enableHtml5Elements( document );
});
function formUp(){
	var formdata = new FormData($("#tabUp"));
	//虽然添加了label，没有什么问题
	$(".childw:first p label").each(function(){
		//console.log($(this).children("label").text());
		//console.log($(this).children("input").prop("name"));
		var tmpname = $(this).prop("title");
		var tmpvalue = $(this).html();
		//用一连串的规则去拼目标字符串。
		var string2 = tmpvalue.replace(/<span.*?>.*?<\/span>/g,"");		
		var string3 = string2.replace(/<\/span>/g,"");
		var string4 = string3.replace(/<\/nobr>/g,"");		
		var string5 = string4.replace(/<script type="math\/tex" id="MathJax-Element-.*?">/g,"${");
		var string6 = string5.replace(/<\/script>/g,"}$");
		var string7 = string6.replace(/<label.*?>/g,"");		
		var string8 = string7.replace(/<\/label>/g,"");		
		var string9 = string8.replace(/<button.*?>.*?<\/button>/g,"");
		console.log(tmpname,string9);
		//formdata.append($(this).children("input").prop("name"),$(this).children("label").text());
		formdata.append(tmpname,string9);
		
	});
	//我要拼接label的标签。如果是相关标签，我要用哪个字段呢？tLabelsQuestionRel[0].
	var strnonrelLabel = "";
	var tmpstrnon = "";
	//科目
		tmpstrnon = $(".label-selectSubject option:selected").prop("value");
		if(tmpstrnon.length == 1)
			tmpstrnon = "0" + tmpstrnon;
	strnonrelLabel += tmpstrnon;
	console.log(strnonrelLabel);
	//出版社
	tmpstrnon = $(".label-selectPublisher option:selected").prop("value");
	if(tmpstrnon.length == 1)
		tmpstrnon = "0" + tmpstrnon;
	strnonrelLabel += tmpstrnon;
	console.log(strnonrelLabel);
	//年份
	if($(".label-selectYear p").children("input").prop("checked") === true){
		tmpstrnon = $(".label-selectYear").children("input").val();
		tmpstrnon = tmpstrnon.replace(/-/,"");
	} else {
		tmpstrnon = "______";
	}	
	strnonrelLabel += tmpstrnon;
	console.log(strnonrelLabel);
	//是否真题
	if($(".label-isRealPro p").children("input").prop("checked") === true){
		if($(".label-isRealPro input").prop("checked") === true)
			strnonrelLabel += "1";
		else 
			strnonrelLabel += "0";
	} else 
		strnonrelLabel += "_";
	console.log(strnonrelLabel);
	
	//题号
	var tmpstrnon = $(".label-proNum option:selected").text();
	if(tmpstrnon.length == 1)
		tmpstrnon = "0" + tmpstrnon;
	strnonrelLabel += tmpstrnon;
	console.log(strnonrelLabel);
	//难度
	var tmpstrnon = $(".label-proDiff option:selected").text();
	strnonrelLabel += tmpstrnon;
	console.log(strnonrelLabel);
	//题类
	var tmpstrnon = $(".label-proKind option:selected").text();
	strnonrelLabel += tmpstrnon;
	console.log(strnonrelLabel);
	//题型
	var tmpstrnon = $(".label-proType option:selected").text();
	strnonrelLabel += tmpstrnon;
	console.log(strnonrelLabel);
	//计算器
	if($(".label-calculator p").children("input").prop("checked") === true){
		if($(".label-calculator input").prop("checked") === true)
			strnonrelLabel += "1";
		else 
			strnonrelLabel += "0";
	} else 
		strnonrelLabel += "_";
	console.log(strnonrelLabel);
	
	//数表
	if($(".label-diagram p").children("input").prop("checked") === true){
		if($(".label-diagram input").prop("checked") === true)
			strnonrelLabel += "1";
		else 
			strnonrelLabel += "0";
	} else {
		strnonrelLabel += "_";
	}
	console.log(strnonrelLabel);
	
	//图片
	if($(".label-image p").children("input").prop("checked") === true){
		if($(".label-image input").prop("checked") === true)
			strnonrelLabel += "1";
		else 
			strnonrelLabel += "0";
	} else 
		strnonrelLabel += "_";
	console.log(strnonrelLabel);
	
	//证明题
	if($(".label-prove p").children("input").prop("checked") === true){
		if($(".label-prove input").prop("checked") === true)
			strnonrelLabel += "1";
		else 
			strnonrelLabel += "0";
	} else
		strnonrelLabel += "_";
	//console.log(strnonrelLabel);
	//部分
	tmpstrnon = $(".label-section option:selected").prop("value");
	if(tmpstrnon.length == 1)
		tmpstrnon = "0" + tmpstrnon;
	strnonrelLabel += tmpstrnon;
	console.log(strnonrelLabel);
	formdata.append("tLabelsQuestionRel[0].labelid",strnonrelLabel);
	//上面就是如何弄拼接这个字符传。
	//下面的相关性标签，要读取三个的全部id，然后都发送。这样看来，arrayselect还要保存每个id的父辈全部的id，这
	//是一个问题，如果不保存，我就要多次遍历了。还是保存下来比较好。我可以考虑生成array的时候改动。这样我上传的时候
	//只要上传最后一个id值就行了。
	var strrelLabel = "";
	$("#relativeDiv").find("select").each(function(){
		var tmpLabelId = $(this).children("option:selected").prop("id");
		if(tmpLabelId != null){
			strreLabel = tmpLabelId.slice(6,tmpLabelId.length);
		}			
	});
	formdata.append("tLabelsQuestionRel[0].labelsrelid",strreLabel);
	$.ajax({
        url:"../../apcompany/data/insert",
        type:"post",
        data:formdata,
        processData:false,
        contentType:false,
        success:function(data){
            console.log("over..");
            $.growl.notice({title: "插入问题", message: "插入成功!" });
        }
	});
	
}
function editorOK(){
		
}
function refresh(){
	MathJax.Hub.Queue(["Typeset",MathJax.Hub]);
}
//test2()用来展示select得到的数据。
function test2(){
	$.get("/apcompany/data/select", function(result) {  
       //console.log(result); 
       //将result转为对象。
       var jsonObj = JSON.parse(result);
       //对对象执行循环操作。
       for(var jn in jsonObj)
		{
    	   var chw = $("<div></div>" ,{class:"childw"});
    	   //chw.attr("title",jsonObj[jn].id);
    	   //两个button
    	   var pBt = $("<p></p>");
    	   var btAddOp=$("<button>AddOption</button>");
    	   var btDelOp=$("<button>DelOption</button>");
    	   btAddOp.attr("onclick","AddOptionBt($(this))");
    	   btDelOp.attr("onclick","deleteDiv($(this))");
    	   btAddOp.appendTo(pBt);
    	   btDelOp.appendTo(pBt);
    	   pBt.appendTo(chw);
    	   //问题标签,一段标签由 p包含一个label和一个button组成.
    	   var pQs = $("<p></p>");
    	   var qs = $("<label></label>",{class:"quesT"});
    	   qs.attr("title",jsonObj[jn].id);
    	   var btQs = $("<button>Edit</button>");
    	   btQs.attr("onclick","editAndSaveBt($(this))");
    	   btQs.addClass("editAndsave");
    	   var btCommitQs = $("<button>Commit</button>");
    	   btCommitQs.addClass=$("btCommit");
    	   btCommitQs.attr("onclick","commitChange($(this),'a')");   	   
    	   qs.html(jsonObj[jn].question);
    	   qs.appendTo(pQs);
    	   btQs.appendTo(pQs);
    	   btCommitQs.appendTo(pQs);
    	   $("<br />").appendTo(pQs);   	   
    	   pQs.appendTo(chw);
    	 //下面是答案标签
    	   var pAn = $("<p></p>");
    	   var an = $("<label></label>",{class:"anT"});
    	   an.attr("title",jsonObj[jn].tAnswers.id);
    	   var btAn = $("<button>Edit</button>");
    	   btAn.attr("onclick","editAndSaveBt($(this))");
    	   btAn.addClass("editAndsave");
    	   var btCommitAn = $("<button>Commit</button>");
    	   btCommitAn.addClass=$("btCommit");
    	   btCommitAn.attr("onclick","commitChange($(this),'c')");    	   
    	   an.html( "The answer is: "+jsonObj[jn].tAnswers.answer);
    	   an.appendTo(pAn);
    	   btAn.appendTo(pAn);
    	   btCommitAn.appendTo(pAn);
    	   $("<br />").appendTo(pAn); 
    	   //pAn.appendTo(chw);
    	   
    	   //下面是选项标签.
    	   var i = 0;
    	   var str = new String("ABCDEFGHIJK");
			for(var jn2 in jsonObj[jn].choices)
			{
				var tmpP = $("<p></p>");
				//console.log("option:" +jsonObj[jn].choices[jn2].choise);
				var op1 = $("<label></label>",{class:"opT"});
				op1.attr("title",jsonObj[jn].choices[jn2].id);
				var opButton = $("<button>Edit</button>");
				opButton.addClass("editAndsave");
				opButton.attr("onclick","editAndSaveBt($(this))");
				var btCommitTmp = $("<button>Commit</button>");
		    	btCommitTmp.addClass=$("btCommit");
		    	btCommitTmp.attr("onclick","commitChange($(this),'b')");		    	
				op1.html("("+ str.charAt(i)+ ") " + jsonObj[jn].choices[jn2].choise);
				op1.appendTo(tmpP);
				opButton.appendTo(tmpP);
				btCommitTmp.appendTo(tmpP);
				$("<br />").appendTo(tmpP);
				tmpP.appendTo(chw);				
				i++;
				
			}
			
			pAn.appendTo(chw);
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
function AddOptionBt(t){
	num++;
	//var op = $("<p>选项:<input name=tChoises["+num+"].choise /></p>" ,{class:"neip"});
	//var op = $("<p>选项:<input name=tChoises["+num+"].choise /></p>" ,{class:"neip"});
	var op = $("<p></p>" ,{class:"neip"});
	var insideD = $("<label>选项</label>");
	var insideE = $("<button>Edit</button>")
	//var insideA = $("<input name=tChoises["+num+"].choise/>",{onblur:"exitInput($(this))"});
	//var insideB = $("<label></label>");
	//var insideC = $("<button>openEditor</button>");
	insideD.addClass("inputRes");
	insideD.attr("title","tChoises[" + num +"].choise");
	insideE.addClass("editAndsave");
	insideE.attr("onclick","editAndSaveBt($(this))");
	op.addClass("neip");
	insideD.appendTo(op);
	insideE.appendTo(op);
	$("<br/>").appendTo(op);
	//insideA.attr("onblur","exitInput($(this))");
	//attr才能实现这个，但是我在html上，在创建时候添加也行啊，而且class用的是Addclass，不明白为什么.
	//insideC.attr("onclick","editorOpen($(this))");
	//insideA.appendTo(op);
	//insideC.appendTo(op);
	//insideB.appendTo(op);
	//op.appendTo($("#cinQues"));
	op.insertBefore(t.parent().parent().children("p:last"));
}
function deleteDiv(t){
	t.parent().parent().children("p:last").prev().remove();
	//$("#cinQues p:last").remove();
	num--;
}
function goInput(t){
	console.log("go input");
}
function exitInput(t){
	//console.log("exit input");
	//t.next().next().html("$\{ " + t.next().next().children("span").text() + t.val() +" \}$");
	//refresh();
	//MathJax.Hub.Queue(["Typeset",MathJax.Hub]);
}
//if the answer is

function tmpClick(t){
//	console.log("in");
//	//console.log($("#tmpPage").text());
//	var str = $("#flg").text();
//	$("#tmpPage" + str).text( "$\{" + $(".tmpInput").val() + "\}$" );
//	MathJax.Hub.Queue(["Typeset",MathJax.Hub]);
//	$("#tmpPage" + str).css("background-color","#FFFFFF");
//	var num = Number(str);
//	num++;
//	$("#flg").text(num);
//	//$("#tmpPage").removeAttr("id");
//	$(".tmpInput").remove();
//	$(".tmpBt").remove();
	console.log("go editor");
	$("#editor").appendTo(t.parent());
	$("#editor").show();
}
function clickNeipP(t){
	//弹出一个input和两个button,一个button关联打开editor,未必会需要打开editor。
	//---另一个button关联提交修改。再加一个，三个button。第三个button，进入编辑模式。
}
function editAndSaveBt(t){
	//需要做什么呢。插入一个input按钮和一个打开editor的button.
	if($("#editorFlg").text() === "0")
	{
		//进入修改模式.
		t.html("Save");
		$("#editorFlg").text("1");
		var tmpInput = $("<textarea></textarea>");
		var tmpEditorBt = $("<button>openEditor</button>");		
		tmpInput.addClass("editAndSaveInput");
		tmpEditorBt.addClass("editorOpenBt");
		tmpEditorBt.attr("onclick","tmpClick($(this))");
		tmpInput.appendTo(t.parent());
		tmpEditorBt.appendTo(t.parent());
		//进入修改模式，将lable中的text()引入到input中.
		var oldInputTxt = t.parent().children("label").html();
		//用一连串的规则去拼目标字符串。
		var string2 = oldInputTxt.replace(/<span.*?>.*?<\/span>/g,"");		
		var string3 = string2.replace(/<\/span>/g,"");
		var string4 = string3.replace(/<\/nobr>/g,"");		
		var string5 = string4.replace(/<script type="math\/tex" id="MathJax-Element-.*?">/g,"${");
		var string6 = string5.replace(/<\/script>/g,"}$");
		var string7 = string6.replace(/<label.*?>/g,"");		
		var string8 = string7.replace(/<\/label>/g,"");		
		var string9 = string8.replace(/<button.*?>.*?<\/button>/g,"");
		t.parent().children("textarea").val(string9);
	} else {
		t.text("Edit");
		var txtInput = t.parent().children("textarea").val();
		t.parent().children("label").text(txtInput);
		$("#editorFlg").text("0");
		$(".editAndSaveInput").remove();
		$(".editorOpenBt").remove();
		//将input的值放入到lable中,清空本身。
	}
}
function commitChange(t,u){
	//先生成数据格式。
	//u的值为  'a','b','c'分别代表问题，选项，答案。	
	var tmpFormData = $("<form></form>");
	tmpFormData.attr("enctype","multipart/form-data");
	tmpFormData.insertAfter($("#tabUp"));
	var formdata = new FormData(tmpFormData);
	//div 的title当id。
	//p标签下面的label标签的title
	//p标签下面的label的text当
	var tmpname = t.parent().children("label").prop("title");
	formdata.append("id",tmpname);
	var tmpvalue = t.parent().children("label").text();
	//用一连串的规则去拼目标字符串。
	var string2 = tmpvalue.replace(/<span.*?>.*?<\/span>/g,"");		
	var string3 = string2.replace(/<\/span>/g,"");
	var string4 = string3.replace(/<\/nobr>/g,"");		
	var string5 = string4.replace(/<script type="math\/tex" id="MathJax-Element-.*?">/g,"${");
	var string6 = string5.replace(/<\/script>/g,"}$");
	var string7 = string6.replace(/<label.*?>/g,"");		
	var string8 = string7.replace(/<\/label>/g,"");		
	var string9 = string8.replace(/<button.*?>.*?<\/button>/g,"");
	
	//$(this),父标签中的label的title情形，tQuestions.question tChoises[0].choise tAnswers.answer
	//但是这样的话，我就得把id考虑进去。title就不能放那些东西了。
	//根据第二个参数进行判断吧。
	var urlString = "";
	if(u === 'a')
	{	 
		formdata.append("question",string9);
		urlString="../../apcompany/data/updateQuesion";
	} else if(u === 'b') {
		string10 = string9.substr(3);
		formdata.append("choise",string10);
		urlString="../../apcompany/data/updateChoise";
	} else if(u === 'c') {
		formdata.append("answer",string9);
		urlString="../../apcompany/data/updateAnswers";
	}
	console.log(urlString);
	//ajax返回数据
	$.ajax({
        url:urlString,
        type:"post",
        data:formdata,
        processData:false,
        contentType:false,
        success:function(data){
            console.log("over..");
        }
	});
	$("#tabUp").next().remove();
	
}


	

	// The trick to keep the editor in the sample quite small
	// unless user specified own height.

var initSample = ( function() {
	var wysiwygareaAvailable = isWysiwygareaAvailable(),
		isBBCodeBuiltIn = !!CKEDITOR.plugins.get( 'bbcode' );

	return function() {
		var editorElement = CKEDITOR.document.getById( 'editor' );

		// :(((
		if ( isBBCodeBuiltIn ) {
			editorElement.setHtml(
				'Hello world!\n\n' +
				'I\'m an instance of [url=http://ckeditor.com]CKEditor[/url].'
			);
		}

		// Depending on the wysiwygare plugin availability initialize classic or inline editor.
		if ( wysiwygareaAvailable ) {
			CKEDITOR.replace( 'ckeditor',{
				extraPlugins: 'mathjax,base64image',
				mathJaxLib: '../js/MathJax-master/MathJax.js?config=TeX-AMS_HTML',			
				height: 600
			} );
			
		} else {
			editorElement.setAttribute( 'contenteditable', 'true' );
			CKEDITOR.inline( 'ckeditor' ,{
				extraPlugins: 'mathjax,base64image',
				mathJaxLib: '../js/MathJax-master/MathJax.js?config=TeX-AMS_HTML',
				height: 600
			});			

			// TODO we can consider displaying some info box that
			// without wysiwygarea the classic editor may not work.
		}
	};

	function isWysiwygareaAvailable() {
		// If in development mode, then the wysiwygarea must be available.
		// Split REV into two strings so builder does not replace it :D.
		if ( CKEDITOR.revision == ( '%RE' + 'V%' ) ) {
			return true;
		}

		return !!CKEDITOR.plugins.get( 'wysiwygarea' );
	}
} )();
	




