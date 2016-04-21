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
<div id="wb_Heading15">
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
    <form name="Form1" method="post" action="<c:url value="${cabinet_action}">
                                                <c:param name="EditId" value="${user_cabinet.id}"/>
                                                <c:param name="EditName" value="${user_cabinet.name}"/>
                                                <c:param name="CurrentRole" value="${user_cabinet.role.id}"/>
                                             </c:url>" id="Form14"
          onsubmit="return ValidateForm3(this)">
        <label for="" id="Label9422">Role:</label>
        <label for="" id="Label5422">Login:</label>
        <label for="" id="Label6422">First name:</label>
        <label for="" id="Label7422">Last name:</label>
        <label for="" id="Label8422">Gender:</label>
        <%--1--%>
        <select name="EditRole" size="1" id="Editbox5422">
            <c:forEach items="${role_list}" var="item">
                <c:if test="${item.id == user_cabinet.role.id}">
                    <option value="${item.id}" selected>${item.name}</option>
                </c:if>
                <c:if test="${item.id != user_cabinet.role.id}">
                    <option value="${item.id}">${item.name}</option>
                </c:if>
            </c:forEach>
        </select>
        <%--2--%>
        <input type="text" id="Editbox1422" name="EditName2" value="${user_cabinet.name}" readonly>
        <%--3--%>
        <input type="text" id="Combobox2422" name="EditIndName" value="${individ_cabinet.name}">
        <%--4--%>
        <input type="text" id="Combobox14" name="EditIndLastName" value="${individ_cabinet.lastName}">
        <%--5--%>
        <select name="EditGender" size="1" id="Editbox4422">
            <c:forEach items="${gender_list}" var="item">
                <c:if test="${item.id == individ_cabinet.gender.id}">
                    <option value="${item.id}" selected>${item.name}</option>
                </c:if>
                <c:if test="${item.id != individ_cabinet.gender.id}">
                    <option value="${item.id}">${item.name}</option>
                </c:if>
            </c:forEach>
        </select>
        <c:if test="${not empty violation.role}"><label id="Label12422">${violation.role}</label></c:if>
        <c:if test="${not empty violation.name}"><label id="Label12522">${violation.name}</label></c:if>
        <c:if test="${not empty violation.individName}"><label id="Label12622">${violation.individName}</label></c:if>
        <c:if test="${not empty violation.individLName}"><label id="Label12722">${violation.individLName}</label></c:if>
        <c:if test="${not empty violation.gender}"><label id="Label12822">${violation.gender}</label></c:if>
        <c:if test="${not empty violation.transaction}"><label for="" id="Label129">${violation.transaction}</label></c:if>
        <c:if test="${success}"><label id="Label13022">successfully saved</label></c:if>

        <input type="submit" id="Button3422" name="Save" value="Save">
        <input type="submit" id="Button1422" name="Close" value="Close">
    </form>
</div>
<t:footer/>
