<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Регистрация</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
    <div class="d-flex align-items-center" style="height:100%;">
        <div class="d-flex justify-content-center" style="width:100%">
            <div class="container" style="max-width: 600px">
                <form id="registrationForm" action="/registration" method="post">
                    <div class="display-6 mb-3">
                        Регистрация
                    </div>
                    <div id="formNotCorrect" class="alert alert-secondary" role="alert">
                        Учётная запись с таким логином уже существует
                    </div>
                    <div id="notUniqueLogin" class="alert alert-secondary" role="alert">
                        Учётная запись с таким логином уже существует
                    </div>
                    <div class="mb-3 row">
                        <label for="inputUserName" class="col-sm-4 col-form-label">Login</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="inputUsername">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="firstName" class="col-sm-4 col-form-label">Имя</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="firstName">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="secondName" class="col-sm-4 col-form-label">Фамилия</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="secondName">
                        </div>
                    </div>
                    <div class="alert alert-secondary" role="alert">
                        Пароли не совпадают
                    </div>
                    <div class="mb-3 row">
                        <label for="inputPasswordFirst" class="col-sm-4 col-form-label">Пароль</label>
                        <div class="col-sm-8">
                            <input type="password" class="form-control" id="inputPasswordFirst">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="inputPasswordSecond" class="col-sm-4 col-form-label">Повторите пароль</label>
                        <div class="col-sm-8">
                            <input type="password" class="form-control" id="inputPasswordSecond">
                        </div>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <div class="d-grid gap-2 d-md-flex justify-content-md-between">
                        <a class="btn btn-secondary me-md-2" role="button" href="/login">Назад</a>
                        <button class="btn btn-primary" type="submit">Зарегистрироваться</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script>

        function checkUserName(isUnique) {
            if (isUnique)
                $("#notUniqueLogin").hide();
            else
                $("#notUniqueLogin").show();
        }

        $().ready(function () {

            $("#inputUsername").change(function (event) {

                console.log($(event.target).val());

                $.ajax({
                    url: "/checkLogin",
                    type: "POST",
                    dataType: "json",
                    data: {
                        login: $(event.target).val(),
                        "${_csrf.parameterName}" : "${_csrf.token}"
                    },
                })
                .done(function (data) {
                    checkUserName(data.unique);
                })
                .fail(function (xhr, status, error) {
                    alert('Error\n' + xhr.responseText + '\n' + status + '\n' + error);
                });

            });

            $("#notUniqueLogin").hide();

            $("#registrationForm").submit(function (evnet) {
                if (!(
                    $("#loginInput").val().length >= 4 &&
                    $("#firstName").val().length > 0 &&
                    $("#secondName").val().length > 0 &&
                    $("passwordInput").val().length > 4 &&
                    $("#inputPasswordFirst").val() === $("#inputPasswordSecond").val())
                ) {
                    $("#formNotCorrect").show();
                    evnet.preventDefault();
                }
            })

            $("#formNotCorrect").hide();

        });

    </script>
</body>
</html>
