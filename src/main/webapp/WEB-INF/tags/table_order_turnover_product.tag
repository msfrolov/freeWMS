<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %><%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="turnover_list" pageEncoding="UTF-8" %>
<%@attribute name="products_list" required="true" type="java.util.List" %>
<table id="Table1">
    <tr>
        <td class="cell3">№</td>
        <td class="cell3">Name</td>
        <td class="cell3">Balance</td>
    </tr>
    <c:forEach items="${turnover_list}" var="item" varStatus="status">
        <tags:line_order_product page_number="${requestScope.page_number}" item="${item}"
                                 status="${status.count}" page_size="${requestScope.page_size}"/>
    </c:forEach>
</table>



