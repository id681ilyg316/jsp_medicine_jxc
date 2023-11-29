<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function providerDelete(providerId){
		if(confirm("确定要删除这条供应商信息吗?")){
			$.post("provider!delete",{providerId:providerId},
					function(result){
						var result=eval('('+result+')');
						if(result.error){
							alert(result.error);
						}else{
							alert("删除成功！");
							window.location.href="${pageContext.request.contextPath}/provider!list";
						}
					}
			);
		}}
	
</script>
<div class="data_list">
	<div class="search_content">
	
		
		
		
		
		
	
		<button class="btn btn-mini btn-primary" type="button" style="float: right;margin-bottom: 5px;" onclick="javascript:window.location='provider!preSave'">添加供应商</button>
	</div>
	<div class="data_content">
		<table class="table table-bordered table-hover">
			<tr>
				<th>序号</th>
				<th>供应商编号</th>
				<th>供应商电话</th>
				<th>供应商地址</th>	
				<th>法人代表</th>	
				<th>操作</th>
			</tr>
			<c:forEach var="provider" items="${providerList }" varStatus="status">
				<tr>
					<td>${status.index+1 }</td>
					<td>${provider.providerNo }</td>
					<td>${provider.providerPhone}</td>
					<td>${provider.providerAddress }</td>	
					<td>${provider.providerlegal }</td>	
					<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='provider!preSave?providerId=${provider.providerId}'">修改</button>&nbsp;&nbsp;<button class="btn btn-mini btn-danger" type="button"  onclick="providerDelete('${provider.providerId}')">删除</button></td>
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