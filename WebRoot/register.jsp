<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统注册页面</title>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<STYLE>
body{
	background: #ebebeb;
	font-family: "Helvetica Neue","Hiragino Sans GB","Microsoft YaHei","\9ED1\4F53",Arial,sans-serif;
	color: #222;
	font-size: 12px;
}
*{padding: 0px;margin: 0px;}
.top_div{
	background: #008ead;
	width: 100%;
	height: 400px;
}
.ipt{
	border: 1px solid #d3d3d3;
	padding: 10px 10px;
	width: 290px;
	border-radius: 4px;
	padding-left: 35px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
	box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
	-webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
	transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s
}
.ipt:focus{
	border-color: #66afe9;
	outline: 0;
	-webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6);
	box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6)
}
 
.login_logo{
background: url("${pageContext.request.contextPath}/css/img/account.png") no-repeat;
	background-size:100% 100%;-moz-background-size:100% 100%;
	padding: 10px 10px;
	position: absolute;
	top: 43px;
	left: 40px;
}
.phone_logo{
background: url("${pageContext.request.contextPath}/css/img/phone.png") no-repeat;
	background-size:100% 100%;-moz-background-size:100% 100%;
	padding: 10px 10px;
	position: absolute;
	top: 43px;
	left: 40px;
}
.post_logo{
background: url("${pageContext.request.contextPath}/css/img/mail.png") no-repeat;
	background-size:100% 100%;-moz-background-size:100% 100%;
	padding: 10px 10px;
	position: absolute;
	top: 43px;
	left: 40px;
}

.u_logo{
	background: url("${pageContext.request.contextPath}/css/img/name.png") no-repeat;
	background-size:100% 100%;-moz-background-size:100% 100%;
	padding: 10px 10px;
	position: absolute;
	top: 43px;
	left: 40px;
}
.p_logo{
	background: url("${pageContext.request.contextPath}/css/img/pwd.png") no-repeat;
	background-size:100% 100%;-moz-background-size:100% 100%;
	padding: 10px 10px;
	position: absolute;
	top: 12px;
	left: 40px;
}
a{
	text-decoration: none;
}
.tou{
	background: url("${pageContext.request.contextPath}/static/images/tou.png") no-repeat;
	width: 97px;
	height: 92px;
	position: absolute;
	top: -87px;
	left: 140px;
}
.left_hand{
	background: url("${pageContext.request.contextPath}/static/images/left_hand.png") no-repeat;
	width: 32px;
	height: 37px;
	position: absolute;
	top: -38px;
	left: 150px;
}
.right_hand{
	background: url("${pageContext.request.contextPath}/static/images/right_hand.png") no-repeat;
	width: 32px;
	height: 37px;
	position: absolute;
	top: -38px;
	right: -64px;
}
.initial_left_hand{
	background: url("${pageContext.request.contextPath}/static/images/hand.png") no-repeat;
	width: 30px;
	height: 20px;
	position: absolute;
	top: -12px;
	left: 100px;
}
.initial_right_hand{
	background: url("${pageContext.request.contextPath}/static/images/hand.png") no-repeat;
	width: 30px;
	height: 20px;
	position: absolute;
	top: -12px;
	right: -112px;
}
.left_handing{
	background: url("${pageContext.request.contextPath}/static/images/left-handing.png") no-repeat;
	width: 30px;
	height: 20px;
	position: absolute;
	top: -24px;
	left: 139px;
}
.right_handinging{
	background: url("${pageContext.request.contextPath}/static/images/right_handing.png") no-repeat;
	width: 30px;
	height: 20px;
	position: absolute;
	top: -21px;
	left: 210px;
}

</STYLE>
<script type="text/javascript">
$(function(){
	//得到焦点
	$(".pwd").focus(function(){
		$("#left_hand").animate({
			left: "150",
			top: " -38"
		},{step: function(){
			if(parseInt($("#left_hand").css("left"))>140){
				$("#left_hand").attr("class","left_hand");
			}
		}}, 2000);
		$("#right_hand").animate({
			right: "-64",
			top: "-38px"
		},{step: function(){
			if(parseInt($("#right_hand").css("right"))> -70){
				$("#right_hand").attr("class","right_hand");
			}
		}}, 2000);
	});
	//失去焦点
	$(".pwd").blur(function(){
		$("#left_hand").attr("class","initial_left_hand");
		$("#left_hand").attr("style","left:100px;top:-12px;");
		$("#right_hand").attr("class","initial_right_hand");
		$("#right_hand").attr("style","right:-112px;top:-12px");
	});
});

