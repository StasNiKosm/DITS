<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <title>Login Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
    <div class="d-flex align-items-center" style="height:100%;">
        <div class="d-flex" style="width:100%">
            <div class="container d-flex justify-content-center">
                <c:url var="loginUrl" value="/login" />
                <form action="${loginUrl}" method="post" class="col" style="max-width: 400px">
                    <c:if test="${param.error != null}">
                        <div class="alert alert-danger" role="alert">
                            Неправильный логин или пароль
                        </div>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <div class="alert alert-primary" role="alert">
                            Выход выполнен успешно
                        </div>
                    </c:if>
                    <c:if test="${param.debug != null}">
                        <div class="alert alert-success" role="alert">
                            <p class="text-center">Окно отладки</p>
                            <div class="d-grid gap-1 col-8 mx-auto">
                                <a class="btn btn-primary" role="button" href="/debug/see_all">Список всех топиков</a>
                                <a class="btn btn-primary" role="button" href="/debug/topic?id=1">Topic id=1</a>
                            </div>
                        </div>
                    </c:if>
                    <div class="mb-3">
                        <label for="loginInput" class="form-label">Логин</label>
                        <input name="ssoId" type="text" class="form-control" id="loginInput" aria-describedby="emailHelp" required>
                    </div>
                    <div class="mb-3">
                        <label for="passwordInput" class="form-label">Пароль</label>
                        <input name="password" type="password" class="form-control" id="passwordInput" required>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <div class="d-grid gap-2 d-md-flex justify-content-md-between">
                        <a class="btn btn-secondary me-md-2" role="button" href="/registration">Зарегистрироваться</a>
                        <button class="btn btn-primary" type="submit">Войти</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
