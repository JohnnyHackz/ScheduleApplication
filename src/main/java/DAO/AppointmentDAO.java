package DAO;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import model.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Defines the necessary operations for interacting with appointment-related data.
 */
public interface AppointmentDAO {

    /**
     * Retrieves appointments for a specific customer on a specific date.
     *
     * @param customerId   The ID of the customer.
     * @param selStartDate The selected start date.
     * @return An observable list of appointments for the specified customer on the given date.
     */
    static ObservableList<Appointment> getAppointmentsByCustomerIdAndDate(int customerId, LocalDate selStartDate) {
        return null;
    }

    /**
     * Retrieves all appointments.
     *
     * @return An observable list containing all appointments.
     */
    ObservableList<Appointment> getAllAppointments();

    ObservableList<Appointment> getAllAppointmentsForUser(int userId);

    /**
     * Retrieves a specific appointment by its ID.
     *
     * @param apptId The ID of the desired appointment.
     * @return The specified appointment.
     */
    Appointment getAppt(int apptId);

    /**
     * Retrieves all appointments for a specific customer.
     *
     * @param customerId The ID of the customer.
     * @return An observable list of appointments associated with the specified customer.
     */
    ObservableList<Appointment> getApptsByCustomer(int customerId);

    /**
     * Retrieves all appointments associated with a specific contact.
     *
     * @param contactId The ID of the contact.
     * @return An observable list of appointments associated with the specified contact.
     */
    ObservableList<Appointment> getApptsByContact(int contactId);

    /**
     * Updates a specific appointment's details.
     *
     * @param apptId       The ID of the appointment to update.
     * @param customerId   The updated customer ID.
     * @param userId       The updated user ID.
     * @param contactId    The updated contact ID.
     * @param apptTitle    The updated appointment title.
     * @param apptDesc     The updated appointment description.
     * @param apptLocation The updated appointment location.
     * @param apptType     The updated appointment type.
     * @param startDtTime  The updated start date and time.
     * @param endDtTime    The updated end date and time.
     * @return An integer indicating the outcome of the update operation.
     */
    int updateAppt(int apptId, int customerId, int userId, int contactId, String apptTitle, String apptDesc,
                   String apptLocation, String apptType, LocalDateTime startDtTime, LocalDateTime endDtTime);

    /**
     * Deletes a specific appointment.
     *
     * @param apptId     The ID of the appointment to delete.
     * @param customerId The ID of the associated customer.
     * @return An integer indicating the outcome of the delete operation.
     */
    int deleteAppt(int apptId, int customerId);

    /**
     * Adds a new appointment.
     *
     * @param customerId   The ID of the associated customer.
     * @param userId       The ID of the associated user.
     * @param contactId    The ID of the associated contact.
     * @param apptTitle    The appointment title.
     * @param apptDesc     The appointment description.
     * @param apptLocation The appointment location.
     * @param apptType     The appointment type.
     * @param startDtTime  The start date and time.
     * @param endDtTime    The end date and time.
     * @return An integer indicating the outcome of the add operation.
     */
    int addAppt(int customerId, int userId, int contactId, String apptTitle, String apptDesc,
                String apptLocation, String apptType, LocalDateTime startDtTime, LocalDateTime endDtTime);


    /**
     * Filters upcoming appointments based on a specified date and duration.
     *
     * @param dateAtLogin The current date at login.
     * @param duration    The duration to filter by (e.g., week, month).
     * @return A filtered list of upcoming appointments.
     */
    FilteredList<Appointment> upcomingAppts(LocalDate dateAtLogin, String duration);

    /**
     * Checks the start time of an appointment.
     *
     * @param apptStartTime The start time of the appointment.
     * @return True if the appointment start time is valid, otherwise false.
     */
    boolean checkApptStartTime(LocalDateTime apptStartTime);

    /**
     * Checks the end time of an appointment.
     *
     * @param apptEndTime The end time of the appointment.
     * @return True if the appointment end time is valid, otherwise false.
     */
    boolean checkApptEndTime(LocalDateTime apptEndTime);

    /**
     * Verifies if there's any overlapping appointment for a specific customer within a specified time range.
     *
     * @param customerId The customer ID.
     * @param start      The start time to check.
     * @param end        The end time to check.
     * @return True if there's an overlap, otherwise false.
     */
    boolean appointmentOverlap(int customerId, LocalDateTime start, LocalDateTime end);


    /**
     * Checks for overlapping appointments for a customer and time range, excluding the current appointment.
     *
     * @param customerId  The customer ID.
     * @param startDtTime The start date and time.
     * @param endDtTime   The end date and time.
     * @param apptId      The ID of the current appointment.
     * @return True if there's an overlap, otherwise false.
     */
    boolean appointmentOverlapExceptCurrent(int customerId, LocalDateTime startDtTime, LocalDateTime endDtTime, int apptId);

    /**
     * Retrieves appointments within a specific time range for a user.
     *
     * @param userId The user ID.
     * @return An observable list of appointments within the specified time range for the user.
     */
    ObservableList<Appointment> getAppointmentsBetweenTimesForUser(int userId);

    /**
     * Retrieves appointments between two specific times for a user.
     *
     * @param now    The start date and time.
     * @param limit  The end date and time.
     * @param userId The user ID.
     * @return An observable list of appointments between the specified times for the user.
     */
    ObservableList<Appointment> getAppointmentsBetweenTimesForUser(LocalDateTime now, LocalDateTime limit, int userId);
}
