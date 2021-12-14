<head>

    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <c:import url="/jsp/partspages/head.jsp"/>
    <%@ taglib prefix="sc" uri="custom-tags" %>
    <link rel="stylesheet" type="text/css" href="../../css/reviews.css">
</head>
<body>

<c:import url="/jsp/partspages/navbar.jsp"/>

<section>
    <div class="container">
        <div class="row">

            <c:forEach items="${commentUserList}" var="entry">
                <div class="col-sm-5 col-md-6 col-12 pb-4">
                    <a  style="text-decoration: none" href="${pageContext.request.contextPath}
                /ratingMovies?command=profile-page&id=${entry.value.getId()}">

                    <div class="comment mt-4 text-justify float-left">
                        <img src="${pageContext.request.contextPath}
                        /images/photo/${entry.value.getProfilePicture()}" alt=""
                             class="rounded-circle" width="40"
                             height="40">
                        <h4>${entry.value.getName()}</h4> <span>${entry.key.getCreateTimeComment()}</span> <br>
                        <p>${entry.key.getMessage()}</p>
                    </div>
                    </a>

                </div>
            </c:forEach>

            <c:if test="${commentUserList.size() != 0}">
                <p>auf</p>
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
                                       href="${pageContext.request.contextPath}/ratingMovies?command=reviews-page&movieId=${id}&id=${sessionScope.get("userId")}&p=<c:out value = "${i}"/>&s=${amountOfPage}">
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
</section>

<sc:access role="USER">

<div class="col-lg-4 col-md-5 col-sm-4 offset-md-1 offset-sm-1 col-12 mt-4">
    <form id="algin-form" method="POST"
          action="${pageContext.request.contextPath}/ratingMovies?command=leaveComment&movieId=${id}&id=${sessionScope.get("userId")}">
        <div class="form-group">
            <h4>Leave a comment</h4>
            <textarea name="leaveComment" id="" msg cols="30" rows="5"
                      class="form-control"
                      style="background-color: black;" required>

            </textarea>
        </div>

        <div class="form-inline"><input type="checkbox" name="check" id="checkbx" class="mr-1"> Subscribe me
            to the newlettter </div>
        <button type="submit" class="form-button button-l margin-b">Sign In</button>
    </form>
</div>
</sc:access>

<sc:access role="ADMIN">

    <div class="col-lg-4 col-md-5 col-sm-4 offset-md-1 offset-sm-1 col-12 mt-4">
        <form id="algin-form" method="POST"
              action="${pageContext.request.contextPath}/ratingMovies?command=leaveComment&movieId=${id}&id=${sessionScope.get("userId")}">
            <div class="form-group">
                <h4>Leave a comment</h4>
                <textarea name="leaveComment" msg cols="30" rows="5"
                          class="form-control"
                          style="background-color: black;" required>
            </textarea>
            </div>

            <div class="form-inline"><input type="checkbox" name="check" id="checkbx" class="mr-1"> Subscribe me
                to the newlettter </div>
            <button type="submit" class="form-button button-l margin-b">Sign In</button>
        </form>
    </div>
</sc:access>




</body>