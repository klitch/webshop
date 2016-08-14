<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="mens">
    <div class="main">
        <div class="wrap">
            <div class="cont span_2_of_3">
                <form id="filter_form">
                    <%@ include file="/WEB-INF/jspf/head_toolbar.jspf" %>
                    <c:choose>
                        <c:when test="${products != null && products.size() ne 0}">
                            <mytag:catalogTag products="${products}"/>
                        </c:when>
                        <c:otherwise>
                        <h1 class="largeHeader"><fmt:message key="filter_error" /></h1>
                        </c:otherwise>
                    </c:choose>
            </div>
            <%@ include file="/WEB-INF/jspf/filter_bar.jspf" %>
            <div class="clear"></div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>