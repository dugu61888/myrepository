function addkey(){
	var formdata = new FormData($("#tab"));
	var keyword = $("#word").val();
	formdata.append("id","0");
	formdata.append("name",keyword);
	formdata.append("fname",$("#fword").val());
	$.ajax({
        url:"../../apcompany/translate/insertOrUpdateKeyWords",
        type:"post",
        data:formdata,
        processData:false,
        contentType:false,
        success:function(data){
            console.log("over..");
            $.growl.notice({title: "添加关键字", message: "添加成功!" });
            window.location.reload();
        }
	});
}

function showkey(){
	$.get("../../apcompany/translate/selectAllKey",function(data){
		var jsonObj = JSON.parse(data);
		for(var jn in jsonObj){
			//jsonObj[jn].id
			var tmpDiv = $("<div></div>",{class:"divch"});
			tmpDiv.prop("id",jsonObj[jn].id);
			var wordSpan = $("<span><span>");
			wordSpan.html(jsonObj[jn].name + "<--->");
			wordSpan.appendTo(tmpDiv);
			var fwordSpan = $("<span><span>");
			fwordSpan.html(jsonObj[jn].fname);
			fwordSpan.appendTo(tmpDiv);
			tmpDiv.appendTo($("#keysw"));
		}
	});
	//我需要一个展示所有keyword的接口。
}
$(document).ready(function(){
	$("#keysw").on("click",".divch",function(e){
		return false;		
	});
	$("#keysw").on("click",".comKey",function(e){
		var formdata = new FormData($("#tab"));
		formdata.append("id",$(e.target).parent().prop("id"));
		formdata.append("name",$(e.target).parent().children("input:first").val());
		formdata.append("fname",$(e.target).parent().children("input:last").val());
		$.ajax({
	        url:"../../apcompany/translate/insertOrUpdateKeyWords",
	        type:"post",
	        data:formdata,
	        processData:false,
	        contentType:false,
	        success:function(data){
	            console.log("over..");
	            $.growl.notice({title: "修改关键字", message: "修改成功!" });
	            window.location.reload();
	        }
		});
	});
	$("#keysw").on("click",".divch span",function(e){
		//console.log($(e.target).text());
		var name = $("<input />");
		name.prop("placeholder","输入中文");
		var tmpstr = $(e.target).parent().children("span:first").text();
		name.val(tmpstr.slice(0,tmpstr.indexOf("<")));
		var fname = $("<input />");
		fname.prop("placeholder","输入英文");
		fname.val($(e.target).parent().children("span:last").text());
		var bt = $("<button>commit</button>");
		bt.addClass("comKey");
		name.appendTo($(e.target).parent());
		fname.appendTo($(e.target).parent());
		bt.appendTo($(e.target).parent());
		$(e.target).parent().children("span").each(function(){
			$(this).remove();
		});
	});
	
});