<%--suppress ALL --%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %><%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="table_document" pageEncoding="UTF-8" %>
<%@attribute name="current_document_list" required="true" type="java.util.List" %>
<%--<%@attribute name="product_list" required="true" type="java.util.List" %>--%>
<%--<%@attribute name="start" required="true" type="java.lang.Integer" %>--%>
<table id="Table1">
    <%--header--%>
    <tr>
        <td class="cell4" width="45">№</td>
        <td class="cell4">Product</td>
        <td class="cell5" width="25">Count</td>
        <td class="cell4" width="25">Action</td>
    </tr>

    <%--body--%>
    <c:forEach items="${current_document_list}" var="product_elem" varStatus="status">
        <tr>
            <td class="cell2">&nbsp;&nbsp;${requestScope.start + status.count}</td>
            <td class="cell1">&nbsp;&nbsp;${product_elem.product.name}</td>
            <td class="cell2">&nbsp;&nbsp;${product_elem.count}</td>
            <td class="cell2">
                    <%-- COMMENT: requestScope.start + status.count-1 - element index List<TableLine> in Document--%>
                    <%--noinspection ALL--%>
                <input name="delete" type="image" value="${requestScope.start + status.count-1}"
                       src="${img_path}/delete.png"
                       height="20"/>
            </td>
        </tr>
    </c:forEach>
    <%--footer--%>
    <tr>
        <td class="cell2">&nbsp;-&nbsp;</td>
        <%--prod--%>
        <td class="cell1">
            <%--<select name="product" size="1" id="Combobox24">--%>
            <select name="product" size="1" style="width: 820px;height: 30px">
                <c:forEach items="${requestScope.product_list}" var="item">
                    <c:choose>
                        <c:when test="${curProd.id == item.id}">
                            <option value="${item.id}" selected>${item.name}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${item.id}">${item.name}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </td>
        <%--count--%>
        <td class="cell2">
            <input name="count" type="number" min="1" value="1" style="width: 50px;height: 30px">
        </td>
        <td class="cell2">
            <input type="image" name="add" value="add" src="${img_path}/add.png" height="20"/>
        </td>
    </tr>
</table>
<style>
    /*noinspection ALL*/
    #Table1 .cell1 {
        background-color: transparent;
        background-image: none;
        border: 1px #C0C0C0 solid;
        text-align: left;
        vertical-align: middle;
        height: 32px;
        color: #000000;
        font-family: Arial;
        font-size: 15px;
        line-height: 16px;
    }

    /*noinspection ALL*/
    #Table1 .cell2 {
        background-color: transparent;
        background-image: none;
        border: 1px #C0C0C0 solid;
        text-align: center;
        vertical-align: middle;
        height: 32px;
        color: #000000;
        font-family: Arial;
        font-size: 15px;
        line-height: 16px;
    }

    /*noinspection ALL*/
    #Table1 .cell3 {
        background-color: lightgray;
        background-image: none;
        border: 1px #C0C0C0 solid;
        text-align: center;
        vertical-align: middle;
        height: 32px;
        color: #000000;
        font-family: Arial;
        font-weight: bold;
        font-size: 15px;
        line-height: 16px;
    }

    /*noinspection ALL*/
    #Table1 .cell4 {
        background-color: lightgray;
        background-image: none;
        border: 1px #C0C0C0 solid;
        text-align: center;
        vertical-align: middle;
        height: 32px;
        color: #000000;
        font-family: Arial;
        font-weight: bold;
        font-size: 15px;
        line-height: 16px;
        width: 25px;
    }

    /*noinspection ALL*/
    #Table1 .cell5 {
        background-color: lightgray;
        background-image: none;
        border: 1px #C0C0C0 solid;
        text-align: center;
        vertical-align: middle;
        height: 32px;
        color: #000000;
        font-family: Arial;
        font-weight: bold;
        font-size: 15px;
        line-height: 16px;
        width: 50px;
    }

    #Table1 {
        position: relative;
        left: 21px;
        top: 287px;
        width: 955px;
        z-index: 17;
    }
</style>