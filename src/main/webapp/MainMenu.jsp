<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu Principal</title>
</head>
<body>
    <h1>ESPACE ELU(E)</h1>

    <form action="RankingByCommune" method="get">
        <label for="commune">Nom de la commune :</label>
        <input type="text" name="commune" id="commune" required />
        <input type="submit" value="Voir le Classement" />
    </form>

    <br><br>

    <form action="DisplayMenu.jsp" method="get">
        <input type="submit" value="Vers les affichages" />
    </form>
    <form action="DiagramsForm.jsp" method="get">
        <input type="submit" value="Vers les Statistiques" />
    </form>
    <form action="Index.jsp" method="get">
        <input type="submit" value="Deconnexion" />
    </form>
</body>
</html>
