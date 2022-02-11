package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.JDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The Contacts object is used to help accessing contacts stored in the database
 */
public class Contacts {

    private int contactId;
    private String contactName;
    private String email;
    static ObservableList<Contacts> allContacts = FXCollections.observableArrayList();

    /**
     * Constructor for contacts used when pulling contact information from the database
     * @param contactId
     * @param contactName
     * @param email
     */
    public Contacts(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * getContact Id used to get the contact ID from the object
     * @return
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * setContactId is used to set the contact Id,
     *
     * @param contactId
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * getContactName is used to get the contact name
     * @return
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * setContactName used to set the contact name
     * @param contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * getEmail used to get the contact email
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * setEmail used to set the contact email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * setAllContacts is used to retrieve the contact from the database.
     * adds the list of contacts to the observable list allContacts
     * @throws SQLException
     */
    public static void setAllContacts() throws SQLException
    {
        Statement statement = JDBC.getConnection().createStatement();
        String setUserList = "SELECT * FROM client_schedule.contacts ";
        ResultSet rs = statement.executeQuery(setUserList);

        while (rs.next())
        {
            int contactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");

            Contacts contactList = new Contacts(contactId, contactName, email);
            allContacts.add(contactList);
        }
    }

    /**
     * getAllContacts is used to return the completed list of retrieved contacts.
     * @return
     */
    public static ObservableList<Contacts> getAllContacts()
    {
        return allContacts;
    }

    /**
     * getAssociatedAppointments is used to query the database for appointments associated with a selected contact
     * The query is performed based on the contact Id.
     * @param id
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointments> getAssociatedAppointments(int id) throws SQLException
        {
            ObservableList<Appointments> appointments = FXCollections.observableArrayList();

            Statement statement = JDBC.getConnection().createStatement();
            String setAppointmentList = "SELECT * FROM client_schedule.appointments WHERE Contact_ID ="+id+"";
            ResultSet rs = statement.executeQuery(setAppointmentList);

            while (rs.next())
            {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                String start = rs.getString("Start");
                String end = rs.getString("End");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                Appointments appointmentList = new Appointments(appointmentId,customerId,userId,contactId,title,description,location,type,start,end);
                appointments.add(appointmentList);
            }
            return appointments;
        }


    /**
     * Override toString to present the array with the contactName information
     * @return
     */
    @Override
    public String toString(){
        return(contactName);
    }
}
