<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Créer une annonce</title>
    <style>
        .form-container {
            width: 60%;
            margin: 30px auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        textarea {
            width: 100%;
            height: 100px;
            padding: 8px;
        }
        input[type="number"] {
            padding: 8px;
            width: 60px;
        }
        .btn-submit {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
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
    <a href="AnnouncesMenu.jsp" class="btn">Retour</a>
</body>
</html>