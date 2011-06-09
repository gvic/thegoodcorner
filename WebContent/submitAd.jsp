<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:actionerror/>
<s:form id="submitAdForm" theme="xhtml" action="populateAd_input"  enctype="multipart/form-data" method="POST">
	<s:select key="Place" name="geographicArea" list="geographicAreas"></s:select>
	<s:select multiple="true" key="Community" list="communautes"></s:select>
	<s:select key="Category" list="categories"></s:select>
	<s:textfield name="title" key="Title" cssClass="active"/>
	<s:textarea cols="50" rows="10" name="description" key="Description" cssClass="active"/>
	<s:textfield name="price" key="Price" cssClass="active"/>
	<s:file name="image" label="File"/>
	<s:textfield name="caption" label="Caption"/>
	<sj:submit button="true" value="login.submit" validate="true" indicator="indicator" cssClass="float-right"/>
</s:form>