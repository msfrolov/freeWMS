<%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="signin_frg" pageEncoding="UTF-8" %>
<script>
    function ValidateForm1(theForm) {

        var regexp;
        regexp = /^[A-Za-zА-Яа-я0-9-]*$/;
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
        regexp = /^[A-Za-zА-Яа-я0-9-]*$/;
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
<div id="wb_Shape1" style="position:absolute;left:0px;top:10px;width:1000px;height:175px;z-index:7;">
    <img src="${img_path}/img0001.png" id="Shape1" alt="" style="width:1000px;height:175px;"></div>
<div id="wb_Form2" style="position:absolute;left:766px;top:21px;width:223px;height:153px;z-index:17;">


    <form name="Form1" method="post" action="${sign_in_action}" accept-charset="UTF-8"
          id="Form2" onsubmit="return ValidateForm1(this)">
        <input type="text" id="Editbox1"
               style="position:absolute;left:14px;top:9px;width:188px;height:18px;line-height:18px;z-index:0;"
               name="login" value="" maxlength="32" placeholder="Login">
        <input type="password" id="Editbox2"
               style="position:absolute;left:14px;top:49px;width:188px;height:18px;line-height:18px;z-index:1;"
               name="password" value="" maxlength="32" placeholder="Password">
        <div id="signInError" style="margin-top: 70px; margin-left: 14px;color: #a30013">
            <c:if test="${not empty signInError}">
                <span>
                        ${signInError}
                </span>
            </c:if>
        </div>
        <input type="submit" id="Button1" name="sign_in" value="Sign in"
               style="position:absolute;left:19px;top:109px;width:90px;height:30px;z-index:4;">
        <input type="submit" id="Button2" name="sign_up" value="Sign up"
               style="position:absolute;left:119px;top:109px;width:90px;height:30px;z-index:5;">
    </form>
</div>
<div id="wb_Image1" style="position:absolute;left:45px;top:40px;width:466px;height:113px;z-index:9;">
    <img src="${img_path}/logo1.png" id="Image1" alt=""></div>
<div id="wb_Image5" style="position:absolute;left:564px;top:20px;width:160px;height:155px;z-index:16;">

    <img src="${img_path}/body2.png" id="Image5" alt=""></div>