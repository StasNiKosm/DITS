<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.07.2021
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Логин</th>
        <th>Роль</th>
        <th>Средний процент правильных ответов за все попытки
            <a href="<c:url value="/goHomeAdmin" />">delete</a>
            <a href="<c:url value="/goHomeAdmin" />">save</a>
        </th>
    </tr>
</table>
<a href="<c:url value="/goHomeAdmin" />">add</a>
<form action="/goHome" >
    <input type="submit" value="Home page">
</form>
<table border="1">
    <tr>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Логин</th>
        <th>Роль</th>
        <th>Средний процент правильных ответов за все попытки</th>
        <th>
            <a href="<c:url value="/goHomeAdmin" />">delete</a>
            <a href="<c:url value="/goHomeAdmin" />">save</a>
        </th>
    </tr>
</table>
<a href="<c:url value="/goHomeAdmin" />">add</a>
<form action="/goHome" >
    <input type="submit" value="Home page">
</form>




<table border="1">
    <tr>
        <th>Topics</th>
        <th>Tests</th>
    </tr>
    <c:forEach items="${topics}" var="topic">
        <tr >
            <td rowspan="${topic.tests.size() + 2}">
                <label class="form-label">Topic name</label> <br>
                <input disabled class="inputTopicName" value="${topic.name}" placeholder="Topic name" required> <br>
                <label class="form-label">Topic description</label> <br>
                <input disabled class="inputTopicName" value="${topic.description}" placeholder="Topic description" required> <br>
                <button class="editTopic">edit</button>
                <button hidden class="saveTopic">save</button>
                <button class="deleteTopic">delete</button>
            </td>
        </tr>
        <c:forEach items="${topic.tests}" var="test">
            <tr >
                <td>
                    <label class="form-label">Test name</label> <br>
                    <input disabled class="inputTestName" value="${test.name}" placeholder="Test name" required> <br>
                    <label class="form-label">Test description</label> <br>
                    <input name="" disabled class="inputTestName" value="${test.description}" placeholder="Test description" required> <br>
                    <button class="editTest">edit</button>
                    <button hidden class="saveTest">save</button>
                    <button class="deleteTest">delete</button>
                    <button class="deleteTest">continue to edit</button>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td>
                <button class="addTest">add test</button>
            </td>
        </tr>
    </c:forEach>
</table>
<button class="addTopic">add topic</button>
</body>
</html>
