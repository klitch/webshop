<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="cart" type="entity.Cart" %>
<div class="custom_span_1_of_4 float-lt"><fmt:message key="product" /></div>
<div class="custom_span_1_of_4 float-lt"><fmt:message key="quantity" /></div>
<div class="custom_span_1_of_4 float-lt"><fmt:message key="price_peritem" /></div>
<div class="custom_span_1_of_4 float-lt"><fmt:message key="total_price" /></div>
<div class="clear"></div>
<hr>
<c:forEach items="${cart.productSet()}" var="product">
    <div class="top-box" id="${product.getId()}">
        <div class="custom_span_1_of_4 float-lt">
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
        <div class="span_text float-lt custom_span_1_of_4">
            <div class="quantity">${cart.getQuantity(product)}</div>
        </div>
        <div class="float-lt custom_span_1_of_4 ">
            <div class="span_text">
                <span class="actual">$${product.getPrice()}.00</span>
            </div>
        </div>
        <div class="float-lt custom_span_1_of_4">
            <div class="span_text" id="productTotal${product.getId()}">
                <span class="actual">$${product.getPrice() * cart.getQuantity(product)}.00</span>
            </div>
        </div>
        <div class="clear"></div>
        <hr class="product_separator">
    </div>
</c:forEach>
<div class="float-lt custom_span_1_of_4">&nbsp;</div>
<div class="float-lt custom_span_1_of_4">&nbsp;</div>
<div class="float-lt custom_span_1_of_4"><fmt:message key='total'/></div>
<div class="float-lt custom_span_1_of_4"><span class="actual total">$${cart.getTotal()}.00</span></div>
<div class="clear last_element"></div>