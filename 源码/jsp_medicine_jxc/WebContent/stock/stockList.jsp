<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function stockDelete(stockId){
		if(confirm("确定要删除这条商品信息吗?")){
			$.post("stock!delete",{stockId:stockId},
					function(result){
						var result=eval('('+result+')');
						if(result.error){
							alert(result.error);
						}else{
							alert("删除成功！");
							window.location.href="${pageContext.request.contextPath}/stock!list";
						}
					}
			);
		}}
	
</script>
<div class="data_list">
	<div class="search_content">
	
		
		
		
		
		
	
		<button class="btn btn-mini btn-primary" type="button" style="float: right;margin-bottom: 5px;" onclick="javascript:window.location='stock!preSave'">添加进货</button>
	</div>
	<div class="data_content">
		<table class="table table-bordered table-hover">
			<tr>
				<th>序号</th>
				<th>商品名称</th>
				<th>供货商名称</th>
				<th>员工名称</th>
				<th>类型</th>	
				<th>价格</th>	
				<th>数量</th>	
				<th>日期</th>	
				<th>操作</th>
			</tr>
			<c:forEach var="stock" items="${stockList }" varStatus="status">
				<tr>
					<td>${status.index+1 }</td>
					<td>${stock.stockName }</td>
					<td>${stock.pn}</td>
					<td>${stock.saleman}</td>
					<td>${stock.type }</td>	
					<td>${stock.price }</td>	
					<td>${stock.count }</td>	
					<td>${stock.date }</td>
					<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='stock!preSave?stockId=${stock.stockId}'">修改</button>&nbsp;&nbsp;<button class="btn btn-mini btn-danger" type="button"  onclick="stockDelete('${stock.stockId}')">删除</button></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="pagination pagination-centered">
  		<ul>
  			${pageCode }
  		</ul>
	</div>
</div>