<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Редактирование пользователя</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


</head>
<body>
    <header class="p-3 bg-dark text-white">
        <div class="container">
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                <img src="https://e7.pngegg.com/pngimages/754/474/png-clipart-computer-icons-system-administrator-avatar-computer-network-heroes-thumbnail.png" alt="mdo" width="32" height="32" class="rounded-circle">
                <span class="fs-4 px-2">Admin</span>
                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                    <li><a href="/admin" class="nav-link px-2 text-secondary">Home</a></li>
                    <li><a href="#" class="nav-link px-2 text-white">Help</a></li>
                    <li><a href="#" class="nav-link px-2 text-white">About</a></li>
                </ul>
                <div class="text-end">
                    <a href="/admin/choose_test_for_edition" type="button" class="btn btn-outline-light me-2">Назад</a>
                    <a href="/logout" type="button" class="btn btn-warning">Выйти</a>
                </div>
            </div>
        </div>
    </header>

    <div class="container col-xl-10 col-xxl-10 py-5">

        <div class="row mb-5">
            <div class="col-md-3 col-sm-6">
                <img src="http://devincubator.by/images/human1.png" alt="mdo" width="200" height="200" >
            </div>
            <div class="col-md-9 col-sm-6 ">
                <dl class="row">
                    <dt class="col-sm-2"><h1>Test:</h1></dt>
                    <dd class="col-sm-10">
                        <h1>${test.name}</h1>
                        <p class="fw-light fst-italic fs-4">${test.description}</p>
                    </dd>
                    <dt class="col-sm-2"><h2>Topic:</h2></dt>
                    <dd class="col-sm-10">
                        <h3>${test.topic.name}</h3>
                        <p class="fw-light fst-italic">${test.topic.description}</p>
                    </dd>
                </dl>
            </div>
        </div>

        <hr class="my-4">
        <%--        <h1 class="pb-1 border-bottom text-center ">Выбирайте, добавляйте и редактируйте вопросы</h1>--%>
        <div class="row  g-lg-5 py-5 " >
            <div class=" text-center ">
                <h1 class="display-4 fw-bold lh-1 mb-3">Questions</h1>
                <p class=" fs-4">Тут еще пусто :(</p>
                <p>Исправь это! Добавь вопрос!</p>

                <div class="col-mb-12 d-flex justify-content-evenly">
                    <button class="btn btn-lg btn-warning mb-4 mx-2" style="width: 200px; " data-bs-toggle="modal" data-bs-target="#configQuestion" type="button">Add question</button>
                </div>
            </div>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="configQuestion" tabindex="-1" aria-labelledby="configQuestionLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="configQuestionLabel">Переходим к созданию первого вопроса</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <p>Укажите количество ответов, литературы и количество ссылок на нее</p>
                        <form method="post" action="/admin/filling_out_the_test">
                            <div class=" form-floating mb-3">
                                <input name="answersNumber" type="number" class="form-control" id="inputAnswersCount" placeholder="Answers number" required>
                                <label for="inputAnswersCount" class="form-label">Answers number</label>
                            </div>
                            <div class=" form-floating mb-3">
                                <input name="literatureNumber" type="number" class="form-control" id="inputLiteratureCount" placeholder="Literature number" required/>
                                <label for="inputLiteratureCount" class="form-label">Literature number</label>
                            </div>
                            <div class=" form-floating mb-3">
                                <input name="linksNumber" type="text" class="form-control" id="inputLinksCount" placeholder="Links number" required/>
                                <label for="inputLinksCount" class="form-label">Links number</label>
                                <small id="passwordHelpBlock" class="form-text text-muted">
                                    При нескольких литератур, укажите количесво ссылок под каждую из них разделяя запятыми.
                                </small>
                            </div>
                            <input hidden id="questionNumber" name="questionNumber" value="1">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <input hidden value="${test.testId}" name="testId" type="text" id="testId">
                            <button class="w-100 btn btn-lg btn-success" type="submit" >Apply</button>

                        </form>
                    </div>
                    <div class="modal-footer">
                        <%--                                <button onclick="create()" type="button" class="btn btn-primary">Create</button>--%>
                        <button id="closeCongigQuestionModal" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    </div>
                </div>
            </div>
        </div>


</body>
</html>