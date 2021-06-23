<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Тест: ${testName}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
    <body>
        <div class="d-flex align-items-center" style="height:100%;">
            <div class="d-flex justify-content-center mt-5" style="width:100%">
                <div class="container" style="max-width: 600px">
                    <div class="h4 mb-3">
                        ${testName}
                    </div>
                    <c:forEach items="${questions}" var="question" varStatus="status">
                        <div id="question-${status.index}">
                            <div class="blockquote mb-3">
                                ${question.description}
                            </div>
                            <c:forEach items="${question.answers}" var="answer">
                                <c:set var="answerId" value="answer-id-${answer.answerid}" />
                                <div class="form-check mb-3">
                                    <input class="form-check-input" type="checkbox" value="" id="${answerId}">
                                    <label class="form-check-label" for="${answerId}">
                                        ${answer.description}
                                    </label>
                                </div>
                            </c:forEach>
                        </div>
                    </c:forEach>
                    <div class="row">
                        <div class="col-sm d-flex justify-content-end">
                            <button id="next-question" class="btn btn-primary" type="submit" style="max-width:200px">Следующий</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
