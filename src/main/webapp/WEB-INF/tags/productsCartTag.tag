<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="products" type="java.util.Set" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag" %>
<div id="cart_table">
    <c:choose>
        <c:when test="${user == null}">
            <div class="user_message_wrapper">
                <div class="user_message">
                    <fmt:message key="not_signed"/>
                    <a href="login.jsp">
                            <fmt:message key="sign_in"/></a>
                            <fmt:message key="to_continue_checkout"/>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <mytag:checkoutBreadcrumbs currentPage="cart"/>
        </c:otherwise>
    </c:choose>
    <div class="custom_span_1_of_5 float-lt"><fmt:message key="product"/></div>
    <div class="custom_span_1_of_5 float-lt"><fmt:message key="quantity"/></div>
    <div class="custom_span_1_of_5 float-lt"><fmt:message key="price_peritem"/></div>
    <div class="custom_span_1_of_5 float-lt"><fmt:message key="total_price"/></div>
    <div class="custom_span_1_of_5 float-lt">
        <button class="clean_cart"><fmt:message key="clean_cart"/></button>
    </div>
    <div class="clear"></div>
    <hr>
    <c:forEach items="${products}" var="product">
        <div class="top-box" id="${product.getId()}">
            <div class="custom_span_1_of_5 float-lt">
                <div class="inner_content clearfix">
                    <div class="product_image float-lt">
                        <img src="photos/products/${product.getPhoto()}" alt=""/>
                    </div>
                    <div class="price float-lt">
                        <p class="title">${product.getProducer()} ${product.getModel()}</p>

                    </div>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="span_text float-lt custom_span_1_of_5">
                <button
                        <c:if test="${cart.getQuantity(product) == 1}">disabled="true"</c:if>
                        class="cart-amount-changer active" name="minus" value="${product.getId()}"> −
                </button>
                <input id="quantityId${product.getId()}" class="quantity" type="text"
                       value="${cart.getQuantity(product)}"/>
                <button class="cart-amount-changer active" name="plus" value="${product.getId()}"> +</button>
            </div>
            <div class="float-lt custom_span_1_of_5 ">
                <div class="span_text">
                    <span class="actual">$${product.getPrice()}.00</span>
                </div>
            </div>
            <div class="float-lt custom_span_1_of_5">
                <div class="span_text" id="productTotal${product.getId()}">
                    <span class="actual">$${product.getPrice() * cart.getQuantity(product)}.00</span>
                </div>
            </div>
            <div class="float-lt custom_span_1_of_5">
                <div class="span_text">
                    <button value="${product.getId()}" class="remove_from_cart"><fmt:message key="remove"/></button>
                </div>
            </div>
            <div class="clear"></div>
            <hr class="product_separator">
        </div>
    </c:forEach>
    <div class="float-lt custom_span_3_of_5"><fmt:message key="total"/>:</div>
    <div class="float-lt custom_span_1_of_5"><span class="actual total">$${cart.getTotal()}.00</span></div>
    <div class="float-lt custom_span_1_of_5"><a href="checkoutDetails.jsp" class="checkout"><fmt:message
            key="to_checkout"/></a>
    </div>
    <div class="clear last_element"></div>
</div>