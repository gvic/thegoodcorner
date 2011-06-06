<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="%{#session.loggedin != 'true'}">
  <jsp:forward page="/index.html" />  
</s:if>