<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="account-ajax">
<span class="tips">
<s:text name="account.manage"></s:text>
</span>
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
		<li>
		<s:url action="list" id="listurl" includeParams="none">
			<s:param name="userId" value="%{#session.user.id}" ></s:param>
		</s:url>
		<s:a href="%{listurl}"  >
			<s:text name="account.show.ads" />
		</s:a>
		</li>
	</ul>
</div>
