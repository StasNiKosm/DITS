<%@ page buffer="8192kb" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Удаление пользователя</title>
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
                <h1 class="display-4 fw-bold lh-1 mb-3">Deleting a user</h1>
                <p class="col-lg-10 fs-4">Удаляй неугодных тебе пользователь, но будь аккуратен, назад со всей их историей не вернуть...</p>
                <c:if test="${success != null}">
                    <c:if test="${success == true}">
                        <div id="successfulDeletion" class="alert alert-success" role="alert">
                            <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg>
                            <h4 class="alert-heading">Well done!</h4>
                            <p>Пользователь удален.</p>
                            <hr>
                            <p class="mb-0">Всё просшло успешно, но только всех пользователей не удаляй, ок? хаха:).</p>
                        </div>
                    </c:if>
                    <c:if test="${success == false}">
                        <div id="unsuccessfulDeletion" class="alert alert-danger" role="alert">
                            <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                            <h4 class="alert-heading">Error!</h4>
                            <p>Пользователь НЕ удален, что-то пошло не так.</p>
                            <hr>
                            <p class="mb-0">Выясняем причины :(</p>
                        </div>
                    </c:if>
                </c:if>
            </div>

            <div class="col-md-10 mx-auto col-lg-6">
                <form method="post" action="/admin/deleteUser" class="p-4 p-md-5 border rounded-3 bg-light" >
                    <div class="form-floating mb-3">
                        <button class="btn btn-outline-primary btn-lg dropdown-toggle " type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" data-bs-display="static" aria-expanded="false">
                            Select user
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                            <div class="form-floating">
                                <input class="form-control" id="myInput" type="text" placeholder="Search..">
                                <label for="myInput">User</label>
                                <table class="table table-bordered table-striped" id="table" style="table-layout: fixed; overflow: scroll;">
                                    <thead>
                                    <tr>
                                        <th width="70">Id</th>
                                        <th width="75">Role</th>
                                        <th >First name</th>
                                        <th >Last name</th>
                                        <th >Login</th>
                                    </tr>
                                    </thead>
                                    <tbody id="myTable">
                                    <c:forEach  items="${users}" var="u">
                                        <tr class="write" style="cursor: pointer;">
                                            <td><div style="overflow: auto;">${u.userId}</div></td>
                                            <td><div style="overflow: auto;">${u.role.substring(5, 6).concat(u.role.substring(6).toLowerCase())}</div></td>
                                            <td><div style="overflow: auto;">${u.firstName}</div></td>
                                            <td><div style="overflow: auto;">${u.lastName}</div></td>
                                            <td><div style="overflow: auto;">${u.login}</div></td>
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

                    <div class="form-floating mb-3">
                        <input class="form-control role" id="role" placeholder="Role" required="true"/>
                        <label for="role">Role</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input class="form-control first-name" id="firstName" placeholder="First name" required="true"/>
                        <label for="firstName">First name</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input class="form-control last-name" id="secondName" placeholder="Last name" required="true"/>
                        <label for="secondName">Last name</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input class="form-control login" id="inputUsername" placeholder="Login" required="true"/>
                        <label for="inputUsername">Login</label>
                    </div>
                    <button class="w-100 btn btn-lg btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal" type="button">Delete</button>
                    <hr class="my-4">
                    <small class="text-muted">By clicking Delete, you delete user data permanently.</small>

                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Посмотрим-ка ещё раз!</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <p>Хочешь удалить ползователя: </p>
                                    <pre id="user" class="fs-5">

                                    </pre>
                                </div>
                                <div class="modal-footer">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    <input hidden name="userId" id="userId" class="form-control id" placeholder="Id" required/>
                                    <button type="submit" class="btn btn-danger">Delete</button>
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
                <script>

                    $("#successfulDeletion").delay(5000).slideUp(300);
                    $("#unsuccessfulDeletion").delay(5000).slideUp(300);

                    $("#table tr").click(function(){
                        $(this).addClass('selected').siblings().removeClass('selected');
                        var id=$(this).find('td').eq(0).text();
                        var role=$(this).find('td').eq(1).text();
                        var firstName=$(this).find('td').eq(2).text();
                        var lastName=$(this).find('td').eq(3).text();
                        var login=$(this).find('td').eq(4).text();
                        $("#userId").val(id);
                        $('input.role').val(role);
                        $('input.first-name').val(firstName);
                        $('input.last-name').val(lastName);
                        $('input.login').val(login);

                        var messageText = 'First name: ' + firstName + '\nLast name: ' + lastName + '\nLogin: ' + login + '\nRole: ' + role;
                        $("#user").text(messageText);
                    });
                </script>

            </div>
        </div>
    </div>



</body>
</html>