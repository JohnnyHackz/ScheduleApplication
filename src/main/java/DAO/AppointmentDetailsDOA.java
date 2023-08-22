package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class AppointmentDetailsDOA {


    public static ObservableList<Appointment> getAllAppointments(Connection connection) throws SQLException {
        ObservableList<Appointment> appointmentObservableList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments";

        try (//Connection connection = JDBC.openConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next()) {
                int appID = rs.getInt("Appointment_ID");
                String appTitle = rs.getString("Title");
                String appDescription = rs.getString("Description");
                String appLocation = rs.getString("Location");
                String appType = rs.getString("Type");
                LocalDateTime appStartTime = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appEndTime = rs.getTimestamp("End").toLocalDateTime();
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                Appointment appointment = new Appointment(appID, appTitle, appDescription, appLocation, appType, appStartTime, appEndTime, customerID, userID, contactID);
                appointmentObservableList.add(appointment);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return appointmentObservableList;
    }
}




