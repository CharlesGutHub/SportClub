package ui;

import javax.swing.*;
import java.awt.*;
import model.Utilisateur;

public class AccueilFrame extends JFrame {

    private Utilisateur adminCourant;

    public AccueilFrame(Utilisateur adminCourant) {
        this.adminCourant = adminCourant;
        setTitle("Accueil - Menu principal");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel lblTitre = new JLabel("Plateforme Clubs de Sport", SwingConstants.CENTER);
        lblTitre.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel panelBoutons = new JPanel(new GridLayout(3, 1, 20, 20));

        JButton btnAdmin = new JButton("Gestion des utilisateurs");
        btnAdmin.addActionListener(e -> new FenetreAdmin(adminCourant).setVisible(true));

        JButton btnRechercheClub = new JButton("Recherche de clubs");
        btnRechercheClub.addActionListener(e -> new FenetreRechercheClub().setVisible(true));

        JButton btnLogs = new JButton("Voir les logs");
        btnLogs.addActionListener(e -> new FenetreLogs().setVisible(true));

        panelBoutons.add(btnAdmin);
        panelBoutons.add(btnRechercheClub);
        panelBoutons.add(btnLogs);

        add(lblTitre, BorderLayout.NORTH);
        add(panelBoutons, BorderLayout.CENTER);

        JButton btnQuitter = new JButton("Quitter");
        btnQuitter.addActionListener(e -> System.exit(0));
        JPanel panelSud = new JPanel();
        panelSud.add(btnQuitter);
        add(panelSud, BorderLayout.SOUTH);
    }
}
