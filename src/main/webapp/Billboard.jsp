<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>BillBoard - Annonces</title>
    <style>
        .billboard {
            width: 80%;
            margin: 30px auto;
            font-family: Arial, sans-serif;
        }
        .announce {
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 15px;
            margin-bottom: 20px;
            background-color: #f9f9f9;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .announce-message {
            font-size: 18px;
            margin-bottom: 10px;
            color: #333;
        }
        .announce-date {
            font-size: 14px;
            color: #666;
            font-style: italic;
        }
        .back-btn {
            display: inline-block;
            padding: 8px 15px;
            background-color: #333;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            margin-top: 20px;
        }
        .error {
            color: red;
            padding: 10px;
            background-color: #ffeeee;
            border: 1px solid #ffcccc;
            border-radius: 4px;
        }
    </style>
</head>
<body>
    <div class="billboard">
        <h1>Annonces Actives</h1>
        
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        
        <c:choose>
            <c:when test="${empty annonces}">
                <p>Aucune annonce active pour le moment.</p>
            </c:when>
            <c:otherwise>
                <c:forEach items="${annonces}" var="annonce">
                    <div class="announce">
                        <div class="announce-message">${annonce.message}</div>
                        <div class="announce-date">
                            Publié le <fmt:formatDate value="${annonce.dateCreationAsDate}" pattern="dd/MM/yyyy à HH:mm"/> |
                            Valide jusqu'au <fmt:formatDate value="${annonce.dateExpirationAsDate}" pattern="dd/MM/yyyy à HH:mm"/>
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        
        <a href="index.jsp" class="back-btn">Retour à l'accueil</a>
    </div>
</body>
</html>