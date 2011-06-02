<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<title><s:property value="%{getText('login.page.titre')}" /></title>
</head>
<body>	
	<s:actionerror />

	<s:form action="doSignUp.action" method="post" validate="true">
		<s:textfield name="username" label="login.username" />
		<s:password name="password" label="login.password" />
		<s:submit method="signUp" value="login.submit" align="center" />
	</s:form>
</body>
</html>

