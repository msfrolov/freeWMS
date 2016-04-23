<%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="sort_document" pageEncoding="UTF-8" %>
 <div class="sort_doc" style="position: absolute;top: 265px;left: 88px;z-index: 25;">
        <b>Sort by</b>
        <c:choose>
            <c:when test="${sort == 'count_asce'}">
                <input name="sort" type="radio" value="count_asce" checked>count (asce)
                <input name="sort" type="radio" value="product_asce">product (asce)
                <input name="sort" type="radio" value="count_desc">count (desc)
                <input name="sort" type="radio" value="product_desc">product (desc)
            </c:when>
            <c:when test="${sort == 'product_asce'}">
                <input name="sort" type="radio" value="count_asce">count (asce)
                <input name="sort" type="radio" value="product_asce" checked>product (asce)
                <input name="sort" type="radio" value="count_desc">count (desc)
                <input name="sort" type="radio" value="product_desc">product (desc)
            </c:when>
            <c:when test="${sort == 'count_desc'}">
                <input name="sort" type="radio" value="count_asce">count (asce)
                <input name="sort" type="radio" value="product_asce">product (asce)
                <input name="sort" type="radio" value="count_desc" checked>count (desc)
                <input name="sort" type="radio" value="product_desc">product (desc)
            </c:when>
            <c:when test="${sort == 'product_desc'}">
                <input name="sort" type="radio" value="count_asce">count (asce)
                <input name="sort" type="radio" value="product_asce">product (asce)
                <input name="sort" type="radio" value="count_desc">count (desc)
                <input name="sort" type="radio" value="product_desc" checked>product (desc)
            </c:when>
            <c:otherwise>
                <input name="sort" type="radio" value="count_asce">count (asce)
                <input name="sort" type="radio" value="product_asce">product (asce)
                <input name="sort" type="radio" value="count_desc">count (desc)
                <input name="sort" type="radio" value="product_desc">product (desc)
            </c:otherwise>
        </c:choose>
    </div>