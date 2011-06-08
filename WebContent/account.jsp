<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:actionerror/>

<s:form id="updateAccountForm" theme="xhtml" action="updateAccount" >
	<s:label name="userBean.login" value="login.username"/> <%="login"%>
	<s:property value="%{login}" />
	<%
	//<s:password name="userBean.md5_mdp" label="login.password" value="<% u.getNom(); "/>
	//<s:password name="confirmPassword" label="login.password.again" value="<% "/>
	%>
	<s:textfield disabled="true" name="userBean.nom" label="login.nom" value="name"/>
	<s:textfield name="userBean.prenom" label="login.prenom" value="firstname"/>
	<s:textfield name="userBean.email" label="login.email" value="email"/>
	<sj:submit button="true" value="login.submit" validate="true" indicator="indicator" cssClass="float-right" />
</s:form>

<img id="indicator" src="images/indicator.gif" alt="Loading..." style="display:none"/>
