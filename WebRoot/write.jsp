<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'write.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/write.css">
</head>

<body>
	<form action="${pageContext.request.contextPath}/appServlet?op=writeUpload" method="post">
		<div id="title">
			<input type="text" name="title" placeholder="输入文章标题...">
		</div>
		<div id="select">
			<span style="color: white;">文章类型 :</span>
			<select name="newsType">
				<c:forEach var="type" items="${types}">
					<option>
						<div style="display: none;">${type.name}</div>
						<div>${type.description}</div>
					</option>
				</c:forEach>
			</select>
		</div>
		<!-- <span style="color: white;">文章内容:</span> -->
		<textarea id="TextArea" name="TextArea" cols="20" rows="2" class="ckeditor"></textarea>
		<br/>
		<input id="submit" type="submit" value="上传" />
	</form>
	
			<script type="text/javascript">
				$(function() {
					CKEDITOR.replace('TextArea');
				});
			</script></body>
</html>
