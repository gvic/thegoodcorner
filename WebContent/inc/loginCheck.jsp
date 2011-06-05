<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="#session.logged-in != 'true'">
<s:action name="Login_input" ></s:action>
</s:if>