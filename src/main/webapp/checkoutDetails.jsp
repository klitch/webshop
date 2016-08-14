<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="cart">
    <div class="wrap">
        <c:choose>
            <c:when test="${cart != null && cart.size() ne 0}">
                <div id="cart_table">
                    <mytag:checkoutBreadcrumbs currentPage="details"/>
                    <mytag:productsListTag cart="${cart}"/>
                </div>
                <div class="clear"></div>
                <hr class="product_separator" style="width:50%; margin:-40px auto 10px;">
                <div class="clear"></div>
                <form class="order_details_form" action="order_details" method="POST">
                    <div class="order_details">

                        <div class="float-lt custom_span_2_of_4">
                            <select class="details_input" id="payment_type" name="payment_type">
                                <option
                                        <c:if test="${paymentType == 'Cash' || paymentType == null}">selected='selected'</c:if>
                                        value="cash"> <fmt:message key='cash'/>
                                </option>
                                <option
                                        <c:if test="${paymentType == 'Card'}">selected='selected'</c:if>
                                        value="card"> <fmt:message key='card'/>
                                </option>
                            </select>
                        </div>

                        <div class="float-lt custom_span_1_of_4">
                            <input class="details_input"
                                   id="card_number"
                                   <c:if test="${paymentType == 'Cash' || paymentType == null}">disabled="disabled"</c:if>
                                   autocomplete="off" maxlength="16"
                                   placeholder="<fmt:message key='card_number'/>" name='card'
                                    />

                            <div class="errorMessage">${cardError}</div>
                        </div>
                        <div class="clear"></div>

                        <div class="float-lt custom_span_2_of_4">
                            <select class="details_input" id="shipping_type" name="shipping_type">
                                <option
                                        <c:if test="${shippingType == 'Self-pickup' || shippingType == null}">selected='selected'
                                </c:if>
                                        value="self_pickup"> <fmt:message key='search_model'/>
                                </option>
                                <option
                                        <c:if test="${shippingType == 'To the door'}">selected='selected'</c:if>
                                        value="to_door"> <fmt:message key='to_door'/>
                                </option>
                            </select>
                        </div>

                        <div class="float-lt custom_span_1_of_4">
                            <input class="details_input"
                                   id="address"
                                   <c:if test="${shippingType == 'Self-pickup' || shippingType == null}">disabled='disabled'</c:if>
                                   autocomplete="off" maxlength="16"
                                   placeholder="<fmt:message key='address'/>" name='address'
                                   value='${address}'/>

                            <div class="errorMessage">${addressError}</div>
                        </div>

                        <div class="clear"></div>
                    </div>
                    <hr class="product_separator" style="width:25%; margin:0 auto 20px;">
                    <div class="float-lt custom_span_1_of_4">&nbsp;</div>
                    <div class="float-lt custom_span_1_of_4" style="margin-bottom:40px;">
                        <button class="checkout"
                                onclick="window.location = 'cart.jsp'; return false;">< <fmt:message key="previous" />
                        </button>
                    </div>
                    <div class="float-lt custom_span_1_of_4" style="margin-bottom:40px;">
                        <input type="submit" class="checkout"
                               value="<fmt:message key='to_checkout'/> >">
                        </input>
                    </div>
                    <div class="clear"></div>
                </form>
                <script src="../../../style/js/checkout.details.js"></script>
            </c:when>
        </c:choose>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>