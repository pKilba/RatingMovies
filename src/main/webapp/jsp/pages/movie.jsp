<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <link rel="stylesheet" type="text/css" href="../../css/movie.css">
    <c:import url="/jsp/partspages/head.jsp"/>
    <title>Title</title>
</head>
<body>

<c:import url="/jsp/partspages/navbar.jsp"/>
<%! private int i = 0; %>


<div class="container py-3">
    <div class="table-responsive">
        <div class="table-wrapper">
<c:forEach items="${movieList}" varStatus="counter" var="movie">
    <div class="movie_card">
        <div class="info_section">
            <div class="movie_header">
                <img class="locandina" src="https://movieplayer.net-cdn.it/t/i
                mages/2017/12/20/bright_jpg_191x283_crop_q85.jpg"/>
                <h1>Bright</h1>
                <h4>2017, David Ayer</h4>
                <span class="minutes">117 min</span>
                <p class="type">Action, Crime, Fantasy</p>
            </div>
            <div class="movie_desc">
                <p class="text">
                        ${movie.getAbout()}
                </p>
            </div>
            <div class="movie_social">
                <ul>
                    <li><i class="material-icons">share</i></li>
                    <li><i class="material-icons"></i></li>
                    <li><i class="material-icons">chat_bubble</i></li>
                </ul>
            </div>
        </div>
        <div class="blur_back bright_back"></div>
    </div>
</c:forEach>


<c:if test="${movieList.size() != 0}">
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <c:forEach var="i" begin="1" end="${maxPage}">
                <c:if test="${i == currentPage+4}">
                    <li class="page-item">
                        <a class="page-link">...</a>
                    </li>
                </c:if>
                <c:if test="${(((currentPage-1) == i) || ((i < currentPage+3) && (i > currentPage-3)))
                            || (i > maxPage-2) || (i == 1)}">
                    <li class="page-item <c:if test="${currentPage == i}">active</c:if>">
                        <a class="page-link"
                           href="${pageContext.request.contextPath}/ratingMovies?command=movies-page&p=<c:out value = "${i}"/>&s=${amountOfPage}">
                            <c:out value="${i}"/>
                        </a>
                    </li>
                </c:if>
            </c:forEach>
        </ul>
    </nav>
</c:if>
        </div>
    </div>
</div>

</body>
</html>