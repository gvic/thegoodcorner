<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:actionerror/>
<s:actionmessage/>
<s:form id="changePasswordForm" theme="xhtml" action="changePassword_changePassword" >
	<s:textfield name="login" key="login.username" value="%{login}" disabled="true" cssClass="disabled"/>
	<s:password name="oldPassword" key="login.oldPassword" />
	<s:password name="newPassword" key="login.newPassword" />
	<s:password name="confirmPassword" key="login.newPassword.again" />
	<sj:submit button="true" key="login.submit" validate="true" cssClass="float-right" />
</s:form>
