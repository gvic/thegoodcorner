<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:actionerror/>
<s:form id="submitAdForm" theme="xhtml" action="populateAd_processDatas">
	<s:select list="geographicAreas"></s:select>
	<s:select list="communautes"></s:select>
	<s:textfield name="annonce.title" key="annonce.title" />
	<s:textarea cols="50" rows="10" name="annonce.description" key="annonce.description"/>
	<s:textfield name="annonce.price" key="annonce.price"/>
	<sj:submit button="true" value="login.submit" validate="true" indicator="indicator" cssClass="float-right"/>
</s:form>