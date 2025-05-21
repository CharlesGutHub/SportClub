<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="jakarta.servlet.http.Cookie" %>
<%
    // Si l’utilisateur est déjà en session, on le redirige
    if (session.getAttribute("email") != null) {
        response.sendRedirect(request.getContextPath() + "/recherche.jsp");
        return;
    }

    // Lecture des cookies pour pré-remplissage
    String emailPrefill = "";
    String rolePrefill  = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie c : cookies) {
            if ("email".equals(c.getName())) {
                emailPrefill = c.getValue();
            }
            if ("role".equals(c.getName())) {
                rolePrefill = c.getValue();
            }
        }
    }

    // Message d’erreur éventuel passé en attribut
    String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login</title>
</head>
<body>

  <% if (error != null) { %>
    <div style="color:red; font-weight:bold; margin-bottom:15px;">
      <%= error %>
    </div>
  <% } %>

  <form action="LogIn" method="post">
    <label for="email">Email :</label><br>
    <input type="email" id="email" name="email"
           value="<%= emailPrefill %>" required><br><br>

    <label for="mdp">Mot de passe :</label><br>
    <input type="password" id="mdp" name="mdp" required><br><br>
    
    <label>Rôle :</label><br>
    <input type="radio" id="elu" name="role" value="elu"
           <%= "elu".equals(rolePrefill) ? "checked" : "" %> required>
    <label for="elu">Élu</label><br>
    <input type="radio" id="acteur" name="role" value="acteur"
           <%= "acteur".equals(rolePrefill) ? "checked" : "" %> required>
    <label for="acteur">Acteur du monde sportif</label><br><br>

    <input type="submit" value="Se connecter">
  </form>

</body>
</html>
