<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function checkForm(){
		var providerNo=$("#providerNo").val();
		var providerPhone=$("#providerPhone").val();
		var providerAddress=$("#providerAddress").val();
		var providerlegal=$("#providerlegal").val();
		
		if(providerNo==null||providerNo==""){
			$("#error").html("请输入编号！");
			return false;
		}
		if(providerPhone==null||providerPhone==""){
			$("#error").html("请输入电话！");
			return false;
		}
		if(providerAddress==null||providerAddress==""){
			$("#error").html("请输入地址");
			return false;
		}
		if(providerlegal==null||providerlegal==""){
			$("#error").html("请输入法人代表");
			return false;
		}
		return true;
	}
</script>
<div class="data_list">
	<div class="data_content">
		<form action="provider!save" onsubmit="return checkForm()">
		<table width="30%" align="center">
			<tr>
				<td><label>姓名：</label></td>
				<td><input type="text" id="providerNo" name="provider.providerNo" value="${provider.providerNo }"/></td>
				
			</tr>
			<tr>
				<td><label>电话：</label></td>
				<td><input type="text" id="providerPhone" name="provider.providerPhone" value="${provider.providerPhone }" /></td>
			</tr>
			<tr>
				<td><label>地址：</label></td>
				<td><input type="text" id="providerAddress" name="provider.providerAddress" value="${provider.providerAddress}" /></td>
			</tr>
			<tr>
				<td><label>法人代表：</label></td>
				<td><input type="text" id="providerlegal" name="provider.providerlegal" value="${provider.providerlegal}" /></td>
			</tr>
			<tr>
				<td>
					<input type="hidden" id="providerId" name="providerId" value="${provider.providerId }"/>
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