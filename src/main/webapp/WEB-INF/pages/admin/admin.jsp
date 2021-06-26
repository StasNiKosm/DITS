<%--<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>--%>

<%--<html>--%>
<%--<head>--%>
<%--    <title>Admin Page</title>--%>
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="container" width="100%">--%>
<%--    <div class="row">--%>
<%--        <div class="col-5">--%>
<%--            <div class="container-sm">--%>
<%--                <div class="display-6">--%>
<%--                    <p class="text-center">Привет, ${user.user.firstName} ${user.user.lastName}!</p>--%>
<%--                </div>--%>
<%--                <div class="d-grid gap- d-md-flex justify-content-sm-start">--%>
<%--                    <a href="/logout" class="btn btn-outline-primary btn-sm" tabindex="-1" role="button" aria-disabled="true">/logout</a>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="col" style="background-color: lightgray;">--%>
<%--            <div class="d-flex align-items-center" style="height:100%;">--%>
<%--                <div class="d-flex justify-content-center" style="width:100%">--%>
<%--                    <div class="alert alert-primary" role="alert">--%>
<%--                        Информация будет пявляться тут--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>

<html>
<head>
    <title>Home</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>

<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
    <symbol id="user" viewBox="0 0 16 16">
        <path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
    </symbol>
    <symbol id="self" viewBox="0 0 16 16">
        <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
        <path fill-rule="evenodd" d="M4.285 9.567a.5.5 0 0 1 .683.183A3.498 3.498 0 0 0 8 11.5a3.498 3.498 0 0 0 3.032-1.75.5.5 0 1 1 .866.5A4.498 4.498 0 0 1 8 12.5a4.498 4.498 0 0 1-3.898-2.25.5.5 0 0 1 .183-.683zM6.5 6.497V6.5h-1c0-.568.447-.947.862-1.154C6.807 5.123 7.387 5 8 5s1.193.123 1.638.346c.415.207.862.586.862 1.154h-1v-.003l-.003-.01a.213.213 0 0 0-.036-.053.86.86 0 0 0-.27-.194C8.91 6.1 8.49 6 8 6c-.491 0-.912.1-1.19.24a.86.86 0 0 0-.271.194.213.213 0 0 0-.036.054l-.003.01z"/>
        <path d="M2.31 5.243A1 1 0 0 1 3.28 4H6a1 1 0 0 1 1 1v1a2 2 0 0 1-2 2h-.438a2 2 0 0 1-1.94-1.515L2.31 5.243zM9 5a1 1 0 0 1 1-1h2.72a1 1 0 0 1 .97 1.243l-.311 1.242A2 2 0 0 1 11.439 8H11a2 2 0 0 1-2-2V5z"/>
    </symbol>
    <symbol id="test" viewBox="0 0 16 16">
        <path fill-rule="evenodd" d="M5.707 13.707a1 1 0 0 1-.39.242l-3 1a1 1 0 0 1-1.266-1.265l1-3a1 1 0 0 1 .242-.391L10.086 2.5a2 2 0 0 1 2.828 0l.586.586a2 2 0 0 1 0 2.828l-7.793 7.793zM3 11l7.793-7.793a1 1 0 0 1 1.414 0l.586.586a1 1 0 0 1 0 1.414L5 13l-3 1 1-3z"/>
        <path fill-rule="evenodd" d="M9.854 2.56a.5.5 0 0 0-.708 0L5.854 5.855a.5.5 0 0 1-.708-.708L8.44 1.854a1.5 1.5 0 0 1 2.122 0l.293.292a.5.5 0 0 1-.707.708l-.293-.293z"/>
        <path d="M13.293 1.207a1 1 0 0 1 1.414 0l.03.03a1 1 0 0 1 .03 1.383L13.5 4 12 2.5l1.293-1.293z"/>
    </symbol>
    <symbol id="topic" viewBox="0 0 16 16">
        <path fill-rule="evenodd" d="M4.5 3a2.5 2.5 0 0 1 5 0v9a1.5 1.5 0 0 1-3 0V5a.5.5 0 0 1 1 0v7a.5.5 0 0 0 1 0V3a1.5 1.5 0 1 0-3 0v9a2.5 2.5 0 0 0 5 0V5a.5.5 0 0 1 1 0v7a3.5 3.5 0 1 1-7 0V3z"/>
    </symbol>
    <symbol id="statistic" viewBox="0 0 16 16">
        <path fill-rule="evenodd" d="M4 11H2v3h2v-3zm5-4H7v7h2V7zm5-5h-2v12h2V2zm-2-1a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h2a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1h-2zM6 7a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v7a1 1 0 0 1-1 1H7a1 1 0 0 1-1-1V7zm-5 4a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1v-3z"/>
        <path fill-rule="evenodd" d="M0 14.5a.5.5 0 0 1 .5-.5h15a.5.5 0 0 1 0 1H.5a.5.5 0 0 1-.5-.5z"/>
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
                <a href="/logout" type="button" class="btn btn-warning">Выйти</a>
            </div>
        </div>
    </div>
