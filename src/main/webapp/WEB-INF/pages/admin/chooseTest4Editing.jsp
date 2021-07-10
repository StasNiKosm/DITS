<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Создание теста</title>
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
    <header class="p-3 bg-dark text-white">
        <div class="container">
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                <img src="https://e7.pngegg.com/pngimages/754/474/png-clipart-computer-icons-system-administrator-avatar-computer-network-heroes-thumbnail.png" alt="mdo" width="32" height="32" class="rounded-circle">
                <span class="fs-4 px-2">Admin</span>
                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                    <li><a href="#" class="nav-link px-2 text-secondary">Home</a></li>
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
            <div class="col-lg-4 text-center text-lg-start">
                <h1 class="display-4 fw-bold lh-1 mb-3">Creating</h1>
                <p class="col-lg-10 fs-4">У тебя есть возвожность создавать нового уникального пользователя. Сделав это, убедись, что права на пользование аккаутном передал физическому пользователю без ошибок.</p>
            </div>


            <div class="col-md-10 mx-auto col-lg-8">
                //form
                <div class="p-4 p-md-5 border rounded-3 bg-light" >
                    <div class="form-floating mb-3">

                        <button class="btn btn-outline-primary btn-lg dropdown-toggle " type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" data-bs-display="static" aria-expanded="false">
                            Select a test
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                            <div class="form-floating">
                                <input class="form-control" id="myInput" type="text" placeholder="Search..">
                                <label for="myInput">Topic</label>
                                <table class="table table-bordered table-striped" id="table" style="table-layout: fixed; overflow: scroll;">
                                    <thead>
                                    <tr>
                                        <th width="65">№</th>
                                        <th >Test</th>
                                        <th >Topic</th>
                                    </tr>
                                    </thead>
                                    <tbody id="myTable">
                                    <c:forEach  items="${tests}" var="t" varStatus="loop">
                                        <tr class="write" style="cursor: pointer;">
                                            <td><div style="overflow: auto;">${loop.index + 1}</div></td>
                                            <td hidden><div style="overflow: auto;">${t.testId}</div></td>
                                            <td><div style="overflow: auto;">${t.name}</div></td>
                                            <td hidden><div style="overflow: auto;">${t.description}</div></td>
                                            <td><div style="overflow: auto;">${t.topic.name}</div></td>
                                            <td hidden><div style="overflow: auto;">${t.topic.description}</div></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
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
                            </div>
                        </ul>
                    </div>


                    <div class=" form-floating mb-3">
                        <input type="text" class="form-control" id="inputTestName" placeholder="name" required/>
                        <label for="inputTestName" class="form-label">Test name</label>
                    </div>
                    <div class=" form-floating mb-3">
                        <textarea class="form-control" id="inputTestDescription" rows="5"></textarea>
                        <label for="inputTestDescription" class="form-label">Test description</label>
                    </div>

                    <hr class="my-3">

                    <div class=" form-floating mb-3">
                        <input type="text" class="form-control" id="inputTopicName" placeholder="Topic name">
                        <label for="inputTopicName" class="form-label">Topic name</label>
                    </div>
                    <div class=" form-floating mb-3">
                        <textarea class="form-control" id="inputTopicDescription" rows="5"></textarea>
                        <label for="inputTopicDescription" class="form-label">Topic description</label>
                    </div>

                    <form method="get" action="/admin/editFirstQuestion4Test">
                        <input name="testId" type="text" class="form-control" id="inputTestId" placeholder="testId" required/>
                        <button id="chooseButton"  class="w-100 btn btn-lg btn-primary" type="submit">Choose</button>

                    </form>

                    <hr class="my-4">
                    <small class="text-muted">By clicking Create, you create a new test with selected topic.</small>
                </div>

            </div>
        </div>
    </div>

    <script>

        $("#table tr").click(function(){
            var testId = $(this).find('td').eq(1).text();
            var testName = $(this).find('td').eq(2).text();
            var testDescription = $(this).find('td').eq(3).text();
            var topicName = $(this).find('td').eq(4).text();
            var topicDescription = $(this).find('td').eq(5).text();

            // document.getElementById('oldUserConfig').innerHTML = 'Login: ' + login + ', first name: ' + firstName + ', last name: ' + lastName + ', role:' + role;

            $("#chooseButton").val(testId);

            $("#inputTestId").val(testId);
            $("#inputTestName").val(testName);
            $("#inputTestDescription").val(testDescription);
            $("#inputTopicName").val(topicName);
            $("#inputTopicDescription").val(topicDescription);
        });

    </script>
</body>
</html>