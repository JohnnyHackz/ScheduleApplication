package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Reports;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static helper.JDBC.connection;

public class ReportsDAOImpl implements ReportsDAO{

    ObservableList<Reports> allReports = FXCollections.observableArrayList();

    @Override
    public ObservableList<Reports> getAllReports() {
        try{
            String sql = "Select monthname(start), type, count(*) as total\n" +
                    "From client_schedule.appointments Group by monthname(start), type;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                String date = rs.getString("monthname(start)");
                String type = rs.getString("type");
                int total = rs.getInt("total");
                Reports reports = new Reports(date, type, total);
                allReports.add(reports);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return allReports;
    }
}
