<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:actionerror/>
<s:form id="submitAdForm" theme="xhtml" action="populateAd_input">
	<s:select name="geographicArea" list="geographicAreas"></s:select>
	<s:select multiple="true" key="sCommunautes" list="communautes"></s:select>
	<s:textfield name="title" key="title" />
	<s:textarea cols="50" rows="10" name="description" key="description"/>
	<s:textfield name="price" key="price"/>
	<sj:submit button="true" value="login.submit" validate="true" indicator="indicator" cssClass="float-right"/>
</s:form>