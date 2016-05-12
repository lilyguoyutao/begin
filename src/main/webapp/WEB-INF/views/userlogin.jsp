<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style>
body {
	background-color: #E0E0E0;
}

div.bac {
	background: url("resources/bac.png");
	background-color: blue;
	background-repeat: no-repeat;
	background-size: 800px 400px;
}
</style>
</head>
<body>
	<% String user=(String)session.getAttribute("username");
if(user==null)
	 user=""; %>
	<div style="margin-top: 30px; margin-left: 350px;">
		<p style="color: #0066CC; font-size: 40px; padding-left: 160px">Welcome
			to CITIC</p>
		<p style="color: #0066CC">Connecting Idea Threads of Innovative
			Communities:A cross-community network platform</p>
	</div>
	<div style="width: 1200px; height: 10px; margin-top: 30px;"></div>
	<div class="bac"
		style="margin-left: 250px; border: 1px solid white; width: 800px; height: 400px">


		<div
			style="margin-left: 410px; margin-top: 100px; height: 300px; font-size: 20px; padding: 20px">

			<form name="userform" action="/CITIC/userlogintest" method="post">

				Username:<input type="text" name="username" value="<%=user%>" /><br>
				<br> Password:<input type="password" name="password" /><br>
				<!-- br> Type:<select name="type">
					<option value="">Please select</option>
					<option value="teacher">teacher</option>
					<option value="writer">student</option> -->

				</select> <input type="submit" value="Submit" />
			</form>
			<br> <font color="red">${message}</font>
		</div>





	</div>

</body>
</html>