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
            </div>
        </div>

        <hr class="my-4">
        <div class="row  g-lg-5 py-5 " >
            <div class="col-lg-4 text-center text-lg-start ">
                <h1 class="display-4 fw-bold lh-1 mb-3">Questions</h1>
                <div class="row ">
                    <div class="col-mb-3 ">
                        <c:forEach items="${test.questions}" var="question" varStatus="loop">
                            <form method="get" action="/admin/editAnyQuestion4Test">
                                <input value="${test.testId}" name="testId" type="text" class="form-control" placeholder="testId" required/>
                                <input value="${question.questionId}" name="questionId" type="text" class="form-control" placeholder="questionId" required/>
                                <input value="${loop.index + 1}" name="questionNumber" type="text" class="form-control" placeholder="questionNumber" required/>
                                <button class="btn btn-lg btn-${questionNumber == loop.index + 1 ? "" : "outline-"}success mb-4 mx-2"  type="submit" style="width: 65px; height: 65px; ">${loop.index + 1}</button>
                            </form>
                        </c:forEach>
                    </div>

                    <hr class="my-4">
                    <div class="col-mb-12 d-flex justify-content-evenly">
                        <button class="btn btn-lg btn-warning mb-4 mx-2" data-bs-toggle="modal" data-bs-target="#configQuestion" style="width: 200px; " type="button">Add question</button>
                    </div>
                </div>
            </div>


            <div class="col-md-10 mx-auto col-lg-8">
                <form class="p-4 p-md-5 border rounded-3 bg-light">
                    <h1 class="text-center ">Question ${questionNumber}</h1>
                    <div class=" form-floating mb-3">
                        <textarea class="form-control" id="inputQuestionDescription" rows="5">${question.description}</textarea>
                        <label for="inputQuestionDescription" class="form-label">Question description</label>
                        <div id="successfulQuestionDescriptionKeeping" class=" alert alert-success" role="alert">
                            <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg>
                            Well done!
                        </div>
                        <div id="unsuccessfulQuestionDescriptionKeeping" class="alert alert-danger" role="alert">
                            <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                            Error!
                        </div>
                        <button onclick="keepEditingQuestionDescription()" id="keepEditingQuestionDescriptionButton" class="btn btn-outline-warning mt-1 "  type="button" >Keep editing</button>
                        <script>
                            <%--                        EDIT QUESTION               --%>

                            var oldQuestionDescription = $("#inputQuestionDescription").val();
                            var newQuestionDescription;

                            $("#keepEditingQuestionDescriptionButton").hide();
                            $("#successfulQuestionDescriptionKeeping").hide();
                            $("#unsuccessfulQuestionDescriptionKeeping").hide();

                            $().ready(function () {
                                $("#inputQuestionDescription").change(function () {
                                    newQuestionDescription = $("#inputQuestionDescription").val();
                                    if(oldQuestionDescription === newQuestionDescription){
                                        $("#keepEditingQuestionDescriptionButton").hide();
                                    } else {
                                        $("#keepEditingQuestionDescriptionButton").show();
                                    }
                                });
                            });

                            function keepEditingQuestionDescription(){
                                if(newQuestionDescription.length > 0){
                                    $.ajax({
                                        url: "/admin/keepEditionQuestionDescription",
                                        type: "POST",
                                        dataType: "json",
                                        data: {
                                            questionId: ${question.questionId},
                                            questionEditingDescription:  newQuestionDescription,
                                            "${_csrf.parameterName}" : "${_csrf.token}"
                                        },
                                    })
                                        .done(function (data) {
                                            console.log( data);
                                            oldQuestionDescription = $("#inputQuestionDescription").val();
                                            $("#keepEditingQuestionDescriptionButton").hide();
                                            $("#unsuccessfulQuestionDescriptionKeeping").hide();
                                            $("#successfulQuestionDescriptionKeeping").show();
                                            $("#successfulQuestionDescriptionKeeping").delay(3000).slideUp(300);
                                        })
                                        .fail(function (xhr, status, error) {
                                            alert('Error\n' + xhr.responseText + '\n' + status + '\n' + error);
                                        });
                                } else {
                                    $("#unsuccessfulQuestionDescriptionKeeping").show();
                                    $("#unsuccessfulQuestionDescriptionKeeping").delay(3000).slideUp(300);
                                }
                            }
                        </script>
                    </div>


                    <c:forEach items="${question.literature}" var="literature" varStatus="loop">
                        <div>
                            <h4 class="pb-1 border-bottom text-center ">Literature ${loop.index + 1}</h4>
                            <div class="literature form-floating mb-3">
                                <input value="${literature.description}" type="text" class="inputLiteratureDescription form-control" placeholder="name" required/>
                                <label class="form-label">Literature description</label>
                                <div class="successfulLiteratureDescriptionKeeping alert alert-success" role="alert">
                                    <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg>
                                    Well done!
                                </div>
                                <div class="unsuccessfulLiteratureDescriptionKeeping alert alert-danger" role="alert">
                                    <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                                    Error!
                                </div>
                                <button onclick="keepButtonEditingLiterature(this)" class=" keepEditingLiteratureDescriptionButton btn btn-outline-warning mt-1 "  type="button" >Keep editing</button>
                                <button class="deleteLiteratureButton btn btn-outline-danger mt-1" type="button" data-bs-toggle="modal" data-bs-target="#deleteModal">Delete literature ${loop.index + 1}</button>
                                <input hidden value="${literature.literatureId}" type="text" class="inputLiteratureId inputLiteratureId form-control" placeholder="name" required/>
                                <input hidden value="${literature.description}" type="text" class="inputLiteratureDescription2 form-control" placeholder="name" required/>
                            </div>
                            <h6 class="pb-1 border-bottom text-center ">Links</h6>
                            <c:forEach items="${literature.links}" var="link">
                                <div class=" form-floating mb-3">
                                    <input value="${link.link}" type="text" class=" inputLink form-control" placeholder="name" required/>
                                    <label class="form-label">Link</label>
                                    <div class="successfulKeepingLink alert alert-success" role="alert">
                                        <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg>
                                        Well done!
                                    </div>
                                    <div class="unsuccessfulKeepingLink alert alert-danger" role="alert">
                                        <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                                        Error!
                                    </div>
                                    <button onclick="keepButtonEditingLink(this)" class=" keepEditingLinkButton btn btn-outline-warning mt-1 "  type="button" >Keep editing</button>
                                    <c:if test="${literature.links.size() != 1}">
                                        <button class="deleteLinkButton btn btn-outline-danger mt-1" type="button" data-bs-toggle="modal" data-bs-target="#deleteModal">Delete link</button>
                                    </c:if>
                                    <input hidden value="${link.linkid}" type="text" class="inputLinkId form-control" placeholder="name" required/>
                                    <input hidden value="${link.link}" type="text" class="inputLink2 form-control" placeholder="name" required/>
                                </div>
                            </c:forEach>
                            <button type="button" class=" addLinkToLiteratureButton w-100 btn btn-outline-warning mb-3" data-bs-toggle="modal" data-bs-target="#addLinkModal">Add link to literature ${loop.index + 1}</button>
                        </div>
                    </c:forEach>
                    <button type="button" class=" w-100 btn btn-outline-warning" data-bs-toggle="modal" data-bs-target="#addLiteratureModal">Add literature</button>


                    <script>

                        // Add link to literature
                        $(".addLinkToLiteratureButton").on('click', function (event) {
                            $("#literatureId").val($(event.target).parent().children('.literature').children('.inputLiteratureId').val());

                        })


                        var linkId;
                        var newLink;
                        var oldLink;
                        <%--                        DELETE LINK               --%>
                        $('.deleteLinkButton').on('click', function (event){
                            $("#modalBody").text("Ты действительно хочешь удалить эту ссылку ?! ");
                            $("#inputElemId").val($(event.target).parent().children('input.inputLinkId').first().val());
                            $("#modalForm").attr("action","/admin/deleteLink");
                        });


                        <%--                        EDIT LINK               --%>

                        $('.keepEditingLinkButton').hide();
                        $('.successfulKeepingLink').hide();
                        $('.unsuccessfulKeepingLink').hide();

                        $().ready(function () {

                            $(".inputLink").on('click', function (event) {
                                oldLink = $(event.target).val();
                                console.log(oldLink);
                            });

                            $(".inputLink").change(function (event) {
                                newLink = $(event.target).val();
                                linkId = $(event.target).parent().children('input.inputLinkId').first().val();

                                console.log(newLink);
                                console.log(linkId);

                                var keepButtonEditingLink = $(event.target).parent().children('button').first();
                                if(oldLink === newLink){
                                    keepButtonEditingLink.hide();
                                } else {
                                    keepButtonEditingLink.show();
                                }

                            });

                        });

                        function keepButtonEditingLink(button){
                            if(newLink.length > 0){
                                $.ajax({
                                    url: "/admin/keepEditionLink",
                                    type: "POST",
                                    dataType: "json",
                                    data: {
                                        linkId: linkId,
                                        linkEditing:  newLink,
                                        "${_csrf.parameterName}" : "${_csrf.token}"
                                    },
                                })
                                    .done(function (data) {
                                        console.log( data);
                                        oldLink = newLink;
                                        $(button).hide();
                                        $(button).parent().children('div.unsuccessfulKeepingLink').first().hide();
                                        $(button).parent().children('div.successfulKeepingLink').first().show();
                                        $(button).parent().children('div.successfulKeepingLink').first().delay(3000).slideUp(300);

                                    })
                                    .fail(function (xhr, status, error) {
                                        alert('Error\n' + xhr.responseText + '\n' + status + '\n' + error);
                                    });
                            } else {
                                $(button).parent().children('div.unsuccessfulKeepingLink').first().show();
                                $(button).parent().children('div.unsuccessfulKeepingLink').first().delay(3000).slideUp(300);
                            }

                        };


                        var literatureId;
                        var newLiteratureDescription;
                        var oldLiteratureDescription;
                        <%--                        DELETE LITERATURE              --%>
                        $('.deleteLiteratureButton').on('click', function (event){
                            $("#modalBody").text("Ты действительно хочешь удалить эту литературу со всеми ссылками ?! ")
                            $("#inputElemId").val($(event.target).parent().children('input.inputLiteratureId').first().val());
                            $("#modalForm").attr("action","/admin/deleteLiterature");

                        });

