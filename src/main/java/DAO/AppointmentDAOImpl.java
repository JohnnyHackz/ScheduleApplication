package DAO;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.stream.Stream;

import static helper.JDBC.connection;

public class AppointmentDAOImpl implements AppointmentDAO {
    ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    //public boolean apptFound;


    @Override
    public ObservableList<Appointment> getAllAppointments() {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointments";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet result = ps.executeQuery()) {

            allAppointments = FXCollections.observableArrayList(
                    // Use Stream.generate to create a Stream of Appointment objects
                    Stream.generate(() -> {
                                try {
                                    // Check if there are more results in the ResultSet
                                    if (!result.next()) {
                                        // If there are no more results, return null to stop further generation
                                        return null; // End of results, return null to stop generation
                                    }

                                    // Extract data from the ResultSet for creating an Appointment object
                                    int apptId = result.getInt("Appointment_ID");
                                    int customerId = result.getInt("Customer_ID");
                                    int userId = result.getInt("User_ID");
                                    int contactId = result.getInt("Contact_ID");
                                    String apptTitle = result.getString("Title");
                                    String apptDesc = result.getString("Description");
                                    String apptLocation = result.getString("Location");
                                    String apptType = result.getString("Type");
                                    LocalDateTime startDtTime = result.getTimestamp("Start").toLocalDateTime();
                                    LocalDateTime endDtTime = result.getTimestamp("End").toLocalDateTime();
                                    LocalDate startDate = startDtTime.toLocalDate();
                                    LocalDate endDate = endDtTime.toLocalDate();
                                    LocalTime startTime = startDtTime.toLocalTime();
                                    LocalTime endTime = endDtTime.toLocalTime();

                                    // Create an Appointment object using the retrieved data
                                    return new Appointment(apptId, customerId, userId, contactId, apptTitle, apptDesc,
                                            apptLocation, apptType, startDtTime, endDtTime, startDate, endDate, startTime, endTime);
                                } catch (SQLException e) {
                                    // If a SQLException occurs during this process, rethrow it as a RuntimeException for error handling
                                    throw new RuntimeException("Error occurred while fetching appointments.", e);
                                }
                            })
                            // Use takeWhile to continue generating Appointment objects until a null is encountered (end of results)
                            .takeWhile(Objects::nonNull)
                            // Collect the generated Appointment objects into an immutable list
                            .toList()
            );


        } catch (SQLException e) {
            // Handle SQLException properly, log it, and throw a runtime exception if needed
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error occurred while fetching appointments.", e);
        } catch (Exception e) {
            // Handle other exceptions, log them, and throw a runtime exception if needed
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error occurred while fetching appointments.", e);
        }

        return allAppointments;
    }

    @Override
    public Appointment getAppt(int apptId) {
        return null;
    }

    @Override//This is where I chanced custId to customerId
    public ObservableList<Appointment> getApptsByCustomer(int customerId) {
        return null;
    }

    @Override
    public ObservableList<Appointment> getApptsByContact(int contactId) {
        return null;
    }

    @Override
    public int updateAppt(int apptId, int customerId, int userId, int contactId, String apptTitle, String apptDesc, String apptLocation, String apptType, LocalDateTime startDtTime, LocalDateTime endDtTime) {
        return 0;
    }

    @Override
    public int deleteAppt(int apptId, int customerId, String apptType) {
        return 0;
    }

    @Override
    public int addAppt(int customerId, int userId, int contactId, String apptTitle, String apptDesc, String apptLocation, String apptType, LocalDateTime startDtTime, LocalDateTime endDtTime) {
        return 0;
    }

    @Override
    public ObservableList<Appointment> lookUpAppt(LocalDate date) {
        return null;
    }

    @Override
    public void upcomingApptAlert(LocalDateTime loginLDT) {

    }

    @Override
    public ObservableList<Appointment> upcomingApptsWeek(LocalDate loginLD) {
        return null;
    }

    @Override
    public ObservableList<Appointment> upcomingApptsMonth(LocalDate loginLD) {
        return null;
    }

    @Override
    public boolean checkApptStartTime(LocalDateTime apptStartTime) {
        return false;
    }

    @Override
    public boolean checkApptEndTime(LocalDateTime apptEndTime) {
        return false;
    }

    @Override
    public boolean checkNewApptForOverlap(int customerId, LocalDate selStartDate, LocalDate selEndDate, LocalTime selStartTime, LocalTime selEndTime) {
        return false;
    }

    @Override
    public boolean checkUpdatedApptForOverlap(int customerId, LocalDate selStartDate, LocalDate selEndDate, LocalTime selStartTime, LocalTime selEndTime, int apptId) {
        return false;
    }


}