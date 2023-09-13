package controller;

import DAO.DivisionDataDAO;
import DAO.DivisionDataDAOImpl;
import helper.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class reportsDivisionTotalController {

    Stage stage;
    Parent scene;
    public TableView reportsTotalCustOfDivisionTableview;
    public TableColumn reportsDivisionInformationTotalCustomersCol;
    public TableColumn reportsDivisionInformationDivNameCol;

    public void onActionBacktoReportsMain(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/reportsMain.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("I am clicked");
    }

    public void initialize() {
        reportsDivisionInformationDivNameCol.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
        reportsDivisionInformationTotalCustomersCol.setCellValueFactory(new PropertyValueFactory<>("totalCustomers"));

        try {
            JDBC.openConnection();
            DivisionDataDAO divisionDataDAO = new DivisionDataDAOImpl();
            reportsTotalCustOfDivisionTableview.setItems(divisionDataDAO.getAllDivisionData());
        } finally {
            JDBC.closeConnection(); // Close the connection when done
        }
    }
}