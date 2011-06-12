<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:actionerror/>
<s:actionmessage/>
<s:form id="searchForm" theme="xhtml" action="search_search" >
	<s:textfield name="text" key="search.text"  />
	<sj:submit button="true" key="login.submit" validate="true" cssClass="float-right" />
</s:form>
<s:iterator value="results">
<p><s:property/></p>
</s:iterator>