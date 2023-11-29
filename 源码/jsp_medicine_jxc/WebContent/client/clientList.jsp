<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function clientDelete(clientId){
		if(confirm("确定要删除这条客户信息吗?")){
			$.post("client!delete",{clientId:clientId},
					function(result){
						var result=eval('('+result+')');
						if(result.error){
							alert(result.error);
						}else{
							alert("删除成功！");
							window.location.href="${pageContext.request.contextPath}/client!list";
						}
					}
			);
		}}
	
</script>
<div class="data_list">
	<div class="search_content">



		<button class="btn btn-mini btn-primary" type="button" style="float: right;margin-bottom: 5px;" onclick="javascript:window.location='client!preSave'">添加客户</button>
	</div>
	<div class="data_content">
		<table class="table table-bordered table-hover">
			<tr>
				<th>序号</th>
				<th>客户姓名</th>
				<th>客户地址</th>
				<th>客户电话</th>
					
				<th>操作</th>
			</tr>
			<c:forEach var="client" items="${clientList }" varStatus="status">
				<tr>
					<td>${status.index+1 }</td>
					<td>${client.clientName }</td>
					<td>${client.clientPhone}</td>
					<td>${client.clientAddress }</td>	
					<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='client!preSave?clientId=${client.clientId}'">修改</button>&nbsp;&nbsp;<button class="btn btn-mini btn-danger" type="button"  onclick="clientDelete('${client.clientId}')">删除</button></td>
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