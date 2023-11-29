<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="${pageContext.request.contextPath}/style/studentInfo.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>

<script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function setDateTime(){
		var date=new Date();
		var day=date.getDay();
		var week;
		switch(day){
		case 0:week="星期日";break;
		case 1:week="星期一";break;
		case 2:week="星期二";break;
		case 3:week="星期三";break;
		case 4:week="星期四";break;
		case 5:week="星期五";break;
		case 6:week="星期六";break;
		}
		var today=date.getFullYear()+"年"+(date.getMonth()+1)+"月"+date.getDate()+"日  "+week+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
		document.getElementById("today").innerHTML=today;
	}
	
	window.setInterval("setDateTime()", 1000);
	
</script>
<script type="text/javascript">
	function logOut(){
		if(confirm("您确定要退出系统吗？")){
			window.location.href="admin!logOut";
		}
	}
</script>
<div style="width: 1200px; margin-left: 90px;">

<div class="row-fluid">
	<div class="span12">
		<div class="head">
			<div class="headLeft">
				<div style="margin-top: 20px;"><img src="${pageContext.request.contextPath}/images/2.png"/></div>
			</div>
			<div class="headRight">
				欢迎管理员：<font color="red">${currentAdmin.adminName }</font>&nbsp;&nbsp;&nbsp;<font id="today" class="currentDateTime"></font>
			</div>
		</div>
	</div>
</div>

<div class="row-fluid">
	<div class="span12">
		<div class="navbar">
		  <div class="navbar-inner">
		    <a class="brand" href="main.jsp">首页</a>
		    <ul class="nav">
		    	<li class="dropdown">
                       <a href="#" class="dropdown-toggle" data-toggle="dropdown">进货信息管理 <b class="caret"></b></a>
                       <ul class="dropdown-menu">
                         <li><a href="stock!preSave">进货添加</a></li>
                         <li><a href="stock!list">药品信息维护</a></li>
                         <li><a href="stock!chaxun">药品信息查询</a></li>
                       </ul>
                   </li>
		      	<li class="dropdown">
                       <a href="#" class="dropdown-toggle" data-toggle="dropdown">销售信息管理 <b class="caret"></b></a>
                       <ul class="dropdown-menu">
                         <li><a href="sell!preSave">销售添加</a></li>
                         <li><a href="sell!list">销售信息维护</a></li>
                          <li><a href="sell!chaxun">销售信息查询</a></li>
                       </ul>
                   </li>
                   <li class="dropdown">
                       <a href="#" class="dropdown-toggle" data-toggle="dropdown">库存信息管理 <b class="caret"></b></a>
                       <ul class="dropdown-menu">
                         <li><a href="repertory!preSave">库存添加</a></li>
                         <li><a href="repertory!list">库存信息维护</a></li>
                         <li><a href="repertory!chaxun">库存信息查询</a></li>
                       </ul>
                        
                   </li>
                   
                   <li class="dropdown">
                       <a href="#" class="dropdown-toggle" data-toggle="dropdown">员工信息管理 <b class="caret"></b></a>
                       <ul class="dropdown-menu">
                         <li><a href="staff!preSave">员工添加</a></li>
                         <li><a href="staff!list">员工信息维护</a></li>
                         <li><a href="staff!chaxun">员工信息查询</a></li>
                       </ul><li class="dropdown">
                       <a href="#" class="dropdown-toggle" data-toggle="dropdown">客户信息管理 <b class="caret"></b></a>
                       <ul class="dropdown-menu">
                         <li><a href="client!preSave">客户添加</a></li>
                         <li><a href="client!list">客户信息维护</a></li>
                         <li><a href="client!chaxun">客户信息查询</a></li>
                         
                       </ul>
                   </li>
                   
                   </li>
                   
                   <li class="dropdown">
                       <a href="#" class="dropdown-toggle" data-toggle="dropdown">供应商信息管理 <b class="caret"></b></a>
                       <ul class="dropdown-menu">
                         <li><a href="provider!preSave">供应商添加</a></li>
                         <li><a href="provider!list">供应商信息维护</a></li>
                            <li><a href="provider!toChanXun">供应商信息查询</a></li>
                       </ul>
                   </li>
                   
                   
                   
		        <li class="dropdown">
                       <a href="#" class="dropdown-toggle" data-toggle="dropdown">系统信息管理 <b class="caret"></b></a>
                       <ul class="dropdown-menu">
                        <li><a href="admin!preSave">修改密码</a></li>
                         <li class="divider"></li>
                         <li><a href="javascript:logOut()">退出系统</a></li>
                       </ul>
                   </li>
		    </ul>
		  </div>
		</div>
	</div>
</div>

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




<form action="${pageContext.request.contextPath}/stock!chaxun" method="post">
			<table align="center" style="margin-top: 20px;">
				<tr>
					<td><lable>姓名：</lable></td>
					<td><input type="text" id="" name="stockName" value="${stock.stockName }"  style="height:30px;" /></td>
					<td><input type="submit" class="btn btn-primary" value="查询" style="margin: 5px;"/></td>
				</tr>		
			</table>
		</form>
		
		<div class="data_content">
			<table class="table table-bordered table-hover">
			<tr>
				
				<th>商品名称</th>
				<th>日期</th>
				<th>pn</th>
				<th>商品数量</th>	
				<th>商品类型</th>	
				<th>商品价格</th>	
				<th>人员</th>	
			</tr>
			
	<tr>
					
					<td>${stock.stockName  }</td>
					<td>${stock.date  }</td>
					<td>${stock.pn }</td>
					<td>${stock.count }</td>
					<td>${stock.type }</td>
					<td>${stock.price }</td>
					<td>${stock.saleman }</td>
			
				</tr>
		</table>

</div>






