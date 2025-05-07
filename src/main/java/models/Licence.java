package models;

public class Licence {
    private String codeCommune;
    private String libelle;
    private String region;
    private String fed2022;
    private String nomFed;
    private String departement;
    public String getDepartement() {
		return departement;
	}


	public void setDepartement(String departement) {
		this.departement = departement;
	}

	private int total2022;
    private int age0_4_2022;
    private int age5_9_2022;
    private int age10_14_2022;
    private int age15_19_2022;
    private int age20_29_2022;
    private int age30_44_2022;
    private int age45_59_2022;
    private int age60_74_2022;
    private int age75Plus2022;
    private int totalFemmes2022;
    private int femmes0_4_2022;
    private int femmes5_9_2022;
    private int femmes10_14_2022;
    private int femmes15_19_2022;
    private int femmes20_29_2022;
    private int femmes30_44_2022;
    private int femmes45_59_2022;
    private int femmes60_74_2022;
    private int femmes75Plus2022;
    private int totalHommes2022;
    private int hommes0_4_2022;
    private int hommes5_9_2022;
    private int hommes10_14_2022;
    private int hommes15_19_2022;
    private int hommes20_29_2022;
    private int hommes30_44_2022;
    private int hommes45_59_2022;
    private int hommes60_74_2022;
    private int hommes75Plus2022;
    private int totalQpv2022;
    private int femmesQpv2022;
    private int hommesQpv2022;
    
    public Licence(String codeCommune,String libelle,String region,String departement,String nomFed,int total2022, int totalFemmes2022 ) 
    {
    	this.codeCommune = codeCommune;
    	this.libelle = libelle;
    	this.region = region;
    	this.departement = departement;
    	this.nomFed = nomFed;
    	this.total2022 = total2022;
    	this.totalFemmes2022 = totalFemmes2022;
    }
    
    
	public Licence(String codeCommune, String libelle, String region, String fed2022, String nomFed, int total2022,
			int age0_4_2022, int age5_9_2022, int age10_14_2022, int age15_19_2022, int age20_29_2022,
			int age30_44_2022, int age45_59_2022, int age60_74_2022, int age75Plus2022, int totalFemmes2022,
			int femmes0_4_2022, int femmes5_9_2022, int femmes10_14_2022, int femmes15_19_2022, int femmes20_29_2022,
			int femmes30_44_2022, int femmes45_59_2022, int femmes60_74_2022, int femmes75Plus2022, int totalHommes2022,
			int hommes0_4_2022, int hommes5_9_2022, int hommes10_14_2022, int hommes15_19_2022, int hommes20_29_2022,
			int hommes30_44_2022, int hommes45_59_2022, int hommes60_74_2022, int hommes75Plus2022, int totalQpv2022,
			int femmesQpv2022, int hommesQpv2022) {
		
		this.codeCommune = codeCommune;
		this.libelle = libelle;
		this.region = region;
		this.fed2022 = fed2022;
		this.nomFed = nomFed;
		this.total2022 = total2022;
		this.age0_4_2022 = age0_4_2022;
		this.age5_9_2022 = age5_9_2022;
		this.age10_14_2022 = age10_14_2022;
		this.age15_19_2022 = age15_19_2022;
		this.age20_29_2022 = age20_29_2022;
		this.age30_44_2022 = age30_44_2022;
		this.age45_59_2022 = age45_59_2022;
		this.age60_74_2022 = age60_74_2022;
		this.age75Plus2022 = age75Plus2022;
		this.totalFemmes2022 = totalFemmes2022;
		this.femmes0_4_2022 = femmes0_4_2022;
		this.femmes5_9_2022 = femmes5_9_2022;
		this.femmes10_14_2022 = femmes10_14_2022;
		this.femmes15_19_2022 = femmes15_19_2022;
		this.femmes20_29_2022 = femmes20_29_2022;
		this.femmes30_44_2022 = femmes30_44_2022;
		this.femmes45_59_2022 = femmes45_59_2022;
		this.femmes60_74_2022 = femmes60_74_2022;
		this.femmes75Plus2022 = femmes75Plus2022;
		this.totalHommes2022 = totalHommes2022;
		this.hommes0_4_2022 = hommes0_4_2022;
		this.hommes5_9_2022 = hommes5_9_2022;
		this.hommes10_14_2022 = hommes10_14_2022;
		this.hommes15_19_2022 = hommes15_19_2022;
		this.hommes20_29_2022 = hommes20_29_2022;
		this.hommes30_44_2022 = hommes30_44_2022;
		this.hommes45_59_2022 = hommes45_59_2022;
		this.hommes60_74_2022 = hommes60_74_2022;
		this.hommes75Plus2022 = hommes75Plus2022;
		this.totalQpv2022 = totalQpv2022;
		this.femmesQpv2022 = femmesQpv2022;
		this.hommesQpv2022 = hommesQpv2022;
	}

