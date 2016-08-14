<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="user" type="entity.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:choose>
    <c:when test="${user eq null || user eq '' }">
        <form action="login" method="post">
            <input class="login_field" type="email" placeholder="E-mail" value="" name="email"/>
            <input class="login_field" type="password" placeholder="<fmt:message key='password'/>" value="" name="password"/>
            <input type="submit" class="login_submit" value=""/>
        </form>
    </c:when>
    <c:otherwise>
        <div class="grettings float-rt">
            <fmt:message key='gretting'/>, ${user.getFirstName()} ${user.getLastName()}
            <form action="logout" method="post" class="float-rt">
                <input class="logout" type="submit" value=""/>
            </form>
        </div>
        <c:choose>
            <c:when test="${user.getPhotoName() ne null}">
                <img class="avatar float-rt" src="<c:url value="/photos/${user.getPhotoName()}.jpg"/>"/>
            </c:when>
            <c:otherwise>
                <img class="avatar float-rt" src="<c:url value="/photos/noavatar.png"/>"/>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>