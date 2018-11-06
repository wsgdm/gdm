<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#all").click(function() {
			$("[name=roles]:checkbox").attr("checked",$("#all").attr("checked")=="checked");
		});
		$("#reverse").click(function() {
			$("[name=roles]:checkbox").each(function () {
                $(this).attr("checked", !$(this).attr("checked"));
            });
		});
		
		var supplierId = $("#selectId").val();
			$.ajax({
			type:"post",
			url:"${path}/ajax_product_getProTypeName",
			data:{
				"productQuery.supplierId":supplierId,
			},
			async:false,
			dataType:"text",
			success:function(responseText){
				$("#productType").empty();
					$("#productType").append("<option value=''>----请-选-择----</option>");
					var jsonArr = $.parseJSON(responseText);
					for(var i = 0; i < jsonArr.length; i++){
						$("#productType").append("<option value='"+jsonArr[i].productTypeId+"'>"+jsonArr[i].name+"</option>")
					}
			},
			});
			$("#productType").val($("#leival").val());
		
		$("#selectId").change(function(){
			var supplierId = $(this).val();
			$.ajax({
			type:"post",
			url:"${path}/ajax_product_getProTypeName",
			data:{
				"productQuery.supplierId":supplierId,
			},
			async:false,
			dataType:"text",
			success:function(responseText){
				$("#productType").empty();
					$("#productType").append("<option value=''>----请-选-择----</option>");
					var jsonArr = $.parseJSON(responseText);
					for(var i = 0; i < jsonArr.length; i++){
						$("#productType").append("<option value='"+jsonArr[i].productTypeId+"'>"+jsonArr[i].name+"</option>")
					}
			},
			});
				
					
		});
	});
	
	function submitForm(){
		$("#frist").ajaxSubmit({
			async:false,
			dataType:"text",
			success:function(responseText){
				ret =  responseText;
			},
		});
		return ret;
	}

</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">商品管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form id="frist" action="${path}/ajax_product_update" method="post">
				<input type="hidden" id="leival"  value="<s:property value="product.productType.productTypeId"/>"/>
				<input type="hidden" name="product.productId"  value="<s:property value="product.productId"/>"/>
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				<div id="divtip" style="text-align: center;color: red;"></div>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">供&nbsp;应&nbsp;商</td>
				      <td width="32%">
				      <s:select id="selectId" list="#supplierList" class="kuan" name="product.productType.supplier.supplierId" listKey="supplierId" listValue="name" headerKey="" headerValue="----请-选-择----"></s:select>
				      </td>
				      <td width="18%"align="center">商品类别</td>
				      <td width="32%">
				      		<select name="product.productType.productTypeId"  id="productType" style="width:190px">
								<option value="">----请-选-择----</option>
							</select>
					  </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td align="center">商品名称</td>
				      <td>
				      <s:textfield id="ProName" type="text" size="25" name="product.name"/>
				      </td>
				      <td  align="center">产&nbsp;&nbsp;&nbsp;&nbsp;地</td>
				      <td >
				      	<s:textfield type="text" size="25" name="product.origin"/>
				      </td>
				    </tr>
				     <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">生产厂家</td>
				      <td>
				      	<s:textfield type="text" size="25" name="product.producer"/>
				      <td align="center">单&nbsp;&nbsp;&nbsp;&nbsp;位</td>
				      <td>
				      	<s:textfield type="text" size="25" name="product.unit"/>
					  </td>
				     </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">进货单价</td>
				      <td>
				      	<s:textfield type="text" size="25" name="product.inPrice"/>
					  </td>
				      <td align="center">销售单价</td>
				      <td>
				      	<s:textfield type="text" size="25" name="product.outPrice"/>
					  </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <!-- <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">体&nbsp;&nbsp;&nbsp;&nbsp;积</td>
				      <td>
				      	<input type="text" size="25"/>
					  </td>
				      <td align="center">&nbsp;</td>
				      <td>&nbsp;</td>
				    </tr> -->
				     <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				</table>
			</div>
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="${path}/images/content_bbg.jpg" /></div>
</div>
