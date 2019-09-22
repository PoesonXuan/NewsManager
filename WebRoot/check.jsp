<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'check.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
body {
	color: white;
}

.selectBtn {
	width: 15%;
	height: 40px;
	margin-top: 20px;
	border: 10x solid #7e9db9;
	border-radius: 10px;
	font-weight: bold;
	color: #904;
	font-size: 16px;
	-webkit-appearance: button;
	-moz-user-select: none;
}
</style>

</head>

<body>
	<div id="btn">
		<input id="pass" class="selectBtn" name="pass" type="button"
			onclick="btnClick(1)" value="审核通过" /> <input id="nopass"
			class="selectBtn" name="nopass" type="button" onclick="btnClick(2)"
			value="审核不通过" /> <input id="return" class="selectBtn" name="return"
			type="button" onclick="btnClick(3)" value="返回上页面" />
	</div>
	<h1>${article.title}</h1>
	<h6>${article.date}</h6>
	<br /> ${article.content }
</body>
<script type="text/javascript">
  	function btnClick(sign){
		if('1' === sign || 1 === sign)
		window.location.href= "${pageContext.request.contextPath}/appServlet?op=articleCheck&articleid=${article.id}&result=1";
		else if('2' === sign || 2 === sign){
	
		//第一个参数是提示文字，第二个参数是文本框中默认的内容
        var checkInfo = prompt("请输入审查信息文字","");
        if(checkInfo){
             //输出word的格式
             	window.location.href= "${pageContext.request.contextPath}/appServlet?op=articleCheck&articleid=${article.id}&result=99&checkInfo="+checkInfo;
             /* window.location.href = '${pageContext.request.contextPath}/appServlet?op=articleDelete&articleid='+articleId; */
         }
     
		}
	
		else if('3' === sign || 3 === sign)
		window.location.href= "${pageContext.request.contextPath}/appServlet?op=articleCheck";
	}
  </script>
</html>
