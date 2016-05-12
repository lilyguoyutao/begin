<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
</head>
<style>
.content {
	width: 600px;
	background-color: #C0C0C0;
	margin-top: 10px;
}

.content1 {
	width: 560px;
	background-color: #C0C0C0;
	margin-top: 10px;
}

.content2 {
	width: 520px;
	background-color: #C0C0C0;
	margin-top: 10px;
}

.content3 {
	width: 480px;
	background-color: #C0C0C0;
	margin-top: 10px;
}
</style>
<script>
function openmy(){ 
	   
	art.dialog.open('/CITIC/threadcomment?projectid=${projectid}&threadname=${threadname}', {title: 'Comment'});
	//要传递数据到里一个页面可以通过data来实现 
	
	//打开窗口 
	/*art.dialog.open({
		title:'dialog',
	//	content:'<form action="Footer.jsp" method="post">'+
		'<input type="text" name="cc"/>'+'<input type="submit" value="submit"/>'+'</form>'
	});*/
}
$(document).ready(function () {
    
    $.ajax({
        type: 'Get',
        url: '/CITIC/getfirstlevel?projectid=${projectid}&threadname=${threadname}',
        contentType : 'application/json',
        dataType: 'json',
        success: function (data) {
        	var html = "";
        	for(i = 0; i< data.length; i++){
        		
        		
        		extract(data[i]);
        		
        	}
        	
        	
        	
        },
        error:function() {  
          alert("");  
        }  

    });
    

});
function extract(predata){
	
    $.ajax({
    		type:'GET',
    		url:'/CITIC/getsecondlevel?projectid=${projectid}&threadname=${threadname}&id='+predata.noteid,
    		contentType : 'application/json',
            dataType: 'json',
            success: function (data) {
            	
            	
            	var html ="<li><div>"+predata.notecontent;
            	html+="</div><ul>"
            	for(i = 0; i< data.length; i++){
            	   html+="<li><div>";
            	   html+=data[i].notecontent;
            	   html+="</div></li>";
            	   
            		
            		
            	}
            	html+="</ul></li>";
            	$("#first").append(html);
            	
            },
            error:function() {  
              alert("");  
            }  		
    		
    	});
       
    	
    	 }
function extract1(level,realdata){
	
    $.ajax({
    		type:'GET',
    		url:'/CITIC/getsecondlevel?projectid=${projectid}&threadname=${threadname}&id='+realdata,
    		contentType : 'application/json',
            dataType: 'json',
            success: function (data) {
            	
            	var html="";
            	var html = "";
            	for(i = 0; i< data.length; i++){
            		
            		html += "<li>";
            		html +="<div class='content2'><p><b>"+data[i].notetitle+"</b>&nbsp&nbsp&nbspcreated at "+data[i].createtime+"</p>";
            		html += "<a"+" onclick='extract2("+level+","+realdata+","+data[i].noteid+")'" + "href='javascript:'>"+data[i].notecontent+"</a>";
            		html +="<a onclick='openmy()' href='javascript:'>comment</a></div>";
            		html +="<ul id='"+level+"_"+realdata+"_"+data[i].noteid+"'></ul>";
            		html += "</li>";
            		
            	}
            	document.getElementById(level+"_"+realdata).innerHTML = html;
            },
            error:function() {  
              alert("");  
            }  		
    		
    	});
    	
    	 }   	 
function extract2(level,realdata,datai){
	
    $.ajax({
    		type:'GET',
    		url:'/CITIC/getsecondlevel?projectid=${projectid}&threadname=${threadname}&id='+datai,
    		contentType : 'application/json',
            dataType: 'json',
            success: function (data) {
            	
            	var html="";
            	var html = "";
            	for(i = 0; i< data.length; i++){
            		
            		html += "<li>";
            		html +="<div class='content3'><p><b>"+data[i].notetitle+"</b>&nbsp&nbsp&nbspcreated at "+data[i].createtime+"</p>";
            		html += "<a" + " href='javascript:'>"+data[i].notecontent+"</a>";
            		html +="<a onclick='openmy()' href='javascript:'>comment</a></div>";
            		// html +="<ul id='"+level+"_"+realdata+"_"+data[i].noteid+"'></ul>";
            		html += "</li>";
            		
            	}
            	document.getElementById(level+"_"+realdata+"_"+datai).innerHTML = html;
            },
            error:function() {  
              alert("");  
            }  		
    		
    	});
    	
    	 }   	 
</script>
<body>
	<ul id="first">

	</ul>
</body>
</html>