<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:header/>
<div id="wb_Shape3" style="position:absolute;left:0px;top:258px;width:1000px;height:500px;z-index:7;">
    <img src="${img_path}/img0006.png" id="Shape3" alt="" style="width:1000px;height:500px;">

</div>
<div id="wb_Image4" style="position:absolute;left:6px;top:264px;width:989px;height:489px;z-index:14;">
    <img src="${img_path}/body2.jpg" id="Image4" alt="">
    <div id="status" style="position: absolute;padding: 0;top: 160px;left: 315px;">
        <h1>Status Code: ${statusCode} </h1><br>
        <h3>Requested URI: ${reqUri} </h3><br>
    </div>
</div>
<t:footer/>
