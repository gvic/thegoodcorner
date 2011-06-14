<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:actionmessage />
<s:actionerror />
<s:form id="submitAdForm" theme="xhtml" action="populateAdUnlogged_execute"
	enctype="multipart/form-data" method="POST">

	<fieldset>	
		<legend><s:text name="ad.form"></s:text></legend>
		<!-- 	En cas de probleme celle ci marche mais pas de couleur pour diff?rencier regions/departs -->
		<s:doubleselect
			name="regionId" list="regions" listValue="nom"
			listKey="id" headerKey="-1" headerValue="%{getText('select.region')}"
			doubleName="departementId" doubleList="departements" doubleListValue="nom"
			doubleListKey="id" doubleHeaderKey="-1" doubleHeaderValue="%{getText('select.depart')}">
		</s:doubleselect>
		<%-- 	<s:select key="adBean.departement" list="departements"></s:select> --%>
		
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
		<s:file name="uploads" label="File (1)" key="ad.photo"
			cssClass="disabled" />
		<s:file name="uploads" label="File (2)" key="ad.photo"
			cssClass="disabled" />
			<s:file name="upload" label="File (3)" key="ad.photo" cssClass="disabled cache"/>
			<s:file name="upload" label="File (4)" key="ad.photo" cssClass="disabled cache"/>
			<s:file name="upload" label="File (5)" key="ad.photo" cssClass="disabled cache"/>
	</fieldset>

<s:if test="%{#session.user == null}">
	<fieldset>	
		<legend><s:text name="user.form"></s:text></legend>
		<s:textfield name="userBean.login" key="login.username" />
		<s:textfield name="userBean.email" key="login.email" />
		<s:password name="userBean.md5_mdp" key="login.password" />
		<s:password name="confirmPassword" key="login.password.again" />
	</fieldset>
</s:if>

	<sj:submit button="true" key="generic.submit" validate="true"
		cssClass="float-right" />
</s:form>
