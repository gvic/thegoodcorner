<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:actionerror/>
<s:form id="changePasswordForm" theme="xhtml" action="changePassword" >
	<s:textfield name="login" key="login.username" value="%{login}" disabled="true" cssClass="disabled"/>
	<s:password name="oldPassword" key="login.oldPassword" cssClass="active"/>
	<s:password name="newPassword" key="login.newPassword" cssClass="active"/>
	<s:password name="confirmPassword" key="login.newPassword.again" cssClass="active"/>
	<sj:submit button="true" key="login.submit" validate="true" indicator="indicator" cssClass="float-right" />
</s:form>
<img id="indicator" src="images/indicator.gif" alt="Loading..." style="display:none"/>

