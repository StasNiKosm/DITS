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
    <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
        <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
        </symbol>
        <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
            <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
        </symbol>
    </svg>

    <header class="p-3 bg-dark text-white">
        <div class="container">
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                <img src="https://e7.pngegg.com/pngimages/754/474/png-clipart-computer-icons-system-administrator-avatar-computer-network-heroes-thumbnail.png" alt="mdo" width="32" height="32" class="rounded-circle">
                <span class="fs-4 px-2">Admin</span>
                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                    <li><a href="#" class="nav-link px-2 text-secondary">Home</a></li>
                    <li><a href="#" class="nav-link px-2 text-white">Help</a></li>
                    <li><a href="#" class="nav-link px-2 text-white">About</a></li>
                </ul>
                <div class="text-end">
                    <a href="/admin" type="button" class="btn btn-outline-light me-2">Назад</a>
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
<%--                <h1>Test: ${test.name}</h1>--%>
<%--                <h3 class="fst-italic">${test.description}</h3>--%>
<%--                <h2>Topic: ${test.topic.name}</h2>--%>
<%--                <h4 class="fst-italic">${test.topic.description}</h4>--%>
            </div>
        </div>

        <hr class="my-4">
<%--        <h1 class="pb-1 border-bottom text-center ">Выбирайте, добавляйте и редактируйте вопросы</h1>--%>
        <div class="row  g-lg-5 py-5 " >
            <div class="col-lg-4 text-center text-lg-start ">
                <h1 class="display-4 fw-bold lh-1 mb-3">Questions</h1>
                <div class="row ">
<%--                    <p class="col-lg-10 fs-4">Ты можешь создавать новые вопросы, а так же удалять их. Для этого тебе надо нажимать на соответствующие кнопки :)</p>--%>
                    <div class="col-mb-3 ">
                        <c:forEach items="${test.questions}" var="question" varStatus="loop">
                            <button class="btn btn-lg btn-outline-success mb-4 mx-2"  type="button" style="width: 65px; height: 65px; ">${loop.index + 1}</button>
                        </c:forEach>
                        <button id="creatingQuestion" class="btn btn-lg btn-outline-success mb-4 mx-2"  type="button" style="background-color: #e7e7e7; color: black; width: 65px; height: 65px; ">${test.questions.size() + 1}</button>
                    </div>

                    <hr class="my-4">
                    <div class="col-mb-12 d-flex justify-content-evenly">
                        <button class="btn btn-lg btn-warning mb-4 mx-2" style="width: 200px; " type="button">Add question</button>
                    </div>
                </div>
            </div>



<%--            <div class="col-md-10 mx-auto col-lg-8">--%>
<%--                XDCFGVBHNJMK< FDFGTYHUJKIM< BVFGYHUJKM VCFTYUJM VCFTGYHJNM VCFTGYHUJMN VGYHUJM VGYHUJKM--%>
<%--            </div>--%>

                <div class="col-md-10 mx-auto col-lg-8">
                    <form class="p-4 p-md-5 border rounded-3 bg-light">
                        <h1 class="text-center ">Creating question </h1>
                        <div class=" form-floating mb-3">
                            <textarea class="form-control" id="inputQuestionDescription" rows="5">${question.description}</textarea>
                            <label for="inputQuestionDescription" class="form-label">Question description</label>
                        </div>

                        <c:forEach items="${question.literature}" var="literature" varStatus="loop">
                            <h4 class="pb-1 border-bottom text-center ">Literature ${loop.index + 1}</h4>
                            <div class=" form-floating mb-3">
                                <input value="${literature.description}" type="text" class="form-control" id="inputLiteratureDescription" placeholder="name" required="true"/>
                                <label for="inputLiteratureDescription" class="form-label">Literature description</label>
                            </div>
                            <h6 class="pb-1 border-bottom text-center ">Links</h6>
                            <c:forEach items="${literature.links}" var="link">
                                <div class=" form-floating mb-3">
                                    <input value="${link.link}" type="text" class="form-control" id="inputLink" placeholder="name" required="true"/>
                                    <label for="inputLink" class="form-label">Link</label>
                                </div>
