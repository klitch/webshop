<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="cart">
    <div class="wrap">
        <mytag:checkoutBreadcrumbs currentPage="done"/>
        <div class="success_tick">
            <img src="../../../style/images/success_tick.png"/>
        </div>
        <div class="order_success_info">
            <fmt:message key="order_success" />
        </div>
    </div>
</div>
<script src="../../../style/js/checkout.details.js"></script>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>