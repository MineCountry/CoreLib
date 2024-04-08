package eu.minecountry.core.common;

import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;

/**
 * Represents an abstract implementation of a logger.
 *
 * @since 1.0.0
 */
public interface CustomLogger {

    /**
     * Logs the given message prefixed with the level. Supports formatted messages.
     *
     * @param level   The level
     * @param message The message
     * @param args    Replacements for formatted messages
     */
    void log(@NotNull Level level, @NotNull String message, Object... args);

    /**
     * Logs the given message with {@link Level#INFO} as the log level. Supports formatted messages.
     *
     * @param message The message
     * @param args    Replacements for formatted messages
     */
    void info(@NotNull String message, Object... args);

    /**
     * Logs the given message with {@link Level#WARNING} as the log level. Supports formatted messages.
     *
     * @param message The message
     * @param args    Replacements for formatted messages
     */
    void warning(@NotNull String message, Object... args);

    /**
     * Logs the given message with {@link Level#SEVERE} as the log level. Supports formatted messages.
     *
     * @param message The message
     * @param args    Replacements for formatted messages
     */
    void error(@NotNull String message, Object... args);

}
