<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="account-ajax">
</div>

<div id="account-menu">
	<div class="thead"><s:text name="account.menu" /></div>
	<ul>
		<li>
		<s:url action="updateAccount_input" id="infosurl">
		</s:url>
		<sj:a href="%{infosurl}" targets="account-ajax" >
			<s:text name="account.infos" />
		</sj:a>
		</li>
		<li>
		<s:url action="list" id="listurl"  >
			<s:param name="userId" value="%{#session.userId}" ></s:param>
		</s:url>
		<sj:a href="%{listurl}" targets="account-ajax" >
			<s:text name="account.show.ads" />
		</sj:a>
		</li>
		<li>
		<s:url action="addCommunaute_input" id="addcomurl"></s:url>
		<sj:a href="%{addcomurl}" targets="account-ajax">
			<s:text name="account.add.communaute" />
		</sj:a>
		</li>
		<li>
		<s:url action="changePassword_input" id="changeurl"></s:url>
		<sj:a href="%{changeurl}" targets="account-ajax">
			<s:text name="account.changePassword" />
		</sj:a>
		</li>
	</ul>
</div>
