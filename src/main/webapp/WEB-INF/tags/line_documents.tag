<%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="line_products" pageEncoding="UTF-8" %>
<%@attribute name="product_elem" required="true" type="java.util.Map" %>
<%@attribute name="page_number" required="true" type="java.lang.Integer" %>
<tr>
    <td class="cell2">&nbsp;&nbsp;${product_elem.type}</td>
    <td class="cell2">&nbsp;&nbsp;${product_elem.id}</td>
    <td class="cell1">&nbsp;&nbsp;${product_elem.date}</td>
    <td class="cell2">&nbsp;&nbsp;${product_elem.sender}</td>
    <td class="cell2">&nbsp;&nbsp;${product_elem.recipient}</td>
    <td class="cell1">&nbsp;&nbsp;<c:if test="${empty product_elem.comment}"><c:forEach begin="1" end="10">&nbsp;</c:forEach></c:if>${product_elem.comment}</td>
</tr>