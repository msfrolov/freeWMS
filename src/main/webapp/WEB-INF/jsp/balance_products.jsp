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
<div id="wb_Heading1588"
     style=" position: absolute;left: 0px;top: 696px;width: 300px;height: 110px;text-align: center;z-index: 29;">
    <h1 id="Heading1">Balance products</h1>
</div>
<t:table_order_product products_list="${balance_list}"/>
<form>
    <div name="div55" id="div55" style="position: absolute;top: 689px;left: 525px;z-index:29;">
        <p style="position: absolute;
    top: -439px;
    left: -500;
    z-index: 14;">Report date:
            <input type="date" name="doc_date" value="${doc_date}" max="${today}" min="2016-01-01" required></p>
        <label for="" id="Label64558"
               style="position: absolute;left: -230px;top: -425px;width: 171px;height: 20px;line-height: 20px;z-index: 10;">Selection
            of warehouse:</label>
        <label for="" id="Label74558"
               style="position: absolute;left: 196px;top: -425px;width: 85;pxheight: 20px;line-height: 20px;z-index: 10;">Sort
            order:</label>
        <script type="text/javascript">
            function getValue(idElem, atrName) {
                return document.getElementById(idElem).getAttribute(atrName);
            }
        </script>
        <select name="sort_select" size="1" id="Combobox1455"
                style="position: absolute;left: 300px;top: -425px;width: 151px;height: 23px;line-height: 20px;z-index: 10;"
                required>
            <c:forEach items="${sort_list}" var="item">
                <c:if test="${item eq sort_select}">
                    <option value="${item}" selected>${item}</option>
                </c:if>
                <c:if test="${item ne sort_select}">
                    <option value="${item}">${item}</option>
                </c:if>
            </c:forEach>
        </select>
        <select name="warehouse_select" size="1" id="Combobox2455"
                style="    position: absolute;left: -73px;top: -425px;width: 260px;height: 23px;z-index: 29;" required>
            <c:forEach items="${warehouse_list}" var="item">
                <c:if test="${item eq warehouse_select}">
                    <option value="${item.id}" selected>${item.name}</option>
                </c:if>
                <c:if test="${item ne warehouse_select}">
                    <option value="${item.id}">${item.name}</option>
                </c:if>
            </c:forEach>
        </select>
    </div>
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


