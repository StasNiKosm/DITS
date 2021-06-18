<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <title>Login Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
    <div class="d-flex align-items-center" style="height:100%;">
        <div class="d-flex justify-content-center" style="width:100%">
            <c:url var="loginUrl" value="/login" />
            <form action="${loginUrl}" method="post">
                <c:if test="${param.error != null}">
                    <div class="alert alert-danger" role="alert">
                        Incorrect login or password
                    </div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div class="alert alert-primary" role="alert">
                        Successfully logout
                    </div>
                </c:if>
                <div class="mb-3">
                    <label for="loginInput" class="form-label">Login</label>
                    <input name="ssoId" type="text" class="form-control" id="loginInput" aria-describedby="emailHelp" required>
                </div>
                <div class="mb-3">
                    <label for="passwordInput" class="form-label">Password</label>
                    <input name="password" type="password" class="form-control" id="passwordInput" required>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

                <input id="submit-btn" type="submit" class="btn btn-primary" value="Submit"></in>
            </form>
        </div>
    </div>
</body>
</html>
