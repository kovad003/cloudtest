<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<form action='/rest/connservice/printconnection' method='post'>
			<p>Get connection properties:</p>
			<input type='submit' name='ok' value='SHOW'><br>
		</form>
	
		<form action='/rest/connservice/printproperties' method='post'>
			<p>Get all system properties:</p>
			<input type='submit' name='ok' value='SHOW'><br>
		</form>
	
		<br><br>
		<p>Results:</p>
	
		<ol>
			<c:forEach var="map" items="${requestScope.map }">
				<li><c:out value="${map.key}"/> => <c:out value="${map.value}"/>
			</c:forEach>
		</ol>
	</body>
</html>


