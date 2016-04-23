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
    <h1 id="Heading1">Products</h1>
</div>
<t:table_products/>
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
