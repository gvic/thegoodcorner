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
<p><s:property value=""/></p>
</s:iterator>

 <s:bean name="actions.SearchAction" id="results">
   <s:param name="r" value="'foo'"/>
   <s:param name="r" value="'bar'"/>
 </s:bean>
 

 <table border="0" cellspacing="0" cellpadding="1">
 <tr>
   <th>Days of the week</th>
 </tr>
 


 <s:iterator value="#results.r" status="rowstatus">
   <tr>
     <s:if test="#rowstatus.odd == true">
       <td style="background: grey"><s:property/></td>
     </s:if>
     <s:else>
       <td><s:property/></td>
     </s:else>
   </tr>
 </s:iterator>
 </table>