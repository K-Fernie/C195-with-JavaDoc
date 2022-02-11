package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.*;
import utils.Alerts;
import utils.sceneSwitch;
import utils.timeConverter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * The public class AddAppointment is used to Add a new appointment to the database.
 */
public class AddAppointment implements Initializable {
    /**
     * Initializing Observable Lists to store User, Customer and Contact Data.
     * Initializing variables to transform start time based on user location.
     */
    private ObservableList<Users> comboUsers = Users.getAllUsers();
    private ObservableList<Customers> comboCustomers = Customers.getAllCustomers();
    private ObservableList<Contacts> comboContacts = Contacts.getAllContacts();
    int timeStart = timeConverter.setStartHours();
    int secondStart = 0;
    int timeEnd = timeConverter.setEndHours();
    int secondEnd = 0;

    /**
     * Initializing FXML items to be used to handle and transform data.
     */

    @FXML
    private Label addAppNotificationLbl;

    @FXML
    private TextField addApptIDTxt;

    @FXML
    private ComboBox<Customers> customerIdComboBox;

    @FXML
    private TextField addDescriptionTxt;

    @FXML
    private TextField addLocationTxt;

    @FXML
    private TextField addTitleTxt;

    @FXML
    private TextField addTypeTxt;

    @FXML
    private ComboBox<Users> userIdComboBox;

    @FXML
    private DatePicker aptDatePicker;

    @FXML
    private DatePicker aptEndDatePicker;

    @FXML
    private ComboBox<Contacts> contactComboBox;

    @FXML
    private ComboBox<LocalTime> endHourComboBox;

    @FXML
    private ComboBox<LocalTime> hourComboBox;

    /**
     * onActionAddSaveBtn sends start and end time to DB in UTC time to be stored
     * Updates the Appointment object with the information located in the fields and comboboxes.
     * @param event
     * @throws SQLException
     */
    @FXML
    void onActionAddSaveBtn(ActionEvent event) throws SQLException {

        StringBuilder startTime = new StringBuilder("");
                 startTime.append(aptDatePicker.getValue());
                 startTime.append(" ");
                 startTime.append(hourComboBox.getValue());
                 startTime.append(":00");

        StringBuilder endTime = new StringBuilder ("");
                endTime.append(aptEndDatePicker.getValue());
                endTime.append(" ");
                endTime.append(endHourComboBox.getValue());
                endTime.append(":00");

        int appointmentId = 0;
    String title =  addTitleTxt.getText();
    String description = addDescriptionTxt.getText();
    String location = addLocationTxt.getText();
    String type = addTypeTxt.getText();
    String start = String.valueOf(startTime);
    String utcStart = timeConverter.toUTC(start);
    String end = String.valueOf(endTime);
    String utcEnd = timeConverter.toUTC(end);
    int customerId = customerIdComboBox.getValue().getCustomerId();
    int userId = userIdComboBox.getValue().getUserId();
    int contactId = contactComboBox.getValue().getContactId();

    //Validate that data is entered correctly
    if(title.isBlank())
    {
        Alerts.aptTitleMissing();
    }
    else if(description.isBlank())
    {
        Alerts.aptDescriptionMissing();
    }
    else if(location.isBlank())
    {
        Alerts.aptLocationMissing();
    }
    else if(type.isBlank())
    {
        Alerts.aptTypeMissing();
    }
    else if(hourComboBox.getValue().isAfter(endHourComboBox.getValue()) && (aptDatePicker.getValue().isEqual(aptEndDatePicker.getValue()) || aptDatePicker.getValue().isAfter(aptEndDatePicker.getValue())))
    {
        //evaluates if the start time is after the end time (takes into account if the date switches between start and end time)
       Alerts.aptTimeError();
    }
    else if(hourComboBox.getValue().isBefore(endHourComboBox.getValue()) && (aptDatePicker.getValue().isAfter(aptEndDatePicker.getValue())))
    {
        Alerts.aptTimeError();
    }
    else if(hourComboBox.getValue().isBefore(LocalTime.of(timeStart,secondStart )) || endHourComboBox.getValue().isAfter(LocalTime.of(timeEnd, secondEnd)))
    {
        Alerts.outsideofBusinessHours();
    }
    else if(Appointments.checkForOverlap(utcStart, utcEnd, customerId))
    {
        Alerts.appointmentOccuring();
    }
    else
    {
        Appointments.addAppointment(appointmentId,title,description,location,type,utcStart,utcEnd,customerId,userId,contactId);
        addAppNotificationLbl.setText("New Appointment Has Successfully Been Created");
        addTitleTxt.clear();
        addDescriptionTxt.clear();
        addLocationTxt.clear();
        addTypeTxt.clear();
        customerIdComboBox.setValue(null);
        userIdComboBox.setValue(null);
        contactComboBox.setValue(null);
        aptDatePicker.setValue(null);
        hourComboBox.setValue(null);
        endHourComboBox.setValue(null);
        aptEndDatePicker.setValue(null);
        Appointments.clearAllAppointments();
        Appointments.setAllAppointments();
    }
    }

    /**
     * onActionCloseBtn is used to close the form and return to the main screen.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionCloseBtn(ActionEvent event) throws IOException {

        sceneSwitch.switchToMain(event);
    }

    /**
     * Sets items in the comboboxes and sets time to be located in the Start and End Time comboboxes
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        customerIdComboBox.setItems(comboCustomers);
        customerIdComboBox.getSelectionModel().selectFirst();
        userIdComboBox.setItems(comboUsers);
        userIdComboBox.getSelectionModel().selectFirst();
        contactComboBox.setItems(comboContacts);
        contactComboBox.getSelectionModel().selectFirst();


        LocalTime start = LocalTime.of(0,0 );
        LocalTime end = LocalTime.of(23,0);
        LocalDate setDate = LocalDate.now();
        aptDatePicker.setValue(setDate);
        aptEndDatePicker.setValue(setDate);

        while(start.isBefore(end.plusSeconds(1)))
        {
            hourComboBox.getItems().add(start);
            endHourComboBox.getItems().add(start);
            start = start.plusMinutes(15);
            end = start.plusMinutes(15);
        }
        hourComboBox.getSelectionModel().select(LocalTime.of(8,0));
        endHourComboBox.getSelectionModel().select(LocalTime.of(9,0));


    }
}
