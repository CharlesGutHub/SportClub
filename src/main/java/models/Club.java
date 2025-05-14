package models;

public class Club {
    private String commune;
    private int totalLicencies;
    private int totalHommes;
    private int totalFemmes;

    public Club(String commune, int totalLicencies, int totalHommes, int totalFemmes) {
        this.commune = commune;
        this.totalLicencies = totalLicencies;
        this.totalHommes = totalHommes;
        this.totalFemmes = totalFemmes;
    }

    public String getCommune() {
        return commune;
    }

    public int getTotalLicencies() {
        return totalLicencies;
    }

    public int getTotalHommes() {
        return totalHommes;
    }

    public int getTotalFemmes() {
        return totalFemmes;
    }
}
