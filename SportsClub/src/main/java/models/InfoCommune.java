package models;

public class InfoCommune {
    private String commune;
    private int total;
    private int totalHomme;
    private int totalFemme;

    public InfoCommune(String commune, int total, int totalHomme, int totalFemme) {
        this.commune = commune;
        this.total = total;
        this.totalHomme = totalHomme;
        this.totalFemme = totalFemme;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalHomme() {
        return totalHomme;
    }

    public void setTotalHomme(int totalHomme) {
        this.totalHomme = totalHomme;
    }

    public int getTotalFemme() {
        return totalFemme;
    }

    public void setTotalFemme(int totalFemme) {
        this.totalFemme = totalFemme;
    }
}
