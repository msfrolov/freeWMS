<%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="pagination_document" pageEncoding="UTF-8" %>
<ul class="pagination">
    <input type="hidden" name="page_number" value="${page_number}"/>
    <li>
        <a href="<c:url value="${receipt_document}"><c:param name="page_number" value="${page_number-1}"/></c:url>">❮</a>
    </li>
    <li><a href="#">${page_number}</a></li>
    <li>
        <a href="<c:url value="${receipt_document}"><c:param name="page_number" value="${page_number+1}"/></c:url>">❯</a>
    </li>
</ul>