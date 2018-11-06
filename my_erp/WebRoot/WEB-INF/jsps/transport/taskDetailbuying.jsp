<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
function getOrderProduct(){
	var result = "";
	
	var orderId = $("#orderId").val();
	var result = "";
	$.ajax({
		url:"${path}/ajax_transportOrder_getOrderProduct",
		type:"post",
		data:{
			"orderModel.orderId":orderId
			
		},
		async:false,
		dataType:"text",
		success:function(responseText){
			result = responseText;
		}
	})
	return result;
	
	
}
function finishTranOrder(){
	var result = "";
	
	var orderId = $("#orderId").val();
	var result = "";
	$.ajax({
		url:"${path}/ajax_transportOrder_finishTranOrder",
		type:"post",
		data:{
			"orderModel.orderId":orderId
			
		},
		async:false,
		dataType:"text",
		success:function(responseText){
			result = responseText;
		}
	})
	return result;
	
	
}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">进货订单明细</span>
		</div>
	</div>
	<div class="content-text">
			<input type="hidden" id="orderId" value="<s:property value="orderModel.orderId"/>">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td height="30">企业名称:</td>
						<td class="order_show_msg"><s:property value="orderModel.supplier.name"/></td>
						<td >订单类别:</td>
						<td class="order_show_msg">
							<e:orderTypetext orderType="${orderModel.orderType }"/>
						</td>
						<td>提货方式:</td>
						<td class="order_show_msg"><s:property value="orderModel.supplier.needs == 1?'送货':'自提'"/></td>
						<td>订 单 号:</td>
						<td class="order_show_msg" colspan="2"><s:property value="orderModel.orderNum"/></td>
					</tr>
					<tr>
						<td height="30">联系人:</td>
						<td class="order_show_msg">
							<s:property value="orderModel.supplier.contact"/>
						</td>
						<td>联系方式:</td>
						<td class="order_show_msg">
							<s:property value="orderModel.supplier.tel"/>
						</td>
						<td>商品总量:</td>
						<td class="order_show_msg"><s:property value="orderModel.totalNum"/></td>
						<td>地址:</td>
						<td class="order_show_msg"><s:property value="orderModel.supplier.address"/> </td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<center style="text-decoration:underline;font-size:16px; font-weight:bold; font-family:"黑体";">&nbsp;&nbsp;&nbsp;&nbsp;订&nbsp;&nbsp;单&nbsp;&nbsp;明&nbsp;&nbsp;细&nbsp;&nbsp;&nbsp;&nbsp;</center>
				<br/>
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="30%" height="30">商品类别</td>
						<td width="30%">商品名称</td>
						<td width="40%">购买数量</td>
						
					</tr>
					<s:iterator value="orderModel.details" var="detail">
						<tr align="center" bgcolor="#FFFFFF">
							<td height="30"><s:property value="#detail.product.productType.name"/></td>
							<td><s:property value="#detail.product.name"/></td>
							<td><s:property value="#detail.detailNum"/></td>
						</tr>
					</s:iterator>
					
					
					
				</table>
				<br/>
				
			</div>
	</div>
	<div class="content-bbg"></div>
</div>
