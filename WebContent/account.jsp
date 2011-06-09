<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:actionerror/>
<s:actionmessage/>
<s:form id="updateAccountForm" theme="xhtml" action="updateAccount_updateAccount" >
	<s:textfield name="userBean.login" key="login.username" value="%{userBean.login}" disabled="true" cssClass="disabled"/>
	<s:textfield  name="userBean.nom" key="login.nom" value="%{userBean.nom}" cssClass="active"/>
	<s:textfield name="userBean.prenom" key="login.prenom" value="%{userBean.prenom}" cssClass="active"/>
	<s:textfield name="userBean.email" key="login.email" value="%{userBean.email}" cssClass="active"/>
	<s:textfield name="userBean.telephoneFixe" key="login.telfixe" value="%{userBean.telephoneFixe}" cssClass="active"/>
	<s:textfield name="userBean.telephonePortable" key="login.telephonePortable" value="%{userBean.telephonePortable}" cssClass="active"/>
	<sj:submit button="true" key="account.update" validate="true" indicator="indicator" cssClass="float-right" />
</s:form>
<img id="indicator" src="images/indicator.gif" alt="Loading..." style="display:none"/>
