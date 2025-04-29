package bddInitializer;

import dataImporter.CSVImporter;

public class main {

	public static void main(String[] args) {
		DatabaseInitializer.createTable();
		CSVImporter.importData();

	}

}
