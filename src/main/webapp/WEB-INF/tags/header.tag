<%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="header" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<t:action_name/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="WMS" content="Warehouse Management System">
    <title>WMS</title>
    <t:css_path/>
    <c:choose>
    <c:when test="${not empty sessionScope.user}">
        <t:signout_frg/>
    </c:when>
    <c:otherwise>
        <t:signin_frg/>
    </c:otherwise>
    </c:choose>
<t:menu/>