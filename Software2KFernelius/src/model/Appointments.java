package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import utils.JDBC;
import utils.timeConverter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Appointment object is used to capture the appointment information from the database
 * Sets methods used within the program to transform and pass data.
 */
public class Appointments {

    private int appointmentId;
    private int aptCustomerId;
    private int aptUserId;
    private int aptContactId;
    private String title;
    private String description;
    private String location;
    private String type;
    private String start;
    private String end;
    private String contactName;
    private String email;
    private static ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

    /**
     * Constructor for appointments used to store an appointment object and send it throughout the program.
     * @param appointmentId
     * @param aptCustomerId
     * @param aptUserId
     * @param aptContactId
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     */
    public Appointments(int appointmentId, int aptCustomerId, int aptUserId, int aptContactId, String title, String description, String location, String type, String start, String end) {
        this.appointmentId = appointmentId;
        this.aptCustomerId = aptCustomerId;
        this.aptUserId = aptUserId;
        this.aptContactId = aptContactId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
    }

    /**
     * Method used to get the CustomerId
     * @return
     */
    public int getAptCustomerId() {
        return aptCustomerId;
    }

    /**
     * Method used to set the apt customerId
     * @param aptCustomerId
     */
    public void setAptCustomerId(int aptCustomerId) {
        this.aptCustomerId = aptCustomerId;
    }

    /**
     * method used to get user ID associated with the appointment
     * @return
     */
    public int getAptUserId() {
        return aptUserId;
    }

    /**
     * method used to set user id associated with the appointment
     * @param aptUserId
     */
    public void setAptUserId(int aptUserId) {
        this.aptUserId = aptUserId;
    }

    /**
     * method used to get the apt contactId
     * @return
     */
    public int getAptContactId() {
        return aptContactId;
    }

    /**
     * method used to set the apt contact id
     * @param aptContactId
     */
    public void setAptContactId(int aptContactId) {
        this.aptContactId = aptContactId;
    }

    /**
     * Method used to get the appointemnt Id
     * @return
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * Method used to set the appointmentId
     * @param appointmentId
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * Method used to get the appointment title
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Method used to set the appointment title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Method used to get the appointment description
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method used to set the appointment description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * method used to get the appointment location
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     * Method used to set the appointment location
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Method used to set the appointment location
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Method used to set the appointment type
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Method used to get the start time of the appointment
     * @return
     */
    public String getStart() {
        return start;
    }

    /**
     * Method used to get the end time of the appointment
     * @return
     */
    public String getEnd() {
        return end;
    }

    /**
     * Method used to set end time of the appointment
     * @param end
     */
    public void setEnd(String end) {
        this.end = end;
    }

    /**
     * Method used to get the contact name of the contact associated with the appointment
     * @return
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Method used to set the contact name of the contact assocated with the appointment
     * @param contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Method used to update an existing appointment in the database with the following:
     * @param appointmentId
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param customerId
     * @param userId
     * @param contactId
     * @throws SQLException
     */
    public static void updateAppointment(int appointmentId, String title, String description, String location, String type, String start, String end, int customerId, int userId, int contactId) throws SQLException
    {
        Statement stmt = JDBC.getConnection().createStatement();
        String aptUpdate = "UPDATE client_schedule.appointments SET Title='"+title+"', Description='"+description+"', Location='"+location+"', Type='"+type+"', Start='"+start+"', End='"+end+"', Customer_ID="+customerId+", User_ID="+userId+", Contact_ID="+contactId+" WHERE Appointment_ID="+appointmentId+"";
        stmt.executeUpdate(aptUpdate);
        allAppointments.clear();
        setAllAppointments();
        System.out.println("This is working");

    }

    /**
     * Returns all appointments from a local list
     * @return
     */
    public static ObservableList<Appointments> getAllAppointments()
    {
        return allAppointments;
    }

    /**
     * Method used to permanently delete the appointment from the database and reset the table view with the updated list
     * @param appointmentId
     * @throws SQLException
     */
    public static void deleteAppointment(int appointmentId) throws SQLException {

        Statement stmt = JDBC.getConnection().createStatement();
        String appointmentDelete = "DELETE FROM client_schedule.appointments WHERE Appointment_ID="+appointmentId+"";
        stmt.executeUpdate(appointmentDelete);
        allAppointments.clear();
        setAllAppointments();

    }

