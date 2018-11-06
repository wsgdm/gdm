<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("form:first").submit();
		});
		
		$("#add").click(function(){
			var diag = new Dialog();
			diag.Width = 850;
			diag.Height = 400;
			diag.ShowButtonRow=true;
			diag.Title = "添加员工";
			diag.URL = path + "/store_input";
			diag.OKEvent = function(){
				var win=diag.innerFrame.contentWindow;
				var ret = win.submitForm();
				if(ret == "success"){
					diag.close();
					window.location.href = "${path}/emp_list";
				}
			};
			diag.show();
			});
	});

</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">仓库管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path}/store_list" method="post"> 
		<input type="hidden" id="pageNo"  name="storeQuery.pageNo" value="<s:property value="#page.pageNo"/>">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="10%">仓库名称:</td>
						<td width="20%"><s:textfield  type="text" size="20" name="storeQuery.name"/></td>
						<td>管&nbsp;理&nbsp;员:</td>
						<td><s:textfield  type="text" size="20" name="storeQuery.empName"/></td>
						<td width="10%">仓库地址:</td>
						<td><s:textfield  type="text" size="20" name="storeQuery.address"/></td>
						<td>
							<a id="add"><img src="${path}/images/can_b_02.gif" border="0" /> </a>
						</td>
						<td>
						<a id="query"><img src="${path}/images/can_b_01.gif" border="0" /> </a>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="10%" height="30">仓库名称</td>
						<td width="10%">仓库管理员</td>
						<td width="44%">仓库地址</td>
						<td width="16%">操作</td>
					</tr>
					<s:iterator value="#page.list" var="store">
						<tr align="center" bgcolor="#FFFFFF">
							<td width="13%" height="30"><s:property value="#store.name"/></td>
							<td><s:property value="#store.stockeEmp.name"/></td>
							<td align="left"><s:property value="#store.address"/></td>
							<td>
								<img src="${path}/images/icon_3.gif" /> 
								<span style="line-height:12px; text-align:center;"> 
									<a href="./input.jsp" class="xiu">修改</a> 
								</span> 
								<img src="${path}/images/icon_04.gif" /> 
								<span style="line-height:12px; text-align:center;"> 
									<a href="javascript:void(0)" class="xiu" onclick="showMsg('是否删除该项数据？',318)">删除</a>
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
