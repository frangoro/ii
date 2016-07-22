<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Computer Inventory</title>
<spring:url value="/resources/app.js" var="appJs" />
<spring:url value="/resources/ext/ext-debug.js" var="extDebugJs" />
<spring:url value="/resources/ext/resources/css/ext-all.css"
	var="extAllCss" />
<link rel="stylesheet" type="text/css" href="${extAllCss}">
<script type="text/javascript" src="${extDebugJs}"></script>
<script type="text/javascript" src="${appJs}"></script>
</head>
<body>
</body>
</html>
