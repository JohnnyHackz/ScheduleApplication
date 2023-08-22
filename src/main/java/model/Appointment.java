package model;

import java.time.LocalDateTime;

public class Appointment {
    private int appID;
    private String appTitle;
    private String appDescription;
    private String appLocation;
    private String appType;
    private LocalDateTime appStartTime;
    private LocalDateTime appEndTime;
    private LocalDateTime appCreateTime;
    private String appCreatedBy;
    private LocalDateTime appLastUpdate;
    private String appLastUpdateBy;
    public int customerID;
    public int userID;
    public int contactID;

    public Appointment(int appID, String appTitle, String appDescription, String appLocation, String appType,
                       LocalDateTime appStartTime, LocalDateTime appEndTime, LocalDateTime appCreateTime,
                       String appCreatedBy, LocalDateTime appLastUpdate, String appLastUpdateBy, int customerID,
                       int userID, int contactID) {
        this.appID = appID;
        this.appTitle = appTitle;
        this.appDescription = appDescription;
        this.appLocation = appLocation;
        this.appType = appType;
        this.appStartTime = appStartTime;
        this.appEndTime = appEndTime;
        this.appCreateTime = appCreateTime;
        this.appCreatedBy = appCreatedBy;
        this.appLastUpdate = appLastUpdate;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    public Appointment(int appID, String appTitle, String appDescription, String appLocation, String appType, LocalDateTime appStartTime, LocalDateTime appEndTime, int customerID, int userID, int contactID) {
    }

    public int getAppID() {
        return appID;
    }

    public void setAppID(int appID) {
        this.appID = appID;
    }

    public String getAppTitle() {
        return appTitle;
    }

    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }

    public String getAppDescription() {
        return appDescription;
    }

    public void setAppDescription(String appDescription) {
        this.appDescription = appDescription;
    }

    public String getAppLocation() {
        return appLocation;
    }

    public void setAppLocation(String appLocation) {
        this.appLocation = appLocation;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public LocalDateTime getAppStartTime() {
        return appStartTime;
    }

    public void setAppStartTime(LocalDateTime appStartTime) {
        this.appStartTime = appStartTime;
    }

    public LocalDateTime getAppEndTime() {
        return appEndTime;
    }

    public void setAppEndTime(LocalDateTime appEndTime) {
        this.appEndTime = appEndTime;
    }

    public LocalDateTime getAppCreateTime() {
        return appCreateTime;
    }

    public void setAppCreateTime(LocalDateTime appCreateTime) {
        this.appCreateTime = appCreateTime;
    }

    public String getAppCreatedBy() {
        return appCreatedBy;
    }

    public void setAppCreatedBy(String appCreatedBy) {
        this.appCreatedBy = appCreatedBy;
    }

    public LocalDateTime getAppLastUpdate() {
        return appLastUpdate;
    }

    public void setAppLastUpdate(LocalDateTime appLastUpdate) {
        this.appLastUpdate = appLastUpdate;
    }

    public String getAppLastUpdateBy() {
        return appLastUpdateBy;
    }

    public void setAppLastUpdateBy(String appLastUpdateBy) {
        this.appLastUpdateBy = appLastUpdateBy;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }
}

