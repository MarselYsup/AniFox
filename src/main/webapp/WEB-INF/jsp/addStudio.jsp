<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Add studio</title>




    <%@include file="/WEB-INF/jsp/components/links.jsp" %>

    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .anime-form{
            margin-left: auto;
            margin-right: auto;
            width: 50%;
        }


    </style>

</head>
<body class="text-center">
<%@include file="/WEB-INF/jsp/components/header.jsp" %>
<main class="container">
    <c:choose>
        <c:when test ="${message != null}">
            <div class="container">
                <div class="alert alert-success" role="alert">
                        ${message}
                </div>
            </div>
        </c:when>
    </c:choose>
    <form method="post" action="<c:url value="/add-studio"/>" class="anime-form" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="studioName" class="form-label">Название студии</label>
            <input type="text" class="form-control" id="studioName" name="name">
        </div>
        <div class="mb-3">
            <label for="formFileSm" class="form-label">Постер</label>
            <input class="form-control form-control-sm" type="file" id="formFileSm" name="file" accept="image/*" required>
        </div>
        <input class="btn btn-primary" type="submit" value="Сохранить">

    </form>

</main>



<%@include file="/WEB-INF/jsp/components/footer.jsp" %>


</body>
</html>
