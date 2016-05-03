<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %><%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="table_products" pageEncoding="UTF-8" %>
<%@attribute name="users_list" required="true" type="java.util.List" %>

<table id="Table1">
    <tr>
        <td class="cell3">ID</td>
        <td class="cell3">Name</td>
        <td class="cell3">Role</td>
        <td class="cell3">Edit</td>
    </tr>
    <c:forEach items="${users_list}" var="user_elem">
        <tags:line_users page_number="${requestScope.page_number}" user_elem="${user_elem}"/>
    </c:forEach>
</table>
