<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<title><s:property value="%{getText('login.page.titre')}" />
</title>
</head>
<body>	
	<s:actionerror />

	<s:form namespace="/" action="doSignUp" method="post">
		<s:textfield name="username" label="login.username" />
		<s:password name="password" label="login.password" />
		<s:submit value="login.submit" align="center" />
	</s:form>
</body>
</html>

