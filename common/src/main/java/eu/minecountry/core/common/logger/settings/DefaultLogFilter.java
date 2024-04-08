package eu.minecountry.core.common.logger.settings;

import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Represents a filter for loggers to provide a uniform style of log entry filtering.
 */
public class DefaultLogFilter implements Filter {

    private final Set<Level> loggableLevels = Set.of(Level.INFO, Level.WARNING, Level.SEVERE);

    /**
     * Check if a given log record should be published. <br>
     * A record will be published if its level is {@link Level#INFO}, {@link  Level#WARNING} or {@link Level#SEVERE}.
     *
     * @param record a LogRecord
     * @return true if the log record should be published.
     */
    @Override
    public boolean isLoggable(@NotNull LogRecord record) {
        return loggableLevels.contains(record.getLevel());
    }
}
