package DAO;


import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import model.Appointment;

import java.sql.*;
import java.time.*;
import java.util.Objects;
import java.util.stream.Stream;

import static helper.JDBC.*;

/**
 * Implementation of the AppointmentDAO interface, providing methods for database operations
 * related to appointments.
 */
public class AppointmentDAOImpl implements AppointmentDAO {
    ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    ZoneId localZone = ZoneId.systemDefault();
    ZoneId businessZone = ZoneId.of("America/New_York");


    /**
     * Retrieves all appointments from the database.
     *
     * @return An observable list containing all appointments.
     */
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

                                    return new Appointment(apptId, customerId, userId, contactId, apptTitle, apptDesc,
                                            apptLocation, apptType, startDtTime, endDtTime, startDate, endDate, startTime, endTime);
                                } catch (SQLException e) {
                                    throw new RuntimeException("Error occurred while fetching appointments.", e);
                                }
                            })
                            .takeWhile(Objects::nonNull)
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
    public ObservableList<Appointment> getAllAppointmentsForUser(int userId) {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE User_ID=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int apptId = rs.getInt("Appointment_ID");
                    int customerId = rs.getInt("Customer_ID");
                    int contactId = rs.getInt("Contact_ID");
                    String apptTitle = rs.getString("Title");
                    String apptDesc = rs.getString("Description");
                    String apptLocation = rs.getString("Location");
                    String apptType = rs.getString("Type");
                    LocalDateTime startDtTime = rs.getTimestamp("Start").toLocalDateTime();
                    LocalDateTime endDtTime = rs.getTimestamp("End").toLocalDateTime();
                    LocalDate startDate = startDtTime.toLocalDate();
                    LocalDate endDate = endDtTime.toLocalDate();
                    LocalTime startTime = startDtTime.toLocalTime();
                    LocalTime endTime = endDtTime.toLocalTime();

                    Appointment appointment = new Appointment(apptId, customerId, userId, contactId, apptTitle, apptDesc,
                            apptLocation, apptType, startDtTime, endDtTime, startDate, endDate, startTime, endTime);

                    allAppointments.add(appointment);
                }
            }

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error occurred while fetching the appointments.", e);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error occurred while fetching the appointments.", e);
        }

        return allAppointments;
    }


    /**
     * Retrieves an appointment from the database based on its ID.
     *
     * @param apptId The ID of the appointment.
     * @return An Appointment object if found, otherwise null.
     */
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

    /**
     * Retrieves all appointments associated with a specific customer.
     *
     * @param customerId The ID of the customer.
     * @return An observable list of appointments associated with the customer.
     */
    @Override
    public ObservableList<Appointment> getApptsByCustomer(int customerId) {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE Customer_ID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, customerId);

            try (ResultSet result = ps.executeQuery()) {
                while (result.next()) {
                    int apptId = result.getInt("Appointment_ID");
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

                    Appointment appointment = new Appointment(apptId, customerId, userId, contactId, apptTitle, apptDesc,
                            apptLocation, apptType, startDtTime, endDtTime, startDate, endDate, startTime, endTime);

                    appointments.add(appointment);
                }
            }
        } catch (SQLException e) {
            // Handle SQLException properly, log it, and throw a runtime exception if needed
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error occurred while fetching the appointments.", e);
        } catch (Exception e) {
            // Handle other exceptions, log them, and throw a runtime exception if needed
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error occurred while fetching the appointments.", e);
        }

        return appointments;
    }

    /**
     * Retrieves all appointments associated with a specific contact.
     *
     * @param contactId The ID of the contact.
     * @return An observable list of appointments associated with the contact.
     */
    @Override
    public ObservableList<Appointment> getApptsByContact(int contactId) {
        return null;
    }

    /**
     * Updates an appointment in the database based on the provided parameters.
     *
     * @param apptId The ID of the appointment to be updated.
     * @param customerId The customer ID for the appointment.
     * @param userId The user ID for the appointment.
     * @param contactId The contact ID for the appointment.
     * @param apptTitle The title of the appointment.
     * @param apptDesc The description of the appointment.
     * @param apptLocation The location of the appointment.
     * @param apptType The type of the appointment.
     * @param startDtTime The start date and time of the appointment.
     * @param endDtTime The end date and time of the appointment.
     * @return The number of rows updated in the database (should be 1 if successful).
     */
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
                // Rows were updated successfully
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

        // Return 0 if no rows were updated
        return 0;
    }

    /**
     * Deletes an appointment with the specified appointment ID and customer ID.
     *
     * @param apptId The ID of the appointment to delete.
     * @param customerId The ID of the customer associated with the appointment.
     * @return The number of rows deleted.
     */
    @Override
    public int deleteAppt(int apptId, int customerId) {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ? AND Customer_ID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, apptId);
            ps.setInt(2, customerId);

            int rowsDeleted = ps.executeUpdate();

            if (rowsDeleted > 0) {
                // Rows were deleted successfully
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

        // Return 0 if no rows were deleted
        return 0;
    }


    /**
     * Adds a new appointment to the database.
     *
     * @param customerId The ID of the customer.
     * @param userId The ID of the user.
     * @param contactId The ID of the contact.
     * @param apptTitle The title of the appointment.
     * @param apptDesc The description of the appointment.
     * @param apptLocation The location of the appointment.
     * @param apptType The type of the appointment.
     * @param startDtTime The start time of the appointment.
     * @param endDtTime The end time of the appointment.
     * @return The number of rows inserted.
     */
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
                // Rows were inserted successfully
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

        // Return 0 if no rows were inserted
        return 0;
    }

    /**
     * Filters upcoming appointments based on the specified duration.
     *
     * @param dateAtLogin The current login date.
     * @param duration The duration ("week" or "month") to filter by.
     * @return A filtered list of appointments.
     */
    @Override
    public FilteredList<Appointment> upcomingAppts(LocalDate dateAtLogin, String duration) {
        ObservableList<Appointment> allAppts;
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

    /**
     * Checks if the appointment start time is during business hours.
     *
     * @param apptStartTime The appointment start time.
     * @return True if the start time is during business hours, otherwise false.
     */
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

    /**
     * Checks if the appointment end time is during business hours.
     *
     * @param apptEndTime The appointment end time.
     * @return True if the end time is during business hours, otherwise false.
     */
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


    /**
     * Checks for appointment overlap with existing appointments for a customer.
     *
     * @param customerId The ID of the customer.
     * @param start The start time of the appointment.
     * @param end The end time of the appointment.
     * @return True if there's an overlap, otherwise false.
     */
    @Override
    public boolean appointmentOverlap(int customerId, LocalDateTime start, LocalDateTime end) {
        ObservableList<Appointment> appointments = getApptsByCustomer(customerId);


        if(appointments == null) {
            System.err.println("Error: Appointments list is null. Please ensure the data is retrieved correctly.");
            return false;
        }

        for (Appointment existingAppt : appointments) {
            LocalDateTime existingStart = existingAppt.getStartDtTime();
            LocalDateTime existingEnd = existingAppt.getEndDtTime();

            if ((start.isEqual(existingStart) || start.isAfter(existingStart)) && start.isBefore(existingEnd)) {
                return true; // This means the start time of the new appointment is in the middle of an existing one
            }

            if ((end.isEqual(existingStart) || end.isAfter(existingStart)) && end.isBefore(existingEnd)) {
                return true; // This means the end time of the new appointment is in the middle of an existing one
            }

            if (start.isBefore(existingStart) && end.isAfter(existingEnd)) {
                return true; // This means the new appointment completely overlaps an existing one
            }
        }
        return false;

    }


    /**
     * Checks for appointment overlap, excluding a specific appointment.
     *
     * @param customerId The customer's ID.
     * @param startDtTime The start date and time.
     * @param endDtTime The end date and time.
     * @param apptId The appointment ID to exclude.
     * @return True if there's an overlap, otherwise false.
     */
    @Override
    public boolean appointmentOverlapExceptCurrent(int customerId, LocalDateTime startDtTime, LocalDateTime endDtTime, int apptId) {
        ObservableList<Appointment> appointments = getApptsByCustomer(customerId);

        // Check if the appointments list is null
        if(appointments == null) {
            System.err.println("Error: Appointments list is null. Please ensure the data is retrieved correctly.");
            return false;
        }

        for (Appointment existingAppt : appointments) {
            if(existingAppt.getApptId() == apptId) {
                continue;
            }

            LocalDateTime existingStart = existingAppt.getStartDtTime();
            LocalDateTime existingEnd = existingAppt.getEndDtTime();

            if ((startDtTime.isEqual(existingStart) || startDtTime.isAfter(existingStart)) && startDtTime.isBefore(existingEnd)) {
                return true; // This means the start time of the new appointment is in the middle of an existing one
            }

            if ((endDtTime.isEqual(existingStart) || endDtTime.isAfter(existingStart)) && endDtTime.isBefore(existingEnd)) {
                return true; // This means the end time of the new appointment is in the middle of an existing one
            }

            if (startDtTime.isBefore(existingStart) && endDtTime.isAfter(existingEnd)) {
                return true; // This means the new appointment completely overlaps an existing one
            }
        }

        return false;
    }


    /**
     * Fetches appointments for a user that occur within the next 15 minutes.
     *
     * @param userId The user's ID.
     * @return A list of appointments between now and 15 minutes from now.
     */
    @Override
    public ObservableList<Appointment> getAppointmentsBetweenTimesForUser(int userId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime limit = now.plusMinutes(15);
        return getAppointmentsBetweenTimesForUser(now, limit, userId);
    }


    /**
     * Fetches appointments for a user between two specific times.
     *
     * @param now The start time to fetch from.
     * @param limit The end time to fetch up to.
     * @param userId The user's ID.
     * @return A list of appointments between the specified times.
     */
    public ObservableList<Appointment> getAppointmentsBetweenTimesForUser(LocalDateTime now, LocalDateTime limit, int userId) {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointments WHERE User_ID=? AND Start BETWEEN ? AND ? ORDER BY Start ASC";

        try (
                Connection conn = JDBC.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, userId);
            ps.setTimestamp(2, Timestamp.valueOf(now));
            ps.setTimestamp(3, Timestamp.valueOf(limit));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Appointment appt = extractAppointmentFromResultSet(rs);
                    appointments.add(appt);
                }
            }
        } catch (Exception e) {
            System.out.println("Error retrieving appointments: " + e.getMessage());
        }

        return appointments;
    }


    /**
     * Extracts an appointment object from a result set.
     *
     * @param rs The result set to extract from.
     * @return The extracted appointment.
     * @throws SQLException If there's an issue extracting the appointment.
     */
    private Appointment extractAppointmentFromResultSet(ResultSet rs) throws SQLException {
        LocalDateTime startDateTime = rs.getTimestamp("Start").toLocalDateTime();
        LocalDateTime endDateTime = rs.getTimestamp("End").toLocalDateTime();

        return new Appointment(
                rs.getInt("Appointment_ID"),
                rs.getInt("Customer_ID"),
                rs.getInt("User_ID"),
                rs.getInt("Contact_ID"),
                rs.getString("Title"),
                rs.getString("Description"),
                rs.getString("Location"),
                rs.getString("Type"),
                startDateTime,
                endDateTime,
                startDateTime.toLocalDate(),
                endDateTime.toLocalDate(),
                startDateTime.toLocalTime(),
                endDateTime.toLocalTime()
        );
    }



}