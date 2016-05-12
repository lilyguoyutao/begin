<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Thread page</title>
<script src="resources/js/artDialog4.1.7/artDialog.js?skin=twitter"
	type="text/javascript"></script>
<script src="resources/js/artDialog4.1.7/plugins/iframeTools.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
$(document).ready(function(){
	  
	  $.ajax({
			  
	  type:'GET',
	  url:'/CITIC/whetherlike?projectid=${projectid}&threadname=${threadname}',
	  contentType:'application/json',
	  dataType:'json',
	  success:function(data){
		  var html="";
		  if(data[0]==0)
			  {html="<a onclick='likeme()' href='javascript:'>like</a>("+data[1]+")"}
		  else
			  {html="<a onclick='dislikeme()' href='javascript:'>dislike</a>("+data[1]+")"}
		  
		  document.getElementById("like").innerHTML = html;
		  
	  },
	  
	  error:function(){
		  alert("error");
	  },
	  
	  
	  });
	  
	  $.ajax({
		  
		  type:'GET',
		  url:'/CITIC/checkfollow?projectid=${projectid}&threadname=${threadname}',
		  contentType:'application/json',
		  dataType:'json',
		  success:function(data){
			  var html="";
			  if(data==true)
				  {html="<a onclick='followme()' href='javascript:'>follow</a>"}
			  else
				  {html="follow"}
			  
			  document.getElementById("follow").innerHTML = html;
			  
		  },
		  
		  error:function(){
			  alert("error");
		  },
		  
		  
		  });
	  
	  
	  
	  
	  
});

function followme(){
	 $.ajax({
		  
		  type:'GET',
		  url:'/CITIC/follow?projectid=${projectid}&threadname=${threadname}',
		  contentType:'application/json',
		  dataType:'json',
		  success:function(data){
			  var html="";
			   
				   html="follow";
			  
			  document.getElementById("follow").innerHTML = html;
			  
		  },
		  
		  error:function(){
			  alert("error");
		  },
		  
		  
		  });
	
}

function likeme(){
  $.ajax({
		  
		  type:'GET',
		  url:'/CITIC/likeme?projectid=${projectid}&threadname=${threadname}',
		  contentType:'application/json',
		  dataType:'json',
		  success:function(data){
			  var html="";
			 
				  html="<a onclick='dislikeme()' href='javascript:'>dislike</a>("+data[1]+")";
				  
			  
			  document.getElementById("like").innerHTML = html;
			  
		  },
		  
		  error:function(){
			  alert("error");
		  },
		  
		  
		  });
	
}
function dislikeme(){
	  $.ajax({
			  
			  type:'GET',
			  url:'/CITIC/dislikeme?projectid=${projectid}&threadname=${threadname}',
			  contentType:'application/json',
			  dataType:'json',
			  success:function(data){
				  var html="";
				  
					  html="<a onclick='likeme()' href='javascript:'>like</a>("+data[1]+")";
					  
				  
				  document.getElementById("like").innerHTML = html;
				  
			  },
			  
			  error:function(){
				  alert("error");
			  },
			  
			  
			  });
		
	}
function openPage(){ 
	   
	art.dialog.open('/CITIC/threadcomment?projectid=${projectid}&threadname=${threadname}', {title: 'Comment'});
	//要传递数据到里一个页面可以通过data来实现 
	
	//打开窗口 
	/*art.dialog.open({
		title:'dialog',
	//	content:'<form action="Footer.jsp" method="post">'+
		'<input type="text" name="cc"/>'+'<input type="submit" value="submit"/>'+'</form>'
	});*/
}
function likewin(){ 
	   
	art.dialog.open('/CITIC/likelist?projectid=${projectid}&threadname=${threadname}', {title: 'likelist'});
	
}

function adopt(){ 
	   
	art.dialog.open('/CITIC/adopt?projectid=${projectid}&threadname=${threadname}', {title: 'Adopt'});
	
}

function collect(){ 
	   
	art.dialog.open('/CITIC/showcollection?projectid=${projectid}&threadname=${threadname}', {title: 'Collect'});
	
}




</script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div style="width: 1200px; height: 20px; clear: left;"></div>


	<jsp:include page="ME.jsp" />


	<div class="nav2">
		<div style="margin-left: 0px; font-size: 20px;">Idea Thread:
			${threadname}</div>
		<p>
			from project <a href="/CITIC/getproject-thread?projectid=${projectid}">${projectname}</a> of community<a href="/CITIC/detail?comm_name=${communityname}">
				${communityname}</a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <a
				href="http://tccl.rit.albany.edu:8080/ITM2/en/threadSum2.jsp?database=${communityname}&threadfocus=${threadname}&projectname=${projectname}%20body%20system
"
				target="_blank">Journey of Thinking</a>
		</p>
		<div style="background-color: #FFFFFF;overflow-y:hidden;">

			<!-- img src="resources/notepic.png" style="width:400px; height:300px;margin-left:200px;border:1px black;"></img> -->
			<iframe
				src="/CITIC/getthreadpageds?projectname=${projectname}&threadname=${threadname}"
				style="width: 1100px; height: 450px; border: 3px black; background: white"></iframe>
			<table style="margin-left: 260px">

				<tr>

					<td id="like"></td>
					<td>&nbsp</td>
					<td id="follow"></td>
					<td>&nbsp</td>
					<td><a href="javascript:" onclick="openPage()">Comment</a></td>
					<td>&nbsp</td>
					<td><a onclick="collect()" href="javascript:">Collect</a></td>
					<td>&nbsp</td>
					<td><a onclick="adopt()" href="javascript:">Adopt</a></td>
					<td>&nbsp</td>
					<td>&nbsp</td>
					<td>&nbsp</td>
					<td>&nbsp</td>
					<td>&nbsp</td>




				</tr>
			</table>
			<iframe
				src="/CITIC/similarfindbar?projectid=${projectid}&threadname=${threadname}"
				width=1100px height=180px
				style="border: 1px black; background: #FFFFFF"> </iframe>
		</div>
		<div style="width: 1200px; height: 10px;"></div>


		<!--iframe src="/CITIC/listidea?projectid=${projectid}&threadname=${threadname}"  width=900px height=800px style="border:5px dotted line;padding-left:5px; background:#ccc">

</iframe-->
		<iframe
			src="/CITIC/test?projectid=${projectid}&threadname=${threadname}"
			width=900px height=800px
			style="border: 5px dotted line; padding-left: 5px; background: #ccc">

		</iframe>

	</div>

</body>
</html>