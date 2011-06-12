<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:actionerror/>
<s:form id="addCommunauteForm" theme="xhtml" action="addCommunaute_processDatas">
	<s:textfield name="comBean.denomination" key="communaute.denomination" />
	<s:textarea name="comBean.description" key="communaute.description" cols="30" rows="8" />
	<sj:submit button="true" key="generic.submit" validate="true" cssClass="float-right"/>
</s:form>
