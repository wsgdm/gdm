<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#all").click(function() {
			$("[name=roles]:checkbox").attr("checked",$("#all").attr("checked")=="checked");
		});
		$("#reverse").click(function() {
			$("[name=roles]:checkbox").each(function () {
                $(this).attr("checked", !$(this).attr("checked"));
            });

		});
		
		$("#submitForm").find("[regr]").keyup(function(){
			var regStr = $(this).attr("regr");
			var reg = new RegExp(regStr);
			var tip = $(this).attr("tip");
			var val = $(this).val();
			var name = $(this).attr("name");
			if(!reg.test(val)){
				isSubmit = false;
				$(this).css("background-color","#FFAC8C");
				$("#divtip").html(tip);
				$("#divtip").show();
			}else{
				$("#divtip").hide();
				$(this).next("span").show();
				$(this).css("background-color","white");
					if(name == "repassword"){
					if($("#password").val() != val){
						$(this).css("background-color","#FFAC8C");
						$("#divtip").html("两次密码不一致");
						$("#divtip").show();
						$(this).next("span").hide();
					}
				}
				
			}
			
		});
		
		
				$("#submitForm").find("[reg]").keyup(function(){
				var regStr = $(this).attr("reg");
				var reg = new RegExp(regStr);
				var tip = $(this).attr("tip");
				var val = $(this).val();
				if(!reg.test($.trim(val)) && val != null && $.trim(val) != ""){
					isSubmit = false;
					$(this).css("background-color","#FFAC8C");
					$("#divtip").html(tip);
					$("#divtip").show();
				}else{
					$(this).css("background-color","white");
					$("#divtip").hide();
				}
				
				});
	});
	function submitForm(){
	var ret = "";
		var isOK =  validForm();
		if(isOK){
			$("#submitForm").ajaxSubmit({
				async:false,
				dataType:"text",
				success:function(responseText){
					ret =  responseText;
				},
			});
		}
		return ret;
	}
	function validForm(){
		var isSubmit = true;
		$("#submitForm").find("[regr]").each(function(){
			var regStr = $(this).attr("regr");
			var reg = new RegExp(regStr);
			var tip = $(this).attr("tip");
			var val = $(this).val();
			var name = $(this).attr("name");
			if(!reg.test(val)){
				isSubmit = false;
				$(this).css("background-color","#FFAC8C");
				$("#divtip").html(tip);
				$("#divtip").show();
				return isSubmit;
			}else{
				$(this).css("background-color","white");
					if(name == "emp.username"){
						if(validUname($(this).val()) == "yes"){
							isSubmit = false;
							$(this).css("background-color","#FFAC8C");
							$("#divtip").html("用户名已经存在");
							$("#divtip").show();
							$(this).next("span").hide();
							return isSubmit;
						}
					}
					
					if(name == "repassword"){
					if($("#password").val() != val){
						isSubmit = false;
						$(this).css("background-color","#FFAC8C");
						$("#divtip").html("两次密码不一致");
						$("#divtip").show();
						return isSubmit;
					}
				}
				
			}
			$("#divtip").hide();
		});
		
			
				$("#submitForm").find("[reg]").each(function(){
				var regStr = $(this).attr("reg");
				var reg = new RegExp(regStr);
				var tip = $(this).attr("tip");
				var val = $(this).val();
				if(!reg.test($.trim(val)) && val != null && $.trim(val) != ""){
					isSubmit = false;
					$(this).css("background-color","#FFAC8C");
					$("#divtip").html(tip);
					$("#divtip").show();
					return isSubmit;
				}else{
					$(this).css("background-color","white");
					
				}
				
				});

		return isSubmit;
	}
	
	function validUname(username){
		var ret = "";
		$.ajax({
			type:"post",
			url:"${path}/ajax_emp_validUname",
			data:{
				"emp.username":username
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
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">员工管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form action="${path}/ajax_emp_add" id="submitForm" method="post"> 
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<div id="divtip" style="text-align: center;  color: red;"></div>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center"><font color="red">*</font>用&nbsp;户&nbsp;名</td>
				      <td width="32%">
				      	<s:textfield type="text" size="25" name="emp.username" regr="^\w{6,8}$" tip="请输入6到8位用户名"/><span style="display:none"><img width="20" src="${path}/images/ok.png"></span>
				      </td>
				      <td width="18%"align="center"><font color="red">*</font>真实姓名</td>
				      <td width="32%">
				      	<s:textfield type="text" size="25" name="emp.name" regr="^[\u4e00-\u9fa5]{2,10}$" tip="请输入2到10位的真实姓名"/><span style="display:none"><img width="20" src="${path}/images/ok.png"></span>
					  </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td align="center"><font color="red">*</font>密&nbsp;&nbsp;&nbsp;&nbsp;码</td>
				      <td>
				      	<s:textfield type="password" id="password" size="25" name="emp.password" regr="^[\w!@#$%^&*()_]{6,8}$" tip="请输入6到8位密码"/><span style="display:none"><img width="20" src="${path}/images/ok.png"></span>
				      </td>
				      <td  align="center"><font color="red">*</font>确认密码</td>
				      <td >
				      	<s:textfield type="password" name="repassword" size="25"  regr="^[\w!@#$%^&*()_]{6,8}$" tip="请输入6到8位密码"/><span style="display:none"><img width="20" src="${path}/images/ok.png"></span>
				      </td>
				    </tr>
				     <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">电子邮箱</td>
				      <td>
				      	<s:textfield type="text" size="25" name="emp.email" reg="^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$" tip="请输入正确格式的邮箱。如969653400@qq.com"/>
				      <td align="center">电话号码</td>
				      <td>
				      	<s:textfield type="text" size="25" name="emp.tel" reg="^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$" tip="请输入正确的11位电话号码"/>
					  </td>
				     </tr>
				      <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center"><font color='red'>*</font>性&nbsp;&nbsp;&nbsp;&nbsp;别</td>
				      <td>
						<s:select list="#{'1':'男','0':'女' }" name="emp.gender" cssClass="kuan" ></s:select>
					  </td>
				      <td align="center">地&nbsp;&nbsp;&nbsp;&nbsp;址</td>
				      <td>
				      	<s:textfield type="text" name="emp.address"  size="25"  reg="^[\u4E00-\u9FA5A-Za-z0-9_]{1,50}$" tip="请输入1-50位地址"/>
					  </td>
				    </tr>
				     <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">出生日期</td>
				      <td>
				      	<s:textfield type="text"  size="25" name="emp.birthday" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"/>
					  </td>
				      <td align="center"><font color='red'>*</font>所属部门</td>
				      <td>
						<s:select list="#qList" name="emp.dep.depId" cssClass="kuan"  listKey="depId" listValue="name"></s:select>
					  </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>

				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				</table>
			</div>

			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="${path}/images/content_bbg.jpg" /></div>
</div>
