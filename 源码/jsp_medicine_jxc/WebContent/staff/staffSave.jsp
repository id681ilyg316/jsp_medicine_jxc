<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function checkForm(){
		var staffName=$("#staffName").val();
		var staffPhone=$("#staffPhone").val();
		var staffAddress=$("#staffAddress").val();
		
		if(staffName==null||staffName==""){
			$("#error").html("姓名不能为空！");
			return false;
		}
		if(staffPhone==null||staffPhone==""){
			$("#error").html("电话不能为空！");
			return false;
		}
		if(staffAddress==null||staffAddress==""){
			$("#error").html("地址不能为空！");
			return false;
		}
		return true;
	}
</script>
<div class="data_list">
	<div class="data_content">
		<form action="staff!save" onsubmit="return checkForm()">
		<table width="30%" align="center">
			<tr>
				<td><label>姓名：</label></td>
				<td><input type="text" id="staffName" name="staff.staffName" value="${staff.staffName }"/></td>
				
			</tr>
			<tr>
				<td><label>电话：</label></td>
				<td><input type="text" id="staffPhone" name="staff.staffPhone" value="${staff.staffPhone }" /></td>
			</tr>
			<tr>
				<td><label>地址：</label></td>
				<td><input type="text" id="staffAddress" name="staff.staffAddress" value="${staff.staffAddress}" /></td>
			</tr>
			<tr>
				<td>
					<input type="hidden" id="staffId" name="staffId" value="${staff.staffId }"/>
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