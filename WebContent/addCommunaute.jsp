<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:actionerror/>
<s:form id="addCommunauteForm" theme="xhtml" action="addCommunaute">
	<s:textfield name="communaute" key="communaute.denomination" />
	<s:textarea name="communauteDesc" key="communaute.desc" cols="30" rows="8"/>
	<sj:submit button="true" key="login.submit" validate="true" indicator="indicator" cssClass="float-right"/>
</s:form>
<img id="indicator" src="images/indicator.gif" alt="Loading..." style="display:none"/>