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
                <div class="display-6 mb-3">Выберите тему и тест:</div>
                <form method="post" action="/user/test-chooser">
                    <select id="topic" name="topicId" class="form-select form-select-lg mb-3" aria-label=".form-select-lg example">
                        <c:forEach items="${topics}" var="topic" varStatus="status">
                            <option value="${topic.topicId}" <c:if test="${status.index == 0}">select</c:if> >
                                [${topic.topicId}] : ${topic.name}
                            </option>
                        </c:forEach>
                    </select>
                    <select id="test" name="testId" class="form-select form-select-sm mb-3" aria-label=".form-select-sm example">
                        <option selected>Open this select menu</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                    </select>
                    <div class="d-flex justify-content-between">
                        <a class="btn btn-secondary" role="button" href="/user/test-chooser" style="min-width:200px">Назад</a>
                        <button class="btn btn-primary" type="submit" style="min-width:200px">Выбрать</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script>

        function fillTestSelection(testList) {
            for (let i = 0; i < testList.tests.length; i++) {
                alert(testList.tests[i].name + testList.tests[i].id);
            }
        }

        $().ready(function() {
            $("#topic").change(function(event) {
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
                  //alert('OK|\n' + data);
                    fillTestSelection(data);
                })
                .fail(function (xhr, status, error) {
                    alert('Error|\n' + xhr.responseText + '|\n' + status + '|\n' + error);
                });
            });
        });
    </script>
</body>
</html>
