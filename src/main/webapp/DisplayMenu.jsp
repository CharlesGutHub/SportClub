<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Choisir une échelle</title>
</head>
<body>
    <h2>Veuillez choisir une échelle :</h2>

    <form id="echelleForm" method="GET">
        <input type="radio" name="echelle" value="region" id="region">
        <label for="region">Région</label><br>

        <input type="radio" name="echelle" value="departement" id="departement">
        <label for="departement">Département</label><br>

        <input type="radio" name="echelle" value="commune" id="commune">
        <label for="commune">Commune</label><br><br>

        <button type="submit">Valider</button>
    </form>

    <script>
        const form = document.getElementById('echelleForm');
        form.addEventListener('submit', function(event) {
            event.preventDefault();

            const choix = document.querySelector('input[name="echelle"]:checked');
            if (!choix) {
                alert("Veuillez sélectionner une échelle.");
                return;
            }

            let actionUrl = '';
            switch (choix.value) {
                case 'region':
                    actionUrl = 'ShowRegion';
                    break;
                case 'departement':
                    actionUrl = 'ShowDepartement';
                    break;
                case 'commune':
                    actionUrl = 'ShowCommune';
                    break;
            }

            form.action = actionUrl;
            form.submit();
        });
    </script>
</body>
</html>