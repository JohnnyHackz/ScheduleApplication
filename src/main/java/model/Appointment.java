package model;

import java.time.*;

/**
 * The Appointment class represents a scheduling appointment
 * with various attributes such as appointment ID, customer ID, user ID, contact ID, title, description, location,
 * type, and various date and time attributes related to the start and end of the appointment.
 */
public class Appointment {
    private int apptId;
    private int customerId;
    private int userId;
    private int contactId;
    private String apptTitle;
    private String apptDesc;
    private String apptLocation;
    private String apptType;
    private LocalDateTime startDtTime;
    private LocalDateTime endDtTime;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Constructor to initialize all fields of the Appointment class.
     *
     * @param apptId Appointment ID
     * @param customerId Customer ID associated with the appointment
     * @param userId User ID associated with the appointment
     * @param contactId Contact ID associated with the appointment
     * @param apptTitle Title of the appointment
     * @param apptDesc Description of the appointment
     * @param apptLocation Location of the appointment
     * @param apptType Type/category of the appointment
     * @param startDtTime Start date and time of the appointment
     * @param endDtTime End date and time of the appointment
     * @param startDate Start date of the appointment
     * @param endDate End date of the appointment
     * @param startTime Start time of the appointment
     * @param endTime End time of the appointment
     */
    public Appointment(int apptId, int customerId, int userId, int contactId, String apptTitle, String apptDesc,
                       String apptLocation, String apptType, LocalDateTime startDtTime, LocalDateTime endDtTime,
                       LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        this.apptId = apptId;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
        this.apptTitle = apptTitle;
        this.apptDesc = apptDesc;
        this.apptLocation = apptLocation;
        this.apptType = apptType;
        this.startDtTime = startDtTime;
        this.endDtTime = endDtTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Retrieves the appointment ID.
     *
     * @return The appointment ID.
     */
    public int getApptId() {
        return apptId;
    }

    /**
     * Sets the appointment ID.
     *
     * @param apptId The ID to set for the appointment.
     */
    public void setApptId(int apptId) {
        this.apptId = apptId;
    }

    /**
     * Retrieves the customer ID associated with the appointment.
     *
     * @return The customer ID.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID for the appointment.
     *
     * @param customerId The customer ID to set.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Retrieves the user ID associated with the appointment.
     *
     * @return The user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID for the appointment.
     *
     * @param userId The user ID to set.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Retrieves the contact ID associated with the appointment.
     *
     * @return The contact ID.
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Sets the contact ID for the appointment.
     *
     * @param contactId The contact ID to set.
     */
    public void setContactId(int contactId) {this.contactId = contactId;}

    /**
     * Retrieves the title of the appointment.
     *
     * @return The appointment title.
     */
    public String getApptTitle() {
        return apptTitle;
    }

    /**
     * Sets the title for the appointment.
     *
     * @param apptTitle The title to set for the appointment.
     */
    public void setApptTitle(String apptTitle) {
        this.apptTitle = apptTitle;
    }

    /**
     * Retrieves the description of the appointment.
     *
     * @return The appointment description.
     */
    public String getApptDesc() {
        return apptDesc;
    }

    /**
     * Sets the description for the appointment.
     *
     * @param apptDesc The description to set for the appointment.
     */
    public void setApptDesc(String apptDesc) {
        this.apptDesc = apptDesc;
    }

    /**
     * Retrieves the location of the appointment.
     *
     * @return The appointment location.
     */
    public String getApptLocation() {
        return apptLocation;
    }

    /**
     * Sets the location for the appointment.
     *
     * @param apptLocation The location to set for the appointment.
     */
    public void setApptLocation(String apptLocation) {
        this.apptLocation = apptLocation;
    }

    /**
     * Retrieves the type of the appointment.
     *
     * @return The appointment type.
     */
    public String getApptType() {
        return apptType;
    }

    /**
     * Sets the type for the appointment.
     *
     * @param apptType The type to set for the appointment.
     */
    public void setApptType(String apptType) {
        this.apptType = apptType;
    }

    /**
     * Retrieves the start date and time of the appointment.
     *
     * @return The start date and time.
     */
    public LocalDateTime getStartDtTime() {
        return startDtTime;
    }

    /**
     * Sets the start date and time for the appointment.
     *
     * @param startDtTime The start date and time to set for the appointment.
     */
    public void setStartDtTime(LocalDateTime startDtTime) {
        this.startDtTime = startDtTime;
    }

    /**
     * Retrieves the end date and time of the appointment.
     *
     * @return The end date and time.
     */
    public LocalDateTime getEndDtTime() {
        return endDtTime;
    }

    /**
     * Sets the end date and time for the appointment.
     *
     * @param endDtTime The end date and time to set for the appointment.
     */
    public void setEndDtTime(LocalDateTime endDtTime) {
        this.endDtTime = endDtTime;
    }

    /**
     * Retrieves the start date of the appointment.
     *
     * @return The start date.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date for the appointment.
     *
     * @param startDate The start date to set for the appointment.
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Retrieves the end date of the appointment.
     *
     * @return The end date.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date for the appointment.
     *
     * @param endDate The end date to set for the appointment.
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Retrieves the start time of the appointment.
     *
     * @return The start time.
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time for the appointment.
     *
     * @param startTime The start time to set for the appointment.
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Retrieves the end time of the appointment.
     *
     * @return The end time.
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time for the appointment.
     *
     * @param endTime The end time to set for the appointment.
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }



    /**
     * Override of the toString method to provide a formatted string representation of the Appointment object.
     *
     * @return A string representation of the Appointment object.
     */
    @Override
    public String toString() {
        return new StringBuilder()
                .append("Appt: [").append(apptId).append("]")
                .append(" | Customer: [").append(customerId).append("]")
                .append(" | Contact: [").append(contactId).append("]")
                .append(" | Type: ").append(apptType)
                .append(" | Start: ").append(startDtTime)
                .append(" | End: ").append(endDtTime)
                .toString();
    }
}
