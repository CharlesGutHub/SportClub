package models;

public class DiagramsClub {
    private String nomClub;
    private int totalLicencies;
    private int totalHommes;
    private int totalFemmes;
    private int totalJeunes;

    public DiagramsClub(String nomClub, int totalLicencies, int totalHommes, int totalFemmes, int totalJeunes) {
        this.nomClub = nomClub;
        this.totalLicencies = totalLicencies;
        this.totalHommes = totalHommes;
        this.totalFemmes = totalFemmes;
        this.totalJeunes = totalJeunes;
    }

    public String getNomClub() { return nomClub; }
    public int getTotalLicencies() { return totalLicencies; }
    public int getTotalHommes() { return totalHommes; }
    public int getTotalFemmes() { return totalFemmes; }
    public int getTotalJeunes() { return totalJeunes; }

    public double getRatioHF() {
        int total = totalHommes + totalFemmes;
        return total == 0 ? 0 : (double) totalHommes / total;
    }

    public double getPourcentageJeunes() {
        return totalLicencies == 0 ? 0 : (double) totalJeunes / totalLicencies * 100;
    }
}
