package model;

import java.time.*;


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

    public int getApptId() {
        return apptId;
    }

    public void setApptId(int apptId) {
        this.apptId = apptId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getApptTitle() {
        return apptTitle;
    }

    public void setApptTitle(String apptTitle) {
        this.apptTitle = apptTitle;
    }

    public String getApptDesc() {
        return apptDesc;
    }

    public void setApptDesc(String apptDesc) {
        this.apptDesc = apptDesc;
    }

    public String getApptLocation() {
        return apptLocation;
    }

    public void setApptLocation(String apptLocation) {
        this.apptLocation = apptLocation;
    }

    public String getApptType() {
        return apptType;
    }

    public void setApptType(String apptType) {
        this.apptType = apptType;
    }

    public LocalDateTime getStartDtTime() {
        return startDtTime;
    }

    public void setStartDtTime(LocalDateTime startDtTime) {
        this.startDtTime = startDtTime;
    }

    public LocalDateTime getEndDtTime() {
        return endDtTime;
    }

    public void setEndDtTime(LocalDateTime endDtTime) {
        this.endDtTime = endDtTime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return ("Appt: [" + Integer.toString(apptId) + "] | Customer: [" + Integer.toString(customerId) + "] " +
                "| Contact: [" + Integer.toString(contactId) + "] | Type: " + apptType + "| Start: " + startDtTime
                + " | End: " + endDtTime);
    }
}
