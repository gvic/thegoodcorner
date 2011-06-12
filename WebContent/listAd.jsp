<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<s:url id="remoteurl" action="listAdJson" />
<sjg:grid
     id="gridtable"
     caption="Listing"
     dataType="json"
     href="%{remoteurl}"
     pager="true"
     gridModel="gridModel"
     rowNum="20" 
     width="980"
 >
<!--     <sjg:gridColumn name="id" index="id" title="ID" formatter="integer" sortable="false" />-->
     <sjg:gridColumn name="title" index="title" title="Title" sortable="true" />
     <sjg:gridColumn name="date_de_publication" index="date_de_publication" title="Published date" formatter="date" sortable="true"/>
     <sjg:gridColumn name="price" index="price" title="Price" formatter="currency" sortable="true"/>
 </sjg:grid>