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

<title>首页</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" charset="utf-8">
	var XHR = false;
	function CreateXHR(){
	    var xhr;
 		if(window.ActiveXObject) { // 判断是否支持ActiveX控件
    		 xhr = new ActiveXObject("Microsoft.XMLHTTP");   // 通过实例化ActiveXObject的一个新实例来创建XMLHTTPRequest对象
 		}else if(window.XMLHTTPRequest){   // 判断是否把XMLHTTPRequest实现为一个本地javascript对象
   		xhr = new XMLHTTPRequest();  // 创建XMLHTTPRequest的一个实例（本地javascript对象）
    } 
 		return xhr;
	}

	 function sendRequest(){
		 var userName = document.getElementById("userName").value;
		 alert(userName);
		 var password = document.getElementById("password").value;
		 var param = "userName="+userName+"&password"+password;
		XHR = new window.XMLHttpRequest();
		if(XHR){
			XHR.open("GET","login-json?"+param,false);
			XHR.send(null);
			XHR.onreadystatechange = resultHander();
		}
	}
	function resultHander(){
		if(XHR.readyState == 4 && XHR.status == 200){
			alert(XHR.responseText);
			var userObj = JSON.parse(XHR.responseText);
			var userStr ="";
			userStr += ('<p>name='+userObj.USER.username+'</p>');
			alert(userStr);
			document.getElementById("jsonDiv").innerHTML = userStr;
		}
	}
	
</script>
</head>

<body>
	<p>成功登陆</p>
	<center>
	<s:form action="login-json" method="post">
	<s:textfield name="userName"  label="用户名"></s:textfield>
	<s:password name="password"  label="密码"></s:password>
	<s:submit value="提交"></s:submit>
	
	</s:form>
	<input type="text" id="userName" value="StarX" >
	<input type="password" id="password" value="0">
	<input type="button" value="提交" onclick="sendRequest()">
		<s:property value="location"></s:property>
		<br />
		<s:iterator value="messages" >
			${title}
			<br />
		</s:iterator>
		<div id="jsonDiv"></div>
		<s:debug></s:debug>
	</center>
</body>
</html>
