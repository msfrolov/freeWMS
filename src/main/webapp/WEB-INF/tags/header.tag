<%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="header" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<c:set var="img_path" scope="request">
    <c:url value="/img"/>
</c:set>
<c:set var="css_path" scope="request">
    <c:url value="/style"/>
</c:set>
<c:set var="sign_in_action" scope="request" value="/wms/signin"/>
<c:set var="sign_out_action" scope="request" value="/wms/signout"/>
<c:set var="home_action" scope="request" value="/wms/home"/>
<c:set var="cabinet_action" scope="request" value="/wms/cabinet"/>
<c:set var="products_catalog" scope="request" value="/wms/products_catalog"/>
<c:set var="product_card" scope="request" value="/wms/product_card"/>
<html>
<head>
    <meta charset="utf-8">
    <title>WMS</title>
    <meta name="WMS" content="Warehouse Management System">
    <link href="${css_path}/font-awesome.min.css" rel="stylesheet">
    <link href="${css_path}/common.css" rel="stylesheet">
    <link href="${css_path}/home.css" rel="stylesheet">
    <link href="${css_path}/signin.css" rel="stylesheet">
    <link href="${css_path}/table.css" rel="stylesheet">
    <c:if test="${not empty sessionScope.user}">
        <t:signout_frg/>
    </c:if>
    <c:if test="${empty sessionScope.user}">
        <t:signin_frg/>
    </c:if>
<t:menu/>