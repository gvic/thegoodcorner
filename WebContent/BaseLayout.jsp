<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<sj:head />
        <!-- This files are needed for AJAX Validation of XHTML Forms -->
        <script language="JavaScript" src="${pageContext.request.contextPath}/struts/utils.js" type="text/javascript"></script>
        <script language="JavaScript" src="${pageContext.request.contextPath}/struts/xhtml/validation.js" type="text/javascript"></script>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<!-- Add CSS and JS files configured in tiles.xml -->
<tiles:useAttribute id="list" name="css" classname="java.util.List" />
<tiles:useAttribute id="list2" name="scripts" classname="java.util.List" />
<c:forEach var="item" items="${list2}">
  <script type="text/javascript" src="${item}"></script>
</c:forEach>
<c:forEach var="item" items="${list}">
  <link href="${item}" rel="stylesheet" type="text/css" />
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