<%--
  Created by IntelliJ IDEA.
  User: пк
  Date: 28.10.2021
  Time: 0:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import=" ru.itis.anifox.models.enums.Role" %>






<header class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
    <img src="<c:url value="/img/fox.png"/>" width="80px" height="80px">
    <a class="navbar-brand" href="<c:url value="/homepage"/>">AniFox</a>

         <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/profile"/>">Profile</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/anime"/>">Anime</a>
            </li>
             <li class="nav-item">
                 <a class="nav-link" href="<c:url value="/anime-list"/>">MyAnimeList</a>
             </li>
            <c:choose>
             <c:when test ="${user != null}">
                 <c:choose>
                     <c:when test ="${user.getRole() eq Role.ADMIN}">
                         <li class="nav-item">
                             <a class="nav-link" href="<c:url value="/add-anime"/>">Add Anime</a>
                         </li>
                         <li class="nav-item">
                             <a class="nav-link" href="<c:url value="/add-studio"/>">Add studio</a>
                         </li>
                     </c:when>
                 </c:choose>
             </c:when>
            </c:choose>
        </ul>
        <c:choose>
            <c:when test ="${user == null}">
                    <div class="ms-auto">
                    <a class="btn btn-outline-success my-2 my-sm-0" href="<c:url value="/sign-in"/>">SignIn</a>

                    <a class="btn btn-outline-success my-2 my-sm-0" href="<c:url value="/sign-up"/>" >SignUp</a>
                    </div>
            </c:when>
            <c:otherwise>
                 <div class="ms-auto">
                    <a class="btn btn-outline-success my-2 my-sm-0" href="<c:url value="/logout"/>">Logout</a>
                </div>
            </c:otherwise>
        </c:choose>


</header>

