<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:header/>
<div id="wb_Shape3">
    <img src="${img_path}/img0006.png" id="Shape3" alt="">
</div>
<div id="wb_Image4">
    <img src="${img_path}/body2.jpg" id="Image4" alt="">
</div>
<div id="wb_Heading15">
    <h1 id="Heading1">Receipt document</h1>
</div>
<table id="Table1">
    <tr>
        <td class="cell3">№</td>
        <td class="cell3">Product</td>
        <td class="cell3">Count</td>
        <td class="cell3">Action</td>
    </tr>
    <c:forEach items="${curent_document_list}" var="product_elem">
        <tr>
            <td class="cell2">&nbsp;&nbsp;${curent_document_list.lineNumber}</td>
            <td class="cell2">&nbsp;&nbsp;${curent_document_list.Product.id}</td>
            <td class="cell1">&nbsp;&nbsp;${curent_document_list.name}</td>
            <td class="cell2">&nbsp;&nbsp;${product_elem.measure.name}</td>
            <td class="cell2">&nbsp;&nbsp;${product_elem.barcode}</td>
            <td class="cell1">&nbsp;&nbsp;
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
    </c:forEach>
</table>
<ul class="pagination">
    <li>
        <a href="<c:url value="${products_catalog}"><c:param name="page_number" value="${page_number-1}"/></c:url>">❮</a>
    </li>
    <li><a href="#">${page_number}</a></li>
    <li>
        <a href="<c:url value="${products_catalog}"><c:param name="page_number" value="${page_number+1}"/></c:url>">❯</a>
    </li>
</ul>
<t:footer/>