function checkForm(){

	var myRegPost=/^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;    
	var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
    var isMob=/^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
	var userName=$("#userName").val();
	var password=$("#password").val();
	var loginName=$("#loginName").val();
	var rePassword=$("#repassword").val();
	var phone=$("#phone").val();
	var post=$("#post").val();
	if(userName==null||userName==""){
		$("#error").html("用户名不能为空！");
		return false;
	}
	if(loginName==null||loginName==""){
		$("#error").html("登录账号不能为空！");
		return false;
	}
	if(password==null||password==""){
		$("#error").html("密码不能为空！");
		return false;
	}
	if(rePassword==null||rePassword==""){
		$("#error").html("确认密码不能为空！");
		return false;
	}
	if(password !== rePassword){
	    $("#error").html("两次密码输入不一致！");
		return false;
	}
	if(password.length < 6){
		$("#error").html("密码长度少于6位！");
		return false;
	}
	if(phone==null||phone==""){
		$("#error").html("电话号码不能为空！");
		return false;
	}
	if(!((isMob.test(phone)||isPhone.test(phone)))){
		$("#error").html("电话号码不对!");
		return false;
	}
	if(post==null||post==""){
		$("#error").html("邮箱不能为空！");
		return false;
	}
	if(!myRegPost.test(post)){
		$("#error").html("邮箱格式不对!");
		return false;
	}
	return true;
}
</script>
</head>
<body>
<DIV class="top_div">
</DIV>
<form action="${pageContext.request.contextPath}/appServlet?op=register" method="post" onsubmit="return checkForm()">
	<DIV style="background: rgb(255, 255, 255); margin: -340px auto auto; border: 1px solid rgb(231, 231, 231); border-image: none; width: 400px; height: 400px; text-align: center;">
		<DIV style="width: 165px; height: 96px; position: absolute;">
			<DIV class="tou">
			</DIV>
			<DIV class="initial_left_hand" id="left_hand">
			</DIV>
			<DIV class="initial_right_hand" id="right_hand">
			</DIV>
		</DIV>
		<P style="padding: 30px 0px 10px; position: relative;top: 5px;">
			<SPAN class="u_logo"></SPAN>
			<INPUT id="userName" name="userName" class="ipt" type="text" placeholder="请输入用户名" value="${blogger.userName }"> 
	    </P>
		<P style="padding: 30px 0px 10px; position: relative;">
			<SPAN class="login_logo"></SPAN>
			<INPUT id="loginName" name="loginName" class="ipt" type="text" placeholder="请输入登录账号" value="${blogger.userName }"> 
	    </P>
		<P style="position: relative;">
			<SPAN class="p_logo"></SPAN>         
			<INPUT id="password" name="password" class="ipt pwd"  type="password" placeholder="请输入密码" value="${blogger.password }">   
	  	</P>
		<P style="position: relative;">
			<SPAN class="p_logo"></SPAN>         
			<INPUT id="repassword" name="repassword" class="ipt pwd"  type="password" placeholder="请确认密码" value="${blogger.password }">   
	  	</P>
	  	
		<P style="padding: 30px 0px 10px; position: relative;top: 0px;">
			<SPAN class="phone_logo"></SPAN>
			<INPUT id="phone" name="phone" class="ipt" type="text" placeholder="请输入联系电话" value="${blogger.userName }"> 
	    </P>
		<P style="padding: 30px 0px 10px; position: relative;top: 0px;">
			<SPAN class="post_logo"></SPAN>
			<INPUT id="post" name="post" class="ipt" type="text" placeholder="请邮箱信息" value="${blogger.userName }"> 
	    </P>
		<DIV style="height: 50px; line-height: 50px; margin-top: 30px; border-top-color: rgb(231, 231, 231); border-top-width: 1px; border-top-style: solid;">
			<P style="margin: 0px 35px 20px 45px;">
			<span><font color="red" id="error">${errorInfo }</font></span>
	        <SPAN style="float: right;"> 
	              <input type="submit" style="background: rgb(0, 142, 173); padding: 7px 10px; border-radius: 4px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold;" value="注册"/> 
	         </SPAN>         
	         </P>
	    </DIV>
	</DIV>
</form>
<br/>
<!-- 
<div style="text-align:center;padding-top: 30px">
Copyright © 2012-2019 
</div> -->
</body>
</html>