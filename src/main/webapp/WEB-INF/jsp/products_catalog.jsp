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
<ul class="pagination">
    <li>
        <input height="35" src="${img_path}/pre.ico" type="image" name="page_number" value="${page_number-1}" style=" text-align: center;text-decoration: underline"
               href="<c:url value="${balance_products}"></c:url>"> </input>
    </li>
    <li>
        <input height="35" src="${img_path}/refresh.png" type="image" name="page_number" value="${page_number}" style=" text-align: center;text-decoration: underline"
               href="<c:url value="${balance_products}"></c:url>"> </input>
    </li>
    <li>
        <input height="35" src="${img_path}/next.png" type="image" name="page_number" value="${page_number+1}" style=" text-align: center;text-decoration: underline"
               href="<c:url value="${balance_products}"></c:url>"> </input>
    </li><h2>&nbsp;&nbsp;&nbsp;&nbsp;Page number ${page_number}</h2>
</ul>
</form>
<t:footer/>