	public String getCodeCommune() {
		return codeCommune;
	}

	public void setCodeCommune(String codeCommune) {
		this.codeCommune = codeCommune;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getFed2022() {
		return fed2022;
	}

	public void setFed2022(String fed2022) {
		this.fed2022 = fed2022;
	}

	public String getNomFed() {
		return nomFed;
	}

	public void setNomFed(String nomFed) {
		this.nomFed = nomFed;
	}

	public int getTotal2022() {
		return total2022;
	}

	public void setTotal2022(int total2022) {
		this.total2022 = total2022;
	}

	public int getAge0_4_2022() {
		return age0_4_2022;
	}

	public void setAge0_4_2022(int age0_4_2022) {
		this.age0_4_2022 = age0_4_2022;
	}

	public int getAge5_9_2022() {
		return age5_9_2022;
	}

	public void setAge5_9_2022(int age5_9_2022) {
		this.age5_9_2022 = age5_9_2022;
	}

	public int getAge10_14_2022() {
		return age10_14_2022;
	}

	public void setAge10_14_2022(int age10_14_2022) {
		this.age10_14_2022 = age10_14_2022;
	}

	public int getAge15_19_2022() {
		return age15_19_2022;
	}

	public void setAge15_19_2022(int age15_19_2022) {
		this.age15_19_2022 = age15_19_2022;
	}

	public int getAge20_29_2022() {
		return age20_29_2022;
	}

	public void setAge20_29_2022(int age20_29_2022) {
		this.age20_29_2022 = age20_29_2022;
	}

	public int getAge30_44_2022() {
		return age30_44_2022;
	}

	public void setAge30_44_2022(int age30_44_2022) {
		this.age30_44_2022 = age30_44_2022;
	}

	public int getAge45_59_2022() {
		return age45_59_2022;
	}

	public void setAge45_59_2022(int age45_59_2022) {
		this.age45_59_2022 = age45_59_2022;
	}

	public int getAge60_74_2022() {
		return age60_74_2022;
	}

	public void setAge60_74_2022(int age60_74_2022) {
		this.age60_74_2022 = age60_74_2022;
	}

	public int getAge75Plus2022() {
		return age75Plus2022;
	}

	public void setAge75Plus2022(int age75Plus2022) {
		this.age75Plus2022 = age75Plus2022;
	}

	public int getTotalFemmes2022() {
		return totalFemmes2022;
	}

	public void setTotalFemmes2022(int totalFemmes2022) {
		this.totalFemmes2022 = totalFemmes2022;
	}

	public int getFemmes0_4_2022() {
		return femmes0_4_2022;
	}

	public void setFemmes0_4_2022(int femmes0_4_2022) {
		this.femmes0_4_2022 = femmes0_4_2022;
	}

	public int getFemmes5_9_2022() {
		return femmes5_9_2022;
	}

	public void setFemmes5_9_2022(int femmes5_9_2022) {
		this.femmes5_9_2022 = femmes5_9_2022;
	}

	public int getFemmes10_14_2022() {
		return femmes10_14_2022;
	}

	public void setFemmes10_14_2022(int femmes10_14_2022) {
		this.femmes10_14_2022 = femmes10_14_2022;
	}

	public int getFemmes15_19_2022() {
		return femmes15_19_2022;
	}

	public void setFemmes15_19_2022(int femmes15_19_2022) {
		this.femmes15_19_2022 = femmes15_19_2022;
	}

	public int getFemmes20_29_2022() {
		return femmes20_29_2022;
	}

	public void setFemmes20_29_2022(int femmes20_29_2022) {
		this.femmes20_29_2022 = femmes20_29_2022;
	}

	public int getFemmes30_44_2022() {
		return femmes30_44_2022;
	}

	public void setFemmes30_44_2022(int femmes30_44_2022) {
		this.femmes30_44_2022 = femmes30_44_2022;
	}

	public int getFemmes45_59_2022() {
		return femmes45_59_2022;
	}

	public void setFemmes45_59_2022(int femmes45_59_2022) {
		this.femmes45_59_2022 = femmes45_59_2022;
	}

	public int getFemmes60_74_2022() {
		return femmes60_74_2022;
	}

	public void setFemmes60_74_2022(int femmes60_74_2022) {
		this.femmes60_74_2022 = femmes60_74_2022;
	}

	public int getFemmes75Plus2022() {
		return femmes75Plus2022;
	}

	public void setFemmes75Plus2022(int femmes75Plus2022) {
		this.femmes75Plus2022 = femmes75Plus2022;
	}

	public int getTotalHommes2022() {
		return totalHommes2022;
	}

	public void setTotalHommes2022(int totalHommes2022) {
		this.totalHommes2022 = totalHommes2022;
	}

	public int getHommes0_4_2022() {
		return hommes0_4_2022;
	}

	public void setHommes0_4_2022(int hommes0_4_2022) {
		this.hommes0_4_2022 = hommes0_4_2022;
	}

	public int getHommes5_9_2022() {
		return hommes5_9_2022;
	}

	public void setHommes5_9_2022(int hommes5_9_2022) {
		this.hommes5_9_2022 = hommes5_9_2022;
	}

	public int getHommes10_14_2022() {
		return hommes10_14_2022;
	}

	public void setHommes10_14_2022(int hommes10_14_2022) {
		this.hommes10_14_2022 = hommes10_14_2022;
	}

	public int getHommes15_19_2022() {
		return hommes15_19_2022;
	}

	public void setHommes15_19_2022(int hommes15_19_2022) {
		this.hommes15_19_2022 = hommes15_19_2022;
	}

	public int getHommes20_29_2022() {
		return hommes20_29_2022;
	}

	public void setHommes20_29_2022(int hommes20_29_2022) {
		this.hommes20_29_2022 = hommes20_29_2022;
	}

	public int getHommes30_44_2022() {
		return hommes30_44_2022;
	}

	public void setHommes30_44_2022(int hommes30_44_2022) {
		this.hommes30_44_2022 = hommes30_44_2022;
	}

	public int getHommes45_59_2022() {
		return hommes45_59_2022;
	}

	public void setHommes45_59_2022(int hommes45_59_2022) {
		this.hommes45_59_2022 = hommes45_59_2022;
	}

	public int getHommes60_74_2022() {
		return hommes60_74_2022;
	}

	public void setHommes60_74_2022(int hommes60_74_2022) {
		this.hommes60_74_2022 = hommes60_74_2022;
	}

	public int getHommes75Plus2022() {
		return hommes75Plus2022;
	}

	public void setHommes75Plus2022(int hommes75Plus2022) {
		this.hommes75Plus2022 = hommes75Plus2022;
	}

	public int getTotalQpv2022() {
		return totalQpv2022;
	}

	public void setTotalQpv2022(int totalQpv2022) {
		this.totalQpv2022 = totalQpv2022;
	}

	public int getFemmesQpv2022() {
		return femmesQpv2022;
	}

	public void setFemmesQpv2022(int femmesQpv2022) {
		this.femmesQpv2022 = femmesQpv2022;
	}

	public int getHommesQpv2022() {
		return hommesQpv2022;
	}

	public void setHommesQpv2022(int hommesQpv2022) {
		this.hommesQpv2022 = hommesQpv2022;
	}
	
	

    
    
    
}

