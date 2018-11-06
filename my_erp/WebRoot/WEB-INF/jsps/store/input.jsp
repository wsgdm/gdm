<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../taglibs.jsp" %>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">仓库管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form action="list.jsp" method="post">
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">仓库名称</td>
				      <td width="32%">
				      	<input type="text" size="25"/>
				      </td>
				      <td width="18%" align="center">仓库地址</td>
				      <td width="32%">
				      	<input type="text" size="25"/>
				      </td>
				         <td width="18%" align="center">管理员</td>
				      <td width="32%">
				      	<select style="width:190px">
								<option value="-1">----请-选-择----</option>
								<option value="1">张三</option>
								<option value="2">李四</option>
							</select>
				      </td>
				    </tr>
				</table>
			</div>
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="${path}/images/content_bbg.jpg" /></div>
</div>
