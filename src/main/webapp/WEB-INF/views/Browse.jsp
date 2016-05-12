<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search</title>
<script>
$(document).ready(function(){
	
 $.ajax({
	type:'GET',
	url:'/CITIC/listallcommunity',
    contentType : 'application/json',
    dataType: 'json',
    success: function (data) {
        	var html = "<option>all</option>";
        	for(i = 0; i< data.length; i++){
        		html+="<option>"+data[i]+"</option>"
        	}
        	document.getElementById("selectcommunity").innerHTML = html;
        },
        error:function() {  
          alert("");  
        }  

 
 }
 
 )




});

function extend(){
	$("#tablee").fadeToggle();
}


</script>
</head>
<body>
	<%@ include file="header.jsp"%>

	<jsp:include page="ME.jsp" />
	<div class="nav2">
		<div style="margin-bottom: 30px">
			<table>
				<tr>
					<td><font size="4">Search for Projects</font></td>
					<td>&nbsp</td>
					<td><font size='4'><a style="font: red"
							href="/CITIC/searchthread">Search for Idea threads</a></font></td>

				</tr>

			</table>
        
		</div>
		<div>
		  <a href="/CITIC/searchprojectall">show all</a>
			<form action="/CITIC/searchproject" method="post">
				<table class="table table-hover">

					<tbody>
						<tr>
							<td>Project name:<input type="text" name="name" />&nbsp<a
								onclick="extend()" href="javascript:">Add more criteria</a></td>
							<td>&nbsp</td>
						</tr>
					</tbody>

				</table>
				<table id="tablee" class="table table-hover" style="display: none">
					<tr>
						<td>School:<input type="text" name="school" /></td>
						<td>Teacher:<input type="text" name="teacher" /></td>
						<td>Community:<select name="community" id="selectcommunity"></select></td>
					<tr>
						<td>From :<input type="text" name="fromyear" /></td>
						<td>To:<input type="text" name="toyear" /></td>
						<td>&nbsp</td>
					</tr>

				</table>
				<table class="table table-hover">
					<tr>
						<td><input type="submit" value="Search" onclick="table2()"></td>
						<td>&nbsp</td>
						<td>&nbsp</td>
					</tr>
				</table>
			</form>
		</div>
		<div style="margin-left: 50px; margin-top: 100px">

			<table id="table2" class="table table-hover">
				<thead>
					<tr>
						<th>project_name</th>
						<th>teacher</th>
						<th>School</th>
						<th>from year</th>
						<th>to year</th>
						<th>Community</th>
						<th>area</th>
					</tr>
				</thead>
				<c:forEach var="project" items="${projects}">
					<tbody>
						<tr>
							<td><a href="/CITIC/getproject-thread?projectid=${project.id}">${project.projectname }</a></td>

							<td>${project.teacher }</td>

							<td>${project.school }</td>
							<td>${project.fromyear }</td>
							<td>${project.toyear}</td>

							<td><a
								href="/CITIC/detail?comm_name=${project.communityname}">${project.communityname}</a></td>
							<td>${project.bigidea}</td>
						</tr>

					</tbody>
				</c:forEach>

			</table>



		</div>
	</div>


</body>
</html>