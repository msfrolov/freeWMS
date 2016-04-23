<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://java.sun.com/jsp/jstl/core" %>
<t:header/>
<div id="wb_Shape3">
    <img src="${img_path}/img0006.png" id="Shape3" alt="">
</div>
<div id="wb_Image4">
    <img src="${img_path}/body3.jpg" id="Image4" alt="">
</div>
<div id="wb_Heading1533">
    <h1 id="Heading1">Receipt document</h1>
</div>
<form name="Form17" method="post" action="<c:url value="${receipt_document}"></c:url>" accept-charset="UTF-8">
    <t:table_document/>
    <t:pagination_document/>
    <t:sort_document/>
</form>
<t:footer/>
