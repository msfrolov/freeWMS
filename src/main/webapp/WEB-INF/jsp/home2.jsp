<%--@elvariable id="user" type="com.epam.msfrolov.freewms.model.User"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<%--todo: use jstl in href--%>
Hello, ${user.name}! You are logged in! Now you may <a href="${pageContext.request.contextPath}/wms/logout">log out</a>.
</body>
</html>