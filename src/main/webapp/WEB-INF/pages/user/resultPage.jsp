<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Результат</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
    <div class="container mt-5" style="max-width: 1000px">
        <div class="h3 row mb-3">
            <div class="display-6 col-sm">
                Тест: ${result.test.name}.
            </div>
        </div>
        <div class="display-6 mb-3">
            Результат: <b>${result.correctQuestionsCount}</b> из <b>${result.totalQuestionsCount}</b> : <b style="color: ${result.color};">${result.percentString}%</b>
        </div>
        <c:forEach items="${result.test.questions}" var="question">
            <div class="row mb-3">
                <button class="btn <c:out value="${result.isQuestionCorrect(question.questionId) ? 'btn-success' : 'btn-warning'}"/> btn-lg btn-block text-left mb-3" type="button" data-toggle="collapse" data-target="#collapse-${question.questionId}" aria-expanded="false" aria-controls="collapse-${question.questionId}">${question.description}</button>
                <div class="collapse" id="collapse-${question.questionId}">
                    <div class="card card-body">
                        <c:forEach items="${question.answers}" var="answers">
                            <div class="form-check mb-3">
                                <input
                                        class="form-check-input"
                                        type="checkbox"
                                        id="${answers.answerid}"
                                        <c:out value="${ result.markedAnswers.contains(answers.answerid) ? 'checked' : 'unchecked' }" />
                                        disabled
                                />
                                <label class="form-check-label" style="<c:out value="${ result.incorrectAnswers.contains(answers) ? (answers.correct == 1 ? 'color: green; font-weight: 600; text-decoration: underline' : 'color: red; font-weight: 600;') : (answers.correct == 1 ? 'color: green; font-weight: 600;' : '') }" />" for="${answers.answerid}">${answers.description}</label>
                            </div>
                        </c:forEach>
                        <c:if test="${!result.isQuestionCorrect(question.questionId)}">
                            <hr/>
                            <p>Информация по вопросу:</p>
                            <ol>
                                <c:forEach items="${question.literature}" var="literature">
                                    <li>
                                        <c:if test="${literature.links.size() == 1}">
                                            <a href="${literature.links.iterator().next().link}" target="_blank">${literature.description}</a>
                                        </c:if>
                                        <c:if test="${literature.links.size() > 1}">
                                            <a target="_blank">${literature.description}</a>
                                            <ul>
                                                <c:forEach items="${literature.links}" var="link" varStatus="status">
                                                    <li><a href="${link.link}" target="_blank">Ссылка №${status.index + 1}</a></li>
                                                </c:forEach>
                                            </ul>
                                        </c:if>
                                    </li>
                                </c:forEach>
                            </li>
                        </c:if>
                    </div>
                </div>
            </div>
        </c:forEach>
        <div class="row">
            <a href="/user/test-chooser" class="btn btn-primary btn-lg btn-block mx-auto" role="button" style="max-width: 150px">На главную</a>
        </div>
    </div>

</body>
</html>
