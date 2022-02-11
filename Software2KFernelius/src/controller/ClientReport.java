package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Appointments;
import utils.sceneSwitch;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * ClientReport class is used to filter a view of appointments with customers based on appointment type or month
 */

public class ClientReport implements Initializable {

    /**
     * Observable list used to set Table View
     */
    private ObservableList<Appointments> allAppointments = Appointments.getAllAppointments();

    /**
     * Method monthView is used to filter by month and allow the ability to search type within that filtered list
     * @param month
     * @throws ParseException
     */
    private void monthView(int month) throws ParseException
    {
        ObservableList<Appointments> monthView = FXCollections.observableArrayList();
        Calendar cal = Calendar.getInstance();


        for(int i = 0; i < allAppointments.size(); i++)
        {
            Appointments dateapt = allAppointments.get(i);
            String date = dateapt.getStart();
            Date parseDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            cal.setTime(parseDate);
            int monSearch = cal.get(Calendar.MONTH);

            if(monSearch == month)
            {
                monthView.add(dateapt);
            }

            calendarTableView.setItems(monthView);
            totalLbl.setText(String.valueOf(monthView.size()));
            noteLbl.setText("Start typing to search by type");

        }

        /**
         * Lambda functions are used to filter the monthView dynamically
         * The filter occurs when a user enters type AFTER selecting the month view Radio Button
         */
        FilteredList<Appointments> filteredAppointments = new FilteredList<>(monthView, p -> true);

        searchTxt.textProperty().addListener((observable, oldValue, newValue) ->
        {
            filteredAppointments.setPredicate(appointment ->
            {
                if(newValue == null || newValue.isEmpty())
                {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(appointment.getType().toLowerCase().indexOf(lowerCaseFilter) != -1)
                {
                    return true;
                }
                else
                    return false;
            });
        });

        SortedList<Appointments> sortedAppointments = new SortedList<>(filteredAppointments);
        sortedAppointments.comparatorProperty().bind(calendarTableView.comparatorProperty());
        calendarTableView.setItems(sortedAppointments);
        calendarTableView.setPlaceholder(new Label("No appointments of that type and month selection were found"));

    }

    /**
     * The method lambdaSearch is used to search the table when ALL appointments are visible.
     *
     */
    private void lambdaSearch()
    {
        FilteredList<Appointments> filteredAppointments = new FilteredList<>(allAppointments, p -> true);
            /**
            * Lambda functions are used to filter the monthView dynamically
            * The filter occurs when a user enters type AFTER selecting the month view Radio Button
            */
        searchTxt.textProperty().addListener((observable, oldValue, newValue) ->
        {
            filteredAppointments.setPredicate(appointment ->
            {
                if(newValue == null || newValue.isEmpty())
                {
                    return true;
                }
                //compare ID
                String lowerCaseFilter = newValue.toLowerCase();
                //searching by name
                if(appointment.getType().toLowerCase().indexOf(lowerCaseFilter) != -1)
                {
                    return true;
                }
                else
                    return false;
            });
        });

        SortedList<Appointments> sortedAppointments = new SortedList<>(filteredAppointments);
        sortedAppointments.comparatorProperty().bind(calendarTableView.comparatorProperty());
        calendarTableView.setItems(sortedAppointments);
        calendarTableView.setPlaceholder(new Label("No appointments of that type and month selection were found"));
    }

    /**
     * Initializing labels, anchorpane, radiobuttons and search text to be used for setting and filtering the tableview
     */
    @FXML
    private Label Menu;

    @FXML
    private Label MenuBack;

    @FXML
    private AnchorPane anchorPaneSlider;

    @FXML
    private RadioButton aprRB;

    @FXML
    private RadioButton augRB;

    @FXML
    private TableColumn<Appointments, Integer> calAppointmentIdCol;

    @FXML
    private TableColumn<Appointments, Integer> calCustomerIdCol;

    @FXML
    private TableColumn<Appointments, String> calDescriptionCol;

    @FXML
    private TableColumn<Appointments, String > calEndTimeCol;

    @FXML
    private TableColumn<Appointments, String> calStartTimeCol;

    @FXML
    private TableColumn<Appointments, String> calTitleCol;

    @FXML
    private TableColumn<Appointments, Integer> calUserIdCol;

    @FXML
    private TableView<Appointments> calendarTableView;

    @FXML
    private TableColumn<Appointments, String > location;

    @FXML
    private RadioButton allRB;

    @FXML
    private RadioButton decRB;

    @FXML
    private RadioButton febRB;

    @FXML
    private RadioButton janRB;

    @FXML
    private RadioButton julRB;

    @FXML
    private RadioButton junRB;

    @FXML
    private RadioButton marRB;

    @FXML
    private RadioButton mayRB;

    @FXML
    private RadioButton novRB;

    @FXML
    private RadioButton octRB;

    @FXML
    private TextField searchTxt;

    @FXML
    private RadioButton sepRB;

    @FXML
    private Label totalLbl;

    @FXML
    private ToggleGroup monthRadioGroup;

    @FXML
    private TableColumn<Appointments, String> typeCol;

    @FXML
    private Label noteLbl;

    /**
     * onActionAll is used to filter the tableview to show all appointments
     * @param event
     */
    @FXML
    void onActionAll(ActionEvent event) {

        calendarTableView.setItems(allAppointments);
        totalLbl.setText(String.valueOf(allAppointments.size()));
        lambdaSearch();
        noteLbl.setText("");

    }

    /**
     * onActionJan is used to filter the appointments occuring in January when the January RB is selected
     * @param event
     * @throws ParseException
     */
    @FXML
    void onActionJan(ActionEvent event) throws ParseException {
        monthView(0);

    }
    /**
     * onActionFeb is used to filter the appointments occuring in February when the February RB is selected
     * @param event
     * @throws ParseException
     */
    @FXML
    void onActionFeb(ActionEvent event) throws ParseException {
        monthView(1);
    }
    /**
     * onActionMar is used to filter the appointments occuring in March when the March RB is selected
     * @param event
     * @throws ParseException
     */
    @FXML
    void onActionMar(ActionEvent event) throws ParseException {
        monthView(2);
    }
    /**
     * onActionApr is used to filter the appointments occuring in April when the April RB is selected
     * @param event
     * @throws ParseException
     */
    @FXML
    void onActionApr(ActionEvent event) throws ParseException {
        monthView(3);
    }
    /**
     * onActionMay is used to filter the appointments occuring in May when the May RB is selected
     * @param event
     * @throws ParseException
     */
    @FXML
    void onActionMay(ActionEvent event) throws ParseException {
        monthView(4);
    }
    /**
     * onActionJun is used to filter the appointments occuring in June when the June RB is selected
     * @param event
     * @throws ParseException
     */
    @FXML
    void onActionJun(ActionEvent event) throws ParseException {
        monthView(5);
    }
    /**
     * onActionJuly is used to filter the appointments occuring in July when the July RB is selected
     * @param event
     * @throws ParseException
     */
    @FXML
    void onActionJuly(ActionEvent event) throws ParseException {
        monthView(6);
    }
    /**
     * onActionAug is used to filter the appointments occuring in August when the August RB is selected
     * @param event
     * @throws ParseException
     */
    @FXML
    void onActionAug(ActionEvent event) throws ParseException {
        monthView(7);
    }
    /**
     * onActionSep is used to filter the appointments occuring in September when the September RB is selected
     * @param event
     * @throws ParseException
     */
    @FXML
    void onActionSep(ActionEvent event) throws ParseException {
        monthView(8);
    }
    /**
     * onActionOct is used to filter the appointments occuring in October when the October RB is selected
     * @param event
     * @throws ParseException
     */
    @FXML
    void onActionOct(ActionEvent event) throws ParseException {
        monthView(9);
    }
    /**
     * onActionNov is used to filter the appointments occuring in November when the November RB is selected
     * @param event
     * @throws ParseException
     */
    @FXML
    void onActionNov(ActionEvent event) throws ParseException {
        monthView(10);
    }
    /**
     * onActionDec is used to filter the appointments occuring in December when the December RB is selected
     * @param event
     * @throws ParseException
     */
    @FXML
    void onActionDec(ActionEvent event) throws ParseException {
        monthView(11);
    }

    /**
     * onActionReturnBtn changes to the main screen once clicked.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionReturnBtn(ActionEvent event) throws IOException {

        sceneSwitch.switchToMain(event);

    }

    /**
     * Initializes the TableView items along with setting the lambda functionality for the full appointment list.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //--------------INITIALIZE TABLE VIEW -------------------------//
        calendarTableView.setItems(Appointments.getAllAppointments());
        calAppointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        calTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        calStartTimeCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        calEndTimeCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        calUserIdCol.setCellValueFactory(new PropertyValueFactory<>("aptUserId"));
        calCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("aptCustomerId"));
        calDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        totalLbl.setText(String.valueOf(allAppointments.size()));

        //------------------SEARCH FUNCTION LAMBDA--------------------//

       lambdaSearch();

    }
}
