<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {	
		
	});
	
	function validForm(){
		var name = $("#roleName").val() + "";
		var code = $("#roleCode").val() + "";
		if(name == "" || code == ""){
			$("#divtip").html("两个文本项都不能为空");
			return "isNull";
		}
		var ret = "";
		$.ajax({
			type:"post",
			url:"${path}/ajax_role_valid",
			data:{
				"roleQuery.name":name,
				"roleQuery.code":code,
			},
			async:false,
			dataType:"text",
			success:function(responseText){
				ret = responseText;
			},
			
		});
		return ret;
	}
	
	function submitForm(){
		var res = validForm();
		if(res == "codeExist"){
			$("#divtip").html("角色编码已经存在");
		}else if(res == "nameExist"){
			$("#divtip").html("角色名已经存在");
		}else if(res == "codenameExist"){
			$("#divtip").html("角色名和编码都已经存在");
		}
		return res;
	}
	
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">角色管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form action="" method="post">
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					<div id="divtip" style="text-align: center; color: red;"></div>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">角色名称</td>
				      <td width="32%">
				      	<input id="roleName" type="text"size="25" />
				      </td>
				      <td width="18%" align="center">角色编码</td>
				      <td width="32%">
				      	<input id="roleCode" type="text" size="25" />
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
