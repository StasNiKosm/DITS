<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
    <head>
        <title>Информация о топиках</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <a href="/">Назад</a>
            </div>
            <c:forEach items="${topic_list}" var="topic">
                <div class="row" style="border: 2px solid black;">
                    <div class="col">
                        <p style="margin: 0;"><b>ID:</b> ${topic.topicId}</p>
                        <p style="margin: 0;"><b>Topic:</b> ${topic.name}</p>
                        <p style="margin: 0;"><b>Description:</b> ${topic.description}</p>
                    </div>
                    <div class="col">
                        <p style="margin: 10px;"><b>Tests: </b><c:if test="${topic.tests.size() == 0}">Нет тестов</c:if></p>
                        <c:forEach items="${topic.tests}" var="test">
                            <div class="row" style="border: 2px solid black;">
                                <p style="margin: 0;"><b>Name:</b> <i>${test.name}</i></p>
                                <p style="margin: 0;"><b>Description:</b> <i>${test.description}</i></p>
                                <p style="margin: 0;"><b>Questions: </b><c:if test="${test.questions.size() == 0}">Нет вопросов</c:if></p>
                                <div class="row">
                                    <c:forEach items="${test.questions}" var="question">
                                        <p style="margin: 0 0 0 20px;"><b>Description:</b> <i>${question.description}</i></p>
                                        <p style="margin: 0 0 0 20px;"><b>Literature: </b><c:if test="${question.literature.size() == 0}">Нет литературы</c:if></p>
                                        <c:forEach items="${question.literature}" var="literature">
                                            <div class="row">
                                                <p style="margin: 0 0 0 40px;"><b>Name:</b> <i>${literature.description}</i></p>
                                                <p style="margin: 0 0 0 40px;"><b>Links: </b><c:if test="${literature.links.size() == 0}">Нет ссылок</c:if></p>
                                                <c:forEach items="${literature.links}" var="url">
                                                    <p style="margin: 0 0 0 60px;">${url.link}</p>
                                                </c:forEach>
                                            </div>
                                        </c:forEach>
                                        <p style="margin: 0 0 0 20px;"><b>Statistics: </b><c:if test="${question.statistic.size() == 0}">Нет статистики</c:if></p>
                                        <c:forEach items="${question.statistic}" var="statistic">
                                            <div class="row">
                                                <p style="margin: 0 0 0 40px;"><b>user:</b> <i>${statistic.user.login}</i></p>
                                                <p style="margin: 0 0 0 40px;"><b>correct: </b>
                                                    <c:if test="${statistic.correct == 0}">false</c:if>
                                                    <c:if test="${statistic.correct == 1}">true</c:if>
                                                </p>
                                            </div>
                                        </c:forEach>
                                        <br/>
                                    </c:forEach>
                                </div>
                            </div>
                            <br>
                        </c:forEach>
                    </div>
                </div>
            </c:forEach>
        </div>
    </body>

</html>
