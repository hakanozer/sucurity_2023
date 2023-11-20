<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="tr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, admin-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
    <div class="row">
        <div class="col-sm-4"></div>
        <div class="col-sm-4">
            <h2>User Login</h2>
            <c:out value="${status}"></c:out>
            <c:if test="${errors != null}">
                <c:forEach var="item" items="${errors}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <strong><c:out value="${item.getField()}"></c:out>!</strong> <c:out value="${item.getDefaultMessage()}"></c:out>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:forEach>
            </c:if>
            <form action="/login" method="post">
                <div class="mb-3">
                    <input required name="email" type="email" class="form-control" placeholder="E-Mail" />
                </div>
                <div class="mb-3">
                    <input required name="password" type="password" class="form-control" placeholder="Password" />
                </div>
                <div class="mb-3">
                    <select required name="status" class="form-select">
                        <option value="user">User</option>
                        <option value="admin">Admin</option>
                    </select>
                </div>
                <div class="mb-3">
                    <button class="btn btn-success">Login</button>
                </div>
            </form>
        </div>
        <div class="col-sm-4"></div>
    </div>
</body>
</html>