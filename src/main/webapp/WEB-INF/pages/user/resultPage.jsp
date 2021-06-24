<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Результат</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
    <div class="container mt-1">
        <div class="h3 row mb-3">
            <div class="col-sm-9">
                ${result.test.name}
            </div>
            <div class="col-sm-3 row align-items-center justify-content-end">
                <a href="/user/test-chooser" class="btn btn-primary btn-lg active mx-auto" role="button" style="max-width: 150px">На главную</a>
            </div>
        </div>
        <div class="row">
            <div class="col-sm">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Вопрос</th>
                        <th scope="col">Ответ</th>
                        <th scope="col">Результат</th>
                        <th scope="col">Литература</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${result.incorrectAnswers}" var="answer">
                            <tr>
                                <td>${answer.question.description}...</td>
                                <td>${answer.description}</td>
                                <td>
                                    <c:if test="${answer.correct == 1}">
                                        Верный, но не был отмечен.
                                    </c:if>
                                    <c:if test="${answer.correct == 0}">
                                        Неверный, но был отмечен.
                                    </c:if>
                                </td>
                                <td>
                                    <c:forEach items="${result.literature}" var="loaded_lit">
                                        <c:if test="${answer.question.questionId == loaded_lit.question.questionId }">
                                            ${loaded_lit.description}
                                            <ul>
                                                <c:forEach items="${loaded_lit.links}" var="link" varStatus="status">
                                                    <li><a target="_blank" href="${link.link}">Ссылка-${status.index + 1}</a></li>
                                                </c:forEach>
                                            </ul>
                                        </c:if>
                                    </c:forEach>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
