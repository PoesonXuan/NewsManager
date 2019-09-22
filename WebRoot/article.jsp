<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'article.jsp' starting page</title>

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
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/article.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
</head>

<body>
	<img id="bgs" alt="bg"
		src="${pageContext.request.contextPath }/css/img/bg.jpg">
	<div id="main">
		<br />
		<br />
		<br /> <a href="${pageContext.request.contextPath }/main.jsp"
			style="color: white;" rel="home"><SPAN class="u_home"></SPAN></a> <br />
		<br />
		<br />
		<br />
		<h1 />
		${article.title }
		<h1>
		<h1/>${article.date }<h1>
		<hr>
		<div id="articleContent">${article.content }</div>
		<br/>
		<span style="color: white;font-family: serif;font: normal;font-size: 20px;">评论区</span>
		<br/><hr/><br/>
		<div id="commentLists">
		<div class="commentList">
			评论为空！
		</div>
		<br/>
		</div>
		
		<div id="comment">
			<textarea id="content" style="width: 100%;height: 120px;"></textarea>
			<button id="send"  >发表评论</button>
		</div>
		
		<br/><br/><br/><br/>
	</div>
</body>
<script type="text/javascript">
	function getContent(content){
		$.ajax({
  					type:'POST',
  					url:"${pageContext.request.contextPath }/appServlet",
  					dataType:'text',
  					data:{op:'sendContent',content:content,articleId:'${article.id }'},
  					success:function(data){
  					
  					/* alert('data'); */
  					
  					 var d = eval("("+data+")");debugger;
  					 if(d.result == 'success'){
	  					 var datas = d.data;
	  					 var html = '';
	  					 for(var i =0;i<datas.length;i++){
	  					 	var item = datas[i];
	  					 	html += "<div class='commentList'>"
		  					 +"<div class='commentHead'>"+
		  					 item.userName+' '+item.date
		  					 +"</div>"
		  					 +"<div class='commentContent'>"
		  					 +item.content
		  					 +"</div>"
		  					 +"</div><br/>";
	  					 }
	  					 $("#commentLists").html(html);
  					 }else{
  					 	if(d.info === 'login'){
  					 		window.location.href = '${pageContext.request.contextPath }/login.jsp';
  					 	}
  					 	else if(d.info === 'bad'){
  					 		alert('加载评论出错');
  					 	}
  					 }
  					},failure:function(error){
  						alert('error');
  					},error:function(error){
  					
  						alert('error2');
  					}
  				});
	}
  	$(function(){
  		$("#send").on("click",function(){
  			var content = $("#content").val();
  			if(content === undefined || '' === content){
  				alert("评论为空");
  			}else{
  				getContent(content);
  			}
  		});
  		getContent('');
  	});
  
  </script>
</html>
