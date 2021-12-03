<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <head>
        <c:import url="/jsp/partspages/head.jsp"/>
        <title>
            f1
        </title>
    </head>

<body>
<c:import url="/jsp/partspages/navbar.jsp"/>

<div class="row flex-column">

    <!--<div class="card-body media align-items-center">

        <img src="${pageContext.request.contextPath}/images/photo/${sessionScope.photoMovie}"
             class="rounded-circle" width="100px" alt="" class="d-block ui-w-80">
        <div class="media-body ml-4">
            <input id="ajaxfile" name="image" type="file" class="d-none"
                   accept=".png, .jpg, .jpeg .gif" size="10" onchange="uploadFile()" required />
            <button type="submit" class="btnUpload btn btn-primary">
                Выбрать фото
            </button>
            <script src="/js/upload-photo.js"></script>
            <div class="text-light small mt-1">Allowed JPG, GIF or PNG.</div>
        </div>
    </div>-->
    <form name="signupForm" method="POST" action="${pageContext.request.contextPath}/ratingMovies?command=createMovie"
          class="flex-box col-md-6">
        <h1>Добавление фильма</h1>

        <div class="mb-3">
            <span class="form-label">Введите ссылку на постер</span>
            <input type="text" class="form-control" name="img" minlength="4" maxlength="32"
                   required>
        </div>


        <div class="mb-3">
            <span class="form-label">Введите ссылку на задний фон фильма</span>
            <input type="text" class="form-control" name="img" minlength="4" maxlength="32"
                   required>
        </div>


        <div class="mb-3">
            <span class="form-label">Название фильма</span>
            <input type="text" class="form-control" name="name" minlength="4" maxlength="32"
                   required>
        </div>



        <div class="mb-3">
            <span class="form-label">О фильме</span>
            <input type="text" class="form-control" name="about" minlength="8" maxlength="256"
                   required>
        </div>



        <div>
            <span class="form-label">Введите дату релиза</span>
            <input type="date" id="start" name="data"
                   value="2018-07-22"
                   min="1941-01-01" max="2025-12-31" required>
        </div>
        <div class="mb-3">
            <span class="form-label">Введите количество лайков</span>
            <input type="number" name="like" minlength="1" maxlength="3" class="form-control" required>
        </div>
        <div class="mb-3">
            <span class="form-label">Введите количество дислайков</span>
            <input type="number" name="dislike" minlength="1" maxlength="3" class="form-control" required>
        </div>
        <div>
            <span class="form-label">Выберите жанр фильма</span>

            <select name="genre">
                <option value="1">dddr</option>
                <option value="2">Триллер</option>
                <option>Не выбирать</option>
                <option>Не выбирать</option>
            </select>

        </div>




        <button type="submit" class="btn btn-primary">Закидывай фильм брат</button>
    </form>
</div>

</body>
</html>
