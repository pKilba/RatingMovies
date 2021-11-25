<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="/jsp/partspages/head.jsp"/>
    <title>Title</title>
        </head>
<body>
<c:import url="/jsp/partspages/navbar.jsp"/>
<p>ee</p>

<a href="${pageContext.request.contextPath}/ratingMovies?command=login-page"> Войти</a>
<a href="${pageContext.request.contextPath}/ratingMovies?command=sign-up-page"> Зарегаться</a>

</body>

        </html>

