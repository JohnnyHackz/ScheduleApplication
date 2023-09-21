package DAO;

import javafx.collections.ObservableList;
import model.Country;

public interface CountryDAO {

    public ObservableList<Country> getAllCountries();

    public Country getCountryId(int countryId);

    public Country getCountryByName(String nameOfCountry);

    public ObservableList<Country> getCountryById();



}
