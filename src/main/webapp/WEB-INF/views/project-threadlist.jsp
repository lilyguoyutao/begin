<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Thread List</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="header.jsp"%>
	<div style="width: 1200px; height: 20px; clear: left;"></div>
	<%@ include file="ME.jsp"%>
	<div class="nav2">
<p>Project Information</p>
	
<table>

<tr><td>Project name: ${project.projectname}</td><td>Teacher :${project.teacher}</td><td>School: ${project.school}</td></tr>
<tr><td>Community : <a href="/CITIC/detail?comm_name=${project.communityname }">${project.communityname}</a></td><td> Area: ${project.bigidea }</td><td>Grade: ${project.grade}</td><td>${project.fromyear} to ${project.toyear}</td></tr>
<tr><td></td></tr>
</table>
<div style="height:30px"></div>
<table class="table table-hover">
<tr><td>Idea thread name</td><td> Author</td></tr>
<c:forEach var="thr" items="${threads}">
<tr><td><a href="/CITIC/threadpage?projectid=${project.id}&threadname=${thr.threadfocus}">${thr.threadfocus}</a></td><td>${thr.author}</td></tr>
</c:forEach>
</table>
</div>
</body>
</html>