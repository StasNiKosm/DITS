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
        <script>
            const count_of_questions = ${questions.size()};
        </script>
        <div class="d-flex align-items-center" style="height:100%;">
            <div class="d-flex justify-content-center mt-5" style="width:100%">
                <div class="container" style="max-width: 600px">
                    <div class="mb-3">
                        <span class="h3">§ ${testName}</span>
                        <hr>
                    </div>
                    <form method="post" action="/user/test?testId=${testId}">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <c:forEach items="${questions}" var="question" varStatus="status">
                            <div id="question-${status.index}" class="mb-3" style="display: none;">
                                <div class="blockquote mb-3">
                                    <p>№${status.index + 1}. <em>${question.description}</em></p>
                                </div>
                                <c:forEach items="${question.answers}" var="answer">
                                    <c:set var="answerId" value="answer-id-${answer.answerid}" />
                                    <div class="form-check mb-3">
                                        <input class="form-check-input" type="checkbox" name="${answerId}" id="${answerId}" />
                                        <label class="form-check-label" for="${answerId}">
                                            ${answer.description}
                                        </label>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:forEach>
                        <div class="row">
                            <div class="col d-flex justify-content-end">
                                <button id="next-question" class="btn btn-primary" type="button" style="max-width:200px">Следующий</button>
                                <button id="submit" class="btn btn-primary" type="submit" style="max-width:200px; display: none;">Зкончить</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
    <script>

        let cIndex = 0;

        jQuery(function() {

            $("#next-question").on("click", function (event) {
                $("#question-"+cIndex).show();
                if (cIndex !== 0)
                    $("#question-"+(cIndex-1)).hide();
                cIndex++;
                $("#qNumber-"+cIndex).text(cIndex);
                if (cIndex === count_of_questions) {
                    $("#next-question").hide();
                    $("#submit").show();
                }
            });

            $("#next-question").trigger("click");

        });

    </script>
</html>
