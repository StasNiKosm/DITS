<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Создание пользователя</title>
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
            <div class="col-lg-6 text-center text-lg-start">
                <h1 class="display-4 fw-bold lh-1 mb-3">Editing a topic</h1>
                <p class="col-lg-10 fs-4">Корректируй темы, исправляй ошибки. Пользователи должны видеть и четко понимать, на какие темы они проходят тесты.</p>
                <c:if test="${success != null}">
                    <c:if test="${success == true}">
                        <div id="successfulEdition" class="alert alert-success" role="alert">
                            <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg>
                            <h4 class="alert-heading">Well done!</h4>
                            <p>Тема была успешна изменена.</p>
                            <hr>
                            <p class="mb-0">Всё прошло удачно.</p>
                        </div>
                    </c:if>
                    <c:if test="${success == false}">
                        <div id="unsuccessfulEdition" class="alert alert-danger" role="alert">
                            <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                            <h4 class="alert-heading">Error!</h4>
                            <p>Смотри внимательно! Поля не должны быть пустыми.</p>
                            <hr>
                            <p class="mb-0">Похоже, что у тея возникли некоторые трудности.</p>
                        </div>
                    </c:if>
                </c:if>
            </div>

            <div class="col-md-10 mx-auto col-lg-6">
                <form class="p-4 p-md-5 border rounded-3 bg-light" method="post" action="/admin/editTopic">
                    <div class="form-floating mb-3">
                        <button class="btn btn-outline-primary btn-lg dropdown-toggle " type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" data-bs-display="static" aria-expanded="false">
                            Select topic
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
                            </div>
                        </ul>
                    </div>
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

                    <div id="notUniqueTopicName" class="alert alert-secondary" role="alert">
                        Тема с таким названием уже сущесвует
                    </div>
                    <div class="mb-3">
                        <label for="inputName" class="form-label">Topic name</label>
                        <input name="topicName" type="text" class="form-control" id="inputName" placeholder="name" required>
                    </div>
                    <div class="mb-3">
                        <label for="inputDescription" class="form-label">Description</label>
                        <textarea name="topicDescription" class="form-control" id="inputDescription" rows="5" required></textarea>
                    </div>

                    <button id="editTopicButton" class="w-100 btn btn-lg btn-warning" data-bs-toggle="modal" data-bs-target="#editModal" type="button">Edit</button>
                    <hr class="my-4">
                    <small class="text-muted">By clicking Edit, you edit selected topic.</small>

                    <!-- Modal -->
                    <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="editModalLabel">Посмотрим-ка еще раз!</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <p>Хочешь изменить тему: </p>
                                    <pre id="oldTopic" class="fs-5 ">

                                    </pre>
                                    <p>на: </p>
                                    <pre id="newTopic" class="fs-5">

                                    </pre>
                                </div>
                                <div class="modal-footer">
                                    <input hidden name="topicId" type="text" class="form-control" id="inputId" placeholder="id" required>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    <button type="submit" class="btn btn-warning">Edit</button>
                                    <button id="closeModal" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script>

        var id;
        var oldName;
        var oldDescription;

        $("#table tr").click(function(){
            id = $(this).find('td').eq(0).text();
            oldName = $(this).find('td').eq(1).text();
            oldDescription = $(this).find('td').eq(2).text();
            $("#inputId").val(id);
            $("#inputName").val(oldName);
            $("#inputDescription").val(oldDescription);
        });

        $("#editTopicButton").on('click', function (){
            var oldTopic = 'Topic name: ' + oldName + '\nDescription: ' + oldDescription;
            var newTopic = 'Topic name: ' + $("#inputName").val() + '\nDescription: ' + $("#inputDescription").val();
            $("#oldTopic").text(oldTopic);
            $("#newTopic").text(newTopic);
        });

        $("#notUniqueTopicName").hide();
        $("#unsuccessfulEdition").delay(7000).slideUp(300);
        $("#successfulEdition").delay(5000).slideUp(300);

        let topicNameIsUnique = false;

        function checkTopicName(isUnique) {
            if (isUnique) {
                $("#notUniqueTopicName").hide();
                topicNameIsUnique = true;
            } else {
                $("#notUniqueTopicName").show();
                topicNameIsUnique = false
            }
        }

        $("#creatingUserForm").submit(function (event) {
            if ( ! ($("#inputName").val().length > 0 && topicNameIsUnique && $("#inputDescription").val().length > 0)) {
                $("#closeModal").click();
                $("#successfulEdition").hide();
                $("#unsuccessfulEdition").show();
                $("#unsuccessfulEdition").delay(7000).slideUp(300);
                event.preventDefault();
            }
        })

        $().ready(function () {

            $("#inputName").change(function (event) {

                topicNameIsUnique = false;
                $("#notUniqueTopicName").hide();
                if($("#inputName").val() !== oldName){

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
                } else {
                    topicNameIsUnique = true;
                }
            });
        });

    </script>

</body>
</html>