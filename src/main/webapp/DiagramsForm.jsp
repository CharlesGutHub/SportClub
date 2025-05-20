<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Statistiques par commune</title>
</head>
<body>
    <h1>Consulter les statistiques des clubs</h1>
    <form method="get" action="DiagramsClubs">
        <label for="commune">Nom de la commune :</label>
        <input type="text" name="commune" required>
        <button type="submit">Afficher</button>
    </form>

    <c:if test="${not empty error}">
        <div style="color: red">${error}</div>
    </c:if>
</body>
</html>
