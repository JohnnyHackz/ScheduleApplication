package DAO;

import javafx.collections.ObservableList;
import model.Division;

public class DivisionDAOImpl implements DivisionDOA {
    @Override
    public ObservableList<Division> getAllDivisions() {
        return null;
    }

    @Override
    public Division getDivisionId(int divisionId) {
        return null;
    }

    @Override
    public int getCountryId(int countryId) {
        return 0;
    }

    @Override
    public ObservableList<Division> fetchCountryDivisions() {
        return null;
    }

    @Override
    public void addDivisionByName(String divisionName, int countryId) {

    }

    @Override
    public void updateDivNameInCountry() {

    }

    @Override
    public void deleteDivisonIdAndName(int divisionId, String divisionName) {

    }

    @Override
    public void updateDivCountryMove() {

    }
}
