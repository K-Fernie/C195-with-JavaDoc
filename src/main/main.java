package main;



import model.*;
import utils.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;
import java.sql.*;


/**
 * The main class is the initializer for the entire program.
 * sets the first view for the program as the login panel
 * Opens database connection
 * Queries database for all object lists needed for data storage and transformation.
 */
public class main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginPanel.fxml"));
        primaryStage.setTitle("First View");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args) throws SQLException, IOException {

        JDBC.openConnection();
        Appointments.setAllAppointments();
        Customers.setAllCustomers();
        Divisions.setAllDivisions();
        Countries.setAllCountries();
        Users.setAllUsers();
        Contacts.setAllContacts();


        launch(args);
        JDBC.closeConnection();


    }
}
