<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Statistiques par Club</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <h1>Statistiques des clubs</h1>
    <h2>Statistiques pour la commune : <c:out value="${commune}" /></h2>

    <c:forEach var="club" items="${stats}">
        
        <canvas id="chart-${fn:replace(club.nomClub, ' ', '')}" width="400" height="200"></canvas>

        <script>
            document.addEventListener('DOMContentLoaded', function () {
                const ctx = document.getElementById("chart-${fn:replace(club.nomClub, ' ', '')}").getContext('2d');
                new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: ['Hommes', 'Femmes', 'Jeunes', 'Total Licenciés'],
                        datasets: [{
                            label: 'Données du club',
                            data: [
                                ${club.totalHommes},
                                ${club.totalFemmes},
                                ${club.totalJeunes},
                                ${club.totalLicencies}
                            ],
                            backgroundColor: ['#3498db', '#e74c3c', '#2ecc71', '#9b59b6']
                        }]
                    },
                    options: {
                        responsive: true,
                        plugins: {
                            title: {
                                display: true,
                                text: 'Statistiques pour ${club.nomClub}'
                            }
                        },
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
            });
        </script>
    </c:forEach>

    <br><a href="MainMenu.jsp">Retour au menu</a>
</body>
</html>
