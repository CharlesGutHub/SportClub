<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tableau d'annonces</title>
    <style>
        .container {
            width: 80%;
            margin: 0 auto;
            text-align: center;
            padding-top: 50px;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            margin: 10px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Bienvenue sur le Tableau d'Annonces</h1>
        
        <a href="CreateAnnounces.jsp" class="btn">Créer une annonce</a>
        <a href="ViewAnnounces" class="btn">Visualiser les annonces</a>
    </div>
</body>
</html>