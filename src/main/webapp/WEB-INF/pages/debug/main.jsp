<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <title>main.jsp</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
    <body>
        <h1>Hello from Controller! <a href="/index.jsp">Go back</a>.</h1>
        <div class="container-sm">
            <ol>
                <c:forEach var="listItem" items="${list}">
                <li>
                    <c:out value="${listItem.name.toString()}"/>
                    <ul>
                        <c:forEach var="testItem" items="${listItem.tests}">
                            <li>
                                ${testItem.name}
                            </li>
                        </c:forEach>
                    </ul>
                </li>
                </c:forEach>
            </ol>
        </div>
    </body>
</html>
