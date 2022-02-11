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
import utils.Alerts;
import utils.sceneSwitch;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Modify Customer class is used to modify an existing customer and update the database.
 */
public class ModifyCustomer implements Initializable {
    /**
     * Observable lists initialized to be used in the table view and comboboxes.
     */
    private ObservableList<Divisions> comboDivisions = Divisions.getAllDivisions();
    private ObservableList<Countries> comboCountries = Countries.getAllCountries();

    /**
     * sendCustomers method is used to initialize the text fields and comboboxes with the information from the selected customer.
     * @param customer
     * @throws SQLException
     */
    public void sendCustomers(Customers customer) throws SQLException {

        modCustIdTxt.setText(String.valueOf(customer.getCustomerId()));
        modCustNameTxt.setText(String.valueOf(customer.getCustomerName()));
        modCustAddressTxt.setText(String.valueOf(customer.getAddress()));
        modCustPostalCodeTxt.setText(String.valueOf(customer.getPostalCode()));
        modCustPhoneNumberTxt.setText(String.valueOf(customer.getPhone()));
        stateComboBox.getSelectionModel().select(customer.getDivisionId() - 1);


    }

    /**
     * FXML items to be used in data collection and transformation.
     */
    @FXML
    private TextField modCustAddressTxt;

    @FXML
    private TextField modCustIdTxt;

    @FXML
    private TextField modCustNameTxt;

    @FXML
    private Label modCustNotificationLbl;

    @FXML
    private TextField modCustPhoneNumberTxt;

    @FXML
    private TextField modCustPostalCodeTxt;

    @FXML
    private ComboBox<Divisions> stateComboBox;

    @FXML
    private ComboBox<Countries> countryComboBox;

    /**
     * onActionCountryComboBox is used to store country information and filter State information based on country selection.
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
    void onActionStateComboBox(ActionEvent event) {

    }

    /**
     * onActionModCustCloseBtn sends user to the main screen on click.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionModCustCloseBtn(ActionEvent event) throws IOException {
        sceneSwitch.switchToMain(event);
    }

    /**
     * onActionModCustSaveBtn updates the information of the existing customer in the database.
     * @param event
     * @throws SQLException
     */
    @FXML
    void onActionModCustSaveBtn(ActionEvent event) throws SQLException {


        int customerId = Integer.parseInt(modCustIdTxt.getText());
        String customerName = modCustNameTxt.getText();
        String address = modCustAddressTxt.getText();
        String postalCode = modCustPostalCodeTxt.getText();
        String phone = modCustPhoneNumberTxt.getText();
        int countryId = 0;
        String state = String.valueOf(stateComboBox.getValue());
        int divisionId = Divisions.getCurrentDivisionId(state);

        //check that all fields are completed

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
            Customers.updateCustomer(customerId, customerName, address, postalCode, phone, countryId, state, divisionId);
            modCustNotificationLbl.setText( customerName + " Has Successfully Been Updated");
            Customers.clearAllCustomers();
            Customers.setAllCustomers();
        }
    }

    /**
     * initializes the state and country combo boxes with the information.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        stateComboBox.setItems(comboDivisions);
        stateComboBox.setVisibleRowCount(5);
        stateComboBox.setPromptText("Please Select a State");

        countryComboBox.setItems(comboCountries);
    }
}