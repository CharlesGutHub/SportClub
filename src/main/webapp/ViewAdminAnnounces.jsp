<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Annonce" %>
<%
    List<Annonce> annonces = (List<Annonce>) request.getAttribute("annonces");
%>
<html>
<head>
    <title>Gestion des annonces</title>
    <link rel ="stylesheet" href ="${pageContext.request.contextPath}/css/ViewAdminAnnounces.css">
    
</head>
<body>
  <h1>Administration des annonces</h1>
  <div class="grid">
    <% if (annonces != null) {
         for (Annonce a : annonces) { %>
      <div class="card">
        <p><strong>ID:</strong> <%= a.getId() %></p>
        <p><strong>Message:</strong> <%= a.getMessage() %></p>
        <p><strong>Expire le:</strong> <%= a.getDateExpiration() %></p>
        <div class="actions">
          <!-- lien vers le formulaire de modification -->
          <a href="UpdateAnnouncePick?id=<%= a.getId() %>">✏️ Modifier</a>
          <!-- lien vers la suppression -->
          <a href="DeleteAnnounce?id=<%= a.getId() %>"
             onclick="return confirm('Supprimer l\'annonce #<%= a.getId() %>?');">
             🗑️ Supprimer
          </a>
        </div>
      </div>
    <%   }
       } %>
  </div>
</body>
</html>
