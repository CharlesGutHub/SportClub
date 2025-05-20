<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestion des annonces</title>
        <link rel ="stylesheet" href ="${pageContext.request.contextPath}/css/AdminAnnouncementMenu.css">
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
