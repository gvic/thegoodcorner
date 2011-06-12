<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="account-infos">
	<s:actionerror />
	<s:actionmessage />
	<s:form id="updateAccountForm" theme="xhtml"
		action="updateAccount_updateAccount">
		<s:textfield name="login" key="login.username" value="%{login}"
			disabled="true" cssClass="disabled" />

		<s:select name="regionId" key="region.select" headerKey="%{regionIdKey}"
			headerValue="%{region}" list="regions" listValue="nom"
			listKey="id"></s:select>
		<s:select name="departementId" key="departement.select" headerKey="%{departementIdKey}"
			headerValue="%{departement}" list="departements" listValue="nom"
			listKey="id"></s:select>

		<s:textfield name="codePostal" key="login.codePostal" value="%{codePostal}" />
		<s:textfield name="name" key="login.nom" value="%{name}" />
		<s:textfield name="firstname" key="login.prenom" value="%{firstname}" />
		<s:textfield name="email" key="login.email" value="%{email}" />
		<s:textfield name="phone" key="login.telfixe" value="%{phone}" />
		<s:textfield name="mobile" key="login.telephonePortable"
			value="%{mobile}" />
		<sj:submit button="true" key="account.update" validate="true"
			cssClass="float-right" />
	</s:form>
</div>

<div id="account-menu">
	<div class="thead"><s:text name="account.menu" /></div>
	<ul>
		<li>
		<s:url action="list" id="listurl">
			<s:param name="userId" value="%{#session.userId}"></s:param>
		</s:url>
		<s:a href="%{listurl}" >
			<s:text name="account.show.ads" />
		</s:a>
		</li>
		<li>
		<s:a action="addCommunaute_input">
			<s:text name="account.add.communaute" />
		</s:a>
		</li>
		<li>
		<s:a action="changePassword_input">
			<s:text name="account.changePassword" />
		</s:a>
		</li>
	</ul>
</div>
