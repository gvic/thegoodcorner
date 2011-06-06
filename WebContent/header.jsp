<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="title">
	<a href="">The <span class="good">Good</span> Corner</a>
</div>
<s:actionmessage/>
<div id="onglet-wrapper">
	<div id="nav-login">
		<s:a action="SignUp_input" >
			<div id="onglet-signup" class="onglet">
				Sign Up !
			</div>
		</s:a>
		<!--AJAX Login popup!-->
		<s:url id="ajaxTest" value="/Login_input.action"/>
		<sj:a id="link1" href="%{ajaxTest}" targets="login" effect="scale" effectMode="show" effectDuration="300"> 
			<div id="onglet-login" class="onglet">
				Log In
			</div>
		</sj:a>
	</div>
	<div id="nav-steps">		
		<div id="onglet-4step" class="onglet" title="#fourth-step">
			Fourth Step
		</div>
		<div id="onglet-3step" class="onglet" title="#third-step">
			Third Step
		</div>
		<div id="onglet-2step" class="onglet" title="#second-step">
			Second Step
		</div>
		<div id="onglet-1step" class="onglet" title="#first-step">
			First Step
		</div>
	</div>
</div> 
<div id="login" style="display:none;"></div>