<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="#session.login != 'true'">
<jsp:forward page="<%= request.getContextPath() %>/login.jsp" />
</s:if>