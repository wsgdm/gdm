<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("form:first").submit();
		});
		
		var orderState = $("#orderState").val();
		if(orderState == "1"){
			$("#li1").css("background","#8ECC3D");
		}else{
			$("#li2").css("background","#8ECC3D");
		}
	});
	
		function inStock(orderId){
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 400;
		diag.ShowButtonRow=true;
		diag.Title = "订单入库";
		diag.URL = "${path}/store_inDetail?orderModel.orderId="+orderId;
		diag.OKEvent = function(){
				diag.close();
				window.location.href = "${path }/store_inList?orderModelQuery.orderType=3&orderModelQuery.orderState=1";	
		};
		diag.show();
		
		
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
			<span class="page_title">入库</span>
		</div>
	</div>
	<div class="content-text">
		<form action="list.jsp" method="post">
		<input type="hidden" id="orderState" name="orderModelQuery.orderState" value="<s:property value="orderModelQuery.orderState"/>"/>
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="10%">订单号:</td>
						<td width="40%"><input type="text" size="40"/></td>
						<td width="10%">跟单人:</td>
						<td width="25%"><input type="text" size="20" /></td>
						<td width="15%"><a id="query"> 
							<img src="${path}/images/can_b_01.gif" border="0" /> </a>
						</td>
					</tr>
				</table>
			</div>
			<ul>
					<li id="li1"><a href="${path }/store_inList?orderModelQuery.orderType=3&orderModelQuery.orderState=1" style="text-decoration: none;color: black">待入库</a></li>
					<li id="li2"><a href="${path }/store_inList?orderModelQuery.orderType=3&orderModelQuery.orderState=2" style="text-decoration: none;color: black">入库中</a></li>
				</ul>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="30%" height="30">订单号</td>
						<td width="20%">跟单人</td>
						<td width="20%">种类</td>
						<td width="20%">入库</td>
					</tr>
					<s:iterator value="#orderList" var="list">
						<tr align="center" bgcolor="#FFFFFF">
							<td height="30"><s:property value="#list.orderNum"/></td>
							<td><s:property value="#list.transportOrder.name"/></td>
							<td><s:property value="#list.details.size()"/></td>
							<td>
								<img src="${path}/images/icon_3.gif" /> 
								<span style="line-height:12px; text-align:center;"> 
									<a href="" onclick="inStock(<s:property value="#list.orderId"/>)"  class="xiu">入库
									</a> 
								</span>
							</td>
						</tr>
						</s:iterator>
				</table>
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
