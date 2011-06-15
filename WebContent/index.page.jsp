<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="steps">
	<s:actionerror />
	<s:actionmessage />

	<s:form id="searchAdForm" theme="xhtml" action="list">
		<div id="first-step">
			<h1>1. What da fuck?</h1>
			<h2>Place</h2>
			<s:doubleselect name="regionId" list="regions" listValue="nom"
				label="%{getText('area.select.label')}" listKey="id" headerKey="-1"
				headerValue="%{getText('region.select')}" value="%{#session.user.region.id}"
				doubleName="departId" doubleList="departements"
				doubleListValue="nom" doubleHeaderKey="-1" doubleListKey="id"
				doubleHeaderValue="%{getText('departement.select')}"
				doubleValue="%{#session.user.departement.id}">
			</s:doubleselect>
			<s:textfield name="codePostal" key="search.codePostal"
				value="%{#session.user.codePostal}" />
		</div>
		<div id="second-step">
			<h1>2. This is real</h1>
			<h2>Community</h2>
			<s:select name="communauteId" key="ad.communautes"
				headerKey="search.communaute" list="communities"
				listValue="denomination" listKey="id" multiple="true" />
		</div>
		<div id="third-step">
			<h1>3. Time has come</h1>
			<h2>Category</h2>
			<s:select name="categorieId" key="search.categorie" headerKey="-1"
				headerValue="%{getText('category.select')}" list="categories"
				listValue="nom" listKey="id" />
		</div>
		<div id="fourth-step">
			<h1>4. Free at last</h1>
			<h2>Key words</h2>
			<s:textfield name="keywords" key="search.keywords"></s:textfield>
		</div>

	</s:form>
</div>

<script
	src="<s:url value='/template/js/index.init.js' includeParams='none' encode='false' />"
	type="text/javascript"></script>