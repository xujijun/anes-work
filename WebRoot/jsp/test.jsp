<%@page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>
<script src="<%=basePath%>/views/resources/js-plug/jquery/jquery-1.9.1.js"  charset="UTF-8" type="text/javascript"></script>
</head>
<body>
<table>
	<tr>
		<th>请求地址</th>
		<td><input type="text" id="url" value="<%=basePath%>/api/authx/pc/login" size="80"/></td>
    </tr>
	<tr>
		<th>请求参数</th>
		<td><textarea id="params" rows="8" cols="100">code=test147&password=25D55AD283AA400AF464C76D713C07AD</textarea></td>
    </tr>
	<tr><td height="40" colspan="2" align="center"><input type="button" value="提交" onClick="goRequest()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="button" name="button1" value="清除" onClick="resetForm();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="button" name="button2" value="粘贴" onClick="pasteData();"></td></tr>
	<tr>
		<th>返回结果</th>
		<td><textarea id="response" rows="20" cols="100"></textarea></td>
    </tr>	
</table>
<script type="text/javascript">
function resetForm(){
	$("#params").val("");
	$("#response").val("");
}
function pasteData(){
	$("#jsonData").val(window.clipboardData.getData("Text"));
}

function goRequest()
{	
	$.post($("#url").val(),$("#params").val(),function(resultBean, status, xhRequest)
	{
		 $("#response").val(JSON.stringify(resultBean));
		// $("#response").val(JSON.stringify(resultBean, null, "\t"));
	},'json');	
	
}
</script>
</body>
</html>