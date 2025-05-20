<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Classement des clubs dans la commune</title>
    <style>
        table {
            width: 80%;
            margin: 30px auto;
            border-collapse: collapse;
            font-family: Arial, sans-serif;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: center;
        }
        th {
            background-color: #333;
            color: white;
        }
        .back-btn {
            display: block;
            width: fit-content;
            margin: 20px auto;
            padding: 10px 20px;
            background-color: #333;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .error {
            color: red;
            text-align: center;
            margin: 20px;
        }
    </style>
</head>
<body>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <c:if test="${not empty classement}">
        <h2 style="text-align:center;">Classement des clubs dans la commune : ${commune}</h2>
        <table>
            <tr>
                <th>Nom du Club</th>
                <th>Fédération</th>
                <th>Total Licenciés</th>
                <th>Hommes</th>
                <th>Femmes</th>
            </tr>
            <c:forEach var="club" items="${classement}">
                <tr>
                    <td>${club.nomClub}</td>
                    <td>${club.federation}</td>
                    <td>${club.totalLicencies}</td>
                    <td>${club.totalHommes}</td>
                    <td>${club.totalFemmes}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <a class="back-btn" href="MainMenu.jsp">Retour au menu principal</a>
</body>
</html>
