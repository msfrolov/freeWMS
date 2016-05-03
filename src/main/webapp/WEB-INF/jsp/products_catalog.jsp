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
    <img src="${img_path}/body3.jpg" id="Image4" alt="">
</div>
<div id="wb_Heading15">
    <h1 id="Heading1">Products</h1>
</div>
<t:table_products products_list="${products_list}" isAdmin="${isAdmin}"/>
<form>
    <div style="top: 685;position: absolute;left: 638;z-index: 17;font-size:20;font-weight: bold;">
        <a href="<c:url value="${product_card}">
                                 <c:param name="page_number" value="${page_number}"/>
                                 <c:param name="add_prod" value="add_prod"/>
                             </c:url>"><img src="${img_path}/add.png" height="24">Add product</a>
    </div>
    <t:pagination url="${balance_products}" page_number="${requestScope.page_number}"/>
</form>
<t:footer/>
