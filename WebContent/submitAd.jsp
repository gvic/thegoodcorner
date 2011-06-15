<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:if test="#session.user == null">
<span class="tips"><s:text name="tips.forgot.login"></s:text><span class="rotate90">-></span></span>
</s:if>
<s:actionmessage />
<s:actionerror />
<s:form id="submitAdForm" theme="xhtml" action="populateAd_execute"
	enctype="multipart/form-data" method="POST">

	<!-- 	En cas de probleme celle ci marche mais pas de couleur pour diff?rencier regions/departs -->
	<s:doubleselect
		name="regionId" list="regions" listValue="nom" key="" label="%{getText('area.select.label')}"
		listKey="id" headerKey="-1" headerValue="%{getText('region.select')}" value="regionId"
		doubleName="departementId" doubleList="departements" doubleListValue="nom"
		doubleListKey="id" doubleHeaderKey="-1" doubleHeaderValue="%{getText('department.select')}"
		doubleValue="%{departementId}">
	</s:doubleselect>
	<%-- 	<s:select key="adBean.departement" list="departements"></s:select> --%>
	
	<s:select name="communitiesId" key="ad.communautes"
		headerKey="communaute.select" list="communities"
		listValue="denomination" listKey="id" multiple="true" />
	<s:select name="categorieId" key="ad.categorie" headerKey="-1"
		headerValue="%{getText('category.select')}" list="categories" listValue="nom"
		listKey="id" />
	<s:textfield name="adBean.title" key="ad.title" />
	<s:textarea name="adBean.description" cols="50" rows="10"
		key="ad.description" />
	<s:textfield name="adBean.price" key="ad.price" />
	<s:file name="uploads" key="ad.photo"
		cssClass="disabled" />
	<s:file name="uploads" cssClass="disabled" />
	<s:file name="uploads" cssClass="disabled cache"/>
	<s:file name="uploads" cssClass="disabled cache"/>
	<s:file name="uploads" cssClass="disabled cache"/>

<s:if test="%{#session.user == null}">
	<s:textfield name="userBean.login" key="login.username" />
	<s:textfield name="userBean.email" key="login.email" />
	<s:password name="userBean.md5_mdp" key="login.password" />
	<s:password name="confirmPassword" key="login.password.again" />
</s:if>

	<sj:submit button="true" key="generic.submit" validate="true"
		cssClass="float-right" />
</s:form>
