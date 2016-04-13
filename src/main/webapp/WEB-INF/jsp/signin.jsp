<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>signin</title>
    <meta name="generator" content="WYSIWYG Web Builder 11 Trial Version - http://www.wysiwygwebbuilder.com">
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/Untitled5.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/signin.css" rel="stylesheet">
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
            if (theForm.Editbox1.value.length < 6) {
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
            if (theForm.Editbox2.value.length < 6) {
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
<a href="http://www.wysiwygwebbuilder.com" target="_blank"><img src="${pageContext.request.contextPath}/img/builtwithwwb11.png"
                                                                alt="WYSIWYG Web Builder"
                                                                style="position:absolute;left:912px;top:965px;border-width:0;z-index:250"></a>
<div id="wb_Shape1" style="position:absolute;left:0px;top:10px;width:1000px;height:175px;z-index:7;">
    <img src="${pageContext.request.contextPath}/img/img0001.png" id="Shape1" alt="" style="width:1000px;height:175px;"></div>
<div id="wb_Shape3" style="position:absolute;left:0px;top:258px;width:1000px;height:595px;z-index:8;">
    <img src="${pageContext.request.contextPath}/img/img0003.png" id="Shape3" alt="" style="width:1000px;height:595px;"></div>
<div id="wb_Image1" style="position:absolute;left:45px;top:40px;width:466px;height:113px;z-index:9;">
    <img src="${pageContext.request.contextPath}/img/logo1.png" id="Image1" alt=""></div>
<div id="wb_Form1" style="position:absolute;left:765px;top:20px;width:223px;height:153px;z-index:10;">
    <form name="Form1" method="post"  accept-charset="UTF-8"
          id="Form1" onsubmit="return ValidateForm1(this)" action="${pageContext.request.contextPath}/wms/login">
        <input type="text" id="Editbox1"
               style="position:absolute;left:14px;top:9px;width:188px;height:18px;line-height:18px;z-index:0;"
               name="login" value="" maxlength="32" placeholder="Login">
        <input type="password"
               style="position:absolute;left:14px;top:49px;width:188px;height:18px;line-height:18px;z-index:1;"
               name="password"  maxlength="32" placeholder="Password">
        <input type="checkbox" id="Checkbox1" name="remember_me" value="true"
               style="position:absolute;left:24px;top:84px;z-index:2;" title="remember_me">
        <label for="Checkbox1" id="Label1"
               style="position:absolute;left:44px;top:79px;width:157px;height:18px;line-height:18px;z-index:3;">remember
            me</label>
        <button type="submit" id="Button1" name="sign_in" value="Sign in"
               style="position:absolute;left:19px;top:109px;width:90px;height:30px;z-index:4;"></button>
        <button type="submit" id="Button2" name="sign_up" value="Sign up"
               style="position:absolute;left:119px;top:109px;width:90px;height:30px;z-index:5;"></button>>

        <%--Login: <input type="text" name="login"/>--%>
        <%--Password: <input type="password" name="password"/>--%>
        <%--<button type="submit">Login</button>--%>

    </form>
</div>
<div id="wb_ResponsiveMenu1" style="position:absolute;left:0px;top:187px;width:1000px;height:70px;z-index:11;">
    <label class="toggle" for="ResponsiveMenu1-submenu" id="ResponsiveMenu1-title">Menu
        <div id="ResponsiveMenu1-icon"><span>&nbsp;</span><span>&nbsp;</span><span>&nbsp;</span></div>
    </label>
    <input type="checkbox" id="ResponsiveMenu1-submenu">
    <ul class="ResponsiveMenu1" id="ResponsiveMenu1">
        <li>
            <label for="ResponsiveMenu1-submenu-0" class="toggle"><i class="fa fa-suitcase fa-2x">&nbsp;</i>Catalogs<b
                    class="arrow-down"></b></label>
            <a href="#" title="Catalog"><i class="fa fa-suitcase fa-2x">&nbsp;</i><br>Catalogs<b class="arrow-down"></b></a>
            <input type="checkbox" id="ResponsiveMenu1-submenu-0">
            <ul>
                <li><a href="#"><i class="fa fa-cubes fa-2x">&nbsp;</i>Products</a></li>
                <li><a href="#"><i class="fa fa-stack-overflow fa-2x">&nbsp;</i>Warehouses</a></li>
                <li><a href="#" title="Counterparts"><i class="fa fa-user-secret fa-2x">&nbsp;</i>Counterparts</a></li>
            </ul>
        </li>
        <li>
            <label for="ResponsiveMenu1-submenu-1" class="toggle"><i class="fa fa-archive fa-2x">&nbsp;</i>Additionals<b
                    class="arrow-down"></b></label>
            <a href="#"><i class="fa fa-archive fa-2x">&nbsp;</i><br>Additionals<b class="arrow-down"></b></a>
            <input type="checkbox" id="ResponsiveMenu1-submenu-1">
            <ul>
                <li><a href="#" title="Product types"><i class="fa fa-list-ul fa-2x">&nbsp;</i>Product&nbsp;types</a>
                </li>
                <li><a href="#"><i class="fa fa-codepen fa-2x">&nbsp;</i>Measures</a></li>
            </ul>
        </li>
        <li>
            <label for="ResponsiveMenu1-submenu-2" class="toggle"><i class="fa fa-book fa-2x">&nbsp;</i>Documents<b
                    class="arrow-down"></b></label>
            <a href="#"><i class="fa fa-book fa-2x">&nbsp;</i><br>Documents<b class="arrow-down"></b></a>
            <input type="checkbox" id="ResponsiveMenu1-submenu-2">
            <ul>
                <li><a href="#" title="Receipt documents"><i class="fa fa-calendar-plus-o fa-2x">&nbsp;</i>Receipt&nbsp;documents</a>
                </li>
                <li><a href="#" title="Move documents"><i class="fa fa-calendar-times-o fa-2x">&nbsp;</i>Move&nbsp;documents</a>
                </li>
                <li><a href="#" title="Expense documens"><i class="fa fa-calendar-minus-o fa-2x">&nbsp;</i>Expense&nbsp;documens</a>
                </li>
            </ul>
        </li>
        <li>
            <label for="ResponsiveMenu1-submenu-3" class="toggle"><i class="fa fa-area-chart fa-2x">&nbsp;</i>Reports<b
                    class="arrow-down"></b></label>
            <a href="#" title="Reports"><i class="fa fa-area-chart fa-2x">&nbsp;</i><br>Reports<b
                    class="arrow-down"></b></a>
            <input type="checkbox" id="ResponsiveMenu1-submenu-3">
            <ul>
                <li><a href="#" title="Balance products"><i class="fa fa-tasks fa-2x">
                    &nbsp;</i>Balance&nbsp;products</a></li>
                <li><a href="#" title="Turnover with counterparties"><i class="fa fa-retweet fa-2x">&nbsp;</i>Turnover&nbsp;with&nbsp;counterparties</a>
                </li>
            </ul>
        </li>
        <li>
            <label for="ResponsiveMenu1-submenu-4" class="toggle"><i class="fa fa-gears fa-2x">
                &nbsp;</i>Administration<b class="arrow-down"></b></label>
            <a href="#" title="Administration"><i class="fa fa-gears fa-2x">&nbsp;</i><br>Administration<b
                    class="arrow-down"></b></a>
            <input type="checkbox" id="ResponsiveMenu1-submenu-4">
            <ul>
                <li><a href="#" title="Users"><i class="fa fa-users fa-2x">&nbsp;</i>Users</a></li>
                <li><a href="#" title="Roles"><i class="fa fa-tachometer fa-2x">&nbsp;</i>Roles</a></li>
            </ul>
        </li>
        <li><a href="#" title="Cabinet"><i class="fa fa-user fa-2x">&nbsp;</i><br>Cabinet</a></li>
    </ul>
</div>
<div id="wb_Shape2" style="position:absolute;left:0px;top:855px;width:1000px;height:141px;z-index:12;">
    <img src="${pageContext.request.contextPath}/img/img0002.png" id="Shape2" alt="" style="width:1000px;height:141px;"></div>
<div id="wb_YouTube1" style="position:absolute;left:420px;top:870px;width:200px;height:112px;z-index:13;">
    <iframe id="YouTube1" src="https://www.youtube.com/embed/ho8U-T5mBhk?rel=1&amp;autohide=0"></iframe>
</div>
<div id="wb_Image2" style="position:absolute;left:4px;top:858px;width:204px;height:135px;z-index:14;">
    <img src="${pageContext.request.contextPath}/img/img0004.png" id="Image2" alt=""></div>
<div id="wb_Image3" style="position:absolute;left:208px;top:858px;width:203px;height:135px;z-index:15;">
    <img src="${pageContext.request.contextPath}/img/img0005.png" id="Image3" alt=""></div>
<div id="WrappingText1"
     style="position:absolute;overflow:hidden;left:635px;top:870px;width:277px;height:112px;z-index:16;">
    <div class="WrappingText1" style="left:4px;top:4px;width:251px;"> A Warehouse Management System (WMS) is a
        software
    </div>
    <div class="WrappingText1" style="left:4px;top:16px;width:246px;">application, designed to support Warehouse or
        Distribution
    </div>
    <div class="WrappingText1" style="left:4px;top:28px;width:42px;">Center
    </div>
    <div class="WrappingText1" style="left:4px;top:40px;width:248px;"> Management in their daily planning, organizing,
        staffing,
    </div>
    <div class="WrappingText1" style="left:4px;top:52px;width:268px;">directing, and controlling the utilization of
        available resources, to
    </div>
    <div class="WrappingText1" style="left:4px;top:64px;width:272px;">move and store materials into, within, and out of
        a warehouse
    </div>
    <div class="WrappingText1" style="left:4px;top:76px;width:267px;"> Staff in the performance of material movement and
        storage
    </div>
    <div class="WrappingText1" style="left:4px;top:88px;width:117px;">in and around a warehouse.</div>
</div>
<label for="" id="Label2"
       style="position:absolute;left:350px;top:518px;width:473px;height:53px;line-height:53px;z-index:17;">To get
    started sign in system or register</label>
<div id="wb_Image4" style="position:absolute;left:106px;top:364px;width:305px;height:461px;z-index:18;">
    <img src="${pageContext.request.contextPath}/img/body1.png" id="Image4" alt=""></div>
<div id="wb_Image5" style="position:absolute;left:564px;top:20px;width:160px;height:155px;z-index:19;">
    <img src="${pageContext.request.contextPath}/img/body2.png" id="Image5" alt=""></div>
</body>
</html>