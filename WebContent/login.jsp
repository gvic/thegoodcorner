<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:actionerror/>
<s:form id="logInForm" theme="new_xhtml" action="Login_login" >
	<s:textfield name="login" key="login.username" cssClass="active"/>
	<s:password name="password" key="login.password" cssClass="active"/>
	<sj:submit button="true" key="login.submit" validate="true" indicator="indicator" cssClass="float-right"/>
</s:form>

