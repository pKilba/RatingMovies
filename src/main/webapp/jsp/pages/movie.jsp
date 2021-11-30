<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 28.11.2021
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sc" uri="custom-tags" %>

<html>
<head>

    <c:import url="/jsp/partspages/head.jsp"/>
    <title>Title</title>
</head>
<body>

<c:import url="/jsp/partspages/navbar.jsp"/>
<h1>${sessionScope.get("userId")}</h1>
<h1>ФИЛЬМ ВАШ ${movie.getAbout()} </h1>
<p>Комментарии</p>
<c:forEach var="comment" items="${commentList}">
    <p>${comment.getMessage()} </p>
</c:forEach>

<p>Пользователи написавшие комментарии</p>
<c:forEach var="user" items="${userList}">

    <p>${user.getName()}</p>
</c:forEach>

<p>-------------------------------------------------------------</p>
<sc:access role="ADMIN">
<div class="container py-3">
    <div class="row flex-column">
        <form method="POST" name="loginForm"
              action="${pageContext.request.contextPath}/ratingMovies?command=leaveComment&id=${movie.getId()}&idUser=${sessionScope.get("userId")}"
              class="flex-box col-md-6">
            <h1>коммент оставь</h1>
            <div class="mb-3">
                <span class="form-label">Введите комментарий</span>
                <input type="text" id="currentPass" name="leaveComment" class="form-control" minlength="8"
                       maxlength="32" required>
            </div>
            <br/>
            <button type="submit" name="Log in" class="btn btn-primary">Оставить комментарий</button>
        </form>
    </div>
</div>
</sc:access>


</body>
</html>
