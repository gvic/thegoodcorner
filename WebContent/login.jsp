<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<title><s:property value="%{getText('login.page.titre')}" /></title>
</head>
<body>
	<s:actionerror />
	<s:actionerror />

	<s:form namespace="/" action="doLogin" method="post">
		<s:textfield name="username" label="username" />
		<s:password name="password" label="password" />
		<s:submit value="Connexion" align="center">
		</s:submit>
	</s:form>
</body>
</html>

