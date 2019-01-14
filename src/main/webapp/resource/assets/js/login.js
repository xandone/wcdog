layui.config({
	base : "js/"
}).use(['form','layer'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		$ = layui.jquery;
	
	
	form.verify({
		username: function(value, item){ //value：表单的值、item：表单的DOM对象
		   if(value!=='admin'){
			   return '用户名错误';
		   }
		  }
		  
		  //我们既支持上述函数式的方式，也支持下述数组的形式
		  //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
		  ,password: function(value,item){
			  console.log(value);
			  if(value!=='123456'){
				  return '密码错误';
			  }
		  }
		});
	
	//登录按钮事件
	form.on("submit(login)",function(data){
		window.location.href = "home.html";
		return false;
	})
})