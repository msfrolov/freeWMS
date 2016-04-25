<%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="line_products" pageEncoding="UTF-8" %>
<%@attribute name="product_elem" required="true" type="com.epam.msfrolov.freewms.model.Product" %>
<%@attribute name="page_number" required="true" type="java.lang.Integer" %>
<tr>
    <td class="cell2">&nbsp;&nbsp;${product_elem.id}</td>
    <td class="cell2">&nbsp;&nbsp;${product_elem.type.name}</td>
    <td class="cell1">&nbsp;&nbsp;${product_elem.name}</td>
    <td class="cell2">&nbsp;&nbsp;${product_elem.measure.name}</td>
    <td class="cell2">&nbsp;&nbsp;${product_elem.barcode}</td>
    <td class="cell1">&nbsp;&nbsp;
        <c:if test="${empty product_elem.description}"><c:forEach begin="1"
                                                                  end="10">&nbsp;</c:forEach></c:if>
        ${product_elem.description}
    </td>
    <td class="cell2">
        <a href="<c:url value="${product_card}"><c:param name="prodId" value="${product_elem.id}"/></c:url>">
            <img src="${img_path}/edit_line.png" height="20">
        </a>
    </td>
    <c:if test="${isAdmin}">
        <td class="cell2">
            <a href="<c:url value="${product_delete}">
                                 <c:param name="page_number" value="${page_number}"/>
                                 <c:param name="prodId" value="${product_elem.id}"/>
                             </c:url>">
                <img src="${img_path}/delete.png" height="20">
            </a>
        </td>
    </c:if>
</tr>