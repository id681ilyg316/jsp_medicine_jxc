<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script type="text/javascript">
	function logOut(){
		if(confirm("您确定要退出系统吗？")){
			window.location.href="admin!logOut";
		}
	}
</script>
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
                          <li><a href="stock!chaxun">进货信息查询</a></li>
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
                         <li><a href="provider!chaxun">供应商信息查询</a></li>
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