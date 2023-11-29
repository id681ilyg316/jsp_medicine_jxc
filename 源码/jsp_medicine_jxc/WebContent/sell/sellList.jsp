<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function sellDelete(sellId){
		if(confirm("确定要删除这条商品信息吗?")){
			$.post("sell!delete",{sellId:sellId},
					function(result){
						var result=eval('('+result+')');
						if(result.error){
							alert(result.error);
						}else{
							alert("删除成功！");
							window.location.href="${pageContext.request.contextPath}/sell!list";
						}
					}
			);
		}}
	
</script>
<div class="data_list">
	<div class="search_content">
	
		
		
		
		
		
	
		<button class="btn btn-mini btn-primary" type="button" style="float: right;margin-bottom: 5px;" onclick="javascript:window.location='sell!preSave'">添加进货</button>
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
				<th>客户</th>	
				<th>操作</th>
			</tr>
			<c:forEach var="sell" items="${sellList }" varStatus="status">
				<tr>
					<td>${status.index+1 }</td>
					<td>${sell.sellName }</td>
					<td>${sell.pn}</td>
					<td>${sell.saleman}</td>
					<td>${sell.type }</td>	
					<td>${sell.price }</td>	
					<td>${sell.count }</td>	
					<td>${sell.cn }</td>
					<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='sell!preSave?sellId=${sell.sellId}'">修改</button>&nbsp;&nbsp;<button class="btn btn-mini btn-danger" type="button"  onclick="sellDelete('${sell.sellId}')">删除</button></td>
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