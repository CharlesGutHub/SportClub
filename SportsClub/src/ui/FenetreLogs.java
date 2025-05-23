package ui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;

public class FenetreLogs extends JFrame {

    private JTextArea textArea;

    public FenetreLogs() {
        setTitle("Journal des connexions");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Ferme juste cette fenêtre

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton btnActualiser = new JButton("Actualiser");
        btnActualiser.addActionListener(e -> chargerLogs());

        JPanel panelBas = new JPanel();
        panelBas.add(btnActualiser);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBas, BorderLayout.SOUTH);

        chargerLogs();
    }

    private void chargerLogs() {
        try {
            String contenu = Files.readString(Paths.get("logs.txt"));
            textArea.setText(contenu);
        } catch (IOException e) {
            textArea.setText("Erreur lors du chargement des logs.\n" + e.getMessage());
        }
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FenetreLogs().setVisible(true);
        });
    }
}
