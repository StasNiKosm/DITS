<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Выбор теста и темы</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
    <div class="d-flex align-items-center" style="height:100%;">
        <div class="d-flex justify-content-center" style="width:100%">
            <div class="container" style="max-width: 600px">
                <c:if test="${param.error != null}">
                    <div class="alert alert-primary" role="alert">
                        Что-то пошло не так. Повторите попытку.
                    </div>
                </c:if>
                <form method="post" action="/user/testStart">

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

                    <div class="mb-3 row">
                        <div class="col-sm form-floating">
                            <select id="topic" name="topicId" class="form-select form-select-sm">
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
                        <div class="col-sm form-floating">
                            <select id="test" name="testId" class="form-select form-select-sm">

                            </select>
                            <label for="test" style="margin-left: 10px">Выберите тест из списка</label>
                        </div>
                    </div>

                    <div class="d-grid gap-2 d-md-flex justify-content-md-between">
                        <a class="btn btn-secondary" role="button" href="/user" style="min-width:200px">Назад</a>
                        <button id="submit-button" class="btn btn-primary" type="submit" style="min-width:200px">Выбрать</button>
                    </div>

                </form>
            </div>
        </div>
    </div>
    <script>

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

            $("#topic").change(function(event) {

                disable($("#submit-button"));
                $("#testDiv").hide();
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
