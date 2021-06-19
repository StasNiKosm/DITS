<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>

<html>
<head>
    <title>User Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
<div class="container-sm h-100">
    <div class="row justify-content-md-center align-items-center" style="min-height: 100px; height:10%; max-height: 150px;">
        <div class="col-sm-6 display-6">
            <p class="text-center">Привет, ${user.user.firstName} ${user.user.lastName}!</p>
        </div>
        <div class="col-sm-6">
            <div class="row justify-content-md-center align-items-center">
                <a href="/logout" class="btn btn-primary btn-lg active mx-auto" style="max-width: 300px;" role="button" aria-pressed="true">Выход</a>
            </div>
        </div>
    </div>
    <div class="row justify-content-md-center align-items-center" style="height: 90%;">
        <div class="col-sm-6">
            <img src="http://devincubator.by/images/human1.png" class="img-fluid mx-auto d-block" alt="DevIncubator">
        </div>
        <div class="col-sm-6 justify-content-md-center align-items-center">
            <div class="row justify-content-md-center align-items-center">
                <div class="col display-6">
                    <p class="text-center">Выбор раздела</p>
                </div>
            </div>
            <div class="d-grid gap-2 col-8 mx-auto">
                <a href="#" class="btn btn-primary" type="button">Выбор темы и теста</a>
                <a href="#" class="btn btn-primary" type="button">Личная статистика</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
