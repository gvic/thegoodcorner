<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:form id="signUpForm" theme="xhtml" action="SignUp_signUp" >
	<s:textfield name="userBean.login" key="login.username" />
	<s:password name="userBean.md5_mdp" key="login.password" />
	<s:password name="confirmPassword" key="login.password.again" />
	<s:textfield name="userBean.nom" key="login.nom" />
	<s:textfield name="userBean.prenom" key="login.prenom" />
	<s:textfield name="userBean.email" key="login.email" />
	<s:textfield name="userBean.telephoneFixe" key="login.telfixe" />
	<s:textfield name="userBean.telephonePortable" key="login.telephonePortable" />
	<sj:submit button="true" key="login.submit" validate="true" cssClass="float-right" />
</s:form>