<%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="pagination" pageEncoding="UTF-8" %>
<%@attribute name="url" required="true" type="java.lang.String" %>
<%@attribute name="page_number" required="true" type="java.lang.Integer" %>
<ul class="pagination">
    <li>
        <input height="35" src="${img_path}/pre.ico" type="image" name="page_number" value="${page_number-1}"
               style=" text-align: center;text-decoration: underline"
               href="<c:url value="${url}"></c:url>"> </input>
    </li>
    <li>
        <input height="35" src="${img_path}/refresh.png" type="image" name="page_number" value="${page_number}"
               style=" text-align: center;text-decoration: underline"
               href="<c:url value="${url}"></c:url>"> </input>
    </li>
    <li>
        <input height="35" src="${img_path}/next.png" type="image" name="page_number" value="${page_number+1}"
               style=" text-align: center;text-decoration: underline"
               href="<c:url value="${url}"></c:url>"> </input>
    </li>
    <br><h4>Page number ${page_number}</h4>
</ul>

