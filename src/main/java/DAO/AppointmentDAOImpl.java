package DAO;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


import static helper.JDBC.connection;

public class AppointmentDAOImpl implements AppointmentDAO {
    ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    public boolean apptFound;


    @Override
    public ObservableList<Appointment> getAllAppointments() {
        try {
            String sql = "SELECT * FROM appointments";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                int appointmentId = result.getInt("Appointment_ID");
                int customerId = result.getInt("Customer_ID");
                int userId = result.getInt("User_ID");
                int contactId = result.getInt("Contact_ID");
                String title = result.getString("Title");
                String description = result.getString("Description");
                String location = result.getString("Location");
                String type = result.getString("Type");
                LocalDateTime startDateTime = result.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endDateTime = result.getTimestamp("End").toLocalDateTime();
                LocalDate startDate = startDateTime.toLocalDate();
                LocalDate endDate = endDateTime.toLocalDate();
                LocalTime startTime = startDateTime.toLocalTime();
                LocalTime endTime = endDateTime.toLocalTime();
                Appointment appointment = new Appointment(appointmentId, customerId, userId, contactId, title, description,
                        location, type, startDateTime, endDateTime, startDate, endDate, startTime, endTime);
                allAppointments.add(appointment);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return allAppointments;
    }

    @Override
    public Appointment getAppt(int apptId) {
        return null;
    }

    @Override
    public ObservableList<Appointment> getApptsByCustomer(int custId) {
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