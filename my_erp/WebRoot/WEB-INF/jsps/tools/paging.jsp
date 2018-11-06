<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function(){
		
		var pageNo=parseInt($("#cpageNo").val());
		var totalPage=parseInt($("#totalPage").val());
	
		$("#fir").click(function(){
			$("#pageNo").val(1);
			$("form").submit();
		});
		
		$("#pre").click(function(){
			$("#pageNo").val(pageNo-1);
			$("form").submit();
			});
			
		$("#next").click(function(){
			$("#pageNo").val(pageNo+1);
			$("form").submit();
		});
		
		$("#last").click(function(){
			$("#pageNo").val(totalPage);
			$("form").submit();
		});
		
		$("#selectPage").click(function(){
			var selectPageNo = $("#selectPageNum").val();
			var reg = /^\d{0,}$/;
			if(!reg.test(selectPageNo)){
				alert("请输入1-"+ totalPage + "的数字");
			}else{
				var selectPageNoNum = parseInt(selectPageNo);
				if(selectPageNoNum > totalPage || selectPageNoNum < 1){
					alert("请输入1-"+ totalPage + "的数字");
				}else{
					$("#pageNo").val(selectPageNoNum);
					$("form").submit();
				}
			}
		});
		if(pageNo == 1 && totalPage > 1){
			$("#fir").hide();
			$("#pre").hide();
			$("#next").show();
			$("#last").show();
		}
		if(pageNo == 1 && totalPage == 1){
			$("#fir").hide();
			$("#pre").hide();
			$("#next").hide();
			$("#last").hide();
		}
		if(pageNo > 1 && totalPage > pageNo){
			$("#fir").show();
			$("#pre").show();
			$("#next").show();
			$("#last").show();
		}
		if(pageNo > 1 && totalPage == pageNo){
			$("#fir").show();
			$("#pre").show();
			$("#next").hide();
			$("#last").hide();
			
		}

	});
</script>
<br/>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		<td width="51%">
			<input type="hidden" id="cpageNo"   value="<s:property value="#page.pageNo"/>">
			<input type="hidden" id="totalPage"  value="<s:property value="#page.totalPage"/>">
		</td>
		<td width="13%">共<s:property value="#page.totalCount"/>条记录
		<td width="6%">
			<input type="button" id="fir" class="sye" value="首页">
		</td>
		<td width="6%">
			<input type="button" id="pre" class="sye" value="上一页">
		</td>
		<td width="6%">
			<input type="button" id="next" class="sye" value="下一页">
		</td>
		<td width="6%">
			<input type="button" id="last" class="sye" value="尾页">
		</td>
		<td><input  type="text" id="selectPageNum" size="1" value="<s:property value="#page.pageNo"/>"/></td>
		<td><input type="button" id="selectPage" value="跳转"></td>
		<td width="12%">当前第<span style="color:red;"><s:property value="#page.pageNo"/></span>/<s:property value="#page.totalPage"/>页</td>
	</tr>
	
</table>
