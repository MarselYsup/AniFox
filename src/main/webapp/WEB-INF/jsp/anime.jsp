<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Anime</title>


    <%@include file="/WEB-INF/jsp/components/links.jsp" %>

    <style>
        .form-select{
            margin-right: auto;
            margin-left: auto;
        }

    </style>
    <script src="<c:url value="/js/jquery-2.2.1.min.js"/>"></script>
    <script>
        $(document).ready(function() {
            const anime = window.location.pathname.split("/")[2]
            let form = $("#my-form")
            $(document).on('submit', '#my-form', function() {
                let stat = form.find("#status").val();
                $.ajax("<c:url value="/anime-list"/>",{
                    method: "POST",
                    data: JSON.stringify({status: stat, animeId: anime}),
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    success:function (data){
                        var element = document.getElementById("stat");
                        if(document.getElementById('st')){
                            var removedEl= document.getElementById('st');
                            removedEl.parentNode.removeChild(removedEl);
                        }
                        if(stat==1) {


                            var text = document.createTextNode("Смотрю");
                            var tag = document.createElement("div");
                            tag.className ="alert alert-primary";
                            tag.id = "st";
                            tag.appendChild(text);
                            element.appendChild(tag);
                        }
                        else if(stat==2){
                            var text = document.createTextNode("Просмотрено");
                            var tag = document.createElement("div");
                            tag.className ="alert alert-success";
                            tag.id = "st";
                            tag.appendChild(text);
                            element.appendChild(tag);
                        }
                        else if (stat==3){
                            var text = document.createTextNode("Буду смотреть");
                            var tag = document.createElement("div");
                            tag.className ="alert alert-warning";
                            tag.id = "st";
                            tag.appendChild(text);
                            element.appendChild(tag);
                        }
                    }
                })
                return false;
            });
        });
    </script>


</head>
<body >
<%@include file="/WEB-INF/jsp/components/header.jsp" %>

<main class="text-center">
    <div class="container">
        <h2>${anime.getTitle()}</h2>
        <div class="row align-items-start">
            <div class="col">
               <img src="<c:url value="/file/${anime.getAvatar()}"/>" class="anime-img">
                <form method="post" action="#"id="my-form">
                    <select class="form-select" id = "status">
                        <option value="1">Смотрю</option>
                        <option value="2">Просмотрено</option>
                        <option value="3">Буду смотреть</option>
                    </select>
                    <input class="btn btn-dark" id="btn" type="submit" value="Добавить">
                </form>
                <h2>Рейтинг: ${anime.getRating()}</h2>
                <div id="stat">
                    <c:choose>
                        <c:when test="${status==1}">
                            <div class="alert alert-primary" id="st">Смотрю</div>
                        </c:when>
                        <c:when test="${status==2}">
                            <div class="alert alert-success" id="st">Просмотрено</div>
                        </c:when>
                        <c:when test="${status==3}">
                            <div class="alert alert-warning" id="st">Буду смотреть</div>
                        </c:when>
                    </c:choose>
                </div>
            </div>
            <div class="col">
                <div class="alert alert-dark" role="alert">Информация</div>
                <p><strong>Название:</strong> ${anime.getTitle()}</p>
                <p><strong>Эпизоды:</strong> ${anime.getCountOfEpisodes()}</p>
                <p><strong>Год выхода</strong> ${anime.getYearOfCreation()}</p>
                <p><strong>Жанры:</strong> <c:forEach var="genre" items="${anime.getGenres()}">${genre.getGenre()} </c:forEach></p>

            </div>
            <div class="col">
                <div class="alert alert-dark" role="alert">Студия</div>
                <img src="<c:url value="/file/${anime.getStudio().getAvatar()}"/>" class="anime-img" width="300" height="80">
                <p class="text-center">${anime.getStudio().getName()}</p>
            </div>
        </div>
        <div class="row">
            <div class="col align-self-center">
                <div class="alert alert-dark" role="alert">Описание</div>
                <p>${anime.getDescription()}</p>
            </div>
        </div>
        <div class="row">
            <div class="col align-self-center">
                <div class="alert alert-dark" role="alert">Видео</div>
                <iframe width="800" height="400"  src="${anime.getYoutubeUrl()}">
                </iframe>
            </div>
        </div>
    </div>

</main>

<%@include file="/WEB-INF/jsp/components/footer.jsp" %>

</body>
</html>
