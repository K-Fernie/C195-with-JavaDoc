package controller;


import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.Appointments;
import utils.sceneSwitch;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * ViewAppointments is used to view appointments in the system based on week, day, or month.
 */
public class ViewAppointments implements Initializable {

    /**
     * Observable list used to set the appointment table view
     */
    private final ObservableList<Appointments> allAppointments = Appointments.getAllAppointments();


    /**
     * FXML items used to handle and filter information in the table view.
     */
    @FXML
    private RadioButton thisMonthRB;

    @FXML
    private RadioButton thisWeekRB;

    @FXML
    private RadioButton todayRB;

    @FXML
    private Label Menu;

    @FXML
    private Label MenuBack;

    @FXML
    private AnchorPane anchorPaneSlider;

    @FXML
    private TableColumn<Appointments, String> location;

    @FXML
    private TableColumn<Appointments, Integer> calAppointmentIdCol;

    @FXML
    private TableColumn<Appointments, Integer> calContactCol;

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
    private TableColumn<Appointments, Integer> calUserIdCol;

    @FXML
    private ToggleGroup calendarGroup;

    @FXML
    private Label totalLbl;

    @FXML
    private TableView<Appointments> calendarTableView;

    /**
     * onActionAllTime is used to re-set the table view with all appointments in the system.
     * @param event
     */
    @FXML
    void onActionAllTimeRad(ActionEvent event) {

        calendarTableView.setItems(Appointments.getAllAppointments());
        totalLbl.setText(String.valueOf(allAppointments.size()));
    }

    /**
     * onAction ReturnBtn is used to switch scenes from appointment view to the main screen.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionReturnBtn(ActionEvent event) throws IOException {

        sceneSwitch.switchToMain(event);
    }

    /**
     * onActionThisMonthViewRad filters the table view by the current month and returns a filtered list to set the table view.
     * @param event
     * @throws ParseException
     */
    @FXML
    void onActionThisMonthViewRad(ActionEvent event) throws ParseException {

    ObservableList<Appointments> monthView = FXCollections.observableArrayList();
    Calendar cal = Calendar.getInstance();

    LocalDate today = LocalDate.now();
    Date todayParse = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(today));
    cal.setTime(todayParse);
    int todayMonth = cal.get(Calendar.MONTH);


    for(int i = 0; i < allAppointments.size(); i++)
    {
        Appointments dateapt = allAppointments.get(i);
        String date = dateapt.getStart();
        Date parseDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        cal.setTime(parseDate);
        int month = cal.get(Calendar.MONTH);

            if(todayMonth == month)
            {
                monthView.add(dateapt);
            }

        calendarTableView.setItems(monthView);
        totalLbl.setText(String.valueOf(monthView.size()));

    }

    }
    /**
     * onActionThisWeekViewRad filters the table view by the current mweek and returns a filtered list to set the table view.
     * @param event
     * @throws ParseException
     */
    @FXML
    void onActionThisWeekViewRad(ActionEvent event) throws ParseException {
        ObservableList<Appointments> monthView = FXCollections.observableArrayList();
        Calendar cal = Calendar.getInstance();

        LocalDate today = LocalDate.now();
        Date todayParse = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(today));
        cal.setTime(todayParse);
        int todayWeekMonth = cal.get(Calendar.WEEK_OF_MONTH);


        for(int i = 0; i < allAppointments.size(); i++)
        {
            Appointments dateapt = allAppointments.get(i);
            String date = dateapt.getStart();
            Date parseDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            cal.setTime(parseDate);
            int weekMonth = cal.get(Calendar.WEEK_OF_MONTH);

            if(todayWeekMonth == weekMonth)
            {
                monthView.add(dateapt);
            }

            calendarTableView.setItems(monthView);
            totalLbl.setText(String.valueOf(monthView.size()));

        }
    }
    /**
     * onActionTodayViewRad filters the table view by today and returns a filtered list to set the table view.
     * @param event
     * @throws ParseException
     */
    @FXML
    void onActionTodayViewRad(ActionEvent event) throws ParseException {

        ObservableList<Appointments> monthView = FXCollections.observableArrayList();
        Calendar cal = Calendar.getInstance();

        LocalDate today = LocalDate.now();
        Date todayParse = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(today));
        cal.setTime(todayParse);
        int todayMonth = cal.get(Calendar.MONTH);
        int todayDate = cal.get(Calendar.DATE);


        for(int i = 0; i < allAppointments.size(); i++)
        {
            Appointments dateapt = allAppointments.get(i);
            String date = dateapt.getStart();
            Date parseDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            cal.setTime(parseDate);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DATE);

            if(todayMonth == month && todayDate == day)
            {
                monthView.add(dateapt);
            }

            calendarTableView.setItems(monthView);
            totalLbl.setText(String.valueOf(monthView.size()));

        }

    }

    /**
     * Initializes the table view with appointment information
     * Sets the Lambda information used to move the Anchor Pane slider when the menu label is clicked
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //-----------------SET TABLE VIEW----------------//

        calendarTableView.setItems(Appointments.getAllAppointments());
        calAppointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        calTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        calStartTimeCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        calEndTimeCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        calUserIdCol.setCellValueFactory(new PropertyValueFactory<>("aptUserId"));
        calCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("aptCustomerId"));
        calDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        calContactCol.setCellValueFactory(new PropertyValueFactory<>("aptContactId"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        totalLbl.setText(String.valueOf(allAppointments.size()));

        //--------ANCHOR PANE LAMBDA--------//
        anchorPaneSlider.setTranslateX(0);
        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(anchorPaneSlider);

            slide.setToX(0);
            slide.play();

            anchorPaneSlider.setTranslateX(-176);

            slide.setOnFinished((ActionEvent e)->{
                Menu.setVisible(false);
                MenuBack.setVisible(true);
            });
        });

        MenuBack.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(anchorPaneSlider);

            slide.setToX(-176);
            slide.play();

            anchorPaneSlider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e)->{
                Menu.setVisible(true);
                MenuBack.setVisible(false);

            });
        });
    }
}