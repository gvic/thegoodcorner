<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:actionerror/>
<s:form id="logInForm" theme="xhtml" action="Login_login" >
	<s:textfield name="login" label="username" cssClass="active" />
	<s:password name="password" label="password" cssClass="active"/>
	<sj:submit button="true" value="login.submit" validate="true" cssClass="float-right"/>
</s:form>
