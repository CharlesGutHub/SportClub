package ui;

import dao.UtilisateurDAO;
import model.Utilisateur;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FenetreAdmin extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private UtilisateurDAO dao = new UtilisateurDAO();
    private Utilisateur adminCourant;

    // --- Constructeur obligatoire avec admin courant ---
    public FenetreAdmin(Utilisateur adminCourant) {
        this.adminCourant = adminCourant;
        setTitle("Gestion des utilisateurs");
        setSize(900, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initUI();
    }

    private void initUI() {
        String[] colonnes = { "Utilisateur", "Email", "Mot de passe", "Valide", "Élu", "Acteur Sportif", "Pièce", "Action"};
        model = new DefaultTableModel(colonnes, 0) {
            public boolean isCellEditable(int row, int column) {
                return column == 7; // Seule la colonne action (bouton) est éditable
            }
        };
        table = new JTable(model);

        // Cache la colonne contenant l’objet Utilisateur
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setPreferredWidth(0);

        table.getColumn("Action").setCellRenderer(new ButtonRenderer());
        table.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane(table);

        JButton btnAjouter = new JButton("Ajouter");
        btnAjouter.addActionListener(e -> afficherFormulaireAjout());

        JButton btnSupprimer = new JButton("Supprimer");
        btnSupprimer.addActionListener(e -> supprimerCompte());

        JButton btnValider = new JButton("Valider");
        btnValider.addActionListener(e -> validerCompte());

        JButton btnRechercher = new JButton("Rechercher un club");
        btnRechercher.addActionListener(e -> new FenetreRechercheClub().setVisible(true));

        JButton btnLogs = new JButton("Voir les logs");
        btnLogs.addActionListener(e -> new FenetreLogs().setVisible(true));

        JButton btnChangerPiece = new JButton("Changer la pièce justificative");
        btnChangerPiece.addActionListener(e -> changerPieceJustificative());

        JButton btnRetirerPiece = new JButton("Retirer la pièce justificative");
        btnRetirerPiece.addActionListener(e -> retirerPieceJustificative());

        JButton btnJournal = new JButton("Voir journal admin");
        btnJournal.addActionListener(e -> new FenetreAuditAdmin().setVisible(true));

        JPanel panelHaut = new JPanel();
        panelHaut.add(btnAjouter);
        panelHaut.add(btnRechercher);
        panelHaut.add(btnLogs);

        JPanel panelBas = new JPanel();
        panelBas.add(btnSupprimer);
        panelBas.add(btnValider);
        panelBas.add(btnChangerPiece);
        panelBas.add(btnRetirerPiece);
        panelBas.add(btnJournal);

        add(panelHaut, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBas, BorderLayout.SOUTH);

        chargerUtilisateurs();
    }

    public void chargerUtilisateurs() {
        model.setRowCount(0);
        List<Utilisateur> utilisateurs = dao.lister();

        for (Utilisateur u : utilisateurs) {
            String statut = u.isValide() ? "Validé" : "En attente";
            JButton boutonAction = getBoutonAction(u);

            Object[] row = {
                u, // Objet complet (colonne cachée)
                u.getEmail(),
                u.getMotDePasse(),
                statut,
                u.isElu() ? "✔" : "✗",
                u.isActeurSportif() ? "✔" : "✗",
                u.getCheminPieceJustificative() == null ? "" : new java.io.File(u.getCheminPieceJustificative()).getName(),
                boutonAction
            };

            model.addRow(row);
        }
    }

    private void afficherFormulaireAjout() {
        JDialog dialog = new JDialog(this, "Ajout", true);
        dialog.setSize(450, 350);
        dialog.setLayout(new GridLayout(8, 2)); // Attention 8 lignes si tu ajoutes un champ
        dialog.setLocationRelativeTo(this);

        JTextField txtNom = new JTextField();
        JTextField txtEmail = new JTextField();
        JPasswordField txtMotDePasse = new JPasswordField();
        JCheckBox chkElu = new JCheckBox("Élu");
        JCheckBox chkActeur = new JCheckBox("Acteur sportif");

        JButton btnPiece = new JButton("Choisir la pièce justificative");
        JLabel lblPieceChoisie = new JLabel("Aucune sélectionnée");
        final String[] cheminPiece = { null };

        // 1️⃣ Ici tu déclares les rôles ET la comboBox
        String[] roles = {"client", "admin"};
        JComboBox<String> comboRole = new JComboBox<>(roles);

        btnPiece.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int retour = chooser.showOpenDialog(dialog);
            if (retour == JFileChooser.APPROVE_OPTION) {
                cheminPiece[0] = chooser.getSelectedFile().getAbsolutePath();
                lblPieceChoisie.setText(chooser.getSelectedFile().getName());
            }
        });

        JButton btnValider = new JButton("Valider");

        // Ajoute tous les composants
        dialog.add(new JLabel("Nom:")); dialog.add(txtNom);
        dialog.add(new JLabel("Email:")); dialog.add(txtEmail);
        dialog.add(new JLabel("Mot de passe:")); dialog.add(txtMotDePasse);
        dialog.add(chkElu); dialog.add(chkActeur);
        dialog.add(btnPiece); dialog.add(lblPieceChoisie);
        dialog.add(new JLabel("Rôle :")); dialog.add(comboRole); // AJOUTE BIEN LA COMBOBOX
        dialog.add(new JLabel()); dialog.add(btnValider);

        // 2️⃣ Le bouton Valider récupère la valeur de comboRole
        btnValider.addActionListener(e -> {
            String nom = txtNom.getText();
            String email = txtEmail.getText();
            String motDePasse = new String(txtMotDePasse.getPassword());
            boolean elu = chkElu.isSelected();
            boolean acteur = chkActeur.isSelected();
            String piece = cheminPiece[0];
            String role = (String) comboRole.getSelectedItem();

            if (nom.isEmpty() || email.isEmpty() || motDePasse.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Champs requis.");
                return;
            }
            Utilisateur u = new Utilisateur(nom, email, motDePasse, false, elu, acteur, piece, role);
            dao.ajouter(u);
            // ... logs, notification, etc. ...
            JOptionPane.showMessageDialog(this, "Utilisateur ajouté !");
            dialog.dispose();
            chargerUtilisateurs();
        });


        dialog.setVisible(true);
    }


    private void supprimerCompte() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionnez un utilisateur à supprimer.");
            return;
        }
        Utilisateur utilisateur = (Utilisateur) model.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Confirmer la suppression ?", "Suppression", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dao.supprimer(utilisateur.getId());
            chargerUtilisateurs();

            LoggerService.logAdminAction(
                adminCourant.getEmail(),
                "Suppression utilisateur",
                utilisateur.getEmail(),
                "Suppression depuis FenetreAdmin"
            );
            NotificationService.envoyerEmail(
                utilisateur.getEmail(),
                "Suppression de votre compte",
                "Bonjour " + utilisateur.getNom() + ",\n\nVotre compte vient d’être supprimé par un administrateur.\n\nCordialement, L’équipe admin."
            );
            JOptionPane.showMessageDialog(this, "Utilisateur supprimé, notification envoyée et audit loggé !");
        }
    }

    private void validerCompte() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionnez un utilisateur.");
            return;
        }
        Utilisateur utilisateur = (Utilisateur) model.getValueAt(selectedRow, 0);

        String cheminPiece = utilisateur.getCheminPieceJustificative();
        if (cheminPiece == null || cheminPiece.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Aucune pièce justificative attachée !");
            return;
        }

        int ouvrir = JOptionPane.showConfirmDialog(this,
            "Voulez-vous ouvrir la pièce justificative avant de valider ?",
            "Vérification pièce", JOptionPane.YES_NO_OPTION);

        if (ouvrir == JOptionPane.YES_OPTION) {
            try {
                Desktop.getDesktop().open(new java.io.File(cheminPiece));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Impossible d'ouvrir le fichier !");
                return;
            }
        }

        int valider = JOptionPane.showConfirmDialog(this, "Valider ce compte ?", "Validation finale", JOptionPane.YES_NO_OPTION);
        if (valider == JOptionPane.YES_OPTION) {
            dao.validerUtilisateur(utilisateur.getId());
            LoggerService.logAdminAction(
                adminCourant.getEmail(),
                "Validation utilisateur",
                utilisateur.getEmail(),
                "Validé via FenetreAdmin"
            );
            NotificationService.envoyerEmail(
                utilisateur.getEmail(),
                "Votre compte a été validé !",
                "Bonjour " + utilisateur.getNom() + ",\n\nVotre compte sur la plateforme Clubs de sport a été validé par l’administrateur.\n\nCordialement, L’équipe admin."
            );
            JOptionPane.showMessageDialog(this, "Utilisateur validé !");
            chargerUtilisateurs();
        }
    }

    private void changerPieceJustificative() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionnez un utilisateur !");
            return;
        }

        Utilisateur utilisateur = (Utilisateur) model.getValueAt(selectedRow, 0);
        String ancienNom = utilisateur.getCheminPieceJustificative() == null ? "Aucun" : new java.io.File(utilisateur.getCheminPieceJustificative()).getName();

        JFileChooser chooser = new JFileChooser();
        int retour = chooser.showOpenDialog(this);
        if (retour == JFileChooser.APPROVE_OPTION) {
            String nouveauChemin = chooser.getSelectedFile().getAbsolutePath();
            utilisateur.setCheminPieceJustificative(nouveauChemin);
            dao.mettreAJourPieceJustificative(utilisateur.getId(), nouveauChemin);
            chargerUtilisateurs();

            String nouveauNom = nouveauChemin == null ? "Aucun" : new java.io.File(nouveauChemin).getName();

            LoggerService.logAdminAction(
                adminCourant.getEmail(),
                "Modification pièce justificative",
                utilisateur.getEmail(),
                "Ancienne pièce : " + ancienNom + " | Nouvelle pièce : " + nouveauNom
            );
            NotificationService.envoyerEmail(
                utilisateur.getEmail(),
                "Modification de votre pièce justificative",
                "Bonjour " + utilisateur.getNom() + ",\n\nVotre pièce justificative a été modifiée par un administrateur sur la plateforme Clubs de sport.\n\nNouveau justificatif : "
                + nouveauNom +
                "\n\nSi ce n'est pas normal, contactez l'équipe support.\n\nCordialement, L’équipe admin."
            );

            JOptionPane.showMessageDialog(this, "Pièce justificative modifiée, notification envoyée et audit loggé !");
        }
    }

    private void retirerPieceJustificative() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionnez un utilisateur !");
            return;
        }
        Utilisateur utilisateur = (Utilisateur) model.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Retirer la pièce justificative de cet utilisateur ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            utilisateur.setCheminPieceJustificative(null);
            dao.mettreAJourPieceJustificative(utilisateur.getId(), null);

            LoggerService.logAdminAction(
                adminCourant.getEmail(),
                "Retrait pièce justificative",
                utilisateur.getEmail(),
                "Retirée via FenetreAdmin"
            );

            NotificationService.envoyerEmail(
                utilisateur.getEmail(),
                "Retrait de votre pièce justificative",
                "Bonjour " + utilisateur.getNom() + ",\n\nVotre pièce justificative a été retirée par un administrateur sur la plateforme Clubs de sport.\n\nCordialement, L’équipe admin."
            );
            chargerUtilisateurs();
            JOptionPane.showMessageDialog(this, "Pièce justificative retirée !");
        }
    }

    // ---- Bouton Action dans le tableau ----
    private JButton getBoutonAction(Utilisateur u) {
        if (!u.isValide()) {
            JButton boutonValider = new JButton("Valider");
            boutonValider.addActionListener(e -> {
                dao.validerUtilisateur(u.getId());
                LoggerService.logAdminAction(
                    adminCourant.getEmail(),
                    "Validation utilisateur",
                    u.getEmail(),
                    "Validé via tableau"
                );
                JOptionPane.showMessageDialog(this, "Utilisateur validé !");
                chargerUtilisateurs();
            });
            return boutonValider;
        } else {
            JButton bouton = new JButton("✓ Validé");
            bouton.setEnabled(false);
            return bouton;
        }
    }

    class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
        public ButtonRenderer() { setOpaque(true); }
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                      boolean isSelected, boolean hasFocus, int row, int column) {
            return (value instanceof JButton) ? (JButton) value : this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
        }
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            if (value instanceof JButton) {
                button = (JButton) value;
            }
            return button;
        }
    }
    

    // --- Main pour test (à enlever en prod) ---
    public static void main(String[] args) {
        Utilisateur fauxAdmin = new Utilisateur("AdminTest", "admin@email.com", "xxxx", true, true, true, null,"admin");
        SwingUtilities.invokeLater(() -> new FenetreAdmin(fauxAdmin).setVisible(true));
    }
}
