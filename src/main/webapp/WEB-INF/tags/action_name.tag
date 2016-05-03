<%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="action_name" pageEncoding="UTF-8" %>
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
<c:set var="product_delete" scope="request" value="/wms/product_delete"/>
<c:set var="product_card" scope="request" value="/wms/product_card"/>
<c:set var="receipt_document" scope="request" value="/wms/receipt_document"/>
<c:set var="move_document" scope="request" value="/wms/move_document"/>
<c:set var="expense_document" scope="request" value="/wms/expense_document"/>
<c:set var="balance_products" scope="request" value="/wms/balance_products"/>
<c:set var="turnover_products" scope="request" value="/wms/turnover_products"/>
<c:set var="document_journal" scope="request" value="/wms/document_journal"/>
<c:set var="users_catalog" scope="request" value="/wms/users_catalog"/>