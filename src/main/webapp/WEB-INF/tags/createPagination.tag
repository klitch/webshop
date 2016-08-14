<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute required="true" name="beginFrom" %>
<%@ attribute required="true" name="endWith" %>
<c:forEach begin="${beginFrom}" end="${endWith}" var="i">
    <li>
        <label>
            <input class="invisible" type="radio" onchange="this.form.submit()"
                   <c:if test="${filter.getPageNumber() == i}">checked</c:if>
                   name="page" value="${i}">

            <p class="page_number <c:if test='${filter.getPageNumber() == i}'>active_page</c:if>">
                    ${i}</p>
        </label>
    </li>
</c:forEach>