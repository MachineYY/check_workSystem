/**
 * 
 */
var inp = document.getElementById("u_id");
var span = document.getElementById("u_pwd");
inp.onfocus = function(){
	console.log("聚焦");
}
inp.onblur = function(){
	console.log(inp.value);
	var temp = inp.value;
	if(temp==""){
		alert("用户名不可为空！")
	}else{
		
	}
	console.log("失焦");
}
