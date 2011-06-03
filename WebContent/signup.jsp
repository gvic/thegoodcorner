<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:form method="post" theme="xhtml" action="doSignUp" >
	<s:textfield name="userBean.login" label="login.username" />
	<s:password name="userBean.md5_mdp" label="login.password" />
	<s:textfield name="userBean.nom" label="login.nom" />
	<s:textfield name="userBean.prenom" label="login.prenom" />
	<s:textfield name="userBean.email" label="login.email" />
	<sx:submit value="login.submit" align="center" validate="true"/>
</s:form>


