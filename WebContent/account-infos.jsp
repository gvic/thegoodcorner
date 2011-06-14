<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:actionerror />
<s:actionmessage />
<s:form id="updateAccountForm" theme="xhtml"
	action="updateAccount_updateAccount">
	<s:textfield name="userBean.login" key="login.username"
		disabled="true" cssClass="disabled" />

	<s:doubleselect
		name="regionId" list="regions" listValue="nom"
		headerKey="-1" headerValue="%{getText('select.region')}"
		doubleName="departementId" doubleList="departements" doubleListValue="nom"
		doubleHeaderKey="-1" doubleHeaderValue="%{getText('select.depart')}">
	</s:doubleselect>

	<s:textfield name="userBean.codePostal" key="login.codePostal" />
	<s:textfield name="userBean.nom" key="login.nom" />
	<s:textfield name="userBean.prenom" key="login.prenom" />
	<s:textfield name="userBean.email" key="login.email" />
	<s:textfield name="userBean.telephoneFixe" key="login.telfixe" />
	<s:textfield name="userBean.telephonePortable" key="login.telephonePortable" />
	<sj:submit button="true" key="account.update" validate="true"
		cssClass="float-right" />
</s:form>