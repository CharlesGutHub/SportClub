package ui;

import org.mindrot.jbcrypt.BCrypt;

public class Password {

    // Hash le mot de passe en clair
    public static String hash(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    // Vérifie si le mot de passe correspond au hash
    public static boolean verify(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}

