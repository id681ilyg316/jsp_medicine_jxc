<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="span12">
		<div class="actionPage">
			<div class="navi">
				<c:choose>
					<c:when test="${navCode==null }">
						当前位置：<a href="${pageContext.request.contextPath}/main.jsp">主页</a>
					</c:when>
					<c:otherwise>
						${navCode }
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</div>