<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:actionerror/>
<s:form id="submitAdForm" theme="xhtml" action="submitAd" >
	<s:select list="departements" key="annonce.departement" name="department"></s:select>
	<s:select list="communautes" key="annonce.communaute" name="community"></s:select>
	<s:textfield name="title" label="Title" key="annonce.title" />
	<s:textarea cols="50" rows="10" name="description" label="Description" key="annonce.description"/>
	<s:textfield name="price" label="Price" key="annonce.price"/>
	<sj:submit button="true" value="login.submit" validate="true" indicator="indicator" cssClass="float-right"/>
</s:form>