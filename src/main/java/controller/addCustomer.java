package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class addCustomer {

    @FXML
    private TextField addCustomerAddress;

    @FXML
    private Button addCustomerCancelButton;

    @FXML
    private ChoiceBox<?> addCustomerCountry;

    @FXML
    private TextField addCustomerID;

    @FXML
    private TextField addCustomerName;

    @FXML
    private TextField addCustomerPhoneNumber;

    @FXML
    private Button addCustomerSaveButton;

    @FXML
    private ChoiceBox<?> addCustomerStateProvidence;

    @FXML
    private AnchorPane addCustomerView;

    @FXML
    private TextField addCustomerZipCode;

    Stage stage;
    Parent scene;

    @FXML
    void onActionAddCustomerCancelButton(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentsMain.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionAddCustomerSaveButton(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentsMain.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

}
