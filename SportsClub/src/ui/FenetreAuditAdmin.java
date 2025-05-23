package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class FenetreAuditAdmin extends JFrame {

    public FenetreAuditAdmin() {
        setTitle("Journal des actions administrateur");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {"Date/Heure", "Admin", "Action", "Cible", "Détails"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        try (Connection conn = ui.DataBaseCon.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT date_heure, admin_email, action, cible, details FROM audit_admin ORDER BY date_heure DESC");
            while (rs.next()) {
                Object[] row = {
                    rs.getTimestamp("date_heure"),
                    rs.getString("admin_email"),
                    rs.getString("action"),
                    rs.getString("cible"),
                    rs.getString("details")
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FenetreAuditAdmin().setVisible(true));
    }
}
