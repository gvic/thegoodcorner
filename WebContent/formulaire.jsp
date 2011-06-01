<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value="%{getText('formulaire.page.titre')}" /></title>
<s:head />
<script type="text/javascript"
	src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.1.custom.min.js"></script>
<script type="text/javascript" src="js/main.js"></script>
</head>
<body>
<s:fielderror></s:fielderror>
<s:form action="formulaire.action">
	<s:textfield name="titre" label="%{getText('formulaire.titre')}" />
	<s:textarea name="description"
		label="%{getText('formulaire.description')}"></s:textarea>
	<s:textfield name="prix" label="%{getText('formulaire.prix')}" />
	<s:submit value="%{getText('formulaire.valider')}" />
</s:form>

<label>Address: </label>
<input id="address" type="text" />
<div id="map_canvas" style="width: 300px; height: 300px"></div>
<br />
<label>latitude: </label>
<input id="latitude" type="text" />
<br />
<label>longitude: </label>
<input id="longitude" type="text" />
</body>
</html>