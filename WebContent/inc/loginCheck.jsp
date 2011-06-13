<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:if test="%{#session.loggedin != 'true'}">
  	<meta http-equiv="refresh" content="0; url=<s:url action="loginPage"></s:url>" />
	<meta name="robots" content="noindex,follow" />
</s:if>