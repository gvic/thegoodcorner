<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:actionerror/>
<s:form id="signUpForm" theme="xhtml" action="SignUp_signUp" >
	<s:textfield name="userBean.login" key="login.username" cssClass="active"/>
	<s:password name="userBean.md5_mdp" key="login.password" cssClass="active"/>
	<s:password name="confirmPassword" key="login.password.again" cssClass="active"/>
	<s:textfield name="userBean.nom" key="login.nom" cssClass="active"/>
	<s:textfield name="userBean.prenom" key="login.prenom" cssClass="active"/>
	<s:textfield name="userBean.email" key="login.email" cssClass="active"/>
	<s:textfield name="userBean.telephoneFixe" key="login.telfixe" cssClass="active"/>
	<s:textfield name="userBean.telephonePortable" key="login.telephonePortable" cssClass="active"/>
	<sj:submit button="true" key="login.submit" validate="true"  indicator="indicator" cssClass="float-right" />
</s:form>
<img id="indicator"  src="/template/new_xhtml/css/images/indicator.gif" alt="Loading..." style="display:none"/>
