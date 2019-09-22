<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>message page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <br/><br/><br/><br/>
  
  <c:choose>
    			<c:when test="${not empty message}">
    				<span style="color: black;font: bold;">${message} <br></span>
    			</c:when>
    			<c:otherwise>
    				<span style="color: black;font: bold;">欢迎使用本系统<br></span>
    			</c:otherwise>
    		</c:choose>
  
  <%-- <span style="color: white;font: bold;">${message} <br></span> --%>
    
  </body>
</html>
