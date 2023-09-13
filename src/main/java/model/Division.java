package model;

public class Division {
    private int divisionId;
    private String divisionName;
    private int countryId;


    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivision() {
        return divisionName;
    }

    public void setDivision(String division) {
        this.divisionName = division;
    }

    public int getCountryID() {
        return countryId;
    }

    public void setCountryID(int countryID) {
        this.countryId = countryID;
    }

    public Division(int divisionId, String divisionName, int countryID){
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryId = countryID;
    }
}
