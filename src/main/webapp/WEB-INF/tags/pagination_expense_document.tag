<%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="pagination_receipt_document" pageEncoding="UTF-8" %>
<ul class="pagination">
    <input type="hidden" name="page_number" value="${page_number}"/>
    <li>
        <a id="" href="<c:url value="${requestScope.expense_document}">
                    <c:param name="page_number" value="${requestScope.page_number-1}"/>
                    <c:param name="sort" value="${requestScope.sort}"></c:param>
                 </c:url>">❮</a>
    </li>
    <li><a href="<c:url value="${requestScope.expense_document}">
                    <c:param name="page_number" value="${requestScope.page_number}"/>
                    <c:param name="sort" value="${requestScope.sort}"></c:param>
                 </c:url>">${requestScope.page_number}</a></li>
    <li>
        <a href="<c:url value="${requestScope.expense_document}">
                    <c:param name="page_number" value="${requestScope.page_number+1}"/>
                    <c:param name="sort" value="${requestScope.sort}"></c:param>
                </c:url>">❯</a>
    </li>
</ul>