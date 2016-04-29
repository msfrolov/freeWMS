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
<div id="wb_Heading1">
    <h1 id="Heading1">Product card</h1>
</div>
<script>
    function ValidateForm2(theForm) {
        var regexp;
        regexp = /^[A-Za-zёЁА-Яа-я \t\r\n\f()0-9-]*$/;
        if (!regexp.test(theForm.Editbox14.value)) {
            alert("Please enter only letter, digit and whitespace characters in the \"EditName\" field.");
            theForm.Editbox14.focus();
            return false;
        }
        if (theForm.Editbox14.value == "") {
            alert("Please enter a value for the \"EditName\" field.");
            theForm.Editbox14.focus();
            return false;
        }
        if (theForm.Editbox14.value.length < 1) {
            alert("Please enter at least 1 characters in the \"EditName\" field.");
            theForm.Editbox14.focus();
            return false;
        }
        if (theForm.Editbox14.value.length > 100) {
            alert("Please enter at most 100 characters in the \"EditName\" field.");
            theForm.Editbox14.focus();
            return false;
        }

        regexp = /^[0-9-]*$/;
        if (!regexp.test(theForm.Editbox24.value)) {
            alert("Please enter only digit characters in the \"EditBarcode\" field.");
            theForm.Editbox24.focus();
            return false;
        }
        if (theForm.Editbox24.value != "" && !(theForm.Editbox24.value > 0)) {
            alert("Please enter a value greater than \"0\" in the \"EditBarcode\" field.");
            theForm.Editbox24.focus();
            return false;
        }
        return true;
    }
</script>
<div id="wb_Form14">
    <form name="Form1" method="post" action="<c:url value="${product_card}">
                                                <c:param name="EditId" value="${product.id}"/>
                                            </c:url>" id="Form14"
          onsubmit="return ValidateForm2(this)">
        <label for="" id="Label94">ID:</label>
        <label for="" id="Label54">Name:</label>
        <label for="" id="Label64">Type:</label>
        <label for="" id="Label74">Measure:</label>
        <label for="" id="Label84">Description:</label>
        <select name="EditMeasure" size="1" id="Combobox14">
            <c:forEach items="${measureList}" var="item">
                <c:if test="${item.id == product.measure.id}">
                    <option value="${item.id}" selected>${item.name}</option>
                </c:if>
                <c:if test="${item.id != product.measure.id}">
                    <option value="${item.id}">${item.name}</option>
                </c:if>
            </c:forEach>
        </select>
        <select name="EditType" size="1" id="Combobox24">
            <c:forEach items="${typeList}" var="item">
                <c:if test="${item.id == product.type.id}">
                    <option value="${item.id}" selected>${item.name}</option>
                </c:if>
                <c:if test="${item.id != product.type.id}">
                    <option value="${item.id}">${item.name}</option>
                </c:if>
            </c:forEach>
        </select>
        <label for="" id="Label104">Barcode:</label>
        <input type="text" id="Editbox54" name="EditId2" value="${product.id}" readonly>
        <input type="text" id="Editbox44" name="EditDescription" value="${product.description}">
        <input type="text" id="Editbox14" name="EditName" value="${product.name}">
        <input type="text" id="Editbox24" name="EditBarcode" value="${product.barcode}">
        <input type="submit" id="Button34" name="Save" value="Save">
        <input type="submit" id="Button14" name="Close" value="Close">
        <c:if test="${not empty violation.id}"><label for="" id="Label124">${violation.id}</label></c:if>
        <c:if test="${not empty violation.name}"><label for="" id="Label125">${violation.name}</label></c:if>
        <c:if test="${not empty violation.type}"><label for="" id="Label126">${violation.type}</label></c:if>
        <c:if test="${not empty violation.measure}"><label for="" id="Label127">${violation.measure}</label></c:if>
        <c:if test="${not empty violation.description}"><label for=""
                                                               id="Label128">${violation.description}</label></c:if>
        <c:if test="${not empty violation.barcode}"><label for="" id="Label129">${violation.barcode}</label></c:if>
        <c:if test="${success}"><label for="" id="Label130">successfully saved</label></c:if>
    </form>
</div>
<t:footer/>
