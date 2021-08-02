<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Выбор теста и темы</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
    <div class="d-flex align-items-center" style="height:100%;">
        <div class="d-flex justify-content-center" style="width:100%">
            <div class="container" style="max-width: 700px">
                <c:if test="${param.error != null}">
                    <div class="alert alert-primary" role="alert">
                        Что-то пошло не так. Повторите попытку.
                    </div>
                </c:if>
                <form method="get" action="/user/test">

                    <div class="mb-3 row">
                        <div class="col-lg form-floating">
                            <select id="topic" class="form-select form-select-lg" style="min-height: 80px">
                                <c:forEach items="${topics}" var="topic" varStatus="status">
                                    <option value="${topic.topicId}" <c:if test="${status.index == 0}">select</c:if> >
                                        [${topic.topicId}] : ${topic.name}
                                    </option>
                                </c:forEach>
                            </select>
                            <label for="test" style="margin-left: 10px">Выберите тему из списка</label>
                        </div>
                    </div>

                    <div class="d-flex justify-content-center">
                        <div id="spinner" class="spinner-border text-primary mb-3" role="status">
                            <span class="visually-hidden">Loading...</span>
                        </div>
                    </div>

                    <div id="testDiv" class="mb-3 row">
                        <div class="col form-floating">
                            <select id="test" name="testId" class="form-select form-select-lg" style="min-height: 80px">

                            </select>
                            <label for="test" style="margin-left: 10px">Выберите тест из списка</label>
                        </div>
                    </div>

                    <div id="d-description" class="mb-3">
                        <div class="collapse" id="col-description">
                            <div class="card card-body">
                                <h5>
                                    Описание:
                                    <small id="description" class="text-muted"></small>
                                </h5>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <a class="col-lg-4 btn btn-lg btn-secondary" role="button" href="/user">Назад</a>
                        <div class="col-lg-4 d-flex justify-content-center">
                            <input type="checkbox" class="mx-auto btn-check" id="btn-check-outlined" autocomplete="off">
                            <label style="width:100%; font-size: 1.25rem; padding: .5rem 1rem;" class="btn btn-outline-primary" for="btn-check-outlined" data-bs-toggle="collapse" href="#col-description" role="button" aria-expanded="false" aria-controls="col-description">Подробно</label>
                        </div>
                        <button id="submit-button" class="col-lg-4 btn btn-lg btn-primary" type="submit">Выбрать</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script>

        let tests;

        function enable(element) {
            element.removeAttr("disabled");
        }

        function disable(element) {
            element.attr("disabled", "disabled");
        }

        function fillTestSelection(testList) {

            $("#test").find('option').remove().end();

            testList.tests.forEach(function (test) {
                $("#test").append(new Option(test.name, test.id));
            })

            enable($("#test"));
            enable($("#submit-button"));

            if (testList.tests.length === 0) {
                $("#test").append(new Option("В этой теме ещё нет тестов"));
                disable($("#submit-button"))
                disable($("#test"))
            }

            $("#spinner").hide();
            $("#testDiv").show();
        }

         $().ready(function() {

            $("#test").change(function (event) {
                console.log("CLICK " + $(event.target).val());
                let cId = $(event.target).val();
                tests.forEach(function (test) {
                    if (cId == test.id) {
                        console.log(test);
                        $("#d-description").show();
                        $("#description").text(test.description);
                    }
                });
            });
         });

        $().ready(function() {

            $("#topic").change(function(event) {

                disable($("#submit-button"));
                $("#testDiv").hide();
                $("#d-description").hide();
                $("#spinner").show();

                $.ajax({
                    url: "/user/test-chooser",
                    type: "POST",
                    dataType: "json",
                    data: {
                        topicId: $(event.target).val(),
                        "${_csrf.parameterName}" : "${_csrf.token}"
                    },
                })
                .done(function (data) {
                    fillTestSelection(data);
                    tests = data.tests;
                    $("#test").trigger("change")
                })
                .fail(function (xhr, status, error) {
                    alert('Error\n' + xhr.responseText + '\n' + status + '\n' + error);
                });

            });

            $("#topic").trigger("change");

        });
    </script>
</body>
</html>
