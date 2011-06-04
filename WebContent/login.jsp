<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:actionerror/>
<s:form id="logInForm" theme="xhtml" action="Login_login" >
	<s:textfield name="login" label="username" />
	<s:password name="password" label="password" />
	<sj:submit button="true" value="login.submit" validate="true" indicator="indicator" />
</s:form>
<img id="indicator" src="images/indicator.gif" alt="Loading..." style="display:none"/>

