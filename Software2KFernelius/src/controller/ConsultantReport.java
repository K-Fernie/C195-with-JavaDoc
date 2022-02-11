package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointments;
import model.Users;
import utils.sceneSwitch;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Class Consultant Report is used to see a list of appointments based on which user has been assigned to the appointment
 *
 */
public class ConsultantReport implements Initializable {
    /**
     * Observable lists used to set the user combobox along with the appointment TableView
     */
    ObservableList<Users> allUsers = Users.getAllUsers();
    ObservableList<Appointments> allAppointments = Appointments.getAllAppointments();
    /**
     * Setting FXML items used to initialize labels, Anchorpanes, text box, and tableviews
     */
    @FXML
    private Label Menu;

    @FXML
    private Label MenuBack;

    @FXML
    private AnchorPane anchorPaneSlider;

    @FXML
    private TableColumn<Appointments, String> calAddressCol;

    @FXML
    private TableColumn<Appointments, String> calAppointmentIdCol;

    @FXML
    private TableColumn<Appointments, String> calContactCol;

    @FXML
    private TableColumn<Appointments, Integer> calCustomerIdCol;

    @FXML
    private TableColumn<Appointments, String> calDescriptionCol;

    @FXML
    private TableColumn<Appointments, String> calEndTimeCol;

    @FXML
    private TableColumn<Appointments, String> calStartTimeCol;

    @FXML
    private TableColumn<Appointments, String> calTitleCol;

    @FXML
    private TableView<Appointments> calendarTableView;

    @FXML
    private ComboBox<Users> consultantChoBox;

    @FXML
    private Label aptTotalLbl;


    /**
     * onActionReturnBtn used to switch from the report to the main screen.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionReturnBtn(ActionEvent event) throws IOException {

       sceneSwitch.switchToMain(event);
    }

    /**
     * onActionFilter is used to set the tableview based on the user selection from the combobox.
     * @param event
     * @throws SQLException
     */
    @FXML
    void onActionFilter(ActionEvent event) throws SQLException {

        int id = consultantChoBox.getValue().getUserId();
        ObservableList<Appointments> apt = Users.getAssociatedAppointments(id);
        calendarTableView.setItems(apt);
        aptTotalLbl.setText(String.valueOf(apt.size()));

    }

    /**
     * Initializes the tableview with appointment items and the combobox with user items.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        calendarTableView.setItems(allAppointments);
        calAppointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        calTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        calStartTimeCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        calEndTimeCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        calCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("aptCustomerId"));
        calDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        calAddressCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        consultantChoBox.setItems(allUsers);
        consultantChoBox.setPromptText("Select A User");
        aptTotalLbl.setText(String.valueOf(allAppointments.size()));

    }
}
