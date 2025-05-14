package models;


public class InfoRegion {
    private String region;
    private int total;
    private int totalHomme;
    private int totalFemme;

    public InfoRegion(String region, int total, int totalHomme, int totalFemme) {
        this.region = region;
        this.total = total;
        this.totalHomme = totalHomme;
        this.totalFemme = totalFemme;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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