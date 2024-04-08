package eu.minecountry.core.common.logger.settings;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Represents a formatter for loggers to provide a uniform style of logging messages.
 */
public class DefaultLogFormatter extends Formatter {

    /**
     * Format the given log record and return the formatted string using the following format: <br>
     * {@code Current time (UTC+1 Europe/Berlin) in HH:mm:ss dd.MM.yyyy [Level] » Message}
     *
     * @param record the log record to be formatted.
     * @return the formatted log record
     */
    @Override
    public String format(@NotNull LogRecord record) {
        var dateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));

        var formattedDate = dateFormat.format(Date.from(Instant.now()));

        return String.format("%s [%s] » %s \n", formattedDate, record.getLevel(), record.getMessage());
    }
}
