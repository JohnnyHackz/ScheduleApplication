package DAO;

import javafx.collections.ObservableList;
import model.Division;

public interface DivisionDOA {
    public ObservableList<Division> getAllDivisions();

    public Division getDivisionId(int divisionId);

    public int getCountryId(int countryId);

    public ObservableList<Division> fetchCountryDivisions();

    public void addDivisionByName(String divisionName, int countryId);

    public void updateDivNameInCountry();

    public void deleteDivisonIdAndName(int divisionId, String divisionName);

    public void updateDivCountryMove();


}
