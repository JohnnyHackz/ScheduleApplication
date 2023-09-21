package DAO;

import javafx.collections.ObservableList;
import model.Division;

public interface DivisionDAO {
    public ObservableList<Division> getAllDivisions();

    public Division getDivisionId(int divisionId);


    //public ObservableList<Division> fetchCountryDivisions(int divisionId);

    public ObservableList<Division> getDivByCountries(int countryId);


    public int addDivisionByName(String divisionName, int countryId);


    public int updateDivNamebyCountry(String currentDivisionName, int countryId, String newDivisionName);


    public void updateDivCountry(String divisionName, int currentCountryId, int newCountryId);


    public void deleteDivisonIdAndName(int divisionId, String divisionName);




}
