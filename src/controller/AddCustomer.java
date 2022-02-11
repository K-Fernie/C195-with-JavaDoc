package controller;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Countries;
import model.Customers;
import model.Divisions;
import utils.sceneSwitch;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import utils.Alerts;

/**
 * Class AddCustomer adds a new customer to the database based on user input in fields and comboboxes.
 */
public class AddCustomer implements Initializable {

    /**
     * Observable lists used to pass information to the table and comboboxes.
     */
    private ObservableList<Divisions> comboDivisions = Divisions.getAllDivisions();
    private ObservableList<Countries> comboCountries = Countries.getAllCountries();

    /**
     * FXML fields, lables, text boxes and comboboxes used to capture customer data.
     */
    @FXML
    private TextField addCustAddressTxt;

    @FXML
    private TextField addCustIdTxt;

    @FXML
    private TextField addCustNameTxt;

    @FXML
    private Label addCustNotificationLbl;

    @FXML
    private TextField addCustPhoneNumberTxt;

    @FXML
    private TextField addCustPostalCodeTxt;

    @FXML
    private ComboBox<Countries> countryComboBox;

    @FXML
    private ComboBox<Divisions> stateComboBox;

    /**
     * onActionCustCloseBtn is used to switch from Add Customer form to main screen
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionAddCustCloseBtn(ActionEvent event) throws IOException {

      sceneSwitch.switchToMain(event);

    }

    /**
     * onActionAddCustSaveBtn is used to insert customer data captured in the form to the database.
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    void onActionAddCustSaveBtn(ActionEvent event) throws SQLException, IOException {


      int customerId = 0;
      String customerName = addCustNameTxt.getText();
      String address = addCustAddressTxt.getText();
      String postalCode = addCustPostalCodeTxt.getText();
      String phone = addCustPhoneNumberTxt.getText();
      int countryId = 0;
      String state = String.valueOf(stateComboBox.getValue());
      int divId = Divisions.getCurrentDivisionId(state);

/**
 * if else statments used to validate that no items are left unselected or blank
 */
        if(customerName.isBlank())
        {
            Alerts.nameMissing();
        }
        else if(address.isBlank())
        {
            Alerts.addressMissing();
        }
        else if(postalCode.isBlank())
        {
            Alerts.postalCodeMissing();
        }
        else if(phone.isBlank())
        {
            Alerts.phoneMissing();
        }
        else
        {
            Customers.addCustomer(customerId, customerName, address, postalCode, phone, countryId, state, divId);
            addCustNotificationLbl.setText( customerName + " Has Successfully Been Added");
            addCustNameTxt.clear();
            addCustAddressTxt.clear();
            addCustPostalCodeTxt.clear();
            addCustPhoneNumberTxt.clear();
            Customers.clearAllCustomers();
            Customers.setAllCustomers();
        }


    }

    /**
     * onActionCountryComboBox is used to create a dependent relationship between the country combobox and the state combobox.
     * On Action the State list is filtered.
     * @param event
     * @throws SQLException
     */
    @FXML
    void onActionCountryComboBox(ActionEvent event) throws SQLException {

        int id = countryComboBox.getSelectionModel().getSelectedItem().getCountryId();
        ObservableList<Divisions> newStateList = Divisions.countrySelectionStateReturn(id);
        stateComboBox.setItems(newStateList);
        stateComboBox.getSelectionModel().selectFirst();

    }

    @FXML
    void onActionStateComboBox(ActionEvent event) throws SQLException {


    }

    /**
     * Initializes the objects contained in the state and country comboboxes.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        stateComboBox.setItems(comboDivisions);
        stateComboBox.setVisibleRowCount(5);
        stateComboBox.getSelectionModel().selectFirst();

        countryComboBox.setItems(comboCountries);
        countryComboBox.getSelectionModel().selectFirst();
        stateComboBox.setVisibleRowCount(5);

    }
}
