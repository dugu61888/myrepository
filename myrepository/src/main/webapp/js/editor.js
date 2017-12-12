$(document).ready(function() {
	//				$("#inp").mouseover(function() {
	//					$(this).css("border-color", "#0066CC");
	//					console.log("in");
	//				});
	//				//				$("button").mouseover(function(){
	//				//					$(this).css("background-color","#C8E0F0")
	//				//				});
	//				$("button").on("mouseover", function() {
	//					$(this).css("background-color", "#C8E0F0");
	//				});
	//				$(".quesT,.anT,.opT").on("mouseover", function() {
	//					$(this).css("background-color", "#C8E0F0");
	//				});
	//focus事件展开键盘.
	$("#editor table button").on("mouseover", function() {
						$(this).css("background-color", "#C8E0F0");
	});
	$("#editor table button").on("mouseout", function() {
		$(this).css("background-color", "buttonface");
	});
});

function editorAppValue(t) {
	var tmp = $("#editor").parent().children("textarea").val();
	tmp += (t.value);
	$("#editor").parent().children("textarea").val(tmp);
}

function editorOverBt() {
	var txt = $("#editor").prev().prev().prev().val();
	var oldtxt = $("#editor").prev().children("span").text();	
	var newtxt = oldtxt + " " + txt;		
	$("#editor").prev().text(newtxt);
	$("#editor").prev().prev().prev().val("");
	MathJax.Hub.Queue(["Typeset", MathJax.Hub]);
}


function editorOpen(t) {
	$("#editor").appendTo(t.parent());
	$("#editor").show();
}
function editorClose(t){
	$("#editor").hide();
}
