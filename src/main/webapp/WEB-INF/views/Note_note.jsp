<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Note</title>
</head>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style>
.ulder {
	list-style-type: none;
	display: none;
}
</style>
<script>


function extend(id){
	  $("#"+id).toggle();
	$.ajax({
		
		type:'GET',
		url:'/CITIC/getchildren?id='+id,
		contentType : 'application/json',
	    dataType: 'json',
	    success: function (data) {
	    	var html="";
	    	for(i=0;i<data.length;i++)
	    	  html+="<li>"+"<a onclick='extend2("+data[i].id+")' href='javacript:'>+</a>"+data[i].name+
	    	  
	    	  "<ul class='ulder' id='"+data[i].id+"'></ul>"+"</li>";
	    	  
	    	  
	    	document.getElementById(id).innerHTML = html;
	    },
	    error:function() {  
	      alert("");  
	    }  		
				
		
		
		
		
		
		
		});

}

function extend2(id){
	  $("#"+id).toggle();
	$.ajax({
		
		type:'GET',
		url:'/CITIC/getchildrenproject?id='+id,
		contentType : 'application/json',
	    dataType: 'json',
	    success: function (data) {
	    	var html="";
	    	for(i=0;i<data.length;i++)
	    	  
              html+="<li>"+"<a onclick='extend3("+data[i].id+")' href='javacript:'>+</a>"+data[i].projectname+
	    	  
	    	  "<ul class='ulder' id='pre_"+data[i].id+"'></ul>"+"</li>";
	    	document.getElementById(id).innerHTML = html;
	    },
	    error:function() {  
	      alert("");  
	    }  		
				
		
		
		
		
		
		
		});

}
function extend3(id){
	$("#pre_"+id).toggle();
	$.ajax({
		
		type:'GET',
		url:'/CITIC/level3?project='+id,
		contentType : 'application/json',
	    dataType: 'json',
	    success: function (data) {
	    	var html="";
	    	for(i=0;i<data.length;i++)
	    	  html+="<li>"+"<a href='/CITIC/threadpage?projectid="+data[i].projectid+"&threadname="+data[i].threadfocus+"'>"+data[i].threadfocus+"</a></li>";
	    	document.getElementById("pre_"+id).innerHTML = html;
	    },
	    error:function() {  
	      alert("");  
	    }  		
				
		
		
		
		
		
		
		});

}

</script>
<body>
	<%@ include file="header.jsp"%>
	<div style="width: 1200px; height: 20px;"></div>

	<jsp:include page="ME.jsp" />


	<div class="nav2">
		<p>Browse Inquiry Projects Based on Subject Areas</p>
		<ul style="list-style-type: none">
			<c:forEach var="item" items="${contentlist}">
				<li><a onclick="extend('${item.id}')" href="javascript:">+</a>${item.name}
					<ul id="${item.id}" class="ulder"></ul></li>
			</c:forEach>
		</ul>

	</div>


</body>
</html>