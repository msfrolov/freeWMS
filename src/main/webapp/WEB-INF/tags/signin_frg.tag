<%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="signin_frg" pageEncoding="UTF-8" %>
<script>
    function ValidateForm1(theForm) {

        var regexp;
        regexp = /^[A-Za-zёЁА-Яа-я0-9-]*$/;
        if (!regexp.test(theForm.Editbox1.value)) {
            alert("Please enter only letter and digit characters in the \"login\" field.");
            theForm.Editbox1.focus();
            return false;
        }
        if (theForm.Editbox1.value == "") {
            alert("Please enter a value for the \"login\" field.");
            theForm.Editbox1.focus();
            return false;
        }
        if (theForm.Editbox1.value.length < 5) {
            alert("Please enter at least 6 characters in the \"login\" field.");
            theForm.Editbox1.focus();
            return false;
        }
        if (theForm.Editbox1.value.length > 32) {
            alert("Please enter at most 32 characters in the \"login\" field.");
            theForm.Editbox1.focus();
            return false;
        }
        regexp = /^[A-Za-zёЁА-Яа-я0-9-]*$/;
        if (!regexp.test(theForm.Editbox2.value)) {
            alert("Please enter only letter and digit characters in the \"password\" field.");
            theForm.Editbox2.focus();
            return false;
        }
        if (theForm.Editbox2.value == "") {
            alert("Please enter a value for the \"password\" field.");
            theForm.Editbox2.focus();
            return false;
        }
        if (theForm.Editbox2.value.length < 5) {
            alert("Please enter at least 6 characters in the \"password\" field.");
            theForm.Editbox2.focus();
            return false;
        }
        if (theForm.Editbox2.value.length > 32) {
            alert("Please enter at most 32 characters in the \"password\" field.");
            theForm.Editbox2.focus();
            return false;
        }
        return true;
    }
</script>
</head>
<body>
<div id="wb_Shape1">
    <img src="${img_path}/img0001.png" id="Shape1" alt=""></div>
<div id="wb_Form2">
    <form name="Form1" method="post" action="<c:url value="${sign_in_action}"></c:url>" accept-charset="UTF-8"
          id="Form2" onsubmit="return ValidateForm1(this)">
        <input type="text" id="Editbox1"
               name="login" value="" maxlength="32" placeholder="Login">
        <input type="password" id="Editbox2"
               name="password" value="" maxlength="32" placeholder="Password">
        <div id="signInError">
            <c:if test="${not empty signInError}">
                <span>
                        ${signInError}
                </span>
            </c:if>
        </div>
        <input type="submit" id="Button1" name="sign_in" value="Sign in">
        <input type="submit" id="Button2" name="sign_up" value="Sign up">
    </form>
</div>
<div id="wb_Image1">
    <img src="${img_path}/logo1.png" id="Image1" alt=""></div>
<div id="wb_Image5">
    <img src="${img_path}/body2.png" id="Image5" alt=""></div>