package models;

public class InfoDepartement {
    private String departement;
    private int total;
    private int totalHomme;
    private int totalFemme;

    public InfoDepartement(String departement, int total, int totalHomme, int totalFemme) {
        this.departement = departement;
        this.total = total;
        this.totalHomme = totalHomme;
        this.totalFemme = totalFemme;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
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
