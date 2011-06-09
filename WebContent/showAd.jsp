<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:actionerror/>
<s:actionmessage/>

<s:div id="ad_%{annonceBean.id}">
<h2 class="ad-title"><s:property value="annonceBean.title"/></h2>
<s:div cssClass="ad-description">
<s:property value="annonceBean.description"/>
</s:div>
<s:div cssClass="ad-price">
<s:property value="annonceBean.price"/>
</s:div>
</s:div>


