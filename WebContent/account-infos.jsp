<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:actionerror />
<s:actionmessage />
<s:form id="updateAccountForm" theme="xhtml"
	action="updateAccount_updateAccount">
	<s:textfield name="login" key="login.username"
		disabled="true" cssClass="disabled" value="%{login}" />

 	<s:doubleselect
		name="regionIdKey" list="regions" listValue="nom" listKey="id"
		headerKey="-1" headerValue="%{getText('select.region')}" value="%{regionIdKey}"
		doubleName="departementIdkey" doubleList="departements" doubleListValue="nom"
		doubleHeaderKey="-1" doubleHeaderValue="%{getText('select.depart')}" doubleValue="%{regionIdKey}">
	</s:doubleselect>

	<s:textfield name="codePostal" key="login.codePostal" value="%{codePostal}" />
	<s:textfield name="name" key="login.nom" value="%{name}" />
	<s:textfield name="firstname" key="login.prenom" value="%{firstname}" />
	<s:textfield name="email" key="login.email" value="%{email}" />
	<s:textfield name="phone" key="login.telfixe" value="%{phone}" />
	<s:textfield name="mobile" key="login.telephonePortable" value="%{mobile}" />
	<sj:submit button="true" key="account.update" validate="true"
		cssClass="float-right" />
</s:form>