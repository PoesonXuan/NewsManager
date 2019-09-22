<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'articleList.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

body {
	width: 100%;
	height: 100%;
}

a {
	text-decoration: none;
}
</style>
</head>

<body>
<br/>
<br/>
<br/>
	<c:forEach var="article" items="${articles}">
		<div style="margin-top: 10px;">
		<%-- ${pageContext.request.contextPath}/appServlet?op=articleCheck&articleid=${article.id} --%>
			<a
				href="${pageContext.request.contextPath}/appServlet?op=articleCheck&articleid=${article.id}" style="color: white;">${article.title}
				${article.date}</a>
			<a href="${pageContext.request.contextPath}/appServlet?op=articleDelete&articleid=${article.id}" style="color: red;">删除文章</a>
		</div>
		<br />
	</c:forEach>
</body>
</html>
