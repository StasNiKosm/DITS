<%--
  Created by IntelliJ IDEA.
  User: halkg
  Date: 20.06.2021
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>

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
                <form method="post" action="/user/testStart">

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

                    <div class="mb-3 row">
                        <label for="topic" class="col-sm-1 col-form-label">Тема</label>
                        <div class="col-sm-11">
                            <select id="topic" name="topicId" class="form-control" aria-label=".form-select-lg example">
                                <c:forEach items="${topics}" var="topic" varStatus="status">
                                    <option value="${topic.topicId}" <c:if test="${status.index == 0}">select</c:if> >
                                        [${topic.topicId}] : ${topic.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="d-flex justify-content-center">
                        <div id="spinner" class="spinner-border text-primary mb-3" role="status">
                            <span class="visually-hidden">Loading...</span>
                        </div>
                    </div>

                    <div id="testDiv" class="mb-3 row">
                        <label for="test" class="col-sm-1 col-form-label">Тест</label>
                        <div class="col-sm-11">
                            <select id="test" name="testId" class="form-control" aria-label=".form-select-sm example">

                            </select>
                        </div>
                    </div>

                    <div class="d-flex justify-content-between">
                        <a class="btn btn-secondary" role="button" href="/user" style="min-width:200px">Назад</a>
                        <button class="btn btn-primary" type="submit" style="min-width:200px">Выбрать</button>
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

            if (testList.tests.length === 0) {
                $("#test").append(new Option("В этой теме ещё нет тестов"));
                disable($("#test"));
            }

            $("#spinner").hide();
            $("#testDiv").show();
        }

        $().ready(function() {

            $("#topic").change(function(event) {

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
