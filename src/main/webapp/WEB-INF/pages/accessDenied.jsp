<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <title>Access Denied</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
    <div class="d-flex align-items-center" style="height:100%;">
        <div class="d-flex justify-content-center" style="width:100%">
            <c:url var="loginUrl" value="/login" />
            <div class=".container">
                <div class="alert alert-danger" role="alert">
                    <h5 class="mb-3">Доступ запрещён 😥</h5>
                    <c:url var="loginUrl" value="/login" />
                    <div class="row">
                        <div class="d-grid gap-1 col-8 mx-auto">
                            <a class="btn btn-primary" role="button" href="${loginUrl}">Войти снова</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
