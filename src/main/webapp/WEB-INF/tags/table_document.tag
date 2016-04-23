<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %><%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="table_document" pageEncoding="UTF-8" %>
<table id="Table1">
        <%--header--%>
        <tr>
            <td class="cell3" width="45">№</td>
            <td class="cell3">Product</td>
            <td class="cell3" width="117">Count</td>
            <td class="cell3" width="25">Action</td>
        </tr>
        <%--body--%>
        <c:forEach items="${current_document_list}" var="product_elem" varStatus="status">
         <tags:line_document/>
        </c:forEach>
        <%--footer--%>
        <tr>
            <td class="cell2">&nbsp;-&nbsp;</td>
            <%--prod--%>
            <td class="cell1">
                <%--<select name="product" size="1" id="Combobox24">--%>
                <select name="product" size="1"">
                <c:forEach items="${product_list}" var="item">
                    <option value="${item.id}" selected>${item.name}</option>
                </c:forEach>
                </select>
            </td>
            <%--count--%>
            <td class="cell2">
                <input name="count" type="number" min="1" value="1">
            </td>
            <td class="cell2">
                <input type="image" name="add" value="add" src="${img_path}/add.png" height="20"/>
            </td>
        </tr>
    </table>