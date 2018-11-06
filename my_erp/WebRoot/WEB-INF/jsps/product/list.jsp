<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("form:first").submit();
		});
		
		
			var supplierId = $("#selectId").val();
			$.ajax({
			type:"post",
			url:"${path}/ajax_product_getProName",
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
						$("#productType").append("<option value='"+jsonArr[i].productId+"'>"+jsonArr[i].name+"</option>")
					}
			},
			});
			$("#productType").val($("#leival").val());
		
		
		$("#selectId").change(function(){
			var supplierId = $(this).val();
			$.ajax({
			type:"post",
			url:"${path}/ajax_product_getProName",
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
						$("#productType").append("<option value='"+jsonArr[i].productId+"'>"+jsonArr[i].name+"</option>")
					}
			},
			});
				
					
		});
		
		$("#addPro").click(function(){
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 400;
		diag.ShowButtonRow=true;
		diag.Title = "添加商品";
		diag.URL = path + "/product_input";
		diag.OKEvent = function(){
			var win=diag.innerFrame.contentWindow;
			var ret = win.submitForm();
			if(ret == "OK"){
				diag.close();
				window.location.href = "${path}/product_list";
			}
		};
		diag.show();
		})
	});
	
	function delete1(productId){
		Dialog.confirm('确定要删除吗',function(){
			window.location.href = path + "/product_delete?product.productId=" + productId;
		});
	}
	
	function update(productId){
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 400;
		diag.ShowButtonRow=true;
		diag.Title = "修改商品";
		diag.URL = path + "/product_update?product.productId=" + productId;
		diag.OKEvent = function(){
			var win=diag.innerFrame.contentWindow;
			var ret = win.submitForm();
			if(ret == "OK"){
			Dialog.alert("商品修改成功",function(){
				diag.close();
				window.location.href = "${path}/product_list";
				})
			}
			
		};

		diag.show();
	}

</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">商品管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path}/product_list" method="post"> 
		<input type="hidden" id="pageNo"  name="productQuery.pageNo" value="<s:property value="#page.pageNo"/>">
		<input type="hidden" id="leival" value="<s:property value="productQuery.productId"/>"/>"
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td>供应商:</td>
						<td>
							<s:select id="selectId" list="#supplierList" class="kuan" name="productQuery.supplierId" listKey="supplierId" listValue="name" headerKey="" headerValue="----请-选-择----"></s:select>
						</td>
						<td height="30">商品名</td>
						<td>
							<select name="productQuery.productId"  id="productType" style="width:190px">
								<option value="">----请-选-择----</option>
							</select></td>
						</td>
						<td>生 产厂家</td>
						<td><s:textfield type="text" size="14" name="productQuery.producer"/></td>
						<td>单位</td>
						<td><s:textfield type="text" size="14" name="productQuery.unit"/></td>
						<td width="70"><a id="addPro"><img src="${path}/images/can_b_02.gif" border="0" /> </a></td>
					</tr>
					<tr>
						<td height="30">进货价格</td>
						<td><s:textfield type="text" size="14" name="productQuery.minInPrice"/></td>
						<td>到</td>
						<td><s:textfield type="text" size="14" name="productQuery.maxInPrice"/></td>
						<td height="30">销售价格</td>
						<td><s:textfield type="text" size="14" name="productQuery.minOutPrice"/></td>
						<td>到</td>
						<td><s:textfield type="text" size="14" name="productQuery.maxOutPrice"/></td>
						<td><a id="query"> <img src="${path}/images/can_b_01.gif" border="0" /> </a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="12%" height="30">供应商</td>
						<td width="12%">商品名</td>
						<td width="12%">生产厂家</td>
						<td width="12%">产地</td>
						<td width="12%">进货价格</td>
						<td width="12%">销售价格</td>
						<td width="12%">单位</td>
						<td width="16%">操作</td>
					</tr>
					<s:iterator value="#page.list" var="pro">
						<tr align="center" bgcolor="#FFFFFF">
							<td width="13%" height="30"><s:property value="#pro.productType.supplier.name"/></td>
							<td><s:property value="#pro.name"/></td>
							<td><s:property value="#pro.producer"/></td>
							<td><s:property value="#pro.origin"/></td>
							<td align="right"><s:property value="#pro.inPrice"/>&nbsp;元&nbsp;</td>
							<td align="right"><s:property value="#pro.outPrice"/>&nbsp;元&nbsp;</td>
							<td><s:property value="#pro.unit"/></td>
							<td>
								<img src="${path}/images/icon_3.gif" /> 
								<span style="line-height:12px; text-align:center;"> 
									<a href="javascript:void(0)" onclick="update(<s:property value="#pro.productId"/>)" class="xiu">修改</a> 
								</span> 
								<img src="${path}/images/icon_04.gif" /> 
								<span style="line-height:12px; text-align:center;"> 
									<a href="javascript:void(0)" class="xiu" onclick="delete1(<s:property value="#pro.productId"/>)">删除</a>
								</span>
							</td>
						</tr>
						</s:iterator>
				</table>
				<%@ include file="../tools/paging.jsp" %>
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
