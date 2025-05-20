<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>


    
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <!--  CSS leaflet -->
  <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css"
     integrity="sha256-p4NxAoJBhIIN+hmNHrzRCf9tD/miZyoHS5obTRR9BMY="
     crossorigin=""/>
     <!-- Make sure you put this AFTER Leaflet's CSS -->
 <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"
     integrity="sha256-20nQCchB9co0qIjJZRGuk2/Z9VM+kNiyxNV1lvTlZBo="
     crossorigin=""></script>
<!-- Leaflet MarkerCluster plugin -->
<link rel="stylesheet" href="https://unpkg.com/leaflet.markercluster@1.5.3/dist/MarkerCluster.css" />
<link rel="stylesheet" href="https://unpkg.com/leaflet.markercluster@1.5.3/dist/MarkerCluster.Default.css" />
<script src="https://unpkg.com/leaflet.markercluster@1.5.3/dist/leaflet.markercluster.js"></script>

     
     
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
		<select id="zoneGeo" name="zoneGeo" onchange="validateSelect()">
		  <option value="">Sélectionnez une zone</option>
		</select>
		<br><br>
		<label for="fed">Sport :</label>
		<input type="text" id="sport" name="sport"/>
	
	  <br><br>
	  <input type="hidden" name="page" value="${page}" />
	  <input type="submit" id="boutonRecherche" value="Rechercher" disabled>
	</form>
	
	
	<p>Nombre de résultats : ${listClub.size()}</p>
	
	<div class="tabs">
	  <button class="tab-button" onclick="showTab('tableau')">Affichage Tableau</button>
	  <button class="tab-button" onclick="showTab('carte')">Affichage Carte</button>
	  <c:if test="${role == 'elu'}">
	    <button class="tab-button" onclick="showTab('graph')">Graphique</button>
	  </c:if>
	</div>
	
	<div id="tableau" class="tab-content">
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
	</div>
	
	
	<!-- Initialisation de la carte-->
	<div id="carte" class="tab-content" style="display: none;">
	  <div id="map" style="height: 680px; width: 80%;margin-left:75px"></div>
	</div>
	<div id="graph" class="tab-content" style="display: none;">
		<canvas id="chart" style="height: 680px; width: 80%;margin-left:75px"></canvas>
	</div>
	
	
	
	<c:if test="${page > 1}">
	  	<a href="Recherche?zoneGeoType=${zoneGeoType}&zoneGeo=${zoneGeo}&page=${page - 1}">Précédent</a>
	</c:if>
	
		<a href="Recherche?zoneGeoType=${zoneGeoType}&zoneGeo=${zoneGeo}&page=${page + 1}">Suivant</a>

		
	<script>
	
	var map = L.map('map').setView([${listClub[0].latitude}, ${listClub[0].longitude}], 9); //Initialisation à la première ville de la liste
	var tableauMarqueurs = [];
	
	//Add JOSM layer to the map
	L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
	    maxZoom: 19,
	    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
	}).addTo(map);
	
	var markers = L.markerClusterGroup();

	<c:forEach var="club" items="${listClub}">
	  var marker = L.marker([${club.latitude}, ${club.longitude}])
	    .bindPopup(`
	      <div>
	        <strong>${club.libelle}</strong><br/>
	        Fédération : ${club.nomFed}<br/>
	        Département : ${club.departement}<br/>
	        Région : ${club.region}<br/>
	        Licenciés : ${club.total2022}<br/>
	        Femmes : ${club.totalFemmes2022}
	      </div>
	    `);

	  markers.addLayer(marker);
	</c:forEach>

	map.addLayer(markers);
  		var groupe = new L.featureGroup(tableauMarqueurs);
	</script>
	
	<!-- Gestion des graphiques pour l'élu -->
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	
	<script>
	  const ctx = document.getElementById('chart');//exemple de graphique à adapter
	
	  new Chart(ctx, {
	    type: 'bar',
	    data: {
	      labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
	      datasets: [{
	        label: '# of Votes',
	        data: [12, 19, 3, 5, 2, 3],
	        borderWidth: 1
	      }]
	    },
	    options: {
	      scales: {
	        y: {
	          beginAtZero: true
	        }
	      }
	    }
	  });
	</script>
	
	<script>
	const regions = [
	  "Auvergne-Rhône-Alpes", "Bourgogne-Franche-Comté", "Bretagne", "Centre-Val de Loire", "Corse",
	  "Grand Est", "Hauts-de-France", "Île-de-France", "Normandie", "Nouvelle-Aquitaine",
	  "Occitanie", "Pays de la Loire", "Provence-Alpes-Côte d'Azur",
	  "Guadeloupe", "Martinique", "Guyane", "La Réunion"
	];
	
	const departements = [
		"01–Ain", "02–Aisne", "03–Allier", "04–Alpes-de-Haute-Provence", "05–Hautes-Alpes", "06–Alpes-Maritimes", 
	    "07–Ardèche", "08–Ardennes", "09–Ariège", "10–Aube", "11–Aude", "12–Aveyron", "13–Bouches-du-Rhône", 
	    "14–Calvados", "15–Cantal", "16–Charente", "17–Charente-Maritime", "18–Cher", "19–Corrèze", "2A–Corse-du-Sud", 
	    "2B–Haute-Corse", "21–Côte-d'Or", "22–Côtes-d'Armor", "23–Creuse", "24–Dordogne", "25–Doubs", "26–Drôme", 
	    "27–Eure", "28–Eure-et-Loir", "29–Finistère", "30–Gard", "31–Haute-Garonne", "32–Gers", "33–Gironde", 
	    "34–Hérault", "35–Ille-et-Vilaine", "36–Indre", "37–Indre-et-Loire", "38–Isère", "39–Jura", "40–Landes", 
	    "41–Loir-et-Cher", "42–Loire", "43–Haute-Loire", "44–Loire-Atlantique", "45–Loiret", "46–Lot", 
	    "47–Lot-et-Garonne", "48–Lozère", "49–Maine-et-Loire", "50–Manche", "51–Marne", "52–Haute-Marne", 
	    "53–Mayenne", "54–Meurthe-et-Moselle", "55–Meuse", "56–Morbihan", "57–Moselle", "58–Nièvre", "59–Nord", 
	    "60–Oise", "61–Orne", "62–Pas-de-Calais", "63–Puy-de-Dôme", "64–Pyrénées-Atlantiques", "65–Hautes-Pyrénées", 
	    "66–Pyrénées-Orientales", "67–Bas-Rhin", "68–Haut-Rhin", "69–Rhône", "70–Haute-Saône", "71–Saône-et-Loire", 
	    "72–Sarthe", "73–Savoie", "74–Haute-Savoie", "75–Paris", "76–Seine-Maritime", "77–Seine-et-Marne", 
	    "78–Yvelines", "79–Deux-Sèvres", "80–Somme", "81–Tarn", "82–Tarn-et-Garonne", "83–Var", "84–Vaucluse", 
	    "85–Vendée", "86–Vienne", "87–Haute-Vienne", "88–Vosges", "89–Yonne", "90–Territoire de Belfort", 
	    "91–Essonne", "92–Hauts-de-Seine", "93–Seine-Saint-Denis", "94–Val-de-Marne", "95–Val-d'Oise", "971–Guadeloupe", 
	    "972–Martinique", "973–Guyane", "974–La Réunion"
	];
	
	function validateSelect() {
	    const zoneGeo = document.getElementById('zoneGeo').value;
	    const searchBtn = document.getElementById('boutonRecherche');

	    // Si une zone est sélectionnée, activer le bouton
	    if (zoneGeo) {
	        searchBtn.disabled = false;
	    } else {
	        searchBtn.disabled = true;
	    }
	}
	
	function changerListe() {
	    const type = document.getElementById("zoneGeoType").value;
	    const select = document.getElementById("zoneGeo");
	    
	    // Vider le select avant de rajouter les options
	    select.innerHTML = '<option value="">Sélectionnez une zone</option>'; 

	    const options = (type === "region") ? regions : departements;

	    for (const item of options) {//parcours du tableau
	        const option = document.createElement("option"); //création d'un élément dans la page web
	        option.value = item; //lui donne la valeur la valeur de l'item du tableau
	        option.textContent = item;//rajoute le texte de l'item du tableau
	        select.appendChild(option);//le rajoute au select        
	    }
	    
	    validateSelect();
	}

	// Appel initial (par défaut "region")
	changerListe();
	</script>
	
	
	<script>
	
	//script pour changer entre le tableau et la carte
	function showTab(tabId) {
	    // Cacher tous les contenus
	    const tabs = document.querySelectorAll(".tab-content");
	    tabs.forEach(tab => tab.style.display = "none");
	
	    // Afficher l'onglet sélectionné
	    document.getElementById(tabId).style.display = "block"; //block pour mettre l'onglet voulu en visible
	    
	    if (tabId === "carte") {//même type et même valeur (== convertit les deux valeurs au même types)
	        map.invalidateSize();
	    }
	}
	</script>

</body>
</html>
