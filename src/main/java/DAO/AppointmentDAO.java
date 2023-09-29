package DAO;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import model.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface  AppointmentDAO {

    public ObservableList<Appointment> getAllAppointments();

    public Appointment getAppt(int apptId);

    public ObservableList<Appointment> getApptsByCustomer(int customerId);

    public ObservableList<Appointment> getApptsByContact(int contactId);

    public int updateAppt(int apptId, int customerId, int userId, int contactId, String apptTitle, String apptDesc,
                          String apptLocation, String apptType, LocalDateTime startDtTime, LocalDateTime endDtTime);

    public int deleteAppt(int apptId, int customerId);

    public int addAppt(int customerId, int userId, int contactId, String apptTitle, String apptDesc,
                       String apptLocation, String apptType, LocalDateTime startDtTime, LocalDateTime endDtTime);

    public ObservableList<Appointment> lookUpAppt(LocalDate date);

    public void upcomingApptAlert(LocalDateTime loginLDT);

    // public ObservableList<Appointment> upcomingApptsWeek(LocalDate loginLD);

    //public ObservableList<Appointment> upcomingApptsMonth(LocalDate loginLD);

    public FilteredList<Appointment> upcomingAppts(LocalDate dateAtLogin, String duration);

    public boolean checkApptStartTime(LocalDateTime apptStartTime);

    public boolean checkApptEndTime(LocalDateTime apptEndTime);

    public boolean checkNewApptForOverlap(int customerId, LocalDate selStartDate, LocalDate selEndDate, LocalTime selStartTime,
                                          LocalTime selEndTime);

    public boolean checkUpdatedApptForOverlap(int customerId, LocalDate selStartDate, LocalDate selEndDate, LocalTime selStartTime,
                                              LocalTime selEndTime, int apptId);}

