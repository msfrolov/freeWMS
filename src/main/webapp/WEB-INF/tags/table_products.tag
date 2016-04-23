<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %><%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="table_products" pageEncoding="UTF-8" %>
<table id="Table1">
    <tr>
        <td class="cell3">ID</td>
        <td class="cell3">Type</td>
        <td class="cell3">Name</td>
        <td class="cell3">Measure</td>
        <td class="cell3">Barcode</td>
        <td class="cell3">Description</td>
        <td class="cell3">Edit</td>
        <c:if test="${isAdmin}">
            <td class="cell3">Delete</td>
        </c:if>
    </tr>
    <c:forEach items="${products_list}" var="product_elem">
        <tags:line_products/>
    </c:forEach>
</table>