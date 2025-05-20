<%-- Définit le type de contenu et l'encodage de la page --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Importe les classes nécessaires : List pour la collection et Club pour le modèle de données --%>
<%@ page import="java.util.List" %>
<%@ page import="models.Club" %>
<%-- Récupère l'attribut 'clubs' de la requête et le caste en List<Club> --%>
<%
    List<Club> clubs = (List<Club>) request.getAttribute("clubs");
%>
<html>
<head>
    <title>Statistiques Clubs</title>
<link rel ="stylesheet" href ="${pageContext.request.contextPath}/css/clubStats.css">
</head>
<body>
    <h1 style="text-align:center;">Statistiques des clubs par commune</h1>
    <div class="container">
        <%-- Vérifie si la liste 'clubs' n'est pas nulle avant de l'itérer --%>
        <%
            if (clubs != null) {
                for (Club club : clubs) {
                    int hommes = club.getTotalHommes();
                    int femmes = club.getTotalFemmes();
                    /* Calcule le ratio hommes/femmes en évitant la division par zéro */
                    double ratioHF = (femmes == 0) ? 0 : ((double) hommes / femmes);
        %>
        <div class="card">
            <%-- Affiche le nom de la commune du club --%>
            <h2>Club de <%= club.getCommune() %></h2>
            <%-- Affiche le nombre total de licenciés --%>
            <div class="stat">👥 Total licenciés : <strong><%= club.getTotalLicencies() %></strong></div>
            <%-- Affiche le nombre d'hommes licenciés --%>
            <div class="stat">♂ Hommes : <strong><%= hommes %></strong></div>
            <%-- Affiche le nombre de femmes licenciées --%>
            <div class="stat">♀ Femmes : <strong><%= femmes %></strong></div>
            <%-- Affiche le ratio hommes/femmes formaté à deux décimales --%>
            <div class="stat">⚖ Ratio H/F : <strong><%= String.format("%.2f", ratioHF) %></strong></div>
        </div>
        <%
                }
            }
        %>
    </div>
</body>
</html>
