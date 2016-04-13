<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="img_path" value="${pageContext.request.contextPath}/img"/>
<c:url var="css_path" value="${pageContext.request.contextPath}/style"/>
<c:url var="sign_in_action" value="/wms/signin"/>
<html>
<head>
<meta charset="utf-8">
<title>signin</title>
<meta name="generator" content="WYSIWYG Web Builder 11 Trial Version - http://www.wysiwygwebbuilder.com">
<link href="${css_path}/font-awesome.min.css" rel="stylesheet">
<link href="${css_path}/Untitled5.css" rel="stylesheet">
<link href="${css_path}/home.css" rel="stylesheet">
</head>
<body>
<a href="http://www.wysiwygwebbuilder.com" target="_blank"><img src="${img_path}/builtwithwwb11.png" alt="WYSIWYG Web Builder" style="position:absolute;left:912px;top:869px;border-width:0;z-index:250"></a>
<div id="wb_Shape1" style="position:absolute;left:0px;top:10px;width:1000px;height:175px;z-index:6;">
<img src="${img_path}/img0007.png" id="Shape1" alt="" style="width:1000px;height:175px;"></div>
<div id="wb_Shape3" style="position:absolute;left:0px;top:258px;width:1000px;height:500px;z-index:7;">
<img src="${img_path}/img0006.png" id="Shape3" alt="" style="width:1000px;height:500px;"></div>
<div id="wb_Image1" style="position:absolute;left:45px;top:40px;width:466px;height:113px;z-index:8;">
<img src="${img_path}/logo1.png" id="Image1" alt=""></div>
<div id="wb_Shape2" style="position:absolute;left:0px;top:759px;width:1000px;height:141px;z-index:9;">
<img src="${img_path}/img0008.png" id="Shape2" alt="" style="width:1000px;height:141px;"></div>
<div id="wb_YouTube1" style="position:absolute;left:416px;top:773px;width:200px;height:112px;z-index:10;">
<iframe id="YouTube1" src="https://www.youtube.com/embed/ho8U-T5mBhk?rel=1&amp;autohide=0"></iframe></div>
<div id="wb_Image2" style="position:absolute;left:0px;top:762px;width:204px;height:135px;z-index:11;">
<img src="${img_path}/img0009.png" id="Image2" alt=""></div>
<div id="wb_Image3" style="position:absolute;left:204px;top:762px;width:203px;height:135px;z-index:12;">
<img src="${img_path}/img0010.png" id="Image3" alt=""></div>
<div id="WrappingText1" style="position:absolute;overflow:hidden;left:623px;top:773px;width:277px;height:112px;z-index:13;">
<div class="WrappingText1" style="left:4px;top:4px;width:251px;">    A Warehouse Management System (WMS) is a software </div>
<div class="WrappingText1" style="left:4px;top:16px;width:246px;">application, designed to support Warehouse or Distribution </div>
<div class="WrappingText1" style="left:4px;top:28px;width:42px;">Center
</div>
<div class="WrappingText1" style="left:4px;top:40px;width:248px;">    Management in their daily planning, organizing, staffing, </div>
<div class="WrappingText1" style="left:4px;top:52px;width:268px;">directing, and controlling the utilization of available resources, to</div>
<div class="WrappingText1" style="left:4px;top:64px;width:272px;">move and store materials into, within, and out of a warehouse
</div>
<div class="WrappingText1" style="left:4px;top:76px;width:267px;">    Staff in the performance of material movement and storage </div>
<div class="WrappingText1" style="left:4px;top:88px;width:117px;">in and around a warehouse. </div>
</div>
<div id="wb_Image4" style="position:absolute;left:6px;top:264px;width:989px;height:489px;z-index:14;">
<img src="${img_path}/body2.jpg" id="Image4" alt=""></div>
<div id="wb_Image5" style="position:absolute;left:564px;top:20px;width:160px;height:155px;z-index:15;">
<img src="${img_path}/body2.png" id="Image5" alt=""></div>
<div id="wb_Form2" style="position:absolute;left:763px;top:20px;width:223px;height:153px;z-index:16;">
<form name="Form1" method="get" action="${sign_in_action}" accept-charset="UTF-8" id="Form2">
<input type="submit" id="Button2" name="sign_out" value="Sign out" style="position:absolute;left:119px;top:109px;width:90px;height:30px;z-index:0;">
<label for="" id="Label1" style="position:absolute;left:19px;top:19px;width:57px;height:18px;line-height:18px;z-index:1;">Login</label>
<label for="" id="Label4" style="position:absolute;left:19px;top:48px;width:57px;height:18px;line-height:18px;z-index:2;">Role:</label>
<label for="" id="Label2" style="position:absolute;left:95px;top:48px;width:57px;height:18px;line-height:18px;z-index:3;">${user.role.name}</label>
<label for="" id="Label3" style="position:absolute;left:95px;top:19px;width:57px;height:18px;line-height:18px;z-index:4;">${user.name}</label>
</form>
</div>
<div id="wb_ResponsiveMenu1" style="position:absolute;left:0px;top:187px;width:1000px;height:70px;z-index:17;">
<label class="toggle" for="ResponsiveMenu1-submenu" id="ResponsiveMenu1-title">Menu<div id="ResponsiveMenu1-icon"><span>&nbsp;</span><span>&nbsp;</span><span>&nbsp;</span></div></label>
<input type="checkbox" id="ResponsiveMenu1-submenu">
<ul class="ResponsiveMenu1" id="ResponsiveMenu1">
<li><a href="#"><i class="fa fa-university fa-2x">&nbsp;</i><br>Home</a></li>
<li>
<label for="ResponsiveMenu1-submenu-0" class="toggle"><i class="fa fa-suitcase fa-2x">&nbsp;</i>Catalogs<b class="arrow-down"></b></label>
<a href="#" title="Catalog"><i class="fa fa-suitcase fa-2x">&nbsp;</i><br>Catalogs<b class="arrow-down"></b></a>
<input type="checkbox" id="ResponsiveMenu1-submenu-0">
<ul>
<li><a href="#"><i class="fa fa-cubes fa-2x">&nbsp;</i>Products</a></li>
<li><a href="#"><i class="fa fa-stack-overflow fa-2x">&nbsp;</i>Warehouses</a></li>
<li><a href="#" title="Counterparts"><i class="fa fa-user-secret fa-2x">&nbsp;</i>Counterparts</a></li>
<li><a href="#" title="Product types"><i class="fa fa-list-ul fa-2x">&nbsp;</i>Product&nbsp;types</a></li>
<li><a href="#"><i class="fa fa-codepen fa-2x">&nbsp;</i>Measures</a></li>
</ul>
</li>
<li>
<label for="ResponsiveMenu1-submenu-1" class="toggle"><i class="fa fa-book fa-2x">&nbsp;</i>Documents<b class="arrow-down"></b></label>
<a href="#"><i class="fa fa-book fa-2x">&nbsp;</i><br>Documents<b class="arrow-down"></b></a>
<input type="checkbox" id="ResponsiveMenu1-submenu-1">
<ul>
<li><a href="#" title="Receipt documents"><i class="fa fa-calendar-plus-o fa-2x">&nbsp;</i>Receipt&nbsp;documents</a></li>
<li><a href="#" title="Move documents"><i class="fa fa-calendar-times-o fa-2x">&nbsp;</i>Move&nbsp;documents</a></li>
<li><a href="#" title="Expense documens"><i class="fa fa-calendar-minus-o fa-2x">&nbsp;</i>Expense&nbsp;documens</a></li>
</ul>
</li>
<li>
<label for="ResponsiveMenu1-submenu-2" class="toggle"><i class="fa fa-area-chart fa-2x">&nbsp;</i>Reports<b class="arrow-down"></b></label>
<a href="#" title="Reports"><i class="fa fa-area-chart fa-2x">&nbsp;</i><br>Reports<b class="arrow-down"></b></a>
<input type="checkbox" id="ResponsiveMenu1-submenu-2">
<ul>
<li><a href="#" title="Balance products"><i class="fa fa-tasks fa-2x">&nbsp;</i>Balance&nbsp;products</a></li>
<li><a href="#" title="Turnover with counterparties"><i class="fa fa-retweet fa-2x">&nbsp;</i>Turnover&nbsp;with&nbsp;counterparties</a></li>
</ul>
</li>
<li>
<label for="ResponsiveMenu1-submenu-3" class="toggle"><i class="fa fa-gears fa-2x">&nbsp;</i>Administration<b class="arrow-down"></b></label>
<a href="#" title="Administration"><i class="fa fa-gears fa-2x">&nbsp;</i><br>Administration<b class="arrow-down"></b></a>
<input type="checkbox" id="ResponsiveMenu1-submenu-3">
<ul>
<li><a href="#" title="Users"><i class="fa fa-users fa-2x">&nbsp;</i>Users</a></li>
<li><a href="#" title="Roles"><i class="fa fa-tachometer fa-2x">&nbsp;</i>Roles</a></li>
</ul>
</li>
<li><a href="#" title="Cabinet"><i class="fa fa-user fa-2x">&nbsp;</i><br>Cabinet</a></li>
</ul>
</div>
</body>
</html>