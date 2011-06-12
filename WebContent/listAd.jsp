<%@ page language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<s:url id="remoteurl" action="listAdJson" />
<sjg:grid
     id="gridtable"
     dataType="json"
     href="%{remoteurl}"
     pager="true"
     gridModel="gridModel"
     rowNum="20" 
     width="980" 
     hoverrows="false"  
 >
     <sjg:gridColumn name="id" index="id" title="ID" sortable="false"/>
     <sjg:gridColumn name="date_de_publication" index="date_de_publication" title="%{getText('list.date.published')}" formatter="date" formatoptions="{newformat : 'd/m H:i', srcformat : 'Y-m-d H:i:s'}" sortable="true"/>
     <sjg:gridColumn name="imgPaths" title="%{getText('list.img')}" formatter="formatImg" sortable="false"/>
     <sjg:gridColumn name="title" index="title" title="%{getText('list.title')}" formatter="showlink" formatoptions="{baseLinkUrl:'showAd.action', idName:'adId'}"  sortable="true" />
     <sjg:gridColumn name="price" index="price" title="%{getText('list.price')}" formatter="currency" formatoptions="{prefix: '£'}" sortable="true"/>
 </sjg:grid>

<!--Custom Formatter -->
<s:url id="adurl" action="showAd" />
<script type="text/javascript">
        function formatImg(cellvalue, options, rowObject) {
            if (cellvalue == null) {
				return "";
            } else {
                return "<a href='<s:property value="adurl"/>?adId="+options.rowId+"' ><img src='" + cellvalue + "' alt='' /></a>";
            }
        }
</script>