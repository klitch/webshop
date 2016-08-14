<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="cart">
    <div class="wrap">
        <c:choose>
            <c:when test="${cart != null && cart.size() ne 0}">
                <div id="cart_table">
                    <mytag:checkoutBreadcrumbs currentPage="confirmation"/>
                    <mytag:productsListTag cart="${cart}"/>
                </div>
            </c:when>
        </c:choose>
        <div class="clear"></div>
        <hr class="product_separator" style="width:50%; margin:-40px auto 20px;">
        <div class="clear"></div>
        <div class="order_details">
            <div class="float-lt custom_span_2_of_4">
                <fmt:message key="payment_type" />: ${paymentType}
            </div>
            <c:if test="${not empty sessionScope.card}">
                <div class="float-lt custom_span_1_of_4">
                    <fmt:message key="card_number" />: ${card}
                </div>
            </c:if>
            <div class="clear"></div>
            <div class="float-lt custom_span_2_of_4">
                <fmt:message key="shipping_type" />: ${shippingType}
            </div>
            <c:if test="${not empty address}">
                <div class="float-lt custom_span_1_of_4">
                    <fmt:message key="address" />: ${address}
                </div>
            </c:if>
            <div class="clear"></div>
        </div>
        <hr class="product_separator" style="width:25%; margin:0 auto 20px;">
        <div class="confirmation_buttons">
            <div class="float-lt custom_span_1_of_4">&nbsp;</div>
            <div class="float-lt custom_span_1_of_4">
                <button class="checkout"
                        onclick="window.location = 'checkoutDetails.jsp'">< <fmt:message key="previous" />
                </button>
            </div>
            <div class="float-lt custom_span_1_of_4" style="margin-bottom:40px;">
                <button class="checkout"
                        onclick="window.location = 'create_order'"><fmt:message key="confirm" /> >
                </button>
            </div>
        </div>
    </div>
    <div class="clear"></div>
</div>
</div>
<script src="../../../style/js/checkout.details.js"></script>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>