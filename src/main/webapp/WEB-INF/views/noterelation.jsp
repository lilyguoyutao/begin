<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Note</title>
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
	width: 800px;
	background-color: #C0C0C0;
	margin-top: 10px;
}

.content1 {
	width: 760px;
	background-color: #C0C0C0;
	margin-top: 10px;
}

.content2 {
	width: 720px;
	background-color: #C0C0C0;
	margin-top: 10px;
}

.content3 {
	width: 680px;
	background-color: #C0C0C0;
	margin-top: 10px;
}
</style>
<script>
function openmy(noteid){ 
	   
	//art.dialog.open('/CITIC/projectid=${projectid}&threadname=${threadname}', {title: 'Comment'});
	//要传递数据到里一个页面可以通过data来实现 
	art.dialog.open('/CITIC/notecomment?projectid=${projectid}&threadname=${threadname}&noteid='+noteid, {title: 'Comment'});
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
        		
        		html += "<li class='first'>";
        		html +="<div class='content'><p id='p_0_"+data[i].noteid+"'><b>"+data[i].notetitle+"</b>&nbsp&nbsp&nbspcreated at "+data[i].createtime;
        		html +=" by " +getName(data[i].noteid,0)+"</p>";
        		html += data[i].notecontent;
        		html +="<a onclick='openmy("+data[i].noteid+")' href='javascript:'>comment</a></div>";
        		html +="<ul id='"+i+"'></ul>"
        		html += "</li>";
        		extract(i,data[i].noteid);
        		
        	}
        	document.getElementById("first").innerHTML = html;
        	
        	
        },
        error:function() {  
          alert("");  
        }  

    });
    

});
function extract(level,realdata){
	
    $.ajax({
    		type:'GET',
    		url:'/CITIC/getsecondlevel?projectid=${projectid}&threadname=${threadname}&id='+realdata,
    		contentType : 'application/json',
            dataType: 'json',
            success: function (data) {
            	
            	
            	var html = "";
            	for(i = 0; i< data.length; i++){
            		
            		html += "<li>";
            		html +="<div class='content1'><p id='p_1_"+data[i].noteid+"'><b>"+data[i].notetitle+"</b>&nbsp&nbsp&nbspcreated at "+data[i].createtime;
            		html +=" by " +getName(data[i].noteid,1)+"</p>";
            		html += data[i].notecontent;
            		html +="<a onclick='openmy("+data[i].noteid+")' href='javascript:'>comment</a></div>";
            		html +="<ul id='"+realdata+"_"+data[i].noteid+"'></ul>";
            		html += "</li>";
            		extract1(realdata,data[i].noteid);
            	}
            	document.getElementById(level).innerHTML = html;
            	
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
            		html +="<div class='content2'><p id='p_2"+data[i].noteid+"'><b>"+data[i].notetitle+"</b>&nbsp&nbsp&nbspcreated at "+data[i].createtime;
            		html +=" by " +getName(data[i].noteid,2)+"</p>";
            		html += data[i].notecontent;
            		html +="<a onclick='openmy("+data[i].noteid+")' href='javascript:'>comment</a></div>";
            		
            		html +="<ul id='"+level+"_"+realdata+"_"+data[i].noteid+"'></ul>";
            		html += "</li>";
            		extract2(level,realdata,data[i].noteid)
            		
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
            		html +="<div class='content3'><p id='p_3_"+data[i].noteid+"'><b>"+data[i].notetitle+"</b>&nbsp&nbsp&nbspcreated at "+data[i].createtime;
            		html +=" by " +getName(data[i].noteid,3)+"</p>"
            		html += data[i].notecontent;
            		html +="<a onclick='openmy("+data[i].noteid+")' href='javascript:'>comment</a></div>";
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
function getName(id,level){
	var html="";
	   $.ajax({
   		type:'GET',
   		url:'/CITIC/getName?projectid=${projectid}&threadname=${threadname}&id='+id,
   		contentType : 'application/json',
        dataType: 'json',
        success: function (data) {
           	
        	
           
           	for(i = 0; i< data.length; i++){
           		console.log(html);
           		html+=data[i]+" ";
           		
           		
           }
           	$("#p_"+level+"_"+id).append(html);
           	
           	
           	
        },
       error:function() {
        	   
             alert("afae");  
             
       }  		
   		
   	});
	return html;	
		
}
</script>
<body>
	<ul id="first">

	</ul>
</body>
</html>