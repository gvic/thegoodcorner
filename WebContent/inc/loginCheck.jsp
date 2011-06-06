<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="%{#session.loggedin != 'true'}">
  <s:action name="index"></s:action>  
</s:if>