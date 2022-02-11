package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.JDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Customer class used to construct customer object, get customer information from the database and store it locally
 */
public class Customers {


    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private int countryId;
    private String state;
    private int divisionId;
    private static ObservableList<Customers> allCustomers = FXCollections.observableArrayList();

    /**
     * Customers constructor used to define the customer object that can be constructed.
     * @param customerId
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param countryId
     * @param state
     * @param divisionId
     */
    public Customers(int customerId, String customerName, String address, String postalCode, String phone, int countryId, String state, int divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.countryId = countryId;
        this.state = state;
        this.divisionId = divisionId;
    }

    /**
     * getDivisionId returns the division Id
     * @return
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * getCustomerId is used to retrieve the customerId from the local list
     * @return
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * getCustomerName is used to get the customerName from the local list
     * @return
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * getAddress is used to get the address from the local list
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * getPostalCode is used to get the address from the local list
     * @return
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * getPhone is used to get the phone from the local list
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     * setCustomerId is used to set the cusomterId
     * @param customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * setCustomerName is used to set the customerName
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * setAddress is used to set customer address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * setPostalCode is used to set postal code for customer
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * setPhone is used to set phone number for customer
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * getCountryId is used to get the country Id for the customer
     * @return
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * setcountryId is used to set the country Id for the customer
     * @param countryId
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * getState is used to get the state for the customer
     * @return
     */
    public String getState() {
        return state;
    }

    /**
     * setState is used to set the state for the customer
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * setDivisionId is used to set the Division Id for the customer
     * @param divisionId
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * updateCustomer is used to update an existing customer in the database with information collected from the user
     * @param customerId
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param countryId
     * @param state
     * @param divisionId
     * @throws SQLException
     */
    public static void updateCustomer(int customerId, String customerName, String address, String postalCode, String phone, int countryId, String state, int divisionId) throws SQLException {

        Statement stmt = JDBC.getConnection().createStatement();
        String customerUpdate = "UPDATE client_schedule.customers SET Customer_Name = '"+customerName+"', Address = '"+address+"', Postal_Code = '"+postalCode+"',Phone = '"+phone+"' ,Division_ID = "+divisionId+" WHERE Customer_ID = "+customerId+"";
        stmt.executeUpdate(customerUpdate);
        allCustomers.clear();
        setAllCustomers();
    }

    /**
     * addcustomer is used to add a new customer to the database
     * @param customerId
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param countryId
     * @param state
     * @param divisionId
     * @throws SQLException
     */
    public static void addCustomer(int customerId, String customerName, String address, String postalCode, String phone, int countryId, String state, int divisionId) throws SQLException
    {
        Statement stmt = JDBC.getConnection().createStatement();
        String customerUpdate = "INSERT INTO customers VALUES("+customerId+", '"+customerName+"', '"+address+"', '"+postalCode+"', '"+phone+"', NOW(), 'script', NOW(), 'script', "+divisionId+")";
        stmt.executeUpdate(customerUpdate);
        Customers addcust = new Customers(customerId, customerName, address, postalCode, phone, countryId, state, divisionId);
        allCustomers.add(addcust);
        System.out.println("Customer has been added");

    }

    /**
     * setAllCustomers is used to retrieve the complete list of customers from the database and store them in an array
     *
     * @throws SQLException
     */
    public static void setAllCustomers() throws SQLException
    {
        Statement stmt = JDBC.getConnection().createStatement();
        String customerQuery = "SELECT * FROM client_schedule.customers INNER JOIN client_schedule.first_level_divisions ON customers.Division_ID=first_level_divisions.Division_ID;";

        ResultSet rs = stmt.executeQuery(customerQuery);

        while(rs.next())
        {
            int customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            int countryId = rs.getInt("Country_ID");
            String state = rs.getString("Division");
            int divisionId = rs.getInt("Division_ID");

            Customers cust = new Customers(customerId, customerName, address, postalCode, phone, countryId, state, divisionId);
            allCustomers.add(cust);

        }

    }

    /**
     * getAllcustomers returns the local list of customers that has been created
     * @return
     */
    public static ObservableList<Customers> getAllCustomers()
    {
        return allCustomers;
    }

    /**
     * clearAllCustomers is used to clear the list of customers that has been stored locally
     */
    public static void clearAllCustomers()
    {
        allCustomers.clear();
    }

    /**
     * deleteCustomer is used to delete the customer permanently from the database
     * @param customerId
     * @throws SQLException
     */
    public static void deleteCustomer(int customerId) throws SQLException {

        Statement stmt = JDBC.getConnection().createStatement();
        String customerDelete = "DELETE FROM client_schedule.customers WHERE Customer_ID="+customerId+"";
        stmt.executeUpdate(customerDelete);
        allCustomers.clear();
        setAllCustomers();

    }

    /**
     * searchForAppointments is used to search the database for a customer with the customer Id passed to it
     * @param customerId
     * @return
     * @throws SQLException
     */
    public static boolean searchForAppointments(int customerId) throws SQLException {

        Statement stmt = JDBC.getConnection().createStatement();
        String appointmentQuery = "SELECT * FROM client_schedule.appointments WHERE Customer_ID="+customerId+"";
        ResultSet rs = stmt.executeQuery(appointmentQuery);

        if(rs.next())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * toString override used to display the customerName in the customer combobox
     * @return
     */
    @Override
    public String toString(){
        return(customerName);
    }

}
