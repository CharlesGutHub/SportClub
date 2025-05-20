package models;

public class RankingResult {
    private String nomClub;
    private String federation;
    private int totalLicencies;
    private int totalHommes;
    private int totalFemmes;

    public RankingResult(String nomClub, String federation, int totalLicencies, int totalHommes, int totalFemmes) {
        this.nomClub = nomClub;
        this.federation = federation;
        this.totalLicencies = totalLicencies;
        this.totalHommes = totalHommes;
        this.totalFemmes = totalFemmes;
    }

    public String getNomClub() {
        return nomClub;
    }

    public String getFederation() {
        return federation;
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
