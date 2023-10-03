package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class represents the controller for the main reports screen.
 * It manages the user's interactions with the main reports page and provides navigation to various report views.
 */
public class reportsMainController {

    /** The current stage the controller is working with. */
    private Stage stage;

    /** The parent scene for the controller. */
    private Parent scene;

    /**
     * Handles the action when the "Contact Report" button is clicked.
     * Navigates the user to the contact report page.
     *
     * @param actionEvent The action event triggered by the button click.
     * @throws IOException If there's an error loading the contact report view.
     */
    public void onActionContactReport(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/reportsContact.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("I am clicked");
    }

    /**
     * Handles the action when the "Division Report" button is clicked.
     * Navigates the user to the division report page.
     *
     * @param actionEvent The action event triggered by the button click.
     * @throws IOException If there's an error loading the division report view.
     */
    public void onActionDivisionReport(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/reportsDivisionTotal.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("I am clicked");
    }

    /**
     * Handles the action when the "Customer Appointment Report" button is clicked.
     * Navigates the user to the customer appointment report page.
     *
     * @param actionEvent The action event triggered by the button click.
     * @throws IOException If there's an error loading the customer appointment report view.
     */
    public void onActionCustAppReport(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/reportsCustomerTotal.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("I am clicked");
    }

    /**
     * Handles the action when the "Back to Main Screen" button is clicked.
     * Navigates the user back to the main appointments screen.
     *
     * @param actionEvent The action event triggered by the button click.
     * @throws IOException If there's an error loading the main appointments view.
     */
    public void onActionBackToMainScreen(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentsMain.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("I am clicked");
    }
}