    /**
     * Method used to setAllAppointments that is used when the program is started to capture appointment information locally
     * @throws SQLException
     */
    public static void setAllAppointments() throws SQLException {

            Statement stmt = JDBC.getConnection().createStatement();
            String appointmentQuery = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID FROM appointments";
            ResultSet rs = stmt.executeQuery(appointmentQuery);


                while (rs.next()) {
                    int appointmentId = rs.getInt("Appointment_ID");
                    String title = rs.getString("Title");
                    String description = rs.getString("Description");
                    String location = rs.getString("Location");
                    String type = rs.getString("Type");
                    String start = rs.getString("Start");
                    String convertedStart = timeConverter.toLocal(start);
                    String end = rs.getString("End");
                    String convertedEnd = timeConverter.toLocal(end);
                    int aptCustomerId = rs.getInt("Customer_ID");
                    int aptUserId = rs.getInt("User_ID");
                    int aptContactId = rs.getInt("Contact_ID");

                    Appointments aptList = new Appointments(appointmentId, aptCustomerId, aptUserId, aptContactId, title, description, location, type, convertedStart, convertedEnd);

                    allAppointments.add(aptList);

                }

        }

    /**
     * addAppointment is used to add a new appointment into the database and set a new table view with the updated appointment list
     * @param appointmentId
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param customerId
     * @param userId
     * @param contactId
     * @throws SQLException
     */
    public static void addAppointment(int appointmentId, String title, String description, String location, String type, String start, String end, int customerId, int userId, int contactId) throws SQLException
    {
        Statement stmt = JDBC.getConnection().createStatement();
        String aptUpdate = "INSERT INTO client_schedule.appointments VALUES("+appointmentId+", '"+title+"', '"+description+"', '"+location+"', '"+type+"', '"+start+"', '"+end+"', NOW(), 'script', NOW(), 'script', "+customerId+", "+userId+", "+contactId+")";
        stmt.executeUpdate(aptUpdate);
        allAppointments.clear();
        setAllAppointments();

    }

    /**
     * method that clears the appointment observable list
     */
    public static void clearAllAppointments(){allAppointments.clear();}


    /**
     * method used to check the data base for any appointments within the current users time.
     * Returns a string with the appointment found.
     * @param id
     * @return
     * @throws SQLException
     */
    public static StringBuilder appointmentInFifteen(int id) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        ObservableList<Appointments> fifteenList = FXCollections.observableArrayList();
        LocalDateTime now = LocalDateTime.now();
        String nowTime = now.format(formatter);
        Boolean appointment = false;


        LocalDateTime fifteen = now.plusMinutes(15);
        String fifteenTime = fifteen.format(formatter);

        Statement stmt = JDBC.getConnection().createStatement();
        String aptInFifteen = "SELECT * FROM client_schedule.appointments WHERE User_ID = " + id + " AND Start BETWEEN '" + nowTime + "' AND '" + fifteenTime + "'";
        ResultSet rs = stmt.executeQuery(aptInFifteen);
        StringBuilder appointmentFound = new StringBuilder();
        StringBuilder noAppointmentFound = new StringBuilder();

        if(rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String startTime = rs.getString("Start");
            appointmentFound.append("15 MINUTE WARNING: \nYour next appointment is at " + startTime + " \nAppointment Id: " + appointmentId);
            appointment = true;
        }

        if(appointment){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("ALERT: UPCOMING APPOINTMENT");
            alert.setContentText(String.valueOf(appointmentFound));
            alert.showAndWait();
            return appointmentFound;
        }
        else
        {
            noAppointmentFound.append("You have no appointments within the next 15 minutes");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("No Appointments Found");
            alert.setContentText(String.valueOf(noAppointmentFound));
            alert.showAndWait();
            return noAppointmentFound;
        }

    }

    /**
     * Method used to check if the appointment entered into the database overlaps with any current appointments for the selected customer
     * This search is based on start time end time and customer id
     * @param time
     * @param endTime
     * @param id
     * @return
     * @throws SQLException
     */
    public static boolean checkForOverlap(String time, String endTime, int id) throws SQLException {
        boolean overlap = false;
        Statement stmt = JDBC.getConnection().createStatement();
        String conflict =  "SELECT * FROM client_schedule.appointments WHERE Customer_ID="+id+" AND (('"+time+"' BETWEEN Start AND End) OR ('"+endTime+"' BETWEEN Start AND End)) OR (( Start BETWEEN '"+time+"' AND '"+endTime+"') OR ( End BETWEEN '"+time+"' AND '"+endTime+"'))";
        ResultSet rs = stmt.executeQuery(conflict);

        if(rs.next()) {
            overlap = true;
        }

        return  overlap;
    }

}




