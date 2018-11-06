<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			$("form:first").submit();
		});
	});
	
	function update(supplierId){
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 400;
		diag.ShowButtonRow=true;
		diag.Title = "供应商修改";
		diag.URL = path + "/supplier_update?supplier.supplierId="+supplierId;
		diag.OKEvent = function(){
			var win=diag.innerFrame.contentWindow;
			var ret = win.SubmitForm();
			if(ret == "OK"){
				diag.close();
				window.location.href = "${path}/supplier_list";
			}
		};
		diag.show();	
	}
	
		function save(){
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 400;
		diag.ShowButtonRow=true;
		diag.Title = "供应商添加";
		diag.URL = path + "/supplier_input";
		diag.OKEvent = function(){
			var win=diag.innerFrame.contentWindow;
			var ret = win.SubmitForm();
			if(ret == "OK"){
				diag.close();
				window.location.href = "${path}/supplier_list";
			}
		};
		diag.show();	
	}
	
	function delete1(supplierId){
		Dialog.confirm('确定要删除吗',function(){
			window.location.href = "${path}/supplier_delete?supplier.supplierId=" + supplierId;
		});
	}

</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">供应商管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path}/supplier_list" method="post">
		<input type="hidden" id="pageNo"  name="supplierQuery.pageNo" value="<s:property value="#page.pageNo"/>">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="28%" height="30">&nbsp;</td>
						<td width="8%">供应商:</td>
						<td width="17%"><s:select cssClass="kuan" list="#list" name="supplierQuery.supplierId" listKey="supplierId"  listValue="name" headerKey="" headerValue="----请-选-择----" ></s:select>
						<td width="8%">联系人:</td>
						<td width="17%"><s:textfield type="text" size="18" name="supplierQuery.contact"/>
						<td width="12%">
							<a id="query"><img src="${path}/images/can_b_01.gif" border="0" /> </a></td>
					</tr>
					<tr>
						<td height="30">&nbsp;</td>
						<td>电话:</td>
						<td><s:textfield type="text" size="18" name="supplierQuery.tel"/></td>
						<td>提货方式：</td>
						<td>
						<s:select list="#{'1':'送货','2':'自提' }" name="supplierQuery.needs" cssClass="kuan" headerKey="" headerValue="----请-选-择----"></s:select>
						</td>
						<td>
							<a href="#"><img src="${path}/images/can_b_02.gif" onclick="save()" border="0" /> </a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="20%" height="30">供应商</td>
						<td width="20%">地址</td>
						<td width="20%">联系人</td>
						<td width="12%">电话</td>
						<td width="12%">送货方式</td>
						<td width="16%">操作</td>
					</tr>
					<s:iterator value="#page.list" var="supplierPage">
					<tr align="center" bgcolor="#FFFFFF">
						<td width="13%" height="30"><s:property value="#supplierPage.name"/></td>
						<td><s:property value="#supplierPage.address"/></td>
						<td><s:property value="#supplierPage.contact"/></td>
						<td><s:property value="#supplierPage.tel"/></td>
						<td><s:property value="#supplierPage.needs==1?'送货':'自提'"/> </td>
						
						<td>
							<img src="${path}/images/icon_3.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a href="#" onclick="update(<s:property value="#supplierPage.supplierId"/>)" class="xiu">修改</a>
							</span> 
							<img src="${path}/images/icon_04.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a href="javascript:void(0)" class="xiu" onclick="delete1(<s:property value="#supplierPage.supplierId"/>)">删除</a>
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
