<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:actionerror/>
<s:form id="logInForm" theme="xhtml" action="Login_login" >
	<s:textfield name="login" key="login.username" tabindex="0"/>
	<s:password name="password" key="login.password" />
	<sj:submit button="true" key="login.submit" validate="true" cssClass="float-right"/>
</s:form>
