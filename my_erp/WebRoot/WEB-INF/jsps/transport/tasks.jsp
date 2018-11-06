<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("form:first").submit();
		});
		
		var orderState = $("#orderState").val();
		if(orderState == "2"){
			$("#li1").css("background","#8ECC3D");
		}
		if(orderState == "3"){
			$("#li2").css("background","#8ECC3D");
		}
	});
	
			function viewOrderDetail(orderId){
			var diag = new Dialog();
			diag.Width = 850;
			diag.Height = 400;
			diag.ShowButtonRow=true;
			diag.Title = "查看订单详情";
			diag.URL = "${path}/transportOrder_taskDetailbuying?orderModel.orderId="+orderId;
			diag.OKEvent = function(){
				var win = diag.innerFrame.contentWindow;
				//调用提交表单的方法获得返回值
				var result = win.getOrderProduct();
				//如果成功页面要刷新
				if(result == "success"){
					diag.close();
					window.location.href = "${path }/transportOrder_tasks?orderModelQuery.orderType=2&orderModelQuery.orderState=2";
				}
			};
			diag.show();
			diag.okButton.value="确认去取货";
			}
			
		function viewOrderDetail1(orderId){
			var diag = new Dialog();
			diag.Width = 850;
			diag.Height = 400;
			diag.ShowButtonRow=true;
			diag.Title = "查看订单详情";
			diag.URL = "${path}/transportOrder_taskDetailbuying?orderModel.orderId="+orderId;
			diag.OKEvent = function(){
				var win = diag.innerFrame.contentWindow;
				//调用提交表单的方法获得返回值
				var result = win.finishTranOrder();
				
				
				//如果成功页面要刷新
				if(result == "success"){
					diag.close();
					window.location.href = "${path }/transportOrder_tasks?orderModelQuery.orderType=2&orderModelQuery.orderState=2";
				}
			};
			diag.show();
			diag.okButton.value="结单";
		
	}
</script>
<style>
li{
	list-style-type: none;
	float: left;
	padding: 3px;
	border: 1px solid #8ECC3D;
	width: 80px;
	text-align: center;
	margin-left: 1px;
	font-size: 15px;
	
}
</style>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">任务查询</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path }/transportOrder_tasks" method="post"> 
		<input type="hidden" id="pageNo"  name="orderModelQuery.pageNo" value="<s:property value="#page.pageNo"/>">
		<input type="hidden" id="orderState" name="orderModelQuery.orderState" value="<s:property value="orderModelQuery.orderState"/>"/>
		<input type="hidden" id="orderState" name="orderModelQuery.orderType" value="<s:property value="orderModelQuery.orderType"/>"/>
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="8%">供&nbsp;应&nbsp;商:</td>
						<td width="29%">
							<s:select  id="selectSupplier" cssClass="kuan" list="#suppliers" listKey="supplierId" listValue="name" name="orderModelQuery.supplierId" headerKey="" headerValue=""></s:select>
						</td>
						<td width="8%">发货方式:</td>
						<td width="45%">
							<s:select list="#{'0':'自提','1':'送货' }" name="orderModelQuery.needs" headerKey="" headerValue=""></s:select>
						</td>
						<td width=""><a id="query"> 
							<img src="${path}/images/can_b_01.gif" border="0" /> </a>
						</td>
					</tr>
				</table>
			</div>
				<ul>
					<li id="li1"><a href="${path }/transportOrder_tasks?orderModelQuery.orderType=2&orderModelQuery.orderState=2" style="text-decoration: none;color: black">待采购</a></li>
					<li id="li2"><a href="${path }/transportOrder_tasks?orderModelQuery.orderType=2&orderModelQuery.orderState=3" style="text-decoration: none;color: black">采购中</a></li>
				</ul>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="8%" height="30">订单类别</td>
						<td width="11%">供应商</td>
						<td width="7%">发货方式</td>
						<td width="6%">联系人</td>
						<td width="12%">联系方式</td>
						<td width="40%">地址</td>
						<td width="6%">详情</td>
					</tr>
					<s:iterator value="#page.list" var="order">
						<tr align="center" bgcolor="#FFFFFF">
							<td height="30">
								<c:set var="orderType" value="${order.orderType}"></c:set>
								<e:orderTypetext orderType="${orderType}"/>
							</td>
							<td><s:property value="#order.supplier.name"/></td>
							<td><s:property value="#order.supplier.needs == 1?'送货':'自提'"/></td>
							<td><s:property value="#order.supplier.contact"/></td>
							<td><s:property value="#order.supplier.tel"/></td>
							<td><s:property value="#order.supplier.address"/></td>
							<td>
									<s:if test="#order.orderState == 2">
									<a href="javascript:void(0);" onclick="viewOrderDetail(<s:property value="#order.orderId"/>)">
										<img src="${path}/images/icon_3.gif" />详情
									</a>
									</s:if>
									<s:else>
										<a href="javascript:void(0);" onclick="viewOrderDetail1(<s:property value="#order.orderId"/>)">
											<img src="${path}/images/icon_3.gif" />详情
										</a>
									</s:else>
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
