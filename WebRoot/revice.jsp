<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>Dwr接收</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>
<script type="text/javascript" src="<%=basePath%>dwr/engine.js"></script>  
<script type="text/javascript" src="<%=basePath%>dwr/util.js"></script>  

<script type="text/javascript">
	// dwr反转激活
	dwr.engine.setActiveReverseAjax(true);

	// 接收推送消息
	function revice(msg) {
		alert(msg);
		document.getElementById("revice_content").innerHTML = msg;
	}
</script>

<body>
	<center>
		<p id="revice_content"></p>
	</center>
</body>
</html>
