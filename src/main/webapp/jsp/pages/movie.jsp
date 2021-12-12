<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../css/movie.css">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:import url="/jsp/partspages/head.jsp"/>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale"/>
</head>
<body style="background-image:
        url(${pageContext.request.contextPath}${movie.getBackground()})">
<c:import url="/jsp/partspages/navbar.jsp"/>
<div class="container" style="height: 500px">
    <div class="cellphone-container">
        <div class="movie" style="height: 900px">
            <div style=" background-image:
                    url(${pageContext.request.contextPath}${movie.getPoster()})
                    " class="movie-img"></div>
            <div class="text-movie-cont" >
                    <div class="col1">
                        <h1 style="color: white">${movie.getName()}</h1>
                        <ul class="movie-gen">
                            <li>${movie.getDuration()}min /</li>
                            <li style="padding-bottom: 10px"> ${movie.getGenre()}</li>
                        </ul>
                    </div>
                <div class="mr-grid summary-row">
                    <div class="col2">
                        <h5>${movie.getProducer()}</h5>
                    </div>
                    <div class="col2">
                        <ul class="movie-likes">
                            <li><i class="material-icons">Like</i>${movie.getAmount_like()}</li>
                            <li><i class="material-icons">DisLike</i>${movie.getAmount_dislike()}</li>
                        </ul>
                    </div>
                </div>
                <div class="mr-grid">
                    <div class="col1">
                        <p class="movie-description">${movie.getAbout()} </p>
                    </div>
                </div>
                    <div class="col1">
                        <a href="${pageContext.request.contextPath}
                        /ratingMovies?command=reviews-page&p=1&s=10&movieId=${movie.getId()}">Комментарий</a>
                    </div>

            </div>
        </div>
    </div>
</div>
</body>
</html>