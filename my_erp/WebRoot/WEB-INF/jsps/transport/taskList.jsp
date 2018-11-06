<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("form:first").submit();
		});
		
	});
	
		function taskAssgin(orderId){
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 400;
		diag.ShowButtonRow=true;
		diag.Title = "任务指派";
		diag.URL = "${path}/transportOrder_taskDetail?orderModel.orderId="+orderId;
		diag.OKEvent = function(){
			var win = diag.innerFrame.contentWindow;
			//调用提交表单的方法获得返回值
			var result = win.getAssginEmp();
			
			
			//如果成功页面要刷新
			if(result == "success"){
				diag.close();
				window.location.href = "${path }/transportOrder_taskList?orderModelQuery.orderType=2&orderModelQuery.orderState=1";
			}
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
			<span class="page_title">商品运输管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path }/transportOrder_taskList" method="post"> 
		<input type="hidden" id="pageNo"  name="orderModelQuery.pageNo" value="<s:property value="#page.pageNo"/>">
		<input type="hidden" id="orderState" name="orderModelQuery.orderState" value="<s:property value="orderModelQuery.orderState"/>"/>
		<input type="hidden" id="orderState" name="orderModelQuery.orderType" value="<s:property value="orderModelQuery.orderType"/>"/>
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td>下单时间:</td>
						<td>
							<s:textfield type="text" size="10" name="orderModelQuery.minCreateTime" onfocus="c.showMoreDay=false;c.show(this);" readonly="false"/>
						</td>
						<td>到&nbsp;</td>
						<td>
							<s:textfield type="text" size="10" name="orderModelQuery.maxCreateTime" onfocus="c.showMoreDay=false;c.show(this);" readonly="false"/>
						</td>
						<td>供&nbsp;应&nbsp;商:</td>
						<td>
						<s:select  id="selectSupplier" cssClass="kuan" list="#suppliers" listKey="supplierId" listValue="name" name="orderModelQuery.supplierId" headerKey="" headerValue=""></s:select>
						</td>
						<td>下单人:</td>
						<td><s:textfield type="text" size="10" name="orderModelQuery.orderCreater"/></td>
						<td>&nbsp;</td>
						<td><a id="query"> 
							<img src="${path}/images/can_b_01.gif" border="0" /> </a>
						</td>
					</tr>
					<tr>
						<td>审核时间:</td>
						<td>
							<s:textfield type="text" size="10" name="orderModelQuery.minCheckTime" onfocus="c.showMoreDay=false;c.show(this);" readonly="false"/>
						</td>
						<td>到&nbsp;</td>
						<td>
							<s:textfield type="text" size="10" name="orderModelQuery.maxCheckTime" onfocus="c.showMoreDay=false;c.show(this);" readonly="false"/>
						</td>
						<td>发货方式:</td>
						<td>
							<s:select list="#{'0':'自提','1':'送货' }" name="orderModelQuery.needs" headerKey="" headerValue=""></s:select>
						</td>
						<td>审核人:</td>
						<td><s:textfield type="text" size="10" name="orderModelQuery.checker"/></</td>
						<td>跟单人:</td>
						<td><s:textfield type="text" size="10" name="orderModelQuery.completer"/></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="10%" height="30">订单类别</td>
						<td width="13%">下单时间</td>
						<td width="13%">制单人</td>
						<td width="13%">审核时间</td>
						<td width="13%">审核人</td>
						<td width="15%">供应商</td>
						<td width="13%">发货方式</td>
						<td width="10%">跟单人</td>
					</tr>
					<s:iterator value="#page.list" var="order">
						<tr align="center" bgcolor="#FFFFFF">
							<td height="30">
								<c:set var="orderType" value="${order.orderType }"></c:set>
								<e:orderTypetext orderType="${orderType }"/>
							</td>
							<td><s:property value="#order.createTime"/></td>
							<td><s:property value="#order.orderCreat.name"/></td>
							<td><s:property value="#order.checkTime"/></td>
							<td><s:property value="#order.orderCreat.name"/></td>
							<td><s:property value="#order.supplier.name"/></td>
							<td><s:property value="#order.supplier.needs == 1?'送货':'自提'"/></td>
							<td>
									<img src="${path}/images/icon_3.gif" /> 
									<span style="line-height:12px; text-align:center;"> 
										<a href="javascript:void(0)" onclick="taskAssgin(<s:property value="#order.orderId"/>)" class="xiu">任务指派
										</a> 
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
