package com.ravekidd.service.helpers;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Helper class for handling output-related operations.
 */
@Component
public class OutputHelper {

    /**
     * Transforms a LocalDateTime object to a string in the format "dd.MM.yyyy".
     *
     * @param dateTime The LocalDateTime object to be transformed.
     * @return A string representation of the LocalDateTime in "dd.MM.yyyy" format.
     */
    public String transformDateToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return dateTime.format(formatter);
    }
}
