<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function checkForm(){
		var repertoryName=$("#repertoryName").val();
		var repertoryQuantity=$("#repertoryQuantity").val();
		
		if(repertoryName==null||repertoryName==""){
			$("#error").html("请输入商品名称！");
			return false;
		}
		if(repertoryQuantity==null||repertoryQuantity==""){
			$("#error").html("请输入库存数量");
			return false;
		}
		return true;
	}
</script>
<div class="data_list">
	<div class="data_content">
		<form action="repertory!save" onsubmit="return checkForm()">
		<table width="30%" align="center">
			<tr>
				<td><label>商品名称：</label></td>
				<td><input type="text" id="repertoryName" name="repertory.repertoryName" value="${repertory.repertoryName }"/></td>
				
			</tr>
			<tr>
				<td><label>库存数量：</label></td>
				<td><input type="text" id="repertoryQuantity" name="repertory.repertoryQuantity" value="${repertory.repertoryQuantity }" /></td>
			</tr>
				<td>
					<input type="hidden" id="repertoryId" name="repertory.repertoryId" value="${repertory.repertoryId }"/>
					<input type="submit" class="btn  btn-primary" value="保存"/>
					<input type="button" class="btn  btn-primary" value="返回" onclick="javascript:history.back() "/>&nbsp;&nbsp;<font id="error" color="red">${error }</font>
					
				</td> 
				<tr>
			</tr>

		</table>
		</form>
	</div>
</div>