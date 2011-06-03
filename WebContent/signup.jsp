<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:actionerror/>
<s:form method="post" theme="xhtml" action="SignUp_signUp" >
	<s:textfield name="userBean.login" label="login.username" />
	<s:password name="userBean.md5_mdp" label="login.password" />
	<s:password name="confirmPassword" label="login.password.again" />
	<s:textfield name="userBean.nom" label="login.nom" />
	<s:textfield name="userBean.prenom" label="login.prenom" />
	<s:textfield name="userBean.email" label="login.email" />
	<sj:submit button="true" value="login.submit"  validate="true" />
</s:form>


