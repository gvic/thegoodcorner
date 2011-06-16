<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="steps">
	<s:actionerror />
	<s:actionmessage />

	<s:form id="searchAdForm" action="list" theme="simple">
		<s:div id="first-step">
			<h1>1. Place</h1>
			<s:doubleselect name="regionId" list="regions" listValue="nom"
				label="%{getText('area.select.label')}" listKey="id" headerKey="-1"
				headerValue="%{getText('region.select')}"
				doubleName="departId" doubleList="departements"
				doubleListValue="nom" doubleHeaderKey="-1" doubleListKey="id"
				doubleHeaderValue="%{getText('departement.select')}">
			</s:doubleselect>
			
		</s:div>
		<s:div id="second-step">
			<h1>2. Community</h1>
			<s:select name="communauteId" key="ad.communautes"
				headerKey="-1" headerValue="%{getText('search.communaute')}" list="communities"
				listValue="denomination" listKey="id" />
		</s:div>
		<s:div id="third-step">
			<h1>3. Category</h1>
			<s:select name="categorieId" key="search.categorie" headerKey="-1"
				headerValue="%{getText('category.select')}" list="categories"
				listValue="nom" listKey="id" />
		</s:div>
		<s:div id="fourth-step">
			<h1>4. Keywords</h1>
			<s:textfield name="keywords" key="search.keywords"></s:textfield>
		</s:div>
		
		<sj:submit button="true" key="login.submit" validate="true" cssClass="float-right" />
	</s:form>
</div>

<script
	src="<s:url value='/template/js/index.init.js' includeParams='none' encode='false' />"
	type="text/javascript"></script>