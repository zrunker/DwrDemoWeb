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

<title>Dwr发送</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript" src="<%=basePath%>dwr/engine.js"></script>  
<script type="text/javascript" src="<%=basePath%>dwr/util.js"></script>  
<script type="text/javascript" src="<%=basePath%>dwr/interface/DwrPush.js"></script>  

<script type="text/javascript">
	// dwr反转激活
	dwr.engine.setActiveReverseAjax(true);

	// 发送推送消息
	function send() {
		var msg = document.getElementById("msg").value;
		alert(msg);
		// DwrPush是在dwr.xml文件中声明的javascript属性
		DwrPush.send2(msg);
	}
	
	// 接收推送消息
	function revice(msg) {
		alert(msg);
		document.getElementById("send_content").innerHTML = msg;
	}
</script>
</head>

<body>
	<center>
		<p id="send_content"></p>
		
		<input type="text" name="msg" id="msg" maxlength="50">
		<input type="button" id="send" value="发送信息" onclick="send()">
	</center>
</body>
</html>
