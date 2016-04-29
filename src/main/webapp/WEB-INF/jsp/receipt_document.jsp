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
<script>
    function ValidateForm4(theForm){
        var regexp;
        regexp = /^[0-9]*$/;
        if (!regexp.test(theForm.product.value)) {
            alert("Please, select a product for adding.");
            theForm.product.focus();
            return false;
        }
    }
</script>
<form name="Form17" method="get" action="<c:url value="${receipt_document}"></c:url>" accept-charset="UTF-8"
onsubmit="return ValidateForm4">
    <p style=" position:absolute;top: 251px;left: 30px;z-index: 14">Document date:
        <input type="date" name="doc_date" value="${doc_date}"
               max="${today}" min="2016-01-01"></p>
    <t:table_document current_document_list="${current_document_list}"/>
    <div name="div55" id="div55" style="position: absolute;top: 689px;left: 525px;z-index:29;">

        <label for="" id="Label6455">Sender:</label>
        <label for="" id="Label74555">Recipient:</label>
        <select name="EditSender" size="1" id="Combobox1455">
            <c:forEach items="${sender_list}" var="item">
                <c:if test="${item eq sender}">
                    <option value="${item.id}" selected>${item.name}</option>
                </c:if>
                <c:if test="${item ne sender}">
                    <option value="${item.id}">${item.name}</option>
                </c:if>
            </c:forEach>
        </select>
        <select name="EditRecipient" size="1" id="Combobox2455">
            <c:forEach items="${recipient_list}" var="item">
                <c:if test="${item eq recipient}">
                    <option value="${item.id}" selected>${item.name}</option>
                </c:if>
                <c:if test="${item ne recipient}">
                    <option value="${item.id}">${item.name}</option>
                </c:if>
            </c:forEach>
        </select>
        <c:if test="${not empty doc_not_save}">
            <label for="" id="Label12477">failed to save the document</label>
        </c:if>
        <c:if test="${not empty doc_save}">
            <label for="" id="Label12478">document is successfully saved</label>
        </c:if>
        <input type="submit" id="Button166" name="save" value="Save">
        <input type="submit" id="Button266" name="clear" value="Clear">
        <%--suppress ALL --%>
        <style>
            #Label12477 {
                position: absolute;
                left: 239px;
                top: 56px;
                width: 221px;
                height: 21px;
                line-height: 21px;
                z-index: 19;
            }

            #Label12477 {
                border: 0px #CCCCCC solid;
                -moz-border-radius: 4px;
                -webkit-border-radius: 4px;
                border-radius: 4px;
                background-color: #FFDAB9;
                background-image: none;
                color: #000000;
                font-family: "Times New Roman";
                font-weight: normal;
                font-size: 14px;
                padding: 4px 4px 4px 4px;
                text-align: left;
                vertical-align: middle;
            }

            #Label12478 {
                position: absolute;
                left: 239px;
                top: 56px;
                width: 221px;
                height: 21px;
                line-height: 21px;
                z-index: 19;
            }

            #Label12478 {
                border: 0px #CCCCCC solid;
                -moz-border-radius: 4px;
                -webkit-border-radius: 4px;
                border-radius: 4px;
                background-color: #10a307;
                background-image: none;
                color: #000000;
                font-family: "Times New Roman";
                font-weight: normal;
                font-size: 14px;
                padding: 4px 4px 4px 4px;
                text-align: left;
                vertical-align: middle;
            }

            #Label74555 {
                position: absolute;
                left: 4px;
                top: 30px;
                width: 60px;
                height: 20px;
                line-height: 20px;
                z-index: 10;
            }

            #Combobox1455 {
                position: absolute;
                left: 87px;
                top: 0px;
                width: 260px;
                height: 23px;
                z-index: 29;
            }

            #Combobox2455 {
                position: absolute;
                left: 88px;
                top: 27px;
                width: 260px;
                height: 23px;
                z-index: 29;
            }

            #Combobox1455 {
                border: 1px #CCCCCC solid;
                -moz-border-radius: 4px;
                -webkit-border-radius: 4px;
                border-radius: 4px;
                background-color: #FFFFFF;
                background-image: none;
                color: #000000;
                font-family: Arial;
                font-weight: normal;
                font-size: 13px;
                padding: 4px 4px 4px 4px;
                -webkit-box-sizing: border-box;
                -moz-box-sizing: border-box;
                box-sizing: border-box;
            }

            #Combobox1455:focus {
                border-color: #66AFE9;
                -webkit-box-shadow: inset 0px 1px 1px rgba(0, 0, 0, 0.075), 0px 0px 8px rgba(102, 175, 233, 0.60);
                -moz-box-shadow: inset 0px 1px 1px rgba(0, 0, 0, 0.075), 0px 0px 8px rgba(102, 175, 233, 0.60);
                box-shadow: inset 0px 1px 1px rgba(0, 0, 0, 0.075), 0px 0px 8px rgba(102, 175, 233, 0.60);
                outline: 0;
            }

            #Combobox2455 {
                border: 1px #CCCCCC solid;
                -moz-border-radius: 4px;
                -webkit-border-radius: 4px;
                border-radius: 4px;
                background-color: #FFFFFF;
                background-image: none;
                color: #000000;
                font-family: Arial;
                font-weight: normal;
                font-size: 13px;
                padding: 4px 4px 4px 4px;
                -webkit-box-sizing: border-box;
                -moz-box-sizing: border-box;
                box-sizing: border-box;
            }

            #Combobox2455:focus {
                border-color: #66AFE9;
                -webkit-box-shadow: inset 0px 1px 1px rgba(0, 0, 0, 0.075), 0px 0px 8px rgba(102, 175, 233, 0.60);
                -moz-box-shadow: inset 0px 1px 1px rgba(0, 0, 0, 0.075), 0px 0px 8px rgba(102, 175, 233, 0.60);
                box-shadow: inset 0px 1px 1px rgba(0, 0, 0, 0.075), 0px 0px 8px rgba(102, 175, 233, 0.60);
                outline: 0;
            }

            /*noinspection ALL*/
            #Button166 {
                position: absolute;
                left: 376px;
                top: 0;
                width: 90px;
                height: 23;
                z-index: 5;
            }

            /*noinspection ALL*/
            #Button266 {
                position: absolute;
                left: 376px;
                top: 28px;
                width: 90px;
                height: 23px;
                z-index: 5;
            }

            /*noinspection ALL*/
            #Button166 {
                border: 1px #2E6DA4 solid;
                -moz-border-radius: 4px;
                -webkit-border-radius: 4px;
                border-radius: 4px;
                background: #3370B7 none;
                color: #FFFFFF;
                font-family: Arial, serif;
                font-weight: normal;
                font-size: 13px;
            }

            /*noinspection ALL*/
            #Button266 {
                border: 1px #2E6DA4 solid;
                -moz-border-radius: 4px;
                -webkit-border-radius: 4px;
                border-radius: 4px;
                background: #3370B7 none;
                color: #FFFFFF;
                font-family: Arial, serif;
                font-weight: normal;
                font-size: 13px;
            }
        </style>
    </div>
    <div name="div55" id="div55" style="position: absolute;top: 689px;left: 525px;z-index:29;">
        <label for="" id="Label74558"
               style="position: absolute;left: 196px;top: -425px;width: 85;pxheight: 20px;line-height: 20px;z-index: 10;">Sort
            order:</label>
        <t:sort/>
    </div>
    <t:pagination url="${receipt_document}" page_number="${requestScope.page_number}"/>
</form>
<t:footer/>
