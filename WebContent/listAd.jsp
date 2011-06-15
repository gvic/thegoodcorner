<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>

<s:a href="javascript:history.go(-1)"><s:text name="history.back"></s:text></s:a>

<s:url id="remoteurl" action="listAdJson" />
<sjg:grid
     id="gridtable"
     dataType="json"
     href="%{remoteurl}"
     pager="true"
     gridModel="gridModel"
     rowNum="20"  
     hoverrows="false"  
     width="980"
 >
     <sjg:gridColumn name="date_de_publication" index="date_de_publication" title="%{getText('list.date.published')}" formatter="date" formatoptions="{newformat : 'd/m H:i', srcformat : 'Y-m-d H:i:s'}" sortable="true"/>
     <sjg:gridColumn name="imgPaths" title="%{getText('list.img')}" formatter="formatImg" sortable="false"/>
     <sjg:gridColumn name="title" index="title" title="%{getText('list.title')}" formatter="showlink" formatoptions="{baseLinkUrl:'showAd.action', idName:'adId'}"  sortable="true" />
     <sjg:gridColumn name="price" index="price" title="%{getText('list.price')}" formatter="currency" formatoptions="{prefix: '£'}" sortable="true"/>
     <sjg:gridColumn name="region.nom" title="%{getText('list.divers')}" formatter="group" sortable="false"/>
     <sjg:gridColumn name="departement" title="%{getText('list.departement')}" sortable="false" hidden="true"/>
     <sjg:gridColumn name="categorie" title="%{getText('list.categorie')}" sortable="false" hidden="true"/>
 </sjg:grid>

<!--Custom Formatter -->
<s:url id="adurl" action="showAd" />
<script type="text/javascript">
        function formatImg(cellvalue, options, rowObject) {
            if (cellvalue == null) {
				return "";
            } else {
                var file = cellvalue[0].path.split(".");
				var format = file[1];
				var nom = file[0];
                return "<a href='<s:property value="adurl"/>?adId="+options.rowId+"' ><img src='"+nom+"_thumb2."+format+"' alt='' /></a>";
            }
        }
        function group(cellvalue, options, rowObject) {
            if (cellvalue == null) {
				return "";
            } else {
                var s = cellvalue+"<br/>";
                if (rowObject.departement != null) {
                	s += rowObject.departement.nom+"<br/>";
                } 
                if (rowObject.categorie != null){
                	s += rowObject.categorie.nom;
                }
                return s;
            }
        }
</script>