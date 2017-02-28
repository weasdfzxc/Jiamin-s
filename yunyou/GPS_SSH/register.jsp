<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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

<title>注册</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<center>
		<s:form action="regist-json" method="post">
			<s:textfield name="userName" label="用户名"></s:textfield>
			<s:password name="password" label="密码"></s:password>
			<s:textfield name="email" label="邮箱（取回密码的唯一途径）"></s:textfield>
			<s:radio label="用户类型" name="usertType"  list="#{'0':'东秦学子','1':'社团管理员','2':'学生会管理员','3':'教师'}"></s:radio>
			<s:submit value="提交"></s:submit>
		</s:form>
	</center>
</body>
</html>
