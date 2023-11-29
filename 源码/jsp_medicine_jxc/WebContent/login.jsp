<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>药品进销存管理系统登录</title>
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<SCRIPT language=javascript type=text/javascript>
	
	function loadimage(){
		document.getElementById("randImage").src = "image.jsp?"+Math.random();
	}
</SCRIPT>

<STYLE type=text/css>
BODY {
	TEXT-ALIGN: center;
	PADDING-BOTTOM: 0px;
	BACKGROUND-COLOR: #ddeef2;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	PADDING-TOP: 0px
}

A:link {
	COLOR: #000000;
	TEXT-DECORATION: none
}

A:visited {
	COLOR: #000000;
	TEXT-DECORATION: none
}

A:hover {
	COLOR: #ff0000;
	TEXT-DECORATION: underline
}

A:active {
	TEXT-DECORATION: none
}

.input {
	BORDER-BOTTOM: #ccc 1px solid;
	BORDER-LEFT: #ccc 1px solid;
	LINE-HEIGHT: 20px;
	WIDTH: 182px;
	HEIGHT: 20px;
	BORDER-TOP: #ccc 1px solid;
	BORDER-RIGHT: #ccc 1px solid
}

.input1 {
	BORDER-BOTTOM: #ccc 1px solid;
	BORDER-LEFT: #ccc 1px solid;
	LINE-HEIGHT: 20px;
	WIDTH: 120px;
	HEIGHT: 20px;
	BORDER-TOP: #ccc 1px solid;
	BORDER-RIGHT: #ccc 1px solid;
}
</STYLE>
</head>
<body>
<FORM id=adminlogin  method=post
	name=adminlogin action="admin!login">

<TABLE style="WIDTH: 100%; HEIGHT: 100%" border=0
	cellSpacing=0 cellPadding=0>
	<TBODY>
		<TR>
			<TD height=150>&nbsp;</TD>
		</TR>
		<TR style="HEIGHT: 254px">
			<TD>
			<DIV style="WIDTH: 936px; margin-left: 770px; margin-bottom: -2px;"><IMG
				style="DISPLAY: block" src="images/1.jpg"></DIV>
			<DIV style="BACKGROUND-COLOR: #278296">
			<DIV style="MARGIN: 0px auto; WIDTH: 936px">
				<span style=" position: absolute; margin-left:-700px; margin-top: 40px;"><img alt="d" src="images/2.png"></span>
			<DIV
				style="BACKGROUND: url(images/body_052.jpg) no-repeat; HEIGHT: 155px; margin-left:385px;" >
			<DIV
				style="WIDTH: 265px; HEIGHT: 125px; _height: 95px; margin-left:76px; ">
			<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%">
				<TBODY>
					<TR>
						<TD style="HEIGHT: 45px"><INPUT type="text" class=input value="${admin.adminName }" name="admin.adminName" id="adminName"></TD>
					</TR>
					<TR>
						<TD><INPUT type="password" class=input value="${admin.password }" name="admin.password" id="password"/></TD>
					</TR>
					<TR>
						<TD style="HEIGHT: 50px;"><INPUT  class=yzm size=8
							type=text value="${imageCode }" name="imageCode" id="imageCode"> <img
							onclick="javascript:loadimage();" title="换一张试试" name="randImage"
							id="randImage" src="image.jsp" width="60" height="20" border="1"
							align="absmiddle"></TD>
					</TR>
				</TBODY>
			</TABLE>
			</DIV>
			<DIV style="HEIGHT: 1px; CLEAR: both"></DIV>
			<DIV style="WIDTH: 380px; CLEAR: both; margin-left:36px;">
			<TABLE border=0 cellSpacing=0 cellPadding=0 width=300>
				<TBODY>
					<TR>
						<TD width=100 align=right><INPUT
							style="BORDER-RIGHT-WIDTH: 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px"
							id=btnLogin src="images/btn1.jpg" type=image name=btnLogin onclick="javascript:adminlogin.submit();return false;"></TD>
						<TD width=100 align=middle><INPUT
							style="BORDER-RIGHT-WIDTH: 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px"
							id=btnReset src="images/btn2.jpg" type=image name=btnReset onclick="javascript:adminlogin.reset();return false;"></TD>
					</TR>
				</TBODY>
			</TABLE>
			</DIV>
			</DIV>
			</DIV>
			</DIV>
			
			</TD>
		</TR>
		<TR style="HEIGHT: 30%">
			<TD>&nbsp;</TD>
		</TR>
	</TBODY>
</TABLE>
</FORM>
</body>
</html>
<script type=text/javascript>
	if('${error}'!=''){
		alert('${error}');
	}
</script>