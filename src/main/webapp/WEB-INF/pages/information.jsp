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
                    <div class="col" style="width:400px">
                        <p style="margin: 0;"><b>Topic:</b> ${topic.name}</p>
                        <p style="margin: 0;"><b>Description:</b> ${topic.description}</p>
                    </div>
                    <div class="col">
                        <p style="margin: 0;">Tests:</p>
                        <c:forEach items="${topic.tests}" var="test">
                            <div class="row">
                                <p style="margin: 0;"><b>Name:</b> <i>${test.name}</i></p>
                                <p style="margin: 0;"><b>Description:</b> <i>${test.description}</i></p>
                            </div>
                            <br>
                        </c:forEach>
                    </div>
                </div>
            </c:forEach>
        </div>
    </body>

</html>
