<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>BillBoard - Annonces</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Billboard.css">
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
        
        <a href="Index.jsp" class="back-btn">Retour à l'accueil</a>
    </div>
</body>
</html>