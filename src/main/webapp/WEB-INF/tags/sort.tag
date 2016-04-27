<%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="sort" pageEncoding="UTF-8" %>
<select name="sort_select" size="1" id="Combobox1455"
        style="position: absolute;left: 300px;top: -425px;width: 151px;height: 23px;line-height: 20px;z-index: 10;"
        required>
    <c:forEach items="${requestScope.sort_list}" var="item">
        <c:if test="${item eq sort_select}">
            <option value="${item}" selected>${item}</option>
        </c:if>
        <c:if test="${item ne requestScope.sort_select}">
            <option value="${item}">${item}</option>
        </c:if>
    </c:forEach>
</select>