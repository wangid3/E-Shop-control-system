<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="${pageContext.request.contextPath }/TestServlet?action=add">添加</a>
	<a href="${pageContext.request.contextPath }/TestServlet?action=del">删除</a>
	<a href="${pageContext.request.contextPath }/TestServlet?action=update">更新</a>
	
	<a href="${pageContext.request.contextPath }/TestServlet?action=find">查找</a>
</body>
</html>