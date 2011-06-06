<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:actionerror/>
<s:form id="signUpForm" theme="xhtml" action="SignUp_signUp" >
	<s:textfield name="userBean.login" key="login.username" />
	<s:password name="userBean.md5_mdp" key="login.password" />
	<s:password name="confirmPassword" key="login.password.again" />
	<s:textfield name="userBean.nom" key="login.nom" />
	<s:textfield name="userBean.prenom" key="login.prenom" />
	<s:textfield name="userBean.email" key="login.email" />
	<sj:submit button="true" key="login.submit" validate="true" indicator="indicator" cssClass="float-right" />
</s:form>
<img id="indicator" src="images/indicator.gif" alt="Loading..." style="display:none"/>

