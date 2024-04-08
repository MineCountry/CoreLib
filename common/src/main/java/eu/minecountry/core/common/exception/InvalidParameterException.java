package eu.minecountry.core.common.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a concrete implementation of a custom exception that is thrown when a passed argument/parameter is invalid and does not match the requirements of the invoked method.
 */
public class InvalidParameterException extends CustomException {

    /**
     * Constructs a new exception with the specified detail message. Optionally the message can be formatted with placeholders.
     *
     * @param template The message including placeholders
     * @param args     The replacements
     */
    public InvalidParameterException(@NotNull String template, Object... args) {
        super(template, args);
    }
}
