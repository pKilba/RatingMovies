<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sc" uri="custom-tags" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<header class="p-3 bg-dark text-white">
  <div class="container">
    <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        <sc:access role="GUEST">
        <ul class="nav col col-lg-auto me-lg-auto mr-auto mb-2 justify-content-center mb-md-0">
          <li><a href="${pageContext.request.contextPath}/ratingMovies?command=home-page"
                 class="nav-link px-2 text-white">Домой</a></li>
          <li><a href="${pageContext.request.contextPath}/ratingMovies?command=login-page"
                 class="nav-link px-2 text-white">Просмотр фильмов</a>
        </ul>
        <div class="text-end">
          <a type="button" href="${pageContext.request.contextPath}/ratingMovies?command=login-page"
             class="btn btn-outline-light me-2">Войти</a>
          <a type="button" href="${pageContext.request.contextPath}/ratingMovies?command=sign-up-page"
             class="btn btn-warning">Зарегистрироваться</a>
          <a type="button" href="${pageContext.request.contextPath}/ratingMovies?command=localization&locale=ru"
             class="btn btn-outline-light me-2 langToggle" data-onstyle="light">RU</a>
          <a type="button" href="${pageContext.request.contextPath}/ratingMovies?command=localization&locale=en"
             class="btn btn-outline-light me-2 langToggle" data-onstyle="light">EN</a>
        </div>
        </sc:access>

    </div>
  </div>
</header>
