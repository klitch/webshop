<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="currentPage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="checkoutBreadcrumb">

    <li class="basket">1. <fmt:message key="cart" /></li>

    <li class="details">2. <fmt:message key="your_details" /> </li>

    <li class="delivery">3. <fmt:message key="confirmation" /></li>

    <li class="done">4. <fmt:message key="all_done" /></li>

    <c:choose>
        <c:when test="${currentPage == 'cart'}">
            <script>
                $(".basket").addClass("basketactive");
                $(".basket").removeClass("basket");
            </script>
        </c:when>
        <c:when test="${currentPage == 'details'}">
            <script>
                $(".basket").wrapInner("<a href='cart.jsp' ></a>");
                $(".basket").addClass("nextactive");
                $(".details").addClass("detailsactive");
                $(".details").removeClass("details");
            </script>
        </c:when>
        <c:when test="${currentPage == 'confirmation'}">
            <script>
                $(".details").wrapInner("<a href='checkoutDetails.jsp' ></a>");
                $(".basket").wrapInner("<a href='cart.jsp' ></a>");
                $(".details").addClass("nextactive");
                $(".delivery").addClass("deliveryactive");
                $(".delivery").removeClass("delivery");
            </script>
        </c:when>
        <c:when test="${currentPage == 'done'}">
            <script>
                $(".delivery").addClass("nextactive");
                $(".done").addClass("doneactive");
                $(".done").removeClass("done");
            </script>
        </c:when>
    </c:choose>
    <div class="clear"></div>
</div>
<div class="clear"></div>