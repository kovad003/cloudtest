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
<form action='/rest/fishservice/readfish' method='post'>
<p>Click the button to list available fishes:</p>
<input type='submit' name='ok' value='SHOW'><br>
</form>

<br><br>
<p>Currently in database:</p>
<table>
<tr>
	<td>Breed</td>
	<td>Weight(kg)</td>
	<td>Length(cm)</td>
	<td>Price(€)</td>
	<td>Delete</td>
	<td>Update</td>
</tr>
<c:forEach var="fish" items="${requestScope.fishlist }">
<tr>
	<td>${fish.breed}</td>
	<td>${fish.weight}</td>
	<td>${fish.length}</td>
	<td>${fish.price}</td>
	<td><a href='../deletefish?id=${fish.id}'>Delete</a></td>
	<td><a href='../readtoupdatefish?id=${fish.id}'>Update</a></td>
</tr>
</c:forEach>
</table>
</body>
</html>


<!--

<ol>
<c:forEach var="fish" items="${requestScope.fishlist }">
	<li>${fish} <a href='../deletefish?id=${fish.id}'>Delete</a> <a href='../readtoupdatefish?id=${fish.id}'>Update</a>
</c:forEach>
</ol>
</body>
</html>

-->