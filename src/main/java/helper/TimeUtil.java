package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeUtil {

    public static ObservableList<LocalTime> generateBusinessHours(ZoneId osZoneId, ZoneId businessZoneId, LocalTime businessStartTime, int workHours) {
        ObservableList<LocalTime> businessHoursList = FXCollections.observableArrayList();
        ZonedDateTime businessZoneDateTime = ZonedDateTime.of(LocalDate.now(), businessStartTime, businessZoneId);
        ZonedDateTime localZoneDateTime = businessZoneDateTime.withZoneSameInstant(osZoneId);
        int localStartHour = localZoneDateTime.getHour();
        int totalLocalHours = localStartHour + workHours;
        int extraHours = 0;

        for (int hour = localStartHour; hour <= totalLocalHours; hour++) {
            if (hour < 24) {
                businessHoursList.add(LocalTime.of(hour, 0));
            }
            if (hour > 23) {
                businessHoursList.add(LocalTime.of(extraHours, 0));
                extraHours += 1;
            }
        }
        return businessHoursList;
    }
}