<%--                        EDIT LITERATURE               --%>
                        $('.keepEditingLiteratureDescriptionButton').hide();
                        $('.successfulLiteratureDescriptionKeeping').hide();
                        $('.unsuccessfulLiteratureDescriptionKeeping').hide();

                        $().ready(function () {

                            $(".inputLiteratureDescription").on('click', function (event) {
                                oldLiteratureDescription = $(event.target).val();
                                console.log(oldLiteratureDescription);
                            });

                            $(".inputLiteratureDescription").change(function (event) {
                                newLiteratureDescription = $(event.target).val();
                                literatureId = $(event.target).parent().children('input.inputLiteratureId').first().val();

                                console.log(newLiteratureDescription);
                                console.log(literatureId);

                                var keepButtonEditingLiterature = $(event.target).parent().children('button').first();
                                if(oldLiteratureDescription === newLiteratureDescription){
                                    keepButtonEditingLiterature.hide();
                                } else {
                                    keepButtonEditingLiterature.show();
                                }

                            });

                        });

                        function keepButtonEditingLiterature(button){
                            if (newLiteratureDescription.length > 0){
                                $.ajax({
                                    url: "/admin/keepEditionLiteratureDescription",
                                    type: "POST",
                                    dataType: "json",
                                    data: {
                                        literatureId: literatureId,
                                        literatureEditingDescription:  newLiteratureDescription,
                                        "${_csrf.parameterName}" : "${_csrf.token}"
                                    },
                                })
                                    .done(function (data) {
                                        console.log( data);
                                        oldLiteratureDescription = newLiteratureDescription;
                                        $(button).hide();
                                        $(button).parent().children('.unsuccessfulLiteratureDescriptionKeeping').first().hide();
                                        $(button).parent().children('.successfulLiteratureDescriptionKeeping').first().show();
                                        $(button).parent().children('.successfulLiteratureDescriptionKeeping').first().delay(3000).slideUp(300);
                                    })
                                    .fail(function (xhr, status, error) {
                                        alert('Error\n' + xhr.responseText + '\n' + status + '\n' + error);
                                    });
                            } else {
                                $(button).parent().children('div.unsuccessfulLiteratureDescriptionKeeping').first().show();
                                $(button).parent().children('.successfulLiteratureDescriptionKeeping').first().hide();
                                $(button).parent().children('div.unsuccessfulLiteratureDescriptionKeeping').first().delay(3000).slideUp(300);
                            }
                        };

                    </script>

                    <h4 class="pb-1 border-bottom text-center mt-4">Answers</h4>
                    <div id="parentDivForAnswers">
                        <ol id="answers" class=" list-group list-group-numbered mb-3">
                            <c:forEach items="${question.answers}" var="answer">
                                <div>
                                <li class=" li list-group-item d-flex justify-content-between align-items-start">
                                    <div class="col-sm-9 ms-2 me-auto">
                                        <textarea class="inputAnswerDescription form-control" rows="2">${answer.description}</textarea>
                                    </div>
                                    <div class="col-sm-2  ">
                                        <div class="row">
                                            <div class="form-check">
                                                <input ${answer.correct == 1 ? "checked" : ""} style="width: 30px; height: 30px;" class="answerCorrect form-check-input" type="checkbox" >
                                                <label class="form-check-label ms-2 mt-1">
                                                    True
                                                </label>
                                            </div>
                                            <input hidden value="${answer.answerid}" type="text" class="inputAnswerId form-control" placeholder="name" required/>
                                            <input hidden value="${answer.description}" type="text" class="inputAnswer2 form-control" placeholder="name" required/>
                                            <button type="button" class=" deleteAnswerButton btn btn-outline-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteModal">Delete answer</button>
                                            <button onclick="keepButtonEditingAnswer(this)" class=" keepEditingAnswerButton btn btn-sm btn-outline-warning "  type="button" >Keep editing</button>
                                        </div>
                                    </div>
                                </li>
                                <div class="successfulKeepingAnswer alert alert-success" role="alert">
                                    <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg>
                                    Well done!
                                </div>
                                <div class="unsuccessfulKeepingAnswer alert alert-danger" role="alert">
                                    <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                                    Error!
                                </div>
                                </div>
                            </c:forEach>
                            <button type="button" class="btn btn-outline-warning" data-bs-toggle="modal" data-bs-target="#addAnswerModal">Add answer</button>
                        </ol>
                    </div>

                    <script>
                        var answerId;
                        var newAnswerDescription = "";
                        var oldAnswerDescription = "";
                        var newCorrect = false;
                        var oldCorrect = false;

                        <%--                        DELETE ANSWER               --%>
                        $('.deleteAnswerButton').on('click', function (event){
                            $("#modalBody").text("Ты действительно хочешь удалить этот ответ ?! ");
                            $("#inputElemId").val($(event.target).parent().children('input.inputAnswerId').val());
                            $("#modalForm").attr("action","/admin/deleteAnswer");

                        });

                        <%--                        EDIT ANSWER               --%>
                        $('.successfulKeepingAnswer').hide();
                        $('.unsuccessfulKeepingAnswer').hide();
                        $('.keepEditingAnswerButton').hide();

                        $(".answerCorrect").one("click", function() {
                            oldCorrect = !this.checked;
                        });

                        $(".answerCorrect").change(function (event) {
                            newCorrect = this.checked;
                            getEditingButton($(event.target).parent().parent().children('button.keepEditingAnswerButton'));
                            console.log("old"+oldCorrect);
                            console.log("new"+newCorrect);
                            console.log("old"+oldAnswerDescription);
                            console.log("new"+newAnswerDescription);
                        });

                        function getEditingButton(keepButtonEditingAnswer){
                            if(oldAnswerDescription === newAnswerDescription && newCorrect === oldCorrect){
                                keepButtonEditingAnswer.hide();
                            } else {
                                keepButtonEditingAnswer.show();
                            }
                        }

                        $(".inputAnswerDescription").on('click', function (event) {
                            oldAnswerDescription = $(event.target).parent().parent().children().last().children().children('input.inputAnswer2').val();
                            newAnswerDescription = oldAnswerDescription;
                            console.log(oldAnswerDescription);
                        })

                        $(".inputAnswerDescription").change(function (event) {
                            newAnswerDescription = $(event.target).val();

                            $(event.target).parent().parent().children().last().children().children('button.keepEditingAnswerButton').show();

                            console.log(newAnswerDescription);

                            getEditingButton($(event.target).parent().parent().children().last().children().children('button.keepEditingAnswerButton'));

                        });

                        function checkNumArray(my_arr){
                            for(var i=0;i<my_arr.length;i++){
                                if(my_arr[i] === 1)
                                    return true;
                            }
                            return false;
                        }

                        function keepButtonEditingAnswer(button){
                            var corrects = [];
                            $("#answers").children().children('.li').each(function() { corrects.push($(this).children('.col-sm-2').first().children().children().children().first().is(':checked') ? 1 : 0) });

                            newAnswerDescription = $(button).parent().parent().parent().children().children().val();
                            newCorrect = $(button).parent().children().children().first().prop('checked');

                            if(newAnswerDescription.length > 0 && checkNumArray(corrects)){
                                $.ajax({
                                    url: "/admin/keepEditionAnswer",
                                    type: "POST",
                                    dataType: "json",
                                    data: {
                                        answerId: $(button).parent().children('input.inputAnswerId').val(),
                                        answerEditingDescription:  newAnswerDescription,
                                        answerEditingCorrect:  newCorrect,
                                        "${_csrf.parameterName}" : "${_csrf.token}"
                                    },
                                })
                                    .done(function (data) {
                                        console.log( data);
                                        oldAnswerDescription = newAnswerDescription;
                                        oldCorrect = newCorrect;
                                        $(button).hide();
                                        $(button).parent().parent().parent().parent().children('.unsuccessfulKeepingAnswer').hide();
                                        $(button).parent().parent().parent().parent().children('.successfulKeepingAnswer').show();
                                        $(button).parent().parent().parent().parent().children('.successfulKeepingAnswer').delay(3000).slideUp(300);

                                    })
                                    .fail(function (xhr, status, error) {
                                        alert('Error\n' + xhr.responseText + '\n' + status + '\n' + error);
                                    });
                            } else {
                                $(button).parent().parent().parent().parent().children('div.unsuccessfulKeepingAnswer').show();
                                $(button).parent().parent().parent().parent().children('div.unsuccessfulKeepingAnswer').delay(3000).slideUp(300);
                                newAnswerDescription = "";
                            }

                        };


                        // DELETE QUESTION
                        function setElemIdAsDeletingQuestion(){
                            $("#inputElemId").val(${question.questionId});
                            $("#modalBody").text("Уверен?! Действительно хочешь удалить этот вопрос: '${question.description}' ?! ");
                            $("#modalForm").attr("action","/admin/deleteQuestion");
                        }

                    </script>

                    <button onclick="setElemIdAsDeletingQuestion()" id="deleteQuestion" class="w-100 btn btn-lg btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal" type="button">Delete question</button>

                </form>
            </div>

            <!-- Modal -->
            <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="deleteModalLabel">Deleting</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div id="modalBody" class="modal-body">

                        </div>
                        <div class="modal-footer">
                            <form id="modalForm" method="post" >
                                <input name="id" type="text" class="form-control" id="inputElemId" placeholder="id" required/>
                                <input name="testId" value="${test.testId}" type="number" class="form-control" placeholder="id" required/>
                                <input name="questionId" value="${question.questionId}" type="number" class="form-control" placeholder="id" required/>
                                <input name="questionNumber" value="${questionNumber}" type="number" class="form-control" placeholder="id" required/>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </form>
                            <button id="closeCreatingModal" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal add literature -->
            <div class="modal fade" id="addLiteratureModal" tabindex="-1" aria-labelledby="addLiteratureModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addLiteratureModalLabel">Addition literature</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form id="modalFormAddLiterature" method="post" action="/admin/addLiterature" >
                            <div id="modalBodyAddLiterature" class="modal-body">
                                <div class=" form-floating mb-3">
                                    <input name="literatureDescription" type="text" class="form-control" id="literatureDescription" placeholder="Literature description" required/>
                                    <label for="literatureDescription" class="form-label">Literature description</label>
                                </div>
                                <div class=" form-floating mb-3">
                                    <input name="link" type="text" class="form-control" id="link" placeholder="link" required/>
                                    <label for="linkToLiterature" class="form-label">Link</label>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <input name="testId" value="${test.testId}" type="number" class="form-control" placeholder="id" required/>
                                <input name="questionId" value="${question.questionId}" type="number" class="form-control" placeholder="id" required/>
                                <input name="questionNumber" value="${questionNumber}" type="number" class="form-control" placeholder="id" required/>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                <button type="submit" class="btn btn-warning">Add</button>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Modal add link to literature -->
            <div class="modal fade" id="addLinkModal" tabindex="-1" aria-labelledby="addLinkModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addLinkModalLabel">Addition link to literature</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form id="modalFormAddLink" method="post" action="/admin/addLinkToLiterature">
                            <div id="modalBodyAddLink" class="modal-body">
                                <div class=" form-floating mb-3">
                                    <input name="link" type="text" class="form-control" id="linkToLiterature" placeholder="link" required/>
                                    <label for="linkToLiterature" class="form-label">Link</label>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <input name="literatureId" type="text" class="form-control" id="literatureId" placeholder="id" required/>
                                <input name="testId" value="${test.testId}" type="number" class="form-control" placeholder="id" required/>
                                <input name="questionId" value="${question.questionId}" type="number" class="form-control" placeholder="id" required/>
                                <input name="questionNumber" value="${questionNumber}" type="number" class="form-control" placeholder="id" required/>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                <button type="submit" class="btn btn-warning">Add</button>

                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Modal add answer -->
            <div class="modal fade" id="addAnswerModal" tabindex="-1" aria-labelledby="addAnswerModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addAnswerModalLabel">Addition answer</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form id="modalFormAddAnswer" method="post" action="/admin/addAnswer">
                            <div id="modalBodyAddAnswer" class="modal-body">
                                <div class=" form-floating mb-3">
                                    <input name="answerDescription" type="text" class="form-control" id="answerDescription" placeholder="link" required/>
                                    <label for="linkToLiterature" class="form-label">Answer</label>
                                </div>
                                <div class=" mb-3">
                                    <input name="correct" id="answerCorrect" style="width: 30px; height: 30px;" class=" form-check-input" type="checkbox" >
                                    <label for="answerCorrect" class="form-check-label mx-2 mt-2 ">True</label>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <input name="testId" value="${test.testId}" type="number" class="form-control" placeholder="id" required/>
                                <input name="questionId" value="${question.questionId}" type="number" class="form-control" placeholder="id" required/>
                                <input name="questionNumber" value="${questionNumber}" type="number" class="form-control" placeholder="id" required/>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                <button type="submit" class="btn btn-warning">Add</button>

                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Modal add question-->
            <div class="modal fade" id="configQuestion" tabindex="-1" aria-labelledby="configQuestionLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="configQuestionLabel">Переходим к созданию ${test.questions.size() + 1} вопроса</h5>
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
                                <input id="questionNumber" name="questionNumber" value="${test.questions.size() + 1}">
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



        </div>
    </div>



</body>
</html>