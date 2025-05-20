<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.InfoDepartement" %>
<html>
<head>
    <title>Licences par département</title>
<link rel ="stylesheet" href ="${pageContext.request.contextPath}/css/DisplayDepartement.css">
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