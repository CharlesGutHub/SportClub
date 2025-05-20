<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Créer une annonce</title>
        <link rel ="stylesheet" href ="${pageContext.request.contextPath}/css/CreateAnnounces.css">
</head>
<body>
    <div class="form-container">
        <h2>Nouvelle Annonce</h2>
        
        <form action="CreateAnnounce" method="post">
            <div class="form-group">
                <label for="message">Message :</label>
                <textarea id="message" name="message" required></textarea>
            </div>
            
            <div class="form-group">
                <label for="daysValid">Durée de validité (jours) :</label>
                <input type="number" id="daysValid" name="daysValid" min="1" value="7" required>
            </div>
            
            <button type="submit" class="btn-submit">Publier l'annonce</button>
        </form>
        
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>
    </div>
</body>
</html>