<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:actionerror />
<s:form id="submitAdForm" theme="xhtml" action="populateAd_processDatas"
	enctype="multipart/form-data" method="POST">

	<!-- 	En cas de probleme celle ci marche mais pas de couleur pour différencier regions/departs -->
	<s:select key="adBean.region" list="regions"></s:select>
	<s:select key="adBean.departement" list="departements"></s:select>


	<%-- 	<s:select list="regions" key="adBean.region"> --%>
	<%-- 		<s:iterator value="regions"> --%>
	<%-- 			<option value="<s:property value="value"/>" --%>
	<%-- 				style="background-color:<s:property value="key"/>"></option> --%>
	<%-- 		</s:iterator> --%>
	<%-- 	</s:select> --%>

	<s:select key="adBean.communautes" list="communities" multiple="true"></s:select>
	<s:select key="adBean.categorie" list="categories"></s:select>
	<s:textfield key="adBean.title" name="title" cssClass="active" />
	<s:textarea key="adBean.description" cols="50" rows="10"
		name="description" cssClass="active" />
	<s:textfield key="adBean.price" name="price" cssClass="active" />
	<s:file name="image" label="File" />
	<s:textfield name="caption" label="Caption" />
	<sj:submit button="true" value="login.submit" validate="true"
		indicator="indicator" cssClass="float-right" />
</s:form>