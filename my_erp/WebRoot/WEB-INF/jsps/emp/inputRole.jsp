<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			$("form:first").submit();
		});
	});
	
		function gRole(){
		var res = "";
		$("input:checked").each(function(){	

			res = res + $(this).val() + ",";
		});
		return res;
	}
	
		function grantRole(empId){
		var RoleIds = gRole();
		$.ajax({
			url:"${path}/ajax_emp_role",
			Type:"post",
			data:{
				"emp.empId":empId,
				"RoleIds":RoleIds,
			},
			async:false,
			dataType:"text",
			success:function(responseText){
				result = responseText;
			}
			
		});
		return result;
	}
	


</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">角色分配</span>
		</div>
	</div>
	<div class="content-text">
		<form action="#" method="post"> 
		
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(../../images/table_bg.gif) repeat-x;">
						<td width="20%">选择</td>
						<td width="40%" height="30">角色名称</td>
						<td width="40%">角色编码</td>		
					</tr>
					<s:iterator value="#roles" var="role">
						<tr align="center" bgcolor="#FFFFFF">
							<td><input type="checkbox"  <s:if test="#role.select == 'yes'">checked="checked"</s:if> value="<s:property value="#role.roleId"/>"/></td>
							<td height="30"><s:property value="#role.name"/></td>
							<td><s:property value="#role.code"/></td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
