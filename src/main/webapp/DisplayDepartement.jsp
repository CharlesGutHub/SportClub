<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.InfoDepartement" %>
<html>
<head>
    <title>Licences par département</title>
    <style>
        .card-container {
            display: flex;
            flex-wrap: wrap;
            gap: 1em;
            margin: 20px;
        }
        .card {
            background-color: #f0f8e8;
            border-radius: 10px;
            padding: 1em;
            width: 250px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            font-family: Arial, sans-serif;
        }
        .card h3 {
            margin: 0 0 10px 0;
            color: #2c3e50;
        }
        .card p {
            margin: 5px 0;
        }
    </style>
</head>
<body>
    <h1 style="text-align:center;">Statistiques des licences par département</h1>
    <div class="card-container">
        <%
            List<InfoDepartement> infos = (List<InfoDepartement>) request.getAttribute("infosDepartement");
            for (InfoDepartement info : infos) {
        %>
        <div class="card">
            <h3><%= info.getDepartement() %></h3>
            <p><strong>Total :</strong> <%= info.getTotal() %></p>
            <p><strong>Hommes :</strong> <%= info.getTotalHomme() %></p>
            <p><strong>Femmes :</strong> <%= info.getTotalFemme() %></p>
        </div>
        <% } %>
    </div>
</body>
</html>
