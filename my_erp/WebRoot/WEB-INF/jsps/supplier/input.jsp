<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
$(function(){

});

	function validName(){
		var tel = $("#tel").val();
		var tip = $("#tel").attr("tip");
		var regStr = $("#tel").attr("reg");
		var reg = new RegExp(regStr);
		var supplierName = $("#supplierName").val();
		if(!reg.test(tel)){
			$("#tipdiv").html(tip);
			return "NoOK";
		}else{
			$.ajax({
				type:"post",
				url:"${path}/ajax_supplier_validName",
				data:{
					"supplier.name":supplierName
				},
				async:false,
				dataType:"text",
				success:function(responseText){
				ret = responseText;
				},
			});
			if(ret == "NoOK"){
				var tip = $("#supplierName").attr("tip");
				$("#tipdiv").html(tip);
			}
			return ret;
		}
	}
	function SubmitForm(){
		var ret = validName();
		if(ret == "OK"){
			$("#first").ajaxSubmit({
				async:false,
				dataType:"text",
				success:function(responseText){
					ret =  responseText;
				},
			})
		}
		return ret;
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">供应商管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form id="first" action="${path}/ajax_supplier_addSupplier" method="post">
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				<div id="tipdiv" style="text-align: center; color: red;"></div>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">供应商名称</td>
				      <td width="82%" colspan="3">
				      	<s:textfield type="text" id="supplierName" name="supplier.name" size="82" tip="此供应商已存在"/>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">供应商地址</td>
				      <td width="82%" colspan="3">
				      	<s:textfield type="text" name="supplier.address" size="82"/>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">联系人</td>
				      <td width="32%">
				      	<s:textfield type="text" name="supplier.contact" size="25"/>
				      </td>
				      <td width="18%" align="center">电话</td>
				      <td width="32%">
				      	<s:textfield type="text" id="tel" name="supplier.tel" size="25" reg="^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$" tip="请输入正确的11位电话号码"/>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">送货方式</td>
				      <td width="32%">
				      <s:select style="width:190px" list="#{'1':'送货','2':'自提'}" name="supplier.needs"></s:select>
				      </td>
				      <td width="18%" align="center">&nbsp;</td>
				      <td width="32%">
				      	&nbsp;
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				</table>
				
			</div>
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="../../images/content_bbg.jpg" /></div>
</div>
