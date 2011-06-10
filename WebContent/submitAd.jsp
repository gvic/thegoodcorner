<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:actionerror />
<s:form id="submitAdForm" theme="xhtml" action="populateAd_processDatas"
	enctype="multipart/form-data" method="POST">

	<!-- 	En cas de probleme celle ci marche mais pas de couleur pour différencier regions/departs -->
	<%-- 	<s:select key="Place" name="geographicAreaSubmitted" --%>
	<%-- 		list="geographicAreas"></s:select> --%>

	<s:select list="geographicAreas" key="geographicAreaSubmitted">
		<s:iterator value="geographicAreas">
			<option value="<s:property value="value"/>"
				style="background-color:<s:property value="key"/>"></option>
		</s:iterator>
	</s:select>

	<s:select key="Community" name="communitiesSubmitted"
		list="communities" multiple="true"></s:select>
	<s:select key="Category" list="categories" name="categorySubmitted"></s:select>
	<s:textfield name="title" key="Title" cssClass="active" />
	<s:textarea cols="50" rows="10" name="description" key="Description"
		cssClass="active" />
	<s:textfield name="price" key="Price" cssClass="active" />
	<s:file name="image" label="File" />
	<s:textfield name="caption" label="Caption" />
	<sj:submit button="true" value="login.submit" validate="true"
		indicator="indicator" cssClass="float-right" />
</s:form>
<img id="indicator"  src="/template/new_xhtml/css/images/indicator.gif" alt="Loading..." style="display:none"/>