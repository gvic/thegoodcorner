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
     <sjg:gridColumn name="date_de_publication" index="date_de_publication" title="Published date" formatter="date" formatoptions="{newformat : 'd/m H:i', srcformat : 'Y-m-d H:i:s'}" sortable="true"/>
     <sjg:gridColumn name="pathsToImg" index="pathsToImg" title="Image" formatter="formatImg" sortable="false"/>
     <sjg:gridColumn name="title" index="title" title="Title" formatter="showlink" formatoptions="{baseLinkUrl:'showAd.action', idName:'adId'}"  sortable="true" />
     <sjg:gridColumn name="price" index="price" title="Price" formatter="currency" formatoptions="{prefix: '£'}" sortable="true"/>
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