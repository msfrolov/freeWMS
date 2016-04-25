<%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="line_products" pageEncoding="UTF-8" %>
<%@attribute name="item" required="true" type="com.epam.msfrolov.freewms.model.TableLine" %>
<%@attribute name="page_number" required="true" type="java.lang.Integer" %>
<%@attribute name="status" required="true" type="java.lang.Integer" %>
<%@attribute name="page_size" required="true" type="java.lang.Integer" %>
<tr>
    <td class="cell2">&nbsp;&nbsp;${status + (page_number-1)*page_size}</td>
    <td class="cell1">&nbsp;&nbsp;${item.product.name}</td>
    <td class="cell2">&nbsp;&nbsp;${item.count}</td>
</tr>

