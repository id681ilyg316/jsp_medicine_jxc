<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function staffDelete(staffId){
		if(confirm("确定要删除这条员工信息吗?")){
			$.post("staff!delete",{staffId:staffId},
					function(result){
						var result=eval('('+result+')');
						if(result.error){
							alert(result.error);
						}else{
							alert("删除成功！");
							window.location.href="${pageContext.request.contextPath}/staff!list";
						}
					}
			);
		}}
	
</script>
<div class="data_list">
	<div class="search_content">
	
		
		
		
		
		
	
		<button class="btn btn-mini btn-primary" type="button" style="float: right;margin-bottom: 5px;" onclick="javascript:window.location='staff!preSave'">添加员工</button>
	</div>
	<div class="data_content">
		<table class="table table-bordered table-hover">
			<tr>
				<th>序号</th>
				<th>员工姓名</th>
				<th>员工电话</th>
				<th>员工地址</th>	
				<th>操作</th>
			</tr>
			<c:forEach var="staff" items="${staffList }" varStatus="status">
				<tr>
					<td>${status.index+1 }</td>
					<td>${staff.staffName }</td>
					<td>${staff.staffPhone}</td>
					<td>${staff.staffAddress }</td>	
					<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='staff!preSave?staffId=${staff.staffId}'">修改</button>&nbsp;&nbsp;<button class="btn btn-mini btn-danger" type="button"  onclick="staffDelete('${staff.staffId}')">删除</button></td>
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