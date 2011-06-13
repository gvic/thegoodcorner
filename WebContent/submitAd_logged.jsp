<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:actionmessage />
<s:actionerror />
<s:form id="submitAdForm" theme="xhtml" action="populateAdLogged_execute"
	enctype="multipart/form-data" method="POST">

<%--	<s:select name="regionId" key="region.select" headerKey="-1"--%>
<%--		headerValue="Select Region" list="regions" listValue="nom"--%>
<%--		listKey="id" ></s:select>--%>
<%--	<s:select name="departementId" key="departement.select" headerKey="-1"--%>
<%--		headerValue="Select Department" list="departements" listValue="nom"--%>
<%--		listKey="id" ></s:select>--%>
		
	<s:doubleselect name="regionId" list="regions" listValue="nom" listKey="id"
		doubleName="departementId" doubleList="departements" doubleListValue="nom" doubleListKey="id">
	</s:doubleselect>

	<s:select name="communitiesId" key="ad.communautes"
		headerKey="communaute.select" list="communities"
		listValue="denomination" listKey="id" multiple="true" />
	<s:select name="categorieId" key="ad.categorie" headerKey="-1"
		headerValue="Select Categorie" list="categories" listValue="nom"
		listKey="id" />
	<s:textfield name="adBean.title" key="ad.title" />
	<s:textarea name="adBean.description" cols="50" rows="10"
		key="ad.description" />
	<s:textfield name="adBean.price" key="ad.price" />

	<s:file name="upload" key="ad.photo"
		cssClass="disabled" />
	<s:file name="upload" key="ad.photo"
		cssClass="disabled" />
	<!-- 	<s:file name="upload" label="File (3)" key="ad.photo" cssClass="disabled cache"/>-->
	<!-- 	<s:file name="upload" label="File (4)" key="ad.photo" cssClass="disabled cache"/>-->
	<!-- 	<s:file name="upload" label="File (5)" key="ad.photo" cssClass="disabled cache"/>-->

	<sj:submit button="true" key="generic.submit" validate="true"
		cssClass="float-right" />
</s:form>
