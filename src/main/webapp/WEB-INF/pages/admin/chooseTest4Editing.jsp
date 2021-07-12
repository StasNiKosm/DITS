<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Создание теста</title>
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
                    <a href="/admin" type="button" class="btn btn-outline-light me-2">Назад</a>
                    <a href="/logout" type="button" class="btn btn-warning">Выйти</a>
                </div>
            </div>
        </div>
    </header>

    <div class="container col-xl-10 col-xxl-10 py-5">
        <div class="row align-items-center g-lg-5 py-5">
            <div class="col-lg-4 text-center text-lg-start">
                <h1 class="display-4 fw-bold lh-1 mb-3">Editing a test</h1>
                <p class="col-lg-10 fs-4">Выбирай тест, который хочешь отредактировать, изменить или исправить в нем ошибки.</p>

                <div id="successfulEditing" class="alert alert-success" role="alert">
                    <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg>
                    <h4 class="alert-heading">Well done!</h4>
                    <p>Редактирование прошло без ошибок</p>
                    <hr>
                    <p class="mb-0">Всё прошло успешно. Молодец!</p>
                </div>
                <div id="unsuccessfulEditing" class="alert alert-danger" role="alert">
                    <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                    <h4 class="alert-heading">Fail!</h4>
                    <p>Что-то пошло не так :(</p>
                    <hr>
                    <p class="mb-0">Похоже, что это вызвало у тебя затруднение. Выясняем!</p>
                </div>
            </div>


            <div class="col-md-10 mx-auto col-lg-8">
                <div class="p-4 p-md-5 border rounded-3 bg-light" >
                    <div class="form-floating mb-3">

                        <button class="btn btn-outline-primary btn-lg dropdown-toggle " type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" data-bs-display="static" aria-expanded="false">
                            Select a test
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                            <div class="form-floating">
                                <input class="form-control" id="myInput" type="text" placeholder="Search..">
                                <label for="myInput">Topic</label>
                                <table class="table table-bordered table-striped" id="table" style="table-layout: fixed; overflow: scroll;">
                                    <thead>
                                    <tr>
                                        <th width="65">№</th>
                                        <th >Test</th>
                                        <th >Topic</th>
                                    </tr>
                                    </thead>
                                    <tbody id="myTable">
                                    <c:forEach  items="${tests}" var="t" varStatus="loop">
                                        <tr class="write" style="cursor: pointer;">
                                            <td><div style="overflow: auto;">${loop.index + 1}</div></td>
                                            <td hidden><div style="overflow: auto;">${t.testId}</div></td>
                                            <td><div style="overflow: auto;">${t.name}</div></td>
                                            <td hidden><div style="overflow: auto;">${t.description}</div></td>
                                            <td><div style="overflow: auto;">${t.topic.name}</div></td>
                                            <td hidden><div style="overflow: auto;">${t.topic.description}</div></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <script>
                                    $(document).ready(function(){
                                        $("#myInput").on("keyup", function() {
                                            var value = $(this).val().toLowerCase();
                                            $("#myTable tr").filter(function() {
                                                $(this).toggle( $(this).text().toLowerCase().indexOf(value) > -1 );
                                            });
                                        });
                                    });
                                </script>
                            </div>
                        </ul>
                    </div>


                    <div class=" form-floating mb-3">
                        <input disabled type="text" class="form-control" id="inputTestName" placeholder="name" required/>
                        <label for="inputTestName" class="form-label">Test name</label>
                    </div>
                    <div class=" form-floating mb-3">
                        <textarea disabled class="form-control" id="inputTestDescription" rows="5"></textarea>
                        <label for="inputTestDescription" class="form-label">Test description</label>
                    </div>

                    <hr class="my-3">

                    <div class=" form-floating mb-3">
                        <input disabled type="text" class="form-control" id="inputTopicName" placeholder="Topic name">
                        <label for="inputTopicName" class="form-label">Topic name</label>
                    </div>
                    <div class=" form-floating mb-3">
                        <textarea disabled class="form-control" id="inputTopicDescription" rows="5"></textarea>
                        <label for="inputTopicDescription" class="form-label">Topic description</label>
                    </div>

                    <div class=" mb-3">
                        <button id="editTestButton" class="w-100 btn btn-lg btn-outline-warning" data-bs-toggle="modal" data-bs-target="#editTestModal" type="button">Edit</button>
                    </div>

                    <form method="get" action="/admin/editFirstQuestion4Test">
                        <input hidden name="testId" type="text" class="form-control" id="inputTestId" placeholder="testId" required/>
                        <button id="chooseButton" class="w-100 btn btn-lg btn-primary" type="submit">Edit its questions</button>
                    </form>

                    <hr class="my-4">
                    <small class="text-muted">By clicking Create, you create a new test with selected topic.</small>
                </div>

            </div>
        </div>
    </div>


    <!-- Modal -->
    <div class="modal fade" id="editTestModal" tabindex="-1" aria-labelledby="editTestModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editTestModalLabel">Editing</h5>
                    <button id="closeModal" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div id="emptyTestName" class="alert alert-secondary" role="alert">
                        Укажи название теста.
                    </div>
                    <div class=" form-floating mb-3">
                        <input name="testName" type="text" class="form-control" id="inputTestNameModal" placeholder="name" required/>
                        <label for="inputTestNameModal" class="form-label">Test name</label>
                    </div>
                    <div class=" form-floating mb-3">
                        <textarea name="testDescription" class="form-control" id="inputTestDescriptionModal" rows="5"></textarea>
                        <label for="inputTestDescriptionModal" class="form-label">Test description</label>
                    </div>

                    <hr class="my-3">
                    <div id="emptyTopicName" class="alert alert-secondary" role="alert">
                        Укажи название темы.
                    </div>
                    <div class=" form-floating mb-3">
                        <input name="topicName" type="text" class="form-control" id="inputTopicNameModal" placeholder="Topic name">
                        <label for="inputTopicNameModal" class="form-label">Topic name</label>
                    </div>
                    <div class=" form-floating mb-3">
                        <textarea name="topicDescription" class="form-control" id="inputTopicDescriptionModal" rows="5"></textarea>
                        <label for="inputTopicDescriptionModal" class="form-label">Topic description</label>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <input hidden disabled name="testId" type="text" id="inputTestIdModal">
                    <button onclick="edit()" class="w-100 btn btn-lg btn-warning" type="button" >Edit</button>
                </div>
                <div class="modal-footer">
                    <button id="closeConfigQuestionModal" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                </div>
            </div>
        </div>
    </div>


    <script>
        $("#editTestButton").hide();
        $("#emptyTopicName").hide();
        $("#emptyTestName").hide();
        $("#successfulEditing").hide();
        $("#unsuccessfulEditing").hide();

        $("#inputTestNameModal").change(function (){
            if ($("#inputTestNameModal").val().length === 0){
                $("#emptyTestName").show();
            } else {
                $("#emptyTestName").hide();
            }
        });

        $("#inputTopicNameModal").change(function (){
            if ($("#inputTopicNameModal").val().length === 0){
                $("#emptyTopicName").show();
            } else {
                $("#emptyTopicName").hide()
            }
        });

        function edit(){

            $("#closeModal").click();

            if($("#inputTestNameModal").val().length !== 0 && $("#inputTopicNameModal").val().length !== 0) {
                $.ajax({
                    url: "/admin/editTest",
                    type: "POST",
                    dataType: "json",
                    data: {
                        testId: $("#inputTestIdModal").val(),
                        testName: $("#inputTestNameModal").val(),
                        testDescription: $("#inputTestDescriptionModal").val(),
                        topicName: $("#inputTopicNameModal").val(),
                        topicDescription: $("#inputTopicDescriptionModal").val(),
                        "${_csrf.parameterName}" : "${_csrf.token}"
                    },
                })
                    .done(function (data) {
                        console.log(data);
                        if(data.success === true){
                            $("#unsuccessfulEditing").hide();
                            $("#successfulEditing").show();
                            $("#successfulEditing").delay(7000).slideUp(300);

                            $("#inputTestName").val($("#inputTestNameModal").val());
                            $("#inputTestDescription").val($("#inputTestDescriptionModal").val());
                            $("#inputTopicName").val($("#inputTopicNameModal").val());
                            $("#inputTopicDescription").val($("#inputTopicDescriptionModal").val());

                        } else {
                            $("#unsuccessfulEditing").show();
                            $("#unsuccessfulEditing").delay(7000).slideUp(300);
                            $("#successfulEditing").hide();
                        }
                    })
                    .fail(function (xhr, status, error) {
                        alert('Error\n' + xhr.responseText + '\n' + status + '\n' + error);
                    });
            }
        };

        $("#table tr").click(function(){
            var testId = $(this).find('td').eq(1).text();
            var testName = $(this).find('td').eq(2).text();
            var testDescription = $(this).find('td').eq(3).text();
            var topicName = $(this).find('td').eq(4).text();
            var topicDescription = $(this).find('td').eq(5).text();

            $("#editTestButton").show();

            $("#inputTestId").val(testId);
            $("#inputTestName").val(testName);
            $("#inputTestDescription").val(testDescription);
            $("#inputTopicName").val(topicName);
            $("#inputTopicDescription").val(topicDescription);

            $("#inputTestIdModal").val(testId);
            $("#inputTestNameModal").val(testName);
            $("#inputTestDescriptionModal").val(testDescription);
            $("#inputTopicNameModal").val(topicName);
            $("#inputTopicDescriptionModal").val(topicDescription);
        });

    </script>
</body>
</html>