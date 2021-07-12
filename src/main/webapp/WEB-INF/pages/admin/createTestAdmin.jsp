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
        <div class="row align-items-center g-lg-5 py-5">
            <div class="col-lg-4 text-center text-lg-start">
                <h1 class="display-4 fw-bold lh-1 mb-3">Creating a test</h1>
                <p class="col-lg-10 fs-4">Создавай новые интересные тесты. выбтрай для них тему или создай ее на месте.</p>

                <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
                    <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
                        <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                    </symbol>
                    <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
                        <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                    </symbol>
                </svg>

                <div id="successfulCreating" class="alert alert-success" role="alert">
                    <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg>
                    <h4 class="alert-heading">Well done!</h4>
                    <p>Новый тест:</p>
                    <pre id="newTest" class="fs-5">

                    </pre>
                    <hr>
                    <p class="mb-0">Отлично! Теперь можешь приступить к его заполнению вопросами.</p>
                </div>
                <div id="unsuccessfulCreating" class="alert alert-danger" role="alert">
                    <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                    <h4 class="alert-heading">Error!</h4>
                    <p>Что-то пошло не так, проверь, заполнил ли ты поля 'Topic name' и 'Test name'.</p>
                    <hr>
                    <p class="mb-0">Похоже, что у тебя возникли проблемы с созданием теста.</p>
                </div>

            </div>
            <div class="col-md-10 mx-auto col-lg-8">
                <form class="p-4 p-md-5 border rounded-3 bg-light" >
                    <div id="selectedTopic" modelAttribute="test">
                        <div class="form-floating mb-3">

                            <button class="btn btn-outline-primary btn-lg dropdown-toggle " type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" data-bs-display="static" aria-expanded="false">
                                Select a topic
                            </button>
                            <button onclick="createOwnTopic()" class="btn btn-lg btn-light float-end" type="button">
                                Create a new topic in place
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                <div class="form-floating">
                                    <input class="form-control" id="myInput" type="text" placeholder="Search..">
                                    <label for="myInput">Topic</label>
                                    <table class="table table-bordered table-striped" id="table" style="table-layout: fixed; overflow: scroll;">
                                        <thead>
                                        <tr>
                                            <th width="65">Id</th>
                                            <th >Name</th>
                                            <th >Description</th>
                                        </tr>
                                        </thead>
                                        <tbody id="myTable">
                                        <c:forEach  items="${topics}" var="t">
                                            <tr class="write" style="cursor: pointer;">
                                                <td><div style="overflow: auto;">${t.topicId}</div></td>
                                                <td><div style="overflow: auto;">${t.name}</div></td>
                                                <td><div style="overflow: auto;">${t.description}</div></td>
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
                        <div hidden class=" form-floating mb-3">
                            <input type="text" class="form-control" id="inputTopicId" placeholder="Topic name"/>
                            <label for="inputTopicId" class="form-label">Topic id</label>
                        </div>
                        <div class=" form-floating mb-3">
                            <input disabled type="text" class="form-control" id="inputTopicName" placeholder="Topic name"/>
                            <label for="inputTopicName" class="form-label">Topic name</label>
                        </div>
                    </div>
                    <div id="ownTopic">
                        <div class="mb-3">
                            <button onclick="selectTopic()" class="btn btn-lg btn-light " type="button">Select a topic</button>
                        </div>
                        <div id="notUniqueTopicName" class="alert alert-secondary" role="alert">
                            Тема с таким названием уже сущесвует
                        </div>
                        <div class=" form-floating mb-3">
                            <input type="text" class="form-control" id="inputOwnTopicName" placeholder="Topic name">
                            <label for="inputOwnTopicName" class="form-label">Topic name</label>
                        </div>
                        <div class=" form-floating mb-3">
                            <textarea class="form-control" id="inputOwnTopicDescription" rows="5"></textarea>
                            <label for="inputOwnTopicDescription" class="form-label">Topic description</label>
                        </div>
                    </div>
                    <hr class="my-3">
                    <div id="notUniqueTestName" class="alert alert-secondary" role="alert">
                        Тест с таким названием уже сущесвует
                    </div>
                    <div class=" form-floating mb-3">
                        <input type="text" class="form-control" id="inputTestName" placeholder="name" required="true"/>
                        <label for="inputTestName" class="form-label">Test name</label>
                    </div>
                    <div class=" form-floating mb-3">
                        <textarea class="form-control" id="inputTestDescription" rows="5"></textarea>
                        <label for="inputTestDescription" class="form-label">Test description</label>
                    </div>

                    <button id="createTestButton" class="w-100 btn btn-lg btn-primary" data-bs-toggle="modal" data-bs-target="#createModal" type="button">Create</button>

                    <hr class="my-4">
                    <small class="text-muted">By clicking Create, you create a new test with selected topic.</small>

                    <!-- Modal -->
                    <div class="modal fade" id="createModal" tabindex="-1" aria-labelledby="createModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="createModalLabel">Modal title</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <pre id="newTestModal" class="fs-5">

                                    </pre>
                                </div>
                                <div class="modal-footer">
                                    <button onclick="create()" type="button" class="btn btn-primary">Create</button>
                                    <button id="closeCreatingModal" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>

                <div id="filling_out_the_test" class=" mb-3">
                    <button class="w-100 btn btn-lg btn-success" data-bs-toggle="modal" data-bs-target="#configQuestion" type="button">Filling out the test</button>
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
                                        <input name="answersNumber" type="number" class="form-control" id="inputAnswersCount" placeholder="Answers number" required="true"/>
                                        <label for="inputAnswersCount" class="form-label">Answers number</label>
                                    </div>
                                    <div class=" form-floating mb-3">
                                        <input name="literatureNumber" type="number" class="form-control" id="inputLiteratureCount" placeholder="Literature number" required="true"/>
                                        <label for="inputLiteratureCount" class="form-label">Literature number</label>
                                    </div>
                                    <div class=" form-floating mb-3">
                                        <input name="linksNumber" type="text" class="form-control" id="inputLinksCount" placeholder="Links number" required="true"/>
                                        <label for="inputLinksCount" class="form-label">Links number</label>
                                        <small id="passwordHelpBlock" class="form-text text-muted">
                                            При нескольких литератур, укажите количесво ссылок под каждую из них разделяя запятыми.
                                        </small>
                                    </div>
                                    <input hidden id="questionNumber" name="questionNumber" value="1">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    <input hidden name="testId" type="text" id="testId">
                                    <button class="w-100 btn btn-lg btn-success" type="submit" >Apply</button>

                                </form>
                            </div>
                            <div class="modal-footer">
                                <button id="closeConfigQuestionModal" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
        </div>
    </div>

    <script>
        $("#ownTopic").hide();
        $("#notUniqueTestName").hide();
        $("#filling_out_the_test").hide();
        $("#notUniqueTopicName").hide();
        $("#successfulCreating").hide();
        $("#unsuccessfulCreating").hide();

        let topicNameIsUnique = false;
        let testNameIsUnique = false;
        let selectedTopic = true;
        var topicName = "";

        function checkTopicName(isUnique) {
            if (isUnique) {
                $("#notUniqueTopicName").hide();
                topicNameIsUnique = true;
            } else {
                $("#notUniqueTopicName").show();
                topicNameIsUnique = false
            }
        }

        function checkTestName(isUnique) {
            if (isUnique) {
                $("#notUniqueTestName").hide();
                testNameIsUnique = true;
            } else {
                $("#notUniqueTestName").show();
                testNameIsUnique = false
            }
        }

        function createOwnTopic(){
            $("#selectedTopic").hide();
            $("#ownTopic").show();

            $("#inputTopicId").val("");
            $("#inputTopicName").val("");

            selectedTopic = false;
        }
        function selectTopic(){
            $("#selectedTopic").show();
            $("#ownTopic").hide();

            $("#inputOwnTopicName").val("");
            $("#inputOwnTopicDescription").val("");

            selectedTopic = true;
        }

        $("#table tr").click(function(){
            var topicId=$(this).find('td').eq(0).text();
            topicName = $(this).find('td').eq(1).text();
            // var topicDescription=$(this).find('td').eq(2).text();

            // document.getElementById('oldUserConfig').innerHTML = 'Login: ' + login + ', first name: ' + firstName + ', last name: ' + lastName + ', role:' + role;

            $("#inputTopicId").val(topicId);
            $("#inputTopicName").val(topicName);
            // $("#inputTestDescription").val(topicDescription);
        });

        function sendJSON(){
            $.ajax({
                url: "/admin/addTest",
                type: "POST",
                dataType: "json",
                data: {
                    topicId: $("#inputTopicId").val(),
                    topicName: $("#inputOwnTopicName").val(),
                    topicDescription: $("#inputOwnTopicDescription").val(),
                    testName: $("#inputTestName").val(),
                    testDescription: $("#inputTestDescription").val(),
                    "${_csrf.parameterName}" : "${_csrf.token}"
                },
            })
                .done(function (data) {
                    console.log(data);
                    $("#closeCreatingModal").click();
                    $("#inputTopicName").val("");
                    $("#inputOwnTopicName").val("");
                    $("#inputOwnTopicDescription").val("");
                    $("#inputTestName").val("");
                    $("#inputTestDescription").val("");
                    if(data.success === true){
                        $("#unsuccessfulCreating").hide();
                        $("#successfulCreating").show();
                        $("#successfulCreating").delay(7000).slideUp(300);
                        $("#filling_out_the_test").show();

                        $("#testId").val(data.newTestId);

                    } else {
                        $("#unsuccessfulCreating").show();
                        $("#unsuccessfulCreating").delay(7000).slideUp(300);
                        $("#successfulCreating").hide();
                        $("#filling_out_the_test").hide();
                    }
                })
                .fail(function (xhr, status, error) {
                    alert('Error\n' + xhr.responseText + '\n' + status + '\n' + error);
                });
        }

        $("#createTestButton").on('click', function (){
            var messageText;
            if($("#inputOwnTopicName").val() === ""){
                messageText = 'Topic: ' + $("#inputTopicName").val() + '\nTest: ' + $("#inputTestName").val();
            } else {
                messageText = 'Topic: ' + $("#inputOwnTopicName").val() + '\nTest: ' + $("#inputTestName").val();
            }
            $("#newTest").text(messageText);
            $("#newTestModal ").text(messageText);
            console.log(messageText);
        });

        function create() {

            if (selectedTopic && ($("#inputTopicName").val().length > 0 || $("#inputOwnTopicName").val().length > 0)) {
                if (testNameIsUnique) {
                    sendJSON();
                } else {
                    $("#successfulCreating").hide();
                    $("#closeCreatingModal").click();
                    $("#unsuccessfulCreating").show();
                    $("#unsuccessfulCreating").delay(7000).slideUp(300);
                }
            } else {
                if (testNameIsUnique && topicNameIsUnique) {
                    sendJSON();
                } else {
                    $("#successfulCreating").hide();
                    $("#closeCreatingModal").click();
                    $("#unsuccessfulCreating").show();
                    $("#unsuccessfulCreating").delay(7000).slideUp(300);
                }
            }
        }

        $().ready(function () {
            $("#inputTestName").change(function (event) {
                console.log($(event.target).val());
                $.ajax({
                    url: "/admin/isUniqueTestName",
                    type: "POST",
                    dataType: "json",
                    data: {
                        name: $(event.target).val(),
                        "${_csrf.parameterName}" : "${_csrf.token}"
                    },
                })
                    .done(function (data) {
                        console.log( data);
                        checkTestName(data.unique);
                    })
                    .fail(function (xhr, status, error) {
                        alert('Error\n' + xhr.responseText + '\n' + status + '\n' + error);
                    });
            });


        });

        $().ready(function () {
            $("#inputOwnTopicName").change(function (event) {
                console.log($(event.target).val());
                $.ajax({
                    url: "/admin/isUniqueTopicName",
                    type: "POST",
                    dataType: "json",
                    data: {
                        name: $(event.target).val(),
                        "${_csrf.parameterName}" : "${_csrf.token}"
                    },
                })
                    .done(function (data) {
                        console.log( data);
                        checkTopicName(data.unique);
                    })
                    .fail(function (xhr, status, error) {
                        alert('Error\n' + xhr.responseText + '\n' + status + '\n' + error);
                    });
            });

        });
    </script>
</body>
</html>