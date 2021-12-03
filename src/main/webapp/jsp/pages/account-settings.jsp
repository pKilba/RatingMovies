<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<html lang=">

<head>
    <c:import url="/jsp/partspages/head.jsp" />
    <title>
    </title>
    <link rel=" stylesheet" href="/css/account-settings.css">
</head>

<body>
<c:import url="/jsp/partspages/navbar.jsp"/>
<div class="container py-3 light-style flex-grow-1 container-p-y">

    <h4 class="font-weight-bold py-3 mb-4">
        Настройка аккаунта
    </h4>

    <div class="card overflow-hidden">
        <div class="row no-gutters row-bordered row-border-light">

            <div class="col-md-3 pt-0" id="choiceSection">
                <div class="list-group list-group-flush account-settings-links">
                    <a class="list-group-item list-group-item-action active"
                       onclick="changeBtnSubmitGeneral()"
                       data-toggle="list" href="#account-general">
                        Основные настройки
                    </a>
                    <a class="list-group-item list-group-item-action" onclick="changeBtnSubmitPas()" data-toggle="list"
                       href="#account-change-password">
                        Изменить пароль
                    </a>
                    <a class="list-group-item list-group-item-action" data-toggle="list" id="sectionNotifications"
                       href="#account-notifications">Notifications</a>
                </div>
            </div>

            <div class="col-md-9">
                <div class="tab-content">
                    <div class="tab-pane fade active show" id="account-general">

                        <div class="card-body media align-items-center">
                            <img src="${pageContext.request.contextPath}/images/photo/${sessionScope.photo}" alt="mdo"
                                 width="40" height="40" class="rounded-circle">


                            <div class="media-body ml-4">
                                <input id="ajaxfile" type="file" class="d-none"
                                       accept=".png, .jpg, .jpeg .gif"
                                       size="10" onchange="uploadFile()"/>
                                <button type="submit" class="btnUpload btn btn-primary">
                                    Изменить фото
                                </button>
                                <script src="/js/upload-photo.js"></script>
                                <div class="text-light small mt-1">Allowed JPG, GIF or PNG.</div>
                            </div>
                        </div>
                        <hr class="border-light m-0">
                        <div class="card-body">
                            <form id="changeGeneralForm" name="changeGeneralDataUserForm" method="POST"
                                  action="${pageContext.request.contextPath}/ratingMovies?command=change-general-info"
                                  class="">
                                <div class="form-group">
                                    <label class="form-label">
                                        Изменить имя
                                    </label>
                                    <input type="text" name="name" minlength="4" maxlength="32" class="form-control"
                                           value="${user.getName()}" required>
                                </div>
                                <div class="form-group">
                                    <label class="form-label">
                                        Изменить телефон(ичпр на имейл)
                                    </label>
                                    <input type="email"  name="email" minlength="4"
                                           maxlength="16" class="form-control" value="${user.getEmail()}"
                                           required>
                                </div>
                                <div class="form-group">
                                    <label class="form-label ">
                                        Инфа о себе
                                    </label>
                                    <div class="text-light small float-right mt-1">Изменитть биографию</div>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="tab-pane fade" id="account-change-password">
                        <div class="card-body pb-2">
                            <form id="changePasForm" name="changePasswordUserForm" method="POST"
                                  action="${pageContext.request.contextPath}/poker?command=change-pas">
                                <div class="form-group">
                                    <label class="form-label">
                                        Изменить текущий пароль
                                    </label>
                                    <input type="password" id="currentPass" name="currPas" minlength="8" maxlength="32"
                                           class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label class="form-label">
                                        Новый пароль
                                    </label>
                                    <input type="password" name="newPas" minlength="8" maxlength="32"
                                           class="form-control password" required>
                                </div>
                                <div class="form-group">
                                    <label class="form-label">
                                        Повторите новый пароль

                                    </label>
                                    <input type="password" class="form-control password" minlength="8" maxlength="32"
                                           required>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="tab-pane fade  bg-secondary" id="account-notifications">
                        <h2 class="display-5">in developing</h2>
                        <div class="card-body pb-2">
                            <h6 class="mb-4">Activity</h6>
                            <div class="form-group">
                                <label class="switcher">
                                    <input type="checkbox" class="switcher-input" checked="">
                                    <span class="switcher-indicator">
                                            <span class="switcher-yes"></span>
                                            <span class="switcher-no"></span>
                                        </span>
                                    <span class="switcher-label">Email me when someone comments on my article</span>
                                </label>
                            </div>
                            <div class="form-group">
                                <label class="switcher">
                                    <input type="checkbox" class="switcher-input" checked="">
                                    <span class="switcher-indicator">
                                            <span class="switcher-yes"></span>
                                            <span class="switcher-no"></span>
                                        </span>
                                    <span class="switcher-label">Email me when someone answers on my forum
                                            thread</span>
                                </label>
                            </div>
                            <div class="form-group">
                                <label class="switcher">
                                    <input type="checkbox" class="switcher-input">
                                    <span class="switcher-indicator">
                                            <span class="switcher-yes"></span>
                                            <span class="switcher-no"></span>
                                        </span>
                                    <span class="switcher-label">Email me when someone follows me</span>
                                </label>
                            </div>
                        </div>
                        <hr class="border-light m-0">
                        <div class="card-body pb-2">

                            <h6 class="mb-4">Application</h6>

                            <div class="form-group">
                                <label class="switcher">
                                    <input type="checkbox" class="switcher-input" checked="">
                                    <span class="switcher-indicator">
                                            <span class="switcher-yes"></span>
                                            <span class="switcher-no"></span>
                                        </span>
                                    <span class="switcher-label">News and announcements</span>
                                </label>
                            </div>
                            <div class="form-group">
                                <label class="switcher">
                                    <input type="checkbox" class="switcher-input">
                                    <span class="switcher-indicator">
                                            <span class="switcher-yes"></span>
                                            <span class="switcher-no"></span>
                                        </span>
                                    <span class="switcher-label">Weekly product updates</span>
                                </label>
                            </div>
                            <div class="form-group">
                                <label class="switcher">
                                    <input type="checkbox" class="switcher-input" checked="">
                                    <span class="switcher-indicator">
                                            <span class="switcher-yes"></span>
                                            <span class="switcher-no"></span>
                                        </span>
                                    <span class="switcher-label">Weekly blog digest</span>
                                </label>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>


    <div class="text-right mt-3">
        <button type="submit" form="changeGeneralForm" class="btn btn-primary" id="btnSubmit">
            Сохранить изменения
        </button>
        <a class="btn btn-default border border-secondary"
           href="${pageContext.request.contextPath}/ratingMovies?command=profile-page&id=${sessionScope.get("userId")}">
            Закрыть изменения
        </a>
    </div>

</div>
<script src="/js/account-settings.js"></script>
<script src="/js/checker-repeat-pas.js"></script>
</body>

</html>