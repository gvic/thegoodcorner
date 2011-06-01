<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="core.ViewBuilder"%>
<% ViewBuilder vBuilder = new ViewBuilder("index.jsp"); %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value="%{getText('index.page.titre')}"/></title>

<%=vBuilder.getCSS() %>
<%=vBuilder.getJS() %>

</head>
<body>
	<div id="wrapper">
		<div id="header">
			<%@ include file="header.jsp" %>
		</div>
		<div id="page">
			<%@ include file="index.page.jsp" %>
		</div>
		<div id="footer">
			<%@ include file="footer.jsp" %>
		</div>
	</div>
</body>
</html>
