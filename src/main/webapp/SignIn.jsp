<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription</title>
</head>
<body>

<h2>Formulaire d'inscription</h2>
    <form action="SignIn" method="post" enctype="multipart/form-data">
        <label for="nom">Nom :</label><br>
        <input type="text" id="nom" name="nom" required><br><br>

        <label for="prenom">Prénom :</label><br>
        <input type="text" id="prenom" name="prenom" required><br><br>

        <label for="email">Email :</label><br>
        <input type="email" id="email" name="email" required><br><br>

        <label for="mdp">Mot de passe :</label><br>
        <input type="password" id="mdp" name="mdp" required><br><br>

        <label for="confirm_mdp">Confirmer le mot de passe :</label><br>
        <input type="password" id="confirm_mdp" name="confirm_mdp" required><br><br>
        
        <label>Rôle :</label><br>
        <input type="radio" id="elu" name="role" value="elu" required>
        <label for="elu">Élu</label><br>
        <input type="radio" id="acteur" name="role" value="acteur" required>
        <label for="acteur">Acteur du monde sportif</label><br><br>
        
        <label for="justificatif">Justificatif (PDF uniquement) :</label><br>
        <input type="file" id="justificatif" name="justificatif" accept="application/pdf" required><br><br>

        <input type="submit" value="S'inscrire">
    </form>

</body>
</html>