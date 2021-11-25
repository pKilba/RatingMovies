<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 23.11.2021
  Time: 01:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../css/login.css">
    <title>Title</title>
</head>
<body class="main-bg">
<form method="POST" name="loginForm"  action="${pageContext.request.contextPath}/ratingMovies?command=login" class="flex-box col-md-6">
<div class="login-container text-c animated flipInX">
    <div>
        <h1 class="logo-badge text-whitesmoke"><span class="fa fa-user-circle"></span></h1>
    </div>
    <h3 class="text-whitesmoke">Sign In Template</h3>
    <p class="text-whitesmoke">Sign In</p>
    <div class="container-content">
        <form class="margin-t">
            <div class="form-group">
                <input type="text" class="form-control" minlength="8" maxlength="32" name="login" value="${login}" requiredplaceholder="login" required="">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="*****"  minlength="8" maxlength="32"  required="">
            </div>
            <button type="submit" class="form-button button-l margin-b">Sign In</button>

            <a class="text-darkyellow" href="#"><small>Forgot your password?</small></a>
            <p class="text-whitesmoke text-center"><small>Do not have an account?</small></p>
            <a class="text-darkyellow" href="${pageContext.request.contextPath}/ratingMovies?command=sign-up-page"><small>Sign Up</small></a>
        </form>
        <p class="margin-t text-whitesmoke"><small> Pavel Kilbas &copy; 2021</small> </p>
    </div>
</div>
</form>
</body>
<!--<div class="container py-3">
    <div class="row flex-column">
        <form method="POST" name="loginForm"  action="${pageContext.request.contextPath}/ratingMovies?command=login" class="flex-box col-md-6">
            <h1>Форма для авторизации</h1>
            <div class="mb-3">
                <span class="form-label">Введите логин</span>
                <input type="text" class="form-control" minlength="8" maxlength="32" name="login" value="${login}" required>
            </div>
            <div class="mb-3">
                <span class="form-label">Введите пароль</span>
                <input type="password" id="currentPass" name="password" class="form-control" minlength="8" maxlength="32" required>
            </div>
            <br/>
            <button type="submit" name="Log in" class="btn btn-primary">Залогинеться</button>
        </form>
    </div>
</div>
-->
</html>
