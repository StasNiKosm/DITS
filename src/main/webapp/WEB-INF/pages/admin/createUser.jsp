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
                <h1 class="display-4 fw-bold lh-1 mb-3">Creating a user</h1>
                <p class="col-lg-10 fs-4">Создавай нового уникального пользователя с конкретными ролями. Сделав это, убедись, что логин и пароль аккаунта физическому пользователю будет передан без ошибок.</p>

                <div id="successfulCreating" class="alert alert-success" role="alert">
                    <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg>
                    <h4 class="alert-heading">Well done!</h4>
                    <p class="fs-6">Новый пользователь: </p>
                    <pre id="newUser" class="fs-5">

                    </pre>
                    <hr>
                    <p class="mb-0">Создание нового пользователя прошло успешно. Молодец!</p>
                </div>
                <div id="unsuccessfulCreating" class="alert alert-danger" role="alert">
                    <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                    <h4 class="alert-heading">Fail!</h4>
                    <p class="fs-6">Создавай юзера внимательно! Ни одно поле не может оставаться пустым. Пароль и логин должны иметь 4 или больше символа.</p>
                    <hr>
                    <p class="mb-0">Создание нового позователя, похоже что, вызвало у тебя затруднение.</p>
                </div>
            </div>

            <div class="col-md-10 mx-auto col-lg-6">
                <form id="creatingUserForm" class="p-4 p-md-5 border rounded-3 bg-light" >
                    <div class="form-floating mb-3">
                        <select id="selectRole" class="form-select  mb-3" id="floatingInput" aria-label=".form-select-lg example">
                            <c:forEach items="${roles}" var="role">
                                <option value="${role}">${role}</option>
                            </c:forEach>
                        </select>
                        <label for="floatingInput">Role</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input  class="form-control" id="firstName" placeholder="First name" required>
                        <label for="firstName">First name</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input  class="form-control" id="secondName" placeholder="Last name" required>
                        <label for="secondName">Last name</label>
                    </div>
                    <div id="notUniqueLogin" class="alert alert-secondary" role="alert">
                        Учётная запись с таким логином уже существует
                    </div>
                    <div class="form-floating mb-3">
                        <input class="form-control" id="inputUsername" placeholder="Login" required>
                        <label for="inputUsername">Login</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control" id="inputPassword" placeholder="Password" required>
                        <label for="inputPassword">Password</label>
                    </div>

                    <button id="createButton" class="w-100 btn btn-lg btn-primary" data-bs-toggle="modal" data-bs-target="#modal" type="button">Create</button>
                    <hr class="my-4">
                    <small class="text-muted">By clicking Create, you must show to the user the login and password.</small>

                    <!-- Modal -->
                    <div id="modal" class="modal fade" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Посмотрим-ка еще разок!</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <p class="fs-6">Пользователь, которого ты хочешь создать: </p>
                                    <pre id="newUserModal" class="fs-5">

                                    </pre>
                                </div>
                                <div class="modal-footer">
                                    <button onclick="create()" type="button" class="btn btn-primary">Create</button>
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
        $("#notUniqueLogin").hide();
        $("#unsuccessfulCreating").hide();
        $("#successfulCreating").hide();
        $("#passwordsNotEquals").hide();

        let usernameIsUnique = false;

        function checkUserName(isUnique) {
            if (isUnique) {
                $("#notUniqueLogin").hide();
                usernameIsUnique = true;
            } else {
                $("#notUniqueLogin").show();
                usernameIsUnique = false
            }
        }

        $("#createButton").on('click', function (){
            var messageText = 'First name: ' + $("#firstName").val() + '\nLast name: ' + $("#secondName").val() + '\nLogin: ' + $("#inputUsername").val() + '\nRole: ' + $("#selectRole option:selected").val();
            $("#newUser").text(messageText);
            $("#newUserModal").text(messageText);
        });

        function create() {
            if (
                $("#inputUsername").val().length >= 4 && usernameIsUnique &&
                $("#firstName").val().length > 0 &&
                $("#secondName").val().length > 0 &&
                $("#inputPassword").val().length >= 4)
            {
                $.ajax({
                    url: "/admin/addUser",
                    type: "POST",
                    dataType: "json",
                    data: {
                        firstName: $("#firstName").val(),
                        lastName: $("#secondName").val(),
                        login: $("#inputUsername").val(),
                        password: $("#inputPassword").val(),
                        role: $("#selectRole option:selected").val(),
                        "${_csrf.parameterName}" : "${_csrf.token}"
                    },
                })
                    .done(function (data) {
                        console.log(data);
                        $("#closeModal").click();
                        $("#firstName").val("");
                        $("#secondName").val("");
                        $("#inputUsername").val("");
                        $("#inputPassword").val("");
                        if(data.success === true){
                            $("#unsuccessfulCreating").hide();
                            $("#successfulCreating").show();
                            $("#successfulCreating").delay(10000).slideUp(300);
                        } else {
                            $("#successfulCreating").hide();
                            $("#unsuccessfulCreating").show();
                            $("#unsuccessfulCreating").delay(10000).slideUp(300);
                        }
                    })
                    .fail(function (xhr, status, error) {
                        alert('Error\n' + xhr.responseText + '\n' + status + '\n' + error);
                    });
            } else {
                $("#successfulCreating").hide();
                $("#closeModal").click();
                $("#unsuccessfulCreating").show();
                $("#unsuccessfulCreating").delay(10000).slideUp(300);
            }
        }

        $().ready(function () {

            $("#inputUsername").change(function (event) {
                $.ajax({
                    url: "/admin/isUniqueLogin",
                    type: "POST",
                    dataType: "json",
                    data: {
                        login: $(event.target).val(),
                        "${_csrf.parameterName}" : "${_csrf.token}"
                    },
                })
                    .done(function (data) {
                        console.log( data);
                        checkUserName(data.unique);
                    })
                    .fail(function (xhr, status, error) {
                        alert('Error\n' + xhr.responseText + '\n' + status + '\n' + error);
                    });
            });

        });
    </script>
</body>
</html>