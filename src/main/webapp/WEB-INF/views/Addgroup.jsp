<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add group</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<jsp:include page="ME.jsp" />
	<div class="nav2">
		<p style="margin-top: 200px">Add a new group</p>
		<div style="margin-left: 300px">

			<form name="groupform" action="/CITIC/addgroup" method="post">
				<table>
					<tr>
						<td>Group name:<input type="text" name="groupname" /></td>
					</tr>
					<tr>
						<td>Description:<input type="text" name="groupcontent" /></td>
					</tr>
					<tr>
						<td><input type="submit" name="submit"></td>
					<tr>
				</table>
			</form>
		</div>
		<p>${message }</p>




	</div>
</body>
</html>