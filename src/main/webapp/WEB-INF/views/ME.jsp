<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>


<title>Insert title here</title>

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<style>
ul.lilydropmenu {
	list-style-type: circle;
}

ul.lilydropmenu li {
	display: block;
	position: relative;
}

ul.lilydropmenu li ul {
	display: none;
}

ul.lilydropmenu li a {
	display: block;
	padding: 0.5em;
}

.lilydropmenu li:hover>ul {
	display: block;
	position: absolute;
}

.lilydropmenu li:hover li {
	float: none;
}

ul.lilydropmenu  li:hover a {
	background: white;
}

.lilydropmenu li:hover li a:hover {
	background: #A0A0A0;
}

.lilydropmenu li ul li {
	border-top: 0;
}

ul.lilydropmenu li a:hover {
	background: yellow;
}

ul.lilydropmenu ul {
	/*width: 200px;*/
	border-style: outset;
	margin-left: 110px;
	padding-left: 0px;
	margin-top: -40px;
}

ul.lilydropmenu ul ul {
	width: 150px;
	right: -150px;
	top: 0%;
	border-style: outset;
	padding-left: 0px;
	margin-top: 2px;
}

ul.lilydropmenu:before, ul.lilydropmenu:after {
	content: " "; /* 1 */
	display: table; /* 2 */
}

ul.lilydropmenu:after {
	clear: both;
}
.newid2 {
 display: block;
	position: absolute;
	background: white;
	border-bottom: 2px solid;
}

.newid {
	background: white;
	border-bottom: 2px solid;
}
</style>
<script>
$(document).ready(function () {
    
    $.ajax({
        type: 'Get',
        url: '/CITIC/json',
        contentType : 'application/json',
        dataType: 'json',
        success: function (data) {
        	var html = "";
        	for(i = 0; i< data.length; i++){
        		var al=data[i];
        		if(al.length>=8)
        			al=al.substr(0,10)+"..."
        		html += "<li id='li_1_'"+i+"' class='first'>";
        		html += "<a"+" onmouseover='extract1("+i+",\""+data[i]+"\")'" + "href='/CITIC/detail?comm_name="+data[i]+"'>"+al+"</a>";
        		html +="<ul id='1_"+i+"'></ul>"
        		html += "</li>";
        	}
        	document.getElementById("0_1").innerHTML = html;
        },
        error:function() {  
          alert("");  
        }  

    });
    

});
    
 function extract1(le,mess){
    	
    $.ajax({
    		type:'GET',
    		url:'/CITIC/level2?community='+mess,
    		contentType : 'application/json',
            dataType: 'json',
            success: function (data) {
            	
            	
            	var html="";
            	html+="<li class='newid2'><a>"+mess+"</a></li>"
            	html+="<li class='newid'>&nbsp&nbsp&nbsp&nbsp&nbspProject</li>";
            	for(i = 0; i< data.length; i++){
            		var al=data[i].id;
            		var el=data[i].projectname;
            		html += "<li id='li_2_'"+i+"'>";
            		html += "<a"+" onmouseover='extract2("+le+","+i+",\""+al+"\")'" + "href='/CITIC/getproject-thread?projectid="+al+"'>"+el;
            		html +="<p><font size='1.5'> teacher: "+data[i].teacher+" School: "+data[i].school+" Grade:"+data[i].grade+"</font></p></a>";
            		html +="<ul id='"+le+"_2_"+i+"'></ul>";
            		html += "</li>";
            		
            	}
            	document.getElementById("1_"+le).innerHTML = html;
            },
            error:function() {  
              alert("");  
            }  		
    		
    	});
    	
    	 }
    
    function extract2(le,lev,al){
    	$.ajax({
    		type:'GET',
    		url:'/CITIC/level3?project='+al,
    		contentType : 'application/json',
            dataType: 'json',
            success: function (data) {
            	var html="<li class='newid'>&nbsp&nbspIdea Threads</li>";
            	
            	for(i = 0; i< data.length; i++){
            		var cl=data[i].threadfocus;
            		var dl=data[i].projectid;
            		html += "<li id='li_3_'"+i+"' class='first'>";
            		//html += "<a"+"href='/CITIC/threadpage?threadname="+data[i]+"'>--"+data[i]+"</a>";
            		html += "<a href='/CITIC/threadpage?projectid="+dl+"&threadname="+cl+"'>"+cl+"</a>";
            		html += "</li>";
            		
            	}
            	document.getElementById(le+"_2_"+lev).innerHTML = html;
            },
            error:function() {  
              alert("");  
            }  		
    		
    	});
    	
    	
    	
    	
    }
    
    




</script>
</head>
<body>
	<%  String Loginin=(String)session.getAttribute("iflogin");
     if(Loginin==null)
    	 Loginin="";
     String user=(String)session.getAttribute("username");
     if(user==null)
    	 user="";
     String type=(String)session.getAttribute("type");
     if(type==null)
    	 type="";
     String con1=(String)session.getAttribute("con1");
     if(con1==null)
    	 con1="";
     String con2=(String)session.getAttribute("con2");
     if(con2==null)
    	 con2="";
%>

	<div id="nav">

		<h4>
			Hello !
			<%
	out.println(user);%>
			<!--a href="/CITIC/userprofile" style="text-decoration:none"><img src="resources/profile.jpg" style="width:55px; height:80px;"></a-->
		</h4>

		<%
	
	
	if(Loginin.equals("true")) 
		{
		
	%>

		<ul id="mewlist" style="list-style-type: none">

			<li><a href="/CITIC/personal" style="text-decoration: none">Personal
					Space</a></li>
			<li><input type="hidden" /></li>
			<li><a class="i" href="/CITIC/listcommunity"
				style="text-decoration: none">My community</a></li>

			<li>

				<ul id="0_1" class="lilydropmenu">

				</ul>
			</li>





			<li><input type="hidden" style="clear: left" /></li>
			<%if(type.equals("teacher")){ %>
			<li><a class="i" href="/CITIC/community" style="clear: left">Create
					Community</a></li>
			<li><a class="i" href="/CITIC/addgroup" style="clear: left">Create
					a group</a></li>
			<%} %>
			<li><input type="hidden" /></li>
			<li><a class="i" href="/CITIC/listmygroup" style="clear: left">My
					group</a></li>
			<li><p>&nbsp</p></li>
			<li>&nbsp</li>


		</ul>
		<ul style="list-style-type: none">
			<li>CITIC NETWORK</li>

			<li><a class="i" href="/CITIC/browse">Search</a></li>
			<li><a class="i" href="/CITIC/contentbrowse">Browse</a></li>

		</ul>





		<%}
	else{
		out.print("Please LOGIN!");
	}
	%>

	</div>

</body>
</html>