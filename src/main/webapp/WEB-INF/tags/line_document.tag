<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %><%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="line_document" pageEncoding="UTF-8" %>
   <tr>${status.count}
                <td class="cell2">&nbsp;&nbsp;${start + status.count -1}</td>
                <td class="cell1">&nbsp;&nbsp;${product_elem.product.name}</td>
                <td class="cell2">&nbsp;&nbsp;${product_elem.count}</td>
                <td class="cell2">
                    <input name="delete" type="image" value="${start + status.count-2}" src="${img_path}/delete.png"
                           height="20">
                        <%--<img src="${img_path}/delete.png" height="20">--%>
                    </input>

                </td>
            </tr>