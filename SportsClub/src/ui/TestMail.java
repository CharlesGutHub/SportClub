package ui;
public class TestMail {
    public static void main(String[] args) {
        NotificationService.envoyerEmail(
            "ton.email.de.test@gmail.com", // Mets ici un mail à toi !
            "Test JavaMail",
            "Ceci est un test d'envoi JavaMail via NotificationService"
        );
    }
}
