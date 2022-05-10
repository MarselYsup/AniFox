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
    <title>Add anime</title>




    <%@include file="/WEB-INF/jsp/components/links.jsp" %>


<style>
    .anime-form{
        margin-left: auto;
        margin-right: auto;
        width: 50%;
    }
    .brd {
        border: 4px double black; /* Параметры границы */
        padding: 10px; /* Поля вокруг текста */
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
        <form method="post" action="<c:url value="/add-anime"/>" class="anime-form" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="animeTitle" class="form-label">Название аниме</label>
                <input type="text" class="form-control" id="animeTitle" name="title">
            </div>
            <div class="mb-3">
                <input type="range" class="form-range" name = "yearOfCreation" value="2000" min="1980" max="2022" id="customRange2"oninput="this.nextElementSibling.value = this.value">
                Год создания:<output>2000</output>
            </div>
            <div class="mb-3">
                <label for="animeEpisodes" class="form-label">Количество эпизодов</label>
                <input type="number" min="1" max="5000" class="form-control" id="animeEpisodes" name="countOfEpisodes">
            </div>
            <div class="mb-3">
                <label for="animeTitle" class="form-label">Ссылка на Youtube видео</label>
                <input type="text" class="form-control" id="animeUrl" name="youtubeUrl">
            </div>
            <div class="mb-3">
                <label for="animeDescription" class="form-label">Описание</label>
                <textarea class="form-control" id="animeDescription" rows="3" name="description"></textarea>
            </div>
            <div>Жанры</div>
            <div class ="brd">
            <c:forEach var="genre" items="${genres}">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" name="genres"type="checkbox" id="inlineCheckbox1" value="${genre.getGenre()}">
                    <label class="form-check-label" for="inlineCheckbox1">${genre.getGenre()}</label>
                </div>
            </c:forEach>
            </div>
            <div class="mb-3">
                <label for="formFileSm" class="form-label">Постер</label>
                <input class="form-control form-control-sm" type="file" id="formFileSm" name="file" accept="image/*" required>
            </div>
            <div>Студия</div>
            <div class ="brd">
                <c:forEach var="studio" items="${studios}">
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="studioTitle" id="inlineRadio1" value="${studio.getName()}">
                        <label class="form-check-label" for="inlineRadio1">${studio.getName()}</label>
                    </div>
                </c:forEach>
            </div>
            <br>
            <br>
            <input class="btn btn-primary" type="submit" value="Сохранить">

        </form>

    </main>



    <%@include file="/WEB-INF/jsp/components/footer.jsp" %>


</body>
</html>
