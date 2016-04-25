<%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="sort_document" pageEncoding="UTF-8" %>

<div class="sort_doc" style="position: absolute;top: 265px;left: 542px;z-index: 25;" onmousedown="false"
     onselect="false">
    <b>Sort by</b>
    <input name="sort" type="submit" value="count(asce)">
    <input name="sort" type="submit" value="product(asce)">
    <input name="sort" type="submit" value="count(desc)">
    <input name="sort" type="submit" value="product(desc)">
</div>