<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!--	<s:actionerror />-->
<s:form action="doSignUp" validate="true">
	<s:textfield name="userBean.login" label="login.username" />
	<s:password name="userBean.md5_mdp" label="login.password" />
	<s:textfield name="userBean.nom" label="login.nom" />
	<s:textfield name="userBean.prenom" label="login.prenom" />
	<s:textfield name="userBean.email" label="login.email" />
	<s:submit value="login.submit" align="center" />
</s:form>


