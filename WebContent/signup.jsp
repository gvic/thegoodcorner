<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:actionerror/>
<s:form id="signUpForm" theme="xhtml" action="SignUp_signUp" >
	<s:textfield name="userBean.login" label="getText('login.username')" />
	<s:password name="userBean.md5_mdp" label="getText('login.password')" />
	<s:password name="confirmPassword" label="getText('login.password.again')" />
	<s:textfield name="userBean.nom" label="getText('login.nom')" />
	<s:textfield name="userBean.prenom" label="getText('login.prenom')" />
	<s:textfield name="userBean.email" label="getText('login.email')" />
	<sj:submit button="true" value="getText('signup.submit')" validate="true" indicator="indicator" cssClass="float-right" />
</s:form>
<img id="indicator" src="images/indicator.gif" alt="Loading..." style="display:none"/>

