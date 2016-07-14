<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Inventory</title>
<spring:url value="/resources/app.js" var="appJs" />
<spring:url value="/resources/ext/ext-debug.js" var="extDebugJs" />
<spring:url value="/resources/ext/resources/css/ext-all.css" var="extAllCss" /> 
<link rel="stylesheet" type="text/css" href="${extAllCss}"> 
<script type="text/javascript" src="${extDebugJs}"></script> 
<script type="text/javascript" src="${appJs}"></script> 
</head>
<body>
</body>
</html>
