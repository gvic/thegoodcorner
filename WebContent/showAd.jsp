<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:actionerror/>
<s:actionmessage/>

<s:div id="annonce-%{annonceBean.id}" cssClass="annonce">
<h2 class="annonce-title"><s:property value="annonceBean.title"/></h2>
<div class="annonce-description">
<s:property value="annonceBean.description"/>
</div>
<div class="annonce-price">
<s:property value="annonceBean.price"/>
</div>
<s:iterator value="annonceBean.imgPaths">
	<img alt="<s:property value="annonceBean.title"/>" src="<s:property value="path"/>" />
</s:iterator>
</s:div>


