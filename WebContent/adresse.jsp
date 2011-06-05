<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<s:actionerror />
	<s:form id="adresseForm" theme="xhtml" action="addAdresse">
		<s:textfield name="adresseBean.numeroVoie" label="Numéro de voie" />
		<s:textfield name="adresseBean.nomVoie" label="Nom de la voie" />
		<s:textfield name="adresseBean.codePostal" label="Code postal" />
		<s:textfield name="adresseBean.ville" label="Ville" />
		<s:textfield name="adresseBean.departement" label="Département" />
		<s:textfield name="adresseBean.region" label="Région" />
		<s:submit button="true" value="login.submit" validate="true"
			indicator="indicator" cssClass="float-right" />
	</s:form>
</body>
</html>