<%--                                <button type="button" class="btn btn-outline-danger">Delete</button>--%>
                            </c:forEach>
                            <button type="button" class=" w-100 btn btn-outline-warning mb-3">Add link</button>
                        </c:forEach>
                        <button type="button" class=" w-100 btn btn-outline-warning">Add literature</button>


                        <h4 class="pb-1 border-bottom text-center ">Answers</h4>
                        <div id="parentDivForAnswers">
                            <ol id="ol" class="list-group list-group-numbered mb-3">
                                <c:forEach items="${question.answers}" var="answer">
                                    <li class="list-group-item d-flex justify-content-between align-items-start">
                                        <div class="col-sm-10  ms-2 me-auto">
                                            <textarea class="form-control inputAnswerDescription" rows="2">${answer.description}</textarea>
                                        </div>
                                        <div class="col-sm-1  ">
                                            <div class="row">
                                                <div class="form-check">
                                                    <input ${answer.correct == 1 ? "checked" : ""} style="width: 30px; height: 30px;" class="form-check-input" type="checkbox" value="fgh" id="flexCheckDefault" >
                                                    <label class="form-check-label ms-2 mt-1" for="flexCheckDefault">
                                                        True
                                                    </label>
                                                </div>
                                                <button onclick="deleteLi(this)" type="button" class="deleteLi btn btn-outline-danger btn-sm">Delete</button>
                                            </div>
                                        </div>
                                    </li>
                                </c:forEach>
                                <li>
                                <div class="col-sm-10  ms-2 me-auto"><textarea class="form-control inputAnswerDescription" rows="2"></textarea></div><div class="col-sm-1"><div class="row"><div class="form-check"><input style="width: 30px; height: 30px;" class="form-check-input" type="checkbox" value="fgh" id="flexCheckDefault" ><label class="form-check-label ms-2 mt-1" for="flexCheckDefault">True</label></div><button type="button" class="deleteLi btn btn-outline-danger btn-sm">Delete</button></div></div>
                                </li>
                                <li>
                                <div class="col-sm-10  ms-2 me-auto"><textarea class="form-control inputAnswerDescription" rows="2"></textarea></div><div class="col-sm-1"><div class="row"><div class="form-check"><input style="width: 30px; height: 30px;" class="form-check-input" type="checkbox" value="fgh" id="flexCheckDefault" ><label class="form-check-label ms-2 mt-1" for="flexCheckDefault">True</label></div><button type="button" class="deleteLi btn btn-outline-danger btn-sm">Delete</button></div></div>
                                </li>
                            </ol>
                            <button onclick="createNewLi()" type="button" class=" w-100 btn btn-outline-warning mb-3">Add answer</button>
                        </div>
                        <ul>
                            <li>One</li>
                            <li>Two</li>
                            <li>Three</li>
                        </ul>
                        <script>

                            $(document).ready(function () {
                                $('li').click(function (event) {
                                    $(event.target).closest('li').remove();
                                });
                            });
                            function createNewLi(){
                                let new_li = document.createElement('li');
                                new_li.className = "list-group-item d-flex justify-content-between align-items-start";
                                new_li.innerHTML = '<div class="col-sm-10  ms-2 me-auto"><textarea class="form-control inputAnswerDescription" rows="2"></textarea></div><div class="col-sm-1"><div class="row"><div class="form-check"><input style="width: 30px; height: 30px;" class="form-check-input" type="checkbox" value="fgh" id="flexCheckDefault" ><label class="form-check-label ms-2 mt-1" for="flexCheckDefault">True</label></div><button type="button" class="deleteLi btn btn-outline-danger btn-sm">Delete</button></div></div>';
                                ol.append(new_li); // вставить liLast в конец <ol>
                            }

                            $(document).ready(function () {
                                $('button.deleteLi').click(function (event) {
                                    $(event.target).closest('li').remove();
                                });
                            });

                        </script>
                            <%--                    <div class="row g-3 mb-3">--%>
                            <%--                        <div class="col-2">--%>
                            <%--                            <button class="btn btn-success mb-4 mx-2" type="button">Да</button>--%>
                            <%--                        </div>--%>
                            <%--                        <div class="col-sm-8">--%>
                            <%--                            <textarea class="form-control inputAnswerDescription" rows="2"></textarea>--%>
                            <%--                        </div>--%>
                            <%--                        <div class="col-sm-2">--%>
                            <%--                            <button class="btn btn-danger mb-4 mx-2" type="button">Delete</button>--%>
                            <%--                        </div>--%>

                            <%--                    </div>--%>


                        <button class="w-100 btn btn-lg btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" type="button">Keep</button>

                    </form>
                </div>


        </div>
    </div>



</body>
</html>