<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#queryrole").click(function() {
			$("form:first").submit();
		});
		
		$("#addrole").click(function() {
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 400;
		diag.ShowButtonRow=true;
		diag.Title = "添加角色";
		diag.URL = path + "/role_input";
		diag.OKEvent = function(){
			var win=diag.innerFrame.contentWindow;
			var ret = win.submitForm();
			if(ret == "success"){
				diag.close();
				window.location.href = "${path}/role_list";
			}
		};
		diag.show();
		});
	});
	
	function update(roleId){
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 400;
		diag.ShowButtonRow=true;
		diag.Title = "修改角色";
		diag.URL = path + "/role_update?role.roleId="+roleId;
		diag.OKEvent = function(){
			var win=diag.innerFrame.contentWindow;
			var ret = win.updateSubmitForm();
			if(ret == "success"){
				diag.close();
				window.location.href = "${path}/role_list";
			}
		};
		diag.show();	
	}
	
	function deleteRole(roleId){
			Dialog.confirm('确定要删除吗',function(){
			window.location.href = "${path}/role_delete?role.roleId=" + roleId;
		});
	}
	
		function grantPerm(roleId, roleName){
		var diag = new Dialog();
		diag.Width = 550;
		diag.Height = 400;
		diag.ShowButtonRow=true;
		diag.Title = roleName;
		diag.URL = "${path}/role_listperm?roleQuery.roleId="+roleId;
		diag.OKEvent = function(){
			var win = diag.innerFrame.contentWindow;
			//调用提交表单的方法获得返回值
			var result1 = win.getNodes(roleId);
			
			if(result1 == "success"){
				Dialog.alert("权限分配成功",function(){
					diag.close();
				});
				
			}
		};
		diag.show();
		
		
	}
	

</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">角色管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path}/role_list" method="post">
		<input type="hidden" id="pageNo"  name="roleQuery.pageNo" value="<s:property value="#page.pageNo"/>"> 
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="68" height="30">&nbsp;</td>
						<td width="123">&nbsp;</td>
						<td width="62">角色名称</td>
						<td width="142"><s:textfield type="text" size="18" name="roleQuery.name"/></td>
						<td width="60">角色编码</td>
						<td width="149"><s:textfield type="text" size="18" name="roleQuery.code"/></td>
						<td width="70"><a id="queryrole"> <img src="${path}/images/can_b_01.gif" border="0" /> </a></td>
						<td width="70"><a id="addrole"><img src="${path}/images/can_b_02.gif" border="0" /></a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="40%" height="30">角色名称</td>
						<td width="40%">角色编码</td>
						<td width="20%">操作</td>
					</tr>
					<s:iterator value="#page.list" var="role">
						<tr align="center" bgcolor="#FFFFFF">
							<td height="30"><s:property value="#role.name"/></td>
							<td><s:property value="#role.code"/></td>
							<td>
								<img src="${path}/images/icon_3.gif" /> 
							    <span style="line-height:12px; text-align:center;"> 
									<a href="javascript:void(0)" onclick="grantPerm(<s:property value="#role.roleId"/>,'<s:property value="#role.name"/>')" class="xiu">分配权限</a>
							    </span> 
								<img src="${path}/images/icon_3.gif" /> 
								<span style="line-height:12px; text-align:center;"> 
									<a href="#" onclick="update(<s:property value="#role.roleId"/>)" class="xiu">修改</a>
								</span> 
								<img src="${path}/images/icon_04.gif" /> 
								<span style="line-height:12px; text-align:center;"> 
									<a href="javascript:void(0)" class="xiu" onclick="deleteRole(<s:property value="#role.roleId"/>)" >删除</a>
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
