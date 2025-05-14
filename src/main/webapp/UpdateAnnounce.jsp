<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.Annonce" %>
<%@ page import="java.util.Date, java.text.SimpleDateFormat" %>
<%
  Annonce a = (Annonce) request.getAttribute("annonce");
  // Préparer la valeur pour <input type="datetime-local">
  Date exp = a.getDateExpirationAsDate();
  SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
  String expVal = fmt.format(exp);
%>
<html>
<head><title>Modifier annonce #<%= a.getId() %></title></head>
<body>
  <h1>Modifier annonce #<%= a.getId() %></h1>
  <form action="UpdateAnnounce" method="post">
    <input type="hidden" name="id" value="<%= a.getId() %>"/>
    <div>
      <label>Message:<br/>
        <textarea name="message" rows="4" cols="50"><%= a.getMessage() %></textarea>
      </label>
    </div>
    <div>
      <label>Date d'expiration:<br/>
        <input type="datetime-local" name="dateExpiration" value="<%= expVal %>"/>
      </label>
    </div>
    <div>
      <button type="submit">Enregistrer</button>
      <a href="view-announces">Annuler</a>
    </div>
  </form>
</body>
</html>
