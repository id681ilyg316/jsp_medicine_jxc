<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function checkForm(){
		var sellName=$("#sellName").val();
		var providerName=$("#providerName").val();
		var saleman=$("#saleman").val();
		var price=$("#price").val();
		var count=$("#count").val();
		var cn=$("#cn").val();
		var type=$("#type").val();
		
		if(staffName==null||staffName==""){
			$("#error").html("学号不能为空！");
			return false;
		}
		if(providerName==null||providerName==""){
			$("#error").html("供应商姓名不能为空！");
			return false;
		}
		if(saleman==null||saleman==""){
			$("#error").html("员工姓名不能为空！");
			return false;
		}
		if(price==null||price==""){
			$("#error").html("价格不能为空！");
			return false;
		}
		if(count==null||count==""){
			$("#error").html("数量不能为空！");
			return false;
		}
		if(cn==null||cn==""){
			$("#error").html("客户姓名不能为空！");
			return false;
		}
		if(type==null||type==""){
			$("#error").html("类型不能为空！");
			return false;
		}
		return true;
	}
</script>
<div class="data_list">
	<div class="data_content">
		<form action="sell!save" onsubmit="return checkForm()">
	<table width="30%" align="center">
			<tr>
				<td><label>商品名称：</label></td>
				<td><input type="text" id="sellName" name="sell.sellName" value="${sell.sellName }"/></td>
				
			</tr>
			<tr>
				<td><label>供货商名称：</label></td>
				<td><input type="text" id="providerName" name="sell.pn" value="${sell.pn}" /></td>
			</tr>
			<tr>
				<td><label>员工名称：</label></td>
				<td><input type="text" id="saleman" name="sell.saleman" value="${sell.saleman}" /></td>
			</tr>
			<tr>
				<td><label>数量：</label></td>
				<td><input type="text" id="count" name="sell.count" value="${sell.count}" /></td>
			</tr>
			<tr>
				<td><label>价格：</label></td>
				<td><input type="text" id="price" name="sell.price" value="${sell.price}" /></td>
			</tr>
			<tr>
				<td><label>客户：</label></td>
				<td><input type="text" id="cn" name="sell.cn" value="${sell.cn}" /></td>
			</tr>
			<tr>
				<td><label>类型：</label></td>
				<td><input type="text" id="type" name="sell.type" value="${sell.type}" /></td>
			</tr>
			<tr>
				<td>
					<input type="hidden" id="sellId" name="sellId" value="${sell.sellId }"/>
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