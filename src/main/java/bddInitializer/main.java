package bddInitializer;

import dataImporter.CSVImporter;

public class main {

	public static void main(String[] args) {
		DatabaseInitializer.createTable();
		DatabaseInitializer.createTableClub();
		DatabaseInitializer.createTableUser();
		CSVImporter.importDataLicence();
		//CSVImporter.importDataClub();

	}

}
