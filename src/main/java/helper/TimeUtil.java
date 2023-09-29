package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeUtil {

    public static ObservableList<LocalTime> openForBusiness(ZoneId localZone, ZoneId businessZone,
                                                                  LocalTime businessStart, LocalTime businessEnd) {
        ObservableList<LocalTime> businessHours = FXCollections.observableArrayList();
        ZonedDateTime businessZoneStartTime = ZonedDateTime.of(LocalDate.now(), businessStart, businessZone);
        ZonedDateTime localZoneStartTime = businessZoneStartTime.withZoneSameInstant(localZone);

        ZonedDateTime businessZoneEndTime = ZonedDateTime.of(LocalDate.now(), businessEnd, businessZone);
        ZonedDateTime localZoneEndTime = businessZoneEndTime.withZoneSameInstant(localZone);

        LocalTime localStart = LocalTime.of(localZoneStartTime.getHour(), 0);
        LocalTime localEnd = LocalTime.of(localZoneEndTime.getHour(), 0);

        for (LocalTime time = localStart; !time.isAfter(localEnd); time = time.plusHours(1)) {
            businessHours.add(time);
        }

        return businessHours;
    }

}
