<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="models.InfoRegion"%>
<html>
<head>
<title>Licences par région</title>
<style>
.card-container {
	display: flex;
	flex-wrap: wrap;
	gap: 1em;
	margin: 20px;
}

.card {
	background-color: #e8f0fe;
	border-radius: 10px;
	padding: 1em;
	width: 250px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	font-family: Arial, sans-serif;
}

.card h3 {
	margin: 0 0 10px 0;
	color: #2c3e50;
}

.card p {
	margin: 5px 0;
}
</style>
</head>
<body>

	<form method="get" action="SearchRegion"
		style="text-align: center; margin: 20px;">
		<input type="text" name="query" placeholder="Rechercher une région" />
		<button type="submit">Rechercher</button>
	</form>

	<h1 style="text-align: center;">Statistiques des licences par
		région</h1>
	<div class="card-container">
		<%
            List<InfoRegion> infos = (List<InfoRegion>) request.getAttribute("infosRegion");
            for (InfoRegion info : infos) {
        %>
		<div class="card">
			<h3><%= info.getRegion() %></h3>
			<p>
				<strong>Total :</strong>
				<%= info.getTotal() %></p>
			<p>
				<strong>Hommes :</strong>
				<%= info.getTotalHomme() %></p>
			<p>
				<strong>Femmes :</strong>
				<%= info.getTotalFemme() %></p>
		</div>
		<% } %>
	</div>
</body>
</html>