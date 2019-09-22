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

<title>My JSP 'main.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/main.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
</head>

<body>
	<img id="bgs" alt="bg"
		src="${pageContext.request.contextPath }/css/img/bg.jpg">
	<div id="main">
		<div id="top">
			<img alt="广告插件"
				src="${pageContext.request.contextPath }/css/img/top.jpg">
		</div>
		<div id="info">
			<img id="newLogo" alt="news_logo"
				src="${pageContext.request.contextPath }/css/img/news_logo.png">
			<div id="rightLogo">
				<c:choose>
					<c:when test="${not empty currentUser}">
						<a href="${pageContext.request.contextPath}/appServlet?op=myInfo">欢迎
							:${currentUser.userName}</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath }/login.jsp">登录</a>
					</c:otherwise>
				</c:choose>
			</div>

			<c:if test="">
			</c:if>
		</div>
		<div id="index" >
			<ul>
				<li id="NEWS" class="select">新闻</li>
				<li id="TECHNOLOGY" class="select">科技</li>
				<li id="ECONOMICS" class="select">财经</li>
				<li id="POLITY" class="select">政治</li>
				<li id="SPORT" class="select">体育</li>
				<li id="AMUSEMENT" class="select">娱乐</li>
				<li id="BLOG" class="select">博客</li>
				<li id="IMAGE" class="select">图片</li>
				<li id="CAR" class="select">汽车</li>
				<li id="HEALTH" class="select">健康</li>
			</ul>
		</div>
		<div id="news" name="news"></div>
	</div>
</body>
<script type="text/javascript">
	
	var articleClick = function(articleid){
		window.location.href = "${pageContext.request.contextPath }/appServlet?op=articleDetail&articleid="+articleid;
	}
  	  var load = function(param){
  	  	$.ajax({
		          type:"POST",
		          url:"${pageContext.request.contextPath }/appServlet",
		          dataType:"text", 
		          data:"op=articleList&articleType="+param,
		          success:function(data){
			          var d = eval("("+data+")");/* data */;debugger;
			          var arr =  d.data;
			          var html = '';
			          $.each(arr,function(index,item){
			          /* href='${pageContext.request.contextPath}/appServlet?op=login&articleid="+item.id+"' */
                          html += "<div class='articleListItem'><div class='articleListItemA' onclick='articleClick("+item.id+")' >"+item.title+"</div>&nbsp&nbsp<span>"+item.date+"</span></div>"; 
			          });
			          $("#news").html(html);		          
			          
		          },
		          failure:function (err) {
			          var e = err;debugger;
			          alert(12312);
				      alert(err);
				  },
		         error:function(response){
		         var e = response;debugger;
		         	console.log(e);
		         ;}
		         });
  	  }
	  $(function(){
	  		load(1);
	  	     $(".select").click(function () {
                var id = $(this).attr("id");
                var param = 0;
                if(id === undefined || '' === id){
                	param = 0;
                }
                else if('NEWS'=== id){
                	param = 1;
                }
				else if('SPORT'=== id){
                	param = 2;
				}
				else if('ECONOMICS'=== id){
                	param = 3;
				}
				else if('AMUSEMENT'=== id){
                	param = 4;
				}
				else if('TECHNOLOGY'=== id){
                	param = 5;
				}
				else if('BLOG'=== id){
                	param = 6;
				}
				else if('IMAGE'=== id){
                	param = 7;
				}
				else if('CAR'=== id){
                	param = 8;
				}
				else if('POLITY'=== id){
                	param = 9;
				}
				else if('HEALTH'=== id){
                	param = 10;
				}
				load(param);
				
			});
	  });
  </script>
</html>
