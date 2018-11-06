<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../../taglibs.jsp" %>
<script type="text/javascript">
	
	$(function() {
		$("#query").click(function() {
			$("form:first").submit();
		});
		
		var orderState = $("#orderState").val();
		if(orderState == "1"){
			$("#li1").css("background","#8ECC3D");
		}else if(orderState == "2"){
			$("#li2").css("background","#8ECC3D");
		}else if(orderState == "3"){
			$("#li3").css("background","#8ECC3D");
		}else{
			$("#li4").css("background","#8ECC3D");
		}
	});
	
		function viewDetail(orderId){
		var myOrderState = -1;
		var note = "";
		var myOrderId = -1;
		var orderState = $("#orderState").val();
		var orderIdStr = orderId + "";
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 400;
		diag.ShowButtonRow=true;
		diag.Title = "详情";
		diag.URL = path + "/orderDetail_orderDetailList?orderDetail.orderId="+orderIdStr;
		diag.OKEvent = function(){
		if(myOrderState != -1 && note != "" && myOrderId != -1){
			submitForm(myOrderState,myOrderId,note);
		}	
			diag.close();
		};
		diag.show();
		if(orderState == "1"){
			diag.addButton("next","审批",function(){
						var diag1 = new Dialog();
						diag1.Width = 400;
						diag1.Height = 200;
						diag1.ShowButtonRow=true;
						diag1.Title = "审批意见";
						diag1.URL = path + "/orderModel_audit?orderModel.orderId="+orderIdStr;
						//通过	
						diag1.OKEvent = function(){
						var win=diag1.innerFrame.contentWindow;
						var ret = win.returnArray();
						if(ret == "noteEmpty"){
							win.notNoteTip();
						}else{
							myOrderId = ret[0];
							note = ret[1];
							myOrderState = 2;
							diag1.close();
								}
											
								};
						diag1.show();
						diag1.okButton.value=" 通过 ";
						diag1.addButton("next","驳回",function(){
							var win=diag1.innerFrame.contentWindow;
							var ret = win.returnArray();
							if(ret == "noteEmpty"){
								win.notNoteTip();
							}else{
								myOrderId = ret[0];
								note = ret[1];
								myOrderState = 3;
								diag1.close();
							}		
					});
						
					});
			

		}

	}
	function submitForm(orderState,orderId,note){
		var ret = "";
		$.ajax({
			type:"post",
			url:"${path}/ajax_orderModel_audit",
			data:{
				"orderModel.orderId":orderId,
				"note":note,
				"orderModel.orderState":orderState,
			},
			async:false,
			dataType:"text",
			success:function(responseText){
				ret = responseText;
			},
		});
		return ret;
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
			<span class="page_title">进货审核</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path}/orderModel_checkList" method="post"> 
		<input type="hidden" id="pageNo"  name="orderModelQuery.pageNo" value="<s:property value="#page.pageNo"/>">
		<input type="hidden" id="orderState" name="orderModelQuery.orderState" value="<s:property value="orderModelQuery.orderState"/>"/>
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td>下单人:</td>
						<td><s:textfield type="text" size="14" name="orderModelQuery.orderCreater"/></td>
						<td>总量:</td>
						<td><s:textfield type="text" size="14" name="orderModelQuery.minTotalNum"/></td>
						<td>到 </td>
						<td><s:textfield type="text" size="14" name="orderModelQuery.maxTotalNum"/></td>
						<td><a id="query"> 
							<img src="${path}/images/can_b_01.gif" border="0" /> </a>
						</td>
					</tr>
					<tr>
						<td>下单时间:
						<td>
							<s:textfield type="text" size="14" name="orderModelQuery.minCreateTime" onfocus="c.showMoreDay=false;c.show(this);" readonly="false"/>
						</td>
						<td>到</td>
						<td>
							<s:textfield type="text" size="14" name="orderModelQuery.maxCreateTime" onfocus="c.showMoreDay=false;c.show(this);" readonly="false"/>
						</td>
						<td>总额:</td>
						<td><s:textfield type="text" size="14" name="orderModelQuery.minTotalPrice"/></td>
						<td>到</td>
						<td><s:textfield type="text" size="14" name="orderModelQuery.maxTotalPrice"/></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<ul>
					<li id="li1"><a href="${path }/orderModel_checkList?orderModelQuery.orderType=1&orderModelQuery.orderState=1" style="text-decoration: none;color: black">未审核</a></li>
					<li id="li2"><a href="${path }/orderModel_checkList?orderModelQuery.orderType=1&orderModelQuery.orderState=2" style="text-decoration: none;color: black">审核通过</a></li>
					<li id="li3"><a href="${path }/orderModel_checkList?orderModelQuery.orderType=1&orderModelQuery.orderState=3" style="text-decoration: none;color: black">审核不通过</a></li>
					<li id="li4"><a href="${path }/orderModel_checkList" style="text-decoration: none;color: black">全部</a></li>
				</ul>
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="25%" height="30">订单号</td>
						<td width="9%">供应商</td>
						<td width="10%">下单人</td>
						<td width="20%">下单时间</td>
						<td width="10%">订单商品总量</td>
						<td width="12%">订单总金额</td>
						<td width="5%">详情</td>
					</tr>
					<s:iterator value="#page.list" var="orderModel">
						<tr align="center" bgcolor="#FFFFFF">
							<td width="13%" height="30"><s:property value="#orderModel.orderNum"/></td>
							<td><s:property value="#orderModel.supplier.name"/></td>
							<td><s:property value="#orderModel.orderCreat.name"/></td>
							<td><s:property value="#orderModel.createTime"/></td>
							<td><s:property value="#orderModel.totalNum"/></td>
							<td ><s:property value="#orderModel.totalPrice"/></td>
							<td>
								<a href="#" onclick="viewDetail(<s:property value="#orderModel.orderId"/>)" class="xiu">详情</a>
							</td>
						</tr>
					</s:iterator>
				</table>
				<%@ include file="../../tools/paging.jsp" %>
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
