package ui;
import javax.swing.table.DefaultTableModel;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.net.InetAddress;
import java.util.Vector;

public class FenetreRechercheClub extends JFrame {

    private JTextField txtFede, txtZone;
    private JTable tableResultats;
    private DefaultTableModel modelResultats;

    public FenetreRechercheClub() {
        setTitle("Recherche de clubs");
        setSize(650, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel de formulaire
        JPanel panelForm = new JPanel(new GridLayout(2, 2, 10, 10));
        JLabel lblFede = new JLabel("Fédération :");
        txtFede = new JTextField();

        JLabel lblZone = new JLabel("Zone (Code Postal ou Région) :");
        txtZone = new JTextField();

        panelForm.add(lblFede); panelForm.add(txtFede);
        panelForm.add(lblZone); panelForm.add(txtZone);

        JButton btnRechercher = new JButton("Rechercher");
        btnRechercher.addActionListener(e -> lancerRecherche());

        // Table pour les résultats
        modelResultats = new DefaultTableModel();
        tableResultats = new JTable(modelResultats);
        JScrollPane scrollPane = new JScrollPane(tableResultats);

        JPanel panelHaut = new JPanel(new BorderLayout());
        panelHaut.add(panelForm, BorderLayout.CENTER);
        panelHaut.add(btnRechercher, BorderLayout.EAST);

        add(panelHaut, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void lancerRecherche() {
        String fede = txtFede.getText();
        String zone = txtZone.getText();

        // Log de l'IP
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            System.out.println("Adresse IP : " + ip);
        } catch (Exception ex) {
            System.out.println("Impossible de récupérer l'adresse IP");
        }

        // Connexion à la base via DataBaseCon
        try (Connection conn = ui.DataBaseCon.getConnection()) {
            // Vérifie les noms de colonnes de ta table clubs
            String sql = "SELECT libelle, region, nom_fed FROM clubs WHERE nom_fed LIKE ? AND (region LIKE ? OR code_commune LIKE ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + fede + "%");
            stmt.setString(2, "%" + zone + "%");
            stmt.setString(3, "%" + zone + "%");
            ResultSet rs = stmt.executeQuery();

            // Prépare les colonnes du modèle
            ResultSetMetaData rsmd = rs.getMetaData();
            int nbColonnes = rsmd.getColumnCount();
            Vector<String> colonnes = new Vector<>();
            for (int i = 1; i <= nbColonnes; i++) {
                colonnes.add(rsmd.getColumnName(i));
            }
            modelResultats.setColumnIdentifiers(colonnes);
            modelResultats.setRowCount(0);

            // Ajoute les résultats
            while (rs.next()) {
                Vector<String> ligne = new Vector<>();
                for (int i = 1; i <= nbColonnes; i++) {
                    ligne.add(rs.getString(i));
                }
                modelResultats.addRow(ligne);
            }

            if (modelResultats.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Aucun club trouvé !");
            }

            stmt.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur de recherche : " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FenetreRechercheClub().setVisible(true));
    }
}
