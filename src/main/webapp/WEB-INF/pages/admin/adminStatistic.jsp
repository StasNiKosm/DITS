<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>

<html>
<head>
    <title>Статистика</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
<div class="container-xxl">
    <div class="row">
        <div class="col-md-12">
            <div class="row gy-15">
                <div class="col-md-2">
                </div>
                <div class="col-md-2">
                </div>
                <div class="col-md-2">
                </div>
                <div class="col-md-2">
                </div>
                <div class="col-md-2">
                    <div class="row d-flex align-items-center">
                        <a href="/admin" type="button" class="btn btn-primary btn-lg" style="max-width: 100px">Назад</a>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="row align-items-center">
                        <a href="/logout" type="button" class="btn btn-outline-danger btn-lg">Выход</a>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-8">
                    <h2>Общая статистика</h2>
                    <p>Смотри любую статистику по всей базе данных</p>
                </div>
                <div class="col-md-4">
                </div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4 gap-3">
                    <div class="row justify-content-md-center">
                        <a href="/goHomeAdmin" type="button" class="btn btn-outline-primary btn-lg">Статистика по тестам</a>
                    </div>
                    <div class="row justify-content-md-center">
                        <a href="/goHomeAdmin" type="button" class="btn btn-outline-primary btn-lg">Статистика по вопросам</a>
                    </div>
                    <div class="row justify-content-md-center">
                        <a href="/goHomeAdmin" type="button" class="btn btn-outline-primary btn-lg">Статистика пользователей</a>
                    </div>
                </div>
                <div class="col-md-4"></div>
            </div>
        </div>
    </div>
</div>
</body>