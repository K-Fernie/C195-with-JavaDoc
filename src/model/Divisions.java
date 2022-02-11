package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.JDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Division class is used to retrieve division information from the database
 *
 */
public class Divisions {

    private int divisionId;
    private String divisionState;
    private int divCountryID;
    private static ObservableList<Divisions> allDivisions = FXCollections.observableArrayList();

    /**
     * Constructor used to define the division object
     * @param divisionId
     * @param divisionState
     * @param divCountryID
     */
    public Divisions(int divisionId, String divisionState, int divCountryID) {
        this.divisionId = divisionId;
        this.divisionState = divisionState;
        this.divCountryID = divCountryID;

    }

    /**
     * Constructor used to retrieve the division id based on the state selected
     * @param state
     * @return
     * @throws SQLException
     */
    public static int getCurrentDivisionId(String state) throws SQLException {
        Statement stmt = JDBC.getConnection().createStatement();
        String divisionQuery = "SELECT * FROM client_schedule.first_level_divisions WHERE Division='"+state+"'";
        ResultSet rs = stmt.executeQuery(divisionQuery);
        if(rs.next())
        {
            int divisionId = rs.getInt(1);
            return divisionId;
        }
        return 0;
    }

    /**
     * getDivisionId is used to retrieve the division Id from the local list
     * @return
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * setDivisionId is used to set the division Id
     * @param divisionId
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * getDivisionState is used to get the division state from the local list
     * @return
     */
    public String getDivisionState() {
        return divisionState;
    }

    /**
     * setDivisionState is used to set the division state
     * @param divisionState
     */
    public void setDivisionState(String divisionState) {
        this.divisionState = divisionState;
    }

    /**
     * getDivCountryId is used to get the country ID from the local list
     * @return
     */
    public int getDivCountryID() {
        return divCountryID;
    }

    /**
     * setDivCountryId is used to set
     * @param divCountryID
     */
    public void setDivCountryID(int divCountryID) {
        this.divCountryID = divCountryID;
    }

    /**
     * setAllDivisions is used to set the complete list of divisions from the database
     * @throws SQLException
     */
    public static void setAllDivisions() throws SQLException
    {
        Statement stmt = JDBC.getConnection().createStatement();
        String divisionQuery = "SELECT * FROM client_schedule.first_level_divisions";

        ResultSet rs = stmt.executeQuery(divisionQuery);

        while(rs.next())
        {
            int divisionId = rs.getInt("Division_ID");
            String divisionState = rs.getString("Division");
            int divCountryID = rs.getInt("Country_ID");

            Divisions newDivision = new Divisions(divisionId, divisionState, divCountryID);
            allDivisions.add(newDivision);
        }
    }

    /**
     * getAllDivisions is used to get the local list of all divisions
     * @return
     */
    public static ObservableList<Divisions> getAllDivisions(){
        return allDivisions;
    }

    /**
     * countrySelectionStateReturn is used to query the database for all states related to the country Id
     * @param id
     * @return
     * @throws SQLException
     */
    public static ObservableList<Divisions> countrySelectionStateReturn(int id) throws SQLException {

        ObservableList<Divisions> stateList = FXCollections.observableArrayList();

        Statement statement = JDBC.getConnection().createStatement();
        String setAppointmentList = "SELECT * FROM client_schedule.first_level_divisions WHERE Country_ID="+id+"";
        ResultSet rs = statement.executeQuery(setAppointmentList);

        while (rs.next())
        {
            int divisionId = rs.getInt("Division_ID");
            String state = rs.getString("Division");
            int countryId = rs.getInt("Country_ID");
            Divisions division = new Divisions(divisionId, state, countryId);
            stateList.add(division);
        }
        return stateList;
    }

    /**
     * toString override used to present the divisionState in the comboBox list
     * @return
     */
    @Override
    public String toString(){
        return(divisionState);
    }
}
