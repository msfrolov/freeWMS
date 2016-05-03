<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %><%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag description="table_products" pageEncoding="UTF-8" %>
<%@ attribute name="document_list" required="true" type="java.util.List" %>
<table id="Table1">
    <tr>
        <td class="cell3">Type</td>
        <td class="cell3">ID</td>
        <td class="cell3">Date</td>
        <td class="cell3">Sender</td>
        <td class="cell3">Recipient</td>
        <td class="cell3">Comment</td>
    </tr>
    <c:forEach items="${document_list}" var="product_elem">
        <tags:line_documents page_number="${requestScope.page_number}" product_elem="${product_elem}"/>
    </c:forEach>
</table>