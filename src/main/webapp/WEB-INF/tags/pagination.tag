<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag" %>

<ul class="dc_pagination dc_paginationA dc_paginationA06">
    <li class="page"> <fmt:message key="pages" /></li>
    <script>
        $(function () {
            $('#filter_form').submit(function () {
                $('#first_page').prop('checked', true);
                return true;
            });
        });
    </script>
    <c:choose>

        <c:when test="${filter.getPageNumber() != null && filter.getPageNumber() > 3 && filter.getPageNumber()+2 < pagesCount}">
            <mytag:createPagination beginFrom="${filter.getPageNumber()-2}" endWith="${filter.getPageNumber()+2}"/>
        </c:when>


        <c:when test="${(filter.getPageNumber() != null && filter.getPageNumber() <= 3) || filter.getPageNumber() > pagesCount}">
            <c:if test="${pagesCount < 5}">
                <c:set var="numberOfPages" value="${pagesCount}"/>
            </c:if>
            <c:if test="${pagesCount > 5}">
                <c:set var="numberOfPages" value="5"/>
            </c:if>
            <mytag:createPagination beginFrom="1" endWith="${numberOfPages}"/>
        </c:when>


        <c:when test="${filter.getPageNumber()+2 >= pagesCount}">
            <mytag:createPagination beginFrom="${pagesCount-4}" endWith="${pagesCount}"/>
        </c:when>

    </c:choose>
    <div class="clear"></div>
</ul>