package model;

public class Country {
    private int countryId;
    private String nameOfCountry;

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    public String getNameOfCountry() {
        return nameOfCountry;
    }

    public void setCountryName(String countryName) {
        this.nameOfCountry = nameOfCountry;
    }

    public Country(int countryId, String nameOfCountry){
        this.countryId = countryId;
        this.nameOfCountry = nameOfCountry;
    }

    @Override
    public String toString() {
        return (nameOfCountry);
    }
}
