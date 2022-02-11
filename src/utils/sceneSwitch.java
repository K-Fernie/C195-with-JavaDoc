package utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * sceneSwitch is a class used to assist in event handling
 * allows user to switch from one scene to the next
 */
public class sceneSwitch {


    static Parent scene;
    static Stage stage;

    /**
     * switchToMain sends user from current screen to main screen
     * @param event
     * @throws IOException
     */
    public static void switchToMain(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(sceneSwitch.class.getResource("/View/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * switchToAddAppointment sends user from current screen to Add Appointment screen
     * @param event
     * @throws IOException
     */
    public static void switchToAddAppointment(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(sceneSwitch.class.getResource("/View/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * switchToAddCustomer sends user from current screen to Add Customer screen
     * @param event
     * @throws IOException
     */
    public static void switchToAddCustomer(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(sceneSwitch.class.getResource("/View/AddCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * switchToAppointmentReport send the user from the current screen to the Contact Report screen
     * @param event
     * @throws IOException
     */
    public static void switchToAppointmentReport (ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(sceneSwitch.class.getResource("/view/ContactReport.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * switchToClientReport sends user from the current screen to the Client Report screen
     * @param event
     * @throws IOException
     */
    public static void switchToClientReport(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(sceneSwitch.class.getResource("/View/ClientReport.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * switchToConsultantReport sends user from the current screen to the Consultant Report screen
     * @param event
     * @throws IOException
     */
    public static void switchToConsultantReport (ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(sceneSwitch.class.getResource("/View/ConsultantReport.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * switchToLoginPanel sends the user from the current screen to the login panel screen
     * @param event
     * @throws IOException
     */
    public static void switchToLoginPanel (ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(sceneSwitch.class.getResource("/View/LoginPanel.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * switchToModifyAppointment sends user from the current screen to the modify appointment screen
     * @param event
     * @throws IOException
     */
    public static void switchToModifyAppointment(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(sceneSwitch.class.getResource("/View/ModifyAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * switchToModifyCustomer sends user from the current screen to the modify customer screen
     * @param event
     * @throws IOException
     */
    public static void switchToModifyCustomer (ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(sceneSwitch.class.getResource("/View/ModifyCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * switchToViewAppointments sends user from the current screen to View Appointments screen
     * @param event
     * @throws IOException
     */
    public static void switchToViewAppointments (ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(sceneSwitch.class.getResource("/View/ViewAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    }
