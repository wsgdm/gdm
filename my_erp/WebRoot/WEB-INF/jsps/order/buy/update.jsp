<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../../taglibs.jsp" %>
<script type="text/javascript">
$(function(){
$("#selectSupplier").attr("disabled","disabled");
	$(".goodsType").change(function(){
		var productTypeId = $(this).val();
		var objTr = $(this).parent().parent();
		if(productTypeId > 0){
			loadPro(objTr,productTypeId);
		}
		var productId = $(this).parent().parent().find(".goods").val();
 		setPrice($(this),productId);
		getTotalPrice();
	});
	
		$(".goods").change(function(){
		var productId = $(this).val();
		var count = 0;
		$(this).parent().parent().parent().find(".goods").each(function(){
			if($(this).val() == productId){
				count++;
			}
		});
		if(count == 2){
			Dialog.alert("商品已经存在");
			$(this).find("[value='"+productId+"']").remove();
		}
		productId = $(this).val();
		setPrice($(this),productId);
		getTotalPrice();
	});
	$("#add").click(function(){
		var tr = $("#mytr").clone(true);
		tr.removeAttr("id");
		tr.find(".goods").empty();
		var productTpyeId = tr.find(".goodsType").val();
		$("#mytr").before(tr);
		$("#mytr").removeAttr("id");
		$(".trr:first").attr("id","mytr");
		loadPro(tr,productTpyeId);
		getTotalPrice();
	});
	$(".deleteBtn").click(function(){
		if($("#selectSupplier").val() != -1){
			if($(".trr").length == 1){
			Dialog.alert("至少要有一项商品");
		}else{
			$(this).parent().parent().remove();
			getTotalPrice();
		}
		}

		
	});
	$(".num").keyup(function(){
		var num = parseInt($(this).val());
		var price = parseInt($(this).parent().parent().find(".prices").attr("aPrice"));
		var totalPrice = num * price;
		$(this).parent().parent().find(".total").html(totalPrice+"元");
		$(this).parent().parent().find(".total").attr("toPrice",totalPrice);
		getTotalPrice();
		
	});
});


function loadPt(supplierId){
		$.ajax({
			type:"post",
			url:"${path}/ajax_orderModel_getProType",
			data:{
				"orderModel.supplierId":supplierId
			},
			async:false,
			dataType:"text",
			success:function(responseText){
				$(".goodsType").empty();
				var jsonArr = $.parseJSON(responseText);
				for(var i = 0; i < jsonArr.length; i++){
					$(".goodsType").append("<option value='"+jsonArr[i].productTypeId+"'>"+jsonArr[i].name+"</option>")
				}
			},
		});
}

function loadPro(objTr,productTypeId){
		$.ajax({
			type:"post",
			url:"${path}/ajax_orderModel_getPro",
			data:{
				"orderModel.productTypeId":productTypeId
			},
			async:false,
			dataType:"text",
			success:function(responseText){
				objTr.find(".goods").empty();
				var jsonArr = $.parseJSON(responseText);
				for(var i = 0; i < jsonArr.length; i++){
					var isExit = 0;
					$("#order").find(".goods").each(function(){
						var productId = $(this).val();
						if(productId == jsonArr[i].productId+""){
							isExit = 1;
						}
					});
					if(isExit == 0){
						objTr.find(".goods").append("<option value='"+jsonArr[i].productId+"'>"+jsonArr[i].name+"</option>");
					}
					
				}
			},
		});
}

function getPrice(productId){
			ret = "";
			$.ajax({
			type:"post",
			url:"${path}/ajax_orderModel_getProductPrice",
			data:{
				"orderModel.productId":productId
			},
			async:false,
			dataType:"text",
			success:function(responseText){
				ret = responseText;
			},
		});
		return ret;
}

function getTotalPrice(){
	var totalPrice = 0;
	$("#finalTr").parent().find(".total").each(function(){
		totalPrice = parseInt($(this).attr("toPrice")) + totalPrice;
	});
		$(".all").html(totalPrice+"元");

	
}

function setPrice(objTr,productId){
			if(productId > 0){
			var pirce = getPrice(productId);
			objTr.parent().parent().find(".prices").html(pirce+"元");
			objTr.parent().parent().find(".prices").attr("aPrice",pirce);
			var tprice = pirce * objTr.parent().parent().find(".num").val();
			objTr.parent().parent().find(".total").html(tprice+"元");
			objTr.parent().parent().find(".total").attr("toPrice",tprice);
		}else{
			objTr.parent().parent().find(".prices").html(""+"元");
			objTr.parent().parent().find(".prices").attr("aPrice","");
			objTr.parent().parent().find(".total").html(""+"元");
			objTr.parent().parent().find(".total").attr("toPrice","");
		}
}

function submitForm(){
	var ret = "";
	$("form:first").ajaxSubmit({
		async:false,
		dataType:"text",
		success:function(responseText){
			ret = responseText;
		}
	});
	return ret;
}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">订单管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path}/ajax_orderModel_update" method="post">
		<input type="hidden" name="orderModel.supplier.supplierId" id="sup" value="<s:property value="orderModel.supplier.supplierId"/>"/>
		<input type="hidden" name="orderModel.orderId" value="<s:property value="orderModel.orderId"/>"/>
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="68px" height="30">供应商：</td>
						<td width="648px">
							<s:select  id="selectSupplier" cssClass="kuan" list="#suppliers" listKey="supplierId" listValue="name" name="orderModel.supplier.supplierId"></s:select>
						</td>
						<div id="noteTip" style="text-align: center;color: red;"><s:property value="note"/></div>
						<td height="30">
							<a id="add"><img src="${path}/images/can_b_02.gif" border="0" /> </a>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table id="order" width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="25%" height="30">商品类别</td>
						<td width="25%">商品名称</td>
						<td width="10%">采购数量</td>
						<td width="15%">单价</td>
						<td width="15%">合计</td>
						<td width="10%">操作</td>
					</tr>
					<s:iterator value="orderModel.details" var="orderList">
					<tr id="mytr" class="trr" align="center" bgcolor="#FFFFFF">
						<td height="30">
							<s:select cssClass="goodsType" list="orderModel.supplier.pts" name="productTypeId" listValue="name" listKey="productTypeId"></s:select>
						</td>
						<td>
							<s:select cssClass="goods" list="#orderList.product.productType.products" name="productId" listValue="name" listKey="productId"></s:select>
						</td>
						<td><input type="text" name="num" value="<s:property value="#orderList.detailNum" />" class="num order_num" style="width:67px;border:1px solid black;text-align:right;padding:2px"/></td>
						<td  class="prices order_num" align="right" aPrice="<s:property value="#orderList.detailPrice"/>"><s:property value="#orderList.detailPrice"/> 元</td>
						<td class="total" align="right" toPrice="<s:property value="#orderList.detailPrice * #orderList.detailNum" />"><s:property value="#orderList.detailPrice * #orderList.detailNum" />元</td>
						<td>
							<a class="deleteBtn delete xiu" value="4"><img src="${path}/images/icon_04.gif" /> 删除</a>
						</td>
					</tr>
					</s:iterator>
					<tr id="finalTr" align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td height="30" colspan="4" align="right">总&nbsp;计:&nbsp;</td>
						<td class="all" width="16%" align="right"><s:property value="orderModel.totalPrice"/>元</td>
						<td>&nbsp;</td>
					</tr>
					
				</table>
				
			</div>
		</form>
	</div>
	
	<div class="content-bbg"></div>
</div>
