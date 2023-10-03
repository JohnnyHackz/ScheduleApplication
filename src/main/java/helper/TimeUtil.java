package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Utility class that provides time-related functionalities.
 * <p>
 * This class offers helper methods for performing operations related to time zones and business hours.
 * For instance, it can generate a list of business hours in a local time zone based on the business hours of another zone.
 * </p>
 */
public class TimeUtil {


    /**
     * Generates a list of business hours in a local time zone.
     * <p>
     * This method takes the business hours defined in one time zone.
     * and converts them to the equivalent hours in another time zone.
     * </p>
     *
     * @param localZone     The local time zone where the hours need to be translated.
     * @param businessZone  The time zone where the business hours are defined.
     * @param businessStart The start of business hours in the businessZone.
     * @param businessEnd   The end of business hours in the businessZone.
     * @return An ObservableList of LocalTime objects representing business hours in the localZone.
     */
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
