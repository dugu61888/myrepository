var arraySelect = new Array();
function showSelect(objArray){
	//我要根据objJson的数据	
	//我需要根据depth来创建new Array;
	var tmpdepth = 1;
	var newlevel = 0;
	arraySelect[0] = new Array();
	var k = 0;
	for(var i = 0;i < objArray.length;i++){
		if(objArray[i][3] != tmpdepth){
			//如果不等，说明层次发生了变化，需要新建一个Array，然后才能赋值。
			//k = 0;array[depth-1] = new Array
			tmpdepth++;
			arraySelect[tmpdepth-1] = new Array();
			k = 0;
			
		}
		//四个元素分别是id，labelname，parentsid，children的路径，自身的绝对路径.
		arraySelect[tmpdepth - 1][k] = new Array(objArray[i][0],objArray[i][1],objArray[i][2],"",(tmpdepth - 1) + ":" +k);
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
		tmpDiv.appendTo($("#optionDiv"));
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
}
$(document).ready(function(){
	$("#frame0").on("click", ".add-option", addOption);
	$("#frame0").on("click", ".commitChange", commitBt);
	$("#frame0").on("click", ".del-option", delLables);
	$("#frame0").on("click",".hide-and-show-option",function(){
		if($(this).text() === "-")
		{			
			$(this).text("+");
			$(this).parent().children("div").hide();
		} else if($(this).text() === "+"){			
			$(this).text("-");
			$(this).parent().children("div").show();
		}
	});
	$("#optionDiv").on("change","select",function(e){
		//console.log($(this)[0].tagName);
		//应该先把后面的select的option的都去掉。
		$(e.target).parent().nextAll().find("option").remove();
		//change的情况下，我要改变下一个
		//先获取改变后的option的class值。
//		var tmpstr = $(this).children("option:selected").prop("class");
//		var str2 = tmpstr.split("+");
//		console.log(str2);
//		for(var i =1;i < str2.length;i++){
//			var tmpstr3 = str2[i];
//			var tmpstr2 = tmpstr3.split(":");
//			console.log(arraySelect[tmpstr2[0]][tmpstr2[1]][1]);
//		}
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
});
function addOption(t,id,labelname,parentid,depth) {
	//对于展示函数，我调用这个传入的是父id，对于button，我传入的是$(this),可以根据t[0].tagName来判定。
	//如果是button我需要添加东西。id要通过mysql传入然后传回。好更改id。
	var tmpdiv = $("<div></div>");
	var tmpinput =$("<input />");
	var tmpAddbutton = $("<button>AddOption</button>");
	var tmpCommitbutton = $("<button>CommitChange</button>");
	var tmpDelbutton = $("<button>DelOption</button>");
	var tmpHidebutton = $("<button>-</button>");
	tmpinput.appendTo(tmpdiv);
	tmpAddbutton.addClass("add-option");
	tmpAddbutton.appendTo(tmpdiv);
	tmpCommitbutton.addClass("commitChange");
	tmpCommitbutton.appendTo(tmpdiv);
	tmpDelbutton.addClass("del-option");
	tmpDelbutton.appendTo(tmpdiv);
	tmpHidebutton.addClass("hide-and-show-option");
	tmpHidebutton.appendTo(tmpdiv);
	if(t === "DIV"){
		//在这种情况下，要给div设置id名，根据传入的t，还要设置class名，与深度有关。inpu的值也要设定
		//父id有用，这样就必须设定五个参数，第一个用来设定标志。
		tmpdiv.attr("id","frame"+id);
		tmpdiv.addClass("depth" + depth);
		tmpinput.val(labelname);
		tmpdiv.appendTo($("#frame"+parentid));
		
	} else  {
		//只是添加ui而已
		tmpdiv.appendTo($(this).parent());
		
	}
}
function commitBt(){
	var tmpForm = $("<form></form>");
	tmpForm.attr("enctype","multipart/form-data");
	tmpForm.appendTo("body");
	var formdata = new FormData($("form"));
	//按下commit的时候，我要根据id属性是否存在，如果有id属性，则是要更新一下.
	//这个在后台已经有对应了。如果传回的id是0，就是新添加，如果是id大于0，则是update，我只需要
	//判断下id属性是否存在，存在，就传回否则就传0，回去。
	var flag = "tmp";
	if($(this).parent().attr("id"))
	{
		//存在id,用update。
		var str4 = $(this).parent().attr("id");
		formdata.append("id",str4.slice(5,str4.length));
		formdata.append("labelname",$(this).parent().children("input").val());
		flag = "update";
	} else {
		//不存在id，我要传输labelname，parentsid，depth，如果返回值为真，根据返回值设置id，
		//parentsid,depth;
		formdata.append("id","0");
		formdata.append("labelname",$(this).parent().children("input").val());
		var str3 = $(this).parent().parent().attr("id");
		formdata.append("parentsid",str3.slice(5,str3.length));
		var str = $(this).parent().parent().attr("class");
		str = str.slice(5,str.length);
		var str2 = Number.parseInt(str) + 1;
		formdata.append("depth",str2);
		//根据depth设置新div的class。
		$(this).parent().addClass("depth" + str2);
		flag = "insert";
	}
	var objDiv = $(this).parent();
	$.ajax({
        url:"../../apcompany/data/labelRelUpdateOrInsert",
        type:"post",
        data:formdata,
        processData:false,
        contentType:false,
        success:function(data){
            console.log("over.." + data);
            if(flag === "insert")
            {
            	//我需要根据传回的数值，设置id。
            	objDiv.attr("id","frame" + data);
            	$.growl.notice({title: "插入标签", message: "插入标签成功!" });
            }
        }
	});
	$("form").remove();
}

function delLables(){
	var tmpForm = $("<form></form>");
	tmpForm.attr("enctype","multipart/form-data");
	tmpForm.appendTo("body");
	var formdata = new FormData($("form"));
	var str = $(this).parent().attr("id");
	formdata.append("id",str.slice(5,str.length));
	$.ajax({
        url:"../../apcompany/data/delLabelRel",
        type:"post",
        data:formdata,
        processData:false,
        contentType:false,
        success:function(data){
            console.log("over.." + data.result);
        }
	});
		
	$("form").remove();
	$(this).parent().remove();
}
function showAllLabels(){
}
function showLabelTree(){
	$(".depth0").children("div").remove();
	$("#optionDiv").children("div").remove();
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
		for(var j=0;j < array.length;j++){
			addOption("DIV", array[j][0], array[j][1], array[j][2], array[j][3]);
		}
		showSelect(array);
	});
}

