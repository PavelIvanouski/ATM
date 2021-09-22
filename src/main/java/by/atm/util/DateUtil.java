package by.atm.util;

import java.time.Duration;
import java.time.LocalDateTime;

public class DateUtil {
    public static Double returnTwoDateDifferenceInHours(LocalDateTime start, LocalDateTime end) {
        Duration duration = Duration.between(start, end);
        Double hours = (duration.getSeconds() * 1.0 / (60 * 60));
        return hours;
    }
}
