<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:actionerror />
<s:form id="adresseForm" theme="xhtml" action="addAdresse">
	<s:textfield name="adresseBean.numeroVoie" label="Numéro de voie" />
	<s:textfield name="adresseBean.nomVoie" label="Nom de la voie" />
	<s:textfield name="adresseBean.codePostal" label="Code postal" />
	<s:textfield name="adresseBean.ville" label="Ville" />
	<s:textfield name="adresseBean.departement" label="Département" />
	<s:textfield name="adresseBean.region" label="Région" />
	<sj:submit button="true" value="login.submit" validate="true"
		indicator="indicator" cssClass="float-right" />
</s:form>
<img id="indicator" src="images/indicator.gif" alt="Loading..." style="display:none"/>
