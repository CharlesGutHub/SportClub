package bddInitializer;
import bddInitializer.DatabaseInitializer;
import dataImporter.CSVImporter;

public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.createTable();        // table licences
        DatabaseInitializer.createTableClub();    // table clubs
        DatabaseInitializer.createTableUser();    // table users
        CSVImporter.importDataLicence();          // importer les données CSV
         CSVImporter.importDataClub();          // tu peux activer plus tard
    }
}