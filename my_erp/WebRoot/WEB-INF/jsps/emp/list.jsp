<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../taglibs.jsp" %>

<script type="text/javascript">
	
	$(function() {
		$("#query").click(function() {
			$("#pageNo").val(1);
			$("form").submit();
			
		});	
		
	$("#addbutton").click(function(){
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 400;
		diag.ShowButtonRow=true;
		diag.Title = "添加员工";
		diag.URL = path + "/emp_input";
		diag.OKEvent = function(){
			var win=diag.innerFrame.contentWindow;
			var ret = win.submitForm();
			if(ret == "success"){
				diag.close();
				window.location.href = "${path}/emp_list";
			}
		};
		diag.show();	
		
	});
		
	});
	
	
		function updateEmp(empId){
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 400;
		diag.ShowButtonRow=true;
		diag.Title = "修改员工";
		diag.URL = path + "/emp_update?emp.empId=" + empId;
		diag.OKEvent = function(){
			var win=diag.innerFrame.contentWindow;
			var ret = win.submitForm();
			if(ret == "success"){
				diag.close();
				window.location.href = "${path}/emp_list";
			}
		};
		diag.show();
	};
	
	function deleteEmp(empId){
		Dialog.confirm('确定要删除吗',function(){
			window.location.href = path + "/emp_delete?emp.empId=" + empId;
		});
	}
	
	function roleEmp(empId){
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 400;
		diag.ShowButtonRow=true;
		diag.Title = "角色分配";
		diag.URL = path + "/emp_inputRole?emp.empId=" + empId;
		diag.OKEvent = function(){
			var win=diag.innerFrame.contentWindow;
			var result = win.grantRole(empId);
			if(result == "success"){
				Dialog.alert("角色分配成功",function(){
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
			<span class="page_title">员工管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path}/emp_list" id="rquery" method="post">
			<input type="hidden" id="pageNo"  name="empQuery.pageNo" value="<s:property value="#page.pageNo"/>">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td height="30">  用户名</td>
						<td><s:textfield type="text" size="14" name="empQuery.username"/></td>
						<td>真实姓名</td>
						<td><s:textfield type="text" size="14" name="empQuery.name"/></td>
						<td>电&nbsp;&nbsp;话</td>
						<td><s:textfield type="text" size="14" name="empQuery.tel"/></td>
						<td>性&nbsp;&nbsp;别</td>
						<td>
						<!--<select class="kuan">
								<option value="-1">----请-选-择----</option>
								<option value="1">男</option>
								<option value="0">女</option>
							</select> -->
							<s:select list="#{'1':'男','0':'女' }" name="empQuery.gender" cssClass="kuan" headerKey="" headerValue="----请-选-择----"></s:select>
						</td>
							
						<td width="70"><a id="addbutton" href="#"> <img src="${path}/images/can_b_02.gif" border="0" /> </a></td>
					</tr>
					<tr>
						<td  height="30">电子邮件</td>
						<td><s:textfield type="text" size="14" name="empQuery.email"/></td>
						<td>出生日期</td>
						<td>
							<s:textfield type="text"  size="14" name="empQuery.startbirth" onfocus="c.showMoreDay=false;c.show(this);" readonly="false"/>
						</td>
						<td>出生日期</td>
						<td>
							<s:textfield type="text" size="14" name="empQuery.endbirth" onfocus="c.showMoreDay=false;c.show(this);" readonly="false"/>
						</td>
						<td>部门名称</td>
						<td>
						<!--<select class="kuan">
								<option value="-1">----请-选-择----</option>
								<option value="1">销售部</option>
								<option value="2">采购部</option>
							</select> -->
						<s:select list="#qList" name="empQuery.depId" cssClass="kuan" headerKey="" headerValue="----请-选-择----" listKey="depId" listValue="name"></s:select>
						</td>
						<td><a id="query"> <img src="${path}/images/can_b_01.gif" border="0" /> </a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="8%" height="30">用户名</td>
						<td width="10%">真实姓名</td>
						<td width="5%">性别</td>
						<td width="12%">出生日期</td>
						<td width="10%">电话</td>
						<td width="12%">电子邮件</td>
						<td width="9%">所属部门</td>
						<td width="18%">操作</td>
					</tr>
					<s:iterator value="#page.list" var="emp">
						<tr align="center" bgcolor="#FFFFFF">
							<td width="13%" height="30"><s:property value="#emp.username"/></td>
							<td><s:property value="#emp.name"/></td>
							<td><s:property value="#emp.gender==1?'男':'女'"/></td>
							<td><s:date name="#emp.birthday" format="yyyy-mm-dd"/></td>
							<td><s:property value="#emp.tel"/></td>
							<td><s:property value="#emp.email"/></td>
							<td><s:property value="#emp.dep.name"/></td>
							<td>
								<img src="${path}/images/icon_3.gif" /> 
								<span style="line-height:12px; text-align:center;"> 
									<a href="javascript:void(0)" onclick="roleEmp(<s:property value="#emp.empId"/>)" class="xiu">角色分配</a>
								</span> 
								<span style="line-height:12px; text-align:center;"> 
									<a href="javascript:void(0)" onclick="updateEmp(<s:property value="#emp.empId"/>)" class="xiu">修改</a>
								</span> 
								<img src="${path}/images/icon_04.gif" /> 
								<span style="line-height:12px; text-align:center;"> 
									<a href="javascript:void(0)" class="xiu" onclick="deleteEmp(<s:property value="#emp.empId"/>)">删除</a>
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
