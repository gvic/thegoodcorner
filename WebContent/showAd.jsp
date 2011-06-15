<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:actionerror/>
<s:actionmessage/>

<s:div id="annonce-%{annonceBean.id}" cssClass="annonce">
<h2 class="annonce-title"><s:property value="annonceBean.title"/></h2>
<span class="date"><s:date name="annonceBean.date_de_publication" format="%{getText('i18n.date.format')}"/></span>
<div class="annonce-description">
<s:property value="annonceBean.description"/>
</div>
<div class="annonce-price">
<s:text name="i18n.currency">
	<s:param><s:property value="annonceBean.price" /></s:param>
</s:text>
</div>
<s:iterator value="annonceBean.imgPaths">
	<img alt="<s:property value="annonceBean.title"/>" src="<s:property value="path"/>" />
</s:iterator>
</s:div>

<s:div cssClass="menu-box">
<div class="thead"><s:property value="annonceBean.user.prenom"/> (<s:property value="annonceBean.user.login"/>)</div>
<s:a><s:text name="user.send.mail"></s:text></s:a>
</s:div>
