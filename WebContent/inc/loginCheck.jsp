<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="#session.logged-in != 'true'">
  <jsp:forward page="/login.html" />  
</s:if>