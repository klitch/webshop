<%@ page isErrorPage="true" %>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="login">
    <div class="wrap">
        <div class="page-not-found">
            <c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
            <c:set var="msg" value="${requestScope['javax.servlet.error.message']}"/>
            <h1>${code}</h1>
            <p align="center">${msg}</p>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>