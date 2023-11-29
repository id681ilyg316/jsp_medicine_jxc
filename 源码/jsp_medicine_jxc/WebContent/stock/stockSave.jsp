<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function checkForm(){
		var stockName=$("#stockName").val();
		var providerName=$("#providerName").val();
		var saleman=$("#saleman").val();
		var count=$("#count").val();
		var price=$("#price").val();
		var type=$("#type").val();
		if(stockName==null||stockName==""){
			$("#error").html("姓名不能为空！");
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
		if(count==null||count==""){
			$("#error").html("数量不能为空！");
			return false;
		}
		if(price==null||price==""){
			$("#error").html("价格不能为空！");
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
		<form action="stock!save" onsubmit="return checkForm()">
	<table width="30%" align="center">
			<tr>
				<td><label>商品名称：</label></td>
				<td><input type="text" id="stockName" name="stock.stockName" value="${stock.stockName }"/></td>
				
			</tr>
			<tr>
				<td><label>供货商名称：</label></td>
				<td><input type="text" id="providerName" name="stock.pn" value="${stock.pn}" /></td>
			</tr>
			<tr>
				<td><label>员工名称：</label></td>
				<td><input type="text" id="saleman" name="stock.saleman" value="${stock.saleman}" /></td>
			</tr>
			<tr>
				<td><label>数量：</label></td>
				<td><input type="text" id="count" name="stock.count" value="${stock.count}" /></td>
			</tr>
			<tr>
				<td><label>价格：</label></td>
				<td><input type="text" id="price" name="stock.price" value="${stock.price}" /></td>
			</tr>
			<tr>
				<td><label>日期：</label></td>
				<td><input type="text" id="date" name="stock.date" value="${stock.date}" /></td>
			</tr>
			<tr>
				<td><label>类型：</label></td>
				<td><input type="text" id="type" name="stock.type" value="${stock.type}" /></td>
			</tr>
		
			<tr>
				<td>
					<input type="hidden" id="stockId" name="stockId" value="${stock.stockId }"/>
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