<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="cart">
    <div class="wrap">
        <c:choose>
            <c:when test="${cart != null && cart.size() ne 0}">
                <mytag:productsCartTag products="${cart.productSet()}"/>
            </c:when>
        </c:choose>
        <div class="empty_cart_wrapper display-false">
            <div class="largeHeader empty_cart">
                <div class="cart_icon float-lt">&nbsp;</div>
                <div class="cart_title float-lt"><fmt:message key="cart_empty" /></div>
                <div class="clear"></div>
            </div>
            <h1 class="largeHeader empty_cart"><fmt:message key="cart_empty_message" /></h1>
        </div>
    </div>
    <div class="clear"></div>
</div>
<script>$('.top-box').last().find('.product_separator').css("opacity", 1);</script>
<script>
    $(document).ready(function () {
        if ($(".actual").size() == 0) {
            $(".empty_cart_wrapper").removeClass("display-false");
        }
    });

</script>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>