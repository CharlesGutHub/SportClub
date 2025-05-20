<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sportclub</title>
    <style>
        /* Style de la modale */
        #legalModal {
            display: none;
            position: fixed;
            z-index: 9999;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0,0,0,0.5);
        }

        #legalModalContent {
            background-color: #fff;
            margin: 10% auto;
            padding: 20px;
            border-radius: 8px;
            width: 60%;
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
            position: relative;
        }

        #closeModal {
            position: absolute;
            top: 10px;
            right: 15px;
            font-size: 20px;
            cursor: pointer;
            color: #aaa;
        }

        #closeModal:hover {
            color: black;
        }

        .legal-btn {
            position: fixed;
            bottom: 20px;
            right: 20px;
            padding: 10px 15px;
            background-color: #333;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h1>Bienvenue</h1>

    <a href="login.jsp">Se connecter</a>
    <a href="SignIn.jsp">S'inscrire</a>
    <a href="recherche.jsp">Rechercher</a>
    <a href="view-announces">Les annonces du moment</a>

    <!-- Bouton pour ouvrir les mentions légales -->
    <button class="legal-btn" onclick="document.getElementById('legalModal').style.display='block'">
        Mentions légales
    </button>

    <!-- Contenu de la modale -->
    <div id="legalModal">
        <div id="legalModalContent">
            <span id="closeModal" onclick="document.getElementById('legalModal').style.display='none'">&times;</span>
            <h2>Mentions légales</h2>
            <p>
                Ce site respecte la législation française et européenne en matière de protection des données personnelles (RGPD).
                Les informations collectées sont utilisées uniquement à des fins de fonctionnement interne.
            </p>
            <p>
                Des cookies peuvent être utilisés pour améliorer l’expérience utilisateur.
                En continuant à utiliser ce site, vous consentez à leur utilisation.
            </p>
            <p>
                Pour plus d’informations, veuillez contacter l’administrateur du site.
            </p>
        </div>
    </div>
</body>
</html>
