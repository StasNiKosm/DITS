<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>

<html>
<head>
    <title>Статистика</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body class="d-flex h-100 text-center">
    <div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
        <div class="mt-5">
            <h5 class="display-6">
                Статистика ${user.login}
                <small id="description" class="text-muted">[${user.firstName} ${user.lastName}]</small>
            </h5>
        </div>
        <div class="px-3 mt-5 mb-auto d-flex justify-content-md-center">
            <div class="row w-100">
                <div class="row align-items-center">
                    <nav class="col-lg d-flex justify-content-md-center">
                        <ul class="pagination">
                            <li class="page-item ${cPage > 0 ? '' : 'disabled'}">
                                <a class="page-link" href="/user/statistic?page=${cPage - 1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <c:forEach items="${pages}" var="page">
                                <li class="page-item ${cPage == page ? 'active' : ''}"><a class="page-link" href="/user/statistic?page=${page}">${page + 1}</a></li>
                            </c:forEach>
                            <li class="page-item">
                                <a class="page-link" href="/user/statistic?page=${cPage + 1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
                <div class="row">
                    <div class="col-lg">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">Тест</th>
                                <th scope="col">Вопрос</th>
                                <th scope="col">Результат</th>
                                <th scope="col">Дата</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${statistic}" var="stat">
                            <tr>
                                <td>${stat.question.test.name}</td>
                                <td>${stat.question.description}</td>
                                <td>${stat.correct == 1 ? 'Правильно' : 'Неправильно'}</td>
                                <td>${stat.date}</td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="mt-3">
                    <a class="text-left btn btn-lg btn-secondary" role="button" href="/user">Назад</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
