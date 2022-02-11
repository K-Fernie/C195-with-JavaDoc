package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * User class is used to store User information used for Login and time transformation
 */
public class Users {

    //--------- USER VARIABLES --------------------------//
    private int userId;
    private String userName;
    private String password;
    private static Users currentUser;
    private static ZoneId zoneId;
    static ObservableList<Users> allUsers = FXCollections.observableArrayList();

    //-------Constructor-------------------------------//

    /**
     * Constructor used to define the user object
     * @param userId
     * @param userName
     */
    public Users(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    /**
     * getCurrent user is used to get the current user
     * @return
     */
    // -------------GETTERS AND SETTERS ----------------//
    public static Users getCurrentUser()
    {
        return currentUser;
    }

    /**
     * getUserId is used to get the user Id from the list
     * @return
     */
    public int getUserId() {
        return userId;
    }

    /**
     * setUserId is used to set the User Id
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * getUserName is used to get the user name from the local list
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * setUserName is used to set the userName
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * getPassword is used to retrieve the password
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * setPassword is used to set the password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * setAllUsers is a method used to query the database and update the observable list with a complete list of Users
     * @throws SQLException
     */

    public static void setAllUsers() throws SQLException
    {
        Statement statement = JDBC.getConnection().createStatement();
        String setUserList = "SELECT * FROM client_schedule.users ";
        ResultSet rs = statement.executeQuery(setUserList);

        while (rs.next())
        {
            int userId = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");

            Users userList = new Users(userId, userName);
            allUsers.add(userList);
        }
    }

    /**
     * getAllUsers is used to return the observable list of all users
     * @return
     */
  public static ObservableList<Users> getAllUsers()
    {
        return allUsers;
    }

    /**
     * login is used to validate username and password entry
     * @param Username
     * @param Password
     * @return
     */
    public static Boolean login(String Username, String Password) {

        try
        {
            Statement statement = JDBC.getConnection().createStatement();
            String checkLogin = "SELECT * FROM client_schedule.users WHERE User_Name='"+Username+"' AND Password='"+Password+"'";
            ResultSet rs = statement.executeQuery(checkLogin);
            if(rs.next())
            {
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                currentUser = new Users(userId, userName);
                currentUser.setUserId(rs.getInt("User_ID"));
                statement.close();

                return Boolean.TRUE;
            }
            else{
                return Boolean.FALSE;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * returns a date instant to localDate
     * @param dateToConvert
     * @return
     */
    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert)
    {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * getAssociated appointments Queries the database and creates a filtered list based on the query
     * @param Id
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointments> getAssociatedAppointments(int Id) throws SQLException {

        ObservableList<Appointments> appointments = FXCollections.observableArrayList();

        Statement statement = JDBC.getConnection().createStatement();
        String setAppointmentList = "SELECT * FROM client_schedule.appointments WHERE User_ID ="+Id+"";
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
     * toString override used to show the userName in a combo list
     * @return
     */
    @Override
    public String toString(){
        return(userName);
    }
}
