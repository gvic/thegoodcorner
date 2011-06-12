<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- If login needed, the loginCheck.jsp will check the session for you -->
<tiles:insertAttribute name="loginCheck"  ignore="true"/>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<sj:head jqueryui="true" compressed="true" defaultIndicator="indicator" />
<s:head theme="new_xhtml"/>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<div id="header_l">
				<div id="header_r">			
					<tiles:insertAttribute name="header" />
				</div>
			</div>
		</div>
		<div id="page_l">
			<div id="page_r">
				<div id="page">
					<tiles:insertAttribute name="body" />
				</div>
			</div>
		</div>
		<div id="footer">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
	<img id="indicator"  src="<s:url value="/template/themes/css/images/indicator.gif" ></s:url>" alt="Loading..." style="display:none"/>
	<div id="overlay-popup"></div>
</body>
</html>