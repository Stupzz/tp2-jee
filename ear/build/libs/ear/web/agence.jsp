<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion des backlogs</title>
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="index.html">Accueil</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="agence">Agence</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container">

    <h1>Agence : ${agence.nom}</h1>
    <br/>

    <c:choose>
    <c:when test="${agence.backlog == null}">
    <h4>Aucun backlog n'a été trouvé pour cette agence</h4>

    <h4>Créer un backlog</h4>
    <form action="backlog" method="post" class="form-group">
        <input type="hidden" name="idAgence" value="${agence.id}">
        Priorité : <input type="number" name="priorite" class="form-control">
        Estimation de temps (en heure) : <input type="number" name="estimation" class="form-control">
        Descriptif du backlog : <input type="text" name="description" class="form-control">
        <input class="btn btn-primary" type="submit" value="Creer le backlog">
    </form>
    </c:when>

    <c:otherwise>
    <h4>Priorité : ${agence.backlog.priorite} </h4>
    <h4>Ce backlog doit prendre environ ${agence.backlog.estimation} heure(s) pour être réalisé</h4>

    <h6>Date de création du backlog : </h6>
    <p>${agence.backlog.date}</p>

    <h6>Descriptif :</h6>
    <p>${agence.backlog.description}</p>

    <form action="userstory" method="get" class="form-group">
        <input type="hidden" name="id" value="${agence.backlog.id}">
        <input class="btn btn-primary" type="submit" value="Vers le Backlog !">
    </form>

    <form action="backlog" method="get" class="form-group">
        <input type="hidden" name="action" value="delete">
        <input type="hidden" name="idAgence" value="${agence.id}">
        <input class="btn btn-danger" type="submit" value="Supprime le backlog">
    </form>
    </c:otherwise>
    </c:choose>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

</body>
</html>