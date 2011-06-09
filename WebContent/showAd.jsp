<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:actionerror/>
<s:actionmessage/>

<s:div id="ad_%{annonceBean.id}">
<h2 class="ad-title"><s:property value="annonceBean.title"/></h2>
<div class="ad-description">
<s:property value="annonceBean.description"/>
</div>
<div class="ad-price">
<s:property value="annonceBean.price"/>
</div>
</s:div>


