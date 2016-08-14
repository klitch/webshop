<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="products" type="java.util.List" %>
<c:set var="counter" scope="page" value="0"/>
<c:forEach items="${products}" var="product">
    <c:if test="${counter % 3 eq 0}">
        <div class="top-box">
    </c:if>
    <div class="col_1_of_3 span_1_of_3">
        <div class="inner_content clearfix">
            <div class="product_image">
                <img src="photos/products/${product.getPhoto()}" alt=""/>
            </div>
            <div class="sale-box"><span class="on_sale title_shop">New</span></div>
            <div class="price">
                <div class="cart-left">
                    <p class="title">${product.getProducer()} ${product.getModel()}</p>

                    <p><fmt:message key="gender" />: ${product.getGender()}</p>

                    <p><fmt:message key="frame_size" />: ${product.getSize()}</p>

                    <div class="price1">
                        <span class="actual">$${product.getPrice()}.00</span>
                    </div>
                </div>
                <label class="cart-right"><input class="invisible addToCart"
                                                 value="${product.getId()}"
                                                 type="button"/></label>

                <div class="clear"></div>
            </div>
        </div>
    </div>
    <c:set var="counter" scope="page" value="${counter+1}"/>
    <c:if test="${counter % 3 eq 0}">
        <div class="clear"></div>
        </div>
    </c:if>
</c:forEach>
<c:if test="${counter%3 ne 0}">
    <div class="clear"></div>
    </div>
</c:if>