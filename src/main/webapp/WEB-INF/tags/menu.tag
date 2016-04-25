<%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="menu" pageEncoding="UTF-8" %>
<div id="wb_ResponsiveMenu1" style="position:absolute;left:0px;top:187px;width:1000px;height:70px;z-index:30;">
    <label class="toggle" for="ResponsiveMenu1-submenu" id="ResponsiveMenu1-title">Menu
        <div id="ResponsiveMenu1-icon"><span>&nbsp;</span><span>&nbsp;</span><span>&nbsp;</span></div>
    </label>
    <input type="checkbox" id="ResponsiveMenu1-submenu">
    <ul class="ResponsiveMenu1" id="ResponsiveMenu1">
        <li><a href="<c:url value="${home_action}"></c:url>"><i class="fa fa-university fa-2x">&nbsp;</i><br>Home</a>
        </li>
        <li>
            <label for="ResponsiveMenu1-submenu-0" class="toggle"><i class="fa fa-suitcase fa-2x">&nbsp;</i>Catalogs<b
                    class="arrow-down"></b></label>
            <a href="#" title="Catalog"><i class="fa fa-suitcase fa-2x">&nbsp;</i><br>Catalogs<b class="arrow-down"></b></a>
            <input type="checkbox" id="ResponsiveMenu1-submenu-0">
            <ul>
                <li><a href="<c:url value="${products_catalog}"></c:url>"><i class="fa fa-cubes fa-2x">&nbsp;</i>Products</a>
                </li>
                <li><a href="#"><i class="fa fa-stack-overflow fa-2x">&nbsp;</i>Warehouses</a></li>
                <li><a href="#" title="Counterparts"><i class="fa fa-user-secret fa-2x">&nbsp;</i>Counterparts</a></li>
                <li><a href="#" title="Product types"><i class="fa fa-list-ul fa-2x">&nbsp;</i>Product&nbsp;types</a>
                </li>
                <li><a href="#"><i class="fa fa-codepen fa-2x">&nbsp;</i>Measures</a></li>
            </ul>
        </li>
        <li>
        <li>
            <label for="ResponsiveMenu1-submenu-1" class="toggle"><i class="fa fa-book fa-2x">&nbsp;</i>Documents<b
                    class="arrow-down"></b></label>
            <a href="#"><i class="fa fa-book fa-2x">&nbsp;</i><br>Documents<b class="arrow-down"></b></a>
            <input type="checkbox" id="ResponsiveMenu1-submenu-1">
            <ul>
                <li><a href="<c:url value="${receipt_document}"></c:url>" title="Receipt document"><i
                        class="fa fa-calendar-plus-o fa-2x">&nbsp;</i>Receipt&nbsp;document</a>
                </li>
                <li><a href="<c:url value="${move_document}"></c:url>" title="Move document"><i
                        class="fa fa-calendar-times-o fa-2x">&nbsp;</i>Move&nbsp;document</a>
                </li>
                <li><a href="<c:url value="${expense_document}"></c:url>" title="Expense documen"><i
                        class="fa fa-calendar-minus-o fa-2x">&nbsp;</i>Expense&nbsp;documen</a>
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
                <li><a href="<c:url value="${balance_products}"></c:url>" title="Balance products"><i
                        class="fa fa-tasks fa-2x">
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
        <%--<li><a href="google.com" title="Cabinet"><i class="fa fa-user fa-2x">&nbsp;</i><br>Cabinet</a></li>--%>
        <li><a href="<c:url value="${cabinet_action}"></c:url>" title="Cabinet"><i class="fa fa-user fa-2x">
            &nbsp;</i><br>Cabinet</a></li>
    </ul>
</div>