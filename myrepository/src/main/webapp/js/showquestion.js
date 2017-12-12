//这个东西要怎么搞呢？我进入界面，可以选择是要进入添加标题界面还是展示界面。或者可以分开，这就是一个单纯的输入界面。
var arraySelect = new Array();
var arrayNonSelect = new Array();
var aleng1 = 0;
var aleng2 = 0;
var editFlag = 0;
CKEDITOR.config.height = 150;
CKEDITOR.config.width = '700px';
//这个是最新的。
function parseStr(str){
	var str2 = str.replace(/<span class="MathJax_Preview" style="display: none;"><\/span>/g,"");
	var str3 = str2.replace(/<nobr>.*<\/nobr>/g,"");
	var str4 = str3.replace(/<span class="MathJax" id="MathJax-Element.*?><\/span>/g,"");
	var str5 = str4.replace(/<script type="math\/tex" id="MathJax-Element.*?>/g,"\\(");
	var str6 = str5.replace(/<\/script>/g,"\\)");
	return str6;
}

function initNonrelativeLabel() {
	$.get("/apcompany/data/labelAll", function(result) {
		var jsonObj = JSON.parse(result);
		arrayNonSelect[0] = new Array();
		arrayNonSelect[1] = new Array();
		for (var jn in jsonObj) {
			if (jsonObj[jn].type === 1) {
				//arrayNonSelect[0].push(jsonObj[jn].id,jsonObj[jn].labelname);
				arrayNonSelect[0][arrayNonSelect[0].length] = new Array(jsonObj[jn].id, jsonObj[jn].labelname);
			} else if (jsonObj[jn].type === 2) {
				arrayNonSelect[1][arrayNonSelect[1].length] = new Array(jsonObj[jn].id, jsonObj[jn].labelname);
			}
		}
		aleng1 = arrayNonSelect[0].length;
		aleng2 = arrayNonSelect[1].length;
		//console.log("ee+aleng1:" + aleng1 +";aleng1:"+aleng1);
		initLabel();
	});
}

function initRelativeLabel() {
	$.get("/apcompany/data/labelRelAll", function(result) {
		var jsonObj = JSON.parse(result);
		var array = new Array();
		var i = 0;
		for (var jn in jsonObj) {
			//console.log(jsonObj[jn].id + ";" + jsonObj[jn].labelname + ";" + jsonObj[jn].parentsid);			
			array[i] = new Array();
			array[i][0] = jsonObj[jn].id;
			array[i][1] = jsonObj[jn].labelname;
			array[i][2] = jsonObj[jn].parentsid;
			array[i][3] = jsonObj[jn].depth; //代表深度。
			i++;
		}
		array.sort(function(x, y) {
			var m = x[3] - y[3];
			if (m === 0)
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
		for (var i = 0; i < array.length; i++) {
			if (array[i][3] != tmpdepth) {
				//如果不等，说明层次发生了变化，需要新建一个Array，然后才能赋值。
				//k = 0;array[depth-1] = new Array
				tmpdepth++;
				arraySelect[tmpdepth - 1] = new Array();
				k = 0;

			}
			//四个元素分别是id，labelname，parentsid，children的路径，自身的绝对路径.
			arraySelect[tmpdepth - 1][k] = new Array(array[i][0], array[i][1], array[i][2], "", (tmpdepth - 1) + ":" + k);
			//console.log(arraySelect[tmpdepth - 1][k][1]);
			//如果相等说明还在这个层次。
			if (tmpdepth != 1) {
				//第一层比较特别，不需要去查找上层的父节点，并把自己的绝对地址加进去。
				for (var j = 0; j < arraySelect[tmpdepth - 2].length; j++) {
					if (arraySelect[tmpdepth - 1][k][2] === arraySelect[tmpdepth - 2][j][0]) {
						//如果该节点是新添加节点的父节点，就把绝对地址加进去。
						arraySelect[tmpdepth - 2][j][3] += ("+" + (tmpdepth - 1) + ":" + k);
					}
				}
			}
			//赋值完成后，k要++
			k++;
		}
		//下面就要根据arraySelect来生成select。不同的select要用div隔开，不然会互相干扰change事件。
		//下面再定义select的change函数，补齐后面的select。
		for (var n = 0; n < arraySelect.length; n++) {
			var tmpDiv = $("<div></div>");
			tmpDiv.addClass("select" + n);
			var tmpselect = $("<select></select>");
			tmpselect.appendTo(tmpDiv);
			tmpDiv.appendTo($("#relativeDiv"));
			//下面要把option加进去。
			//option的选项只要设置第一级即可，其他的保留select就够了。		
		}
		for (var p = 0; p < arraySelect[0].length; p++) {

			tmpoption = $("<option></option>");
			//id和labelname，parentsid没必要，classname 可以用自身路径加上children路径。
			tmpoption.attr("id", "option" + arraySelect[0][p][0]);
			tmpoption.attr("value", arraySelect[0][p][1]);
			tmpoption.text(arraySelect[0][p][1]);
			//这样可以用+号分割，第一个是自己的绝对路径，而后面是子菜单的路径。
			tmpoption.addClass(arraySelect[0][p][4] + arraySelect[0][p][3]);
			tmpoption.appendTo($(".select0 select"));
		}
	});
}

function initLabel() {
	$(".label-selectSubject,.label-selectPublisher").find("select").each(function() {
		var tmpopt22 = $("<option>__</option>");
		tmpopt22.attr("value", "__");
		tmpopt22.appendTo($(this));
	});
	//console.log("aleng1:" + aleng1 +";aleng1:"+aleng1);
	for (var i = 0; i < aleng1; i++) {
		var tmpopt22 = $("<option></option>");
		tmpopt22.attr("value", arrayNonSelect[0][i][0]);
		tmpopt22.text(arrayNonSelect[0][i][1]);
		//console.log(arrayNonSelect[0][i][1]);
		tmpopt22.appendTo($(".label-selectSubject select"));
	}
	for (var i = 0; i < aleng2; i++) {
		var tmpopt22 = $("<option></option>");
		tmpopt22.attr("value", arrayNonSelect[1][i][0]);
		tmpopt22.text(arrayNonSelect[1][i][1]);
		//console.log(arrayNonSelect[1][i][1]);
		tmpopt22.appendTo($(".label-selectPublisher select"));
	}
	$(".label-proNum,.label-section").each(function() {
		var tmpopt22 = $("<option>__</option>");
		tmpopt22.attr("value", "__");
		tmpopt22.appendTo($(this).find("select"));
		for (var i = 0; i < 20; i++) {
			var tmpoption = $("<option></option>");
			tmpoption.attr("value", i + 1);
			tmpoption.text(i + 1);
			tmpoption.appendTo($(this).find("select"));
		}
	});
	$(".label-proDiff,.label-proKind,.label-proType").each(function() {
		var tmpopt22 = $("<option>_</option>");
		tmpopt22.attr("value", "_");
		tmpopt22.appendTo($(this).find("select"));
		for (var i = 0; i < 10; i++) {
			var tmpoption = $("<option></option>");
			tmpoption.attr("value", i);
			tmpoption.text(i);
			tmpoption.appendTo($(this).find("select"));
		}
	});
}

function editOpen(e) {
	if(editFlag === 0 && e.text() === "Edit"){
		//console.log($(e.target));是button
		//这样目标下面的neip类都要删除，然后在这里重新添加。
		var objPa = e.parent();
		$("#editDiv").appendTo(objPa);
		$("#editDiv").show();
		//labelDiv类下面共14个标签，前13个用:分隔符，分割出来label进行判定和还原
		var arrayLabel = new Array(0, 1, 4, 5, 6, 7, 12);
		var i = 0;
		$("#editDiv").parent().children(".labelDiv").children("label:eq(0),label:eq(1)").each(function() {
			var tmpstr = $(this).text().split(":");

			if (tmpstr[1].charAt(0) === "0")
				tmpstr[1] = tmpstr[1].charAt(1);
			//console.log("1:" +".nonrelativeLabel div:eq("+arrayLabel[i] + ")" + ";2:" + "option[text='" +tmpstr[1]+"']");
			$(".nonrelativeLabel").children("div:eq(" + arrayLabel[i] + ")").children("select").find("option[value='" + $(this).attr("value") + "']").attr("selected", true);
			//console.log($(".nonrelativeLabel").children("div:eq("+ arrayLabel[i] +")").prop("class"));
			i++;
		});
		$("#editDiv").parent().children(".labelDiv").children("label:eq(4),label:eq(5),label:eq(6),label:eq(7),label:eq(12)").each(function() {
			var tmpstr = $(this).text().split(":");
			if (tmpstr[1].charAt(0) === "0")
				tmpstr[1] = tmpstr[1].charAt(1);
			//console.log($(this).text());
			$(".nonrelativeLabel").children("div:eq(" + arrayLabel[i] + ")").find("option[value='1']").attr("selected", true);
			i++;
		});
		var tmpstr2 = $("#editDiv").parent().children(".labelDiv").children("label:eq(2)").text().split(":");
		if (tmpstr2[1] === "______") {
			$(".nonrelativeLabel").children("div:eq(2)").find("input:first").attr("checked", false);
			$(".nonrelativeLabel").children("div:eq(2)").find("input:last").attr("disabled", "true");
		} else {
			$(".nonrelativeLabel").children("div:eq(2)").find("input:first").attr("checked", true);
			$(".nonrelativeLabel").children("div:eq(2)").find("input:last").removeAttr("disabled");
			$(".nonrelativeLabel").children("div:eq(2)").find("input:last").val(tmpstr2[1].slice(0, 4) + "-" + tmpstr2[1].slice(4, 6));
		}
		var arrayJudge = new Array(3, 8, 9, 10, 11);
		i = 0;
		$("#editDiv").parent().children(".labelDiv").children("label:eq(3),label:eq(8),label:eq(9),label:eq(10),label:eq(11)").each(function() {
			var tmpstr = $(this).text();
			if (tmpstr === "_") {
				$(".nonrelativeLabel").children("div:eq(" + arrayJudge[i] + ")").find("input:first").attr("checked", false);
				$(".nonrelativeLabel").children("div:eq(" + arrayJudge[i] + ")").find("input:last").attr("disabled", "true");
			} else {
				$(".nonrelativeLabel").children("div:eq(" + arrayJudge[i] + ")").find("input:first").attr("checked", true);
				$(".nonrelativeLabel").children("div:eq(" + arrayJudge[i] + ")").find("input:last").removeAttr("disabled", "true");
				if (tmpstr.indexOf("不"))
					$(".nonrelativeLabel").children("div:eq(" + arrayJudge[i] + ")").find("input:last").attr("checked", true);
				else
					$(".nonrelativeLabel").children("div:eq(" + arrayJudge[i] + ")").find("input:last").attr("checked", false);
			}
			i++;
		});



		//quesT,opT,anT三个类，每个类都要添加一个div框，里面有一个input，一个commitChange
		//如果是新加的option，里面的button应该是commitAdd，
		//$(e.target).parent().children('.quesT').
		//我要引入ckeditor,那么之前的那个呢？现在我已经能处理了。用parseStr来处理下，扔到CKEditor中
		var tmpQues = $("<div></div>",{class:"neip"});
		var tmpQIn = $("<div></div>");
		tmpQIn.attr("id","editorQues");
		//tmpQIn.val(stringPar(e.parent().find('.quesT').html()));
		tmpQIn.prop("title",e.parent().find('.quesT').prop('title'));
		var tmpBtQ = $("<button>CommitChange</button>");
		tmpBtQ.attr('onclick', "commitChange($(this),'a')");
		tmpQIn.appendTo(tmpQues);
		tmpBtQ.appendTo(tmpQues);
		tmpQues.appendTo($(".editChildw"));
		initSample("editorQues");
		CKEDITOR.instances.editorQues.setData(parseStr(e.parent().find('.quesT').html()));
		//直接查找该类里面的quesT是可以的，然后就是 html，然后将进行替换。

		//对于选项还要添加一个 deleteoption
		var flagopt = 0;
		e.parent().find('.opT').each(function() {
			var tmpOpt = $("<div></div>",{class:"neip"});
			var tmpInp = $("<div></div>");			
			//var tmpstr = $(this).html();
			//tmpInp.val(stringPar(tmpstr.slice(4,tmpstr.length)));
			tmpInp.prop("title",$(this).attr("title"));
			tmpInp.prop("id","editorOpt" + flagopt);
			var tmpBtCommitChange = $("<button>CommitChange</button>");
			tmpBtCommitChange.attr('onclick',"commitChange($(this),'b')");
			var tmpBtDel = $("<button>delOption</button>");
			tmpBtDel.attr("onclick", "deloption($(this))");//这个函数一会再加。
			tmpInp.appendTo(tmpOpt);
			tmpBtCommitChange.appendTo(tmpOpt);
			tmpBtDel.appendTo(tmpOpt);
			tmpOpt.appendTo($(".editChildw"));
			switch(flagopt){
				case 0: {
					initSample('editorOpt0');
					CKEDITOR.instances.editorOpt0.setData(parseStr($(this).html()));
				}
				break;
				case 1: {
					initSample('editorOpt1');
					CKEDITOR.instances.editorOpt1.setData(parseStr($(this).html()));
				}
				break;
				case 2: {
					initSample('editorOpt2');
					CKEDITOR.instances.editorOpt2.setData(parseStr($(this).html()));
				}
				break;
				case 3: {
					initSample('editorOpt3');
					CKEDITOR.instances.editorOpt3.setData(parseStr($(this).html()));
				}
				break;
				case 4: {
					initSample('editorOpt4');
					CKEDITOR.instances.editorOpt4.setData(parseStr($(this).html()));
				}
				break;
				case 5: {
					initSample('editorOpt5');
					CKEDITOR.instances.editorOpt5.setData(parseStr($(this).html()));
				}
				break;
				case 6: {
					initSample('editorOpt6');
					CKEDITOR.instances.editorOpt6.setData(parseStr($(this).html()));
				}
				break;				
			}
			flagopt++;

		});
		//答案选项
		var tmpAn = $("<div></div>",{class:"neip"});
		var tmpAInp = $("<div></div>");
		tmpAInp.attr("id","editorAn");
		//var strAn = e.parent().find('.anT').html();
		//tmpAInp.val(stringPar(strAn.slice(15,strAn.length)));
		tmpAInp.prop('title', e.parent().find('.anT').attr("title"));
		var tmpbtCom = $("<button>CommitChange</button>");
		tmpbtCom.attr('onclick', "commitChange($(this),'c')");
		tmpAInp.appendTo(tmpAn);
		tmpbtCom.appendTo(tmpAn);
		tmpAn.appendTo($(".editChildw"));
		initSample("editorAn");
		CKEDITOR.instances.editorAn.setData(parseStr(e.parent().find('.anT').html()));
		editFlag = 1;
		//下面是步骤的那几图片。
		var tmpStep = $("<div></div>",{class:"neip"});
		tmpStep.appendTo($(".editChildw"));
		var tmpStepArr = new Array();
		e.parent().children(".frameStep").children("div").each(function(){
			var tmpframeEditor =  $("<div></div>");
			tmpframeEditor.prop("id",$(this).prop("id").replace("step","framestep"));
			tmpframeEditor.prop("title",$(this).prop("title"));
			var tmpStepEditor = $("<div></div>");
			tmpStepEditor.prop("id",$(this).prop("id"));
			tmpStepEditor.appendTo(tmpframeEditor);
			tmpStepArr.push(tmpStepEditor.prop("id"));
//			initSample(tmpStepEditor.get(0));
//			var tmphtmlstr = $(this).html();
//			//eval("CKEDITOR.instances." + tmpStepEditor.prop("id") +".setData('" + tmphtmlstr + "')");
			tmpStepBt = $("<button>ComStepChange</button>");
			tmpStepBt.attr('onclick',"commitChange($(this),'d')");
			tmpStepBt.appendTo(tmpframeEditor);
			tmpStepBt2 = $("<button>DelStep</button>");
			tmpStepBt2.attr('onclick',"delStep($(this))");
			tmpStepBt2.appendTo(tmpframeEditor);
			tmpframeEditor.appendTo(tmpStep);
			//必须先将各个dom节点插入到页面中
			initSample(tmpStepEditor.get(0));
			var tmphtmlstr = $(this).html();
			eval("CKEDITOR.instances." + tmpStepEditor.prop("id") +".setData('" + tmphtmlstr + "')");
			//console.log("open");
		});
		
		e.text("Save");
	} else if(editFlag === 1){
		//save的话，我选择重新刷新整个页面吧，还要加一个局部刷新的函数。
		if(e.text() === "Save"){
			e.text("Edit");
			$("#editDiv").find(".neip").remove();
			$("#editDiv").hide();
			editFlag = 0;
		}
		
	}
	
}
$(document).ready(function() {
	//下面我要添加非相关标签的select和相关标签的select。
	initNonrelativeLabel();
	initRelativeLabel();
	test2();
	//接下来我要设计一下编辑界面，如果要编辑某个选项，怎么弄？
	//$("#dv2").on("click", $(".editBt"), editOpen);
	$(".editChildw").click(function() {
		return false;
	});
	$("#relativeDiv").on("change", "select", function(e) {
		//console.log($(this)[0].tagName);
		//应该先把后面的select的option的都去掉。
		$(e.target).parent().nextAll().find("option").remove();
		//下一步就是根据取到的值将下一个select的option重新添加。
		var tmpstr = $(e.target).children("option:selected").prop("class");
		var tmpstr2 = tmpstr.split("+");
		for (var i = 1; i < tmpstr2.length; i++) {
			//解析出option的在arraySelect中的绝对路径。
			tmpstr3 = tmpstr2[i].split(":");
			tmpoption = $("<option></option>");
			//id和labelname，parentsid没必要，classname 可以用自身路径加上children路径。
			tmpoption.attr("id", "option" + arraySelect[tmpstr3[0]][tmpstr3[1]][0]);
			tmpoption.attr("value", arraySelect[tmpstr3[0]][tmpstr3[1]][1]);
			tmpoption.text(arraySelect[tmpstr3[0]][tmpstr3[1]][1]);
			//这样可以用+号分割，第一个是自己的绝对路径，而后面是子菜单的路径。
			tmpoption.addClass(arraySelect[tmpstr3[0]][tmpstr3[1]][4] + arraySelect[tmpstr3[0]][tmpstr3[1]][3]);
			tmpoption.appendTo($(e.target).parent().next().children("select"));
		}
	});
	if ( CKEDITOR.env.ie && CKEDITOR.env.version < 9 )
		CKEDITOR.tools.enableHtml5Elements( document );
});

function refresh() {
	MathJax.Hub.Queue(["Typeset", MathJax.Hub]);
}
//test2()用来展示select得到的数据。
function test2() {
	$.get("/apcompany/data/select", function(result) {
		//console.log(result); 
		//将result转为对象。
		var jsonObj = JSON.parse(result);
		//对对象执行循环操作。
		for (var jn in jsonObj) {
			var chw = $("<div></div>", {
				class: "childw"
			});
			var tmpbt = $("<button>Edit</button>");
			tmpbt.attr("onclick","editOpen($(this))");
			tmpbt.addClass("editBt");
			tmpbt.appendTo(chw);
			//添加一个删除按钮.
			var tmpbt2 = $("<button>Delete</button>");
			tmpbt.addClass("delBt");
			tmpbt.appendTo(chw);
			//我要添加标签了，一个是非相关标签，一个非相关标签。
			var labelDiv = $("<div></div>", {
					class: "labelDiv"
				});
				//利用ajax取回字符串，然后将
			var strNonRelLabel = jsonObj[jn].labels[0].labelid;
			var tmpSt = new Array(0, 2, 4, 10, 11, 13, 14, 15, 16, 17, 18, 19, 20);
			var tmpstrLabel = new Array("科目", "出版商", "年份", "是真题", "题号", "难度", "题类", "题型", "用计算器", "含数表", "含图片", "是证明题", "题部分");
			var tmpEnd = new Array(2, 4, 10, 11, 13, 14, 15, 16, 17, 18, 19, 20, 22);
			for (var i = 0; i < 13; i++) {
				var tmpSE = strNonRelLabel.slice(tmpSt[i], tmpEnd[i]);
				switch (i) {
					case 0:
						//需要添加前两个标签。
						if (strNonRelLabel.slice(tmpSt[i], tmpEnd[i]) === "__") {
							var tmpSubjectLabel = $("<label></label>");
							tmpSubjectLabel.text(tmpstrLabel[i] + ":" + "__");
							tmpSubjectLabel.attr("value", "__");
							tmpSubjectLabel.appendTo(labelDiv);
						} else {
							//arrayNonSelect[i].length写法不能识别，抑郁。
							for (var j = 0; j < arrayNonSelect[0].length; j++) {
								if (arrayNonSelect[0][j][0] === Number(strNonRelLabel.slice(tmpSt[i], tmpEnd[i]))) {
									var tmpSubjectLabel = $("<label></label>");
									tmpSubjectLabel.text(tmpstrLabel[i] + ":" + arrayNonSelect[i][j][1]);
									tmpSubjectLabel.attr("value", arrayNonSelect[i][j][0]);
									tmpSubjectLabel.appendTo(labelDiv);
								}
							}
						}
						break;
					case 1:
						if (strNonRelLabel.slice(tmpSt[i], tmpEnd[i]) === "__") {
							var tmpSubjectLabel = $("<label></label>");
							tmpSubjectLabel.text(tmpstrLabel[i] + ":" + "__");
							tmpSubjectLabel.attr("value", "__");
							tmpSubjectLabel.appendTo(labelDiv);
						} else {
							//arrayNonSelect[i].length写法不能识别，抑郁。
							for (var j = 0; j < arrayNonSelect[1].length; j++) {
								if (arrayNonSelect[1][j][0] === Number(strNonRelLabel.slice(tmpSt[i], tmpEnd[i]))) {
									var tmpSubjectLabel = $("<label></label>");
									tmpSubjectLabel.text(tmpstrLabel[i] + ":" + arrayNonSelect[i][j][1]);
									tmpSubjectLabel.attr("value", arrayNonSelect[i][j][0]);
									tmpSubjectLabel.appendTo(labelDiv);
								}
							}
						}
						break;
					case 2:
					case 5:
					case 6:
					case 7:
					case 4:
					case 12:
						//添加日期
						{
							var tmpNonRelLabel = $("<label></label>");
							tmpNonRelLabel.text(tmpstrLabel[i] + ":" + strNonRelLabel.slice(tmpSt[i], tmpEnd[i]));
							tmpNonRelLabel.appendTo(labelDiv);

						}
						break;
					case 3:
					case 8:
					case 9:
					case 10:
					case 11:
						//添加，都是些default之类的，这里要用三个，-，代表该属性不存在，用占位符表示
						//回头需要给insert文件添加占位符。
						{
							var tmpNonRelLabel = $("<label></label>");
							if (strNonRelLabel.slice(tmpSt[i], tmpEnd[i]) === "1") {
								tmpNonRelLabel.text(tmpstrLabel[i]);

							} else if (strNonRelLabel.slice(tmpSt[i], tmpEnd[i]) === "0") {
								tmpNonRelLabel.text("不" + tmpstrLabel[i]);
							} else if (strNonRelLabel.slice(tmpSt[i], tmpEnd[i]) === "_") {
								tmpNonRelLabel.text(tmpstrLabel[i] + " 该属性不使用");
							}
							tmpNonRelLabel.appendTo(labelDiv);

						}
						break;
				}
			}
			//利用ajax取回相关标签。
			var strRelLabel = jsonObj[jn].labels[0].labelsrelid;
			var tmpSelect = new Array();
			var flagstr = strRelLabel;
			//在这里，我只要将原来的标签的路径给贴出来就行了，我不弄自动对齐了，因为这个功能不一定有用，而且复杂。
			for (var i = 0; i < arraySelect.length; i++) {
				for (var j = 0; j < arraySelect[i].length; j++) {
					//console.log(arraySelect[i][j][0] + ":" + flagstr);
					if (arraySelect[i][j][0] === Number(flagstr)) {
						tmpSelect.push(i + ":" + j);
						if (arraySelect[i][j][2] != 0) {
							flagstr = arraySelect[i][j][2];
							i = 0;
							j = 0;
						}
					}
				}
			}
			var tmpLabelRel = $("<label></label>");
			for (var i = (tmpSelect.length - 1); i >= 0; i--) {
				var tmpstrrel = tmpSelect[i].split(":");
				var tmpBtext = $("<b></b>");
				var tmp1 = Number(tmpstrrel[0]);
				var tmp2 = Number(tmpstrrel[1]);

				if (i != 0)
					tmpBtext.text(arraySelect[tmp1][tmp2][1] + "--->");
				else
					tmpBtext.text(arraySelect[tmp1][tmp2][1]);
				tmpBtext.appendTo(tmpLabelRel);
			}
			tmpLabelRel.appendTo(labelDiv);
			labelDiv.appendTo(chw);
			//问题标签,一段标签由 p包含一个label和一个button组成.
			var pQs = $("<div></div>",{class:"newDiv"});
			var qs = $("<div></div>", {
				class: "quesT"
			});
			qs.attr("title", jsonObj[jn].id);

			qs.html(jsonObj[jn].question);
			qs.appendTo(pQs);

			$("<br />").appendTo(pQs);
			pQs.appendTo(chw);
			//下面是答案标签
			//答案标签，我需要弄个对称.
			var pAn = $("<div></div>",{class:"newDiv"});
			var an = $("<div></div>", {
				class: "anT"
			});
			an.attr("title", jsonObj[jn].tAnswers.id);
			an.html(jsonObj[jn].tAnswers.answer);
			var anFl = $("<div></div>");
			anFl.addClass("fl");
			var anflp = $("<p>The answer is: </p>");
			anflp.appendTo(anFl);
			anFl.appendTo(pAn);
			an.appendTo(pAn);
			$("<br />").appendTo(pAn);

			//下面是选项标签.
			var i = 0;
			var str = new String("ABCDEFGHIJK");
			for (var jn2 in jsonObj[jn].choices) {
				var tmpP = $("<div></div>",{class:"newDiv"});
				//console.log("option:" +jsonObj[jn].choices[jn2].choise);
				var op1 = $("<div></div>", {
					class: "opT"
				});
				op1.attr("title", jsonObj[jn].choices[jn2].id);
				op1.html(jsonObj[jn].choices[jn2].choise);				
				var opfl = $("<div></div>");
				opfl.addClass("fl");
				var opflp = $("<p>(" + str.charAt(i) + ")</p>");
				opflp.appendTo(opfl);
				opfl.appendTo(tmpP);
				op1.appendTo(tmpP);
				$("<br />").appendTo(tmpP);
				tmpP.appendTo(chw);
				i++;
			}

			pAn.appendTo(chw);
			//要把chw添加个title吧。
			chw.prop("title",jsonObj[jn].id);
			//jsonObj[jn].id我要用这个来取出step.
			//已经确定要用这个东西了。
			var stepDiv = $("<div></div>");
			stepDiv.addClass("frameStep");
			var stepText = $("<p>解题步骤</p>");
			stepText.appendTo(stepDiv);
			
			//console.log("jsonObj[jn].id:" + jsonObj[jn].id);
			//为什么会出现把所有的步骤都堆到最后一个问题下面呢？是ajax的异步原因吧？
			var tmpurl = "/apcompany/hits/selectHitsByQuestionid2?questionId=" + jsonObj[jn].id;
			//var tmpurl = "/apcompany/hits/selectHitsByQuestionid2";
			//var tmpformdata = new FormData();
			//tmpformdata.append("questionId",jsonObj[jn].id);
			$.ajax({  
		          type : "get",  
		          url : tmpurl, 		      
		          async : false,  
		          success : function(resultStep){  
		        	  var jsonStep = JSON.parse(resultStep);
						//json数据我要弄一个 
						for(var jnstep in jsonStep){
							var stepChild = $("<div></div>");
							stepChild.prop("id","stepid"+jsonStep[jnstep].id);
							stepChild.prop("title",jsonStep[jnstep].step);
							stepChild.html(jsonStep[jnstep].img_string);
							//这里的异步模式，所以要去根据quesitonid来寻找要插入的节点。
							stepChild.appendTo(stepDiv);
						}  
		          }
			});
					
			stepDiv.appendTo(chw);
			chw.appendTo($("#dv2"));
		}
		//在每个循环里都执行一次插入节点的操作。
	});
	MathJax.Hub.Queue(["Typeset", MathJax.Hub]);
}
var num = 0;
function commitChange(t, u) {
	//先生成数据格式。
	//u的值为  'a','b','c'分别代表问题，选项，答案，'d'代表step.	
	var tmpFormData = $("<form></form>");
	tmpFormData.attr("enctype", "multipart/form-data");
	tmpFormData.insertAfter($("#tabUp"));
	var formdata = new FormData(tmpFormData);
	//div 的title当id。
	//p标签下面的label标签的title
	//p标签下面的label的text当
	//var tmpname = t.parent().find("textarea").prop("title");
	//formdata.append("id", tmpname);
	//var tmpvalue = t.parent().find("textarea").val();
	//用一连串的规则去拼目标字符串。
	//直接getdata了.
	

	//$(this),父标签中的label的title情形，tQuestions.question tChoises[0].choise tAnswers.answer
	//但是这样的话，我就得把id考虑进去。title就不能放那些东西了。
	//根据第二个参数进行判断吧。
	var urlString = "";
	var editorData = "";
	if (u === 'a') {
		editorData = CKEDITOR.instances.editorQues.getData();
		formdata.append("id",$("#editorQues").prop("title"));
		formdata.append("question", editorData);
		urlString = "../../apcompany/data/updateQuesion";
	} else if (u === 'b') {		
		urlString = "../../apcompany/data/insertOrUpdateChoise";
		var tmpname = t.parent().children("div:first").prop("title");
		formdata.append("id",tmpname);
		//我要判定是第几个editor，然后好提取数据.
		var optflag = Number(t.parent().children("div:first").prop("id").charAt(9));
		//用eval应该就可以了.
		eval("editorData = CKEDITOR.instances.editorOpt" + optflag + ".getData()");
		if(tmpname === "0")
			formdata.append("questionid",$("#editorQues").prop("title"));
		formdata.append("choise", editorData);	
	} else if (u === 'c') {
		editorData = CKEDITOR.instances.editorAn.getData();
		formdata.append("id",$("#editorAn").prop("title"));
		formdata.append("answer", editorData );
		urlString = "../../apcompany/data/updateAnswers";
	} else if(u === 'd') {
		eval("editorData = CKEDITOR.instances."+ t.parent().prop("id").replace("framestep","step") +".getData()");
		formdata.append("id",t.parent().prop("id").replace("framestepid",""));
		formdata.append("step",t.parent().prop("title"));
		formdata.append("question_id",$("#editorQues").prop("title"));
		formdata.append("img_string", editorData );
		urlString = "/apcompany/hits/insertOrUpdateHits";
	}
	//ajax返回数据
	$.ajax({
		url: urlString,
		type: "post",
		data: formdata,
		processData: false,
		contentType: false,
		success: function(data) {
			console.log("over..");
			$.growl.notice({title: "提交修改问题", message: "修改成功!" });
			window.location.reload();
		}
	});
	$("#tabUp").next().remove();
}

function AddOptionBt(t) {	
	//其实就是添加一个input
	var tmpP = $("<p></p>", {
		class: "neip"
	});
	var tmpIn = $("<div></div>");
	tmpIn.prop('title', '0');
	tmpIn.prop("id","editorOpt7");
	tmpIn.appendTo(tmpP);
	var tmpBt = $("<button>Commit</button>");
	tmpBt.attr('onclick', "commitChange($(this),'b')");
	tmpBt.appendTo(tmpP);
	tmpP.insertBefore(t.parent().parent().children(".neip:last"));
	initSample("editorOpt7");
}

function commitLableChange() {
	//参考form
	var tmpform = $("<form></form>");
	tmpform.attr("enctype", "multipart/form-data");
	tmpform.appendTo("body");
	var formdata = new FormData(tmpform);
	//我要拼接label的标签。如果是相关标签，我要用哪个字段呢？tLabelsQuestionRel[0].
	var strnonrelLabel = "";
	var tmpstrnon = "";
	//科目
	tmpstrnon = $(".label-selectSubject option:selected").prop("value");
	if (tmpstrnon.length == 1)
		tmpstrnon = "0" + tmpstrnon;
	strnonrelLabel += tmpstrnon;

	//出版社
	tmpstrnon = $(".label-selectPublisher option:selected").prop("value");
	if (tmpstrnon.length == 1)
		tmpstrnon = "0" + tmpstrnon;
	strnonrelLabel += tmpstrnon;

	//年份
	if ($(".label-selectYear p").children("input").prop("checked") === true) {
		tmpstrnon = $(".label-selectYear").children("input").val();
		tmpstrnon = tmpstrnon.replace(/-/, "");
	} else {
		tmpstrnon = "______";
	}
	strnonrelLabel += tmpstrnon;

	//是否真题
	if ($(".label-isRealPro").find("input:first").prop("checked") === true) {
		if ($(".label-isRealPro").find("input:last").prop("checked") === true)
			strnonrelLabel += "1";
		else
			strnonrelLabel += "0";
	} else
		strnonrelLabel += "_";


	//题号
	var tmpstrnon = $(".label-proNum option:selected").text();
	if (tmpstrnon.length == 1)
		tmpstrnon = "0" + tmpstrnon;
	strnonrelLabel += tmpstrnon;

	//难度
	var tmpstrnon = $(".label-proDiff option:selected").text();
	strnonrelLabel += tmpstrnon;

	//题类
	var tmpstrnon = $(".label-proKind option:selected").text();
	strnonrelLabel += tmpstrnon;

	//题型
	var tmpstrnon = $(".label-proType option:selected").text();
	strnonrelLabel += tmpstrnon;

	//计算器
	if ($(".label-calculator").find("input:first").prop("checked") === true) {
		if ($(".label-calculator").find("input:last").prop("checked") === true)
			strnonrelLabel += "1";
		else
			strnonrelLabel += "0";
	} else
		strnonrelLabel += "_";


	//数表
	if ($(".label-diagram").find("input:first").prop("checked") === true) {
		if ($(".label-diagram").find("input:last").prop("checked") === true)
			strnonrelLabel += "1";
		else
			strnonrelLabel += "0";
	} else {
		strnonrelLabel += "_";
	}


	//图片
	if ($(".label-image").find("input:first").prop("checked") === true) {
		if ($(".label-image").find("input:last").prop("checked") === true)
			strnonrelLabel += "1";
		else
			strnonrelLabel += "0";
	} else
		strnonrelLabel += "_";


	//证明题
	if ($(".label-prove").find("input:first").prop("checked") === true) {
		if ($(".label-prove").find("input:last").prop("checked") === true)
			strnonrelLabel += "1";
		else
			strnonrelLabel += "0";
	} else
		strnonrelLabel += "_";
	tmpstrnon = $(".label-section option:selected").prop("value");
	if (tmpstrnon.length == 1)
		tmpstrnon = "0" + tmpstrnon;
	strnonrelLabel += tmpstrnon;
	console.log(strnonrelLabel);
	formdata.append("tLabelsQuestionRel[0].labelid", strnonrelLabel);

	var strrelLabel = "";
	$("#relativeDiv").find("select").each(function() {
		var tmpLabelId = $(this).children("option:selected").prop("id");
		if (tmpLabelId != null) {
			strreLabel = tmpLabelId.slice(6, tmpLabelId.length);
		}
	});
	formdata.append("tLabelsQuestionRel[0].labelsrelid", strreLabel);
	console.log(strreLabel);
	var tmpquesId = $("#editDiv").parent().find(".quesT").prop("title");
	formdata.append("tLabelsQuestionRel[0].questionid", tmpquesId);
	console.log(tmpquesId);
	$.ajax({
		//调用更新接口。
		url: "../../apcompany/data/updateQL",
		type: "post",
		data: formdata,
		processData: false,
		contentType: false,
		success: function(data) {
			console.log("over..");
			$.growl.notice({title: "提交修改", message: "修改成功!" });
		}
	});

	$("form").remove();
}




function deloption(t){
	var tmpFormData = $("<form></form>");
	tmpFormData.attr("enctype","multipart/form-data");
	tmpFormData.insertAfter($("#tabUp"));
	var formdata = new FormData(tmpFormData);
	formdata.append("id",t.parent().children("div:first").attr("title"));
	//删除选项的接口也没写。
	 $.ajax({
	 	url: "../../apcompany/data/delChoise",
	 	type: "post",
		data: formdata,
	 	processData:false,
         contentType:false,
         success:function(data){
             console.log("over..");
             $.growl.notice({title: "删除选项", message: "删除成功!" });
             window.location.reload();
         }
	 });
	
	tmpFormData.remove();
}
function delUiOption(t) {
	$(".editChildw .neip:last").prev().remove();
}

var initSample = ( function() {
	var wysiwygareaAvailable = isWysiwygareaAvailable(),
		isBBCodeBuiltIn = !!CKEDITOR.plugins.get( 'bbcode' );

	return function(id) {
		var editorElement = CKEDITOR.document.getById( id );

		// :(((
		if ( isBBCodeBuiltIn ) {
			editorElement.setHtml(
				'Hello world!\n\n' +
				'I\'m an instance of [url=http://ckeditor.com]CKEditor[/url].'
			);
		}

		// Depending on the wysiwygare plugin availability initialize classic or inline editor.
		if ( wysiwygareaAvailable ) {
			CKEDITOR.replace( id,{
				extraPlugins: 'mathjax,base64image',
				mathJaxLib: '../js/MathJax-master/MathJax.js?config=TeX-AMS_HTML',			
				height: 100
			} );
			
		} else {
			editorElement.setAttribute( 'contenteditable', 'true' );
			CKEDITOR.inline( id ,{
				extraPlugins: 'mathjax,base64image',
				mathJaxLib: '../js/MathJax-master/MathJax.js?config=TeX-AMS_HTML',
				height: 100
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
})();

function delStep(t){
	var formdata = new FormData();
	formdata.append("id",t.parent().prop("id").replace("framestepid",""));
	$.ajax({
		url: "/apcompany/hits/deleleHits",
	 	type: "post",
		data: formdata,
	 	processData:false,
         contentType:false,
         success:function(data){
             console.log("over..");
             $.growl.notice({title: "删除步骤", message: "删除成功!" });
             window.location.reload();
         }
	});
}
