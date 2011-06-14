<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:actionerror />
<s:actionmessage />
<s:form id="updateAccountForm" theme="xhtml"
	action="updateAccount_updateAccount">
	<s:textfield name="userBean.login" key="login.username"
		disabled="true" cssClass="disabled" value="#session.user.login" />

<%-- 	<s:doubleselect
		name="userBean.region" list="regions" listValue="nom" listKey="id"
		headerKey="-1" headerValue="%{getText('select.region')}" value="%{#session.user.regionIdKey}"
		doubleName="userBean.departement" doubleList="departements" doubleListValue="nom"
		doubleHeaderKey="-1" doubleHeaderValue="%{getText('select.depart')}" doubleValue="%{#session.user.regionIdKey}">
	</s:doubleselect>--%>

	<s:textfield name="userBean.codePostal" key="login.codePostal" value="#session.user.codePostal" />
	<s:textfield name="userBean.nom" key="login.nom" value="#session.user.nom" />
	<s:textfield name="userBean.prenom" key="login.prenom" value="#session.user.prenom" />
	<s:textfield name="userBean.email" key="login.email" value="#session.user.email" />
	<s:textfield name="userBean.telephoneFixe" key="login.telfixe" value="#session.user.telephoneFixe" />
	<s:textfield name="userBean.telephonePortable" key="login.telephonePortable" value="#session.user.telephonePortable" />
	<sj:submit button="true" key="account.update" validate="true"
		cssClass="float-right" />
</s:form>