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
    
    <title>My JSP 'MyInfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/myInfo.css">
  </head>
  
  <body>
    <div id="main">
    	<div id="left">
    		<div id="head">
    		<img id="head" alt="head" src="${pageContext.request.contextPath }/css/img/head.jpg">
    		</div>
    		<div id="info">
    			<span>用户名:${currentUser.userName}-;</span>
    			<a href="${pageContext.request.contextPath}/appServlet?op=write" target="content">
    				<SPAN style="color: white;">写新闻</SPAN><SPAN class="write"></SPAN>
    			</a>
    		</div>
    		<div id="contentList">
    			<table style="color: white;">
    			<tbody>
    			
    			<c:forEach var="article" items="${list}">
    				<tr onclick="func('${article.id}')">
    				<td colspan="3">${article.title}</td>
    				</tr><tr>
    				<td colspan="2" style="width: 20%;">${article.checkedResult}</td>
    				<td>${article.checkInfo}</td>
    				</tr><tr>
    				<!-- <td></td><td></td><td></td> -->
    				<td colspan="3" style="border-bottom:#cccccc solid 1px;">${article.date}</td>
    				</tr>
    			</c:forEach>
    			</tbody>
    			</table>
    		</div>
    	</div>
    	<div id="right">
    		<iframe id="content" name="content" src="message.jsp" style="border: 0;overflow-x: hidden; overflow-y: auto; "></iframe>
    	</div>
    </div>
    <script type="text/javascript">
    	function func(articleId){
    		window.parent.content.location.href= "${pageContext.request.contextPath}/appServlet?op=articleInfo&articleId="+articleId; 
    	};
    </script>
  </body>
</html>
