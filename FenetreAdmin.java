/*package ui;

import javax.swing.JFrame;

public class FenetreAdmin {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Administrateur");
		frame.setSize(1200, 700);
		frame.setVisible(true);
	}
}*/
package ui;
import model.Utilisateur;
import dao.UtilisateurDAO;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import javax.swing.*;

public class FenetreAdmin extends JFrame {

    public FenetreAdmin() {
        setTitle("Gestion des utilisateurs");
        setSize(600, 400);
        setLocationRelativeTo(null); // centre la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new java.awt.BorderLayout());
        // À compléter dans les prochaines étapes
        initUI();
    }

    private void initUI() {
        // Interface utilisateur ici
    	String[] columnNames = { "ID", "Nom", "Email" };

    	// Exemple de données provisoires (à remplacer par les vrais utilisateurs plus tard)
    	Object[][] data = {
    	    {1, "Jacques", "jacques@email.com"},
    	    {2, "Marie", "marie@email.com"},
    	    {3, "Ali", "ali@email.com"}
    	};

    	DefaultTableModel model = new DefaultTableModel(data, columnNames);
    	JTable table = new JTable(model);
    	JScrollPane scrollPane = new JScrollPane(table);

    	add(scrollPane); // On ajoute le tableau à la fenêtre
    	// --- Création des boutons ---
    	JButton btnAjouter = new JButton("Ajouter");
    	btnAjouter.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        afficherFormulaireAjout();
    	    }
    	});

    	JButton btnModifier = new JButton("Modifier");
    	JButton btnSupprimer = new JButton("Supprimer");

    	// On les met dans un panneau horizontal
    	JPanel panelBoutons = new JPanel();
    	panelBoutons.add(btnAjouter);
    	panelBoutons.add(btnModifier);
    	panelBoutons.add(btnSupprimer);

    	// On place ce panneau en bas (SUD) de la fenêtre
    	add(panelBoutons, BorderLayout.SOUTH);

    	
    }
    private void afficherFormulaireAjout() {
        // Création de la boîte de dialogue (fenêtre secondaire)
        JDialog dialog = new JDialog(this, "Ajouter un utilisateur", true);
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new java.awt.GridLayout(3, 2, 5, 5)); // 3 lignes, 2 colonnes

        // Champs de saisie
        JLabel lblNom = new JLabel("Nom :");
        JTextField txtNom = new JTextField();

        JLabel lblEmail = new JLabel("Email :");
        JTextField txtEmail = new JTextField();

        JButton btnValider = new JButton("Valider");

        // Ajout des composants dans la boîte
        dialog.add(lblNom);
        dialog.add(txtNom);
        dialog.add(lblEmail);
        dialog.add(txtEmail);
        dialog.add(new JLabel()); // cellule vide
        dialog.add(btnValider);

        // Action du bouton Valider
       /* btnValider.addActionListener(e -> {
            String nom = txtNom.getText();
            String email = txtEmail.getText();

            JOptionPane.showMessageDialog(this, "Utilisateur ajouté :\nNom: " + nom + "\nEmail: " + email);
            dialog.dispose(); // fermer la fenêtre
        });*/
        btnValider.addActionListener(e -> {
            String nom = txtNom.getText();
            String email = txtEmail.getText();
            String motDePasse = "motDePasse123"; // à améliorer plus tard

            Utilisateur utilisateur = new Utilisateur(nom, email, motDePasse);
            UtilisateurDAO dao = new UtilisateurDAO();
            dao.ajouter(utilisateur);

            JOptionPane.showMessageDialog(this, "Utilisateur ajouté avec succès !");
            dialog.dispose();
        });
        

        dialog.setVisible(true); // afficher la fenêtre
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FenetreAdmin().setVisible(true);
        });
    }
}