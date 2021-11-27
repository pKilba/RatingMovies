<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<html lang="">

<head>
    <c:import url="/jsp/partspages/head.jsp"/>
    <title>
        f1
    </title>
</head>

<body>
<c:import url="/jsp/partspages/navbar.jsp"/>

<div class="container py-3">
    <div class="table-responsive">
        <div class="table-wrapper">

            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Имя пользователя</th>
                    <th>Логин пользоватлея</th>
                    <th>Дата создание аккаунта</th>
                    <th>Роль юзера</th>
                    <th>Статус пользователя</th>
                </tr>
                </thead>
                <tbody>
                <%! private int i = 0; %>

                <c:forEach items="${userList}" var="user">
                    <tr>
                        <td><%= ++i %>
                        </td>
                        <td>
                                ${user.getName()}
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/ratingMovies?command=profile-page&id=${user.getId()}">
                                    ${user.getLogin()}
                            </a>
                        </td>
                        <td>${user.getCreateTime()}</td>
                        <td>${user.getUserRole()}</td>
                        <td>
                            <span>${user.getUserStatus()}</span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</div>

</body>
</html>