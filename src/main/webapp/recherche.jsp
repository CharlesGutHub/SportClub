<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

    
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Recherche zone</title>
</head>
<body>

<form action="Recherche" method="get">
  <label for="zoneGeoType">Type de zone :</label>
  <select id="zoneGeoType" name="zoneGeoType" onchange="changerListe()">
    <option value="region">Région</option>
    <option value="departement">Département</option>
  </select><br><br>

  <label for="zoneGeo">Zone :</label>
  <input list="zoneOptions" id="zoneGeo" name="zoneGeo">
  <datalist id="zoneOptions"></datalist>

  <br><br>
  <input type="submit" value="Rechercher">
</form>
<p>Nombre de résultats : ${listClub.size()}</p>

<table>
	<thead>
		<tr>
			<th>Code Commune Insee</th>
			<th>Libelle</th>
			<th>Nom de fédération</th>
			<th>Département</th>
			<th>Région</th>
			<th>Nombre de licencié</th>
			<th>Nombre de licencié femme</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="club" items="${listClub}">
		<tr>
			<td>${club.codeCommune}</td>
			<td>${club.libelle}</td>
			<td>${club.nomFed}</td>
			<td>${club.departement}</td>
			<td>${club.region}</td>
			<td>${club.total2022}</td>
			<td>${club.totalFemmes2022}</td>		
		</tr>	
	</c:forEach>
	</tbody>

</table>


<script>
const regions = [
  "Auvergne-Rhône-Alpes", "Bourgogne-Franche-Comté", "Bretagne", "Centre-Val de Loire", "Corse",
  "Grand Est", "Hauts-de-France", "Île-de-France", "Normandie", "Nouvelle-Aquitaine",
  "Occitanie", "Pays de la Loire", "Provence-Alpes-Côte d'Azur",
  "Guadeloupe", "Martinique", "Guyane", "La Réunion", "Mayotte"
];

const departements = [
  "Ain", "Aisne", "Allier", "Alpes-de-Haute-Provence", "Hautes-Alpes", "Alpes-Maritimes", "Ardèche",
  "Ardennes", "Ariège", "Aube", "Aude", "Aveyron", "Bouches-du-Rhône", "Calvados", "Cantal",
  "Charente", "Charente-Maritime", "Cher", "Corrèze", "Corse-du-Sud", "Haute-Corse", "Côte-d'Or",
  "Côtes-d'Armor", "Creuse", "Dordogne", "Doubs", "Drôme", "Eure", "Eure-et-Loir", "Finistère", "Gard",
  "Haute-Garonne", "Gers", "Gironde", "Hérault", "Ille-et-Vilaine", "Indre", "Indre-et-Loire", "Isère",
  "Jura", "Landes", "Loir-et-Cher", "Loire", "Haute-Loire", "Loire-Atlantique", "Loiret", "Lot",
  "Lot-et-Garonne", "Lozère", "Maine-et-Loire", "Manche", "Marne", "Haute-Marne", "Mayenne",
  "Meurthe-et-Moselle", "Meuse", "Morbihan", "Moselle", "Nièvre", "Nord", "Oise", "Orne", "Pas-de-Calais",
  "Puy-de-Dôme", "Pyrénées-Atlantiques", "Hautes-Pyrénées", "Pyrénées-Orientales", "Bas-Rhin",
  "Haut-Rhin", "Rhône", "Haute-Saône", "Saône-et-Loire", "Sarthe", "Savoie", "Haute-Savoie", "Paris",
  "Seine-Maritime", "Seine-et-Marne", "Yvelines", "Deux-Sèvres", "Somme", "Tarn", "Tarn-et-Garonne",
  "Var", "Vaucluse", "Vendée", "Vienne", "Haute-Vienne", "Vosges", "Yonne", "Territoire de Belfort",
  "Essonne", "Hauts-de-Seine", "Seine-Saint-Denis", "Val-de-Marne", "Val-d'Oise",
  "Guadeloupe", "Martinique", "Guyane", "La Réunion"
  // Mayotte volontairement exclue
];

function changerListe() {
  const type = document.getElementById("zoneGeoType").value;
  const datalist = document.getElementById("zoneOptions");

  // Supprimer les options existantes
  datalist.innerHTML = "";

  const options = (type === "region") ? regions : departements;

  for (const item of options) {
    const option = document.createElement("option");
    option.value = item;
    datalist.appendChild(option);
  }
}

// Appel initial (par défaut "region")
changerListe();
</script>

</body>
</html>
