<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Exception/Error Details</title>
</head>
<body>
<%--if (statusCode != 500) {--%>
<%--<h3>Error Details</h3>--%>
<%--<strong>Status Code</strong>:" + statusCode + "<br>--%>
<%--<strong>Requested URI</strong>:" + requestUri--%>
<%--} else {--%>
<h3>Exception Details</h3>
<ul>
    <li>Servlet Name:" + servletName + "</li>
    <li>Exception Name:" + throwable.getClass().getName() + "</li>
    <li>Requested URI:" + requestUri + "</li>
    <li>Exception Message:" + throwable.getMessage() + "</li>
</ul>
<br><br>
<a href="<c:redirect url="/wms/home"/></a>
</body>
</html>