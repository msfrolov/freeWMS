<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:header/>
<div id="wb_Shape3" style="position:absolute;left:0px;top:258px;width:1000px;height:500px;z-index:7;">
    <img src="${img_path}/img0006.png" id="Shape3" alt="" style="width:1000px;height:500px;">
</div>
<div id="wb_Image4" style="position:absolute;left:6px;top:264px;width:989px;height:489px;z-index:14;">
    <img src="${img_path}/body2.jpg" id="Image4" alt="">
</div>
<div id="wb_Heading1"
     style="position:absolute;left:0px;top:698px;width:205px;height:110px;text-align:center;z-index:19;">
    <h1 id="Heading1">Products</h1>
</div>
<table style="position:relative;left:21px;top:278px;width:955px;height:409px;z-index:17;" id="Table1">

    <tr>
        <td class="cell3">&nbsp;&nbsp;ID&nbsp;&nbsp;</td>
        <td class="cell3">&nbsp;&nbsp;Type&nbsp;&nbsp;</td>
        <td class="cell3">&nbsp;&nbsp;Name&nbsp;&nbsp;</td>
        <td class="cell3">&nbsp;&nbsp;Measure&nbsp;&nbsp;</td>
        <td class="cell3">&nbsp;&nbsp;Barcode&nbsp;&nbsp;</td>
        <td class="cell3">&nbsp;&nbsp;Description&nbsp;&nbsp;</td>
        <td class="cell3">&nbsp;&nbsp;Edit&nbsp;&nbsp;</td>
    </tr>

    <c:forEach items="${products_list}" var="product_elem">
        <tr>
            <td class="cell2">&nbsp;&nbsp;${product_elem.id}</td>
            <td class="cell2">&nbsp;&nbsp;${product_elem.type.name}</td>
            <td class="cell1">&nbsp;&nbsp;${product_elem.name}</td>
            <td class="cell2">&nbsp;&nbsp;${product_elem.measure.name}</td>
            <td class="cell2">&nbsp;&nbsp;${product_elem.barcode}</td>
            <td class="cell1">&nbsp;&nbsp;
                <c:if test="${empty product_elem.description}">
                    <c:forEach begin="1" end="10">
                    &nbsp;
                    </c:forEach>
                </c:if>
                    ${product_elem.description}
            </td>
            <td class="cell2" ><a href="<c:url value="${product_card}?prodId=${product_elem.id}"></c:url>"><img src="${img_path}/edit_line.png" height="20"></a></td>
        </tr>
    </c:forEach>
</table>


<t:footer/>
