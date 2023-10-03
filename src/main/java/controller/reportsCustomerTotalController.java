package controller;

import DAO.ReportsDAO;
import DAO.ReportsDAOImpl;
import helper.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Reports;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**
 * The reportsCustomerTotalController class manages the UI for displaying a summary of customer reports.
 *
 * This controller populates a table with report details about appointments, grouped by type and month.
 * Users can view the total count of appointments for each type within a given month.
 * It also provides a navigation option back to the main reports page.
 */
public class reportsCustomerTotalController implements Initializable {
    private Stage stage;
    private Parent scene;
    public TableView<Reports> reportsTotalAppointmentsTableview;
    public TableColumn reportsAppointmentInformationMonthAppointCol;
    public TableColumn reportsAppointmentInformationTypeAppointCol;
    public TableColumn reportsAppointmentInformationTotalAppointCol;


    /**
     * Sets up and populates the table with report details from the database.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reportsAppointmentInformationTotalAppointCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        reportsAppointmentInformationMonthAppointCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        reportsAppointmentInformationTypeAppointCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        JDBC.openConnection();

        ReportsDAO reportsDAO = new ReportsDAOImpl();
        reportsTotalAppointmentsTableview.setItems(reportsDAO.getAllReports());
        JDBC.closeConnection();
    }

    public void onActionBackToMainReports(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/reportsMain.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        System.out.println("I am clicked");
    }
}
