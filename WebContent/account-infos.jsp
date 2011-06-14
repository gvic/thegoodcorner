<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:actionerror />
<s:actionmessage />
<s:form id="updateAccountForm" theme="xhtml"
	action="updateAccount_updateAccount">
	<s:textfield name="userBean.login" key="login.username"
		disabled="true" cssClass="disabled" value="%{userBean.login}" />

	<s:doubleselect
		name="userBean.region" list="regions" listValue="nom"
		headerKey="-1" headerValue="%{getText('select.region')}" value="%{userBean.region}"
		doubleName="userBean.departement" doubleList="departements" doubleListValue="nom"
		doubleHeaderKey="-1" doubleHeaderValue="%{getText('select.depart')}" doubleValue="%{userBean.departement}">
	</s:doubleselect>

	<s:textfield name="userBean.codePostal" key="login.codePostal" value="%{userBean.codePostal}" />
	<s:textfield name="userBean.nom" key="login.nom" value="%{userBean.nom}" />
	<s:textfield name="userBean.prenom" key="login.prenom" value="%{userBean.prenom}" />
	<s:textfield name="userBean.email" key="login.email" value="%{userBean.email}" />
	<s:textfield name="userBean.telephoneFixe" key="login.telfixe" value="%{userBean.telephoneFixe}" />
	<s:textfield name="userBean.telephonePortable" key="login.telephonePortable" value="%{userBean.telephonePortable}" />
	<sj:submit button="true" key="account.update" validate="true"
		cssClass="float-right" />
</s:form>