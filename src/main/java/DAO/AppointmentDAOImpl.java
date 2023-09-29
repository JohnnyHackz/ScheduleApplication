package DAO;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.util.Objects;
import java.util.stream.Stream;

import static helper.JDBC.connection;

public class AppointmentDAOImpl implements AppointmentDAO {
    ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    ZoneId localZone = ZoneId.systemDefault();
    ZoneId businessZone = ZoneId.of("America/New_York");


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
        String sql = "SELECT * FROM appointments WHERE Appointment_ID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, apptId);

            try (ResultSet result = ps.executeQuery()) {
                if (result.next()) {
                    // Retrieve appointment data from the ResultSet
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

                    // Create and return an Appointment object with the retrieved data
                    return new Appointment(apptId, customerId, userId, contactId, apptTitle, apptDesc,
                            apptLocation, apptType, startDtTime, endDtTime, startDate, endDate, startTime, endTime);
                }
            }
        } catch (SQLException e) {
            // Handle SQLException properly, log it, and throw a runtime exception if needed
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error occurred while fetching the appointment.", e);
        } catch (Exception e) {
            // Handle other exceptions, log them, and throw a runtime exception if needed
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error occurred while fetching the appointment.", e);
        }

        // Return null if no appointment with the given apptId was found
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
    public int updateAppt(int apptId, int customerId, int userId, int contactId, String apptTitle, String apptDesc,
                          String apptLocation, String apptType, LocalDateTime startDtTime, LocalDateTime endDtTime) {
        String sql = "UPDATE appointments SET Customer_ID = ?, User_ID = ?, Contact_ID = ?, " +
                "Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ? " +
                "WHERE Appointment_ID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            ps.setInt(2, userId);
            ps.setInt(3, contactId);
            ps.setString(4, apptTitle);
            ps.setString(5, apptDesc);
            ps.setString(6, apptLocation);
            ps.setString(7, apptType);
            ps.setTimestamp(8, Timestamp.valueOf(startDtTime));
            ps.setTimestamp(9, Timestamp.valueOf(endDtTime));
            ps.setInt(10, apptId);

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                // Rows were updated successfully, return a positive value to indicate success
                return rowsUpdated;
            }
        } catch (SQLException e) {
            // Handle SQLException properly, log it, and throw a runtime exception if needed
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error occurred while updating the appointment.", e);
        } catch (Exception e) {
            // Handle other exceptions, log them, and throw a runtime exception if needed
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error occurred while updating the appointment.", e);
        }

        // Return 0 if no rows were updated (indicating a failure to update the appointment)
        return 0;
    }


    @Override
    public int deleteAppt(int apptId, int customerId) {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ? AND Customer_ID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, apptId);
            ps.setInt(2, customerId);

            int rowsDeleted = ps.executeUpdate();

            if (rowsDeleted > 0) {
                // Rows were deleted successfully, return a positive value to indicate success
                return rowsDeleted;
            }
        } catch (SQLException e) {
            // Handle SQLException properly, log it, and throw a runtime exception if needed
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error occurred while deleting the appointment.", e);
        } catch (Exception e) {
            // Handle other exceptions, log them, and throw a runtime exception if needed
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error occurred while deleting the appointment.", e);
        }

        // Return 0 if no rows were deleted (indicating a failure to delete the appointment)
        return 0;
    }



    @Override
    public int addAppt(int customerId, int userId, int contactId, String apptTitle, String apptDesc,
                       String apptLocation, String apptType, LocalDateTime startDtTime, LocalDateTime endDtTime) {
        String sql = "INSERT INTO appointments (Customer_ID, User_ID, Contact_ID, Title, Description, Location, " +
                "Type, Start, End) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            ps.setInt(2, userId);
            ps.setInt(3, contactId);
            ps.setString(4, apptTitle);
            ps.setString(5, apptDesc);
            ps.setString(6, apptLocation);
            ps.setString(7, apptType);
            ps.setTimestamp(8, Timestamp.valueOf(startDtTime));
            ps.setTimestamp(9, Timestamp.valueOf(endDtTime));

            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                // Rows were inserted successfully, return a positive value to indicate success
                return rowsInserted;
            }
        } catch (SQLException e) {
            // Handle SQLException properly, log it, and throw a runtime exception if needed
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error occurred while adding the appointment.", e);
        } catch (Exception e) {
            // Handle other exceptions, log them, and throw a runtime exception if needed
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error occurred while adding the appointment.", e);
        }

        // Return 0 if no rows were inserted (indicating a failure to add the appointment)
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
    public FilteredList<Appointment> upcomingAppts(LocalDate dateAtLogin, String duration) {
        ObservableList<Appointment> allAppts = FXCollections.observableArrayList();
        allAppts = getAllAppointments();
        FilteredList<Appointment> filteredAppts = new FilteredList<>(allAppts);

        filteredAppts.setPredicate(appointment -> {
            LocalDate apptDate = appointment.getStartDate();

            if ("week".equalsIgnoreCase(duration)) {
                return ((apptDate.isEqual(dateAtLogin) || apptDate.isAfter(dateAtLogin)) &&
                        apptDate.isBefore(dateAtLogin.plusDays(7)));
            } else if ("month".equalsIgnoreCase(duration)) {
                return (apptDate.isEqual(dateAtLogin) || apptDate.isAfter(dateAtLogin)) &&
                        apptDate.getMonth().equals(dateAtLogin.getMonth());
            } else {
                return false;
            }
        });
        return filteredAppts;
    }


    @Override
    public boolean checkApptStartTime(LocalDateTime apptStartTime) {
        ZonedDateTime zonedApptStartTime = apptStartTime.atZone(localZone).withZoneSameInstant(businessZone);
        int hourInBusinessZone = zonedApptStartTime.getHour();

        // Now check if the time in EST is during business hours
        if (hourInBusinessZone >= 8 && hourInBusinessZone < 17) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkApptEndTime(LocalDateTime apptEndTime) {
        ZonedDateTime zonedApptEndTime = apptEndTime.atZone(localZone).withZoneSameInstant(businessZone);
        int hourInBusinessZone = zonedApptEndTime.getHour();

        // Now check if the time in EST is during business hours
        if (hourInBusinessZone >= 8 && hourInBusinessZone <= 17) {
            return true;
        }
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