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

    <style type="text/css">
        .nobull {
            list-style-type: none;
            padding: 0;
        }
    </style>

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
                                <input id="questionNumber" name="questionNumber" value="${questionNumber}">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                <label for="testId">test id</label><input value="${test.testId}" name="testId" type="text" id="testId">
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

            <div id="successfulCreating" class="alert alert-success" role="alert">
                <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg>
                <h4 class="alert-heading">Well done!</h4>
                <p>Aww yeah, you successfully read this important alert message. This example text is going to run a bit longer so that you can see how spacing within an alert works with this kind of content.</p>
                <hr>
                <p class="mb-0">Whenever you need to, be sure to use margin utilities to keep things nice and tidy.</p>
            </div>
            <div id="unsuccessfulCreating" class="alert alert-danger" role="alert">
                <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                <h4 class="alert-heading">Error!</h4>
                <p>Aww yeah, you successfully read this important alert message. This example text is going to run a bit longer so that you can see how spacing within an alert works with this kind of content.</p>
                <hr>
                <p class="mb-0">Whenever you need to, be sure to use margin utilities to keep things nice and tidy.</p>
            </div>

            <div id="configQuestionForm" class="col-md-10 mx-auto col-lg-8">
                <form class="p-4 p-md-5 border rounded-3 bg-light">
                    <h1 class="text-center ">Creating question ${questionNumber}</h1>
                    <div class=" form-floating mb-3">
                        <textarea class="form-control" id="inputQuestionDescription" rows="5"></textarea>
                        <label for="inputQuestionDescription" class="form-label">Question description</label>
                    </div>

                    <ul id="literature" class="nobull">
                        <c:forEach var="i" begin="1" end="${literatureNumber}" step="1">
                            <li class="li">
                                <h4 class="pb-1 border-bottom text-center ">Literature ${i}</h4>
                                <div class="literature_div form-floating mb-3">
                                    <input type="text" class="form-control" id="inputLiteratureDescription" placeholder="name" required/>
                                    <label for="inputLiteratureDescription" class="form-label">Literature description</label>
                                </div>
                                <h6 class="pb-1 border-bottom text-center ">Links</h6>
                                <ul class="nobull">
                                    <c:forEach var="j" begin="1" end="${linksNumber.get(i - 1)}" step="1">
                                        <li class="li">
                                            <div class=" form-floating mb-3">
                                                <input type="text" class="form-control" id="inputLink" placeholder="name" required/>
                                                <label for="inputLink" class="form-label">Link</label>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:forEach>
                    </ul>


                    <h4 class="pb-1 border-bottom text-center ">Answers</h4>
                    <div id="parentDivForAnswers">
                        <ol id="answers" class="list-group list-group-numbered mb-3">
                            <c:forEach  var="i" begin="1" end="${answersNumber}" step="1">
                                <li class="list-group-item d-flex justify-content-between align-items-start">
                                    <div class="col-sm-9  ms-2 me-auto">
                                        <textarea class="form-control inputAnswerDescription"></textarea>
                                    </div>
                                    <div class="form-check">
                                        <input style="width: 30px; height: 30px;" class="form-check-input" type="checkbox" value="fgh" id="flexCheckDefault" >
                                        <label class="form-check-label ms-2 mt-1" for="flexCheckDefault">
                                            True
                                        </label>
                                    </div>
                                </li>
                            </c:forEach>
                        </ol>
                    </div>
                    <div id="notConfigured" class="alert alert-secondary" role="alert">
                        ПроверЬ, всё ли заполнил! И помни, должен быть хоть один ответ верен :)
                    </div>
                    <button onclick="keep()" class="w-100 btn btn-lg btn-primary"  type="button">Keep</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                </form>
            </div>
            <script>
                $("#configQuestionForm").show();
                $("#successfulCreating").hide();
                $("#unsuccessfulCreating").hide();
                $("#notConfigured").hide();

                function keep(){

                    var answers = [];
                    var corrects = [];
                    var literature = [];
                    var links = [];
                    $("#answers").children().each(function() { answers.push($(this).children().first().children().val()) });
                    $("#literature").children().each(function() { literature.push($(this).children('div').children().val()) });
                    $("#literature").children().each(function() { $(this).children('ul').children().each(function() { links.push( $(this).children().children().val() )}) });
                    $("#answers").children().each(function() { corrects.push($(this).children().last().first().children().is(':checked') ? 1 : 0) });
                    console.log(answers);
                    console.log(corrects);
                    console.log(literature);
                    console.log(links);

                    //Если какой-либо из одного элемента пуст, он вернет false
                    function checkArray(my_arr){
                        for(var i=0;i<my_arr.length;i++){
                            if(my_arr[i] === "")
                                return false;
                        }
                        return true;
                    }
                    //Если массиве только 0, он вернет false
                    function checkNumArray(my_arr){
                        for(var i=0;i<my_arr.length;i++){
                            if(my_arr[i] === 1)
                                return true;
                        }
                        return false;
                    }

                    if(
                        $("#questionNumber").val().length !== 0 &&
                        checkArray(answers) &&
                        checkArray(literature) &&
                        checkArray(links) &&
                        checkNumArray(corrects)
                    ) {
                        $.ajax({
                            url: "/admin/addQuestion",
                            type: "POST",
                            dataType: "json",
                            data: {
                                testId: ${test.testId},
                                questionNumber: $("#questionNumber").val(),
                                description: $("#inputQuestionDescription").val(),
                                answers: answers,
                                corrects: corrects,
                                literature: literature,
                                links: links,
                                linkCipher: ${linksNumber},
                                "${_csrf.parameterName}" : "${_csrf.token}"
                            },
                        })
                            .done(function (data) {
                                console.log(data);

                                $("#questionNumber").val(parseInt($("#questionNumber").val()) + 1);
                                $("#inputQuestionDescription").val("");
                                $("#answers").children().each(function() { answers.push($(this).children().first().children().val("")) });
                                $("#literature").children().each(function() { literature.push($(this).children('div').children().val("")) });
                                $("#literature").children().each(function() { $(this).children('ul').children().each(function() { links.push( $(this).children().children().val("") )}) });

                                $("#configQuestionForm").hide();
                                $("#successfulCreating").show();
                                $("#unsuccessfulCreating").hide();
                                $("#notConfigured").hide();

                            })
                            .fail(function (xhr, status, error) {
                                alert('Error\n' + xhr.responseText + '\n' + status + '\n' + error);
                                $("#successfulCreating").hide();
                                $("#unsuccessfulCreating").show();
                                $("#notConfigured").show();
                            });
                    } else {
                        $("#notConfigured").show();
                    }

                }

            </script>


        </div>
    </div>



</body>
</html>