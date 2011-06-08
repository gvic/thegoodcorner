<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:actionerror/>

<s:form id="updateAccountForm" theme="xhtml" action="updateAccountResult" >
	<s:textfield name="userBean.login" label="login.username" value="%{login}"/>
	<s:textfield  name="userBean.nom" label="login.nom" value="%{name}"/>
	<s:textfield name="userBean.prenom" label="login.prenom" value="%{firstname}"/>
	<s:textfield name="userBean.email" label="login.email" value="%{email}"/>
	<sj:submit button="true" value="login.submit" validate="true" indicator="indicator" cssClass="float-right" />
</s:form>

<img id="indicator" src="images/indicator.gif" alt="Loading..." style="display:none"/>
