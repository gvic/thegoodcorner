<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="%{#session.loggedin != 'true'}">
  <jsp:forward page="/login.html" />  
</s:if>