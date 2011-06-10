<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:actionmessage/>
<s:actionerror />
<s:form id="submitAdForm" theme="xhtml" action="populateAd_submit">
<!--	enctype="multipart/form-data" method="POST">-->

	<!-- 	En cas de probleme celle ci marche mais pas de couleur pour différencier regions/departs -->
	<s:select name="regionId" key="region.select" headerKey="-1" headerValue="Select Region" list="regions" listValue="nom" listKey="id"></s:select>
<%-- 	<s:select key="adBean.departement" list="departements"></s:select> --%>


	<%-- 	<s:select list="regions" key="adBean.region"> --%>
	<%-- 		<s:iterator value="regions"> --%>
	<%-- 			<option value="<s:property value="value"/>" --%>
	<%-- 				style="background-color:<s:property value="key"/>"></option> --%>
	<%-- 		</s:iterator> --%>
	<%-- 	</s:select> --%>

 	<s:select name="communitiesId" key="ad.communautes" headerKey="communaute.select" list="communities" listValue="denomination" listKey="id" multiple="true" />
 	<s:select name="categorieId" key="ad.categorie" headerKey="-1"  headerValue="Select Categorie" list="categories" listValue="nom" listKey="id" />
 	<s:textfield name="adBean.title" key="ad.title" /> 
 	<s:textarea name="adBean.description" cols="50" rows="10" 
 		 key="ad.description" /> 
 	<s:textfield name="adBean.price" key="ad.price" /> 
<%-- 	<s:file name="image" label="File" /> --%>
<%-- 	<s:textfield name="caption" label="Caption" /> --%>
	<sj:submit button="true" value="generic.submit" validate="true"
		indicator="indicator" cssClass="float-right" />
</s:form>
<img id="indicator"  src="/template/new_xhtml/css/images/indicator.gif" alt="Loading..." style="display:none"/>
