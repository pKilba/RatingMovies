
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>sign up really</title>
</head>
<body>

<h1>ЧЕЛ РЕГАЙСЯ ДАВАЙ </h1>

<div class="row flex-column">
    <form name="signupForm" method="POST" action="${pageContext.request.contextPath}/ratingMovies?command=sign-up"
          class="flex-box col-md-6">
        <h1>"Окно регистрации"</h1>
        <div class="mb-3">
            <span class="form-label">введите логин</span>
            <input type="text" class="form-control" name="login" id="loginRegForm" minlength="8" maxlength="32"
                   required>
        </div>
        <div class="mb-3">
            <span class="form-label">введите пароль</span>
            <input type="password" name="password" id="currentPass" minlength="8" maxlength="32" class="form-control password"
                   required>
        </div>
        <div class="mb-3">
            <span class="form-label">Введите имя</span>
            <input type="text" name="name" minlength="4" maxlength="32" class="form-control" required>
        </div>
        <div class="mb-3">
            <span class="form-label">Введите аккаунт телеграм</span>
            <input type="text" name="telegram" minlength="2" maxlength="32" class="form-control" required>
        </div>
        <div class="mb-3">
            <span class="form-label">Введите емейл</span>
            <input type="email" name="email" id="emailRegForm" minlength="5" maxlength="64" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Регайся брат</button>
    </form>
</div>
</body>
</html>
