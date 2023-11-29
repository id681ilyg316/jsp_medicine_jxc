<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function checkForm(){
		var clientName=$("#clientName").val();
		var clientPhone=$("#clientPhone").val();
		var clientAddress=$("#clientAddress").val();
		
		if(clientName==null||clientName==""){
			$("#error").html("姓名不能为空！");
			return false;
		}
		if(clientPhone==null||clientPhone==""){
			$("#error").html("电话不能为空！");
			return false;
		}
		if(clientAddress==null||clientAddress==""){
			$("#error").html("地址不能為空");
			return false;
		}
		return true;
	}
</script>
<div class="data_list">
	<div class="data_content">
		<form action="client!save" onsubmit="return checkForm()">
		<table width="30%" align="center">
			<tr>
				<td><label>姓名：</label></td>
				<td><input type="text" id="clientName" name="client.clientName" value="${client.clientName }"/></td>
				
			</tr>
			<tr>
				<td><label>电话：</label></td>
				<td><input type="text" id="clientPhone" name="client.clientPhone" value="${client.clientPhone }" /></td>
			</tr>
			<tr>
				<td><label>地址：</label></td>
				<td><input type="text" id="clientAddress" name="client.clientAddress" value="${client.clientAddress}" /></td>
			</tr>
			<tr>
				<td>
					<input type="hidden" id="clientId" name="clientId" value="${client.clientId }"/>
					<input type="submit" class="btn  btn-primary" value="保存"/>
				</td> 
				<td colspan="4">
					<input type="button" class="btn  btn-primary" value="返回" onclick="javascript:history.back() "/>&nbsp;&nbsp;<font id="error" color="red">${error }</font>
				</td>
			</tr>

		</table>
		</form>
	</div>
</div>