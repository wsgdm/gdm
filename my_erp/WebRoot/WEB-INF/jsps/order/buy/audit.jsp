<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../../taglibs.jsp" %>
<script type="text/javascript">
$(function(){
	
});


function returnArray(){
		var note = $("#note").val();
		if(note == null || note == ""){
			return "noteEmpty";
		}
		var orderId = $("#orderId").val();
		var myArray = new Array(orderId,note);
		return myArray;					
}

function notNoteTip(){
	$("#tip").html("意见不能为空");
}
</script>
<div>
<div id="tip" style="text-align: center;color: red;"></div>
	<form action="#" method="post">
		<textarea id="note" rows="50" cols="50" name="note"></textarea>
		<input id="orderId" type="hidden" name="orderModel.orderId" value="<s:property value="orderModel.orderId"/>" />
	</form>	
</div>
