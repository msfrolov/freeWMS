<%@ attribute name="title" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="img_path" value="${pageContext.request.contextPath}/img"/>
<c:url var="css_path" value="${pageContext.request.contextPath}/style"/>
<c:url var="sign_in_action" value="/wms/signin"/>
<html>
<head>
    <meta charset="utf-8">
    <title>WMS</title>
    <meta name="WMS" content="Warehouse Management System">
    <link href="${css_path}/font-awesome.min.css" rel="stylesheet">
    <link href="${css_path}/Untitled5.css" rel="stylesheet">
    <link href="${css_path}/home.css" rel="stylesheet">
</head>
<body>
<div id="wb_Shape1" style="position:absolute;left:0px;top:10px;width:1000px;height:175px;z-index:6;">
    <img src="${img_path}/img0007.png" id="Shape1" alt="" style="width:1000px;height:175px;"></div>
<div id="wb_Form2" style="position:absolute;left:763px;top:20px;width:223px;height:153px;z-index:16;">
    <form name="Form1" method="get" action="${sign_in_action}" accept-charset="UTF-8" id="Form2">
        <input type="submit" id="Button2" name="sign_out" value="Sign out"
               style="position:absolute;left:119px;top:109px;width:90px;height:30px;z-index:0;">
        <label for="" id="Label1"
               style="position:absolute;left:19px;top:19px;width:57px;height:18px;line-height:18px;z-index:1;">Login</label>
        <label for="" id="Label4"
               style="position:absolute;left:19px;top:48px;width:57px;height:18px;line-height:18px;z-index:2;">Role:</label>
        <label for="" id="Label2"
               style="position:absolute;left:95px;top:48px;width:57px;height:18px;line-height:18px;z-index:3;">${user.role.name}</label>
        <label for="" id="Label3"
               style="position:absolute;left:95px;top:19px;width:57px;height:18px;line-height:18px;z-index:4;">${user.name}</label>
    </form>
</div>
<div id="wb_Image1" style="position:absolute;left:45px;top:40px;width:466px;height:113px;z-index:8;">
    <img src="${img_path}/logo1.png" id="Image1" alt=""></div>
<div id="wb_Image5" style="position:absolute;left:564px;top:20px;width:160px;height:155px;z-index:15;">
    <img src="${img_path}/body2.png" id="Image5" alt=""></div>
<div id="wb_ResponsiveMenu1" style="position:absolute;left:0px;top:187px;width:1000px;height:70px;z-index:17;">
    <label class="toggle" for="ResponsiveMenu1-submenu" id="ResponsiveMenu1-title">Menu
        <div id="ResponsiveMenu1-icon"><span>&nbsp;</span><span>&nbsp;</span><span>&nbsp;</span></div>
    </label>
    <input type="checkbox" id="ResponsiveMenu1-submenu">
    <ul class="ResponsiveMenu1" id="ResponsiveMenu1">
        <li><a href="#"><i class="fa fa-university fa-2x">&nbsp;</i><br>Home</a></li>
        <li>
            <label for="ResponsiveMenu1-submenu-0" class="toggle"><i class="fa fa-suitcase fa-2x">&nbsp;</i>Catalogs<b
                    class="arrow-down"></b></label>
            <a href="#" title="Catalog"><i class="fa fa-suitcase fa-2x">&nbsp;</i><br>Catalogs<b class="arrow-down"></b></a>
            <input type="checkbox" id="ResponsiveMenu1-submenu-0">
            <ul>
                <li><a href="#"><i class="fa fa-cubes fa-2x">&nbsp;</i>Products</a></li>
                <li><a href="#"><i class="fa fa-stack-overflow fa-2x">&nbsp;</i>Warehouses</a></li>
                <li><a href="#" title="Counterparts"><i class="fa fa-user-secret fa-2x">&nbsp;</i>Counterparts</a></li>
                <li><a href="#" title="Product types"><i class="fa fa-list-ul fa-2x">&nbsp;</i>Product&nbsp;types</a>
                </li>
                <li><a href="#"><i class="fa fa-codepen fa-2x">&nbsp;</i>Measures</a></li>
            </ul>
        </li>
        <li>
            <label for="ResponsiveMenu1-submenu-1" class="toggle"><i class="fa fa-book fa-2x">&nbsp;</i>Documents<b
                    class="arrow-down"></b></label>
            <a href="#"><i class="fa fa-book fa-2x">&nbsp;</i><br>Documents<b class="arrow-down"></b></a>
            <input type="checkbox" id="ResponsiveMenu1-submenu-1">
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
            <label for="ResponsiveMenu1-submenu-2" class="toggle"><i class="fa fa-area-chart fa-2x">&nbsp;</i>Reports<b
                    class="arrow-down"></b></label>
            <a href="#" title="Reports"><i class="fa fa-area-chart fa-2x">&nbsp;</i><br>Reports<b
                    class="arrow-down"></b></a>
            <input type="checkbox" id="ResponsiveMenu1-submenu-2">
            <ul>
                <li><a href="#" title="Balance products"><i class="fa fa-tasks fa-2x">
                    &nbsp;</i>Balance&nbsp;products</a></li>
                <li><a href="#" title="Turnover with counterparties"><i class="fa fa-retweet fa-2x">&nbsp;</i>Turnover&nbsp;with&nbsp;counterparties</a>
                </li>
            </ul>
        </li>
        <li>
            <label for="ResponsiveMenu1-submenu-3" class="toggle"><i class="fa fa-gears fa-2x">
                &nbsp;</i>Administration<b class="arrow-down"></b></label>
            <a href="#" title="Administration"><i class="fa fa-gears fa-2x">&nbsp;</i><br>Administration<b
                    class="arrow-down"></b></a>
            <input type="checkbox" id="ResponsiveMenu1-submenu-3">
            <ul>
                <li><a href="#" title="Users"><i class="fa fa-users fa-2x">&nbsp;</i>Users</a></li>
                <li><a href="#" title="Roles"><i class="fa fa-tachometer fa-2x">&nbsp;</i>Roles</a></li>
            </ul>
        </li>
        <li><a href="#" title="Cabinet"><i class="fa fa-user fa-2x">&nbsp;</i><br>Cabinet</a></li>
    </ul>
</div>