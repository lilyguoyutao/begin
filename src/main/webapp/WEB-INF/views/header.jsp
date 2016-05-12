<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="resources/Style.css" rel='stylesheet' type='text/css' />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<%
	String loggedIn = (String) session.getAttribute("iflogin");
	if (loggedIn == null)
		loggedIn = "";



%>
</head>
<body>
	<img src="resources/logo.png" Style="width: 80px; height: 80px;">

	<div id="header"
		style="background-color: #33CCFF; width: 1010px; height: 80px; position: absolute; left: 100px; top: 0px">
		<form name="form1" action="/CITIC/findallthread" method="post">

			<input type="text" name="search"
				style="height: 30px; margin-left: 10px; margin-top: 20px; width: 300px"
				placeholder="discover ideas,thread"></input> <a
				href="javascript:form1.submit"><img
				src="resources/Search-icon.png" Style="width: 20px; height: 20px;"></a>
		</form>

		<a href="/CITIC/home.jsp"
			style="float: right; margin-left: 10px; margin-right: 10px">Home</a>

		<%
			if (loggedIn.equals("")) {
		%>
		<a href="/CITIC/userlogin" style="float: right">Log in</a><br>
		<%
			} else {
		%>
		<a href="/CITIC/logout" style="float: right">Log out</a><br>
		<%
			}
		%>
	</div>
	<div Style="height: 20; background-color: red" style="float:clear"></div>
</body>
</html>