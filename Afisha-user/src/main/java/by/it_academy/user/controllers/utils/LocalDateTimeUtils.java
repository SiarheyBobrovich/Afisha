package by.it_academy.user.controllers.utils;

import java.time.LocalDateTime;

public class LocalDateTimeUtils {

    private LocalDateTimeUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Format nanoseconds
     * @param dateTime Source
     * @return new LocalDateTime
     */
    public static LocalDateTime convertNanosToMillis(LocalDateTime dateTime) {
        return dateTime.minusNanos(dateTime.getNano() % 1_000_000);
    }
}