</header>

<div class="container px-4 py-5" >
    <div class="row">
        <div class="col-md-3 col-sm-6">
            <img src="http://devincubator.by/images/human1.png" alt="mdo" width="200" height="200" >
        </div>
        <div class="col col-sm-6 display-5">
            <p class="text-left">Привет, ${user.user.firstName} ${user.user.lastName}!</p>
        </div>
    </div>
    <h1 class="pb-1 border-bottom text-center ">Выбор возможностей</h1>
    <div class="row g-4 py-5 row-cols-1 row-cols-lg-3">
        <div class="col d-flex align-items-start">
            <div class="icon-square bg-light text-dark flex-shrink-0 me-3">
                <svg class="bi" width="1em" height="1em"><use xlink:href="#user"/></svg>
            </div>
            <div>
                <h2>Users</h2>
                <p>Можешь создать нового уникального пользователя, изменить роль уже существующего пользователя: сделать админом, тьютером или обычным пользователем, а также его удалить.</p>
                <a href="/admin/createUser" class="btn btn-outline-primary btn-lg" style="min-width: 90px;">Create</a>
                <a href="/admin/editUser" class="btn btn-outline-warning btn-lg" style="min-width: 90px">Edit</a>
                <a href="/admin/deleteUser" class="btn btn-outline-danger btn-lg" style="min-width: 90px">Delete</a>
            </div>
        </div>
        <div class="col d-flex align-items-start">
            <div class="icon-square bg-light text-dark flex-shrink-0 me-3">
                <svg class="bi" width="1em" height="1em"><use xlink:href="#topic"/></svg>
            </div>
            <div>
                <h2>Topics</h2>
                <p>Расширяй ситему тестирования! Можете создать новую тему для тестов, изменить уже имеющиеся, либо его удалить. Но будь внимателен при удалении!</p>
                <a href="#" class="btn btn-outline-primary btn-lg" style="min-width: 90px;">Create</a>
                <a href="#" class="btn btn-outline-warning btn-lg" style="min-width: 90px">Edit</a>
                <a href="#" class="btn btn-outline-danger btn-lg" style="min-width: 90px">Delete</a>
            </div>
        </div>
        <div class="col d-flex align-items-start">
            <div class="icon-square bg-light text-dark flex-shrink-0 me-3">
                <svg class="bi" width="1em" height="1em"><use xlink:href="#test"/></svg>
            </div>
            <div>
                <h2>Tests</h2>
                <p>Расширяй ситему тестирования! Можешь создать новый тест, изменить уже имеющиеся, либо его удалить. Но будь внимателен при удалении!</p>
                <a href="/admin/createTest" class="btn btn-outline-primary btn-lg" style="min-width: 90px;">Create</a>
                <a href="#" class="btn btn-outline-warning btn-lg" style="min-width: 90px">Edit</a>
                <a href="#" class="btn btn-outline-danger btn-lg" style="min-width: 90px">Delete</a>
            </div>
        </div>
        <div class="col d-flex align-items-start">
            <div class="icon-square bg-light text-dark flex-shrink-0 me-3">
                <svg class="bi" width="1em" height="1em"><use xlink:href="#statistic"/></svg>
            </div>
            <div>
                <h2>Statistic</h2>
                <p>Можешь постмотривать общюю статистику: по всем тестам, по всем пользователям, по всем вопросам.</p>
                <a href="/admin/statistic" class="btn btn-outline-primary btn-lg" style="min-width: 90px;">Get</a>
            </div>
        </div>
        <div class="col d-flex align-items-start">
            <div class="icon-square bg-light text-dark flex-shrink-0 me-3">
                <svg class="bi" width="1em" height="1em"><use xlink:href="#self"/></svg>
            </div>
            <div>
                <h2>Self</h2>
                <p>Саморазвивайся и тестируй систему! Проходи тесты как обычный пользователь.  </p>
                <a href="#" class="btn btn-outline-primary btn-lg" style="min-width: 90px;">Go</a>
            </div>
        </div>
    </div>
</div>
<%--<div class="container-sm h-100">
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
                <a href="/admin/creatTest" class="btn btn-primary" type="button">Создать тест</a>
                <a href="/admin/creatUser" class="btn btn-primary" type="button">Создать пользователя</a>
                <a href="/admin/statistic" class="btn btn-primary" type="button">Статистика</a>
            </div>
        </div>
    </div>
</div>--%>
</body>
</html>
