package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class FenetreLogsBase extends JFrame {

    public FenetreLogsBase() {
        setTitle("Logs des connexions clients");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {"Date/Heure", "IP", "Email", "Action"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        try (Connection conn = DataBaseCon.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT date_heure, ip, email, action FROM logs ORDER BY date_heure DESC");
            while (rs.next()) {
                Object[] row = {
                    rs.getTimestamp("date_heure"),
                    rs.getString("ip"),
                    rs.getString("email"),
                    rs.getString("action")
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FenetreLogsBase().setVisible(true));
    }
}
