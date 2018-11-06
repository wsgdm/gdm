<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("form:first").submit();
		});
		
		
			$("#addsupplier").click(function(){
			var diag = new Dialog();
			diag.Width = 850;
			diag.Height = 400;
			diag.ShowButtonRow=true;
			diag.Title = "添加供应商";
			diag.URL = "${path}/productType_input";
			diag.OKEvent = function(){
				var win=diag.innerFrame.contentWindow;
				var res = win.proSubmit();
				if(res == "OK"){
					Dialog.alert("商品添加成功",function(){
						diag.close();
						window.location.href = path + "/productType_list";
					});
				}
			};
			diag.show();
			
		})


			var supplierId = $("#selectId").val();
			$.ajax({
			type:"post",
			url:"${path}/ajax_productType_getProName",
			data:{
				"productTypeQuery.supplierId":supplierId,
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
			})
			$("#productType").val($("#leival").val());


		
		$("#selectId").change(function(){
			var supplierId = $(this).val();
			
			$.ajax({
			type:"post",
			url:"${path}/ajax_productType_getProName",
			data:{
				"productTypeQuery.supplierId":supplierId,
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
			})
				
					
		});
		

	});
	
	function delete1(productTypeId){
		Dialog.confirm('确定要删除吗',function(){
			window.location.href = path + "/productType_delete?productType.productTypeId="+productTypeId;
									 
		});
	}
	function update(producTypeId){
			var diag = new Dialog();
			diag.Width = 850;
			diag.Height = 400;
			diag.ShowButtonRow=true;
			diag.Title = "修改供应商";
			diag.URL = "${path}/productType_update?productType.productTypeId="+producTypeId;
			diag.OKEvent = function(){
				var win=diag.innerFrame.contentWindow;
				var res = win.proSubmit();
				if(res == "OK"){
					Dialog.alert("修改成功",function(){
						diag.close();
						window.location.href = path + "/productType_list";
					});
				}
			};
			diag.show();
	}

	
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">商品类别管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path}/productType_list" method="post">
		<input type="hidden" id="pageNo"  name="productTypeQuery.pageNo" value="<s:property value="#page.pageNo"/>">
		<input type="hidden" id="leival" value="<s:property value="productTypeQuery.productTypeId"/>"/>"
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="68" height="30">&nbsp;</td>
						<td width="123">&nbsp;</td>
						<td width="62">供应商:</td>
						<td width="130">
						<s:select  id="selectId" list="#supplierList"  class="kuan"  name="productTypeQuery.supplier.supplierId" listKey="supplierId" listValue="name" headerKey="" headerValue="----请-选-择----"></s:select>
						</td>
						<td width="60">类别名称:</td>
						<td width="149">
							<select name="productTypeQuery.productTypeId"  id="productType" style="width:190px">
								<option value="">----请-选-择----</option>
							</select></td>
						<td width="70"><a id="query"> <img
								src="${path}/images/can_b_01.gif" border="0" /> </a></td>
						<td width="70"><a id="addsupplier"><img
								src="${path}/images/can_b_02.gif" border="0" /> </a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="30%" height="30">供应商</td>
						<td width="30%">类别名称</td>
						<td width="40%">操作</td>
					</tr>
					<s:iterator value="#page.list" var="propage">
						<tr align="center" bgcolor="#FFFFFF">
							<td width="30%" height="30"><s:property value="#propage.supplier.name"/></td>
							<td><s:property value="#propage.name"/></td>
							<td>
								<img src="${path}/images/icon_3.gif" /> 
								<span style="line-height:12px; text-align:center;"> 
									<a href="javascript:void(0)" class="xiu" onclick="update(<s:property value="#propage.productTypeId"/>)">修改</a> 
								</span> 
								<img src="${path}/images/icon_04.gif" /> 
								<span style="line-height:12px; text-align:center;"> 
									<a href="javascript:void(0)" class="xiu" onclick="delete1(<s:property value="#propage.productTypeId"/>)">删除</a>
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
