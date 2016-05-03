<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="line_products" pageEncoding="UTF-8" %>
<%@attribute name="user_elem" required="true" type="com.epam.msfrolov.freewms.model.User" %>
<%@attribute name="page_number" required="true" type="java.lang.Integer" %>
<tr>
    <td class="cell2">&nbsp;&nbsp;${user_elem.id}</td>
    <td class="cell2">&nbsp;&nbsp;${user_elem.name}</td>
    <td class="cell1">&nbsp;&nbsp;${user_elem.role.name}</td>
    <td class="cell2">
        <a href="<c:url value="${cabinet_action}">
        <c:param name="userId" value="${user_elem.id}"/>
        <c:param name="edit" value="edit"/>
        </c:url>">
            <img src="${img_path}/edit_line.png" height="20">
        </a>
    </td>
</tr>