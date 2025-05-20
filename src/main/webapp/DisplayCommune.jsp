<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.InfoCommune" %>
<html>
<head>
    <title>Licences par commune</title>
    <link rel ="stylesheet" href ="${pageContext.request.contextPath}/css/DisplayCommune.css">
</head>
<body>
    <h1 style="text-align:center;">Statistiques des licences par commune</h1>
    <div class="card-container">
        <%
            List<InfoCommune> infos = (List<InfoCommune>) request.getAttribute("infosCommune");
            for (InfoCommune info : infos) {
        %>
        <div class="card">
            <h3><%= info.getCommune() %></h3>
            <p><strong>Total :</strong> <%= info.getTotal() %></p>
            <p><strong>Hommes :</strong> <%= info.getTotalHomme() %></p>
            <p><strong>Femmes :</strong> <%= info.getTotalFemme() %></p>
        </div>
        <% } %>
    </div>
</body>
</html>