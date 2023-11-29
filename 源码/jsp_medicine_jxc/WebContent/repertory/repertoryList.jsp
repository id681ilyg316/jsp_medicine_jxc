<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function repertoryDelete(repertoryId){
		if(confirm("确定要删除这条库存信息吗?")){
			$.post("repertory!delete",{repertoryId:repertoryId},
					function(result){
						var result=eval('('+result+')');
						if(result.error){
							alert(result.error);
						}else{
							alert("删除成功！");
							window.location.href="${pageContext.request.contextPath}/repertory!list";
						}
					}
			);
		}}
	
</script>
<div class="data_list">
	<div class="search_content">
		<button class="btn btn-mini btn-primary" type="button" style="float: right;margin-bottom: 5px;" onclick="javascript:window.location='repertory!preSave'">添加库存商品</button>
	</div>
	<div class="data_content">
		<table class="table table-bordered table-hover">
			<tr>
				<th>序号</th>
				<th>商品名称</th>
				<th>商品数量</th>
				<th>操作</th>
			</tr>
			<c:forEach var="repertory" items="${repertoryList }" varStatus="status">
				<tr>
					<td>${status.index+1 }</td>
					<td>${repertory.repertoryName }</td>
					<td>${repertory.repertoryQuantity }</td>	
					<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='repertory!preSave?repertoryId=${repertory.repertoryId}'">修改</button>&nbsp;&nbsp;<button class="btn btn-mini btn-danger" type="button"  onclick="repertoryDelete('${repertory.repertoryId}')">删除</button></td>
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