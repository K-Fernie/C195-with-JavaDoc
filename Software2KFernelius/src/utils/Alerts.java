package utils;

import javafx.scene.control.Alert;

/**
 * Alerts class used to send alerts to the User Interface triggered by validation
 */
public class Alerts {

    /**
     * loginError is used to tell the user when the username and password are invalid
     */
    public static void loginError ()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Incorrect Username or Password");
        alert.setContentText("Enter valid Username and Password");
        alert.showAndWait();
    }

    /**
     * login error shown for french users
     */
    public static void frLoginError()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("nom d’utilisateur ou mot de passe incorrect");
        alert.setContentText("Entrez un nom d’utilisateur et un mot de passe valides.");
        alert.showAndWait();
    }

    /**
     * nameMissing is used to show an error when the userName is missing
     */
    public static void nameMissing()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Name Missing");
        alert.setContentText("Please enter a valid name for the customer");
        alert.showAndWait();
    }

    /**
     * addressMissing method is used to show and error when the address isn't entered
     */
    public static void addressMissing()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Address Missing");
        alert.setContentText("Please enter a valid address for the customer");
        alert.showAndWait();
    }

    /**
     * postalcodemissing method is used to show an error when the postalcode isn't entered
     */
    public static void postalCodeMissing()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Postal Code Missing");
        alert.setContentText("Please enter a valid postal code for the customer");
        alert.showAndWait();
    }

    /**
     * phoneMissing method is used to show an error when the phone number field is empty
     */
    public static void phoneMissing()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Phone Missing");
        alert.setContentText("Please enter a valid phone number for the customer");
        alert.showAndWait();
    }

    /**
     * customerMissing is used to show an error when a customer to modify isn't selected
     */
    public static void customerMissing()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("");
        alert.setHeaderText("No Customer Selected");
        alert.setContentText("Please select the customer to modify");
        alert.showAndWait();
    }

    /**
     * delCustomerMissing is used to show an error when the customer to delete isn't selected
     */
    public static void delCustomerMissing()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("");
        alert.setHeaderText("No Customer Selected");
        alert.setContentText("Please select the customer to be deleted");
        alert.showAndWait();
    }

    /**
     * delCustomerAppointmentsExist is used to show an error when the customer to delete has appointments that still exist
     */
    public static void delCustomerAppointmentsExist()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("");
        alert.setHeaderText("Delete Denied");
        alert.setContentText("Appointments for this customer still exist. Delete the associated appointments prior to the deletion of the customer.");
        alert.showAndWait();
    }

    /**
     * aptTitle Missing is used to show an error when the title for the appointment isn't entered
     */
    public static void aptTitleMissing()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("");
        alert.setHeaderText("Appointment Add Error");
        alert.setContentText("Title for appointment is required.");
        alert.showAndWait();
    }

    /**
     * aptDescriptionMissing is used to show an error when the description for the appointment isn't entered
     */
    public static void aptDescriptionMissing()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("");
        alert.setHeaderText("Appointment Add Error");
        alert.setContentText("Description of appointment is required.");
        alert.showAndWait();
    }

    /**
     * aptLocationMissing is used to show an error when the location of the appointment isn't entered
     */
    public static void aptLocationMissing()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("");
        alert.setHeaderText("Appointment Add Error");
        alert.setContentText("Location of appointment is required.");
        alert.showAndWait();
    }

    /**
     * aptTypeMissing is used to show an error when the type of appointment isn't entered
     */
    public static void aptTypeMissing()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("");
        alert.setHeaderText("Appointment Add Error");
        alert.setContentText("Type of appointment is required.");
        alert.showAndWait();
    }

    /**
     * aptCustomerMissing is used to show an error when the customer associated with the appointment isn't selected
     */
    public static void aptCustomerMissing()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("");
        alert.setHeaderText("Appointment Add Error");
        alert.setContentText("Customer associated with appointment is required.");
        alert.showAndWait();
    }

    /**
     * aptUserMissing is used to show an error when the user associated with the appointment isn't selected
     */
    public static void aptUserMissing()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("");
        alert.setHeaderText("Appointment Add Error");
        alert.setContentText("User associated with appointment is required.");
        alert.showAndWait();
    }

    /**
     * aptContactMissing is used to show an error when the contact associated with the appointment isn't selected
     */
    public static void aptContactMissing()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("");
        alert.setHeaderText("Appointment Add Error");
        alert.setContentText("Contact for appointment is required.");
        alert.showAndWait();
    }

    /**
     * aptTimeError is used to show an error when the End Time occurs prior to the Start Time
     */
    public static void aptTimeError()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("");
        alert.setHeaderText("Appointment Add Error");
        alert.setContentText("Start Time for appointment must be before the End Time");
        alert.showAndWait();
    }

    /**
     * appointmentSelectionMissing is used to show an error when an appointment isn't selected for deletion
     */
    public static void appointmentSelectionMissing()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("");
        alert.setHeaderText("No Appointment Selected");
        alert.setContentText("Please select the appointment to be deleted");
        alert.showAndWait();
    }

    /**
     * appointmentModSelectionMissing is used to show an error when an appointment to update isn't selected
     */
    public static void appointmentModSelectionMissing()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("");
        alert.setHeaderText("No Appointment Selected");
        alert.setContentText("Please select the appointment to be updated");
        alert.showAndWait();
    }

    /**
     * outsideofBusinessHours is used to show an error when the appointment occurs outside of EST business hours
     */
    public static void outsideofBusinessHours()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("");
        alert.setHeaderText("Time Selection Error");
        alert.setContentText("Please ensure the appointment is during business hours. Business hours are 8 a.m. to 10 p.m. EST");
        alert.showAndWait();
    }

    /**
     * appointmentOccuring is used to show a notification in the UI when the user logs in and an appointment is occuring within fifteen minutes of login
     */
    public static void appointmentOccuring()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Time Selection Error");
        alert.setContentText("There is a conflicting appointment scheduled at this time for this customer please select another time");
        alert.showAndWait();
    }
}
