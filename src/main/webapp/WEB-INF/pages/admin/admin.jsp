<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>

<html>
<head>
    <title>Admin Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
<div class="container" width="100%">
    <div class="row">
        <div class="col-5">
            <div class="container-sm">
                <div class="display-6">
                    Администратор:
                    <small class="text-muted">${user.username}</small>
                </div>
                <div class="d-grid gap- d-md-flex justify-content-sm-start">
                    <a href="/logout" class="btn btn-outline-primary btn-sm" tabindex="-1" role="button" aria-disabled="true">/logout</a>
                </div>
            </div>
        </div>
        <div class="col" style="background-color: lightgray;">
            <div class="d-flex align-items-center" style="height:100%;">
                <div class="d-flex justify-content-center" style="width:100%">
                    <div class="alert alert-primary" role="alert">
                        Информация будет пявляться тут
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
