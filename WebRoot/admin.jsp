<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'amdin.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/admin.css">

</head>

<body>
	<div id="main">
		<div id="left">
			<div id="head">
				<img id="headimg" alt="head"
					src="${pageContext.request.contextPath }/css/img/admin.png">
			</div>
			<br />
			<div id="info">
				<span>当前用户:${currentUser.userName}</span>
			</div>

			<div id="btn">
				<input id="Permission" class="selectBtn"  onclick="btnClick(1)" name="Permission"
					type="button" value="用户权限管理" style="display: none;" /> 
				<input id="check" class="selectBtn" onclick="btnClick(2)"
					name="check" type="button" value="审核文章管理" /> 
				<input id="check" class="selectBtn" onclick="btnClick(3)"
					name="check" type="button" value="已审核文章列表" />
			</div>

		</div>
		<div id="right">
			<div id="frame">
				<iframe id="iframe" name="iframe" frameborder="0"
					src="message.jsp" scrolling="auto"></iframe>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function btnClick(sign){
		if('1' === sign || 1 === sign)
			window.parent.iframe.location.href= "${pageContext.request.contextPath}/appServlet?op=userPermission";
		else if('2' === sign || 2 === sign)
			window.parent.iframe.location.href= "${pageContext.request.contextPath}/appServlet?op=articleCheck";
		else if('3' === sign || 3 === sign)
			window.parent.iframe.location.href= "${pageContext.request.contextPath}/appServlet?op=articleChecked";
	}
</script>
</html>
