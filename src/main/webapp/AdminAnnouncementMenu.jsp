<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestion des annonces</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 2rem;
        }
        .actions {
            display: flex;
            gap: 1rem;
            margin-top: 2rem;
        }
        .actions form {
            display: inline;
        }
        .actions button {
            padding: 0.75rem 1.5rem;
            font-size: 1rem;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .btn-create {
            background-color: #28a745;
            color: white;
        }
        .btn-modify {
            background-color: #007bff;
            color: white;
        }
    </style>
</head>
<body>
    <h1>Administration des annonces</h1>
    <div class="actions">
        <!-- Bouton pour créer une nouvelle annonce -->
        <form action="CreateAnnounce.jsp" method="get">
            <button type="submit" class="btn-create">Créer une nouvelle annonce</button>
        </form>

        <!-- Bouton pour modifier une annonce :
             il invoque le servlet qui liste les annonces pour modification -->
        <form action="ShowAdminAnnounces" method="get">
            <button type="submit" class="btn-modify">Modifier une annonce</button>
        </form>
    </div>
</body>
</html>
