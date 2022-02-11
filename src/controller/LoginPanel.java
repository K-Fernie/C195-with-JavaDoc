package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Appointments;
import model.Users;
import utils.Alerts;
import utils.Logger;
import utils.sceneSwitch;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Login Panel is used as the primary screen when program is launched.
 * Validates user login information.
 * Can be viewed in both english and french
 */
public class LoginPanel implements Initializable {
    /**
     * Initializing variables to be used for time zone collection and transformation.
     */
    Locale currentLocale = Locale.getDefault();
    LocalDateTime loginTime;
    LocalDateTime now = loginTime.now();
    String formattedTime = now.format(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss"));

    /**
     * Initializing FXML items and handlers to capture and transform data.
     */
    @FXML
    private TextField loginPanelPasswordTxt;

    @FXML
    private Label consultantSchedulingLbl;

    @FXML
    private TextField loginPanelUsernameTxt;

    @FXML
    private Label zoneIdLbl;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button loginBtn;

    /**
     * onClickCancelBtn will exit the user out of the program when clicked.
     * @param event
     */
    @FXML
    void onClickCancelBtn(ActionEvent event) {
        System.out.println("Exiting...");
        System.exit(0);
    }

    /**
     * onClickLoginBtn is used to capture username and password input.
     * On successful login checks for upcoming appointments within 15 minutes.
     * On unsuccessful login checks for the information in the username field and logs it in an external file.
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    void onClickLoginBtn(ActionEvent event) throws IOException, SQLException {

        String userName = loginPanelUsernameTxt.getText();
        String password = loginPanelPasswordTxt.getText();
        boolean valid = Users.login(userName, password);


        if(valid){

            int id = Users.getCurrentUser().getUserId();
            Appointments.appointmentInFifteen(id);
            String append = "LOGIN ATTEMPT || ATTEMPT DATE:" +formattedTime+" || USERNAME ENTERED:" +userName+" || RESULT: SUCCESS || LANG: EN";
            Logger.Logger(append);
            sceneSwitch.switchToMain(event);

        }
        else if(currentLocale.getLanguage().equals("fr"))
        {
            Alerts.frLoginError();
                String append = "LOGIN ATTEMPT || ATTEMPT DATE:" +formattedTime+" || USERNAME ENTERED:" +userName+" || RESULT: FAIL || LANG: FR";
                Logger.Logger(append);
        }
        else{
            Alerts.loginError();
                String append = "LOGIN ATTEMPT || ATTEMPT DATE:" +formattedTime+" || USERNAME ENTERED:" +userName+"|| RESULT: FAIL || LANG: EN";
                Logger.Logger(append);

        }


    }

    /**
     * Initializes the zone Id information to be shown on the login page.
     * Sets labels and text if the language of the system is set to french.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //---------- ZoneId Information -----------------------//
        ZoneId zoneId = ZoneId.systemDefault();
        zoneIdLbl.setText(String.valueOf(zoneId));
        //__________ changing language information ___________//
       if(currentLocale.getLanguage().equals("fr"))
       {
           consultantSchedulingLbl.setText("Connexion à la planification des consultants");
           loginBtn.setText("Connexion");
           cancelBtn.setText("Annuler");
           loginPanelUsernameTxt.setPromptText("Nom d'utilisateur");
           loginPanelPasswordTxt.setPromptText("Mot de passe");
       }





    }
}
