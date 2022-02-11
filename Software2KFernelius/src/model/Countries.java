package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.JDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Countries class is used to retrieve and present Country information pulled from the database
 */
public class Countries {

    private int countryId;
    private String country;
    private static ObservableList<Countries> allCountries = FXCollections.observableArrayList();

    /**
     * Countries constructor is used to model the countries and place them in an observable list
     * @param countryId
     * @param country
     */
    public Countries(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }

    /**
     * getCountryId is used to retrieve the country Id
     * @return
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * setCountryId is used to set the country Id
     * @param countryId
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * getCountry is used to retrieve the Country Name
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     * setCountry is used to set the Country Name
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * setAllCoountries is used when the program starts to query the database and set the primary list of countries for use.
     * @throws SQLException
     */
    public static void setAllCountries() throws SQLException
    {
        Statement stmt = JDBC.getConnection().createStatement();
        String countryQuery = "SELECT * FROM client_schedule.countries";

        ResultSet rs = stmt.executeQuery(countryQuery);

        while(rs.next())
        {
            int countryId = rs.getInt("Country_ID");
            String country = rs.getString("Country");

            Countries newCountry = new Countries(countryId, country);
            allCountries.add(newCountry);
        }
    }

    /**
     * getAllCountries is used to return the observable array of countries pulled from the database
     * @return
     */
    public static ObservableList<Countries> getAllCountries(){

        return allCountries;
    }

    /**
     * toString override to display the country name in the combobox
     * @return
     */
    @Override
    public String toString(){
        return(country);
    }
}
