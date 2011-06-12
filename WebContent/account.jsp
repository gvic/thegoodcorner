<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div style="float: left">
	<s:actionerror />
	<s:actionmessage />
	<s:form id="updateAccountForm" theme="xhtml"
		action="updateAccount_updateAccount">
		<s:textfield name="login" key="login.username" value="%{login}"
			disabled="true" cssClass="disabled" />
		<s:textfield name="name" key="login.nom" value="%{name}" />
		<s:textfield name="firstname" key="login.prenom" value="%{firstname}" />
		<s:textfield name="email" key="login.email" value="%{email}" />
		<s:textfield name="phone" key="login.telfixe" value="%{phone}" />
		<s:textfield name="mobile" key="login.telephonePortable"
			value="%{mobile}" />
		<sj:submit button="true" key="account.update" validate="true" cssClass="float-right" />
	</s:form>
</div>

<div style="float: right">
	<p>
		<s:a action="showAd">
			<s:text name="account.show.ads" />
		</s:a>
	</p>
	<p>
		<s:a action="addCommunaute_input">
			<s:text name="account.add.communaute" />
		</s:a>
	</p>
	<p>
		<s:a action="changePassword_input">
			<s:text name="account.changePassword" />
		</s:a>
	</p>
</div>
