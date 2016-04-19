<%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="signout_frg" pageEncoding="UTF-8" %>
</head>
<body>
<div id="wb_Shape1" style="position:absolute;left:0px;top:10px;width:1000px;height:175px;z-index:6;">
    <img src="${img_path}/img0007.png" id="Shape1" alt="" style="width:1000px;height:175px;"></div>
<div id="wb_Form2" style="position:absolute;left:763px;top:20px;width:223px;height:153px;z-index:16;">
    <form name="Form1" method="get" action="<c:url value="${sign_out_action}"></c:url>" accept-charset="UTF-8" id="Form2">
        <input type="submit" id="Button2" name="sign_out" value="Sign out"
               style="position:absolute;left:119px;top:109px;width:90px;height:30px;z-index:0;">
        <label for="" id="Label1"
               style="position:absolute;left:19px;top:19px;width:57px;height:18px;line-height:18px;z-index:1;">Login:</label>
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