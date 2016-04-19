<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:header/>
<div id="wb_Shape3" style="position:absolute;left:0px;top:258px;width:1000px;height:500px;z-index:7;">
    <img src="${img_path}/img0006.png" id="Shape3" alt="" style="width:1000px;height:500px;"></div>
<div id="wb_Image4" style="position:absolute;left:6px;top:264px;width:989px;height:489px;z-index:14;">
    <img src="${img_path}/body2.jpg" id="Image4" alt=""></div>
<div id="wb_Heading1"
     style="position:absolute;left:228px;top:396px;width:545px;height:110px;text-align:center;z-index:19;">
    <h1 id="Heading1">Cabinet</h1></div>
<script>
    function ValidateForm3(theForm) {
        var regexp;
        regexp = /^[A-Za-zёЁА-Яа-я \t\r\n\f0-9-]*$/;
        if (!regexp.test(theForm.Editbox1.value)) {
            alert("Please enter only letter, digit and whitespace characters in the \"EditName\" field.");
            theForm.Editbox1.focus();
            return false;
        }
        if (theForm.Editbox1.value == "") {
            alert("Please enter a value for the \"EditName\" field.");
            theForm.Editbox1.focus();
            return false;
        }
        if (theForm.Editbox1.value.length < 1) {
            alert("Please enter at least 1 characters in the \"EditName\" field.");
            theForm.Editbox1.focus();
            return false;
        }
        if (theForm.Editbox1.value.length > 100) {
            alert("Please enter at most 100 characters in the \"EditName\" field.");
            theForm.Editbox1.focus();
            return false;
        }
        regexp = /^[A-Za-zёЁА-Яа-я \t\r\n\f0-9-]*$/;
        if (!regexp.test(theForm.Editbox4.value)) {
            alert("Please enter only letter, digit and whitespace characters in the \"EditDescription\" field.");
            theForm.Editbox4.focus();
            return false;
        }
        if (theForm.Combobox1.selectedIndex < 0) {
            alert("Please select one of the \"EditMeasure\" options.");
            theForm.Combobox1.focus();
            return false;
        }
        if (theForm.Combobox2.selectedIndex < 0) {
            alert("Please select one of the \"EditType\" options.");
            theForm.Combobox2.focus();
            return false;
        }
        regexp = /^[0-9-]*$/;
        if (!regexp.test(theForm.Editbox2.value)) {
            alert("Please enter only digit characters in the \"EditBarcode\" field.");
            theForm.Editbox2.focus();
            return false;
        }
        if (theForm.Editbox2.value != "" && !(theForm.Editbox2.value > 0)) {
            alert("Please enter a value greater than \"0\" in the \"EditBarcode\" field.");
            theForm.Editbox2.focus();
            return false;
        }
        return true;
    }
</script>
<div id="wb_Form101">
    <form name="Form1" method="post" action="<c:url value=""></c:url>" id="wb_Form101"
          onsubmit="return ValidateForm3(this)">
        <label for="" id="Label9422">ID:</label>
        <label for="" id="Label5422">Name:</label>
        <label for="" id="Label6422">Type:</label>
        <label for="" id="Label7422">Measure:</label>
        <label for="" id="Label8422">Description:</label>
        <%--<select name="EditMeasure" size="1" id="Combobox1422">--%>
            <%--<c:forEach items="" var="item">--%>
                <%--<c:if test="${item.id == product.measure.id}">--%>
                    <%--<option value="${item.id}" selected>${item.name}</option>--%>
                <%--</c:if>--%>
                <%--<c:if test="${item.id != product.measure.id}">--%>
                    <%--<option value="${item.id}">${item.name}</option>--%>
                <%--</c:if>--%>
            <%--</c:forEach>--%>
        <%--</select>--%>
        <%--<select name="EditType" size="1" id="Combobox2422">--%>
            <%--<c:forEach items="" var="item">--%>
                <%--<c:if test="${item.id == product.type.id}">--%>
                    <%--<option value="${item.id}" selected>${item.name}</option>--%>
                <%--</c:if>--%>
                <%--<c:if test="${item.id != product.type.id}">--%>
                    <%--<option value="${item.id}">${item.name}</option>--%>
                <%--</c:if>--%>
            <%--</c:forEach>--%>
        <%--</select>--%>
        <label for="" id="Label10422">Barcode:</label>
        <input type="text" id="Editbox5422" name="EditId" value="" readonly>
        <input type="text" id="Editbox4422" name="EditDescription" value="">
        <input type="text" id="Editbox1422" name="EditName" value="">
        <input type="text" id="Editbox2422" name="EditBarcode" value="">
        <input type="submit" id="Button3422" name="Save" value="Save">
        <input type="submit" id="Button1422" name="Close" value="Close">
        <%--<c:if test="${not empty violation.id}"><label for="" id="Label12422">${violation.id}</label></c:if>--%>
        <%--<c:if test="${not empty violation.name}"><label for="" id="Label12522">${violation.name}</label></c:if>--%>
        <%--<c:if test="${not empty violation.type}"><label for="" id="Label12622">${violation.type}</label></c:if>--%>
        <%--<c:if test="${not empty violation.measure}"><label for="" id="Label12722">${violation.measure}</label></c:if>--%>
        <%--<c:if test="${not empty violation.description}"><label for="" id="Label12822">${violation.description}</label></c:if>--%>
        <%--<c:if test="${not empty violation.barcode}"><label for="" id="Label12922">${violation.barcode}</label></c:if>--%>
        <%--<c:if test="${success}"><label for="" id="Label13022">successfully saved</label></c:if>--%>
    </form>
</div>
<t:footer/>
