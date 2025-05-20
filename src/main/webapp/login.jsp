<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel ="stylesheet" href ="${pageContext.request.contextPath}/css/login.css">
</head>
<body>

<% String error = (String) request.getAttribute("error"); %>
<% if (error != null) { %>
    <div style="color:red; font-weight:bold; margin-bottom: 15px;">
        <%= error %>
    </div>
<% } %>

<form action="LogIn" method="post">
        <label for="email">Email :</label><br>
        <input type="email" id="email" name="email" required><br><br>

        <label for="mdp">Mot de passe :</label><br>
        <input type="password" id="mdp" name="mdp" required><br><br>
        
        <label>Rôle :</label><br>
        <input type="radio" id="elu" name="role" value="elu" required>
        <label for="elu">Élu</label><br>
        <input type="radio" id="acteur" name="role" value="acteur" required>
        <label for="acteur">Acteur du monde sportif</label><br><br>

        <input type="submit" value="Se connecter">
</form>

</body>
</html>