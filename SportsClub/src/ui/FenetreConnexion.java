package ui;

import javax.swing.*;
import java.awt.*;
import model.Utilisateur;
import dao.UtilisateurDAO;

public class FenetreConnexion extends JFrame {

    public FenetreConnexion() {
        setTitle("Connexion");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 5, 5));

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();

        JLabel lblMotDePasse = new JLabel("Mot de passe:");
        JPasswordField txtMotDePasse = new JPasswordField();

        JButton btnConnexion = new JButton("Se connecter");

        btnConnexion.addActionListener(e -> {
            String email = txtEmail.getText();
            String motDePasse = new String(txtMotDePasse.getPassword());
            UtilisateurDAO dao = new UtilisateurDAO();
            Utilisateur utilisateur = dao.trouverParEmail(email);

            if (utilisateur != null && Password.verify(motDePasse, utilisateur.getMotDePasse())) {
                // Connexion réussie, ouvre le menu principal avec l'utilisateur connecté
                new AccueilFrame(utilisateur).setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Email ou mot de passe incorrect !");
            }
        });

        add(lblEmail); add(txtEmail);
        add(lblMotDePasse); add(txtMotDePasse);
        add(new JLabel()); add(btnConnexion);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FenetreConnexion().setVisible(true));
    }
}
