<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="steps">
	<s:actionerror />
	<s:form>
		<div id="first-step">
			<h1>1. What da fuck?</h1>
			<h2>Localisation</h2>
			<s:select label="Choix du département" name="departement" headerKey="1"
				headerValue="-- Séléctionnez --" list="departements" />
		</div>
		<div id="second-step">
			<h1>2. This is real</h1>
			<h2>Communaute</h2>
			<s:select label="Choix de la communaute" name="communaute" headerKey="1"
				headerValue="-- Séléctionnez --" list="communautes" />


		</div>
		<div id="third-step">
			<h1>3. Time has come</h1>


		</div>
		<div id="fourth-step">
			<h1>4. Free at last</h1>


		</div>
	</s:form>
</div>
