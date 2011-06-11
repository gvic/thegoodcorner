<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:actionerror/>
<s:form id="addCommunauteForm" theme="xhtml" action="addCommunaute_processDatas">
	<s:textfield name="comBean.denomination" key="communaute.denomination" cssClass="active"/>
	<s:textarea name="comBean.description" key="communaute.description" cols="30" rows="8" cssClass="active"/>
	<sj:submit button="true" key="generic.submit" validate="true" indicator="indicator" cssClass="float-right"/>
</s:form>
<img id="indicator"  src="/template/new_xhtml/css/images/indicator.gif" alt="Loading..." style="display:none"/>