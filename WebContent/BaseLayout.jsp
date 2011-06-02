<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<tiles:useAttribute id="list" name="css" classname="java.util.List" />
<tiles:useAttribute id="list2" name="scripts" classname="java.util.List" />
<c:forEach var="item" items="${list}">
  <script type="text/javascript" src="<tiles:insertAttribute value="${item}" flush="true" />"></script>
</c:forEach>
<c:forEach var="item" items="${list2}">
  <link href="<tiles:insertAttribute value="${item}" flush="true" />" rel="stylesheet" type="text/css" />
</c:forEach>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>
		<div id="page">
			<tiles:insertAttribute name="body" />
		</div>
		<div id="footer">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>
</html>