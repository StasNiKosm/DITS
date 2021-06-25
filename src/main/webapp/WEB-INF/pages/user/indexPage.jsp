<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>

<html>
<head>
    <title>Выбор раздела</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row justify-content-md-center align-items-center" style="min-height: 100px;">
        <div class="col-lg-6 display-6">
            <p class="text-center">Привет, ${user.user.firstName} ${user.user.lastName}!</p>
        </div>
        <div class="col-lg-6">
            <div class="row justify-content-md-center align-items-center">
                <a href="/logout" class="btn btn-primary btn-lg mx-auto" style="max-width: 300px;" role="button" aria-pressed="true">Выход</a>
            </div>
        </div>
    </div>
    <div class="mb-5"></div>
    <div class="row justify-content-md-center align-items-center">
        <div class="col-lg-6">
            <img src="http://devincubator.by/images/human1.png" class="img-fluid mx-auto d-block" alt="DevIncubator">
        </div>
        <div class="col-lg-6 justify-content-md-center align-items-center">
            <div class="row justify-content-md-center align-items-center">
                <div class="col-lg display-6">
                    <p class="text-center">Выбор раздела</p>
                </div>
            </div>
            <div class="d-grid gap-2 col-lg-8 mx-auto">
                <a href="/user/test-chooser" class="btn btn-lg btn-primary" type="button">Выбор темы и теста</a>
                <a href="#" class="btn btn-lg btn-primary" type="button">Личная статистика</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
