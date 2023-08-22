package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class modifyCustomerController {

    @FXML
    private TextField modCustomerAddress;

    @FXML
    private Button modCustomerCancel;

    @FXML
    private ChoiceBox<?> modCustomerCountry;

    @FXML
    private TextField modCustomerID;

    @FXML
    private TextField modCustomerName;

    @FXML
    private TextField modCustomerPhoneNumber;

    @FXML
    private TextField modCustomerPostalCode;

    @FXML
    private Button modCustomerSave;

    @FXML
    private ChoiceBox<?> modCustomerStateProvince;

    Stage stage;
    Parent scene;
    @FXML
    void onActionModCustomerCancelButton(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentsMain.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionModCustomerSaveButton(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/appointmentsMain.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

}
